import { IBizPortletModel } from "ibiz-core";
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
     * @type {IBizPortletModel}
     * @memberof PortletControlBase
     */
    public controlInstance!: IBizPortletModel;

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
        let toolbar = this.controlInstance.controls?.[0];
        let targetToolbarItems: any[] = [];
        if (toolbar && toolbar.getPSDEToolbarItems?.length > 0) {
            toolbar.getPSDEToolbarItems.forEach((item: any) => {
                targetToolbarItems.push({ name: item.name, showCaption: item.showCaption, showIcon: item.showIcon, tooltip: item.tooltip, iconcls: item?.getPSSysImage?.cssClass, icon: item.icon, actiontarget: item.uIActionTarget, caption: item.caption, disabled: false, itemType: item.itemType, visabled: true, noprivdisplaymode: item.noPrivDisplayMode, dataaccaction: '', uiaction: {} });
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
        if (this.controlInstance.actionGroupDetails?.length > 0) {
            this.controlInstance.actionGroupDetails.forEach((item: any, index: number) => {
                let appUIAction: any = item.getPSUIAction;
                this.actionBarModelData[appUIAction.uIActionTag] = Object.assign(appUIAction, { 
                    viewlogicname: item.name,
                    actionName: appUIAction?.caption, 
                    icon: appUIAction?.getPSSysImage?.cssClass,
                    // todo 计数器
                    // counterService: null,
                    // counterId: null, 
                    disabled: false, 
                    visabled: true, 
                    getNoPrivDisplayMode: appUIAction.getNoPrivDisplayMode ? appUIAction.getNoPrivDisplayMode : 6 });
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
            tag = `${this.controlInstance.controls?.[0]?.name?.toLowerCase()}_${data.tag}_click`;
        }
        AppViewLogicService.getInstance().executeViewLogic(tag, $event?$event:data.event, this, data.params?data.params:data, this.controlInstance.getPSAppViewLogics);
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
        const { portletType, portletAppView, controls } = this.controlInstance;
        if (portletType == "VIEW") {
            this.viewState.next({ tag: portletAppView.name, action: 'refresh', data: args });
        }
        if (portletType == "CHART" || portletType == "LIST") {
            this.viewState.next({ tag: controls[0].name, action: 'refresh', data: args });
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
        const { portletType, portletAppView, controls } = this.controlInstance;
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