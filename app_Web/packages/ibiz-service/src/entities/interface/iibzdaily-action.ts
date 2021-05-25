import { IEntityBase } from 'ibiz-core';

/**
 * 日报日志
 *
 * @export
 * @interface IIBZDailyAction
 * @extends {IEntityBase}
 */
export interface IIBZDailyAction extends IEntityBase {
    /**
     * 本月
     */
    thismonth?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 昨天
     */
    yesterday?: any;
    /**
     * 附加值
     */
    extra?: any;
    /**
     * 系统日志编号
     */
    actionsn?: any;
    /**
     * 文件
     */
    files?: any;
    /**
     * 上月
     */
    lastmonth?: any;
    /**
     * 当前用户
     */
    isactorss?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 本周
     */
    thisweek?: any;
    /**
     * 今天
     */
    today?: any;
    /**
     * 对象类型
     *
     * @type {('product' | 'story' | 'productplan' | 'release' | 'project' | 'task' | 'build' | 'bug' | 'case' | 'caseresult' | 'stepresult' | 'testtask' | 'user' | 'doc' | 'doclib' | 'todo' | 'branch' | 'module' | 'testsuite' | 'caselib' | 'testreport' | 'entry' | 'webhook' | 'daily' | 'weekly' | 'monthly' | 'reportly')} product: 产品, story: 需求, productplan: 计划, release: 发布, project: 项目, task: 任务, build: 版本, bug: Bug, case: 用例, caseresult: 用例结果, stepresult: 用例步骤, testtask: 测试单, user: 用户, doc: 文档, doclib: 文档库, todo: 待办, branch: 分支, module: 模块, testsuite: 套件, caselib: 用例库, testreport: 报告, entry: 应用, webhook: Webhook, daily: 日报, weekly: 周报, monthly: 月报, reportly: 汇报
     */
    objecttype?: 'product' | 'story' | 'productplan' | 'release' | 'project' | 'task' | 'build' | 'bug' | 'case' | 'caseresult' | 'stepresult' | 'testtask' | 'user' | 'doc' | 'doclib' | 'todo' | 'branch' | 'module' | 'testsuite' | 'caselib' | 'testreport' | 'entry' | 'webhook' | 'daily' | 'weekly' | 'monthly' | 'reportly';
    /**
     * 显示日期
     */
    date1?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * id
     */
    id?: any;
    /**
     * 消息通知用户
     */
    noticeusers?: any;
    /**
     * 备注
     */
    comment?: any;
    /**
     * 已读
     *
     * @type {('0' | '1')} 0: 0, 1: 1
     */
    read?: '0' | '1';
    /**
     * 动作
     *
     * @type {('created' | 'opened' | 'changed' | 'edited' | 'assigned' | 'closed' | 'deleted' | 'deletedfile' | 'editfile' | 'erased' | 'undeleted' | 'hidden' | 'commented' | 'activated' | 'blocked' | 'resolved' | 'reviewed' | 'moved' | 'confirmed' | 'bugconfirmed' | 'tostory' | 'frombug' | 'fromlib' | 'totask' | 'svncommited' | 'gitcommited' | 'linked2plan' | 'unlinkedfromplan' | 'changestatus' | 'marked' | 'linked2project' | 'unlinkedfromproject' | 'unlinkedfrombuild' | 'linked2release' | 'unlinkedfromrelease' | 'linkrelatedbug' | 'unlinkrelatedbug' | 'linkrelatedcase' | 'unlinkrelatedcase' | 'linkrelatedstory' | 'unlinkrelatedstory' | 'subdividestory' | 'unlinkchildstory' | 'started' | 'restarted' | 'recordestimate' | 'editestimate' | 'canceled' | 'finished' | 'paused' | 'verified' | 'delayed' | 'suspended' | 'login' | 'logout' | 'deleteestimate' | 'linked2build' | 'linked2bug' | 'linkchildtask' | 'unlinkchildrentask' | 'linkparenttask' | 'unlinkparenttask' | 'batchcreate' | 'createchildren' | 'managed' | 'deletechildrentask' | 'createchildrenstory' | 'linkchildstory' | 'unlinkchildrenstory' | 'linkparentstory' | 'unlinkparentstory' | 'deletechildrenstory' | 'submit' | 'read' | 'remind' | 'unlinkedfrombranch')} created: 创建, opened: 创建, changed: 变更了, edited: 编辑了, assigned: 指派了, closed: 关闭了, deleted: 删除了, deletedfile: 删除附件, editfile: 编辑附件, erased: 删除了, undeleted: 还原了, hidden: 隐藏了, commented: 评论了, activated: 激活了, blocked: 阻塞了, resolved: 解决了, reviewed: 评审了, moved: 移动了, confirmed: 确认了需求, bugconfirmed: 确认了, tostory: 转需求, frombug: 转需求, fromlib: 从用例库导入, totask: 转任务, svncommited: 提交代码, gitcommited: 提交代码, linked2plan: 关联计划, unlinkedfromplan: 移除计划, changestatus: 修改状态, marked: 编辑了, linked2project: 关联项目, unlinkedfromproject: 移除项目, unlinkedfrombuild: 移除版本, linked2release: 关联发布, unlinkedfromrelease: 移除发布, linkrelatedbug: 关联了相关Bug, unlinkrelatedbug: 移除了相关Bug, linkrelatedcase: 关联了相关用例, unlinkrelatedcase: 移除了相关用例, linkrelatedstory: 关联了相关需求, unlinkrelatedstory: 移除了相关需求, subdividestory: 细分了需求, unlinkchildstory: 移除了细分需求, started: 开始了, restarted: 继续了, recordestimate: 记录了工时, editestimate: 编辑了工时, canceled: 取消了, finished: 完成了, paused: 暂停了, verified: 验收了, delayed: 延期, suspended: 挂起, login: 登录系统, logout: 退出登录, deleteestimate: 删除了工时, linked2build: 关联了, linked2bug: 关联了, linkchildtask: 关联子任务, unlinkchildrentask: 取消关联子任务, linkparenttask: 关联到父任务, unlinkparenttask: 从父任务取消关联, batchcreate: 批量创建任务, createchildren: 创建子任务, managed: 维护, deletechildrentask: 删除子任务, createchildrenstory: 创建子需求, linkchildstory: 关联子需求, unlinkchildrenstory: 取消关联子需求, linkparentstory: 关联到父需求, unlinkparentstory: 从父需求取消关联, deletechildrenstory: 删除子需求, submit: 提交, read: 已读了, remind: 提醒, unlinkedfrombranch: 移除关联平台
     */
    action?: 'created' | 'opened' | 'changed' | 'edited' | 'assigned' | 'closed' | 'deleted' | 'deletedfile' | 'editfile' | 'erased' | 'undeleted' | 'hidden' | 'commented' | 'activated' | 'blocked' | 'resolved' | 'reviewed' | 'moved' | 'confirmed' | 'bugconfirmed' | 'tostory' | 'frombug' | 'fromlib' | 'totask' | 'svncommited' | 'gitcommited' | 'linked2plan' | 'unlinkedfromplan' | 'changestatus' | 'marked' | 'linked2project' | 'unlinkedfromproject' | 'unlinkedfrombuild' | 'linked2release' | 'unlinkedfromrelease' | 'linkrelatedbug' | 'unlinkrelatedbug' | 'linkrelatedcase' | 'unlinkrelatedcase' | 'linkrelatedstory' | 'unlinkrelatedstory' | 'subdividestory' | 'unlinkchildstory' | 'started' | 'restarted' | 'recordestimate' | 'editestimate' | 'canceled' | 'finished' | 'paused' | 'verified' | 'delayed' | 'suspended' | 'login' | 'logout' | 'deleteestimate' | 'linked2build' | 'linked2bug' | 'linkchildtask' | 'unlinkchildrentask' | 'linkparenttask' | 'unlinkparenttask' | 'batchcreate' | 'createchildren' | 'managed' | 'deletechildrentask' | 'createchildrenstory' | 'linkchildstory' | 'unlinkchildrenstory' | 'linkparentstory' | 'unlinkparentstory' | 'deletechildrenstory' | 'submit' | 'read' | 'remind' | 'unlinkedfrombranch';
    /**
     * 日期
     */
    date?: any;
    /**
     * 产品
     */
    product?: any;
    /**
     * 备注
     */
    lastcomment?: any;
    /**
     * 操作方式
     *
     * @type {('story_closed_willnotdo' | 'story_closed_cancel' | 'story_closed_done' | 'story_closed_subdivided' | 'story_closed_postponed' | 'story_closed_bydesign' | 'story_closed_duplicate' | 'bug_resolved_willnotfix' | 'bug_resolved_external' | 'bug_resolved_fixed' | 'bug_resolved_postponed' | 'bug_resolved_notrepro' | 'bug_resolved_bydesign' | 'bug_resolved_tostory' | 'bug_resolved_duplicate' | 'story_reviewed_reject' | 'story_reviewed_revert' | 'story_reviewed_clarify' | 'story_reviewed_pass' | ' release_changestatus_terminate' | ' release_changestatus_normal')} story_closed_willnotdo: 不做, story_closed_cancel: 已取消, story_closed_done: 已完成, story_closed_subdivided: 已细分, story_closed_postponed: 延期, story_closed_bydesign: 设计如此, story_closed_duplicate: 重复, bug_resolved_willnotfix: 不予解决, bug_resolved_external: 外部原因, bug_resolved_fixed: 已解决, bug_resolved_postponed: 延期处理, bug_resolved_notrepro: 无法重现, bug_resolved_bydesign: 设计如此, bug_resolved_tostory: 转为需求, bug_resolved_duplicate: 重复Bug, story_reviewed_reject: 拒绝, story_reviewed_revert: 撤销变更, story_reviewed_clarify: 有待明确, story_reviewed_pass: 确认通过,  release_changestatus_terminate: 停止维护,  release_changestatus_normal: 正常
     */
    actionmanner?: 'story_closed_willnotdo' | 'story_closed_cancel' | 'story_closed_done' | 'story_closed_subdivided' | 'story_closed_postponed' | 'story_closed_bydesign' | 'story_closed_duplicate' | 'bug_resolved_willnotfix' | 'bug_resolved_external' | 'bug_resolved_fixed' | 'bug_resolved_postponed' | 'bug_resolved_notrepro' | 'bug_resolved_bydesign' | 'bug_resolved_tostory' | 'bug_resolved_duplicate' | 'story_reviewed_reject' | 'story_reviewed_revert' | 'story_reviewed_clarify' | 'story_reviewed_pass' | ' release_changestatus_terminate' | ' release_changestatus_normal';
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 上周
     */
    lastweek?: any;
    /**
     * 操作者
     */
    actor?: any;
    /**
     * 日报标识
     */
    objectid?: any;
    /**
     * 项目
     */
    project?: any;
}
