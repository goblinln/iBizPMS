import { EntityBase } from 'ibiz-core';
import { ITaskEstimate } from '../interface';

/**
 * 任务预计基类
 *
 * @export
 * @abstract
 * @class TaskEstimateBase
 * @extends {EntityBase}
 * @implements {ITaskEstimate}
 */
export abstract class TaskEstimateBase extends EntityBase implements ITaskEstimate {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof TaskEstimateBase
     */
    get srfdename(): string {
        return 'ZT_TASKESTIMATE';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    get srfmajortext() {
        return this.id;
    }
    set srfmajortext(val: any) {
        this.id = val;
    }
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 月（显示）
     */
    monthname?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 年
     */
    year?: any;
    /**
     * 用户
     */
    account?: any;
    /**
     * 预计剩余
     */
    left?: any;
    /**
     * 总计消耗
     */
    consumed?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 评估成本
     */
    evaluationcost?: any;
    /**
     * 月（排序）
     */
    monthorder?: any;
    /**
     * 附件
     */
    files?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 评估状态
     */
    evaluationstatus?: any;
    /**
     * 年（显示）
     */
    yearname?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 评估工时
     */
    evaluationtime?: any;
    /**
     * 投入成本
     */
    inputcost?: any;
    /**
     * 日期
     */
    dates?: any;
    /**
     * 月
     */
    month?: any;
    /**
     * work
     */
    work?: any;
    /**
     * 评估说明
     */
    evaluationdesc?: any;
    /**
     * 任务种别
     *
     * @type {('plan' | 'cycle' | 'temp')} plan: 计划任务, cycle: 周期任务, temp: 临时任务
     */
    taskspecies?: 'plan' | 'cycle' | 'temp';
    /**
     * 任务名称
     */
    taskname?: any;
    /**
     * 所属项目
     */
    projectname?: any;
    /**
     * 任务类型
     *
     * @type {('design' | 'devel' | 'test' | 'study' | 'discuss' | 'ui' | 'affair' | 'serve' | 'misc')} design: 设计, devel: 开发, test: 测试, study: 研究, discuss: 讨论, ui: 界面, affair: 事务, serve: 服务, misc: 其他
     */
    type?: 'design' | 'devel' | 'test' | 'study' | 'discuss' | 'ui' | 'affair' | 'serve' | 'misc';
    /**
     * 任务删除标识
     */
    deleted?: any;
    /**
     * 项目
     */
    project?: any;
    /**
     * 任务
     */
    task?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 归属部门名
     */
    deptname?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof TaskEstimateBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.id = data.id || data.srfmajortext;
    }
}
