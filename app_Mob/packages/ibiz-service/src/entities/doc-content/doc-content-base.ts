import { EntityBase } from 'ibiz-core';
import { IDocContent } from '../interface';

/**
 * 文档内容基类
 *
 * @export
 * @abstract
 * @class DocContentBase
 * @extends {EntityBase}
 * @implements {IDocContent}
 */
export abstract class DocContentBase extends EntityBase implements IDocContent {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof DocContentBase
     */
    get srfdename(): string {
        return 'ZT_DOCCONTENT';
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
     * 文档内容编号
     */
    doccontentsn?: any;
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
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof DocContentBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.title = data.title || data.srfmajortext;
    }
}
