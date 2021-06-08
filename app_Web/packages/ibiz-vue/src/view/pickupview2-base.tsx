import { IPSAppDEPickupView, IPSDEPickupViewPanel, IPSTreeExpBar } from '@ibiz/dynamic-model-api';
import { ModelTool, PickupView2Engine } from 'ibiz-core';
import { MainViewBase } from './mainview-base';

export class PickupView2Base extends MainViewBase {

    /**
     * 视图实例
     * 
     * @memberof PickupView2Base
     */
    public viewInstance!: IPSAppDEPickupView;

    /**
     * 选择视图面板实例对象
     * 
     * @memberof PickupView2Base
     */
    public pickupViewInstance!: IPSDEPickupViewPanel;

    /**
     * 树导航实例对象
     * 
     * @memberof PickupView2Base
     */
    public treeExpBarInstance!: IPSTreeExpBar;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof PickupView2Base
     */
    public engine: PickupView2Engine = new PickupView2Engine();

    /**
     * 选中数据的字符串
     *
     * @type {string}
     * @memberof PickupView2Base
     */
    public selectedData: string = "";

    /**
     * 视图选中数据
     *
     * @type {any[]}
     * @memberof PickupView2Base
     */
    public viewSelections: any[] = [];

    /**
     * 是否显示按钮
     *
     * @type {boolean}
     * @memberof PickupView2Base
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
     * @memberof PickupView2Base
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isShowButton = newVal?.isShowButton !== false;
        super.onStaticPropsChange(newVal,oldVal);
    }

    /**
     * 引擎初始化
     *
     * @public
     * @memberof PickupView2Base
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        this.engine.init({
            view: this,
            parentContainer: this.$parent,
            treeExpBar: (this.$refs[this.treeExpBarInstance?.name] as any).ctrl,
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
        });
    }

    /**
     * 确定
     *
     * @memberof PickupView2Base
     */
    public onClickOk(): void {
        this.$emit('view-event', { viewName: this.viewInstance.name, action: 'viewdataschange', data: this.viewSelections });
        this.$emit('view-event', { viewName: this.viewInstance.name, action: 'close', data: null });
    }

    /**
     * 取消
     *
     * @memberof PickupView2Base
     */
    public onClickCancel(): void {
        this.$emit('view-event', { viewName: this.viewInstance.name, action: 'viewdataschange', data: null });
        this.$emit('view-event', { viewName: this.viewInstance.name, action: 'close', data: null });
    }

    /**
     *  视图挂载
     *
     * @memberof PickupView2Base
     */
    public async viewMounted() {
        super.viewMounted();
        if (this.viewparams?.selectedData) {
            this.engine.onCtrlEvent('pickupviewpanel', 'selectionchange', this.viewparams.selectedData);
        }
    }

    public setIsMounted(name: string = 'self') {
        super.setIsMounted(name);
        console.log(this.mountedMap);
    }

    /**
     * 初始化数据选择视图实例
     * 
     * @memberof PickupView2Base
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDEPickupView;
        await super.viewModelInit();
        this.pickupViewInstance = ModelTool.findPSControlByType("PICKUPVIEWPANEL",this.viewInstance.getPSControls());
        this.treeExpBarInstance = ModelTool.findPSControlByType("TREEEXPBAR",this.viewInstance.getPSControls());
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof PickupView2Base
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
}