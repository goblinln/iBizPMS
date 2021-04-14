import { EntityBase } from 'ibiz-core';
import { ITestTask } from '../interface';

/**
 * 测试版本基类
 *
 * @export
 * @abstract
 * @class TestTaskBase
 * @extends {EntityBase}
 * @implements {ITestTask}
 */
export abstract class TestTaskBase extends EntityBase implements ITestTask {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof TestTaskBase
     */
    get srfdename(): string {
        return 'ZT_TESTTASK';
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
     * 归属部门
     */
    dept?: any;
    /**
     * 结束日期
     */
    end?: any;
    /**
     * 开始日期
     */
    begin?: any;
    /**
     * 负责人（选择）
     */
    ownerpk?: any;
    /**
     * 抄送给
     */
    mailto?: any;
    /**
     * 用例数
     */
    casecnt?: any;
    /**
     * 抄送给
     */
    mailtopk?: any;
    /**
     * 由谁更新
     */
    updateby?: any;
    /**
     * 归属组织
     */
    org?: any;
    /**
     * 优先级
     *
     * @type {(1 | 2 | 3 | 4)} 1: 1, 2: 2, 3: 3, 4: 4
     */
    pri?: 1 | 2 | 3 | 4;
    /**
     * 备注
     */
    comment?: any;
    /**
     * 由谁创建
     */
    createby?: any;
    /**
     * 子状态
     */
    substatus?: any;
    /**
     * report
     */
    report?: any;
    /**
     * 描述
     */
    desc?: any;
    /**
     * 编号
     */
    id?: any;
    /**
     * 当前状态
     *
     * @type {('wait' | 'doing' | 'done' | 'blocked')} wait: 未开始, doing: 进行中, done: 已完成, blocked: 被阻塞
     */
    status?: 'wait' | 'doing' | 'done' | 'blocked';
    /**
     * 联系人
     */
    mailtoconact?: any;
    /**
     * 负责人
     */
    owner?: any;
    /**
     * 已删除
     */
    deleted?: any;
    /**
     * auto
     */
    auto?: any;
    /**
     * 名称
     */
    name?: any;
    /**
     * 版本
     */
    buildname?: any;
    /**
     * 产品
     */
    productname?: any;
    /**
     * 项目
     */
    projecttname?: any;
    /**
     * 所属产品
     */
    product?: any;
    /**
     * 版本
     */
    build?: any;
    /**
     * 所属项目
     */
    project?: any;

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof TestTaskBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.id = data.id || data.srfkey;
        this.name = data.name || data.srfmajortext;
    }
}
