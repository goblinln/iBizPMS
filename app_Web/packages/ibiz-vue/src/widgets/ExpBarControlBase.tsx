import { CodeListServiceBase, IBizExpBarModel, IBizToolBarItemModel, Util, ViewTool } from 'ibiz-core';
import { AppViewLogicService } from '../app-service';
import { MainControlBase } from './MainControlBase';
/**
 * 导航栏部件基类
 *
 *
 */
export class ExpBarControlBase extends MainControlBase {
    /**
     * 导航栏部件模型对象
     *
     * @memberof ExpBarControlBase
     */
    public controlInstance!: IBizExpBarModel;

    /**
     * 数据部件
     *
     * @memberof ExpBarControlBase
     */
    protected $xDataControl!: any;

    /**
     * 视图唯一标识
     *
     * @type {boolean}
     * @memberof ExpBarControlBase
     */
    public viewUID: string = '';

    /**
     * 导航边栏位置
     *
     * @memberof ExpBarControlBase
     */
    public sideBarlayout: any;

    /**
     * 搜素值
     *
     * @type {(string)}
     * @memberof ExpBarControlBase
     */
    public searchText: string = '';

    /**
     * 呈现模式，可选值：horizontal或者vertical
     *
     * @public
     * @type {(string)}
     * @memberof ExpBarControlBase
     */
    public showMode: string = '';

    /**
     * 控件宽度
     *
     * @type {number}
     * @memberof ExpBarControlBase
     */
    public ctrlWidth: number = 0;

    /**
     * 控件高度
     *
     * @type {number}
     * @memberof ExpBarControlBase
     */
    public ctrlHeight: number = 0;

    /**
     * 分割宽度
     *
     * @type {number}
     * @memberof ExpBarControlBase
     */
    public split: number = 0.3;

    /**
     * 导航视图名称
     *
     * @type {*}
     * @memberof ExpBarControlBase
     */
    public navViewName: any = {};

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
     * 备份当前分割宽度
     * 
     * @memberof ExpBarControlBase
     */
    public copySplit: number = 0;

    /**
     * 导航dom节点
     * 
     * @memberof ExpBarControlBase
     */
    public expDom: any;

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof ControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.viewUID = newVal.viewUID;
        this.sideBarlayout = newVal.sideBarLayout ? newVal.sideBarLayout : "LEFT";
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 部件模型数据初始化实例
     *
     * @memberof GridExpBarControlBase
     */
    public async ctrlModelInit(args?:any) {
        await super.ctrlModelInit();
        this.$xDataControl = this.controlInstance.getXDataControl();
        this.showBusyIndicator = this.controlInstance.showBusyIndicator;
        this.ctrlWidth = this.controlInstance.width;
        this.showMode = this.sideBarlayout && this.sideBarlayout == "LEFT" ? "horizontal" : "vertical";
        if(this.$xDataControl?.navAppView) {
            this.navViewName = this.$xDataControl.navAppView.dynaModelFilePath;
        }
        this.navFilter = this.$xDataControl?.navFilter ? this.$xDataControl.navFilter : "";
        this.navPSDer = this.$xDataControl?.navPSDer ? this.$xDataControl.navPSDer : "";
        //TODO  待补充
        this.navigateContext = null;
        this.navigateParams = null;
        if(this.controlInstance.getView()?.enableQuickGroup) {
            await this.loadQuickGroupModel();
        }
    }

    /**
     * 部件初始化
     *
     * @param {*} [args]
     * @memberof TreeExpBarControlBase
     */
    public ctrlInit(args?:any){
        super.ctrlInit(args);
        this.initCtrlToolBar();
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if(this.$xDataControl){
                    this.viewState.next({ tag: this.$xDataControl.name, action: action, data: data });
                }
            });
        }
    }

    /**
     * 设置导航区工具栏禁用状态
     *
     * @param {boolean} state
     * @return {*} 
     * @memberof GridExpBarControlBase
     */
    public calcToolbarItemState(state: boolean) {
        let _this: any = this;
        const models: any = _this.toolbarModels;
        if(models && models.length >0) {
            for(const key in models) {
                if(!models.hasOwnProperty(key)) {
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
     * @memberof GridExpBarControlBase
     */
    public async loadQuickGroupModel(){
        let quickGroupCodeList = this.controlInstance.getView().quickGroupCodeList;
        let codeListService = new CodeListServiceBase({ $store: this.$store });

        if (!(quickGroupCodeList && quickGroupCodeList.codeName)) {
            return;
        }
        try {
            let res: any = await codeListService.getDataItems({tag: quickGroupCodeList.codeName, type: quickGroupCodeList.codeListType});
            this.quickGroupModel = [...this.handleDynamicData(JSON.parse(JSON.stringify(res)))];
        } catch (error: any) {
            console.log(`----${quickGroupCodeList.codeName}----代码表不存在`);
        }
    }

    /**
     * 处理快速分组模型动态数据部分(%xxx%)
     *
     * @memberof MDViewBase
     */
    public handleDynamicData(inputArray: Array<any>) {
        if (inputArray.length > 0) {
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
     * 初始化工具栏数据
     * 
     * @memberof ExpBarControlBase
     */
    public initCtrlToolBar() {
        let targetViewToolbarItems: any[] = [];
        if (this.controlInstance && this.controlInstance.toolbar?.getPSDEToolbarItems) {
            this.controlInstance.toolbar.getPSDEToolbarItems.forEach((item: IBizToolBarItemModel) => {
                targetViewToolbarItems.push({ name: item.name, showCaption: item.showCaption, showIcon: item.showIcon, tooltip: item.tooltip, iconcls: item.getPSSysImage?.cssClass, icon: item.getPSSysImage?.glyph, actiontarget: item.uIActionTarget, caption: item.caption, disabled: false, itemType: item.itemType, visabled: true, noprivdisplaymode: item.noPrivDisplayMode, dataaccaction: '', uiaction: {} });
            })
        }
        this.toolbarModels = targetViewToolbarItems;
    }

    /**
     * split值变化事件
     *
     * @memberof ExpBarControlBase
     */
    public onSplitChange() {
        if (this.split) {
            this.$store.commit("setViewSplit",{viewUID: `${this.controlInstance.getView()?.codeName}_${this.controlInstance.codeName}`, viewSplit:this.split});
        }
    }

    /**
     * 部件挂载
     * 
     * @param args 
     * @memberof ExpBarControlBase
     */
    public ctrlMounted(args?: any) {
        super.ctrlMounted(args);
        const tempSplit = this.$store.getters.getViewSplit(`${this.controlInstance.getView()?.codeName}_${this.controlInstance.codeName}`);
        if(tempSplit){
            this.split = Number(tempSplit);
        }else{
            let splitDom: any = this.$refs[`${this.controlInstance.appDataEntity?.codeName}-${this.controlInstance.codeName.toLowerCase()}`];
            let containerWidth:number = splitDom?.$el?.offsetWidth;
            let containerHeight:number = splitDom?.$el?.offsetHeight;
            if(Object.is(this.showMode,'horizontal')){
                if(this.ctrlWidth && containerWidth){
                    this.split = this.ctrlWidth/containerWidth;
                }
            }else{
                if(this.ctrlHeight && containerHeight){
                    this.split = this.ctrlHeight/containerHeight;
                }
            }
            this.$store.commit("setViewSplit",{viewUID: `${this.controlInstance.getView()?.codeName}_${this.controlInstance.codeName}`, viewSplit:this.split});
        }
    }

    /**
     * 绘制数据部件
     *
     * @memberof ExpBarControlBase
     */
    public renderXDataControl() {
        if (this.$xDataControl) {
            let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.$xDataControl);
            return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.$xDataControl.name, on: targetCtrlEvent });
        }
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof ExpBarControlBase
     */
    public computeTargetCtrlData(controlInstance:any) {
      const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
      Object.assign(targetCtrlParam.staticProps,{
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
   public onSearch($event?:any) { }

    /**
     * 绘制快速搜索
     * 
     * @memberof ExpBarControlBase
     */
    public renderSearch() {
        const { appDataEntity } = this.controlInstance;
        return (
            <div class="search-container">
                <i-input
                    search={true}
                    on-on-change={($event: any) => { this.searchText = $event.target.value; }}
                    placeholder={appDataEntity?.getQuickSearchPlaceholder()}
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
        const { enableCounter } = this.controlInstance;
        if(enableCounter) {
            let counterService: any;
            let sysCounter: any = this.controlInstance.getView().getPSSysCounterRef;
            if (sysCounter?.id) {
                counterService = Util.findElementByField(this.counterServiceArray, 'id', sysCounter.id )?.service;
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
        return (<view-toolbar slot='toolbar' toolbarModels={this.toolbarModels} on-item-click={(data: any,$event: any) => { this.handleItemClick(data,$event) }}></view-toolbar>);
    }

    /**
     * 工具栏点击
     * 
     * @memberof ExpBarControlBase
     */
    public handleItemClick(data: any,$event: any) {
        AppViewLogicService.getInstance().excuteViewLogic(`${this.controlInstance.name?.toLowerCase()}_toolbar_${data.tag}_click`,$event,this,undefined,this.controlInstance.getPSAppViewLogics);
    }

    /**
     * 快速分组值变化
     *
     * @memberof GridExpBarControlBase
     */
    public quickGroupValueChange($event:any){
        if($event && $event.data){
            if(this.quickGroupData) {
                for(let key in this.quickGroupData) {
                    delete this.viewparams[key];
                }
            }
            this.quickGroupData = $event.data;
            Object.assign(this.viewparams, $event.data);
        }else{
            if(this.quickGroupData) {
                for(let key in this.quickGroupData) {
                    delete this.viewparams[key];
                }
            }
        }
        if(this.isEmitQuickGroupValue){
            this.onSearch($event);
        }
        this.isEmitQuickGroupValue = true;
    }

    /**
     * 计算导航工具栏权限状态
     * 
     * @memberof GridExpBarControlBase
     */
    public calcNavigationToolbarState(){
        let _this: any = this;
        if(_this.toolbarModels) {
            ViewTool.calcActionItemAuthState({}, this.toolbarModels, this.appUIService);
        }
    }

    /**
     * 刷新
     *
     * @memberof GridExpBarControlBase
     */
    public refresh(args?: any): void {
        if(this.$xDataControl) {
            const xDataControl: any = (this.$refs[`${this.$xDataControl.name.toLowerCase()}`] as any).ctrl;
            if(xDataControl && xDataControl.refresh && xDataControl.refresh instanceof Function) {
                xDataControl.refresh();
            }
        }
    } 

    /**
     * 选中数据事件
     * 
     * @memberof GridExpBarControlBase
     */
    public onSelectionChange(args: any [], tag?: string, $event2?: any): void {
        let tempContext: any = {};
        let tempViewParam: any = {};
        if(args.length === 0) {
            this.calcToolbarItemState(true);
            return;
        }
        const arg: any = args[0];
        if(this.context) {
            Object.assign(tempContext, JSON.parse(JSON.stringify(this.context)));
        }
        if(this.$xDataControl) {
            const appDataEntity = this.$xDataControl.appDataEntity;
            if(appDataEntity) {
                Object.assign(tempContext, { [`${appDataEntity.codeName.toLowerCase()}`]: arg[appDataEntity.codeName.toLowerCase()] });
                Object.assign(tempContext, { srfparentdename: appDataEntity.codeName, srfparentkey: arg[appDataEntity.codeName.toLowerCase()] });
                if(this.navFilter && !Object.is(this.navFilter, "")) {
                    Object.assign(tempViewParam, { [this.navFilter]: arg[appDataEntity.codeName.toLowerCase()] });
                }
                if(this.navPSDer && !Object.is(this.navPSDer, "")) {
                    Object.assign(tempViewParam, { [this.navPSDer]: arg[appDataEntity.codeName.toLowerCase()] });
                }
            }
            if(this.navigateContext && Object.keys(this.navigateContext).length>0) {
                let _context:any = this.$util.computedNavData(arg,tempContext,tempViewParam,this.navigateContext);
                Object.assign(tempContext,_context);
            }
            if(this.navigateParams && Object.keys(this.navigateParams).length >0){
                let _params:any = this.$util.computedNavData(arg,tempContext,tempViewParam,this.navigateParams);
                Object.assign(tempViewParam,_params);
            }
            this.selection = {};
            Object.assign(tempContext, { viewpath: this.navViewName });
            Object.assign(this.selection, { view: { viewname: 'app-view-shell' },  context:tempContext,viewparam:tempViewParam });
            this.calcToolbarItemState(false);
            if(this.expDom && this.selection){
                this.expDom.className = 'ivu-split-horizontal openedit';
                this.split = this.copySplit;
            }
            this.$forceUpdate();
        }
    }

    /**
     * load完成事件
     * 
     * @memberof GridExpBarControlBase
     */
    public onLoad(args: any, tag?: string, $event2?: any) {
        this.calcToolbarItemState(true);
        if(this.$xDataControl) {
            this.$emit('ctrl-event', { controlname: this.$xDataControl.name, action: "load", data: args });
        }
    }

    public onCtrlEvent(controlname: any, action: any, data: any) {
        if(!(controlname && Object.is(controlname, this.$xDataControl.name))) {
            return;
        }
        switch (action) {
            case "selectionchange": 
                this.onSelectionChange(data, action);
                break;
            case "load": 
                this.onLoad(data, action);
                break;
        }
    }

    /**
     * 关闭编辑视图
     * 
     * @memberof ExpBarControlBase
     */
    public close(data: any) {
        this.expDom = document.getElementsByClassName('ivu-split-horizontal')[1];
        this.expDom.className = 'ivu-split-horizontal closeedit';
        this.selection = {};
        this.copySplit = this.split;
        this.split = 1;
    }

    public renderTitleBar() {
        const classStr: string = `${this.$xDataControl.controlType?.toLowerCase()}-exp-bar`;
        return (
            <div class={`${classStr}-header`}>
                <div class={`${classStr}-title`}>
                    <icon type="ios-home-outline"/>
                    <span>{this.controlInstance?.title}</span>
                </div>
            </div>
        );
    }

    /**
     * 绘制右侧导航组件
     * 
     * @memberof ExpBarControlBase
     */
    public renderNavView() {
        if(this.selection?.view && !Object.is(this.selection.view.viewname, '')) {
            let targetCtrlParam: any = {
                staticProps: {
                    viewDefaultUsage: false,
                },
                dynamicProps:{
                    viewparam: JSON.stringify(this.selection.viewparam),
                    viewdata: JSON.stringify(this.selection.context),
                }
            };
            return this.$createElement(this.selection.view.viewname, {
                class: "viewcontainer2",
                props: targetCtrlParam,
                on: {close: (data: any)=>{ this.close(data) }}
            });
        }
    }

    /**
     * 绘制内容
     * 
     * @memberof ExpBarControlBase
     */
    public renderContent(otherClassNames?: any) {
        const {
            showTitleBar,
            enableSearch,
            toolbar,
        } = this.controlInstance;
        let enableQuickGroup = this.controlInstance.getView()?.enableQuickGroup;
        const controlType = this.$xDataControl.controlType;
        let classNames: any = {
            [`${controlType.toLowerCase()}-exp-bar-content`]: showTitleBar ? true : false,
            [`${controlType.toLowerCase()}-exp-bar-content2`]: !showTitleBar ? true : false,
            "has-search": enableSearch ? true : false
        };
        if(otherClassNames) {
            Object.assign(classNames, otherClassNames);
        }
        return [
            <div slot={this.sideBarlayout == "LEFT" ? "left" : "top"}>
                {showTitleBar ? this.renderTitleBar() : null}
                <div class="container-header">
                    { enableQuickGroup ? this.renderQuickGroup() : null }
                    { enableSearch ? this.renderSearch() : null }
                    { toolbar ? this.renderToolbar() : null }
                </div>
                <div class={classNames}>
                    { this.renderXDataControl() }
                </div>
            </div>,
            <div slot={this.sideBarlayout == "LEFT" ? "right" : "bottom"}>
                {this.selection && this.selection.view ? 
                    this.renderNavView() : null}
            </div>
        ];
    }

    /**
     * 绘制导航栏主体内容
     * 
     * @memberof ExpBarControlBase
     */
    public renderMainContent() {
        if(!this.controlIsLoaded) {
            return null;
        }
        return (
            <split
                ref={`${this.controlInstance.appDataEntity?.codeName}-${this.controlInstance.codeName.toLowerCase()}`}
                id={this.controlInstance.codeName.toLowerCase()}
                class={[`app-${this.$xDataControl.controlType?.toLowerCase()}-exp-bar`, this.renderOptions.controlClassNames]}
                v-model={this.split}
                mode={this.sideBarlayout == 'LEFT' ? 'horizontal' : 'vertical'}
                on-on-move-end={this.onSplitChange.bind(this)}>
                    {this.renderContent()}
            </split>
        );
    }

}
