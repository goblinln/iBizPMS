import { EntityBase } from 'ibiz-core';
import { IDynaDashboard } from '../interface';

/**
 * 动态数据看板基类
 *
 * @export
 * @abstract
 * @class DynaDashboardBase
 * @extends {EntityBase}
 * @implements {IDynaDashboard}
 */
export abstract class DynaDashboardBase extends EntityBase implements IDynaDashboard {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof DynaDashboardBase
     */
    get srfdename(): string {
        return 'DYNADASHBOARD';
    }
    get srfkey() {
        return this.dynadashboardid;
    }
    set srfkey(val: any) {
        this.dynadashboardid = val;
    }
    get srfmajortext() {
        return this.dynadashboardname;
    }
    set srfmajortext(val: any) {
        this.dynadashboardname = val;
    }
    /**
     * 用户标识
     */
    userid?: any;
    /**
     * 动态数据看板标识
     */
    dynadashboardid?: any;
    /**
     * 动态数据看板名称
     */
    dynadashboardname?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 模型标识
     */
    modelid?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 应用标识
     */
    appid?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 模型
     */
    model?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof DynaDashboardBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.dynadashboardid = data.dynadashboardid || data.srfkey;
        this.dynadashboardname = data.dynadashboardname || data.srfmajortext;
    }
}
