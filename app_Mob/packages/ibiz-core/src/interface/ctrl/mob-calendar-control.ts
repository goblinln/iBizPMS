import { MobMDControlInterface } from 'ibiz-core';

/**
 * 日历基类接口
 *
 * @interface MobCalendarControlInterface
 */
export interface MobCalendarControlInterface extends MobMDControlInterface {

    /**
     * 分页节点切换
     *
     * @param {*} $event
     * @returns
     * @memberof MobCalendarControlInterface
     */
    ionChange($event:any): void

    /**
     * 上一月事件的回调方法
     *
     * @memberof MobCalendarControlInterface
     */
    prev(year: any, month: any, weekIndex: any):void

    /**
     * 下一月事件的回调方法
     *
     * @memberof MobCalendarControlInterface
     */
    next(year: any, month: any, weekIndex: any):void      

    /**
     * 选择年份事件的回调方法
     *
     * @memberof MobCalendarControlInterface
     */
    selectYear(year: any): void
       
    /**
     * 选择月份事件的回调方法
     *
     * @memberof MobCalendarControlInterface
     */
    selectMonth(month: any, year: any): void      

    /**
     * 点击前一天
     * @memberof MobCalendarControlInterface
     */
    prevDate(): void

    /**
     * 点击后一天
     * @memberof MobCalendarControlInterface
     */
    nextDate(): void

    /**
     * 日历部件数据选择日期回调
     *
     * @param any 
     * @memberof MobCalendarControlInterface
     */
    clickDay(data: any): void

    /**
     * 日程点击事件
     *
     * @param {*} $event calendar事件对象或event数据
     * @param {*} isOriginData true：$event是原始event数据，false：是组件
     * @param {*} $event timeline事件对象
     * @memberof MobCalendarControlInterface
     */
    onEventClick($event: any, isOriginData?:boolean, $event2?: any): void;

    /**
     * 日历刷新
     *
     * @param {*} [args] 额外参数
     * @memberof MobCalendarControlInterface
     */
    refresh(args?:any): void;


}
