import { EntityBase } from 'ibiz-core';
import { ISubProductPlan } from '../interface';

/**
 * 产品计划基类
 *
 * @export
 * @abstract
 * @class SubProductPlanBase
 * @extends {EntityBase}
 * @implements {ISubProductPlan}
 */
export abstract class SubProductPlanBase extends EntityBase implements ISubProductPlan {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof SubProductPlanBase
     */
    get srfdename(): string {
        return 'ZT_PRODUCTPLAN';
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
     * 总任务数
     */
    taskscnt?: any;
    /**
     * 工时数
     */
    estimatecnt?: any;
    /**
     * 备注
     */
    comment?: any;
    /**
     * 名称
     */
    title?: any;
    /**
     * 延迟任务数
     */
    delaytaskscnt?: any;
    /**
     * 上一次计划名称
     */
    oldtitle?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 开始日期
     */
    begin?: any;
    /**
     * 状态
     */
    statuss?: any;
    /**
     * 描述
     */
    desc?: any;
    /**
     * 结束日期
     */
    end?: any;
    /**
     * 延期
     */
    delay?: any;
    /**
     * 持续时间
     */
    duration?: any;
    /**
     * 开始日期
     */
    beginstr?: any;
    /**
     * 剩余工时
     */
    leftestimate?: any;
    /**
     * 计划模板
     */
    plantemplet?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 未完成任务数
     */
    unfinishedtaskscnt?: any;
    /**
     * 结束日期
     */
    endstr?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 计划状态
     *
     * @type {('wait' | 'doing' | 'done' | 'pause' | 'cancel' | 'closed')} wait: 未开始, doing: 进行中, done: 已完成, pause: 已暂停, cancel: 已取消, closed: 已关闭
     */
    status?: 'wait' | 'doing' | 'done' | 'pause' | 'cancel' | 'closed';
    /**
     * 是否过期
     */
    isexpired?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * 消耗工时
     */
    consumedestimate?: any;
    /**
     * 排序
     */
    order?: any;
    /**
     * 叶子节点
     */
    isleaf?: any;
    /**
     * 待定
     *
     * @type {('on')} on: 待定
     */
    future?: 'on';
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 需求数
     */
    storycnt?: any;
    /**
     * 周期
     *
     * @type {('7' | '14' | '31' | '62' | '93' | '186' | '365')} 7: 一星期, 14: 两星期, 31: 一个月, 62: 两个月, 93: 三个月, 186: 半年, 365: 一年
     */
    delta?: '7' | '14' | '31' | '62' | '93' | '186' | '365';
    /**
     * 完成任务数
     */
    finishedtaskscnt?: any;
    /**
     * bug数
     */
    bugcnt?: any;
    /**
     * 父计划名称
     */
    parentname?: any;
    /**
     * 平台/分支
     */
    branch?: any;
    /**
     * 父计划
     */
    parent?: any;
    /**
     * 产品
     */
    product?: any;
    /**
     * 产品计划编号
     */
    productplansn?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof SubProductPlanBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.title = data.title || data.srfmajortext;
    }
}
