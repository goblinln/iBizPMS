/**
 * 甘特视图基类接口
 *
 * @interface GanttControlInterface
 */
export interface GanttControlInterface {

    /**
     * 加载数据
     *
     *
     * @param {*} [task={}] 节点数据
     * @memberof GanttControlInterface
     */
    load(task?: any): void;

    /**
     * 刷新
     *
     * @memberof GanttControlInterface
     */
    refresh(args?: any): void;

    /**
     * 节点点击事件
     *
     * @param {{event: any, data: any}} {event, data} 事件源，节点数据
     * @memberof GanttControlInterface
     */
    taskClick({event, data}: {event: any, data: any}): void;

    /**
     * 节点展开事件
     *
     * @param {*} task 当前节点
     * @memberof GanttControlInterface
     */
    taskItemExpand(task: any): void;
}
