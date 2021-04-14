import { IEntityBase } from 'ibiz-core';

/**
 * 用例库
 *
 * @export
 * @interface IIbzLib
 * @extends {IEntityBase}
 */
export interface IIbzLib extends IEntityBase {
    /**
     * 描述
     */
    desc?: any;
    /**
     * 产品
     */
    product?: any;
    /**
     * 创建时间
     */
    addeddate?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 最后编辑时间
     */
    lastediteddate?: any;
    /**
     * 由谁创建
     */
    addedby?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 名称
     */
    name?: any;
    /**
     * 类型
     *
     * @type {('library' | 'private' | 'public')} library: 用例库, private: 私有, public: 公开
     */
    type?: 'library' | 'private' | 'public';
    /**
     * 最后编辑人
     */
    lasteditedby?: any;
}
