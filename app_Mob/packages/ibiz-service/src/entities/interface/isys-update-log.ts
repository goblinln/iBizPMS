import { IEntityBase } from 'ibiz-core';

/**
 * 更新日志
 *
 * @export
 * @interface ISysUpdateLog
 * @extends {IEntityBase}
 */
export interface ISysUpdateLog extends IEntityBase {
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
}
