import { EntityBase } from 'ibiz-core';
import { IIbizproProjectWeekly } from '../interface';

/**
 * 项目周报基类
 *
 * @export
 * @abstract
 * @class IbizproProjectWeeklyBase
 * @extends {EntityBase}
 * @implements {IIbizproProjectWeekly}
 */
export abstract class IbizproProjectWeeklyBase extends EntityBase implements IIbizproProjectWeekly {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IbizproProjectWeeklyBase
     */
    get srfdename(): string {
        return 'IBZPRO_PROJECTWEEKLY';
    }
    get srfkey() {
        return this.projectweeklyid;
    }
    set srfkey(val: any) {
        this.projectweeklyid = val;
    }
    get srfmajortext() {
        return this.projectweeklyname;
    }
    set srfmajortext(val: any) {
        this.projectweeklyname = val;
    }
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 年
     */
    year?: any;
    /**
     * 结束统计
     */
    enddatestats?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 任务
     */
    tasks?: any;
    /**
     * 项目周报名称
     */
    projectweeklyname?: any;
    /**
     * 周
     */
    week?: any;
    /**
     * 项目周报标识
     */
    projectweeklyid?: any;
    /**
     * 项目负责人
     */
    pm?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 总工时
     */
    totalestimates?: any;
    /**
     * 开始统计
     */
    begindatestats?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 月
     */
    month?: any;
    /**
     * 项目名称
     */
    projectname?: any;
    /**
     * 项目编号
     */
    project?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IbizproProjectWeeklyBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.projectweeklyid = data.projectweeklyid || data.srfkey;
        this.projectweeklyname = data.projectweeklyname || data.srfmajortext;
    }
}
