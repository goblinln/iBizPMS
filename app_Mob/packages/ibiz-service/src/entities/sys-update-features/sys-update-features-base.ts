import { EntityBase } from 'ibiz-core';
import { ISysUpdateFeatures } from '../interface';

/**
 * 系统更新功能基类
 *
 * @export
 * @abstract
 * @class SysUpdateFeaturesBase
 * @extends {EntityBase}
 * @implements {ISysUpdateFeatures}
 */
export abstract class SysUpdateFeaturesBase extends EntityBase implements ISysUpdateFeatures {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof SysUpdateFeaturesBase
     */
    get srfdename(): string {
        return 'SYS_UPDATE_FEATURES';
    }
    get srfkey() {
        return this.sysupdatefeaturesid;
    }
    set srfkey(val: any) {
        this.sysupdatefeaturesid = val;
    }
    get srfmajortext() {
        return this.sysupdatefeaturesname;
    }
    set srfmajortext(val: any) {
        this.sysupdatefeaturesname = val;
    }
    /**
     * 系统更新功能名称
     */
    sysupdatefeaturesname?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 更新类型
     *
     * @type {('10' | '20')} 10: 功能增强, 20: 优化
     */
    type?: '10' | '20';
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 更新功能
     */
    upfeatures?: any;
    /**
     * 系统更新功能标识
     */
    sysupdatefeaturesid?: any;
    /**
     * 展示顺序
     */
    displayorder?: any;
    /**
     * 功能描述
     */
    featuresdesc?: any;
    /**
     * 所属更新
     */
    sysupdatelogname?: any;
    /**
     * 系统更新日志标识
     */
    sysupdatelogid?: any;
    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof SysUpdateFeaturesBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.sysupdatefeaturesid = data.sysupdatefeaturesid || data.srfkey;
        this.sysupdatefeaturesname = data.sysupdatefeaturesname || data.srfmajortext;
    }
}
