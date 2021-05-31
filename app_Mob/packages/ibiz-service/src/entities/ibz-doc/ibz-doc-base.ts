import { EntityBase } from 'ibiz-core';
import { IIBzDoc } from '../interface';

/**
 * 文档基类
 *
 * @export
 * @abstract
 * @class IBzDocBase
 * @extends {EntityBase}
 * @implements {IIBzDoc}
 */
export abstract class IBzDocBase extends EntityBase implements IIBzDoc {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IBzDocBase
     */
    get srfdename(): string {
        return 'IBZ_DOC';
    }
    get srfkey() {
        return this.ibzdocid;
    }
    set srfkey(val: any) {
        this.ibzdocid = val;
    }
    get srfmajortext() {
        return this.ibzdocname;
    }
    set srfmajortext(val: any) {
        this.ibzdocname = val;
    }
    /**
     * 文档标识
     */
    ibzdocid?: any;
    /**
     * 由谁添加
     */
    addedby?: any;
    /**
     * 由谁更新
     */
    editedby?: any;
    /**
     * 添加时间
     */
    addeddate?: any;
    /**
     * 大小
     */
    size?: any;
    /**
     * 所属文档库
     */
    lib?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 文档名称
     */
    ibzdocname?: any;
    /**
     * 排序
     */
    order?: any;
    /**
     * 是否已收藏
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    iscollect?: 'yes' | 'no';
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 更新时间
     */
    editeddate?: any;
    /**
     * 对象类型
     */
    objecttype?: any;
    /**
     * 更新人
     */
    updateman?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IBzDocBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.ibzdocid = data.ibzdocid || data.srfkey;
        this.ibzdocname = data.ibzdocname || data.srfmajortext;
    }
}
