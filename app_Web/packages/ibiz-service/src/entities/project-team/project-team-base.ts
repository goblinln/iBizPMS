import { EntityBase } from 'ibiz-core';
import { IProjectTeam } from '../interface';

/**
 * 项目团队基类
 *
 * @export
 * @abstract
 * @class ProjectTeamBase
 * @extends {EntityBase}
 * @implements {IProjectTeam}
 */
export abstract class ProjectTeamBase extends EntityBase implements IProjectTeam {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof ProjectTeamBase
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
     * 角色
     */
    role?: any;
    /**
     * 受限用户
     *
     * @type {('yes' | 'no')} yes: 是, no: 否
     */
    limited?: 'yes' | 'no';
    /**
     * 总计可用
     */
    total?: any;
    /**
     * 用户
     */
    username?: any;
    /**
     * 可用工日
     */
    days?: any;
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
     * 项目编号
     */
    root?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof ProjectTeamBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.account = data.account || data.srfmajortext;
    }
}
