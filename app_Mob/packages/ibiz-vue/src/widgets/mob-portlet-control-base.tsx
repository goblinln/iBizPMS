import { IPSAppDEUIAction, IPSDBAppViewPortletPart, IPSDBPortletPart, IPSDETBUIActionItem, IPSDEToolbar, IPSDEToolbarItem, IPSLanguageRes, IPSUIActionGroupDetail } from "@ibiz/dynamic-model-api";
import { MobPortletControlInterface } from "ibiz-core";
import { AppViewLogicService } from '../app-service/logic-service/app-viewlogic-service';
import { MainControlBase } from "./main-control-base";

/**
 * 门户部件基类
 *
 * @export
 * @class AppControlBase
 * @extends {MobPortletControlBase}
 */
export class MobPortletControlBase extends MainControlBase implements MobPortletControlInterface{

    /**
     * 部件模型实例对象
     *
     * @type {IBizMobPortletModel}
     * @memberof MobPortletControlBase
     */
    public controlInstance!: IPSDBPortletPart;

    /**
     * 选择器状态取消事件
     */
    public selectStatus: boolean = false;

    /**
     * 操作栏模型对象
     *
     * @type {*}
     * @memberof MobPortletControlBase
     */
    public actionBarModelData: any = [];

    /**
     * 工具栏栏模型对象
     *
     * @type {*}
     * @memberof MobPortletControlBase
     */
    public toolbarModels: any;


    /**
     * 初始化工具栏数据
     * 
     * @memberof MobPortletControlBase
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
                    iconcls: item?.getPSSysImage()?.cssClass,
                    icon: item.getPSSysImage?.()?.imagePath,
                    actiontarget: (item as IPSDETBUIActionItem).uIActionTarget,
                    caption: this.$tl((item.getCapPSLanguageRes() as IPSLanguageRes)?.lanResTag, item.caption),
                    disabled: false,
                    itemType: item.itemType,
                    visabled: true,
                    noprivdisplaymode: (item as IPSDETBUIActionItem).noPrivDisplayMode,
                    dataaccaction: "",
                    uiaction: {},
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
     * @memberof MobPortletControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal, oldVal);
    }

    /**
     * 初始化操作栏数据
     * 
     * @memberof MobPortletControlBase
     */
    public initActionBar() {
        let groupDetails = this.controlInstance.getPSUIActionGroup?.()?.getPSUIActionGroupDetails?.();
        if (groupDetails?.length) {
            this.actionBarModelData = [];
            groupDetails.forEach((item: IPSUIActionGroupDetail, index: number) => {
                let appUIAction = item.getPSUIAction() as IPSAppDEUIAction;
                this.actionBarModelData.push({
                    viewlogicname: item.name,
                    icon: appUIAction?.getPSSysImage()?.cssClass,
                    name: this.$tl((appUIAction?.getCapPSLanguageRes() as IPSLanguageRes)?.lanResTag ,appUIAction?.caption),
                    disabled: false,
                    visabled: true,
                    getNoPrivDisplayMode: appUIAction.noPrivDisplayMode ? appUIAction.noPrivDisplayMode : 6
                });
            })
        }
    }

    /**
     * 触发界面行为
     *
     * @param {*} $event
     * @memberof MobPortletControlBase
     */
    public handleItemClick(data: any, $event: any) {
        if(this.Environment?.isPreviewMode){
            return;
        }
        let tag = `${this.controlInstance.name?.toLowerCase()}_${data.viewlogicname}_click`;
        if (this.controlInstance?.portletType == 'TOOLBAR') {
            // 工具栏要用工具栏的name
            tag = `${this.controlInstance.getPSControls()?.[0]?.name?.toLowerCase()}_${data.tag}_click`;
        }
        AppViewLogicService.getInstance().executeViewLogic(tag, $event ? $event : data.event, this, data.params ? data.params : data, this.controlInstance.getPSAppViewLogics());
    }

    /**
     * 初始化门户部件
     *
     * @memberof MobPortletControlBase
     */
    public async ctrlModelInit(opts: any) {
        await super.ctrlModelInit();
        //初始化工具栏模型
        if (this.controlInstance.portletType == 'TOOLBAR') {
            this.initToolBar();
        }
        //初始化操作栏模型
        this.initActionBar();
    }

    /**
     * 初始化部件的绘制参数
     *
     * @param {*} [opts]
     * @memberof MobPortletControlBase
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
     * @memberof MobPortletControlBase
     */
    public ctrlInit(args?: any) {
        super.ctrlInit(args);
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (Object.is(tag, "all-portlet") && Object.is(action, 'loadmodel')) {
                    this.calcUIActionAuthState(data);
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
     * @memberof MobPortletControlBase
     */
    public refresh(args?: any) {
        const { portletType } = this.controlInstance;
        let controls = this.controlInstance?.getPSControls();
        let portletAppView = (this.controlInstance as IPSDBAppViewPortletPart)?.getPortletPSAppView();
        if (portletType == "VIEW") {
            this.viewState.next({ tag: portletAppView?.name, action: 'refresh', data: args });
        }
        if (portletType == "CHART" || portletType == "LIST") {
            this.viewState.next({ tag: controls?.[0].name, action: 'refresh', data: args });
        }
    }

    /**
     * 计算界面行为权限
     *
     * @param {*} [data={}]
     * @returns
     * @memberof MobPortletControlBase
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