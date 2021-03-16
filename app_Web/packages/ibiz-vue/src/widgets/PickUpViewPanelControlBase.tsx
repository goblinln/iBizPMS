import { Util } from "ibiz-core";
import { MainControlBase } from './MainControlBase';
/**
 * 选择视图面板部件基类
 *
 * @export
 * @class PickUpViewPanelControlBase
 * @extends {MainControlBase}
 */
export class PickUpViewPanelControlBase extends MainControlBase {

    /**
     * 选中数据字符串
     *
     * @type {string}
     * @memberof PickUpViewPanelControlBase
     */
    public selectedData?: string;

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
                let srfmajortext = item.srfmajortext ? item.srfmajortext : item[this.controlInstance.appDataEntity.majorField.codeName.toLowerCase()];
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
                    this.inited = true;
                }
            });
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
        this.isShowButton = this.staticProps.isShowButton;
        this.isSingleSelect = this.staticProps.isSingleSelect;
        super.onStaticPropsChange(newVal, oldVal)
    }

    /**
      * 初始化面板部件实例
      * 
      * @memberof PickUpViewPanelControlBase
      */
    public async ctrlModelInit() {
        super.ctrlModelInit();
        if (this.controlInstance.getEmbeddedPSAppDEView && this.controlInstance.getEmbeddedPSAppDEView.dynaModelFilePath) {
            Object.assign(this.context, { viewpath: this.controlInstance.getEmbeddedPSAppDEView.dynaModelFilePath });
        }
        this.view.viewName = 'app-view-shell';
    }
}