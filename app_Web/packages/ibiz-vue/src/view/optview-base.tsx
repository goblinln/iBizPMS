import { MainViewBase } from "./mainview-base";
import { OptionViewEngine, ModelTool } from 'ibiz-core';
import { IPSAppDEEditView, IPSDEForm } from "@ibiz/dynamic-model-api";
/**
 * 选项操作视图基类
 *
 * @export
 * @class OptViewBase
 * @extends {MainViewBase}
 */
export class OptViewBase extends MainViewBase {

    /**
     * 视图实例
     * 
     * @memberof OptViewBase
     */
    public viewInstance!: IPSAppDEEditView;

    /**
     * 编辑表单实例
     *
     * @public
     * @type {IBizFormModel}
     * @memberof OptViewBase
     */
    public editFormInstance !: IPSDEForm;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof OptViewBase
     */
    public engine: OptionViewEngine = new OptionViewEngine();

    /**
     * 引擎初始化
     *
     * @public
     * @memberof OptViewBase
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        this.engine.init({
            view: this,
            parentContainer: this.$parent,
            form: (this.$refs[this.editFormInstance?.name] as any).ctrl,
            p2k: '0',
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            isLoadDefault: this.viewInstance.loadDefault,
        });
    }

    /**
     * 初始化编辑视图实例
     * 
     * @memberof OptViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.editFormInstance = ModelTool.findPSControlByName('form',this.viewInstance.getPSControls()) as IPSDEForm;      
    }


    /**
     * 渲染视图主体内容区
     * 
     * @memberof OptViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.editFormInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.editFormInstance?.name, on: targetCtrlEvent },);
    }

    /**
     *  渲染视图底部按钮
     */
    public renderFooter() {
        return <template slot="footer">
            <div style=" text-align: right " dis-hover bordered={false} class="option-view-footer-actions">
                <i-button type='primary' on-click={this.onClickOk.bind(this)}>{this.containerModel.view_okbtn.text}</i-button>
                    &nbsp;&nbsp;
                    <i-button on-click={this.onClickCancel.bind(this)}>{this.containerModel.view_cancelbtn.text}</i-button>
            </div>
        </template>
    }

    /**
     * 确定
     *
     * @memberof OptViewBase
     */
    public onClickOk(): void {
        const form: any = this.$refs[this.editFormInstance?.name];
        if (!form || !form.ctrl) return;
        form.ctrl?.save().then((res: any) => {
            if (res.status == 200) {
                this.$emit('view-event', { viewName: this.viewInstance.name, action: 'close', data: [res.data] });
            }
        });
    }

    /**
     * 取消
     *
     * @memberof OptViewBase
     */
    public onClickCancel(): void {
        this.$emit('view-event', { viewName: this.viewInstance.name, action: 'close', data: null });
    }

}
