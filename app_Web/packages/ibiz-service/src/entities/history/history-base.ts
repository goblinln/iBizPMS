import { EntityBase } from 'ibiz-core';
import { IHistory } from '../interface';

/**
 * 操作历史基类
 *
 * @export
 * @abstract
 * @class HistoryBase
 * @extends {EntityBase}
 * @implements {IHistory}
 */
export abstract class HistoryBase extends EntityBase implements IHistory {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof HistoryBase
     */
    get srfdename(): string {
        return 'ZT_HISTORY';
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
     * 归属组织名
     */
    orgname?: any;
    /**
     * 不同
     */
    diff?: any;
    /**
     * 字段
     */
    field?: any;
    /**
     * 新值
     */
    ibiznew?: any;
    /**
     * 由谁创建
     */
    createby?: any;
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
     * 归属部门名
     */
    deptname?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 关联日志
     */
    action?: any;
    /**
     * 操作历史编号
     */
    historysn?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof HistoryBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.diff = data.diff || data.srfmajortext;
    }
}
