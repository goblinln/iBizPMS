import { IPSAppDECalendarExplorerView, IPSCalendarExpBar } from '@ibiz/dynamic-model-api';
import { CalendarExpViewEngine, CalendarExpViewInterface, ModelTool } from 'ibiz-core';
import { ExpViewBase } from './expview-base';


/**
 * 日历导航视图基类
 *
 * @export
 * @class CalendarExpViewBase
 * @extends {ExpViewBase}
 * @implements {CalendarExpViewInterface}
 */
export class CalendarExpViewBase extends ExpViewBase implements CalendarExpViewInterface {

    /**
     * 视图实例
     * 
     * @memberof CalendarExpViewBase
     */
    public viewInstance!: IPSAppDECalendarExplorerView;

    /**
     * 导航栏实例
     * 
     * @memberof CalendarExpViewBase
     */
    public expBarInstance!: IPSCalendarExpBar;

    /**
     * 视图引擎
     *
     * @public
     * @type {Engine}
     * @memberof CalendarExpViewBase
     */
    public engine: CalendarExpViewEngine = new CalendarExpViewEngine();

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
          keyPSDEField: this.appDeCodeName.toLowerCase(),
          majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
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
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppDECalendarExplorerView;
        await super.viewModelInit();
        this.expBarInstance = ModelTool.findPSControlByType('CALENDAREXPBAR', this.viewInstance.getPSControls() || []);
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