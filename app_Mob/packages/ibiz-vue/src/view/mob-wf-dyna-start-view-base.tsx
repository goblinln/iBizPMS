import { IPSAppDEWFDynaStartView, IPSDEForm } from '@ibiz/dynamic-model-api';
import { ModelTool } from 'ibiz-core';
import { MainViewBase } from './main-view-base';

export class MobWFDynaStartViewBase extends MainViewBase {

    /**
     * 视图实例
     * 
     * @memberof MobWFDynaStartViewBase
     */
    public viewInstance!: IPSAppDEWFDynaStartView;

    /**
     * 表单实例
     * 
     * @memberof MobWFDynaStartViewBase
     */
    protected editFormInstance!: IPSDEForm;

    /**
     * 初始化挂载状态集合
     *
     * @memberof MobWFDynaStartViewBase
     */
    public initMountedMap(){
        this.mountedMap.set('self', false);
    }

    /**
     * 设置已经绘制完成状态
     *
     * @memberof MobWFDynaStartViewBase
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
     * @memberof MobWFDynaStartViewBase
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
     * @memberof MobWFDynaStartViewBase
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
     * @memberof MobWFDynaStartViewBase
     */
    public onClickOk(){
        let xData:any =(this.$refs.form as any).ctrl;
        if(xData && xData.formValidateStatus()){
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
     * @memberof MobWFDynaStartViewBase
     */
    public onClickCancel(){
        this.$emit("view-event", { action: "close", data: null });
    }

    /**
     * 初始化编辑视图实例
     * 
     * @memberof MobWFDynaStartViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();        
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof MobWFDynaStartViewBase
     */
    public renderMainContent() {
        if(!this.editFormInstance){
            return;
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.editFormInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: "form", on: targetCtrlEvent });
    }

}