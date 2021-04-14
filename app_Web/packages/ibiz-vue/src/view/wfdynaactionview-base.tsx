import { IBizFormModel, IBizWFDynaActionViewModel, WFActionViewEngine } from 'ibiz-core';
import { MainViewBase } from './mainview-base';

export class WFDynaActionViewBase extends MainViewBase {

    /**
     * 视图实例
     * 
     * @memberof WFDynaActionViewBase
     */
    public viewInstance!: IBizWFDynaActionViewModel;

    /**
     * 表单实例
     * 
     * @memberof WFDynaActionViewBase
     */
    protected editFormInstance!: IBizFormModel;

    /**
     *  视图挂载
     *
     * @memberof WFDynaActionViewBase
     */
    public viewMounted() {
        super.viewMounted();
        if(this.viewparams && this.viewparams.actionForm) {
            this.computeActivedForm(this.viewparams.actionForm);
        } else {
            this.computeActivedForm(null);
        }
        setTimeout(() => {
            this.viewState.next({ tag: this.editFormInstance.name, action: 'autoload', data: {srfkey:this.context[this.viewInstance.appDeCodeName.toLowerCase()]} });
        }, 0);
    }
    
    /**
     * 计算激活表单
     * 
     * @memberof WFDynaActionViewBase
     */
    public computeActivedForm(inputForm:any){
        if (!inputForm) {
            this.editFormInstance = this.viewInstance.getControl('form');
        }else{
            this.editFormInstance = this.viewInstance.getControl(`wfform_${inputForm.toLowerCase()}`);
        }
        this.$forceUpdate();
    }

    /**
     * 确认
     * 
     * @memberof WFDynaActionViewBase
     */
    public onClickOk(){
        let xData:any =(this.$refs.form as any).ctrl;
        if(xData){
            let preFormData:any = xData.getData();
            let nextFormData:any = xData.transformData(preFormData);
            Object.assign(preFormData,nextFormData);
            this.$emit("view-event", { action: "viewdataschange", data: [preFormData] });
            this.$emit("view-event", { action: "close", data: null });
        }
    }

    /**
     * 取消
     * 
     * @memberof WFDynaActionViewBase
     */
    public onClickCancel(){
        this.$emit("view-event", { action: "close", data: null });
    }

    /**
     * 初始化编辑视图实例
     * 
     * @memberof WFDynaActionViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizWFDynaActionViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.editFormInstance = this.viewInstance.getControl('form');
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof WFDynaActionViewBase
     */
    public renderMainContent() {
        if(!this.editFormInstance){
            return;
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.editFormInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: "form", on: targetCtrlEvent });
    }

}