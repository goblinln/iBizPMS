import { IEntityBase } from 'ibiz-core';

/**
 * 测试版本
 *
 * @export
 * @interface ITestTask
 * @extends {IEntityBase}
 */
export interface ITestTask extends IEntityBase {
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
}
