import { IEntityBase } from 'ibiz-core';

/**
 * 员工负载表
 *
 * @export
 * @interface IEmpLoyeeload
 * @extends {IEntityBase}
 */
export interface IEmpLoyeeload extends IEntityBase {
    /**
     * 任务名
     */
    name?: any;
    /**
     * 任务数
     */
    taskcnt?: any;
    /**
     * 部门
     */
    dept?: any;
    /**
     * 主键
     */
    id?: any;
    /**
     * 工作日天数
     */
    workday?: any;
    /**
     * 总任务数
     */
    totaltaskcnt?: any;
    /**
     * 指派给
     */
    assignedto?: any;
    /**
     * 剩余工时
     */
    left?: any;
    /**
     * 是否指派
     */
    assign?: any;
    /**
     * 属性
     */
    begin?: any;
    /**
     * 总工时
     */
    totalleft?: any;
    /**
     * 工作负载
     */
    workload?: any;
    /**
     * 结束
     */
    end?: any;
    /**
     * 项目
     */
    projectname?: any;
    /**
     * 项目编号
     */
    project?: any;
}
