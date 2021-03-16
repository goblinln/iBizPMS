import { EntityBase } from 'ibiz-core';
import { IIbzAgent } from '../interface';

/**
 * 代理基类
 *
 * @export
 * @abstract
 * @class IbzAgentBase
 * @extends {EntityBase}
 * @implements {IIbzAgent}
 */
export abstract class IbzAgentBase extends EntityBase implements IIbzAgent {
    /**
     * 实体名称
     *
     * @readonly
     * @type {string}
     * @memberof IbzAgentBase
     */
    get srfdename(): string {
        return 'IBZ_AGENT';
    }
    get srfkey() {
        return this.ibzagentid;
    }
    set srfkey(val: any) {
        this.ibzagentid = val;
    }
    get srfmajortext() {
        return this.ibzagentname;
    }
    set srfmajortext(val: any) {
        this.ibzagentname = val;
    }
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

    /**
     * 重置实体数据
     *
     * @private
     * @param {*} [data={}]
     * @memberof IbzAgentBase
     */
    reset(data: any = {}): void {
        super.reset(data);
        this.ibzagentid = data.ibzagentid || data.srfkey;
        this.ibzagentname = data.ibzagentname || data.srfmajortext;
    }
}
