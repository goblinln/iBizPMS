import { IPSAppDEMobEditView, IPSDEForm } from "@ibiz/dynamic-model-api";
import {  MobEditViewEngine, ModelTool } from "ibiz-core";
import { MainViewBase } from "./main-view-base";

/**
 * 编辑视图基类
 *
 * @export
 * @class MobEditViewBase
 * @extends {MainViewBase}
 */
export class MobEditViewBase extends MainViewBase {

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof MobEditViewBase
     */
    public engine: MobEditViewEngine = new MobEditViewEngine();

    /**
     * 视图实例
     * 
     * @memberof MobEditViewBase
     */
    public viewInstance !: IPSAppDEMobEditView;

    /**
     * 编辑表单实例
     *
     * @public
     * @type {IBizMobFormModel}
     * @memberof MobEditViewBase
     */
    public editFormInstance !: IPSDEForm;

    /**
     * 引擎初始化
     *
     * @public
     * @memberof MobEditViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
            return;
        }
        this.engine.init({
            view: this,
            parentContainer: this.$parent,
            form: (this.$refs[this.editFormInstance.name] as any).ctrl,
            p2k: '0',
            isLoadDefault: this.viewInstance.loadDefault,
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
        });
    }

    /**
     * 初始化编辑视图实例
     * 
     * @memberof MobEditViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.editFormInstance = ModelTool.findPSControlByName('form',this.viewInstance.getPSControls()) as IPSDEForm;
        this.initViewToolBar()
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof MobEditViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.editFormInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.editFormInstance.name, on: targetCtrlEvent });
    }
}
