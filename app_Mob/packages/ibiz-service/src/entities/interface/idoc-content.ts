import { IEntityBase } from 'ibiz-core';

/**
 * 文档内容
 *
 * @export
 * @interface IDocContent
 * @extends {IEntityBase}
 */
export interface IDocContent extends IEntityBase {
    /**
     * 附件
     */
    files?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 文档正文
     */
    content?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 文档类型
     *
     * @type {('html' | 'markdown' | 'url' | 'word' | 'ppt' | 'excel')} html: 富文本, markdown: Markdown, url: 链接, word: Word, ppt: PPT, excel: Excel
     */
    type?: 'html' | 'markdown' | 'url' | 'word' | 'ppt' | 'excel';
    /**
     * 文档标题
     */
    title?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 版本号
     */
    version?: any;
    /**
     * 文档摘要
     */
    digest?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 文档
     */
    doc?: any;
    /**
     * 文档内容编号
     */
    doccontentsn?: any;
}
