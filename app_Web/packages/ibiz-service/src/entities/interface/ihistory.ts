import { IEntityBase } from 'ibiz-core';

/**
 * 操作历史
 *
 * @export
 * @interface IHistory
 * @extends {IEntityBase}
 */
export interface IHistory extends IEntityBase {
    /**
     * 不同
     */
    diff?: any;
    /**
     * 字段
     */
    field?: any;
    /**
     * 新值
     */
    ibiznew?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 旧值
     */
    old?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 关联日志
     */
    action?: any;
}
