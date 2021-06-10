/**
 * 日历基类接口
 *
 * @interface CalendarControlInterface
 */
export interface CalendarControlInterface {
    /**
     * 图例点击事件
     *
     * @param {string} itemType 日历项类型
     * @return {*} 
     * @memberof CalendarControlInterface
     */
    legendTrigger(itemType:string): void;

    /**
     * 面板数据变化处理事件
     * @param {any} item 当前数据
     * @param {any} $event 面板事件数据
     *
     * @memberof CalendarControlInterface
     */
    onPanelDataChange(item:any, $event:any): void;

    /**
     * 搜索获取日程事件
     *
     * @param {*} [fetchInfo] 日期信息
     * @param {*} [successCallback] 成功回调
     * @return {*} 
     * @memberof CalendarControlInterface
     */
    searchEvents(fetchInfo?:any, successCallback?:any): void;

    /**
     * 日期点击事件
     *
     * @param {*} $event 日期信息
     * @memberof CalendarControlInterface
     */
    onDateClick($event: any): void;

    /**
     * 日程点击事件
     *
     * @param {*} $event calendar事件对象或event数据
     * @param {*} isOriginData true：$event是原始event数据，false：是组件
     * @param {*} $event timeline事件对象
     * @memberof CalendarControlInterface
     */
    onEventClick($event: any, isOriginData?:boolean, $event2?: any): void;

    /**
     * 日历刷新
     *
     * @param {*} [args] 额外参数
     * @memberof CalendarControlInterface
     */
    refresh(args?:any): void;

    /**
     * 日程拖动事件
     *
     * @param {*} $event 事件信息
     * @memberof CalendarControlInterface
     */
    onEventDrop($event: any): void;

    /**
     * 快速工具栏菜单项点击
     * 
     * @param {*} tag 菜单项标识
     * @param {*} $event 事件源
     * @memberof CalendarControlInterface
     */
    itemClick(tag: any, $event: any): void;

    /**
     * 时间点击
     *
     * @param {*} $event 当前时间
     * @param {*} jsEvent 原生事件对象  
     * @returns
     * @memberof CalendarControlInterface
     */
    onDayClick($event: any, jsEvent: any): void;
}
