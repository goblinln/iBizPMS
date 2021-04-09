import { EntityBase } from 'ibiz-core';
import { IBurn } from '../interface';

/**
 * burn基类
 *
 * @export
 * @abstract
 * @class BurnBase
 * @extends {EntityBase}
 * @implements {IBurn}
 */
export abstract class BurnBase extends EntityBase implements IBurn {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof BurnBase
     */
    get srfdename(): string {
        return 'ZT_BURN';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.date;
    }
    set srfmajortext(val: any) {
        this.date = val;
    }
    /**
     * 周末
     */
    isweekend?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 虚拟主键
     */
    id?: any;
    /**
     * 总计消耗
     */
    consumed?: any;
    /**
     * 预计剩余
     */
    left?: any;
    /**
     * 最初预计
     */
    estimate?: any;
    /**
     * 所属项目
     */
    project?: any;
    /**
     * 任务
     */
    task?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof BurnBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.date = data.date || data.srfmajortext;
    }
}
