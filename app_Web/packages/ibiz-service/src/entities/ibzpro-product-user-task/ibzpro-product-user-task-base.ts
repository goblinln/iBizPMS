import { EntityBase } from 'ibiz-core';
import { IIbzproProductUserTask } from '../interface';

/**
 * 产品汇报用户任务基类
 *
 * @export
 * @abstract
 * @class IbzproProductUserTaskBase
 * @extends {EntityBase}
 * @implements {IIbzproProductUserTask}
 */
export abstract class IbzproProductUserTaskBase extends EntityBase implements IIbzproProductUserTask {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IbzproProductUserTaskBase
     */
    get srfdename(): string {
        return 'IBIZPRO_PRODUCTUSERTASK';
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
     * 任务类型
     *
     * @type {('design' | 'devel' | 'test' | 'study' | 'discuss' | 'ui' | 'affair' | 'serve' | 'misc')} design: 设计, devel: 开发, test: 测试, study: 研究, discuss: 讨论, ui: 界面, affair: 事务, serve: 服务, misc: 其他
     */
    tasktype?: 'design' | 'devel' | 'test' | 'study' | 'discuss' | 'ui' | 'affair' | 'serve' | 'misc';
    /**
     * 用户
     */
    account?: any;
    /**
     * 总计消耗
     */
    consumed?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 任务名称
     */
    taskname?: any;
    /**
     * 进度
     */
    progressrate?: any;
    /**
     * 预计开始
     */
    eststarted?: any;
    /**
     * 日期
     */
    date?: any;
    /**
     * 延期天数
     */
    delaydays?: any;
    /**
     * 任务
     */
    task?: any;
    /**
     * 预计剩余
     */
    left?: any;
    /**
     * 截止日期
     */
    deadline?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IbzproProductUserTaskBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.id = data.id || data.srfmajortext;
    }
}
