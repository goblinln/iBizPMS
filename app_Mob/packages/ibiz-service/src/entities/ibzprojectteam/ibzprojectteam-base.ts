import { EntityBase } from 'ibiz-core';
import { IIBZPROJECTTEAM } from '../interface';

/**
 * 项目团队基类
 *
 * @export
 * @abstract
 * @class IBZPROJECTTEAMBase
 * @extends {EntityBase}
 * @implements {IIBZPROJECTTEAM}
 */
export abstract class IBZPROJECTTEAMBase extends EntityBase implements IIBZPROJECTTEAM {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IBZPROJECTTEAMBase
     */
    get srfdename(): string {
        return 'IBZ_PROJECTTEAM';
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
     * 归属部门名
     */
    deptname?: any;
    /**
     * 角色
     */
    role?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 受限用户
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    limited?: 'yes' | 'no';
    /**
     * 归属部门
     */
    dept?: any;
    /**
     * 总计可用
     */
    total?: any;
    /**
     * 用户
     */
    username?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 可用工日
     */
    days?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 退场时间
     */
    exitdate?: any;
    /**
     * 团队类型
     *
     * @type {('project' | 'task' | 'product')} project: 项目团队, task: 任务团队, product: 产品团队
     */
    type?: 'project' | 'task' | 'product';
    /**
     * 排序
     */
    order?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 归属组织名
     */
    orgname?: any;
    /**
     * 总计消耗
     */
    consumed?: any;
    /**
     * 用户
     */
    account?: any;
    /**
     * 最初预计
     */
    estimate?: any;
    /**
     * 加盟日
     */
    join?: any;
    /**
     * 可用工时/天
     */
    hours?: any;
    /**
     * 任务数
     */
    taskcnt?: any;
    /**
     * 预计剩余
     */
    left?: any;
    /**
     * 项目经理
     */
    pm?: any;
    /**
     * 所属项目
     */
    projectname?: any;
    /**
     * 项目编号
     */
    root?: any;
    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IBZPROJECTTEAMBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.account = data.account || data.srfmajortext;
    }
}
