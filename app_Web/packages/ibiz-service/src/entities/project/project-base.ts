import { EntityBase } from 'ibiz-core';
import { IProject } from '../interface';

/**
 * 项目基类
 *
 * @export
 * @abstract
 * @class ProjectBase
 * @extends {EntityBase}
 * @implements {IProject}
 */
export abstract class ProjectBase extends EntityBase implements IProject {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof ProjectBase
     */
    get srfdename(): string {
        return 'ZT_PROJECT';
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
     * 当前系统版本
     */
    openedversion?: any;
    /**
     * 开始时间
     */
    begin?: any;
    /**
     * 可用工时/天
     */
    hours?: any;
    /**
     * 项目立项信息
     */
    pmseeprojectinfo?: any;
    /**
     * 访问控制
     *
     * @type {('open' | 'private' | 'custom')} open: 默认设置(有项目视图权限，即可访问), private: 私有项目(只有项目团队成员才能访问), custom: 自定义白名单(团队成员和白名单的成员可以访问)
     */
    acl?: 'open' | 'private' | 'custom';
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 任务消耗总工时
     */
    totalconsumed?: any;
    /**
     * 关联产品
     */
    products?: any;
    /**
     * 已完成任务数
     */
    ycompletetaskcnt?: any;
    /**
     * 关联计划
     */
    plans?: any;
    /**
     * 项目描述
     */
    desc?: any;
    /**
     * 临时任务数
     */
    temptaskcnt?: any;
    /**
     * 文档数量
     */
    doclibcnt?: any;
    /**
     * 我完成任务数
     */
    mycompletetaskcnt?: any;
    /**
     * 是否置顶
     */
    istop?: any;
    /**
     * 未完成任务数
     */
    uncompletetaskcnt?: any;
    /**
     * 团队成员总数
     */
    teamcnt?: any;
    /**
     * 项目负责人
     */
    pm?: any;
    /**
     * 选择部门
     */
    dept?: any;
    /**
     * 项目编号
     */
    id?: any;
    /**
     * 项目名称
     */
    name?: any;
    /**
     * 子状态
     */
    substatus?: any;
    /**
     * 角色
     */
    role?: any;
    /**
     * 项目排序
     */
    order?: any;
    /**
     * Bug总数
     */
    bugcnt?: any;
    /**
     * 发布负责人
     */
    rd?: any;
    /**
     * 复制团队
     */
    managemembers?: any;
    /**
     * 进行中任务数
     */
    ystarttaskcnt?: any;
    /**
     * 分组白名单
     */
    whitelist?: any;
    /**
     * 移动端图片
     */
    mobimage?: any;
    /**
     * 总工时
     */
    totalwh?: any;
    /**
     * 项目团队成员
     */
    projectteams?: any;
    /**
     * 可用工时
     */
    totalhours?: any;
    /**
     * 优先级
     *
     * @type {('1' | '2' | '3' | '4')} 1: 1, 2: 2, 3: 3, 4: 4
     */
    pri?: '1' | '2' | '3' | '4';
    /**
     * 结束日期
     */
    end?: any;
    /**
     * 取消日期
     */
    canceleddate?: any;
    /**
     * 计划任务数
     */
    plantaskcnt?: any;
    /**
     * 加盟日
     */
    join?: any;
    /**
     * 任务最初预计总工时
     */
    totalestimate?: any;
    /**
     * 部门标识
     */
    mdeptid?: any;
    /**
     * 任务预计剩余总工时
     */
    totalleft?: any;
    /**
     * 关联数据数组
     */
    srfarray?: any;
    /**
     * 项目代号
     */
    code?: any;
    /**
     * 时间段
     *
     * @type {('7' | '14' | '31' | '62' | '93' | '186' | '365')} 7: 一星期, 14: 两星期, 31: 一个月, 62: 两个月, 93: 三个月, 186: 半年, 365: 一年
     */
    period?: '7' | '14' | '31' | '62' | '93' | '186' | '365';
    /**
     * 关闭任务数
     */
    closetaskcnt?: any;
    /**
     * 关联产品平台集合
     */
    branchs?: any;
    /**
     * catID
     */
    catid?: any;
    /**
     * 组织标识
     */
    orgid?: any;
    /**
     * 未开始任务数
     */
    unstarttaskcnt?: any;
    /**
     * 项目团队相关成员
     */
    accounts?: any;
    /**
     * 消息通知用户
     */
    noticeusers?: any;
    /**
     * statge
     *
     * @type {('1' | '2' | '3' | '4' | '5')} 1: 1, 2: 2, 3: 3, 4: 4, 5: 5
     */
    statge?: '1' | '2' | '3' | '4' | '5';
    /**
     * 取消任务数
     */
    canceltaskcnt?: any;
    /**
     * 任务总数
     */
    taskcnt?: any;
    /**
     * 所有任务数
     */
    alltaskcnt?: any;
    /**
     * 支持项目汇报
     *
     * @type {('1' | '0')} 1: 是, 0: 否
     */
    supproreport?: '1' | '0';
    /**
     * 由谁取消
     */
    canceledby?: any;
    /**
     * isCat
     *
     * @type {('1' | '0')} 1: 是, 0: 否
     */
    iscat?: '1' | '0';
    /**
     * 创建日期
     */
    openeddate?: any;
    /**
     * 未关闭任务数
     */
    unclosetaskcnt?: any;
    /**
     * 需求变更数
     */
    storychangecnt?: any;
    /**
     * 由谁关闭
     */
    closedby?: any;
    /**
     * 项目类型
     *
     * @type {('sprint' | 'waterfall' | 'ops')} sprint: 短期项目, waterfall: 长期项目, ops: 运维项目
     */
    type?: 'sprint' | 'waterfall' | 'ops';
    /**
     * 版本总数
     */
    buildcnt?: any;
    /**
     * 项目团队成员
     */
    account?: any;
    /**
     * 产品负责人
     */
    po?: any;
    /**
     * 指派给我任务数
     */
    asstomytaskcnt?: any;
    /**
     * 项目排序
     */
    order1?: any;
    /**
     * 项目状态
     *
     * @type {('wait' | 'doing' | 'suspended' | 'closed')} wait: 未开始, doing: 进行中, suspended: 已挂起, closed: 已关闭
     */
    status?: 'wait' | 'doing' | 'suspended' | 'closed';
    /**
     * 更多任务数
     */
    moretaskcnt?: any;
    /**
     * 可用工作日
     */
    days?: any;
    /**
     * 周期任务数
     */
    cycletaskcnt?: any;
    /**
     * 团队名称
     */
    team?: any;
    /**
     * 关闭日期
     */
    closeddate?: any;
    /**
     * 备注
     */
    comment?: any;
    /**
     * 由谁创建
     */
    openedby?: any;
    /**
     * 需求总数
     */
    storycnt?: any;
    /**
     * 测试负责人
     */
    qd?: any;
    /**
     * parent
     */
    parentname?: any;
    /**
     * 父项目
     */
    parent?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof ProjectBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
