import { IPSAppDEView, IPSDETabViewPanel } from "@ibiz/dynamic-model-api";
import { Util, ModelTool, TabViewPanelControlInterface } from "ibiz-core";
import { MainControlBase } from './main-control-base';


/**
 * 分页视图面板部件基类
 *
 * @export
 * @class TabViewPanelBase
 * @extends {MainControlBase}
 */
export class TabViewPanelBase extends MainControlBase implements TabViewPanelControlInterface{

    /**
     * 部件模型
     *
     * @memberof TabViewPanelBase
     */
    public controlInstance!: IPSDETabViewPanel;

    /**
     * 是否被激活
     *
     * @type {boolean}
     * @memberof TabViewPanelBase
     */
    public isActivied: boolean = false;

    /**
     * 是否被激活
     *
     * @type {boolean}
     * @memberof TabViewPanelBase
     */
    public activeData?: any;

    /**
     * 局部上下文
     * 
     * @type {*}
     * @memberof TabViewPanelBase
     */
    public localContext: any;

    /**
     * 局部视图参数
     *
     * @type {*}
     * @memberof TabViewPanelBase
     */
    public localViewParam: any;

    /**
     * 传入上下文
     *
     * @type {string}
     * @memberof TabViewPanelBase
     */
    public viewdata: string = "";

    /**
     * 传入视图参数
     *
     * @type {string}
     * @memberof TabViewPanelBase
     */
    public viewparam: string = "";

    /**
     * 视图面板过滤项
     *
     * @type {string}
     * @memberof TabViewPanelBase
     */
    public navFilter: string = "";

    /**
     * 部件模型数据初始化实例
     *
     * @memberof TabViewPanelBase
     */
    public async ctrlModelInit(args?: any) {
        const { navFilter, name } = this.controlInstance;
        await super.ctrlModelInit();
        this.name = name;
        this.navFilter = navFilter;
        this.isActivied = this.staticProps.isActivied;
        this.localContext = ModelTool.getNavigateContext(this.controlInstance);
        this.localViewParam = ModelTool.getNavigateParams(this.controlInstance);
        const embedView: IPSAppDEView = this.controlInstance.getEmbeddedPSAppDEView() as IPSAppDEView;
        if (this.isActivied && !embedView) {
            await this.activiedChange()
        }
    }

    /**
     * 初始化导航参数
     *
     *  @memberof TabViewPanelBase
     */
    public initNavParam() {
        if (!Util.isEmpty(this.navFilter)) {
            Object.assign(this.viewparams, { [this.navFilter]: this.context[this.appDeCodeName.toLowerCase()] })
        }
        if (this.localContext && Object.keys(this.localContext).length > 0) {
            let _context: any = Util.computedNavData({}, this.context, this.viewparams, this.localContext);
            Object.assign(this.context, _context);
        }
        if (this.localViewParam && Object.keys(this.localViewParam).length > 0) {
            let _param: any = Util.computedNavData({}, this.context, this.viewparams, this.localViewParam);
            Object.assign(this.viewparams, _param);
        }
        this.viewdata = JSON.stringify(this.context);
        this.viewparam = JSON.stringify(this.viewparams);
    }

    /**
     * 部件初始化
     * 
     * @memberof TabViewPanelBase
     */
    public ctrlInit() {
        super.ctrlInit();
        const _this: any = this
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag }: any) => {
                if (!Object.is(tag, _this.name)) {
                    return;
                }
                this.activiedChange();
            });
        }
    }
    
    /**
     * 视图数据变化
     *
     * @param {*} $event
     * @memberof TabViewPanelBase
     */
    public viewDatasChange($event: any) {
        this.$emit('ctrl-event', { controlname: 'tabviewpanel', action: 'viewpanelDatasChange', data: $event });
    }

    /**
     * 激活项变化
     *
     * @memberof TabViewPanelBase
     */
    public async activiedChange() {
        const embedView: IPSAppDEView = this.controlInstance.getEmbeddedPSAppDEView() as IPSAppDEView;
        if (embedView && !embedView.name) {
            await embedView.fill();
        }
        this.isActivied = false;
        this.$nextTick(() => {
            this.isActivied = true;
        });
        this.$nextTick(() => {
            this.initNavParam();
        });
    }
}
