import { EntityBase } from 'ibiz-core';
import { IIbzTaskestimate } from '../interface';

/**
 * 任务预计基类
 *
 * @export
 * @abstract
 * @class IbzTaskestimateBase
 * @extends {EntityBase}
 * @implements {IIbzTaskestimate}
 */
export abstract class IbzTaskestimateBase extends EntityBase implements IIbzTaskestimate {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IbzTaskestimateBase
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
        return this.account;
    }
    set srfmajortext(val: any) {
        this.account = val;
    }
    /**
     * 最初预计
     */
    estimate?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 用户
     */
    username?: any;
    /**
     * 加盟日
     */
    join?: any;
    /**
     * 可用工时/天
     */
    hours?: any;
    /**
     * 用户
     */
    account?: any;
    /**
     * 排序
     */
    order?: any;
    /**
     * 预计剩余
     */
    left?: any;
    /**
     * 归属部门名
     */
    deptname?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 总计消耗
     */
    consumed?: any;
    /**
     * 受限用户
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    limited?: 'yes' | 'no';
    /**
     * 角色
     */
    role?: any;
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 可用工日
     */
    days?: any;
    /**
     * 总计可用
     */
    total?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 团队类型
     *
     * @type {('project' | 'task' | 'product')} project: 项目团队, task: 任务团队, product: 产品团队
     */
    type?: 'project' | 'task' | 'product';
    /**
     * 编号
     */
    root?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IbzTaskestimateBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.account = data.account || data.srfmajortext;
    }
}
