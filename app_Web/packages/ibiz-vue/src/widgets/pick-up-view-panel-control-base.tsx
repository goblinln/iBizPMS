import { IPSDEPickupViewPanel } from "@ibiz/dynamic-model-api";
import { Util } from "ibiz-core";
import { MainControlBase } from './main-control-base';
/**
 * 选择视图面板部件基类
 *
 * @export
 * @class PickUpViewPanelControlBase
 * @extends {MainControlBase}
 */
export class PickUpViewPanelControlBase extends MainControlBase {

    /**
     * 部件模型实例对象
     *
     * @type {*}
     * @memberof ControlBase
     */
    public controlInstance!: IPSDEPickupViewPanel;

    /**
     * 选中数据字符串
     *
     * @type {string}
     * @memberof PickUpViewPanelControlBase
     */
    public selectedData?: string;

    /**
     * 视图模式
     * 0: 默认模式（选择视图，多项数据选择视图）
     * 1：左右关系（选择视图[左右关系], 多项数据选择视图[左右关系]）
     * 2: 分页关系（选择视图[分页关系], 多项数据选择视图[分页关系]）
     *
     * @type {string}
     * @memberof PickUpViewPanelControlBase
     */
    public viewMode: number = 0;

    /**
     * 获取多项数据
     *
     * @returns {any[]}
     * @memberof PickUpViewPanelControlBase
     */
    public getDatas(): any[] {
        return [];
    }

    /**
     * 获取单项树
     *
     * @returns {*}
     * @memberof PickUpViewPanelControlBase
     */
    public getData(): any {
        return {};
    }

    /**
     * 视图名称
     *
     * @type {*}
     * @memberof PickUpViewPanelControlBase
     */
    public view: any = {
        viewName: '',
        data: {},
    }

    /**
     * 局部上下文
     *
     * @type {*}
     * @memberof PickUpViewPanelControlBase
     */
    public localContext: any = "";

    /**
     * 局部视图参数
     *
     * @type {*}
     * @memberof PickupViewpickupviewpanel
     */
    public localViewParam: any = "";

    /**
     * 视图数据
     *
     * @type {*}
     * @memberof PickUpViewPanelControlBase
     */
    public viewdata: string = JSON.stringify(this.context);

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof PickupViewpickupviewpanel
     */
    public viewparam: string = JSON.stringify(this.viewparams);

    /**
     * 是否显示按钮
     *
     * @type {boolean}
     * @memberof PickUpViewPanelControlBase
     */
    public isShowButton!: boolean;

    /**
     * 是否单选
     *
     * @type {boolean}
     * @memberof PickUpViewPanelControlBase
     */
    public isSingleSelect?: boolean;

    /**
     * 初始化完成
     *
     * @type {boolean}
     * @memberof PickUpViewPanelControlBase
     */
    public inited: boolean = false;


    /**
     * 视图数据变化
     *
     * @param {*} $event
     * @memberof PickUpViewPanelControlBase
     */
    public onViewDatasChange($event: any): void {
        if ($event.length > 0) {
            $event.forEach((item: any, index: any) => {
                let srfmajortext = item.srfmajortext ? item.srfmajortext : item[this.appDeMajorFieldName.toLowerCase()];
                if (srfmajortext) {
                    Object.assign($event[index], { srfmajortext: srfmajortext });
                }
            });
        }
        this.$emit("ctrl-event", { controlname: "pickupviewpanel", action: "selectionchange", data: $event });
    }

    /**
     * 部件初始化
     *
     *  @memberof PickUpViewPanelControlBase
     */
    public ctrlInit() {
        this.initNavParam();
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if (Object.is('load', action)) {
                    this.viewdata = JSON.stringify(this.context);
                    this.viewparam = JSON.stringify(Object.assign(data, this.viewparams));
                    this.handleLoad();
                    this.inited = true;
                }
            });
        }
    }

    public handleLoad() {
        if (!this.inited || this.viewMode == 0) {
            return;
        }
        const viewname = this.controlInstance.getEmbeddedPSAppDEView()?.name;
        if (viewname && this.viewMode == 1) {
            const viewDom: any = (this.$refs[viewname] as any)?.$children[0];
            if (viewDom && viewDom.engine) {
                viewDom.engine.load();
            }
        }
    }

    /**
     * 初始化导航参数
     *
     *  @memberof PickUpViewPanelControlBase
     */
    public initNavParam() {
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
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof PickUpViewPanelControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal, oldVal);
        if (newVal?.selectedData && newVal.selectedData != oldVal?.selectedData) {
            this.selectedData = newVal.selectedData;
        }
    }

    /**
     * 监听部件参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof PickUpViewPanelControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isShowButton = newVal.isShowButton;
        this.isSingleSelect = newVal.isSingleSelect;
        this.viewMode = newVal.viewMode || 0;
        super.onStaticPropsChange(newVal, oldVal)
    }

    /**
      * 初始化面板部件实例
      * 
      * @memberof PickUpViewPanelControlBase
      */
    public async ctrlModelInit() {
        super.ctrlModelInit();
        this.view.viewName = 'app-view-shell';
    }
}