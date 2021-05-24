import { IEntityBase } from 'ibiz-core';

/**
 * 产品操作历史
 *
 * @export
 * @interface IIBZProProductHistory
 * @extends {IEntityBase}
 */
export interface IIBZProProductHistory extends IEntityBase {
    /**
     * id
     */
    id?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 不同
     */
    diff?: any;
    /**
     * 字段
     */
    field?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 新值
     */
    ibiznew?: any;
    /**
     * 操作历史编号
     */
    historysn?: any;
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
    action?: any;
}
