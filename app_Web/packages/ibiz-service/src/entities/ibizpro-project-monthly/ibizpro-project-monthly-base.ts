import { EntityBase } from 'ibiz-core';
import { IIbizproProjectMonthly } from '../interface';

/**
 * 项目月报基类
 *
 * @export
 * @abstract
 * @class IbizproProjectMonthlyBase
 * @extends {EntityBase}
 * @implements {IIbizproProjectMonthly}
 */
export abstract class IbizproProjectMonthlyBase extends EntityBase implements IIbizproProjectMonthly {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IbizproProjectMonthlyBase
     */
    get srfdename(): string {
        return 'IBIZPRO_PROJECTMONTHLY';
    }
    get srfkey() {
        return this.ibizproprojectmonthlyid;
    }
    set srfkey(val: any) {
        this.ibizproprojectmonthlyid = val;
    }
    get srfmajortext() {
        return this.ibizproprojectmonthlyname;
    }
    set srfmajortext(val: any) {
        this.ibizproprojectmonthlyname = val;
    }
    /**
     * 项目月报名称
     */
    ibizproprojectmonthlyname?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 年月
     */
    yearmonth?: any;
    /**
     * 总工时
     */
    totalestimates?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 任务
     */
    tasks?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 项目月报标识
     */
    ibizproprojectmonthlyid?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 项目负责人
     */
    pm?: any;
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
     * @memberof IbizproProjectMonthlyBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.ibizproprojectmonthlyid = data.ibizproprojectmonthlyid || data.srfkey;
        this.ibizproprojectmonthlyname = data.ibizproprojectmonthlyname || data.srfmajortext;
    }
}
