import { IBizFormModel, IBizWFActionViewModel, WFActionViewEngine } from 'ibiz-core';
import { MainViewBase } from './mainview-base';

export class WFActionViewBase extends MainViewBase {
    
    /**
     * 视图实例
     * 
     * @memberof WFActionViewBase
     */
    public viewInstance!: IBizWFActionViewModel;

    /**
     * 视图引擎
     * 
     * @memberof WFActionViewBase
     */
    public engine: WFActionViewEngine = new WFActionViewEngine();

    /**
     * 表单实例
     * 
     * @memberof WFActionViewBase
     */
    protected formInstance!: IBizFormModel;

    /**
     * 初始化编辑视图实例
     * 
     * @memberof WFActionViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizWFActionViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.formInstance = this.viewInstance.getControl('form');
    }

    /**
     * 引擎初始化
     *
     * @public
     * @memberof WFActionViewBase
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        this.engine.init({
            view: this,
            parentContainer: this.$parent,
            form: (this.$refs[this.formInstance.name] as any).ctrl,
            p2k: '0',
            isLoadDefault: this.viewInstance.loadDefault,
            keyPSDEField: (this.viewInstance.appDataEntity.codeName).toLowerCase(),
            majorPSDEField: (this.viewInstance.appDataEntity.majorField.codeName).toLowerCase(),
        });
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof WFActionViewBase
     */
    public renderMainContent() {
        if(!this.formInstance){
            return;
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.formInstance);
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: "form", on: targetCtrlEvent });
    }

    /**
     * 确定
     *
     * @memberof WFActionViewBase
     */
    public onClickOk(): void {
        const form: any = (this.$refs[this.formInstance.name] as any)?.ctrl;
        if (!form) {
            return;
        }
        form.wfsubmit([{...form.data}]).then((response:any) =>{
            if (!response || response.status !== 200) {
                return;
            }
            this.$store.commit('viewaction/setViewDataChange', { viewtag: this.viewtag, viewdatachange: false });
            this.$emit('view-event', { viewName: this.viewInstance.name, action: 'viewdataschange', data: [{ ...response.data }] });
            this.$emit('view-event', { viewName: this.viewInstance.name, action: 'close', data: null });
        })
    }

    /**
     * 取消
     *
     * @memberof WFActionViewBase
     */
    public onClickCancel(): void {
        this.$emit('view-event', { viewName: this.viewInstance.name, action: 'close', data: null });
    }
}