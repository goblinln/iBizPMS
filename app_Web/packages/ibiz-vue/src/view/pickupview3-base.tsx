import { IPSAppDEPickupView, IPSDEPickupViewPanel } from '@ibiz/dynamic-model-api';
import { debounce, ModelTool, PickupView3Engine, Util } from 'ibiz-core';
import { MainViewBase } from './mainview-base';

export class PickupView3Base extends MainViewBase {

    public viewInstance!: IPSAppDEPickupView;

    public engine: PickupView3Engine = new PickupView3Engine();

    public pickupViewPanelModels: Array<IPSDEPickupViewPanel> = [];

    public activedPickupViewPanel: string = '';

    /**
     * 选中数据的字符串
     *
     * @type {string}
     * @memberof PickupViewBase
     */
    public selectedData: string = "";

    /**
     * 视图选中数据
     *
     * @type {any[]}
     * @memberof PickupViewBase
     */
    public viewSelections: any[] = [];

    /**
     * 是否显示按钮
     *
     * @type {boolean}
     * @memberof PickupViewBase
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
     * @memberof PickupViewBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isShowButton = newVal?.isShowButton !== false;
        super.onStaticPropsChange(newVal,oldVal);
    }

    /**
     * 初始化数据选择视图实例
     * 
     * @memberof PickupView3Base
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDEPickupView;
        await super.viewModelInit();
        this.initPanelModels();
    }

    public viewInit() {
        this.activedPickupViewPanel = this.pickupViewPanelModels.length > 0 ? this.pickupViewPanelModels[1].name : '';
    }

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
     * @memberof PickupViewBase
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
     * @memberof PickupViewBase
     */
    public onClickOk(): void {
        this.$emit('view-event', { viewName: this.viewInstance.name, action: 'viewdataschange', data: this.viewSelections });
        this.$emit('view-event', { viewName: this.viewInstance.name, action: 'close', data: null });
    }

    /**
     * 取消
     *
     * @memberof PickupViewBase
     */
    public onClickCancel(): void {
        this.$emit('view-event', { viewName: this.viewInstance.name, action: 'viewdataschange', data: null });
        this.$emit('view-event', { viewName: this.viewInstance.name, action: 'close', data: null });
    }

}