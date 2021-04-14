import { IEntityBase } from 'ibiz-core';

/**
 * 代理
 *
 * @export
 * @interface IIbzAgent
 * @extends {IEntityBase}
 */
export interface IIbzAgent extends IEntityBase {
    /**
     * 更新人
     */
    updateman?: any;
    /**
     * 创建人姓名
     */
    createmanname?: any;
    /**
     * 代理结束日期
     */
    agentend?: any;
    /**
     * 代理标识
     */
    ibzagentid?: any;
    /**
     * 建立人
     */
    createman?: any;
    /**
     * 更新时间
     */
    updatedate?: any;
    /**
     * 代理用户
     */
    agentuser?: any;
    /**
     * 建立时间
     */
    createdate?: any;
    /**
     * 代理开始日期
     */
    agentbegin?: any;
    /**
     * 代理名称
     */
    ibzagentname?: any;
}
