import { IPSAppDEPickupView, IPSDEPickupViewPanel } from '@ibiz/dynamic-model-api';
import { PickupView3Engine, PickupView3Interface, Util } from 'ibiz-core';
import { MainViewBase } from './mainview-base';

/**
 * 应用实体数据选择视图（分页关系）
 *
 * @export
 * @class PickupView3Base
 * @extends {MainViewBase}
 * @implements {PickupView3Interface}
 */
export class PickupView3Base extends MainViewBase implements PickupView3Interface {

    /**
     * 视图实例对象
     * 
     * @type {IPSAppDEPickupView}
     * @memberof PickupView3Base
     */
    public viewInstance!: IPSAppDEPickupView;

    /**
     * 视图引擎对象
     * 
     * @type {PickupView3Engine}
     * @memberof PickupView3Base
     */
    public engine: PickupView3Engine = new PickupView3Engine();

    /**
     * 选择视图面板集合
     * 
     * @type {Array<IPSDEPickupViewPanel>}
     * @memberof PickupView3Base
     */
    public pickupViewPanelModels: Array<IPSDEPickupViewPanel> = [];

    /**
     * 当前激活选择视图面板名称
     * 
     * @type {string}
     * @memberof PickupView3Base
     */
    public activedPickupViewPanel: string = '';

    /**
     * 选中数据的字符串
     *
     * @type {string}
     * @memberof PickupView3Base
     */
    public selectedData: string = "";

    /**
     * 视图选中数据
     *
     * @type {any[]}
     * @memberof PickupView3Base
     */
    public viewSelections: any[] = [];

    /**
     * 是否显示按钮
     *
     * @type {boolean}
     * @memberof PickupView3Base
     */
    public isShowButton: boolean = true;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof PanelControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal,oldVal);
        if(this.viewparams?.selectedData){
            this.selectedData = JSON.stringify(this.viewparams.selectedData);
        }
    }

    /**
     * 监听部件静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof PickupView3Base
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isShowButton = newVal?.isShowButton !== false;
        super.onStaticPropsChange(newVal,oldVal);
    }

    /**
     * 视图模型初始化
     * 
     * @memberof PickupView3Base
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDEPickupView;
        await super.viewModelInit();
        this.initPanelModels();
    }

    /**
     * 视图初始化
     * 
     * @memberof PickupView3Base
     */
    public viewInit() {
        this.activedPickupViewPanel = this.pickupViewPanelModels.length > 0 ? this.pickupViewPanelModels[0].name : '';
    }

    /**
     * 视图引擎初始化
     * 
     * @memberof PickupView3Base
     */
    public engineInit() {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        this.engine.init({
            view: this,
            parentContainer: this.$parent,
            pickupViewPanelModels: this.pickupViewPanelModels,
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
        });
    }

    /**
     * 初始化选择视图面板实例
     * 
     * @memberof PickupView3Base
     */
    public initPanelModels() {
        const controls: any[] = this.viewInstance.getPSControls() || [];
        controls.forEach((control: any) => {
            if (control.controlType == 'PICKUPVIEWPANEL') {
                this.pickupViewPanelModels.push(control as IPSDEPickupViewPanel);
            }
        })
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof PickupView3Base
     */
    public computeTargetCtrlData(controlInstance:any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.dynamicProps,{
            selectedData: this.selectedData,
        })
        Object.assign(targetCtrlParam.staticProps,{
            isSingleSelect: true,
            isShowButton: this.isShowButton,
        })
        return { targetCtrlName, targetCtrlParam, targetCtrlEvent }
    }

    /**
     * 分页点击
     * 
     * @memberof PickupView3Base
     */
    public tabPanelClick(event: any) {
        if (!event || Object.is(event, this.activedPickupViewPanel)) {
            return;
        }
        this.activedPickupViewPanel = event;
        this.viewState.next({ tag: this.activedPickupViewPanel, action: 'load', data: Util.deepCopy(this.viewparams) });
    }

    /**
     * 确定
     *
     * @memberof PickupView3Base
     */
    public onClickOk(): void {
        this.$emit('view-event', { viewName: this.viewInstance.name, action: 'viewdataschange', data: this.viewSelections });
        this.$emit('view-event', { viewName: this.viewInstance.name, action: 'close', data: null });
    }

    /**
     * 取消
     *
     * @memberof PickupView3Base
     */
    public onClickCancel(): void {
        this.$emit('view-event', { viewName: this.viewInstance.name, action: 'viewdataschange', data: null });
        this.$emit('view-event', { viewName: this.viewInstance.name, action: 'close', data: null });
    }

}