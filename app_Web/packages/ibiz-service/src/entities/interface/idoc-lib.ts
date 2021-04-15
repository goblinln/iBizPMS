import { IEntityBase } from 'ibiz-core';

/**
 * 文档库
 *
 * @export
 * @interface IDocLib
 * @extends {IEntityBase}
 */
export interface IDocLib extends IEntityBase {
    /**
     * 文档类型
     *
     * @type {('product' | 'project' | 'custom')} product: 产品文档库, project: 项目文档库, custom: 自定义文档库
     */
    type?: 'product' | 'project' | 'custom';
    /**
     * 文件库类型
     *
     * @type {('doc' | 'file')} doc: 文档, file: 附件
     */
    doclibtype?: 'doc' | 'file';
    /**
     * 是否收藏
     */
    isfavourites?: any;
    /**
     * 收藏者
     */
    collector?: any;
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * 组织标识
     */
    orgid?: any;
    /**
     * 权限
     *
     * @type {('default' | 'custom')} default: 默认, custom: 自定义
     */
    acl?: 'default' | 'custom';
    /**
     * Root
     */
    root?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 分组
     */
    groups?: any;
    /**
     * 文档数量
     */
    doccnt?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 用户
     */
    users?: any;
    /**
     * 是否是主库
     */
    main?: any;
    /**
     * 文档库名称
     */
    name?: any;
    /**
     * 文件夹数
     */
    modulecnt?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 创建时间
     */
    openeddate?: any;
    /**
     * 排序
     */
    order?: any;
    /**
     * 所属产品
     */
    productname?: any;
    /**
     * 所属项目
     */
    projectname?: any;
    /**
     * 项目库
     */
    project?: any;
    /**
     * 产品库
     */
    product?: any;
    /**
     * 由谁更新
     */
    updatemanname?: any;
}
