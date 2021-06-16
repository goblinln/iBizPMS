/**
 * 任务
 *
 * @export
 * @interface Task
 */
export interface Task {

    /**
     * 由谁取消
     *
     * @returns {*}
     * @memberof Task
     */
    canceledby?: any;

    /**
     * 周期类型
     *
     * @returns {*}
     * @memberof Task
     */
    configtype?: any;

    /**
     * 项目团队成员
     *
     * @returns {*}
     * @memberof Task
     */
    taskteams?: any;

    /**
     * 预计剩余
     *
     * @returns {*}
     * @memberof Task
     */
    left?: any;

    /**
     * 是否收藏
     *
     * @returns {*}
     * @memberof Task
     */
    isfavorites?: any;

    /**
     * 过期日期
     *
     * @returns {*}
     * @memberof Task
     */
    configend?: any;

    /**
     * 是否填写描述
     *
     * @returns {*}
     * @memberof Task
     */
    hasdetail?: any;

    /**
     * 创建日期
     *
     * @returns {*}
     * @memberof Task
     */
    openeddate?: any;

    /**
     * 是否指派
     *
     * @returns {*}
     * @memberof Task
     */
    assign?: any;

    /**
     * 标题颜色
     *
     * @returns {*}
     * @memberof Task
     */
    color?: any;

    /**
     * 编号
     *
     * @returns {*}
     * @memberof Task
     */
    id?: any;

    /**
     * 由谁完成
     *
     * @returns {*}
     * @memberof Task
     */
    finishedby?: any;

    /**
     * 我的总消耗
     *
     * @returns {*}
     * @memberof Task
     */
    mytotaltime?: any;

    /**
     * 抄送给
     *
     * @returns {*}
     * @memberof Task
     */
    mailtopk?: any;

    /**
     * 完成者列表
     *
     * @returns {*}
     * @memberof Task
     */
    finishedlist?: any;

    /**
     * 所属模块
     *
     * @returns {*}
     * @memberof Task
     */
    modulename1?: any;

    /**
     * 是否子任务
     *
     * @returns {*}
     * @memberof Task
     */
    isleaf?: any;

    /**
     * 实际开始
     *
     * @returns {*}
     * @memberof Task
     */
    realstarted?: any;

    /**
     * 任务状态
     *
     * @returns {*}
     * @memberof Task
     */
    status1?: any;

    /**
     * 回复数量
     *
     * @returns {*}
     * @memberof Task
     */
    replycount?: any;

    /**
     * 开始日期
     *
     * @returns {*}
     * @memberof Task
     */
    configbegin?: any;

    /**
     * 最后的更新日期
     *
     * @returns {*}
     * @memberof Task
     */
    updatedate?: any;

    /**
     * 消息通知用户
     *
     * @returns {*}
     * @memberof Task
     */
    noticeusers?: any;

    /**
     * 由谁关闭
     *
     * @returns {*}
     * @memberof Task
     */
    closedby?: any;

    /**
     * 本次消耗
     *
     * @returns {*}
     * @memberof Task
     */
    currentconsumed?: any;

    /**
     * 附件
     *
     * @returns {*}
     * @memberof Task
     */
    files?: any;

    /**
     * 子状态
     *
     * @returns {*}
     * @memberof Task
     */
    substatus?: any;

    /**
     * 关闭原因
     *
     * @returns {*}
     * @memberof Task
     */
    closedreason?: any;

    /**
     * 任务种别
     *
     * @returns {*}
     * @memberof Task
     */
    taskspecies?: any;

    /**
     * 最后修改日期
     *
     * @returns {*}
     * @memberof Task
     */
    lastediteddate?: any;

    /**
     * 间隔天数
     *
     * @returns {*}
     * @memberof Task
     */
    configday?: any;

    /**
     * 指派日期
     *
     * @returns {*}
     * @memberof Task
     */
    assigneddate?: any;

    /**
     * 优先级
     *
     * @returns {*}
     * @memberof Task
     */
    pri?: any;

    /**
     * 最后修改
     *
     * @returns {*}
     * @memberof Task
     */
    lasteditedby?: any;

    /**
     * 关联编号
     *
     * @returns {*}
     * @memberof Task
     */
    idvalue?: any;

    /**
     * 任务状态
     *
     * @returns {*}
     * @memberof Task
     */
    status?: any;

    /**
     * 多人任务
     *
     * @returns {*}
     * @memberof Task
     */
    multiple?: any;

    /**
     * 任务名称
     *
     * @returns {*}
     * @memberof Task
     */
    name?: any;

    /**
     * 关闭时间
     *
     * @returns {*}
     * @memberof Task
     */
    closeddate?: any;

    /**
     * 投入成本
     *
     * @returns {*}
     * @memberof Task
     */
    inputcost?: any;

    /**
     * 总计耗时
     *
     * @returns {*}
     * @memberof Task
     */
    totaltime?: any;

    /**
     * 任务类型
     *
     * @returns {*}
     * @memberof Task
     */
    type?: any;

    /**
     * 指派给
     *
     * @returns {*}
     * @memberof Task
     */
    assignedto?: any;

    /**
     * 工时
     *
     * @returns {*}
     * @memberof Task
     */
    ibztaskestimates?: any;

    /**
     * 延期
     *
     * @returns {*}
     * @memberof Task
     */
    delay?: any;

    /**
     * 任务描述
     *
     * @returns {*}
     * @memberof Task
     */
    desc?: any;

    /**
     * 预计开始
     *
     * @returns {*}
     * @memberof Task
     */
    eststarted?: any;

    /**
     * 截止日期
     *
     * @returns {*}
     * @memberof Task
     */
    deadline?: any;

    /**
     * 排序
     *
     * @returns {*}
     * @memberof Task
     */
    statusorder?: any;

    /**
     * 联系人
     *
     * @returns {*}
     * @memberof Task
     */
    mailtoconact?: any;

    /**
     * 已删除
     *
     * @returns {*}
     * @memberof Task
     */
    deleted?: any;

    /**
     * 周期
     *
     * @returns {*}
     * @memberof Task
     */
    cycle?: any;

    /**
     * 抄送给
     *
     * @returns {*}
     * @memberof Task
     */
    mailto?: any;

    /**
     * 总计消耗
     *
     * @returns {*}
     * @memberof Task
     */
    consumed?: any;

    /**
     * 最初预计
     *
     * @returns {*}
     * @memberof Task
     */
    estimate?: any;

    /**
     * 由谁创建
     *
     * @returns {*}
     * @memberof Task
     */
    openedby?: any;

    /**
     * 是否完成
     *
     * @returns {*}
     * @memberof Task
     */
    isfinished?: any;

    /**
     * 取消时间
     *
     * @returns {*}
     * @memberof Task
     */
    canceleddate?: any;

    /**
     * 周期设置月
     *
     * @returns {*}
     * @memberof Task
     */
    configmonth?: any;

    /**
     * 备注
     *
     * @returns {*}
     * @memberof Task
     */
    comment?: any;

    /**
     * 持续时间
     *
     * @returns {*}
     * @memberof Task
     */
    duration?: any;

    /**
     * 转交给
     *
     * @returns {*}
     * @memberof Task
     */
    assignedtozj?: any;

    /**
     * 团队用户
     *
     * @returns {*}
     * @memberof Task
     */
    usernames?: any;

    /**
     * 之前消耗
     *
     * @returns {*}
     * @memberof Task
     */
    myconsumed?: any;

    /**
     * 周期设置周几
     *
     * @returns {*}
     * @memberof Task
     */
    configweek?: any;

    /**
     * 任务类型
     *
     * @returns {*}
     * @memberof Task
     */
    tasktype?: any;

    /**
     * 所有模块
     *
     * @returns {*}
     * @memberof Task
     */
    allmodules?: any;

    /**
     * 提前天数
     *
     * @returns {*}
     * @memberof Task
     */
    configbeforedays?: any;

    /**
     * 实际完成
     *
     * @returns {*}
     * @memberof Task
     */
    finisheddate?: any;

    /**
     * 进度
     *
     * @returns {*}
     * @memberof Task
     */
    progressrate?: any;

    /**
     * 所属模块
     *
     * @returns {*}
     * @memberof Task
     */
    modulename?: any;

    /**
     * 相关需求
     *
     * @returns {*}
     * @memberof Task
     */
    storyname?: any;

    /**
     * 模块路径
     *
     * @returns {*}
     * @memberof Task
     */
    path?: any;

    /**
     * 所属计划
     *
     * @returns {*}
     * @memberof Task
     */
    planname?: any;

    /**
     * 所属项目
     *
     * @returns {*}
     * @memberof Task
     */
    projectname?: any;

    /**
     * 产品
     *
     * @returns {*}
     * @memberof Task
     */
    product?: any;

    /**
     * 需求版本
     *
     * @returns {*}
     * @memberof Task
     */
    storyversion?: any;

    /**
     * 产品
     *
     * @returns {*}
     * @memberof Task
     */
    productname?: any;

    /**
     * 父任务
     *
     * @returns {*}
     * @memberof Task
     */
    parentname?: any;

    /**
     * 所属项目
     *
     * @returns {*}
     * @memberof Task
     */
    project?: any;

    /**
     * 编号
     *
     * @returns {*}
     * @memberof Task
     */
    plan?: any;

    /**
     * 模块
     *
     * @returns {*}
     * @memberof Task
     */
    module?: any;

    /**
     * 相关需求
     *
     * @returns {*}
     * @memberof Task
     */
    story?: any;

    /**
     * 父任务
     *
     * @returns {*}
     * @memberof Task
     */
    parent?: any;

    /**
     * 来源Bug
     *
     * @returns {*}
     * @memberof Task
     */
    frombug?: any;

    /**
     * 排序
     *
     * @returns {*}
     * @memberof Task
     */
    ordernum?: any;

    /**
     * 指派给
     *
     * @returns {*}
     * @memberof Task
     */
    assignedtopk?: any;
}