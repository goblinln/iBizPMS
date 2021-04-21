import { IPSAppDEWFDynaActionView, IPSDEForm } from '@ibiz/dynamic-model-api';
import { ModelTool, WFActionViewEngine } from 'ibiz-core';
import { MainViewBase } from './mainview-base';

export class WFDynaActionViewBase extends MainViewBase {

    /**
     * 视图实例
     * 
     * @memberof WFDynaActionViewBase
     */
    public viewInstance!: IPSAppDEWFDynaActionView;

    /**
     * 表单实例
     * 
     * @memberof WFDynaActionViewBase
     */
    protected editFormInstance!: IPSDEForm;

    /**
     * 初始化挂载状态集合
     *
     * @memberof WFDynaActionViewBase
     */
     public initMountedMap(){
        this.mountedMap.set('self', false);
    }

    /**
     * 设置已经绘制完成状态
     *
     * @memberof WFDynaActionViewBase
     */
    public setIsMounted(name: string = 'self') {
        super.setIsMounted(name);
        if(this.editFormInstance?.name == name){
           this.viewState.next({ tag: this.editFormInstance.name, action: 'autoload', data: {srfkey:this.context[this.appDeCodeName.toLowerCase()]} });
        }
    }

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
    }
    
    /**
     * 计算激活表单
     * 
     * @memberof WFDynaActionViewBase
     */
    public computeActivedForm(inputForm:any){
        if (!inputForm) {
            this.editFormInstance = ModelTool.findPSControlByName('form',this.viewInstance.getPSControls()) as IPSDEForm;
        }else{
            this.editFormInstance = ModelTool.findPSControlByName(`wfform_${inputForm.toLowerCase()}`,this.viewInstance.getPSControls()) as IPSDEForm;
        }
        this.mountedMap.set(this.editFormInstance.name, false);
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
        await super.viewModelInit();
        this.editFormInstance = ModelTool.findPSControlByName('form',this.viewInstance.getPSControls()) as IPSDEForm;
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