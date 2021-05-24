import { EntityBase } from 'ibiz-core';
import { IIBZProProductHistory } from '../interface';

/**
 * 产品操作历史基类
 *
 * @export
 * @abstract
 * @class IBZProProductHistoryBase
 * @extends {EntityBase}
 * @implements {IIBZProProductHistory}
 */
export abstract class IBZProProductHistoryBase extends EntityBase implements IIBZProProductHistory {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IBZProProductHistoryBase
     */
    get srfdename(): string {
        return 'IBZPRO_PRODUCTHISTORY';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.diff;
    }
    set srfmajortext(val: any) {
        this.diff = val;
    }
    /**
     * id
     */
    id?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 不同
     */
    diff?: any;
    /**
     * 字段
     */
    field?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 新值
     */
    ibiznew?: any;
    /**
     * 操作历史编号
     */
    historysn?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 旧值
     */
    old?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * id
     */
    action?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IBZProProductHistoryBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.diff = data.diff || data.srfmajortext;
    }
}
