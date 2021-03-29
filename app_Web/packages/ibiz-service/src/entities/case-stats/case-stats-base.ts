import { EntityBase } from 'ibiz-core';
import { ICaseStats } from '../interface';

/**
 * 测试用例统计基类
 *
 * @export
 * @abstract
 * @class CaseStatsBase
 * @extends {EntityBase}
 * @implements {ICaseStats}
 */
export abstract class CaseStatsBase extends EntityBase implements ICaseStats {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof CaseStatsBase
     */
    get srfdename(): string {
        return 'IBZ_CASESTATS';
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
     * 通过用例数
     */
    passcase?: any;
    /**
     * 阻塞用例数
     */
    blockedcase?: any;
    /**
     * 总执行数
     */
    totalruncase?: any;
    /**
     * 失败用例数
     */
    failcase?: any;
    /**
     * 用例标题
     */
    title?: any;
    /**
     * 总用例数
     */
    totalcase?: any;
    /**
     * 用例通过率
     */
    passrate?: any;
    /**
     * 用例编号
     */
    id?: any;
    /**
     * 模块名称
     */
    modulename?: any;
    /**
     * 产品
     */
    product?: any;
    /**
     * 模块
     */
    module?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof CaseStatsBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.title = data.title || data.srfmajortext;
    }
}
