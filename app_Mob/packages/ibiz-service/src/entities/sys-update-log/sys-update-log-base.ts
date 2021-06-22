import { EntityBase } from 'ibiz-core';
import { ISysUpdateLog } from '../interface';

/**
 * 更新日志基类
 *
 * @export
 * @abstract
 * @class SysUpdateLogBase
 * @extends {EntityBase}
 * @implements {ISysUpdateLog}
 */
export abstract class SysUpdateLogBase extends EntityBase implements ISysUpdateLog {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof SysUpdateLogBase
     */
    get srfdename(): string {
        return 'SYS_UPDATE_LOG';
    }
    get srfkey() {
        return this.sysupdatelogid;
    }
    set srfkey(val: any) {
        this.sysupdatelogid = val;
    }
    get srfmajortext() {
        return this.sysupdatelogname;
    }
    set srfmajortext(val: any) {
        this.sysupdatelogname = val;
    }
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 系统更新日志标识
     */
    sysupdatelogid?: any;
    /**
     * 更新平台
     *
     * @type {('PC' | 'MOB')} PC: PC, MOB: MOB
     */
    updatebranch?: 'PC' | 'MOB';
    /**
     * 更新说明
     */
    updesc?: any;
    /**
     * 更新名称
     */
    sysupdatelogname?: any;
    /**
     * 最新更新
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    latestupdate?: 'yes' | 'no';
    /**
     * 更新日期
     */
    update?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof SysUpdateLogBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.sysupdatelogid = data.sysupdatelogid || data.srfkey;
        this.sysupdatelogname = data.sysupdatelogname || data.srfmajortext;
    }
}
