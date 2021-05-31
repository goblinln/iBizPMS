import { IEntityBase } from 'ibiz-core';

/**
 * 文档库分类
 *
 * @export
 * @interface IDocLibModule
 * @extends {IEntityBase}
 */
export interface IDocLibModule extends IEntityBase {
    /**
     * 是否已收藏
     */
    isfavourites?: any;
    /**
     * grade
     */
    grade?: any;
    /**
     * path
     */
    path?: any;
    /**
     * 排序值
     */
    order?: any;
    /**
     * 名称
     */
    name?: any;
    /**
     * 查询类型
     */
    docqtype?: any;
    /**
     * owner
     */
    owner?: any;
    /**
     * branch
     */
    branch?: any;
    /**
     * 叶子模块
     */
    isleaf?: any;
    /**
     * 类型
     */
    type?: any;
    /**
     * 简称
     */
    ibizshort?: any;
    /**
     * 文档数
     */
    doccnt?: any;
    /**
     * collector
     */
    collector?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 逻辑删除标志
     */
    deleted?: any;
    /**
     * 上级模块
     */
    modulename?: any;
    /**
     * 所属文档库
     */
    doclibname?: any;
    /**
     * id
     */
    parent?: any;
    /**
     * 编号
     */
    root?: any;
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * 组织机构标识
     */
    orgid?: any;
    /**
     * 归属部门名
     */
    mdeptname?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
}
