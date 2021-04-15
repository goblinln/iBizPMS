import { IEntityBase } from 'ibiz-core';

/**
 * 产品的分支和平台信息
 *
 * @export
 * @interface IBranch
 * @extends {IEntityBase}
 */
export interface IBranch extends IEntityBase {
    /**
     * 名称
     */
    name?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 排序
     */
    order?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 所属产品
     */
    product?: any;
}
