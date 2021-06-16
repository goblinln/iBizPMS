/**
 * 子任务
 *
 * @export
 * @interface SubTask
 */
export interface SubTask {

    /**
     * 由谁取消
     *
     * @returns {*}
     * @memberof SubTask
     */
    canceledby?: any;

    /**
     * 周期类型
     *
     * @returns {*}
     * @memberof SubTask
     */
    configtype?: any;

    /**
     * 项目团队成员
     *
     * @returns {*}
     * @memberof SubTask
     */
    taskteams?: any;

    /**
     * 预计剩余
     *
     * @returns {*}
     * @memberof SubTask
     */
    left?: any;

    /**
     * 是否收藏
     *
     * @returns {*}
     * @memberof SubTask
     */
    isfavorites?: any;

    /**
     * 过期日期
     *
     * @returns {*}
     * @memberof SubTask
     */
    configend?: any;

    /**
     * 是否填写描述
     *
     * @returns {*}
     * @memberof SubTask
     */
    hasdetail?: any;

    /**
     * 创建日期
     *
     * @returns {*}
     * @memberof SubTask
     */
    openeddate?: any;

    /**
     * 是否指派
     *
     * @returns {*}
     * @memberof SubTask
     */
    assign?: any;

    /**
     * 标题颜色
     *
     * @returns {*}
     * @memberof SubTask
     */
    color?: any;

    /**
     * 编号
     *
     * @returns {*}
     * @memberof SubTask
     */
    id?: any;

    /**
     * 由谁完成
     *
     * @returns {*}
     * @memberof SubTask
     */
    finishedby?: any;

    /**
     * 我的总消耗
     *
     * @returns {*}
     * @memberof SubTask
     */
    mytotaltime?: any;

    /**
     * 抄送给
     *
     * @returns {*}
     * @memberof SubTask
     */
    mailtopk?: any;

    /**
     * 完成者列表
     *
     * @returns {*}
     * @memberof SubTask
     */
    finishedlist?: any;

    /**
     * 所属模块
     *
     * @returns {*}
     * @memberof SubTask
     */
    modulename1?: any;

    /**
     * 是否子任务
     *
     * @returns {*}
     * @memberof SubTask
     */
    isleaf?: any;

    /**
     * 实际开始
     *
     * @returns {*}
     * @memberof SubTask
     */
    realstarted?: any;

    /**
     * 任务状态
     *
     * @returns {*}
     * @memberof SubTask
     */
    status1?: any;

    /**
     * 回复数量
     *
     * @returns {*}
     * @memberof SubTask
     */
    replycount?: any;

    /**
     * 开始日期
     *
     * @returns {*}
     * @memberof SubTask
     */
    configbegin?: any;

    /**
     * 最后的更新日期
     *
     * @returns {*}
     * @memberof SubTask
     */
    updatedate?: any;

    /**
     * 消息通知用户
     *
     * @returns {*}
     * @memberof SubTask
     */
    noticeusers?: any;

    /**
     * 由谁关闭
     *
     * @returns {*}
     * @memberof SubTask
     */
    closedby?: any;

    /**
     * 本次消耗
     *
     * @returns {*}
     * @memberof SubTask
     */
    currentconsumed?: any;

    /**
     * 附件
     *
     * @returns {*}
     * @memberof SubTask
     */
    files?: any;

    /**
     * 子状态
     *
     * @returns {*}
     * @memberof SubTask
     */
    substatus?: any;

    /**
     * 关闭原因
     *
     * @returns {*}
     * @memberof SubTask
     */
    closedreason?: any;

    /**
     * 任务种别
     *
     * @returns {*}
     * @memberof SubTask
     */
    taskspecies?: any;

    /**
     * 最后修改日期
     *
     * @returns {*}
     * @memberof SubTask
     */
    lastediteddate?: any;

    /**
     * 间隔天数
     *
     * @returns {*}
     * @memberof SubTask
     */
    configday?: any;

    /**
     * 指派日期
     *
     * @returns {*}
     * @memberof SubTask
     */
    assigneddate?: any;

    /**
     * 优先级
     *
     * @returns {*}
     * @memberof SubTask
     */
    pri?: any;

    /**
     * 最后修改
     *
     * @returns {*}
     * @memberof SubTask
     */
    lasteditedby?: any;

    /**
     * 关联编号
     *
     * @returns {*}
     * @memberof SubTask
     */
    idvalue?: any;

    /**
     * 任务状态
     *
     * @returns {*}
     * @memberof SubTask
     */
    status?: any;

    /**
     * 多人任务
     *
     * @returns {*}
     * @memberof SubTask
     */
    multiple?: any;

    /**
     * 任务名称
     *
     * @returns {*}
     * @memberof SubTask
     */
    name?: any;

    /**
     * 关闭时间
     *
     * @returns {*}
     * @memberof SubTask
     */
    closeddate?: any;

    /**
     * 投入成本
     *
     * @returns {*}
     * @memberof SubTask
     */
    inputcost?: any;

    /**
     * 总计耗时
     *
     * @returns {*}
     * @memberof SubTask
     */
    totaltime?: any;

    /**
     * 任务类型
     *
     * @returns {*}
     * @memberof SubTask
     */
    type?: any;

    /**
     * 指派给
     *
     * @returns {*}
     * @memberof SubTask
     */
    assignedto?: any;

    /**
     * 工时
     *
     * @returns {*}
     * @memberof SubTask
     */
    ibztaskestimates?: any;

    /**
     * 延期
     *
     * @returns {*}
     * @memberof SubTask
     */
    delay?: any;

    /**
     * 任务描述
     *
     * @returns {*}
     * @memberof SubTask
     */
    desc?: any;

    /**
     * 预计开始
     *
     * @returns {*}
     * @memberof SubTask
     */
    eststarted?: any;

    /**
     * 截止日期
     *
     * @returns {*}
     * @memberof SubTask
     */
    deadline?: any;

    /**
     * 排序
     *
     * @returns {*}
     * @memberof SubTask
     */
    statusorder?: any;

    /**
     * 联系人
     *
     * @returns {*}
     * @memberof SubTask
     */
    mailtoconact?: any;

    /**
     * 已删除
     *
     * @returns {*}
     * @memberof SubTask
     */
    deleted?: any;

    /**
     * 周期
     *
     * @returns {*}
     * @memberof SubTask
     */
    cycle?: any;

    /**
     * 抄送给
     *
     * @returns {*}
     * @memberof SubTask
     */
    mailto?: any;

    /**
     * 总计消耗
     *
     * @returns {*}
     * @memberof SubTask
     */
    consumed?: any;

    /**
     * 最初预计
     *
     * @returns {*}
     * @memberof SubTask
     */
    estimate?: any;

    /**
     * 由谁创建
     *
     * @returns {*}
     * @memberof SubTask
     */
    openedby?: any;

    /**
     * 是否完成
     *
     * @returns {*}
     * @memberof SubTask
     */
    isfinished?: any;

    /**
     * 取消时间
     *
     * @returns {*}
     * @memberof SubTask
     */
    canceleddate?: any;

    /**
     * 周期设置月
     *
     * @returns {*}
     * @memberof SubTask
     */
    configmonth?: any;

    /**
     * 备注
     *
     * @returns {*}
     * @memberof SubTask
     */
    comment?: any;

    /**
     * 持续时间
     *
     * @returns {*}
     * @memberof SubTask
     */
    duration?: any;

    /**
     * 转交给
     *
     * @returns {*}
     * @memberof SubTask
     */
    assignedtozj?: any;

    /**
     * 团队用户
     *
     * @returns {*}
     * @memberof SubTask
     */
    usernames?: any;

    /**
     * 之前消耗
     *
     * @returns {*}
     * @memberof SubTask
     */
    myconsumed?: any;

    /**
     * 周期设置周几
     *
     * @returns {*}
     * @memberof SubTask
     */
    configweek?: any;

    /**
     * 任务类型
     *
     * @returns {*}
     * @memberof SubTask
     */
    tasktype?: any;

    /**
     * 所有模块
     *
     * @returns {*}
     * @memberof SubTask
     */
    allmodules?: any;

    /**
     * 提前天数
     *
     * @returns {*}
     * @memberof SubTask
     */
    configbeforedays?: any;

    /**
     * 实际完成
     *
     * @returns {*}
     * @memberof SubTask
     */
    finisheddate?: any;

    /**
     * 进度
     *
     * @returns {*}
     * @memberof SubTask
     */
    progressrate?: any;

    /**
     * 所属模块
     *
     * @returns {*}
     * @memberof SubTask
     */
    modulename?: any;

    /**
     * 相关需求
     *
     * @returns {*}
     * @memberof SubTask
     */
    storyname?: any;

    /**
     * 模块路径
     *
     * @returns {*}
     * @memberof SubTask
     */
    path?: any;

    /**
     * 所属计划
     *
     * @returns {*}
     * @memberof SubTask
     */
    planname?: any;

    /**
     * 所属项目
     *
     * @returns {*}
     * @memberof SubTask
     */
    projectname?: any;

    /**
     * 产品
     *
     * @returns {*}
     * @memberof SubTask
     */
    product?: any;

    /**
     * 需求版本
     *
     * @returns {*}
     * @memberof SubTask
     */
    storyversion?: any;

    /**
     * 产品
     *
     * @returns {*}
     * @memberof SubTask
     */
    productname?: any;

    /**
     * 父任务
     *
     * @returns {*}
     * @memberof SubTask
     */
    parentname?: any;

    /**
     * 所属项目
     *
     * @returns {*}
     * @memberof SubTask
     */
    project?: any;

    /**
     * 编号
     *
     * @returns {*}
     * @memberof SubTask
     */
    plan?: any;

    /**
     * 模块
     *
     * @returns {*}
     * @memberof SubTask
     */
    module?: any;

    /**
     * 相关需求
     *
     * @returns {*}
     * @memberof SubTask
     */
    story?: any;

    /**
     * 父任务
     *
     * @returns {*}
     * @memberof SubTask
     */
    parent?: any;

    /**
     * 来源Bug
     *
     * @returns {*}
     * @memberof SubTask
     */
    frombug?: any;

    /**
     * 排序
     *
     * @returns {*}
     * @memberof SubTask
     */
    ordernum?: any;

    /**
     * 指派给
     *
     * @returns {*}
     * @memberof SubTask
     */
    assignedtopk?: any;
}