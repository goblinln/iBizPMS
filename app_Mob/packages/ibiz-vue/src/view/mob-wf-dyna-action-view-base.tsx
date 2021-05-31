import { MobWFActionViewEngine, ModelTool } from 'ibiz-core';
import { MainViewBase } from './main-view-base';
import { IPSAppDEMobWFDynaActionView, IPSDEForm } from '@ibiz/dynamic-model-api';

/**
 * 工作流动态操作视图基类
 *
 * @export
 * @class MobWFDynaActionViewBase
 * @extends {MainViewBase}
 */
export class MobWFDynaActionViewBase extends MainViewBase {

    /**
     * 视图实例
     * 
     * @memberof MobWFDynaActionViewBase
     */
    public viewInstance!: IPSAppDEMobWFDynaActionView;

    /**
     * 表单实例
     * 
     * @memberof MobWFDynaActionViewBase
     */
    protected editFormInstance!: IPSDEForm;

    /**
     *  视图挂载
     *
     * @memberof MobWFDynaActionViewBase
     */
    public viewMounted() {
        super.viewMounted();
        if (this.viewparams && this.viewparams.actionForm) {
            this.computeActivedForm(this.viewparams.actionForm);
        } else {
            this.computeActivedForm(null);
        }
        setTimeout(() => {
            if (this.Environment?.isPreviewMode) {
              return
            }
            this.viewState.next({ tag: this.editFormInstance.name, action: 'autoload', data: { srfkey: this.context[this.appDeCodeName?.toLowerCase()] } });
        }, 0);
    }

    /**
     * 计算激活表单
     * 
     * @memberof MobWFDynaActionViewBase
     */
    public computeActivedForm(inputForm: any) {
        if (!inputForm) {
            this.editFormInstance = ModelTool.findPSControlByName('form',this.viewInstance.getPSControls());
        } else {
            this.editFormInstance = ModelTool.findPSControlByName(`wfform_${inputForm.toLowerCase()}`,this.viewInstance.getPSControls());
        }
        this.$forceUpdate();
    }

    /**
     * 确认
     * 
     * @memberof MobWFDynaActionViewBase
     */
    public onClickOk() {
        let xData: any = (this.$refs.form as any).ctrl;
        if (xData) {
            let preFormData: any = xData.getData();
            let nextFormData: any = xData.transformData(preFormData);
            Object.assign(preFormData, nextFormData);
            this.$emit("view-event", { action: "viewdataschange", data: [preFormData] });
            this.$emit("view-event", { action: "close", data: null });
        }
    }

    /**
     * 取消
     * 
     * @memberof MobWFDynaActionViewBase
     */
    public onClickCancel() {
        this.$emit("view-event", { action: "close", data: null });
    }

    /**
     * 初始化编辑视图实例
     * 
     * @memberof MobWFDynaActionViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps.modeldata) as IPSAppDEMobWFDynaActionView;
        await super.viewModelInit();
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof MobWFDynaActionViewBase
     */
    public renderMainContent() {
        if (!this.editFormInstance) {
            return;
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.editFormInstance);
        return <div class='view-card view-no-caption view-no-toolbar' slot="default">
            <div class="content-container">
                {this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: "form", on: targetCtrlEvent })}
            </div>
        </div>
    }

    /**
     * 渲染底部内容区
     * 
     * @memberof MobWFDynaActionViewBase
     */    
    public renderFooter() {
        return <div class="option-wf-view-btnbox" slot="footer">
            <app-mob-button
                class="option-btn medium"
                color="medium"
                text={this.$t('app.button.cancel')}
                on-click={this.onClickCancel.bind(this)} />
            <app-mob-button
                class="option-btn success"
                text={this.$t('app.button.confirm')}
                on-click={this.onClickOk.bind(this)} />
        </div>
    }

}