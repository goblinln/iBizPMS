import { EntityBase } from 'ibiz-core';
import { IDoc } from '../interface';

/**
 * 文档基类
 *
 * @export
 * @abstract
 * @class DocBase
 * @extends {EntityBase}
 * @implements {IDoc}
 */
export abstract class DocBase extends EntityBase implements IDoc {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof DocBase
     */
    get srfdename(): string {
        return 'ZT_DOC';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.title;
    }
    set srfmajortext(val: any) {
        this.title = val;
    }
    /**
     * 分组
     */
    groups?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 文档正文
     */
    content?: any;
    /**
     * 文档查询类型
     */
    docqtype?: any;
    /**
     * 更新时间
     */
    editeddate?: any;
    /**
     * views
     */
    views?: any;
    /**
     * 版本号
     */
    version?: any;
    /**
     * 由谁更新
     */
    editedby?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 文档编号
     */
    id?: any;
    /**
     * 文档标题
     */
    title?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 最近更新数量
     */
    recentupdatecnt?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 文档编号
     */
    docsn?: any;
    /**
     * 文档类型
     *
     * @type {('text' | 'url')} text: 文档, url: 链接
     */
    type?: 'text' | 'url';
    /**
     * 所有文档数量
     */
    alldoccnt?: any;
    /**
     * 添加时间
     */
    addeddate?: any;
    /**
     * 权限
     *
     * @type {('open' | 'private' | 'custom')} open: 公开, private: 私有, custom: 自定义
     */
    acl?: 'open' | 'private' | 'custom';
    /**
     * 我的文档数量
     */
    mydoccnt?: any;
    /**
     * 文档链接
     */
    url?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 文档数
     */
    doccnt?: any;
    /**
     * 我的收藏数量
     */
    myfavouritecnt?: any;
    /**
     * 用户
     */
    users?: any;
    /**
     * 最近添加数量
     */
    recentaddcnt?: any;
    /**
     * 关键字
     */
    keywords?: any;
    /**
     * 是否收藏
     */
    isfavourites?: any;
    /**
     * 收藏者
     */
    collector?: any;
    /**
     * 由谁添加
     */
    addedby?: any;
    /**
     * 今日更新数量
     */
    todayupdatecnt?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 附件
     */
    files?: any;
    /**
     * 所属产品
     */
    productname?: any;
    /**
     * 所属项目
     */
    projectname?: any;
    /**
     * 所属文档库
     */
    libname?: any;
    /**
     * 模块分类
     */
    modulename?: any;
    /**
     * 所属文档库
     */
    lib?: any;
    /**
     * 所属项目
     */
    project?: any;
    /**
     * 所属产品
     */
    product?: any;
    /**
     * 所属分类
     */
    module?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof DocBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.title = data.title || data.srfmajortext;
    }
}
