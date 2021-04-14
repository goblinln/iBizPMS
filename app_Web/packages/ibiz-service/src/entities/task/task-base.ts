import { EntityBase } from 'ibiz-core';
import { ITask } from '../interface';

/**
 * 任务基类
 *
 * @export
 * @abstract
 * @class TaskBase
 * @extends {EntityBase}
 * @implements {ITask}
 */
export abstract class TaskBase extends EntityBase implements ITask {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof TaskBase
     */
    get srfdename(): string {
        return 'ZT_TASK';
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
     * 由谁取消
     */
    canceledby?: any;
    /**
     * 周期类型
     *
     * @type {('day' | 'week' | 'month')} day: 天, week: 周, month: 月度
     */
    configtype?: 'day' | 'week' | 'month';
    /**
     * 项目团队成员
     */
    taskteams?: any;
    /**
     * 预计剩余
     */
    left?: any;
    /**
     * 是否收藏
     */
    isfavorites?: any;
    /**
     * 过期日期
     */
    configend?: any;
    /**
     * 是否填写描述
     */
    hasdetail?: any;
    /**
     * 创建日期
     */
    openeddate?: any;
    /**
     * 是否指派
     */
    assign?: any;
    /**
     * 标题颜色
     *
     * @type {('#3da7f5' | '#75c941' | '#2dbdb2' | '#797ec9' | '#ffaf38' | '#ff4e3e')} #3da7f5: #3da7f5, #75c941: #75c941, #2dbdb2: #2dbdb2, #797ec9: #797ec9, #ffaf38: #ffaf38, #ff4e3e: #ff4e3e
     */
    color?: '#3da7f5' | '#75c941' | '#2dbdb2' | '#797ec9' | '#ffaf38' | '#ff4e3e';
    /**
     * 编号
     */
    id?: any;
    /**
     * 由谁完成
     */
    finishedby?: any;
    /**
     * 我的总消耗
     */
    mytotaltime?: any;
    /**
     * 抄送给
     */
    mailtopk?: any;
    /**
     * 完成者列表
     */
    finishedlist?: any;
    /**
     * 所属模块
     */
    modulename1?: any;
    /**
     * 是否子任务
     */
    isleaf?: any;
    /**
     * 实际开始
     */
    realstarted?: any;
    /**
     * 任务状态
     *
     * @type {('wait' | 'doing' | 'done' | 'pause' | 'cancel' | 'closed' | 'storychange')} wait: 未开始, doing: 进行中, done: 已完成, pause: 已暂停, cancel: 已取消, closed: 已关闭, storychange: 需求变更
     */
    status1?: 'wait' | 'doing' | 'done' | 'pause' | 'cancel' | 'closed' | 'storychange';
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 回复数量
     */
    replycount?: any;
    /**
     * 开始日期
     */
    configbegin?: any;
    /**
     * 最后的更新日期
     */
    updatedate?: any;
    /**
     * 消息通知用户
     */
    noticeusers?: any;
    /**
     * 由谁关闭
     */
    closedby?: any;
    /**
     * 本次消耗
     */
    currentconsumed?: any;
    /**
     * 附件
     */
    files?: any;
    /**
     * 子状态
     */
    substatus?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 关闭原因
     *
     * @type {('done' | 'cancel')} done: 已完成, cancel: 已取消
     */
    closedreason?: 'done' | 'cancel';
    /**
     * 任务种别
     *
     * @type {('plan' | 'cycle' | 'temp')} plan: 计划任务, cycle: 周期任务, temp: 临时任务
     */
    taskspecies?: 'plan' | 'cycle' | 'temp';
    /**
     * 最后修改日期
     */
    lastediteddate?: any;
    /**
     * 间隔天数
     */
    configday?: any;
    /**
     * 指派日期
     */
    assigneddate?: any;
    /**
     * 优先级
     *
     * @type {(1 | 2 | 3 | 4)} 1: 1, 2: 2, 3: 3, 4: 4
     */
    pri?: 1 | 2 | 3 | 4;
    /**
     * 最后修改
     */
    lasteditedby?: any;
    /**
     * 关联编号
     */
    idvalue?: any;
    /**
     * 任务状态
     *
     * @type {('wait' | 'doing' | 'done' | 'pause' | 'cancel' | 'closed')} wait: 未开始, doing: 进行中, done: 已完成, pause: 已暂停, cancel: 已取消, closed: 已关闭
     */
    status?: 'wait' | 'doing' | 'done' | 'pause' | 'cancel' | 'closed';
    /**
     * 多人任务
     */
    multiple?: any;
    /**
     * 任务名称
     */
    name?: any;
    /**
     * 关闭时间
     */
    closeddate?: any;
    /**
     * 投入成本
     */
    inputcost?: any;
    /**
     * 总计耗时
     */
    totaltime?: any;
    /**
     * 任务类型
     *
     * @type {('design' | 'devel' | 'test' | 'study' | 'discuss' | 'ui' | 'affair' | 'serve' | 'misc')} design: 设计, devel: 开发, test: 测试, study: 研究, discuss: 讨论, ui: 界面, affair: 事务, serve: 服务, misc: 其他
     */
    type?: 'design' | 'devel' | 'test' | 'study' | 'discuss' | 'ui' | 'affair' | 'serve' | 'misc';
    /**
     * 指派给
     */
    assignedto?: any;
    /**
     * 工时
     */
    ibztaskestimates?: any;
    /**
     * 延期
     */
    delay?: any;
    /**
     * 任务描述
     */
    desc?: any;
    /**
     * 预计开始
     */
    eststarted?: any;
    /**
     * 截止日期
     */
    deadline?: any;
    /**
     * 排序
     */
    statusorder?: any;
    /**
     * 联系人
     */
    mailtoconact?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 周期
     */
    cycle?: any;
    /**
     * 抄送给
     */
    mailto?: any;
    /**
     * 总计消耗
     */
    consumed?: any;
    /**
     * 最初预计
     */
    estimate?: any;
    /**
     * 由谁创建
     */
    openedby?: any;
    /**
     * 是否完成
     */
    isfinished?: any;
    /**
     * 取消时间
     */
    canceleddate?: any;
    /**
     * 周期设置月
     *
     * @type {('1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | '10' | '11' | '12' | '13' | '14' | '15' | '16' | '17' | '18' | '19' | '20' | '21' | '22' | '23' | '24' | '25' | '26' | '27' | '28' | '29' | '30' | '31')} 1: 1号, 2: 2号, 3: 3号, 4: 4号, 5: 5号, 6: 6号, 7: 7号, 8: 8号, 9: 9号, 10: 10号, 11: 11号, 12: 12号, 13: 13号, 14: 14号, 15: 15号, 16: 16号, 17: 17号, 18: 18号, 19: 19号, 20: 20号, 21: 21号, 22: 22号, 23: 23号, 24: 24号, 25: 25号, 26: 26号, 27: 27号, 28: 28号, 29: 29号, 30: 30号, 31: 31号
     */
    configmonth?: '1' | '2' | '3' | '4' | '5' | '6' | '7' | '8' | '9' | '10' | '11' | '12' | '13' | '14' | '15' | '16' | '17' | '18' | '19' | '20' | '21' | '22' | '23' | '24' | '25' | '26' | '27' | '28' | '29' | '30' | '31';
    /**
     * 备注
     */
    comment?: any;
    /**
     * 持续时间
     */
    duration?: any;
    /**
     * 转交给
     */
    assignedtozj?: any;
    /**
     * 团队用户
     */
    usernames?: any;
    /**
     * 之前消耗
     */
    myconsumed?: any;
    /**
     * 周期设置周几
     *
     * @type {('2' | '3' | '4' | '5' | '6' | '7' | '1')} 2: 星期一, 3: 星期二, 4: 星期三, 5: 星期四, 6: 星期五, 7: 星期六, 1: 星期日
     */
    configweek?: '2' | '3' | '4' | '5' | '6' | '7' | '1';
    /**
     * 任务类型
     */
    tasktype?: any;
    /**
     * 所有模块
     *
     * @type {('1' | '0')} 1: 是, 0: 否
     */
    allmodules?: '1' | '0';
    /**
     * 提前天数
     */
    configbeforedays?: any;
    /**
     * 实际完成
     */
    finisheddate?: any;
    /**
     * 进度
     */
    progressrate?: any;
    /**
     * 所属模块
     */
    modulename?: any;
    /**
     * 相关需求
     */
    storyname?: any;
    /**
     * 模块路径
     */
    path?: any;
    /**
     * 所属计划
     */
    planname?: any;
    /**
     * 所属项目
     */
    projectname?: any;
    /**
     * 产品
     */
    product?: any;
    /**
     * 需求版本
     */
    storyversion?: any;
    /**
     * 产品
     */
    productname?: any;
    /**
     * 父任务
     */
    parentname?: any;
    /**
     * 所属项目
     */
    project?: any;
    /**
     * 编号
     */
    plan?: any;
    /**
     * 模块
     */
    module?: any;
    /**
     * 相关需求
     */
    story?: any;
    /**
     * 父任务
     */
    parent?: any;
    /**
     * 来源Bug
     */
    frombug?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof TaskBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
