import { IEntityBase } from 'ibiz-core';

/**
 * 套件用例
 *
 * @export
 * @interface ISuiteCase
 * @extends {IEntityBase}
 */
export interface ISuiteCase extends IEntityBase {
    /**
     * 虚拟主键
     */
    id?: any;
    /**
     * 用例版本
     */
    version?: any;
    /**
     * 测试套件
     */
    suite?: any;
    /**
     * 用例
     */
    ibizcase?: any;
    /**
     * 所属产品
     */
    product?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 归属部门
     */
    dept?: any;
}
