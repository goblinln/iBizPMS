import { IPSDEPickupViewPanel } from "@ibiz/dynamic-model-api";
import { MainControlBase } from "./main-control-base";

/**
 * 选择视图面板基类
 *
 * @export
 * @class MobPickUpViewPanelControlBase
 * @extends {MainControlBase}
 */
export class MobPickUpViewPanelControlBase extends MainControlBase {

    /**
     * 部件模型实例对象
     *
     * @type {IBizMobPickUpViewPanelModel}
     * @memberof MobPickUpViewPanelControlBase
     */
    public controlInstance!: IPSDEPickupViewPanel;

    /**
      * 是否单选
      *
      * @type {boolean}
      * @memberof MobPickUpViewPanelControlBase
      */
    protected isSingleSelect?: boolean;

    /**
     * 视图数据变化
     *
     * @param {*} $event
     * @memberof MobPickUpViewPanelControlBase
     */
    protected onViewDatasChange($event: any): void {
        if ($event.length > 0) {
            $event.forEach((item: any, index: any) => {
                let srfmajortext = item[this.appDeMajorFieldName.toLowerCase()];
                if (srfmajortext) {
                    Object.assign($event[index], { srfmajortext: srfmajortext });
                }
            });
        }
        this.ctrlEvent({ controlname: this.controlInstance.name, action: 'selectionchange', data: $event });
    }

    /**
     * 部件初始化
     *
     * @param {*} [args]
     * @memberof MobPickUpViewPanelControlBase
     */
    public ctrlInit(args?: any) {
        super.ctrlInit(args);
    }

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        super.onStaticPropsChange(newVal,oldVal);
        this.isSingleSelect = newVal.isSingleSelect;
    }
}