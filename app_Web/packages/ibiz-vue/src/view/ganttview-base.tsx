import { IPSAppDEGanttView, IPSDEGantt, IPSAppDataEntity, IPSAppDEField } from '@ibiz/dynamic-model-api';
import { MDViewBase } from './mdview-base';
import { ModelTool } from 'ibiz-core';

/**
 * 甘特视图基类
 *
 * @export
 * @class GanttViewBase
 * @extends {MDViewBase}
 */
export class GanttViewBase extends MDViewBase {

    /**
     * 甘特视图实例
     * 
     * @memberof GanttViewBase
     */
    public viewInstance!: IPSAppDEGanttView;

    /**
     * 甘特部件实例
     * 
     * @memberof GanttViewBase
     */
    public ganttInstance!: IPSDEGantt;

    /**
    * 视图默认加载
    * 
    * @memberof GanttViewBase
    */
    public isLoadDefault: boolean = true;

    /**
     * 初始化甘特视图实例
     * 
     * @param opts 
     * @memberof GanttViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps.modeldata) as IPSAppDEGanttView;
        await super.viewModelInit();
        this.isLoadDefault = this.viewInstance?.loadDefault;
        this.ganttInstance = ModelTool.findPSControlByName('gantt',this.viewInstance.getPSControls()) as IPSDEGantt;
    }

    /**
     *  多数据视图挂载
     *
     * @memberof GanttViewBase
     */
    public viewMounted() {
        super.viewMounted();
        if(this.viewIsLoaded && this.isLoadDefault) {
            this.viewState.next({ tag:'gantt', action: 'load', data: this.viewparams });
        }
    }

    /**
     *  绘制甘特部件
     *
     * @memberof GanttViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam,targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any,targetCtrlEvent:any } = this.computeTargetCtrlData(this.ganttInstance);
        return this.$createElement(targetCtrlName,{ props: targetCtrlParam,ref: this.ganttInstance?.name ,on: targetCtrlEvent},);
    }

}