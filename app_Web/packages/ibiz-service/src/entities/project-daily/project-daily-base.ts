import { EntityBase } from 'ibiz-core';
import { IProjectDaily } from '../interface';

/**
 * 项目日报基类
 *
 * @export
 * @abstract
 * @class ProjectDailyBase
 * @extends {EntityBase}
 * @implements {IProjectDaily}
 */
export abstract class ProjectDailyBase extends EntityBase implements IProjectDaily {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof ProjectDailyBase
     */
    get srfdename(): string {
        return 'IBIZPRO_PROJECTDAILY';
    }
    get srfkey() {
        return this.ibizproprojectdailyid;
    }
    set srfkey(val: any) {
        this.ibizproprojectdailyid = val;
    }
    get srfmajortext() {
        return this.ibizproprojectdailyname;
    }
    set srfmajortext(val: any) {
        this.ibizproprojectdailyname = val;
    }
    /**
     * 项目日报名称
     */
    ibizproprojectdailyname?: any;
    /**
     * 任务
     */
    tasks?: any;
    /**
     * 开始日期
     */
    begin?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 结束日期
     */
    end?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 总工时
     */
    totalestimates?: any;
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 项目负责人
     */
    pm?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 项目日报标识
     */
    ibizproprojectdailyid?: any;
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
     * @memberof ProjectDailyBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.ibizproprojectdailyid = data.ibizproprojectdailyid || data.srfkey;
        this.ibizproprojectdailyname = data.ibizproprojectdailyname || data.srfmajortext;
    }
}
