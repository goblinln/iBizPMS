import { EntityBase } from 'ibiz-core';
import { IProjectStory } from '../interface';

/**
 * 需求基类
 *
 * @export
 * @abstract
 * @class ProjectStoryBase
 * @extends {EntityBase}
 * @implements {IProjectStory}
 */
export abstract class ProjectStoryBase extends EntityBase implements IProjectStory {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof ProjectStoryBase
     */
    get srfdename(): string {
        return 'ZT_STORY';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.title;
    }
    set srfmajortext(val: any) {
        this.title = val;
    }
    /**
     * 指派给
     */
    assignedto?: any;
    /**
     * 所属模块名称
     */
    modulename1?: any;
    /**
     * 附件
     */
    files?: any;
    /**
     * 细分需求
     */
    childstories?: any;
    /**
     * IBIZ标识
     */
    ibiz_id?: any;
    /**
     * 所属计划
     */
    plan?: any;
    /**
     * 版本号
     */
    version?: any;
    /**
     * 指派日期
     */
    assigneddate?: any;
    /**
     * 故事点
     *
     * @type {('0' | '0.5' | '1' | '2' | '3' | '5' | '8' | '13' | '20' | '40' | '100')} 0: 0, 0.5: 0.5, 1: 1, 2: 2, 3: 3, 5: 5, 8: 8, 13: 13, 20: 20, 40: 40, 100: 100
     */
    storypoints?: '0' | '0.5' | '1' | '2' | '3' | '5' | '8' | '13' | '20' | '40' | '100';
    /**
     * 来源对象名称
     */
    sourcename?: any;
    /**
     * 需求提供时间
     */
    storyprovidedate?: any;
    /**
     * 是否子需求
     */
    isleaf?: any;
    /**
     * 优先级
     *
     * @type {(1 | 2 | 3 | 4)} 1: 1, 2: 2, 3: 3, 4: 4
     */
    pri?: 1 | 2 | 3 | 4;
    /**
     * 来源对象标识
     */
    sourceid?: any;
    /**
     * 相关需求
     */
    linkstories?: any;
    /**
     * 评审结果
     *
     * @type {('pass' | 'clarify' | 'reject')} pass: 确认通过, clarify: 有待明确, reject: 拒绝
     */
    assessresult?: 'pass' | 'clarify' | 'reject';
    /**
     * 当前状态
     *
     * @type {('draft' | 'active' | 'closed' | 'changed')} draft: 草稿, active: 激活, closed: 已关闭, changed: 已变更
     */
    status?: 'draft' | 'active' | 'closed' | 'changed';
    /**
     * 抄送给
     */
    mailtopk?: any;
    /**
     * 预计工时
     */
    estimate?: any;
    /**
     * 消息通知用户
     */
    noticeusers?: any;
    /**
     * 评审时间
     */
    revieweddate?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 需求名称
     */
    title?: any;
    /**
     * 联系人
     */
    mailtoconact?: any;
    /**
     * 来源备注
     */
    sourcenote?: any;
    /**
     * 版本号
     */
    versionc?: any;
    /**
     * 由谁评审
     */
    reviewedby?: any;
    /**
     * 子状态
     */
    substatus?: any;
    /**
     * 设置阶段者
     */
    stagedby?: any;
    /**
     * 由谁创建
     */
    openedby?: any;
    /**
     * 创建日期
     */
    openeddate?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 来源对象
     */
    ibiz_sourceobject?: any;
    /**
     * 需求来源
     *
     * @type {('customer' | 'user' | 'po' | 'market' | 'service' | 'operation' | 'support' | 'competitor' | 'partner' | 'dev' | 'tester' | 'bug' | 'forum' | 'other' | 'iBiz')} customer: 客户, user: 用户, po: 产品经理, market: 市场, service: 客服, operation: 运营, support: 技术支持, competitor: 竞争对手, partner: 合作伙伴, dev: 开发人员, tester: 测试人员, bug: Bug, forum: 论坛, other: 其它, iBiz: iBiz
     */
    source?: 'customer' | 'user' | 'po' | 'market' | 'service' | 'operation' | 'support' | 'competitor' | 'partner' | 'dev' | 'tester' | 'bug' | 'forum' | 'other' | 'iBiz';
    /**
     * 需求最晚完成时间
     */
    storylatestfinisheddate?: any;
    /**
     * 不需要评审
     *
     * @type {('0')} 0: 不需要评审
     */
    neednotreview?: '0';
    /**
     * 是否可以细分
     */
    ischild?: any;
    /**
     * 关闭原因
     *
     * @type {('done' | 'subdivided' | 'duplicate' | 'postponed' | 'willnotdo' | 'cancel' | 'bydesign')} done: 已完成, subdivided: 已细分, duplicate: 重复, postponed: 延期, willnotdo: 不做, cancel: 已取消, bydesign: 设计如此
     */
    closedreason?: 'done' | 'subdivided' | 'duplicate' | 'postponed' | 'willnotdo' | 'cancel' | 'bydesign';
    /**
     * 标题颜色
     *
     * @type {('#3da7f5' | '#75c941' | '#2dbdb2' | '#797ec9' | '#ffaf38' | '#ff4e3e')} #3da7f5: #3da7f5, #75c941: #75c941, #2dbdb2: #2dbdb2, #797ec9: #797ec9, #ffaf38: #ffaf38, #ff4e3e: #ff4e3e
     */
    color?: '#3da7f5' | '#75c941' | '#2dbdb2' | '#797ec9' | '#ffaf38' | '#ff4e3e';
    /**
     * orgid
     */
    orgid?: any;
    /**
     * 抄送给
     */
    mailto?: any;
    /**
     * 是否收藏
     */
    isfavorites?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 来源对象
     */
    sourceobject?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 关键词
     */
    keywords?: any;
    /**
     * 最后修改
     */
    lasteditedby?: any;
    /**
     * 所处阶段
     *
     * @type {('wait' | 'planned' | 'projected' | 'developing' | 'developed' | 'testing' | 'tested' | 'verified' | 'released' | 'closed')} wait: 未开始, planned: 已计划, projected: 已立项, developing: 研发中, developed: 研发完毕, testing: 测试中, tested: 测试完毕, verified: 已验收, released: 已发布, closed: 已关闭
     */
    stage?: 'wait' | 'planned' | 'projected' | 'developing' | 'developed' | 'testing' | 'tested' | 'verified' | 'released' | 'closed';
    /**
     * 项目
     */
    project?: any;
    /**
     * 关闭日期	
     */
    closeddate?: any;
    /**
     * 需求描述
     */
    spec?: any;
    /**
     * 来源对象名称
     */
    ibiz_sourcename?: any;
    /**
     * 指派给（选择）
     */
    assignedtopk?: any;
    /**
     * 备注
     */
    comment?: any;
    /**
     * acllist
     */
    acllist?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 验收标准
     */
    verify?: any;
    /**
     * 由谁关闭
     */
    closedby?: any;
    /**
     * acl
     */
    acl?: any;
    /**
     * 评审结果
     *
     * @type {('pass' | 'revert' | 'clarify' | 'reject')} pass: 确认通过, revert: 撤销变更, clarify: 有待明确, reject: 拒绝
     */
    result?: 'pass' | 'revert' | 'clarify' | 'reject';
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 需求类型
     *
     * @type {('requirement' | 'story')} requirement: 用户需求, story: 软件需求
     */
    type?: 'requirement' | 'story';
    /**
     * 最后修改日期
     */
    lastediteddate?: any;
    /**
     * 来源对象标识
     */
    ibiz_sourceid?: any;
    /**
     * 之前的版本
     */
    preversion?: any;
    /**
     * 需求提供人
     */
    storyprovider?: any;
    /**
     * MDEPTID
     */
    mdeptid?: any;
    /**
     * 模块路径
     */
    path?: any;
    /**
     * 父需求名称
     */
    parentname?: any;
    /**
     * 所属模块名称
     */
    modulename?: any;
    /**
     * 产品名称
     */
    productname?: any;
    /**
     * 平台/分支
     */
    branchname?: any;
    /**
     * 来源Bug
     */
    frombug?: any;
    /**
     * 父需求
     */
    parent?: any;
    /**
     * 所属模块
     */
    module?: any;
    /**
     * 所属产品
     */
    product?: any;
    /**
     * 重复需求ID
     */
    duplicatestory?: any;
    /**
     * 平台/分支
     */
    branch?: any;
    /**
     * 转Bug
     */
    tobug?: any;
    /**
     * 需求编号
     */
    storysn?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof ProjectStoryBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.title = data.title || data.srfmajortext;
    }
}
