import { Subject } from 'rxjs';
import { DataPanelEngine, EditView3Engine, EditViewEngine, ModelTool, ViewState } from 'ibiz-core';
import { EditViewBase } from './editview-base';
import { IPSAppDEEditView, IPSDRTab } from '@ibiz/dynamic-model-api';

/**
 * 编辑视图基类
 *
 * @export
 * @class EditView3Base
 * @extends {MainViewBase}
 */
export class EditView3Base extends EditViewBase {

    /**
     * 视图实例
     * 
     * @memberof ViewBase
     */
     public viewInstance!: IPSAppDEEditView;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof EditView3Base
     */
    public engine: EditView3Engine = new EditView3Engine();

    /**
     * 数据关系分页部件实例
     *
     * @public
     * @type {IBizFormModel}
     * @memberof EditView3Base
     */
     public drtabInstance !:IPSDRTab;

    /**
     * 选中数据
     *
     * @type {*}
     * @memberof EditView3Base
     */
     public selection: any = {};

    /**
     * 引擎初始化
     *
     * @public
     * @memberof EditView3Base
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        this.engine.init({
            view: this,
            parentContainer: this.$parent,
            form: (this.$refs[this.editFormInstance.name] as any).ctrl,
            drtab:(this.$refs[this.drtabInstance.name] as any).ctrl,
            p2k: '0',
            isLoadDefault: this.viewInstance.loadDefault,
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
        });
        if(this.dataPanelInstance){
            this.datapanel.init({
                view: this,
                parentContainer: this.$parent,
                datapanel: (this.$refs[this.dataPanelInstance?.name] as any).ctrl,
                p2k: '0',
                isLoadDefault: this.viewInstance.loadDefault,
                keyPSDEField: this.appDeCodeName.toLowerCase(),
                majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            });
        }
    }

    /**
     * 初始化编辑视图实例
     * 
     * @memberof EditView3Base
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.drtabInstance = ModelTool.findPSControlByName('drtab',this.viewInstance.getPSControls()) as IPSDRTab;
    }

    /**
     * 绘制表单
     *
     * @return {*} 
     * @memberof EditView3Base
     */
    public renderForm(){
        if (!this.editFormInstance) {
            return null;
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.editFormInstance);
        return this.$createElement(targetCtrlName, {slot:'mainform', props: targetCtrlParam, ref: this.editFormInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof EditView3Base
     */
    public renderMainContent() {
        if (!this.drtabInstance) {
            return null;
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.drtabInstance);
        return this.$createElement(targetCtrlName, { 
            slot: 'default', 
            props: targetCtrlParam, 
            ref: this.drtabInstance?.name, 
            on: targetCtrlEvent, 
        },[
            this.renderForm(),
        ]);
    }

}
