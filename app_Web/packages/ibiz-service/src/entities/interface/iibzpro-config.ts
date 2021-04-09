import { IEntityBase } from 'ibiz-core';

/**
 * 系统配置表
 *
 * @export
 * @interface IIbzproConfig
 * @extends {IEntityBase}
 */
export interface IIbzproConfig extends IEntityBase {
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 系统配置表名称
     */
    ibzproconfigname?: any;
    /**
     * 描述
     */
    memo?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 系统配置表标识
     */
    ibzproconfigid?: any;
    /**
     * 范围
     *
     * @type {('sys' | 'org' | 'dept1' | 'dept2' | 'user')} sys: 全局, org: 当前组织, dept1: 当前部门（含子部门）, dept2: 当前部门（不含子部门）, user: 个人
     */
    scope?: 'sys' | 'org' | 'dept1' | 'dept2' | 'user';
    /**
     * 类型
     *
     * @type {('GROUP' | 'ITEM')} GROUP: 分组, ITEM: 配置项
     */
    type?: 'GROUP' | 'ITEM';
    /**
     * 管理现状
     *
     * @type {('product_project' | 'product_iteration' | 'project_iteration' | 'product_sprint' | 'project_sprint')} product_project: 产品 - 项目, product_iteration: 产品 - 迭代, project_iteration: 项目 - 迭代, product_sprint: 产品 - 冲刺, project_sprint: 项目 - 冲刺
     */
    managementstatus?: 'product_project' | 'product_iteration' | 'project_iteration' | 'product_sprint' | 'project_sprint';
    /**
     * 是否启用
     *
     * @type {('1' | '0')} 1: 是, 0: 否
     */
    vaild?: '1' | '0';
    /**
     * 建立人
     */
    createman?: any;
}
