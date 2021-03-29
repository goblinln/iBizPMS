import { ControlFactory } from '../../../utils';
import { IBizMainControlModel } from '../ibiz-main-control-model';

/**
 * 向导面板部件模型
 *
 * @export
 * @class IBizWizardPanelModel
 */
export class IBizWizardPanelModel extends IBizMainControlModel {

    /**
     * 向导表单Map
     * 
     * @memberof IBizWizardPanelModel
     */
    public $wizardFormMap:Map<string,any> = new Map();

    /**
     * 加载数据模型（获取向导表单）
     * 
     * @memberof IBizWizardPanelModel
     */
     public async loaded() {
        await super.loaded();
        if (this.controlModelData.getPSDEEditForms?.length > 0) {
            for (let control of this.controlModelData.getPSDEEditForms) {
                const runtimeData = {
                    context: this.context
                }
                // 实例化部件模型对象
                let ctrlInstance = ControlFactory.getInstance(control, this, null, runtimeData);
                if (ctrlInstance) {
                    await ctrlInstance.loaded();
                    this.$wizardFormMap.set(control.codeName,ctrlInstance);
                }
            }
        }
    }

    /**
     * 是否显示步骤栏
     * 
     * @readonly
     * @memberof IBizWizardPanelModel
     */
    get showStepBar() {
        return this.controlModelData.showStepBar;
    }

    /**
     * 是否显示行为栏
     * 
     * @readonly
     * @memberof IBizWizardPanelModel
     */
    get showActionBar() {
        return this.controlModelData.showActionBar;
    }

    /**
     * 实体向导对象
     * 
     * @readonly
     * @memberof IBizWizardPanelModel
     */
    get getPSDEWizard() {
        return this.controlModelData.getPSDEWizard;
    }

    /**
     * 获取向导步骤表单
     * 
     * @readonly
     * @memberof IBizWizardPanelModel
     */
    get getPSDEEditForms(){
        return [...this.$wizardFormMap.values()];
    }

    /**
     * 状态属性
     * 
     * @readonly
     * @memberof IBizWizardPanelModel
     */
    get getStatePSAppDEField() {
        return this.controlModelData.getStatePSAppDEField;
    }

    /**
     * 向导面板初始化行为
     * 
     * @readonly
     * @memberof IBizWizardPanelModel
     */
    get initAction() {
        return this.controlModelData.getInitPSControlAction;
    }

    /**
     * 向导面板完成行为
     * 
     * @readonly
     * @memberof IBizWizardPanelModel
     */
    get finishAction() {
        return this.controlModelData.getFinishPSControlAction;
    }

    /**
     * 是否状态向导面板
     * 
     * @readonly
     * @memberof IBizWizardPanelModel
     */
    get stateWizard() {
        return this.controlModelData.getPSDEWizard?.stateWizard;
    }

    /**
     * 部件样式
     *
     * @type {string}
     * @memberof IBizWizardPanelModel
     */
    get controlStyle() {
        return this.stateWizard ? "STATE" : "DEFAULT";
    }
}