<template>
     <div class="app-mobile-select-drop-down">
        <div class="cancel-icon" v-if="curvalue"><app-mob-icon name="close-circle-outline" @onClick="onClear"></app-mob-icon></div>
        <div v-if="curvalue== null || curvalue==''" class="ion-select-icon"></div>
        <div class="select_text" @click="openSelect" >{{curvalue}}</div>
        <ion-select :selected-text="selectValue" :ref="name+'select'" v-show="false" :disabled="disabled " @ionChange="change" interface="action-sheet" :cancel-text="$t('app.button.cancel')" @ionCancel="cancel">
            <ion-select-option  v-for="option of items" :key="option.value" :value="option.value">{{option.text}}</ion-select-option>
        </ion-select>
    </div>   
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch, Model } from "vue-property-decorator";
import { Subject } from 'rxjs';
import { ViewOpenService } from 'ibiz-vue';
import { GlobalService } from 'ibiz-service';
@Component({
    components: {},
    i18n: {
        messages: {
            'ZH-CN': {
                associated_entity: '请在对应实体属性中配置关联实体与数据集!',
                error_request: '错误，请求异常!',
                error_system: '错误，系统异常!',
                error_value: '错误,值项异常!',
            },
            'EN-US': {
                associated_entity: 'Please configure the associated entity and data set in the corresponding entity properties!',
                error_request: 'Error, request exception!',
                error_system: 'Error, the system is abnormal!',
                error_value: 'Error, abnormal value item!',
            }
        }
    }
})
export default class AppSelectDropDown extends Vue {

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof AppSelectDropDown
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppSelectDropDown
     */
    @Prop() public viewparams!: any;

    /**
     * AC参数
     *
     * @type {*}
     * @memberof AppSelectDropDown
     */
    @Prop({ default: () => { } }) public acParams?: any;

    /**
     * 表单服务
     *
     * @type {*}
     * @memberof AppSelectDropDown
     */
    @Prop() public service?: any;

    /**
     * 应用实体主信息属性名称
     *
     * @type {string}
     * @memberof AppSelectDropDown
     */
    @Prop({ default: 'srfmajortext' }) public deMajorField!: string;

    /**
     * 应用实体主键属性名称
     *
     * @type {string}
     * @memberof AppSelectDropDown
     */
    @Prop({ default: 'srfkey' }) public deKeyField!: string;

    /**
     * 表单数据
     *
     * @type {*}
     * @memberof AppSelectDropDown
     */
    @Prop() public data!: any;

    /**
     * 属性项名称
     *
     * @type {string}
     * @memberof AppSelectDropDown
     */
    @Prop() public name!: string;

    /**
     * 是否启用
     *
     * @type {boolean}
     * @memberof AppSelectDropDown
     */
    @Prop() public disabled?: boolean;

    /**
     * 类型
     *
     * @type {string}
     * @memberof AppSelectDropDown
     */
    @Prop() public editortype?: string;


    /**
     * 视图参数（如：视图name，title，width，height）
     *
     * @type {*}
     * @memberof AppSelectDropDown
     */
    @Prop() public pickupView?: any;

    /**
     * 数据链接参数
     *
     * @type {*}
     * @memberof AppSelectDropDown
     */
    @Prop() public linkview?: any;

    /**
     * 表单项参数
     * 
     * @type {any}
     * @memberof AppSelectDropDown
     */
    @Prop() public itemParam: any;

    /**
     * 值项名称
     *
     * @type {string}
     * @memberof AppSelectDropDown
     */
    @Prop() public valueitem?: string;

    /**
     * 值
     *
     * @type {*}
     * @memberof AppSelectDropDown
     */
    @Model('change') public value?: any;

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
     * 下拉数组
     * @type {any[]}
     * @memberof AppSelectDropDown
     */
    public items: any[] = [];

    /** 
     * 下拉图标指向状态管理
     * @type {boolean}
     * @memberof AppSelectDropDown 
     */
    public open: boolean = false;

    /**
     * 输入状态
     *
     * @type {boolean}
     * @memberof AppSelectDropDown
     */
    public inputState: boolean = false;

    /**
      * 当前选择的值
      *
      * @type {string}
      * @memberof AppSelectDropDown
      */
    public selectValue: any = "";


    /**
     * 视图打开服务
     *
     * @type {ViewOpenService}
     * @memberof AppSelectDropDown
     */
    public openService: ViewOpenService = ViewOpenService.getInstance();


    /**
     * 获取关联数据项值
     *
     * @readonly
     * @memberof AppSelectDropDown
     */
    get curvalue() {
        if (this.value && this.items.length > 0) { // 判断是否拿到表单传来的值、列表项是否加载完成
            if (this.valueitem && this.items.every((item: any) => { return item[this.deKeyField] != this.value })) {
                return this.value;
            }
        }
        return this.value;
    }

    /**
      * 关联数据项值
      *
      * @readonly
      * @memberof AppSelectDropDown
      */
    set curvalue(item: any) {
        this.onSelect(item);
    }

    /**
     * 值变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppSelectDropDown
     */
    @Watch('value')
    public onValueChange(newVal: any, oldVal: any) {
        if (oldVal && !newVal) {
            this.$nextTick(() => {
                let select: any = this.$refs[this.name + 'select'];
                if (select) {
                    select.value = null;
                }
            })
        }
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
            this.onSearch(newVal, false);
            this.$store.commit('setSelectStatus', true);
        }
    }

    /**
     * vue 生命周期
     *
     * @memberof AppSelectDropDown
     */
    public mounted() {
        this.onSearch(null, true);
        this.$store.commit('setSelectStatus', true);
    }

    /**
     * 下拉切换回调
     * @param flag 
     */
    public onSelectOpen(flag: boolean): void {
        this.open = flag;
        if (this.open) {
            this.onSearch(this.curvalue, true);
            this.$store.commit('setSelectStatus', true);
        }
    }
    /**
     * 执行搜索数据
     * @param query 
     * @param callback 
     */
    public async onSearch(query: any = {}, other: boolean = false): Promise<any> {
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
        if (this.acParams && (!this.acParams.serviceName || !this.acParams.interfaceName) ) {
            // this.$Notice.error(`${this.$t('associated_entity')}`);
            return;
        }
        if (this.acParams) {
          const entityService: any = await new GlobalService().getService(this.acParams.serviceName);
          if (entityService && entityService[this.acParams.interfaceName] && entityService[this.acParams.interfaceName] instanceof Function) {
              const response = await entityService[this.acParams.interfaceName](_context, _param);
              if (response && response.status === 200) {
                  this.items = response.data;
                  this.result(this.items);
              } else {
                  this.$Notice.error(`${this.$t('error_request')}`);
              }
          }
        }
    }


    /**
      * 处理返回数据格式
      */
    public result(items: any) {
        let _data = [];
        items.forEach((item: any, index: number) => {
            if (item[this.deMajorField]) {
                items[index].text = item[this.deMajorField];

            }
            if (item[this.deKeyField]) {
                items[index].value = item[this.deKeyField];

            }
        });
    }

    /**
     * 选中数据回调
     * @param item 
     */
    public onACSelect(item: any): void {
        if (this.valueitem) {
            let tempvalue = item[this.deKeyField] ? item[this.deKeyField] : item.srfkey;
            this.$emit('formitemvaluechange', { name: this.valueitem, value: tempvalue });

        }
        if (this.name) {
            let temptext = item[this.deMajorField] ? item[this.deMajorField] : item.srfmajortext;
            this.$emit('formitemvaluechange', { name: this.name, value: temptext });
        }
    }

    /**
     * 下拉选中
     *
     * @param {string} val
     * @memberof AppSelectDropDown
     */
    public onSelect(val: string) {
        let index = this.items.findIndex((item) => { return item[this.deKeyField] == val });
        if (index >= 0) {
            this.onACSelect(this.items[index]);
        }
    }

    /**
     * 失去焦点事件
     * @param e 
     */
    public onBlur(e: any): void {
        this.curvalue = this.value;
    }

    /**
     * 清除
     */
    public onClear($event: any): void {
        if (this.valueitem) {
            this.$emit('formitemvaluechange', { name: this.valueitem, value: '' });
        }
        if (this.name) {
            this.$emit('formitemvaluechange', { name: this.name, value: '' });
        }
        this.selectValue = null;
        let select: any = this.$refs[this.name + 'select'];
        select.value = null;
        this.$forceUpdate();
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
        let _param = data.param;
        // 判断打开方式
        if (view.placement && !Object.is(view.placement, '')) {
            if (Object.is(view.placement, 'POPOVER')) {
                this.openPopOver($event, view, _context, data);
            } else {
                this.openDrawer(view, _context, data);
            }
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
     * @memberof AppSelectDropDown
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
     * @memberof AppSelectDropDown
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
     * @memberof AppSelectDropDown
     */
    private async openDrawer(view: any, context: any, param: any): Promise<any> {
        let container: Subject<any> = await this.$appdrawer.openDrawer(view, context, param);
        container.subscribe((result: any) => {
            if (!result || !Object.is(result.ret, 'OK')) {
                return;
            }
            this.openViewClose(result);
        });
    }

    /**
     * 气泡卡片模式打开
     *
     * @private
     * @param {*} $event
     * @param {*} view
     * @param {*} data
     * @memberof AppSelectDropDown
     */
    private openPopOver($event: any, view: any, context: any, param: any): void {
        // let container: Subject<any> = this.$apppopover.openPop($event, view, context, param);
        // container.subscribe((result: any) => {
        //     if (!result || !Object.is(result.ret, 'OK')) {
        //         return;
        //     }
        //     this.openViewClose(result);
        // });
    }

    /**
     * 独立里面弹出
     *
     * @private
     * @param {string} url
     * @memberof AppSelectDropDown
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
     * @memberof AppSelectDropDown
     */
    private openRedirectView($event: any, view: any, data: any): void {
        this.$http.get(view.url, data).then((response: any) => {
            if (!response || response.status !== 200) {
                this.$Notice.error(`${this.$t('error_request')}`);
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
            } else if (Object.is(result.openmode, 'POPOVER')) {
                const viewname = this.$util.srfFilePath2(result.viewname);
                const view: any = {
                    viewname: viewname,
                    title: result.title,
                    width: result.width,
                    height: result.height,
                    placement: result.openmode,
                }
                this.openPopOver($event, view, null, data);
            }
        }).catch((response: any) => {
            if (!response || !response.status || !response.data) {
                this.$Notice.error(`${this.$t('error_system')}`);
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
     * @memberof AppSelectDropDown
     */
    public openLinkView($event: any): void {
        if (this.disabled) {
            return;
        }
        if (!this.data || !this.valueitem || !this.data[this.valueitem]) {
            this.$Notice.error(`${this.$t('error_value')}`);
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
        } else if (Object.is(view.placement, 'POPOVER')) {
            this.openPopOver($event, view, _context, _param);
        } else if (Object.is(view.placement, 'POPUPMODAL')) {
            this.openPopupModal(view, _context, _param);
        } else if (view.placement.startsWith('DRAWER')) {
            this.openDrawer(view, _context, _param);
        }
    }

    /**
     * 打开页面关闭
     *
     * @param {*} result
     * @memberof AppSelectDropDown
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
     * @memberof AppSelectDropDown
     */
    public handlePublicParams(arg: any): boolean {
        if (!this.data) {
            return false;
        }
        // 导航参数处理
        const { context, param } = this.$viewTool.formatNavigateParam(this.navigateContext, this.navigateParam, this.context, this.viewparams, this.data);
        arg.context = context;
        arg.param = param;
        return true;
    }

    /**
     * 创建并编辑
     *
     * @param {*} arg
     * @returns
     * @memberof AppSelectDropDown
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
            if (Object.is(view.placement, 'POPOVER')) {
                this.openPopOver($event, view, _context, data);
            } else {
                this.openDrawer(view, _context, data);
            }
        } else {
            this.openPopupModal(view, _context, _param);
        }

    }

    /**
     * 输入过程中
     *
     * @memberof AppSelectDropDown
     */
    public onInput($event: any) {
        if (Object.is($event, this.value)) {
            this.inputState = true;
        }
    }

    /**
     * 展开下拉
     *
     * @memberof AppSelectDropDown
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
     * @memberof AppSelectDropDown
     */
    public closeDropdown() {
        const appPicker: any = this.$refs.appPicker;
        if (appPicker) {
            appPicker.blur();
        }
    }

    /**
     * 附加参数格式化
     *
     * @static
     * @param {any} arg 表单数据
     * @param {any} params 附加参数
     * @returns {any}
     * @memberof AppSelectDropDown
     */
    public formatData(arg: any, params: any): any {
        let _data: any = {};
        Object.keys(params).forEach((name: string) => {
            if (!name) {
                return;
            }
            let value: string | null = params[name];
            if (value && value.startsWith('%') && value.endsWith('%')) {
                const key = value.substring(1, value.length - 1);
                if (arg && arg.hasOwnProperty(key)) {
                    value = (arg[key] !== null && arg[key] !== undefined) ? arg[key] : null;
                } else {
                    value = null;
                }
            }
            Object.assign(_data, { [name]: value });
        });
        return _data;
    }

    /**
     * change事件
     *
     * @memberof AppSelect
     */
    public change(value: any) {
        this.$store.commit('setSelectStatus', true);
        this.curvalue = value.detail.value;
    }

    /**
     * 取消选择
     *
     * @type {*}
     * @memberof AppSelect
     */
    public cancel() {
        this.$store.commit('setSelectStatus', true);
    }

    /**
     * 清空值
     * @memberof AppSelect
     */
    public clear() {
        this.curvalue = "";
        this.$emit('change', '')
    }

    /**
     * open选择器
     *
     * @type {*}
     * @memberof AppSelectDropDown
     */
    public openSelect() {
        this.onSearch(null);
        this.$store.commit('setSelectStatus', true);
        let select: any = this.$refs[this.name + 'select'];
        if (select) {
            setTimeout(() => {
                select.open();
                this.$store.commit('setSelectStatus', false);
            }, 1);
        }
    }
}
</script>
<style lang="less">
@import './app-mob-select-drop-down.less';
</style>