import { EntityBase } from 'ibiz-core';
import { ITaskestimatestats } from '../interface';

/**
 * 任务工时统计基类
 *
 * @export
 * @abstract
 * @class TaskestimatestatsBase
 * @extends {EntityBase}
 * @implements {ITaskestimatestats}
 */
export abstract class TaskestimatestatsBase extends EntityBase implements ITaskestimatestats {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof TaskestimatestatsBase
     */
    get srfdename(): string {
        return 'TASKESTIMATESTATS';
    }
    get srfkey() {
        return this.id;
    }
    set srfkey(val: any) {
        this.id = val;
    }
    // taskestimatestats 实体未设置主文本属性
    /**
     * 日期
     */
    date?: any;
    /**
     * 消耗的工时
     */
    consumed?: any;
    /**
     * 用户
     */
    account?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 项目名称
     */
    name?: any;
    /**
     * 任务数
     */
    taskcnt?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof TaskestimatestatsBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        // taskestimatestats 实体未设置主文本属性
    }
}
