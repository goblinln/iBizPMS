import { EntityBase } from 'ibiz-core';
import { IBranch } from '../interface';

/**
 * 产品的分支和平台信息基类
 *
 * @export
 * @abstract
 * @class BranchBase
 * @extends {EntityBase}
 * @implements {IBranch}
 */
export abstract class BranchBase extends EntityBase implements IBranch {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof BranchBase
     */
    get srfdename(): string {
        return 'ZT_BRANCH';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.name;
    }
    set srfmajortext(val: any) {
        this.name = val;
    }
    /**
     * 名称
     */
    name?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 排序
     */
    order?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 所属产品
     */
    product?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof BranchBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
