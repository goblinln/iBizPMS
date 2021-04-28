import { MDViewBase } from './md-view-base';
import { MobCalendarViewEngine, ModelTool } from 'ibiz-core';
import { IPSAppDEMobCalendarView, IPSDECalendar } from '@ibiz/dynamic-model-api';

export class MobCalendarViewBase extends MDViewBase {

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof MobCalendarViewBase
     */
    public engine: MobCalendarViewEngine = new MobCalendarViewEngine()

    /**
     * 视图实例
     * 
     * @memberof MobCalendarViewBase
     */
    public viewInstance!: IPSAppDEMobCalendarView;

    /**
     * 日历实例
     * 
     * @memberof MobCalendarViewBase
     */
    public calendarInstance!: IPSDECalendar;

    /**
     * 引擎初始化（移动端日历有引擎）
     * 
     * @memberof MobCalendarViewBase
     */
    public engineInit() {
        this.engine.init({
            view: this,
            parentContainer: this.$parent,
            calendar: (this.$refs[this.calendarInstance.name] as any).ctrl,
            p2k: '0',
            keyPSDEField: this.appDeCodeName.toLowerCase(),
            majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            isLoadDefault: this.staticProps.hasOwnProperty('isLoadDefault') ? this.staticProps.isLoadDefault : true,
        });
    }

    /**
     * 初始化日历视图实例
     * 
     * @param opts 
     * @memberof MobCalendarViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDEMobCalendarView;
        await super.viewModelInit();
        this.calendarInstance = ModelTool.findPSControlByName('calendar', this.viewInstance.getPSControls() || []);
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {*} controlInstance
     * @returns
     * @memberof MobCalendarViewBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.staticProps, {
            isChoose: this.isChoose
        })
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }

    /**
     * 渲染视图主体内容区
     * 
     * @memberof MobCalendarViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.calendarInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.calendarInstance.name, on: targetCtrlEvent });
    }

}