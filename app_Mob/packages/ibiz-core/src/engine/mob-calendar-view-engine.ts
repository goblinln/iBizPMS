import { ViewEngine } from './view-engine';

/**
 * 实体移动端日历视图界面引擎
 *
 * @export
 * @class MobCalendarViewEngine
 * @extends {ViewEngine}
 */
export class MobCalendarViewEngine extends ViewEngine {

    /**
     * 日历部件
     *
     * @protected
     * @type {*}
     * @memberof MobCalendarViewEngine
     */
    protected calendar: any;

    /**
     * 初始化日历视图引擎
     *
     * @param {*} [options={}]
     * @memberof MobCalendarViewEngine
     */
    public init(options: any = {}): void {
        this.calendar = options.calendar;
        super.init(options);
    }

    /**
     * 引擎加载
     *
     * @param {*} [opts={}]
     * @memberof MobCalendarViewEngine
     */
    public load(opts: any = {}): void {
        super.load(opts);
        if (this.getCalendar()) {
            const tag = this.getCalendar().name;
            const data: any = {};
            let action: string = 'load';
            Object.assign(data, this.view.viewParam);
            this.setViewState2({ tag: tag, action: action, viewdata: data });
        }
    }

    /**
     * 部件事件机制
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof MobCalendarViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);
        if (Object.is(ctrlName, 'calendar')) {
            this.calendarEvent(eventName, args);
        }
    }

    /**
     * 日历事件
     *
     * @param {string} eventName
     * @param {*} args
     * @memberof MobCalendarViewEngine
     */
    public calendarEvent(eventName: string, args: any): void {
        if (Object.is(eventName, 'load')) {
            this.onCalendarLoad(args);
        }
    }

    /**
     * 日历数据加载完成
     *
     * @param {*} args
     * @memberof MobCalendarViewEngine
     */
    public onCalendarLoad(arg: any): void {
        this.emitViewEvent('load', arg);
        this.emitViewEvent('viewdataschange', JSON.stringify({ action: 'load', status: 'success', data: arg }));
    }

    /**
     * 获取日历对象
     *
     * @returns {*}
     * @memberof MobCalendarViewEngine
     */
    public getCalendar(): any {
        return this.calendar;
    }

}