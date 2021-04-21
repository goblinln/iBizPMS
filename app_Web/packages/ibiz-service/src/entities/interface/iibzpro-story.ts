import { IEntityBase } from 'ibiz-core';

/**
 * 需求
 *
 * @export
 * @interface IIBZProStory
 * @extends {IEntityBase}
 */
export interface IIBZProStory extends IEntityBase {
    /**
     * 版本号
     */
    version?: any;
    /**
     * 来源备注
     */
    sourcenote?: any;
    /**
     * 抄送给
     */
    mailto?: any;
    /**
     * 设置阶段者
     */
    stagedby?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 来源对象名称
     */
    ibiz_sourcename?: any;
    /**
     * 优先级
     *
     * @type {(1 | 2 | 3 | 4)} 1: 1, 2: 2, 3: 3, 4: 4
     */
    pri?: 1 | 2 | 3 | 4;
    /**
     * 需求阶段
     *
     * @type {('wait' | 'planned' | 'projected' | 'developing' | 'developed' | 'testing' | 'tested' | 'verified' | 'released' | 'closed')} wait: 未开始, planned: 已计划, projected: 已立项, developing: 研发中, developed: 研发完毕, testing: 测试中, tested: 测试完毕, verified: 已验收, released: 已发布, closed: 已关闭
     */
    stage?: 'wait' | 'planned' | 'projected' | 'developing' | 'developed' | 'testing' | 'tested' | 'verified' | 'released' | 'closed';
    /**
     * 由谁关闭
     */
    closedby?: any;
    /**
     * 需求描述
     */
    spec?: any;
    /**
     * 关闭日期	
     */
    closeddate?: any;
    /**
     * 相关需求
     */
    linkstories?: any;
    /**
     * 需求类型
     *
     * @type {('requirement' | 'story')} requirement: 用户需求, story: 软件需求
     */
    type?: 'requirement' | 'story';
    /**
     * 重复需求
     */
    duplicatestory?: any;
    /**
     * 平台
     */
    branch?: any;
    /**
     * 关键词
     */
    keywords?: any;
    /**
     * 需求细分
     */
    childstories?: any;
    /**
     * 创建日期
     */
    openeddate?: any;
    /**
     * 转Bug
     */
    tobug?: any;
    /**
     * 备注
     */
    comment?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 关闭原因
     */
    closedreason?: any;
    /**
     * 项目
     */
    project?: any;
    /**
     * 来源Bug
     */
    frombug?: any;
    /**
     * 最后修改者
     */
    lasteditedby?: any;
    /**
     * 指派日期
     */
    assigneddate?: any;
    /**
     * 子状态
     */
    substatus?: any;
    /**
     * 来源对象标识
     */
    ibiz_sourceid?: any;
    /**
     * 需求名称
     */
    title?: any;
    /**
     * 最后修改日期
     */
    lastediteddate?: any;
    /**
     * 来源对象
     *
     * @type {('sourcenote__dataentity')} sourcenote__dataentity: 实体
     */
    ibiz_sourceobject?: 'sourcenote__dataentity';
    /**
     * IBIZ标识
     */
    ibizid?: any;
    /**
     * 验收标准
     */
    verify?: any;
    /**
     * 由谁评审
     */
    reviewedby?: any;
    /**
     * 指派给
     */
    assignedto?: any;
    /**
     * 需求来源
     *
     * @type {('customer' | 'user' | 'po' | 'market' | 'service' | 'operation' | 'support' | 'competitor' | 'partner' | 'dev' | 'tester' | 'bug' | 'forum' | 'other' | 'iBiz')} customer: 客户, user: 用户, po: 产品经理, market: 市场, service: 客服, operation: 运营, support: 技术支持, competitor: 竞争对手, partner: 合作伙伴, dev: 开发人员, tester: 测试人员, bug: Bug, forum: 论坛, other: 其它, iBiz: iBiz
     */
    source?: 'customer' | 'user' | 'po' | 'market' | 'service' | 'operation' | 'support' | 'competitor' | 'partner' | 'dev' | 'tester' | 'bug' | 'forum' | 'other' | 'iBiz';
    /**
     * 预计工时
     */
    estimate?: any;
    /**
     * 由谁创建
     */
    openedby?: any;
    /**
     * 评审时间
     */
    revieweddate?: any;
    /**
     * 颜色
     *
     * @type {('#3da7f5' | '#75c941' | '#2dbdb2' | '#797ec9' | '#ffaf38' | '#ff4e3e')} #3da7f5: #3da7f5, #75c941: #75c941, #2dbdb2: #2dbdb2, #797ec9: #797ec9, #ffaf38: #ffaf38, #ff4e3e: #ff4e3e
     */
    color?: '#3da7f5' | '#75c941' | '#2dbdb2' | '#797ec9' | '#ffaf38' | '#ff4e3e';
    /**
     * 状态
     *
     * @type {('draft' | 'active' | 'closed' | 'changed')} draft: 草稿, active: 激活, closed: 已关闭, changed: 已变更
     */
    status?: 'draft' | 'active' | 'closed' | 'changed';
    /**
     * 编号
     */
    product?: any;
    /**
     * id
     */
    module?: any;
}
