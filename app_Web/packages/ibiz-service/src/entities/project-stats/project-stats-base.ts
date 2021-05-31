import { EntityBase } from 'ibiz-core';
import { IProjectStats } from '../interface';

/**
 * 项目统计基类
 *
 * @export
 * @abstract
 * @class ProjectStatsBase
 * @extends {EntityBase}
 * @implements {IProjectStats}
 */
export abstract class ProjectStatsBase extends EntityBase implements IProjectStats {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof ProjectStatsBase
     */
    get srfdename(): string {
        return 'IBZ_PROJECTSTATS';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.name;
    }
    set srfmajortext(val: any) {
        this.name = val;
    }
    /**
     * 标准规范
     */
    standard?: any;
    /**
     * 工时类型
     */
    type?: any;
    /**
     * 空需求
     */
    emptystory?: any;
    /**
     * 草稿需求
     */
    draftstory?: any;
    /**
     * 截止日期
     */
    end?: any;
    /**
     * 已发布阶段需求数
     */
    releasedstagestorycnt?: any;
    /**
     * 重要Bug数
     */
    importantbugcnt?: any;
    /**
     * 已取消任务数
     */
    canceltaskcnt?: any;
    /**
     * 开始时间
     */
    begin?: any;
    /**
     * 状态
     *
     * @type {('wait' | 'doing' | 'suspended' | 'closed')} wait: 未开始, doing: 进行中, suspended: 已挂起, closed: 已关闭
     */
    status?: 'wait' | 'doing' | 'suspended' | 'closed';
    /**
     * 严重Bug比率
     */
    seriousbugproportion?: any;
    /**
     * 进行中任务数
     */
    doingtaskcnt?: any;
    /**
     * 未完成任务总数
     */
    undonetaskcnt?: any;
    /**
     * 服务类型任务
     */
    servetaskcnt?: any;
    /**
     * 已完成任务数
     */
    donetaskcnt?: any;
    /**
     * 总工时
     */
    totalwh?: any;
    /**
     * 未关闭需求总数
     */
    unclosedstorycnt?: any;
    /**
     * 其他类型任务
     */
    misctaskcnt?: any;
    /**
     * 剩余需求数
     */
    leftstorycnt?: any;
    /**
     * 性能问题
     */
    performance?: any;
    /**
     * 空阶段需求数
     */
    emptystagestorycnt?: any;
    /**
     * 未开始阶段需求数
     */
    waitstagestorycnt?: any;
    /**
     * 研发中阶段需求数
     */
    developingstagestorycnt?: any;
    /**
     * 是否置顶
     */
    istop?: any;
    /**
     * 完成需求数
     */
    completestorycnt?: any;
    /**
     * 测试类型任务
     */
    testtaskcnt?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 需求总数
     */
    storycnt?: any;
    /**
     * 已立项阶段需求数
     */
    projectedstagestorycnt?: any;
    /**
     * 讨论类型任务
     */
    discusstaskcnt?: any;
    /**
     * 完成任务数
     */
    completetaskcnt?: any;
    /**
     * 已计划阶段需求数
     */
    plannedstagestorycnt?: any;
    /**
     * 测试完毕阶段需求数
     */
    testedstagestorycnt?: any;
    /**
     * 项目名称
     */
    name?: any;
    /**
     * 任务总数
     */
    taskcnt?: any;
    /**
     * 任务预计剩余总工时
     */
    totalleft?: any;
    /**
     * 工期
     */
    timescale?: any;
    /**
     * 激活需求
     */
    activestory?: any;
    /**
     * 项目排序
     */
    order1?: any;
    /**
     * 未解决Bug总数
     */
    activebugcnt?: any;
    /**
     * 设计缺陷
     */
    designdefect?: any;
    /**
     * 未开始任务数
     */
    waittaskcnt?: any;
    /**
     * 已暂停任务数
     */
    pausetaskcnt?: any;
    /**
     * 已关闭阶段需求数
     */
    closedstagestorycnt?: any;
    /**
     * 任务最初预计总工时
     */
    totalestimate?: any;
    /**
     * 已验收阶段需求数
     */
    verifiedstagestorycnt?: any;
    /**
     * 安装部署
     */
    install?: any;
    /**
     * 项目编号
     */
    id?: any;
    /**
     * 设计类型任务
     */
    designtaskcnt?: any;
    /**
     * 安全相关
     */
    security?: any;
    /**
     * 其他
     */
    others?: any;
    /**
     * Bug总数
     */
    bugcnt?: any;
    /**
     * 未关闭Bug总数
     */
    unclosedbugcnt?: any;
    /**
     * 昨日完成任务数
     */
    yesterdayctaskcnt?: any;
    /**
     * 工时
     */
    time?: any;
    /**
     * 测试脚本
     */
    automation?: any;
    /**
     * 未确认Bug总数
     */
    unconfirmedbugcnt?: any;
    /**
     * 研发完毕阶段需求数
     */
    developedstagestorycnt?: any;
    /**
     * 测试中阶段需求数
     */
    testingstagestorycnt?: any;
    /**
     * 已结束任务总数
     */
    finishtaskcnt?: any;
    /**
     * 配置相关
     */
    config?: any;
    /**
     * 任务消耗总工时
     */
    totalconsumed?: any;
    /**
     * 事务类型任务
     */
    affairtaskcnt?: any;
    /**
     * 项目消耗总工时
     */
    projecttotalconsumed?: any;
    /**
     * 界面类型任务
     */
    uitaskcnt?: any;
    /**
     * Bug/完成需求
     */
    bugstory?: any;
    /**
     * 已关闭需求
     */
    closedstory?: any;
    /**
     * Bug/完成任务
     */
    bugtask?: any;
    /**
     * 已发布需求数
     */
    releasedstorycnt?: any;
    /**
     * 研究类型任务
     */
    studytaskcnt?: any;
    /**
     * 已关闭任务数
     */
    closedtaskcnt?: any;
    /**
     * 代码错误
     */
    codeerror?: any;
    /**
     * 关闭需求总数
     */
    closedstorycnt?: any;
    /**
     * 已解决Bug总数
     */
    finishbugcnt?: any;
    /**
     * 已变更需求
     */
    changedstory?: any;
    /**
     * 开发类型任务
     */
    develtaskcnt?: any;
    /**
     * 人数
     */
    membercnt?: any;
    /**
     * 进度
     */
    progress?: any;
    /**
     * 昨天解决Bug数
     */
    yesterdayrbugcnt?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof ProjectStatsBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
