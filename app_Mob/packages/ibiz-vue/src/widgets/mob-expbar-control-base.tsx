import { CodeListServiceBase, ModelTool, Util, ViewTool } from 'ibiz-core';
import { AppViewLogicService } from '../app-service';
import { MainControlBase } from './main-control-base';
import { IPSAppCodeList, IPSAppCounterRef, IPSAppDataEntity, IPSAppDEField, IPSAppDEMultiDataView, IPSCodeItem, IPSControlNavigatable, IPSDECalendar, IPSDETBUIActionItem, IPSDEToolbar, IPSDEToolbarItem, IPSDETree, IPSDEUIAction, IPSExpBar, IPSSysImage } from '@ibiz/dynamic-model-api';
/**
 * 导航栏部件基类
 *
 *
 */
export class MobExpBarControlBase extends MainControlBase {
    /**
       * 导航栏部件模型对象
       *
       * @memberof ExpBarControlBase
       */
    public controlInstance!: IPSExpBar;

    /**
     * 数据部件
     *
     * @memberof ExpBarControlBase
     */
    protected $xDataControl!: IPSDECalendar | IPSControlNavigatable | IPSDETree;

    /**
     * 父导航视图模型对象
     *
     * @memberof ExpBarControlBase
     */
    protected parentModel!: IPSAppDEMultiDataView;

    /**
     * 数据部件名称
     *
     * @memberof ExpBarControlBase
     */
    public xDataControlName!: string;

    /**
     * 视图唯一标识
     *
     * @type {boolean}
     * @memberof ExpBarControlBase
     */
    public viewUID: string = '';

    /**
     * 搜素值
     *
     * @type {(string)}
     * @memberof ExpBarControlBase
     */
    public searchText: string = '';

    /**
     * 导航视图名称
     *
     * @type {*}
     * @memberof ExpBarControlBase
     */
    public navView!: any;

    /**
     * 导航参数
     *
     * @type {*}
     * @memberof ExpBarControlBase
     */
    public navParam: any = {};

    /**
     * 导航上下文参数
     *
     * @type {*}
     * @memberof ExpBarControlBase
     */
    public navigateContext: any = {};

    /**
     * 导航视图参数
     *
     * @type {*}
     * @memberof ExpBarControlBase
     */
    public navigateParams: any = {};

    /**
     * 导航过滤项
     *
     * @type {string}
     * @memberof ExpBarControlBase
     */
    public navFilter: string = "";

    /**
     * 导航关系
     *
     * @type {string}
     * @memberof ExpBarControlBase
     */
    public navPSDer: string = "";

    /**
     * 选中数据
     *
     * @type {*}
     * @memberof ExpBarControlBase
     */
    public selection: any = {};

    /**
     * 工具栏模型数据
     *
     * @protected
     * @type {*}
     * @memberof ExpBarControlBase
     */
    protected toolbarModels: any;

    /**
     * 快速分组数据对象
     *
     * @memberof ExpBarControlBase
     */
    public quickGroupData: any;

    /**
     * 快速分组是否有抛值
     *
     * @memberof ExpBarControlBase
     */
    public isEmitQuickGroupValue: boolean = false;

    /**
     * 快速分组模型
     *
     * @memberof ExpBarControlBase
     */
    public quickGroupModel: Array<any> = [];

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof ExpBarControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.viewUID = newVal.viewUID;
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 部件模型数据初始化实例
     *
     * @memberof ExpBarControlBase
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        this.xDataControlName = this.controlInstance.xDataControlName;
        this.$xDataControl = ModelTool.findPSControlByName(this.xDataControlName, this.controlInstance.getPSControls());
        this.showBusyIndicator = this.controlInstance?.showBusyIndicator;
        //TODO  待补充
        this.navigateContext = null;
        this.navigateParams = null;
        await this.handleXDataCtrlOptions();
    }

    /**
     * 处理数据部件参数
     *
     * @memberof ExpBarControlBase
     */
    public async handleXDataCtrlOptions() {
        this.parentModel = this.controlInstance.getParentPSModelObject() as IPSAppDEMultiDataView;
        if (this.parentModel && this.parentModel.enableQuickGroup) {
            await this.loadQuickGroupModel();
        }
    }

    /**
     * 部件初始化
     *
     * @param {*} [args]
     * @memberof ExpBarControlBase
     */
    public ctrlInit(args?: any) {
        super.ctrlInit(args);
        this.initCtrlToolBar();
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if (this.$xDataControl) {
                    this.viewState.next({ tag: this.xDataControlName, action: action, data: data });
                }
            });
        }
    }

    /**
     * 设置导航区工具栏禁用状态
     *
     * @param {boolean} state
     * @return {*} 
     * @memberof ExpBarControlBase
     */
    public calcToolbarItemState(state: boolean) {
        let _this: any = this;
        const models: any = _this.toolbarModels;
        if (models && models.length > 0) {
            for (const key in models) {
                if (!models.hasOwnProperty(key)) {
                    return;
                }
                const _item = models[key];
                if (_item.uiaction && (Object.is(_item.uiaction.actionTarget, 'SINGLEKEY') || Object.is(_item.uiaction.actionTarget, 'MULTIKEY'))) {
                    _item.disabled = state;
                }
                _item.visabled = true;
                if (_item.noprivdisplaymode && _item.noprivdisplaymode === 6) {
                    _item.visabled = false;
                }
            }
            this.calcNavigationToolbarState();
        }
    }

    /**
     * 加载快速分组模型
     *
     * @memberof ExpBarControlBase
     */
    public async loadQuickGroupModel() {
        let quickGroupCodeList: IPSAppCodeList = this.parentModel.getQuickGroupPSCodeList() as IPSAppCodeList;
        await quickGroupCodeList?.fill?.();
        let codeListService = new CodeListServiceBase({ $store: this.$store });
        if (!(quickGroupCodeList && quickGroupCodeList.codeName)) {
            return;
        }
        try {
            let res: any = await codeListService.getDataItems({ tag: quickGroupCodeList.codeName, type: quickGroupCodeList.codeListType, codeList: quickGroupCodeList });
            this.quickGroupModel = [...this.handleDynamicData(JSON.parse(JSON.stringify(res)))];
        } catch (error: any) {
            console.log(`----${quickGroupCodeList.codeName}----代码表不存在`);
        }
    }

    /**
     * 处理快速分组模型动态数据部分(%xxx%)
     *
     * @memberof ExpBarControlBase
     */
    public handleDynamicData(inputArray: Array<any>) {
        if (inputArray.length > 0) {
            const defaultSelect: any = this.getQuickGroupDefaultSelect();
            if (defaultSelect) {
                let select = inputArray.find((item: any) => { return item.value === defaultSelect.value; });
                if (select) select.default = true;
            }
            inputArray.forEach((item: any) => {
                if (item.data && Object.keys(item.data).length > 0) {
                    Object.keys(item.data).forEach((name: any) => {
                        let value: any = item.data[name];
                        if (value && typeof (value) == 'string' && value.startsWith('%') && value.endsWith('%')) {
                            const key = (value.substring(1, value.length - 1)).toLowerCase();
                            if (this.context[key]) {
                                value = this.context[key];
                            } else if (this.viewparams[key]) {
                                value = this.viewparams[key];
                            }
                        }
                        item.data[name] = value;
                    })
                }
            })
        }
        return inputArray;
    }

    /**
     * 获取快速分组默认选中项
     *
     * @memberof ExpBarControlBase
     */
    public getQuickGroupDefaultSelect() {
        const codeListItems: Array<IPSCodeItem> = (this.parentModel?.getQuickGroupPSCodeList() as IPSAppCodeList)?.getPSCodeItems() || [];
        let defaultSelect: any = null;
        if (codeListItems.length > 0) {
            for (const item of codeListItems) {
                const childItems: Array<IPSCodeItem> = item.getPSCodeItems() || [];
                if (childItems.length > 0) {
                    defaultSelect = childItems.find((_item: any) => { return _item.default; });
                }
                if (item.default || defaultSelect) {
                    defaultSelect = item;
                    break;
                }
            }
        }
        return defaultSelect;
    }

    /**
     * 初始化工具栏数据
     * 
     * @memberof ExpBarControlBase
     */
    public initCtrlToolBar() {
        let targetViewToolbarItems: any[] = [];
        const toolbar: IPSDEToolbar = ModelTool.findPSControlByType("TOOLBAR", this.controlInstance.getPSControls() || []) as IPSDEToolbar;
        if (toolbar) {
            const toolbarItems: Array<IPSDEToolbarItem> = toolbar.getPSDEToolbarItems() || [];
            toolbarItems.forEach((item: IPSDEToolbarItem) => {
                let itemModel: any = { name: item.name, showCaption: item.showCaption, showIcon: item.showIcon, tooltip: item.tooltip, iconcls: (item.getPSSysImage() as IPSSysImage)?.cssClass, icon: (item.getPSSysImage() as IPSSysImage)?.glyph, caption: item.caption, disabled: false, itemType: item.itemType, visabled: true };
                if (item.itemType && item.itemType == "DEUIACTION") {
                    const uiAction: IPSDEUIAction = (item as IPSDETBUIActionItem).getPSUIAction() as IPSDEUIAction;
                    Object.assign(itemModel, {
                        actiontarget: (item as IPSDETBUIActionItem).uIActionTarget,
                        noprivdisplaymode: (item as IPSDETBUIActionItem).data,
                        dataaccaction: uiAction?.dataAccessAction,
                        uiaction: {
                            tag: uiAction?.uIActionTag,
                            target: uiAction?.actionTarget
                        }
                    });
                    //TODO YY 表格工具栏
                }
                targetViewToolbarItems.push(itemModel);
            })
        }
        this.toolbarModels = targetViewToolbarItems;
    }

    /**
     * 部件挂载
     * 
     * @param args 
     * @memberof ExpBarControlBase
     */
    public ctrlMounted(args?: any) {
        super.ctrlMounted(args);
    }

    /**
     * 绘制数据部件
     *
     * @memberof ExpBarControlBase
     */
    public renderXDataControl() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.$xDataControl);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.xDataControlName, on: targetCtrlEvent });
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof ExpBarControlBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.staticProps, {
            isSelectFirstDefault: true,
            isSingleSelect: true,
        })
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }

    /**
    * 执行搜索
    *
    * @memberof ExpBarControlBase
    */
    public onSearch($event?: any) { }

    /**
     * 绘制快速搜索
     * 
     * @memberof ExpBarControlBase
     */
    public renderSearch() {
        const appDataEntity: IPSAppDataEntity = this.controlInstance.getPSAppDataEntity() as IPSAppDataEntity;
        const getQuickSearchPlaceholader = (entity: IPSAppDataEntity) => {
            let placeholder: any = '';
            const fields: Array<IPSAppDEField> = entity.getQuickSearchPSAppDEFields() || [];
            fields.forEach((field: IPSAppDEField, index: number) => {
                const _field = entity.findPSAppDEField(field.codeName);
                if (_field) {
                    placeholder += `${_field.logicName ? _field.logicName : ''} ${index === fields.length - 1 ? '' : ', '}`;
                }
            })
            return placeholder;
        }
        return (
            <div class="search-container">
                <i-input
                    search={true}
                    on-on-change={($event: any) => { this.searchText = $event.target.value; }}
                    placeholder={getQuickSearchPlaceholader(appDataEntity)}
                    on-on-search={($event: any) => this.onSearch($event)}>
                </i-input>
            </div>
        );
    }


    /**
     * 绘制快速分组
     * 
     * @memberof ExpBarControlBase
     */
    public renderQuickGroup() {
        const enableCounter = this.controlInstance.enableCounter;
        if (enableCounter) {
            let counterService: any;
            let sysCounter: IPSAppCounterRef | null = this.parentModel?.getPSAppCounterRef();
            if (sysCounter?.id) {
                counterService = Util.findElementByField(this.counterServiceArray, 'id', sysCounter.id)?.service;
            }
            return <app-quick-group items={this.quickGroupModel} counterService={counterService} on-valuechange={(value: any) => this.quickGroupValueChange(value)}></app-quick-group>
        } else {
            return <app-quick-group items={this.quickGroupModel} on-valuechange={(value: any) => this.quickGroupValueChange(value)}></app-quick-group>
        }
    }

    /**
     * 绘制工具栏
     * 
     * @memberof ExpBarControlBase
     */
    public renderToolbar() {
        return (<view-toolbar slot='toolbar' toolbarModels={this.toolbarModels} on-item-click={(data: any, $event: any) => { this.handleItemClick(data, $event) }}></view-toolbar>);
    }

    /**
     * 工具栏点击
     * 
     * @memberof ExpBarControlBase
     */
    public handleItemClick(data: any, $event: any) {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        AppViewLogicService.getInstance().executeViewLogic(`${this.controlInstance.name?.toLowerCase()}_toolbar_${data.tag}_click`, $event, this, undefined, this.controlInstance.getPSAppViewLogics() || []);
    }

    /**
     * 快速分组值变化
     *
     * @memberof ExpBarControlBase
     */
    public quickGroupValueChange($event: any) {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        if ($event && $event.data) {
            if (this.quickGroupData) {
                for (let key in this.quickGroupData) {
                    delete this.viewparams[key];
                }
            }
            this.quickGroupData = $event.data;
            Object.assign(this.viewparams, $event.data);
        } else {
            if (this.quickGroupData) {
                for (let key in this.quickGroupData) {
                    delete this.viewparams[key];
                }
            }
        }
        if (this.isEmitQuickGroupValue) {
            this.onSearch($event);
        }
        this.isEmitQuickGroupValue = true;
    }

    /**
     * 计算导航工具栏权限状态
     * 
     * @memberof ExpBarControlBase
     */
    public calcNavigationToolbarState() {
        let _this: any = this;
        if (_this.toolbarModels) {
            ViewTool.calcActionItemAuthState({}, this.toolbarModels, this.appUIService);
        }
    }

    /**
     * 刷新
     *
     * @memberof ExpBarControlBase
     */
    public refresh(args?: any): void {
        if (this.$xDataControl) {
            const xDataControl: any = (this.$refs[`${(this.xDataControlName)?.toLowerCase()}`] as any).ctrl;
            if (xDataControl && xDataControl.refresh && xDataControl.refresh instanceof Function) {
                xDataControl.refresh();
            }
        }
    }

    /**
     * 选中数据事件
     * 
     * @memberof ExpBarControlBase
     */
    public onSelectionChange(args: any[], tag?: string, $event2?: any): void {
        let tempContext: any = {};
        let tempViewParam: any = {};
        if (args.length === 0) {
            this.calcToolbarItemState(true);
            return;
        }
        const arg: any = args[0];
        if (this.context) {
            Object.assign(tempContext, JSON.parse(JSON.stringify(this.context)));
        }
        if (this.$xDataControl) {
            const appDataEntity: IPSAppDataEntity | null = this.$xDataControl?.getPSAppDataEntity();
            if (appDataEntity) {
                Object.assign(tempContext, { [`${appDataEntity.codeName?.toLowerCase()}`]: arg[appDataEntity.codeName?.toLowerCase()] });
                Object.assign(tempContext, { srfparentdename: appDataEntity.codeName, srfparentdemapname: (appDataEntity as any)?.getPSDEName(), srfparentkey: arg[appDataEntity.codeName?.toLowerCase()] });
                if (this.navFilter && !Object.is(this.navFilter, "")) {
                    Object.assign(tempViewParam, { [this.navFilter]: arg[appDataEntity.codeName?.toLowerCase()] });
                }
                if (this.navPSDer && !Object.is(this.navPSDer, "")) {
                    Object.assign(tempViewParam, { [this.navPSDer]: arg[appDataEntity.codeName?.toLowerCase()] });
                }
            }
            if (this.navigateContext && Object.keys(this.navigateContext).length > 0) {
                let _context: any = this.$util.computedNavData(arg, tempContext, tempViewParam, this.navigateContext);
                Object.assign(tempContext, _context);
            }
            if (this.navigateParams && Object.keys(this.navigateParams).length > 0) {
                let _params: any = this.$util.computedNavData(arg, tempContext, tempViewParam, this.navigateParams);
                Object.assign(tempViewParam, _params);
            }
            this.selection = {};
            Object.assign(this.selection, { view: { viewname: 'app-view-shell' }, context: tempContext, viewparam: tempViewParam, viewModelData: this.navView });
            this.calcToolbarItemState(false);
            this.$forceUpdate();
        }
    }

    /**
     * load完成事件
     * 
     * @memberof ExpBarControlBase
     */
    public onLoad(args: any, tag?: string, $event2?: any) {
        this.calcToolbarItemState(true);
        if (this.$xDataControl) {
            this.$emit('ctrl-event', { controlname: this.xDataControlName, action: "load", data: args });
        }
    }

    public onCtrlEvent(controlname: any, action: any, data: any) {
        if (controlname && Object.is(controlname, this.xDataControlName)) {
            switch (action) {
                case "selectionchange":
                    this.onSelectionChange(data, action);
                    return;
                case "load":
                    this.onLoad(data, action);
                    return;
            }
        }
        super.onCtrlEvent(controlname, action, data);
    }

    /**
     * 绘制右侧导航组件
     * 
     * @memberof ExpBarControlBase
     */
    public renderNavView() {
        if (this.selection?.view && !Object.is(this.selection.view.viewname, '')) {
            let targetCtrlParam: any = {
                staticProps: {
                    viewDefaultUsage: 'includedView',
                    viewModelData: this.selection.viewModelData
                },
                dynamicProps: {
                    _viewparams: JSON.stringify(this.selection.viewparam),
                    _context: JSON.stringify(this.selection.context),
                }
            };
            return this.$createElement('app-view-shell', {
                class: "viewcontainer2",
                props: targetCtrlParam,
            });
        }
    }
}
