<template>
    <div  class="app-picker" >
        <ion-input :disabled="disabled" class="ibz-input" :value="refvalue"  readonly @click="openView"></ion-input>
        <app-mob-icon  v-show="refvalue" class="delete-value right-common-icon" name="close-circle-outline" @onClick="onClear"></app-mob-icon>
        <app-mob-icon  v-show="refvalue == '' || refvalue == null" class="open-picker right-common-icon" name="search-outline" @onClick="openView"></app-mob-icon>
    </div>
</template>
<script lang="ts">
import { Component, Vue, Prop, Model, Watch } from 'vue-property-decorator';
import { Subject, Subscription } from 'rxjs';
import { debounceTime, distinctUntilChanged } from 'rxjs/operators';
// todo 视图打开服务
import { ViewOpenService } from 'ibiz-vue';
import { GlobalService } from 'ibiz-service';
@Component({
    i18n: {
        messages: {
            'ZH-CN': {
                missingParameters: '缺少参数！',
                requestException: '请求异常!',
                formDataIsAbnormal: '表单数据异常!',
                valueItemIsAbnormal: '值项异常!',
                systemIsAbnormal: '系统异常!'
            },
            'EN-US': {
                missingParameters: 'Missing parameters！',
                requestException: 'Request exception！',
                formDataIsAbnormal: 'Form data is abnormal！',
                valueItemIsAbnormal: 'Value item is abnormal！',
                systemIsAbnormal: 'The system is abnormal!'
            }
        }
    }
})
export default class AppPicker extends Vue {

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof AppPicker
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppPicker
     */
    @Prop() public viewparams!: any;

    /**
     * AC参数
     *
     * @type {*}
     * @memberof AppPicker
     */
    @Prop({ default: () => { } }) public acParams?: any;

    /**
     * 表单服务
     *
     * @type {*}
     * @memberof AppPicker
     */
    @Prop() public service?: any;

    /**
     * 应用实体主信息属性名称
     *
     * @type {string}
     * @memberof AppPicker
     */
    @Prop({ default: 'srfmajortext' }) public deMajorField!: string;

    /**
     * 应用实体主键属性名称
     *
     * @type {string}
     * @memberof AppPicker
     */
    @Prop({ default: 'srfkey' }) public deKeyField!: string;

    /**
     * 表单数据
     *
     * @type {*}
     * @memberof AppPicker
     */
    @Prop() public data!: any;

    /**
     * 属性项名称
     *
     * @type {string}
     * @memberof AppPicker
     */
    @Prop() public name!: string;

    /**
     * 是否启用
     *
     * @type {boolean}
     * @memberof AppPicker
     */
    @Prop() public disabled?: boolean;

    /**
     * 类型
     *
     * @type {string}
     * @memberof AppPicker
     */
    @Prop() public editortype?: string;

    /**
     * 视图参数（如：视图name，title，width，height）
     *
     * @type {*}
     * @memberof AppPicker
     */
    @Prop() public pickupView?: any;

    /**
     * 数据链接参数
     *
     * @type {*}
     * @memberof AppPicker
     */
    @Prop() public linkview?: any;


    /**
     * 值项名称
     *
     * @type {string}
     * @memberof AppPicker
     */
    @Prop() public valueitem?: string;

    /**
     * 值
     *
     * @type {*}
     * @memberof AppPicker
     */
    @Model('change') public value!: any;

    /**
     * 导航参数
     *
     * @type {*}
     * @memberof AppSelect
     */
    @Prop() protected navigateParam?: any;

    /**
     * 导航上下文
     *
     * @type {*}
     * @memberof AppSelect
     */
    @Prop() protected navigateContext?: any;

    /**
     * 当前值
     *
     * @type {string}
     * @memberof AppPicker
     */
    public curvalue: string = '';

    /**
     * 下拉数组
     * @type {any[]}
     * @memberof AppPicker
     */
    public items: any[] = [];

    /** 
     * 下拉图标指向状态管理
     * @type {boolean}
     * @memberof AppPicker 
     */
    public open: boolean = false;

    /**
     * 输入状态
     *
     * @type {boolean}
     * @memberof AppPicker
     */
    public inputState: boolean = false;

    /**
      * 当前选择的值
      *
      * @type {string}
      * @memberof AppPicker
      */
    public selectValue = this.value;

    /**
     * 视图打开服务
     *
     * @type {ViewOpenService}
     * @memberof AppPicker
     */
    public openService: ViewOpenService = ViewOpenService.getInstance();

    /**
     * 获取关联数据项值
     *
     * @readonly
     * @memberof AppPicker
     */
    get refvalue() {
        return this.curvalue;
    }

    /**
     * 值变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppPicker
     */
    @Watch('value')
    public onValueChange(newVal: any, oldVal: any) {
        this.curvalue = newVal;
        if (Object.is(this.editortype, 'dropdown') && this.valueitem) {
            const value = this.data[this.valueitem];
            const index = this.items.findIndex((item: any) => Object.is(item.value, value));
            if (index !== -1) {
                return;
            }
            this.items = [];
            if (value) {
                this.items.push({ text: newVal, value: value });
            }
            this.onSearch(newVal, null, false);
        }
    }
    /**
     * 文本输入防抖
     *
     * @private
     * @type {Subject<any>}
     * @memberof IBizFormField
     */
    private inputImageStabilization: Subject<any> = new Subject();

    /**
     * 文本输入防抖订阅对象
     *
     * @private
     * @type {(Subscription | undefined)}
     * @memberof AppPicker
     */
    private inputImageStabilizationEvent: Subscription | undefined;

    /**
     * vue 生命周期
     *
     * @memberof AppPicker
     */
    public created() {
        if (this.acParams && this.service) {
            this.onSearch("", null, true);
        }
        this.inputImageStabilizationEvent = this.inputImageStabilization.pipe(
            debounceTime(200),
            distinctUntilChanged()
        ).subscribe(
            (query: any) => {
                const _query = query;
                if (this.name) {
                    this.$emit('formitemvaluechange', { name: this.name, value: _query ? _query : null });
                }
                this.onSearch(_query, null, false);
            }
        );
    }

    /**
     * vue 生命周期
     *
     * @memberof AppPicker
     */
    public mounted() {
    }

    /**
     * 组件销毁
     *
     * @memberof AppPicker
     */
    public destroyed(): void {
        if (this.inputImageStabilizationEvent) {
            this.inputImageStabilizationEvent.unsubscribe();
        }
    }

    /**
     * 下拉切换回调
     * @param flag 
     */
    public onSelectOpen($event: Event, flag: boolean): void {
        this.open = flag;
        if (this.open) {
            this.onSearch(this.curvalue, null, true);
        }
    }

    /**
     * 执行搜索数据
     * @param query 
     * @param callback 
     */
    public async onSearch(query: any, callback: any, other: boolean): Promise<any> {
        // 公共参数处理
        let data: any = {};
        const bcancel: boolean = this.handlePublicParams(data);
        if (!bcancel) {
            return;
        }
        // 处理搜索参数
        let _context = data.context;
        let _param = data.param;
        query = !query ? '' : query;
        if (!this.inputState && other && Object.is(query, this.value)) {
            query = '';
        }
        this.inputState = false;
        Object.assign(_param, { query: query });
        if (!this.service) {
            // this.$Notice.error(this.$t("missingParameters") + 'service' )
        } else if (!this.acParams.serviceName) {
            // this.$Notice.error(this.$t("missingParameters") + 'serviceName' )
        } else if (!this.acParams.interfaceName) {
            // this.$Notice.error(this.$t("missingParameters") + 'interfaceName')
        } else {
            let { serviceName: _serviceName, interfaceName: _interfaceName }: { serviceName: string, interfaceName: string } = this.acParams;
            const entityService: any = await new GlobalService().getService(_serviceName);
            if (entityService && entityService[_interfaceName] && entityService[_interfaceName] instanceof Function) {
                entityService[_interfaceName](_context, _param).then((response: any) => {
                    if (!response) {
                        this.$Notice.error(`${this.$t("requestException")}`)
                    } else {
                        this.items = [...response.data];
                    }
                    if (this.acParams && this.linkview) {
                        this.items.push({ isNew: true });
                    }
                    if (callback) {
                        callback(this.items);
                    }
                    this.parseValue();
                }).catch((response: any) => {
                    if (callback) {
                        callback([]);
                    }
                });
            }
        }
    }


    /**
     * 解析回填文本
     */
    public parseValue(){
        let index = this.items.findIndex((item)=>{return Object.is(item[this.deKeyField],this.refvalue)});
        if(index > -1){
            this.curvalue = this.items[index][this.deMajorField];
        }
    }

    /**
     * 选中数据回调
     * @param item 
     */
    public onACSelect(item: any): void {
        this.selectValue = item[this.deMajorField];
        if (this.valueitem) {
            this.$emit('formitemvaluechange', { name: this.valueitem, value: item[this.deKeyField] });
        }
        if (this.name) {
            this.$emit('formitemvaluechange', { name: this.name, value: item[this.deMajorField] });
        }
    }

    /**
     * 下拉选中
     *
     * @param {string} val
     * @memberof AppPicker
     */
    public onSelect($event: Event, val: string, open: boolean) {
        this.open = open;
        let index = this.items.findIndex((item) => Object.is(item[this.deKeyField], val));
        if (index >= 0) {
            this.onACSelect(this.items[index]);
        }
    }

    /**
     * 失去焦点事件
     * @param e 
     */
    public onBlur($event: Event): void {
        const target: any = $event.target;
        const value: any = target.value;
        setTimeout(() => {
            if (this.open) {
                this.open = false;
                if (this.name) {
                    this.$emit('formitemvaluechange', { name: this.name, value: value ? value : null });
                }
            }
        }, 300);
    }

    /**
     * 清除
     */
    public onClear($event: any): void {
        if (this.valueitem) {
            this.curvalue="";
            this.$emit('formitemvaluechange', { name: this.valueitem, value: '' });
        }
        if (this.name) {
            this.curvalue="";
            this.$emit('formitemvaluechange', { name: this.name, value: '' });
        }
        // this.$forceUpdate();
    }

    /**
     * 打开视图
     */
    public openView($event: any): void {
        if (this.disabled) {
            return;
        }
        // 公共参数处理
        let data: any = {};
        const bcancel: boolean = this.handlePublicParams(data);
        if (!bcancel) {
            return;
        }
        // 参数处理
        const view = { ...this.pickupView };
        let _context = data.context;
        if(view.viewModelData){
            Object.assign(_context,{viewModelData:view.viewModelData});
        }
        let _param = data.param;
        // 判断打开方式
        if (view.placement && !Object.is(view.placement, '')) {
            this.openDrawer(view, _context, data);
        } else {
            this.openPopupModal(view, _context, _param);
        }

    }

    /**
     * 路由模式打开视图
     *
     * @private
     * @param {string} viewpath
     * @param {*} data
     * @memberof AppPicker
     */
    private openIndexViewTab(view: any, context: any, param: any): void {
        const routeStr: any = this.openService.formatRouteParam(this.context, view.deResParameters, view.parameters, [context], param);
        this.$router.push(routeStr);
    }

    /**
     * 模态模式打开视图
     *
     * @private
     * @param {*} view
     * @param {*} data
     * @memberof AppPicker
     */
    private async openPopupModal(view: any, context: any, param: any): Promise<any> {
        const result: any = await this.$appmodal.openModal(view, context, param);
        if (result || Object.is(result.ret, 'OK')) {
            this.openViewClose(result);
        }
    }

    /**
     * 抽屉模式打开视图
     *
     * @private
     * @param {*} view
     * @param {*} data
     * @memberof AppPicker
     */
    private async openDrawer(view: any, context: any, param: any) {
        let result: any = await this.$appdrawer.openDrawer(view, context, param);
         if (result || Object.is(result.ret, 'OK')) {
            this.openViewClose(result);
        }
    }

    /**
     * 独立里面弹出
     *
     * @private
     * @param {string} url
     * @memberof AppPicker
     */
    private openPopupApp(url: string): void {
        window.open(url, '_blank');
    }

    /**
     * 打开重定向视图
     *
     * @private
     * @param {*} $event
     * @param {*} view
     * @param {*} data
     * @memberof AppPicker
     */
    private openRedirectView($event: any, view: any, data: any): void {
        this.$http.get(view.url, data).then((response: any) => {
            if (!response || response.status !== 200) {
                this.$Notice.error(`${this.$t("requestException")}`);
            }
            if (response.status === 401) {
                return;
            }
            const { data: result } = response;

            if (result.viewparams && !Object.is(result.viewparams.srfkey, '')) {
                Object.assign(data, { srfkey: result.viewparams.srfkey });
            }

            if (Object.is(result.openmode, 'POPUPAPP') && result.url && !Object.is(result.url, '')) {
                this.openPopupApp(result.url);
            } else if (Object.is(result.openmode, 'INDEXVIEWTAB') || Object.is(result.openmode, '')) {
                // 所有数据保持在同一级
                if (data.srfparentdata) {
                    Object.assign(data, data.srfparentdata);
                    delete data.srfparentdata;
                }
                this.openIndexViewTab(view, null, data);
            } else if (Object.is(result.openmode, 'POPUPMODAL')) {
                const viewname = this.$util.srfFilePath2(result.viewname);
                const view: any = {
                    viewname: viewname,
                    title: result.title,
                    width: result.width,
                    height: result.height,
                }
                this.openPopupModal(view, null, data);
            } else if (result.openmode.startsWith('DRAWER')) {
                const viewname = this.$util.srfFilePath2(result.viewname);
                const view: any = {
                    viewname: viewname,
                    title: result.title,
                    width: result.width,
                    height: result.height,
                    placement: result.openmode,
                }
                this.openDrawer(view, null, data);
            }
        }).catch((response: any) => {
            if (!response || !response.status || !response.data) {
                this.$Notice.error(`${this.$t('systemIsAbnormal')}`);
                return;
            }
            if (response.status === 401) {
                return;
            }
        });
    }

    /**
     * 打开链接视图
     *
     * @memberof AppPicker
     */
    public openLinkView($event: any): void {
        if (this.disabled) {
            return;
        }
        if (!this.data || !this.valueitem || !this.data[this.valueitem]) {
            this.$Notice.error(`${this.$t('valueItemIsAbnormal')}`);
            return;
        }
        // 公共参数处理
        let data: any = {};
        const bcancel: boolean = this.handlePublicParams(data);
        if (!bcancel) {
            return;
        }
        // 参数处理
        let _context = data.context;
        let _param = data.param;
        Object.assign(_context, { [this.deKeyField]: this.data[this.valueitem] });
        const view = JSON.parse(JSON.stringify(this.linkview));
        const viewname2: string = this.$util.srfFilePath2(view.viewname);
        view.viewname = viewname2;

        if (view.isRedirectView) {
            this.openRedirectView($event, view, data);
        } else if (Object.is(view.placement, 'INDEXVIEWTAB') || Object.is(view.placement, '')) {
            this.openIndexViewTab(view, _context, _param);
        }else if (Object.is(view.placement, 'POPUPMODAL')) {
            this.openPopupModal(view, _context, _param);
        } else if (view.placement.startsWith('DRAWER')) {
            this.openDrawer(view, _context, _param);
        }
    }

    /**
     * 打开页面关闭
     *
     * @param {*} result
     * @memberof AppPicker
     */
    public openViewClose(result: any) {
        let item: any = {};
        if (result.datas && Array.isArray(result.datas)) {
            Object.assign(item, result.datas[0]);
        }

        if (this.data) {
            if (this.valueitem) {
                this.$emit('formitemvaluechange', { name: this.valueitem, value: item[this.deKeyField] ? item[this.deKeyField] : item["srfkey"] });
            }
            if (this.name) {
                this.$emit('formitemvaluechange', { name: this.name, value: item[this.deMajorField] ? item[this.deMajorField] : item["srfmajortext"] });
            }
        }
    }

    /**
     * 公共参数处理
     *
     * @param {*} arg
     * @returns
     * @memberof AppPicker
     */
    public handlePublicParams(arg: any): boolean {
        if (!this.data) {
            return false;
        }
        // 导航参数处理
        const {context, param} = this.$viewTool.formatNavigateParam( this.navigateContext, this.navigateParam, this.context, this.viewparams, this.data );
        arg.context =  context;
        arg.param = param;
        return true;
    }

    /**
     * 创建并编辑
     *
     * @param {*} arg
     * @returns
     * @memberof AppPicker
     */
    public newAndEdit($event: any) {
        if (this.disabled) {
            return;
        }
        // 公共参数处理
        let data: any = {};
        const bcancel: boolean = this.handlePublicParams(data);
        if (!bcancel) {
            return;
        }
        // 参数处理
        const view = { ...this.linkview };
        view.viewname = this.$util.srfFilePath2(view.viewname);
        let _context = data.context;
        delete _context[this.deKeyField];
        let _param = data.param;
        // 判断打开方式
        if (view.placement && !Object.is(view.placement, '')) {
            this.openDrawer(view, _context, data);
        } else {
            this.openPopupModal(view, _context, _param);
        }

    }

    /**
     * 输入过程中
     *
     * @memberof AppAutocomplete
     */
    public onInput($event: any) {
        if (Object.is($event, this.value)) {
            this.inputState = true;
        }
        const value = $event;
        this.inputImageStabilization.next(value);
    }

    /**
     * 展开下拉
     *
     * @memberof AppPicker
     */
    public openDropdown() {
        const appPicker: any = this.$refs.appPicker;
        if (appPicker) {
            appPicker.focus();
        }
    }

    /**
     * 收起下拉
     *
     * @memberof AppPicker
     */
    public closeDropdown() {
        const appPicker: any = this.$refs.appPicker;
        if (appPicker) {
            appPicker.blur();
        }
    }
}
</script>
<style lang="less">
@import './app-mob-picker.less';
</style>