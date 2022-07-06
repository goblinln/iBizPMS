import { MobEditView3Engine, MobEditView3Interface, ModelTool } from 'ibiz-core';
import { MobEditViewBase } from './mob-edit-view-base';
import { IPSAppDEMobEditView, IPSDRTab } from '@ibiz/dynamic-model-api';

/**
 * 编辑视图基类
 *
 * @export
 * @class MobEditView3Base
 * @extends {MainViewBase}
 */
export class MobEditView3Base extends MobEditViewBase implements MobEditView3Interface {

    /**
     * 视图实例
     * 
     * @memberof MobEditView3Base
     */
     public viewInstance!: IPSAppDEMobEditView;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof MobEditView3Base
     */
    public engine: MobEditView3Engine = new MobEditView3Engine();

    /**
     * 数据关系分页部件实例
     *
     * @public
     * @type {IBizFormModel}
     * @memberof MobEditView3Base
     */
     public drtabInstance !:IPSDRTab;

    /**
     * 选中数据
     *
     * @type {*}
     * @memberof MobEditView3Base
     */
     public selection: any = {};

    /**
     * 引擎初始化
     *
     * @public
     * @memberof MobEditView3Base
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
    }

    /**
     * 初始化编辑视图实例
     * 
     * @memberof MobEditView3Base
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.drtabInstance = ModelTool.findPSControlByName('drtab',this.viewInstance.getPSControls()) as IPSDRTab;
    }

    /**
     * 绘制表单
     *
     * @return {*} 
     * @memberof MobEditView3Base
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
     * @memberof MobEditView3Base
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
