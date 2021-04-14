import { IBizWizardPanelModel, IBizWizardViewModel, WizardViewEngine } from 'ibiz-core';
import { MDViewBase } from './mdview-base';

/**
 * 实体向导视图基类
 *
 * @export
 * @class WizardViewBase
 * @extends {MDViewBase}
 */
export class WizardViewBase extends MDViewBase {

    /**
     * 向导视图模型实例
     * 
     * @memberof WizardViewBase
     */
    public viewInstance!: IBizWizardViewModel;

    /**
     * 向导面板部件模型实例
     * 
     * @memberof WizardViewBase
     */
    public wizardPanelInstance!: IBizWizardPanelModel;

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
                isLoadDefault: this.viewInstance.loadDefault,
                keyPSDEField: (this.viewInstance.appDataEntity?.codeName)?.toLowerCase(),
                majorPSDEField: (this.viewInstance.appDataEntity.majorField?.codeName)?.toLowerCase(),
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
        this.viewInstance = new IBizWizardViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit()
        this.wizardPanelInstance = this.viewInstance.getControl('wizardpanel');
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof WizardViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.wizardPanelInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.wizardPanelInstance.name, on: targetCtrlEvent });
    }
}