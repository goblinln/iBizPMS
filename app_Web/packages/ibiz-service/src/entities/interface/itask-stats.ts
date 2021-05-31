import { IEntityBase } from 'ibiz-core';

/**
 * 任务统计
 *
 * @export
 * @interface ITaskStats
 * @extends {IEntityBase}
 */
export interface ITaskStats extends IEntityBase {
    /**
     * 任务截至日期
     */
    taskdeadline?: any;
    /**
     * 任务编号
     */
    taskid?: any;
    /**
     * 任务实际开始时间
     */
    taskrealstart?: any;
    /**
     * 效率
     */
    taskefficient?: any;
    /**
     * 任务预计消耗
     */
    taskestimate?: any;
    /**
     * 结束
     */
    end?: any;
    /**
     * 用户总消耗
     */
    userconsumed?: any;
    /**
     * 剩余总工时
     */
    totalleft?: any;
    /**
     * 项目总消耗
     */
    projectconsumed?: any;
    /**
     * 属性
     */
    begin?: any;
    /**
     * 消耗总工时
     */
    totalconsumed?: any;
    /**
     * 预计总工时
     */
    totalestimate?: any;
    /**
     * 任务预计开始日期
     */
    taskeststarted?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 部门
     */
    dept?: any;
    /**
     * 名称
     */
    name?: any;
    /**
     * 项目名称
     */
    projectname?: any;
    /**
     * 项目
     */
    project?: any;
    /**
     * 任务实际完成日期
     */
    taskfinisheddate?: any;
    /**
     * 任务名称
     */
    taskname?: any;
    /**
     * 总任务数
     */
    taskcnt?: any;
    /**
     * 任务优先级
     *
     * @type {(1 | 2 | 3 | 4)} 1: 1, 2: 2, 3: 3, 4: 4
     */
    taskpri?: 1 | 2 | 3 | 4;
    /**
     * 任务延期
     */
    taskdelay?: any;
    /**
     * 完成者
     */
    finishedby?: any;
}
