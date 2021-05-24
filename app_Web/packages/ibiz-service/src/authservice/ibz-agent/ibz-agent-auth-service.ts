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
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzAgentAuthService}
     * @memberof IbzAgentAuthService
     */
    private static basicUIServiceInstance: IbzAgentAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzAgentAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzAgentAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzAgentAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzAgentAuthService
     */
     public static getInstance(context: any): IbzAgentAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzAgentAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzAgentAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzAgentAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzAgentAuthService({context:context}));
            }
            return IbzAgentAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}