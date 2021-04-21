import { Subject } from 'rxjs';
import { DataPanelEngine, EditViewEngine, ModelTool, ViewState } from 'ibiz-core';
import { MainViewBase } from './mainview-base';
import { IPSAppDataEntity, IPSAppDEEditView, IPSAppDEField, IPSDEForm } from '@ibiz/dynamic-model-api';

/**
 * 编辑视图基类
 *
 * @export
 * @class EditViewBase
 * @extends {MainViewBase}
 */
export class EditViewBase extends MainViewBase {

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
    public editFormInstance !:IPSDEForm;

    /**
     * 标题头信息表单部件实例
     *
     * @public
     * @type {IBizFormModel}
     * @memberof EditViewBase
     */
    public dataPanelInstance !:IPSDEForm;

    /**
     * 多编辑面板状态
     *
     * @public
     * @type {Subject<ViewState>}
     * @memberof EditViewBase
     */
    public panelState?: Subject<ViewState>;

    /**
     * 监听视图静态参数变化
     * 
     * @memberof EditViewBase 
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal.panelState) {
            this.panelState = newVal.panelState;
        }
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 视图初始化
     * 
     * @memberof EditViewBase 
     */
    public viewInit() {
        super.viewInit();
        if (this.panelState) {
            this.panelState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(tag, this.viewInstance.name)) {
                    return;
                }
                if (action == 'save') {
                    this.viewState.next({ tag: this.editFormInstance.name, action: 'save', data: data });
                }
                if (action == "remove") {
                    this.viewState.next({ tag: this.editFormInstance.name, action: 'remove', data: data });
                }
            })
        }
    }

    /**
     * 引擎初始化
     *
     * @public
     * @memberof EditViewBase
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
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
     * @memberof EditViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.editFormInstance = ModelTool.findPSControlByName('form',this.viewInstance.getPSControls()) as IPSDEForm;
        this.dataPanelInstance = ModelTool.findPSControlByName('datapanel',this.viewInstance.getPSControls()) as IPSDEForm;
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
        return this.$createElement(targetCtrlName, { slot: 'datapanel', props: targetCtrlParam, ref: this.dataPanelInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof AppDefaultEditView
     */
    public renderMainContent() {
        if (!this.editFormInstance) {
            return null;
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.editFormInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.editFormInstance?.name, on: targetCtrlEvent });
    }

}
