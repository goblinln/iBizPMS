import { CalendarExpViewEngine, IBizCalendarExpBarModel, IBizCalendarExpViewModel } from 'ibiz-core';
import { ExpViewBase } from './expview-base';

export class CalendarExpViewBase extends ExpViewBase {

    /**
     * 视图实例
     * 
     * @memberof CalendarExpViewBase
     */
    public viewInstance!: IBizCalendarExpViewModel;

    /**
     * 导航栏实例
     * 
     * @memberof CalendarExpViewBase
     */
    public expBarInstance!: IBizCalendarExpBarModel;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof CalendarExpViewBase
     */
    public engine: CalendarExpViewEngine = new CalendarExpViewEngine;

    /**
     * 引擎初始化
     *
     * @public
     * @memberof CalendarExpViewBase
     */
    public engineInit(): void {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        let engineOpts = ({
          view: this,
          parentContainer: this.$parent,
          p2k: '0',
          calendarExpBar:(this.$refs[this.expBarInstance.name] as any).ctrl,
          keyPSDEField: (this.viewInstance.appDataEntity?.codeName)?.toLowerCase(),
          majorPSDEField: (this.viewInstance.appDataEntity?.majorField?.codeName)?.toLowerCase(),
          isLoadDefault: this.viewInstance.loadDefault,
        });
        this.engine.init(engineOpts);
    } 

    /**
     * 初始化分页导航视图实例
     * 
     * @memberof CalendarExpViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizCalendarExpViewModel(this.staticProps.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.expBarInstance = this.viewInstance.getControl('calendarexpbar');
    }

    /**
     * 计算日历导航栏部件参数
     * 
     * @memberof CalendarExpViewBase
     */
    public computeTargetCtrlData(controlInstance:any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.staticProps,{
            sideBarLayout:this.viewInstance.sideBarLayout
        })
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }

}