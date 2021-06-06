import { IPSAppDEUIAction, IPSDBAppViewPortletPart, IPSDBPortletPart, IPSDETBUIActionItem, IPSDEToolbar, IPSDEToolbarItem, IPSLanguageRes, IPSUIActionGroupDetail } from "@ibiz/dynamic-model-api";
import { AppViewLogicService } from '../app-service/logic-service/app-viewlogic-service';
import { MainControlBase } from "./main-control-base";

/**
 * 门户部件基类
 *
 * @export
 * @class ControlBase
 * @extends {PortletControlBase}
 */
export class PortletControlBase extends MainControlBase {

    /**
     * 部件模型实例对象
     *
     * @type {IPSDBPortletPart}
     * @memberof PortletControlBase
     */
    public controlInstance!: IPSDBPortletPart;

    /**
     * 操作栏模型对象
     *
     * @type {*}
     * @memberof PortletControlBase
     */
    public actionBarModelData: any = {};

    /**
     * 工具栏栏模型对象
     *
     * @type {*}
     * @memberof PortletControlBase
     */
    public toolbarModels: any;

    /**
     * 是否自适应大小
     *
     * @returns {boolean}
     * @memberof PortletControlBase
     */
    public isAdaptiveSize?: boolean;
    

    /**
     * 初始化工具栏数据
     * 
     * @memberof PortletControlBase
     */
    public initToolBar() {
        let toolbar = this.controlInstance.getPSControls()?.[0] as IPSDEToolbar;
        let targetToolbarItems: any[] = [];
        if (toolbar && toolbar.getPSDEToolbarItems()?.length) {
            toolbar.getPSDEToolbarItems()?.forEach((item: IPSDEToolbarItem) => {
                targetToolbarItems.push({
                        name: item.name,
                        showCaption: item.showCaption,
                        showIcon: item.showIcon,
                        tooltip: this.$tl((item.getTooltipPSLanguageRes() as IPSLanguageRes)?.lanResTag, item.tooltip),
                        iconcls: item.getPSSysImage?.()?.cssClass,
                        icon: item.getPSSysImage?.()?.imagePath,
                        actiontarget: (item as IPSDETBUIActionItem).uIActionTarget,
                        caption: this.$tl((item.getCapPSLanguageRes() as IPSLanguageRes)?.lanResTag, item.caption),
                        disabled: false,
                        itemType: item.itemType,
                        visabled: true,
                        noprivdisplaymode: (item as IPSDETBUIActionItem).noPrivDisplayMode,
                        dataaccaction: '',
                        uiaction: {}
                    });
            })
        }
        this.toolbarModels = targetToolbarItems;
    }

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof PortletControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal, oldVal);
        this.isAdaptiveSize = newVal.isAdaptiveSize;
    }

    /**
     * 初始化操作栏数据
     * 
     * @memberof PortletControlBase
     */
    public initActionBar() {
        let groupDetails = this.controlInstance.getPSUIActionGroup?.()?.getPSUIActionGroupDetails?.()
        if (groupDetails?.length) {
            groupDetails.forEach((item: IPSUIActionGroupDetail, index: number) => {
                let appUIAction = item.getPSUIAction() as IPSAppDEUIAction;
                if(appUIAction){
                    this.actionBarModelData[appUIAction.uIActionTag] = Object.assign(appUIAction, { 
                        viewlogicname: item.name,
                        actionName: this.$tl(appUIAction?.getCapPSLanguageRes()?.lanResTag ,appUIAction?.caption), 
                        icon: appUIAction?.getPSSysImage?.()?.cssClass,
                        // todo 计数器
                        // counterService: null,
                        // counterId: null, 
                        disabled: false,  
                        visabled: true, 
                        getNoPrivDisplayMode: appUIAction.noPrivDisplayMode || 6 
                    });
                }
            })
        }
    }

    /**
     * 触发界面行为
     *
     * @param {*} $event
     * @memberof PortletControlBase
     */
    public handleItemClick(data: any, $event: any) {
        let tag = `${this.controlInstance.name?.toLowerCase()}_${data.tag}_click`;
        if(this.controlInstance?.portletType == 'TOOLBAR'){
            // 工具栏要用工具栏的name
            tag = `${this.controlInstance.getPSControls()?.[0]?.name?.toLowerCase()}_${data.tag}_click`;
        }
        AppViewLogicService.getInstance().executeViewLogic(tag, $event?$event:data.event, this, data.params?data.params:data, this.controlInstance.getPSAppViewLogics() as any[]);
    }

    /**
     * 初始化挂载状态集合
     *
     * @memberof PortletControlBase
     */
    public initMountedMap(){
        super.initMountedMap();
        let portletAppView = (this.controlInstance as IPSDBAppViewPortletPart)?.getPortletPSAppView?.();
        if(portletAppView){
            this.mountedMap.set(portletAppView.name, false);
        }
    }

    /**
     * 门户部件模型数据加载
     *
     * @memberof ViewBase
     */
     public async ctrlModelLoad(){ 
        switch (this.controlInstance.portletType) {
            case 'VIEW':
                await (this.controlInstance as IPSDBAppViewPortletPart)?.getPortletPSAppView()?.fill(true);
                break;
            case 'CUSTOM':
            case 'HTML':
            case 'RAWITEM':
                break;
            case 'APPMENU':
            case 'ACTIONBAR':
            case 'TOOLBAR':
            default:
                await this.controlInstance.getPSControls()?.[0]?.fill(true);
        }
     }

    /**
     * 初始化门户部件
     *
     * @memberof PortletControlBase
     */
    public async ctrlModelInit(opts: any) {
        await super.ctrlModelInit();
        //初始化工具栏模型
        if (this.controlInstance.portletType == 'TOOLBAR') {
            this.initToolBar();
        }
        //初始化操作栏模型
        if (this.controlInstance.portletType == 'ACTIONBAR') {
            this.initActionBar();
        }
    }

    /**
     * 初始化部件的绘制参数
     *
     * @param {*} [opts]
     * @memberof PortletControlBase
     */
    public initRenderOptions(opts?: any) {
        super.initRenderOptions(opts);
        const { portletType } = this.controlInstance;
        Object.assign(this.renderOptions.controlClassNames, {
            [portletType.toLowerCase()]: true,
        });
    }

    /**
     * 部件初始化
     *
     * @param {*} [args]
     * @memberof PortletControlBase
     */
    public ctrlInit(args?: any) {
        super.ctrlInit(args);
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (Object.is(tag, "all-portlet") && Object.is(action, 'loadmodel')) {
                    this.calcUIActionAuthState(data);
                }
                if (tag == 'all-portlet' && action == 'refreshAll'){
                    this.handleRefreshAll(data);
                }
                if (!Object.is(tag, this.name)) {
                    return;
                }
                const refs: any = this.$refs;
                Object.keys(refs).forEach((_name: string) => {
                    this.viewState.next({ tag: _name, action: action, data: data });
                });
            });
        }
    }

    /**
     * 刷新
     *
     * @memberof PortletControlBase
     */
    public refresh(args?: any) {
        const { portletType } = this.controlInstance;
        let controls = this.controlInstance?.getPSControls();
        if (portletType == "VIEW") {
            let portletAppView = (this.controlInstance as IPSDBAppViewPortletPart)?.getPortletPSAppView();
            this.viewState.next({ tag: portletAppView?.name, action: 'refresh', data: args });
        }
        if (portletType == "CHART" || portletType == "LIST") {
            this.viewState.next({ tag: controls?.[0].name, action: 'refresh', data: args });
        }
        if ( portletType == 'ACTIONBAR' ){
            this.ctrlEvent({ controlname: this.controlInstance.name, action: 'refreshAll', data: args })
        }
    }

    /**
     * 处理dashboard的refreshAll通知
     *
     * @param {*} [args]
     * @memberof PortletControlBase
     */
    public handleRefreshAll(args?: any) {
        const { portletType } = this.controlInstance;
        if (portletType == "VIEW" || portletType == "CHART" || portletType == "LIST") {
            this.refresh();
        }
    }

    /**
     * 计算界面行为权限
     *
     * @param {*} [data={}]
     * @returns
     * @memberof PortletControlBase
     */
    public calcUIActionAuthState(data: any = {}) {
        //  如果是操作栏，不计算权限
        if (this.controlInstance?.portletType == 'ACTIONBAR') {
            return;
        }
        let _this: any = this;
        // let uiservice: any = _this.appUIService ? _this.appUIService : new UIService(_this.$store);
        // if(_this.uiactionModel){
        //     ViewTool.calcActionItemAuthState(data,_this.uiactionModel,uiservice);
        // }
    }
}