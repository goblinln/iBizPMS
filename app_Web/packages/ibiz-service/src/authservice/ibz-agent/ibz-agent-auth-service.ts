import { IbzAgentAuthServiceBase } from './ibz-agent-auth-service-base';


/**
 * 代理权限服务对象
 *
 * @export
 * @class IbzAgentAuthService
 * @extends {IbzAgentAuthServiceBase}
 */
export default class IbzAgentAuthService extends IbzAgentAuthServiceBase {

    /**
     * Creates an instance of  IbzAgentAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzAgentAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}