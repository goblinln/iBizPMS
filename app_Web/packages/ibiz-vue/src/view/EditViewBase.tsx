import { DataPanelEngine, EditViewEngine, IBizEditViewModel, IBizFormModel } from 'ibiz-core';
import { MainViewBase } from './MainViewBase';

/**
 * 编辑视图基类
 *
 * @export
 * @class EditViewBase
 * @extends {MainViewBase}
 */
export class EditViewBase extends MainViewBase {

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof EditViewBase
     */
    public engine: EditViewEngine = new EditViewEngine();

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof AccountInfoBase
     */
     public datapanel: DataPanelEngine = new DataPanelEngine();

    /**
     * 编辑表单实例
     *
     * @public
     * @type {IBizFormModel}
     * @memberof EditViewBase
     */
    public editFormInstance !:IBizFormModel;

    /**
     * 标题头信息表单部件实例
     *
     * @public
     * @type {IBizFormModel}
     * @memberof EditViewBase
     */
    public dataPanelInstance !:IBizFormModel;

    /**
     * 引擎初始化
     *
     * @public
     * @memberof EditViewBase
     */
    public engineInit(): void {
        this.engine.init({
            view: this,
            parentContainer: this.$parent,
            form: (this.$refs[this.viewInstance.viewForm.name] as any).ctrl,
            p2k: '0',
            isLoadDefault: this.viewInstance.loadDefault,
            keyPSDEField: (this.viewInstance.appDataEntity.codeName).toLowerCase(),
            majorPSDEField: (this.viewInstance.appDataEntity.majorField.codeName).toLowerCase(),
        });
        if(this.dataPanelInstance){
            this.datapanel.init({
                view: this,
                p2k: '0',
                parentContainer: this.$parent,
                datapanel: (this.$refs[this.viewInstance.dataPanel.name] as any).ctrl,
                keyPSDEField: (this.viewInstance.appDataEntity.codeName).toLowerCase(),
                majorPSDEField: (this.viewInstance.appDataEntity.codeName).toLowerCase(),
                isLoadDefault: this.viewInstance.loadDefault,
            });
        }
    }

    /**
     * 初始化编辑视图实例
     * 
     * @memberof EditViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizEditViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.editFormInstance = this.viewInstance.getControl('form');
        this.dataPanelInstance = this.viewInstance.getControl('datapanel');
    }

    /**
     * 渲染视图标题头信息表单部件
     * 
     * @memberof AppDefaultEditView
     */
     public renderDataPanelInfo() {
        if (!this.dataPanelInstance) {
            return null;
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.dataPanelInstance);
        return this.$createElement(targetCtrlName, { slot: 'datapanel', props: targetCtrlParam, ref: this.viewInstance.dataPanel.name, on: targetCtrlEvent });
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof AppDefaultEditView
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.editFormInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.viewInstance.viewForm.name, on: targetCtrlEvent });
    }

}
