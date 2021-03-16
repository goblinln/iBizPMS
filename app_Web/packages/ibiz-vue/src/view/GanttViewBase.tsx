import { IBizGanttViewModel, IBizGanttModel } from 'ibiz-core';
import { MDViewBase } from './MDViewBase';
export class GanttViewBase extends MDViewBase {

    /**
     * 甘特视图实例
     * 
     * @memberof GanttViewBase
     */
    public viewInstance!: IBizGanttViewModel;

    /**
     * 甘特部件实例
     * 
     * @memberof GanttViewBase
     */
    public ganttInstance!: IBizGanttModel;

    /**
    * 视图默认加载
    * 
    * @memberof GanttViewBase
    */
    public isLoadDefault:boolean = true;

    /**
     * 初始化甘特视图实例
     * 
     * @param opts 
     * @memberof AppDefaultCalendarView
     */
    public async viewModelInit() {
        this.viewInstance = new IBizGanttViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.isLoadDefault = this.viewInstance.loadDefault;
        this.ganttInstance = this.viewInstance.getControl('gantt');
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
        return this.$createElement(targetCtrlName,{ props: targetCtrlParam,ref: this.viewInstance.viewGantt.name ,on:targetCtrlEvent},);
    }

}