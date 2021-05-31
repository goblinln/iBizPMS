import { Subject } from "rxjs";
import { ModelTool, ViewState } from 'ibiz-core';
import { MDViewBase } from './mdview-base';
import { IPSDEMultiEditViewPanel } from "@ibiz/dynamic-model-api";
/**
 * 多表单编辑视图基类
 *
 * @export
 * @class EditViewBase
 * @extends {MainViewBase}
 */
export class MEditViewBase extends MDViewBase {

    /**
     * 视图实例
     * 
     * @memberof MEditViewBase
     */
    public viewInstance!: any;

    /**
     * 多编辑视图实例
     * 
     * @memberof MEditViewBase
     */
    public meditViewPanelInstance!: IPSDEMultiEditViewPanel;

    /**
     * 关系数据变化
     *
     * @param {*} $event
     * @memberof MEditViewBase
     */
    public onViewDataDirty($event: any) {
        this.$emit('view-event', { viewName: this.viewCodeName, action: 'drdatachange', data: $event });
    }

    /**
     * 关系数据保存执行完成
     *
     * @param {*} $event
     * @memberof MEditViewBase
     */
    public onDRDataSaved($event: any) {
        this.$emit('view-event', { viewName: this.viewCodeName, action: 'drdatasaved', data: $event });

    }

    /**
     * 初始化多表单编辑视图实例
     * 
     * @param opts 
     * @memberof MEditViewBase
     */
    public async viewModelInit(arg?: any) {
        await super.viewModelInit();
        this.meditViewPanelInstance = ModelTool.findPSControlByName('meditviewpanel',this.viewInstance.getPSControls()) as IPSDEMultiEditViewPanel;
    }

    /**
     * 绘制视图主体内容区
     * 
     * @memberof MEditViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.meditViewPanelInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.meditViewPanelInstance.name, on: targetCtrlEvent });
    }

    /**
     * 多表单编辑视图初始化
     *
     * @memberof MEditViewBase
     */
    public async viewInit(){
        super.viewInit()
        if (this.formDruipartState) {
            this.formDruipartStateEvent = this.formDruipartState.subscribe(({ action }: any) => {
                if(Object.is(action,'save')){
                    this.viewState.next({ tag:'meditviewpanel', action: 'save', data: this.viewparams });
                }
                if(Object.is(action,'remove')){
                    this.viewState.next({ tag:'meditviewpanel', action: 'remove', data: this.viewparams });
                }
                if(Object.is(action,'load')){
                    this.viewState.next({ tag: 'meditviewpanel', action: 'load', data: this.viewparams }); 
                }
            });
        }
    }


    /**
     * 多表单编辑视图挂载
     *
     * @memberof MEditViewBase
     */
    public viewMounted() {
        if (!this.formDruipartState) {
            this.viewState.next({ tag: 'meditviewpanel', action: 'load', data: this.viewparams });
        }
        super.viewMounted();
    }

    /**
     * 部件事件监听
     *
     * @memberof MEditViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        if (Object.is(controlname, this.meditViewPanelInstance?.name) && action) {
            switch (action) {
                case 'drdatasaved':
                    this.onDRDataSaved(data);
                    break;
                case 'drdatachange':
                    this.onViewDataDirty(data);
            }
        }
        super.onCtrlEvent(controlname, action, data);
    }

}