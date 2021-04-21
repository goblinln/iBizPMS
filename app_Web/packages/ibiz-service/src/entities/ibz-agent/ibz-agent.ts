import { IbzAgentBase } from './ibz-agent-base';

/**
 * 代理
 *
 * @export
 * @class IbzAgent
 * @extends {IbzAgentBase}
 * @implements {IIbzAgent}
 */
export class IbzAgent extends IbzAgentBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzAgent
     */
    clone(): IbzAgent {
        return new IbzAgent(this);
    }
}
export default IbzAgent;
