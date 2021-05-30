import { IEntityBase } from 'ibiz-core';

/**
 * 测试用例统计
 *
 * @export
 * @interface ICaseStats
 * @extends {IEntityBase}
 */
export interface ICaseStats extends IEntityBase {
    /**
     * 通过用例数
     */
    passcase?: any;
    /**
     * 阻塞用例数
     */
    blockedcase?: any;
    /**
     * 总执行数
     */
    totalruncase?: any;
    /**
     * 失败用例数
     */
    failcase?: any;
    /**
     * 用例标题
     */
    title?: any;
    /**
     * 总用例数
     */
    totalcase?: any;
    /**
     * 用例通过率
     */
    passrate?: any;
    /**
     * 用例编号
     */
    id?: any;
    /**
     * 模块名称
     */
    modulename?: any;
    /**
     * 产品
     */
    product?: any;
    /**
     * 模块
     */
    module?: any;
}
