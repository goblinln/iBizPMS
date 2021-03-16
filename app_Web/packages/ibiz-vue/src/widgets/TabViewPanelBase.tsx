import { Util, ModelParser, DynamicService } from "ibiz-core";
import { IBizTabViewPanelModel } from "ibiz-core/src/model/control/ibiz-tab-view-panel-model/ibiz-tab-view-panel-model";
import { MainControlBase } from './MainControlBase';


/**
 * 分页视图面板部件基类
 *
 * @export
 * @class FormControlBase
 * @extends {MainControlBase}
 */
export class TabViewPanelBase extends MainControlBase {

    /**
     * 部件模型
     *
     * @type {AppTabViewPanelBase}
     * @memberof TabViewPanelBase
     */
    public controlInstance!: IBizTabViewPanelModel;


    /**
     * 部件模型数据初始化实例
     *
     * @memberof TabViewPanelBase
     */
    public async ctrlModelInit(args?: any) {
        const { navFilter, isActivied, name } = this.controlInstance;
        await super.ctrlModelInit();
        this.name = name;
        this.navFilter = navFilter;
        this.isActivied = isActivied;
        this.localContext = ModelParser.getNavigateContext(this.controlInstance);
        this.localViewParam = ModelParser.getNavigateParams(this.controlInstance);
        if (this.isActivied && !this.controlInstance.embedView) {
            await this.activiedChange()
        }
    }



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
     * 初始化导航参数
     *
     *  @memberof TabViewPanelBase
     */
    public initNavParam() {
        if (!Util.isEmpty(this.navFilter)) {
            Object.assign(this.viewparams, { [this.navFilter]: this.context[this.controlInstance.appDeCodeName.toLowerCase()] })
        }
        if (this.localContext && Object.keys(this.localContext).length > 0) {
            let _context: any = this.$util.computedNavData({}, this.context, this.viewparams, this.localContext);
            Object.assign(this.context, _context);
        }
        if (this.localViewParam && Object.keys(this.localViewParam).length > 0) {
            let _param: any = this.$util.computedNavData({}, this.context, this.viewparams, this.localViewParam);
            Object.assign(this.viewparams, _param);
        }
        this.viewdata = JSON.stringify(this.context);
        this.viewparam = JSON.stringify(this.viewparams);
    }

    /**
     * 视图数据变化
     *
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
        if (!this.controlInstance.viewName) {
            await this.controlInstance.loaded();
        }
        this.isActivied = false;
        this.$nextTick(() => {
            this.isActivied = true;
        });
        this.$nextTick(() => {
            this.initNavParam();
        });
    }

    /**
      * 部件初始化
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
}
