<template>
    <div v-if="Object.is(editorType, 'linkonly')" class='app-picker'>
        <a @click="openLinkView">{{curvalue}}</a>
    </div>
    <div v-else-if="!Object.is(editorType, 'pickup-no-ac') && !Object.is(editorType, 'dropdown')" class='app-picker'>
        <div class='app-picker'>
            <el-autocomplete ref="appAc" class='text-value' :value-key="deMajorField" :disabled="disabled || readonly" v-model="curvalue" size='small'
                :trigger-on-focus="true" :fetch-suggestions="(query, callback) => { this.onSearch(query, callback, true) }" @select="onACSelect"
                @input="onInput" @blur="onBlur" @focus="onAcFocus" style='width:100%;'
                :placeholder="placeholder" :popper-class="`app-ac-${name}`">
                <template v-slot:default="{item}">
                    <!-- <template v-if="item.isNew">
                        <div v-if="linkview" @click="newAndEdit">{{$t('components.apppicker.newandedit')}}</div>
                    </template> -->
                    <template v-if="item.tag">
                        <div @click="clickAction(item.tag)">{{item.caption}}</div>
                    </template>
                    <slot v-else name="default" :item="item"></slot>
                </template>
                <template v-slot:suffix>
                    <i v-if="curvalue && !disabled" class='el-icon-circle-close' @click="onClear"></i>
                    <i v-show="open" class='el-icon-arrow-up' @click="closeAppAc"></i> 
                    <i v-show="!open" class='el-icon-arrow-down' @click="openAppAc"></i>
                    <i v-if="!Object.is(editorType, 'ac') && showButton" class='el-icon-search' @click="openView"></i>
                    <icon v-if="linkview" type="ios-open-outline" @click="openLinkView"/>
                </template>
            </el-autocomplete>
        </div>
    </div>
    <div v-else-if="Object.is(editorType, 'pickup-no-ac')" class='app-picker'>
        <div class='app-picker'>
            <el-input class='text-value' :placeholder="placeholder" :value="curvalue" readonly size='small' :disabled="disabled">
                <template slot='suffix'>
                    <i v-if="curvalue && !disabled" class='el-icon-circle-close' @click="onClear"></i>
                    <i class='el-icon-search' @click="openView"></i>
                    <icon v-if="linkview" type="ios-open-outline" @click="openLinkView"/>
                </template>
            </el-input>
        </div>
    </div>
    <div v-else-if="Object.is(editorType, 'dropdown')" class='app-picker'>
        <el-select ref="appPicker" remote :remote-method="(query) => this.onSearch(query, null, true)" :value="refvalue" size='small' filterable
            @change="onSelect" :disabled="disabled || readonly" style='width:100%;' clearable popper-class="app-picker-dropdown"
            @clear="onClear" @visible-change="onSelectOpen" :loading="loading" 
            :placeholder="placeholder">
            <template v-if="loading" slot="empty">
                <li class="loading">
                    <i class="el-icon-loading"></i>
                </li>
            </template>
            <template v-if="items">
                <template v-for="(_item,index) in items">
                    <el-option  v-if="!_item.tag" :key="`${_item[deKeyField]}${index}${parentCodeName}`" :value="_item[deKeyField]" :label="_item[deMajorField]" :disabled="_item.disabled"></el-option>
                    <el-option  v-else :key="`${_item[deKeyField]}${index}${parentCodeName}`" value="action"><span  @click="clickAction(_item.tag)" style="float: left; width: 100%;">{{ _item.caption }}</span></el-option>
                </template>
            </template>
        </el-select>
        <span style='position: absolute;right: 5px;color: #c0c4cc;top:0;font-size: 13px;'>
            <i v-show="open" class='el-icon-arrow-up' @click="closeDropdown"></i> 
            <i v-show="!open" class='el-icon-arrow-down' @click="openDropdown"></i>
        </span>
    </div>
</template>

<script lang = 'ts'>
import { Component, Vue, Prop, Model, Watch } from 'vue-property-decorator';
import { Subject } from 'rxjs';
import { Util, LogUtil, ModelTool, ViewTool  } from 'ibiz-core';
import { IPSAppDataEntity, IPSAppDERedirectView, IPSAppDEView, IPSAppViewRef, IPSNavigateContext, IPSAppView } from '@ibiz/dynamic-model-api';
import { UIServiceRegister } from 'ibiz-service';

@Component({
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
    @Prop({default: () => {}}) public acParams?: any;

    /**
     * 外键值附加数据
     *
     * @type {*}
     * @memberof AppPicker
     */
    @Prop() public pickUpData?: string;

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
    @Prop({default: 'srfmajortext'}) public deMajorField!: string;

    /**
     * 应用实体主键属性名称
     *
     * @type {string}
     * @memberof AppPicker
     */
    @Prop({default: 'srfkey'}) public deKeyField!: string;

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
	 * 只读模式
	 * 
	 * @type {boolean}
	 */
	@Prop({default: false}) public readonly?: boolean;

    /**
     * 是否显示按钮
     *
     * @type {boolean}
     * @memberof AppPicker
     */
    @Prop({default:true}) public showButton?: boolean;

    /**
     * 类型
     *
     * @type {string}
     * @memberof AppPicker
     */
    @Prop() public editorType?: string;

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
     * 局部上下文导航参数
     * 
     * @type {any}
     * @memberof AppPicker
     */
    @Prop() public localContext!:any;

    /**
     * 局部导航参数
     * 
     * @type {any}
     * @memberof AppPicker
     */
    @Prop() public localParam!:any;

    /**
     * 值项名称
     *
     * @type {string}
     * @memberof AppPicker
     */
    @Prop() public valueitem!: string;

    /**
     * 排序
     *
     * @type {string}
     * @memberof AppPicker
     */
    @Prop() public sort?: string;

    /**
     * 行为组
     *
     * @type {Array<any>}
     * @memberof AppPicker
     */
    @Prop() public actionDetails?:Array<any>;

    /**
     * 值
     *
     * @type {*}
     * @memberof AppPicker
     */
    @Model('change') public value?: any;

    /**
     * 占位提示
     * 
     * @type {*}
     * @memberof AppPicker
     */
    @Prop() public placeholder?: string;

    /**
     * 父容器标识
     * 
     * @type {*}
     * @memberof AppPicker
     */
    @Prop() public parentCodeName?: string;

    /**
     * 值格式化
     * 
     * @type {*}
     * @memberof AppPicker
     */
    @Prop() public valFormat?: any;

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
    public selectValue = null;

    /**
     * 下拉列表节点元素
     *
     * @type {*}
     * @memberof AppPicker
     */
    public dropdownDom:any = {};

    /**
     * 下拉远程加载状态
     * 
     * @type {boolean}
     * @memberof AppPicker
     */
    public loading: boolean = false;

    /**
     * 获取关联数据项值
     *
     * @readonly
     * @memberof AppPicker
     */
    get refvalue() {
        if (this.valueitem && this.data) {
            const key = this.data[this.valueitem];
            if (this.valFormat) {
                const format =  eval("("+ this.valFormat +")");
                if (format.hasOwnProperty(key)) {
                    if (!Util.isEmpty(this.curvalue)) {
                        return format[key];
                    } else {
                        return '';
                    }
                }
            }
            if (!Util.isEmpty(this.curvalue)) {
                return key;
            } else {
                return '';
            }
        }
        return this.curvalue;
    }

    /**
     * 值变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppPicker
     */
    @Watch('value',{immediate:true})
    public onValueChange(newVal: any, oldVal: any) {
        this.curvalue = newVal;
		this.selectValue = newVal;
        if (Object.is(this.editorType, 'dropdown') && this.valueitem) {
            const value = this.data[this.valueitem];
            const index = this.items.findIndex((item: any) => Object.is(item.value, value));
            if (index !== -1) {
                return;
            }
            // items里匹配不到当前值项值时，生成自身的选项
            this.items = [];
            if (!Util.isEmpty(newVal) && !Util.isEmpty(value)) {
                this.items.push({[this.deMajorField]: newVal, [this.deKeyField]: value});
            }
        }
    }

    /**
     * vue 生命周期
     *
     * @memberof AppPicker
     */
    public created() {
        if(!Object.is(this.editorType, 'pickup-no-ac') && !Object.is(this.editorType, 'dropdown')){
            this.curvalue = this.value;
        }
    }

    /**
     * vue 生命周期
     *
     * @memberof AppPicker
     */
    public mounted() {
        const dropdownDom:any = this.$el.getElementsByClassName('app-picker-dropdown')[0];
        this.dropdownDom = dropdownDom;
    }

    /**
     * 组件销毁
     *
     * @memberof AppPicker
     */
    public destroyed(): void {
    }

    /**
     * 下拉切换回调
     * @param flag 
     */
    public onSelectOpen(flag: boolean): void {
        this.open = flag;
        if (this.open) {
            this.items = [];
            //设置下拉列表的最大宽度
            this.setDropdownWidth();
            this.onSearch(this.curvalue, null, true);
        }
    }

    /**
     * 设置下拉列表最大宽度使下拉列表宽度和编辑器宽度一致
     *
     * @memberof AppPicker
     */
    public setDropdownWidth(){
        const elInput:any = this.$el.getElementsByClassName('el-input__inner')[0];
        this.dropdownDom.style.maxWidth = elInput.offsetWidth+'px';
    }

    /**
     * 执行搜索数据
     * @param query 
     * @param callback 
     */
    public onSearch(query: any, callback: any, other: boolean): void {
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
        if(this.sort && !Object.is(this.sort, "")) {
            Object.assign(_param, { sort: this.sort });
        }
        Object.assign(_param, { query: query, size: 1000 });
        // 错误信息国际化
        // let error: string = '错误';
        // let miss: string = (this.$t('components.apppicker.miss') as any);
        // let requestException: string = (this.$t('components.apppicker.requestexception') as any);
        if(!this.service){
            // this.$throw(miss+'service','onSearch');
        } else if(!this.acParams.serviceName) {
            // this.$throw(miss+'serviceName','onSearch');
        } else if(!this.acParams.interfaceName) {
            // this.$throw(miss+'interfaceName','onSearch');
        } else {
          this.loading = true;
          this.service.getItems(this.acParams.serviceName,this.acParams.interfaceName, _context, _param).then((response: any) => {
              this.loading = false;
              if (!response) {
                  // this.$throw(requestException,'onSearch');
              } else {
                  this.items = [...response];
              }
            //   if(this.acParams && this.linkview){
            //       this.items.push({ isNew :true });
            //   }
             if(this.acParams && this.actionDetails && this.actionDetails.length >0){
                  this.items = [...this.items,...this.actionDetails];
              }
              if (callback) {
                  callback(this.items);
              }
          }).catch((error: any) => {
              this.loading = false;
              if (callback) {
                  callback([]);
              }
          });
        } 
    }

    /**
     * 选中数据回调
     * @param item 
     */
    public onACSelect(item: any): void {
        if (this.valueitem) {
            this.$emit('formitemvaluechange', { name: this.valueitem, value: item[this.deKeyField] });
        }
        if (this.name) {
            this.$emit('formitemvaluechange', { name: this.name, value: item[this.deMajorField] });
        }
        this.fillPickUpData(item);
    }

    /**
     * 下拉选中
     *
     * @param {string} val
     * @memberof AppPicker
     */
    public onSelect(val: string) {
        let index = this.items.findIndex((item) => Object.is(item[this.deKeyField], val));
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
        window.setTimeout(() => {
            this.closeAppAc();
        }, 200);
    }

    /**
     * ac获得焦点事件
     * @param e 
     */
    public onAcFocus(e: any) {
        this.openAppAc();
    }

    /**
     * 清除
     */
    public onClear($event: any): void {
        if (this.valueitem) {
            this.$emit('formitemvaluechange', { name: this.valueitem, value: null });
        }
        if (this.name) {
            this.$emit('formitemvaluechange', { name: this.name, value: null });
        }
        this.fillPickUpData();
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
        if(this.valueitem && this.data && this.data[this.valueitem] && this.curvalue) {
            _param.selectedData = [{srfkey: this.data[this.valueitem], srfmajortext: this.curvalue }];
        }
        // 判断打开方式
        view.placement = view.placement || '';
        if (view.placement === 'POPOVER') {
            this.openPopOver($event, view, _context, _param);
        } else if (view.placement.indexOf('DRAWER') !== -1) {
            this.openDrawer(view, _context, _param);
        } else {
            this.openPopupModal(view, _context, _param);
        }
    }

    /**
     * 路由模式打开视图
     *
     * @private
     * @param {*} view
     * @param {*} context
     * @param {*} param
     * @memberof AppPicker
     */
    private openIndexViewTab(view: any, context: any, param: any): void {
        if (context.srfdynainstid) {
            Object.assign(param, { srfdynainstid: context.srfdynainstid });
        }
        const routePath = this.$viewTool.buildUpRoutePath(this.$route, context, view.deResParameters, view.parameters, [this.data] , param);
        this.$router.push(routePath);
    }

    /**
     * 模态模式打开视图
     *
     * @private
     * @param {*} view
     * @param {*} data
     * @memberof AppPicker
     */
    private openPopupModal(view: any, context: any, param: any): void {
        if(view.viewpath){
            Object.assign(context,{viewpath:view.viewpath});
        }
        let container: Subject<any> = this.$appmodal.openModal(view, context, param);
        container.subscribe((result: any) => {
            if (!result || !Object.is(result.ret, 'OK')) {
                return;
            }
            this.openViewClose(result);
        });
    }

    /**
     * 抽屉模式打开视图
     *
     * @private
     * @param {*} view
     * @param {*} data
     * @memberof AppPicker
     */
    private openDrawer(view: any, context: any, param: any): void {
        if(view.viewpath){
            Object.assign(context,{viewpath:view.viewpath});
        }
        let container: Subject<any> = this.$appdrawer.openDrawer(view, Util.getViewProps(context, param));
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
     * @memberof AppPicker
     */
    private openPopOver($event: any, view: any, context: any, param: any): void {
        if(view.viewpath){
            Object.assign(context,{viewpath:view.viewpath});
        }
        let container: Subject<any> = this.$apppopover.openPop($event, view, context, param);
        container.subscribe((result: any) => {
            if (!result || !Object.is(result.ret, 'OK')) {
                return;
            }
            this.openViewClose(result);
        });
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
    private async openRedirectView($event: any,context:any, params: any) {
        let targetRedirectView: IPSAppDERedirectView = this.linkview.viewModel;
        await targetRedirectView.fill(true);
        if (
            targetRedirectView.getRedirectPSAppViewRefs() &&
            targetRedirectView.getRedirectPSAppViewRefs()?.length === 0
        ) {
            return;
        }
        const redirectUIService: any = await UIServiceRegister.getInstance().getService(
            context,
            (ModelTool.getViewAppEntityCodeName(targetRedirectView) as string)?.toLowerCase(),
        );
        await redirectUIService.loaded();
        const redirectAppEntity: IPSAppDataEntity | null = targetRedirectView.getPSAppDataEntity();
        await ViewTool.calcRedirectContext(context, this.data, redirectAppEntity);
        let result = await redirectUIService.getRDAppView(context, this.data['srfkey'], params);
        if (!result) {
            return;
        }
        if(result.isError){
            const data = result?.response?.data;
            this.$throw(data?.messages?data.messages:this.$t('components.500.errortext1'));
            return;
        }
        let targetOpenViewRef: IPSAppViewRef | undefined = targetRedirectView
            .getRedirectPSAppViewRefs()
            ?.find((item: IPSAppViewRef) => {
                return item.name === result.param.split(':')[0];
            });
        if (!targetOpenViewRef) {
            return;
        }
        if (
            targetOpenViewRef.getPSNavigateContexts() &&
            (targetOpenViewRef.getPSNavigateContexts() as IPSNavigateContext[]).length > 0
        ) {
            let localContextRef: any = Util.formatNavParam(targetOpenViewRef.getPSNavigateContexts(), true);
            let _context: any = Util.computedNavData(this.data, context, params, localContextRef);
            Object.assign(context, _context);
        }
        if (result && result.hasOwnProperty('srfsandboxtag')) {
            Object.assign(context, { srfsandboxtag: result['srfsandboxtag'] });
            Object.assign(params, { srfsandboxtag: result['srfsandboxtag'] });
        }
        let targetOpenView: IPSAppView | null = targetOpenViewRef.getRefPSAppView();
        if (!targetOpenView) {
            return;
        }
        await targetOpenView.fill(true);
        const view: any = {
            viewname: 'app-view-shell',
            height: targetOpenView.height,
            width: targetOpenView.width,
            title: this.$tl(targetOpenView.getCapPSLanguageRes()?.lanResTag, targetOpenView.title),
            placement: targetOpenView.openMode ? targetOpenView.openMode : '',
            viewpath: targetOpenView.modelFilePath,
        };
        if (!targetOpenView.openMode || targetOpenView.openMode == 'INDEXVIEWTAB') {
            if (targetOpenView.getPSAppDataEntity()) {
                view.deResParameters = Util.formatAppDERSPath(
                    context,
                    (targetOpenView as IPSAppDEView).getPSAppDERSPaths(),
                );
                view.parameters = [
                    {
                        pathName: Util.srfpluralize(
                            (targetOpenView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName,
                        ).toLowerCase(),
                        parameterName: (
                            targetOpenView.getPSAppDataEntity() as IPSAppDataEntity
                        )?.codeName.toLowerCase(),
                    },
                    {
                        pathName: 'views',
                        parameterName: ((targetOpenView as IPSAppDEView).getPSDEViewCodeName() as string).toLowerCase(),
                    },
                ];
            } else {
                view.parameters = [
                    {
                        pathName: targetOpenView.codeName.toLowerCase(),
                        parameterName: targetOpenView.codeName.toLowerCase(),
                    },
                ];
            }
        } else {
            if (targetOpenView.getPSAppDataEntity()) {
                view.parameters = [
                    {
                        pathName: Util.srfpluralize(
                            (targetOpenView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName,
                        ).toLowerCase(),
                        parameterName: (
                            targetOpenView.getPSAppDataEntity() as IPSAppDataEntity
                        )?.codeName.toLowerCase(),
                    },
                ];
            }
            if (targetOpenView && targetOpenView.modelPath) {
                Object.assign(context, { viewpath: targetOpenView.modelPath });
            }
        }
        if (Object.is(view.placement, 'INDEXVIEWTAB') || Util.isEmpty(view.placement)) {
            this.openIndexViewTab(view, context, params);
        } else if (Object.is(view.placement, 'POPOVER')) {
            this.openPopOver($event, view, context, params);
        } else if (Object.is(view.placement, 'POPUPMODAL')) {
            this.openPopupModal(view, context, params);
        } else if (view.placement.startsWith('DRAWER')) {
            this.openDrawer(view, context, params);
        }
    }

    /**
     * 打开链接视图
     *
     * @memberof AppPicker
     */
    public openLinkView($event: any): void {
        if (!this.data || !this.valueitem || !this.data[this.valueitem]) {
            this.$throw((this.$t('components.apppicker.valueitemexception') as any),'openLinkView');
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
        const view = { ...this.linkview };
        const viewname2: string = view.viewname;
        view.viewname = viewname2;
        if (view.isRedirectView) {
            this.openRedirectView($event, _context,_param);
        } else if (Object.is(view.placement, 'INDEXVIEWTAB') || Util.isEmpty(view.placement)) {
            this.openIndexViewTab(view, _context, _param);
        } else if (Object.is(view.placement, 'POPOVER')) {
            this.openPopOver($event, view, _context, _param);
        } else if (view.placement.startsWith('DRAWER')) {
            this.openDrawer(view, _context, _param);
        } else if (Object.is(view.placement, 'POPUPMODAL')) {
            this.openPopupModal(view, _context, _param);
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
                this.$emit('formitemvaluechange', { name: this.valueitem, value: item[this.deKeyField]?item[this.deKeyField]:item["srfkey"] });
            }
            if (this.name) {
                this.$emit('formitemvaluechange', { name: this.name, value: item[this.deMajorField]?item[this.deMajorField]:item["srfmajortext"] });
            }
            this.fillPickUpData(item);
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
            this.$throw((this.$t('components.apppicker.formdataexception') as any),'handlePublicParams');
            return false;
        }
        // 合并表单参数
        arg.param = this.viewparams ? JSON.parse(JSON.stringify(this.viewparams)) : {};
        arg.context = this.context ? JSON.parse(JSON.stringify(this.context)) : {};
        // 附加参数处理
        if (this.localContext && Object.keys(this.localContext).length >0) {
            let _context = this.$util.computedNavData(this.data,arg.context,arg.param,this.localContext);
            Object.assign(arg.context,_context);
        }
        if (this.localParam && Object.keys(this.localParam).length >0) {
            let _param = this.$util.computedNavData(this.data,arg.context,arg.param,this.localParam);
            Object.assign(arg.param,_param);
        }
        return true;
    }

    /**
     * 触发界面行为
     *
     * @param {*} arg
     * @returns
     * @memberof AppPicker
     */
    public clickAction(arg:any){
        this.$emit('editoractionclick',arg);
    }

    /**
     * 创建并编辑
     *
     * @param {*} arg
     * @returns
     * @memberof AppPicker
     */
    public newAndEdit($event:any){
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
        if (view.placement && !Util.isEmpty(view.placement)) {
            if (Object.is(view.placement, 'POPOVER')) {
                this.openPopOver($event, view, _context, _param);
            } else {
                this.openDrawer(view, _context, _param);
            }
        } else {
            this.openPopupModal(view, _context, _param);
        }
        
    }

    /**
     * 输入过程中
     *
     * @memberof AppPicker
     */
    public onInput($event: any) {
        if (Object.is($event, this.value)) {
            this.inputState = true;
        }
    }

    /**
     * 展开下拉
     *
     * @memberof AppPicker
     */
    public openDropdown() {
        const appPicker: any = this.$refs.appPicker;
        if(appPicker) {
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
        if(appPicker) {
            appPicker.blur();
        }
    }
    
    /**
     * 打开ac
     *
     * @memberof AppPicker
     */
    public openAppAc() {
        const acPopover: any = document.querySelector(`.app-ac-${this.name}`);
        const appAc: any = this.$refs.appAc;
        if (acPopover && appAc) {
            this.open = true;
            if (acPopover.getAttribute('is-close')) {
                acPopover.style.display = 'block';
            }
            appAc.focus();
        }
    }

    /**
     * 关闭ac
     *
     * @memberof AppPicker
     */
    public closeAppAc() {
        const acPopover: any = document.querySelector(`.app-ac-${this.name}`);
        if (acPopover) {
            this.open = false;
            acPopover.style.display = 'none';
            acPopover.setAttribute('is-close', true);
        }
    }

  /**
   * 填充外键值附加数据
   *
   * @param {item} 数据集
   * @memberof AppPicker
   */
  public fillPickUpData(item?:any){
    if(this.pickUpData){
        let pickUpDataArray:Array<any> = this.pickUpData.split(";")
        if(pickUpDataArray && pickUpDataArray.length >0){
            for(let i=0;i<pickUpDataArray.length;i++){
               if(item){
                    this.$emit("formitemvaluechange", {
                        name: pickUpDataArray[i],
                        value: item[pickUpDataArray[i]],
                    });
               }else{
                    this.$emit("formitemvaluechange", {
                        name: pickUpDataArray[i],
                        value: "",
                    });
               }
            }
        }
    }
  } 

}
</script>
<style lang="less">
@import './app-picker.less';
</style>