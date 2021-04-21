import { IEntityBase } from 'ibiz-core';

/**
 * 测试套件
 *
 * @export
 * @interface ITestSuite
 * @extends {IEntityBase}
 */
export interface ITestSuite extends IEntityBase {
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 名称
     */
    name?: any;
    /**
     * 创建时间
     */
    addeddate?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 最后编辑人
     */
    lasteditedby?: any;
    /**
     * 类型
     *
     * @type {('library' | 'private' | 'public')} library: 用例库, private: 私有, public: 公开
     */
    type?: 'library' | 'private' | 'public';
    /**
     * 最后编辑时间
     */
    lastediteddate?: any;
    /**
     * 由谁创建
     */
    addedby?: any;
    /**
     * 用例数
     */
    casecnt?: any;
    /**
     * 描述
     */
    desc?: any;
    /**
     * 所属产品
     */
    product?: any;
}
