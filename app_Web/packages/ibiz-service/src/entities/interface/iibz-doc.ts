import { IEntityBase } from 'ibiz-core';

/**
 * 文档
 *
 * @export
 * @interface IIBzDoc
 * @extends {IEntityBase}
 */
export interface IIBzDoc extends IEntityBase {
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
}
