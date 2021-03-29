import { IEntityBase } from 'ibiz-core';

/**
 * 产品统计
 *
 * @export
 * @interface IProductStats
 * @extends {IEntityBase}
 */
export interface IProductStats extends IEntityBase {
    /**
     * 产品代号
     */
    code?: any;
    /**
     * 产品编号
     */
    id?: any;
    /**
     * 已完成的需求数
     */
    finishedstorycnt?: any;
    /**
     * 已发布需求数
     */
    releasedstorycnt?: any;
    /**
     * 严重bug比
     */
    importantbugpercent?: any;
    /**
     * 未完成关联项目数
     */
    undoneresprojectcnt?: any;
    /**
     * 指派给我的Bug数
     */
    assigntomebugcnt?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 是否置顶
     */
    istop?: any;
    /**
     * 关联项目数
     */
    resprojectcnt?: any;
    /**
     * 所有Bug数
     */
    bugcnt?: any;
    /**
     * 已消耗工时
     */
    haveconsumed?: any;
    /**
     * 产品类型
     *
     * @type {('normal' | 'branch' | 'platform')} normal: 正常, branch: 多分支, platform: 多平台
     */
    type?: 'normal' | 'branch' | 'platform';
    /**
     * 未开始需求数
     */
    waitstorycnt?: any;
    /**
     * 需求所提bug数
     */
    bugstory?: any;
    /**
     * 需求总数
     */
    storycnt?: any;
    /**
     * 产品名称
     */
    name?: any;
    /**
     * 重要的Bug数
     */
    importantbugcnt?: any;
    /**
     * 昨天关闭Bug数
     */
    yesterdayclosedbugcnt?: any;
    /**
     * 状态
     *
     * @type {('normal' | 'closed')} normal: 正常, closed: 结束
     */
    status?: 'normal' | 'closed';
    /**
     * 昨天解决Bug数
     */
    yesterdayresolvedbugcnt?: any;
    /**
     * 开发中需求数
     */
    developingstorycnt?: any;
    /**
     * 激活需求数
     */
    activestorycnt?: any;
    /**
     * 未关闭Bug数
     */
    notclosedbugcnt?: any;
    /**
     * 维护中发布数
     */
    normalreleasecnt?: any;
    /**
     * 产品排序
     */
    order1?: any;
    /**
     * 当前项目
     */
    currproject?: any;
    /**
     * 测试中需求数
     */
    testingstorycnt?: any;
    /**
     * 计划总数
     */
    productplancnt?: any;
    /**
     * 发布总数
     */
    releasecnt?: any;
    /**
     * 昨天确认Bug数
     */
    yesterdayconfirmbugcnt?: any;
    /**
     * 已延期
     */
    postponedprojectcnt?: any;
    /**
     * 未过期计划数
     */
    unendproductplancnt?: any;
    /**
     * 解决Bug数
     */
    resolvedbugcnt?: any;
    /**
     * 已计划需求数
     */
    plannedstorycnt?: any;
    /**
     * 未确认Bug数
     */
    unconfirmbugcnt?: any;
    /**
     * 未解决Bug数
     */
    activebugcnt?: any;
}
