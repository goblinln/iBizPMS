import { IPSAppDEPickupView, IPSDEPickupViewPanel } from '@ibiz/dynamic-model-api';
import { PickupViewEngine, ModelTool, debounce, PickupViewInterface } from 'ibiz-core';
import { MainViewBase } from './mainview-base';

/**
 * 数据选择视图基类
 *
 * @export
 * @class PickupViewBase
 * @extends {MainViewBase}
 * @implements {PickupViewInterface}
 */
export class PickupViewBase extends MainViewBase implements PickupViewInterface {

    /**
     * 视图实例
     * 
     * @memberof GridViewBase
     */
   public viewInstance!: IPSAppDEPickupView;

    /**
     * 选择视图面板实例
     * 
     * @memberof PickupViewBase
     */
    public pickUpViewPanelInstance!: IPSDEPickupViewPanel;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof PickupViewBase
     */
    public engine: PickupViewEngine = new PickupViewEngine();

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
     * 引擎初始化
     *
     * @public
     * @memberof PickupViewBase
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        this.engine.init({
            view: this,
            parentContainer: this.$parent,
            pickupViewPanel: (this.$refs[this.pickUpViewPanelInstance?.name] as any).ctrl,
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
        });
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

    /**
     *  视图挂载
     *
     * @memberof PickupViewBase
     */
    public async viewMounted() {
        super.viewMounted();
        if (this.viewparams?.selectedData) {
            this.engine.onCtrlEvent('pickupviewpanel', 'selectionchange', this.viewparams.selectedData);
        }
    }

    /**
     * 初始化数据选择视图实例
     * 
     * @memberof PickupViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDEPickupView;
        await super.viewModelInit();
        this.pickUpViewPanelInstance = ModelTool.findPSControlByType("PICKUPVIEWPANEL",this.viewInstance.getPSControls());
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

    /**
     * 渲染选择视图面板
     * 
     * @memberof PickupViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.pickUpViewPanelInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.pickUpViewPanelInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 渲染选择视图按钮
     * 
     * @memberof PickupViewBase
     */
    public renderPickButton() {
        if(this.isShowButton){
            return (
                <card dis-hover={true} bordered={false} class="footer">
                    <row style={{ "textAlign": 'right' }}>
                        <i-button type="primary" disabled={this.viewSelections.length > 0 ? false : true} on-click={(...params: any[]) => debounce(this.onClickOk,params,this)}>{this.containerModel?.view_okbtn?.text}</i-button>
                            &nbsp;&nbsp;
                        <i-button on-click={(...params: any[]) => debounce(this.onClickCancel,params,this)}>{this.containerModel?.view_cancelbtn?.text}</i-button>
                    </row>
                </card>
            )
        }
    }
}
