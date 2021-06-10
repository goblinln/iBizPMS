import { IPSAppDEWizardView, IPSDEWizardPanel } from '@ibiz/dynamic-model-api';
import { ModelTool, WizardViewEngine, WizardViewInterface } from 'ibiz-core';
import { MDViewBase } from './mdview-base';

/**
 * 实体向导视图基类
 *
 * @export
 * @class WizardViewBase
 * @extends {MDViewBase}
 * @implements {WizardViewInterface}
 */
export class WizardViewBase extends MDViewBase implements WizardViewInterface {

    /**
     * 向导视图模型实例
     * 
     * @memberof WizardViewBase
     */
    public viewInstance!: IPSAppDEWizardView;

    /**
     * 向导面板部件模型实例
     * 
     * @memberof WizardViewBase
     */
    public wizardPanelInstance!: IPSDEWizardPanel;

        /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof TreeViewBase
     */
    public engine: WizardViewEngine = new WizardViewEngine();
    
    /**
     * 引擎初始化
     *
     * @param {*} [opts={}] 引擎参数
     * @memberof TreeViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        if(this.engine && this.wizardPanelInstance){
            let engineOpts = Object.assign({
                view: this,
                parentContainer: this.$parent,
                p2k: '0',
                isLoadDefault: true,
                //TODO IPSAppDEWizardView缺少loaddefault
                // isLoadDefault: this.viewInstance.loadDefault,
                keyPSDEField: this.appDeCodeName.toLowerCase(),
                majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
                opendata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                    this.opendata(args, fullargs, params, $event, xData);
                },
                newdata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                    this.newdata(args, fullargs, params, $event, xData);
                },
                wizardpanel: (this.$refs[this.wizardPanelInstance.name] as any).ctrl,
            }, opts)
            this.engine.init(engineOpts);
        }
    }


     /**
     * 初始化列表视图实例
     *
     * @memberof WizardViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDEWizardView;
        await super.viewModelInit();
        this.wizardPanelInstance = ModelTool.findPSControlByType('WIZARDPANEL', this.viewInstance.getPSControls() || []);
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof WizardViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.wizardPanelInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.wizardPanelInstance?.name, on: targetCtrlEvent });
    }
}