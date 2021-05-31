import { PSSysSFPubAuthServiceBase } from './pssys-sfpub-auth-service-base';


/**
 * 后台服务架构权限服务对象
 *
 * @export
 * @class PSSysSFPubAuthService
 * @extends {PSSysSFPubAuthServiceBase}
 */
export default class PSSysSFPubAuthService extends PSSysSFPubAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {PSSysSFPubAuthService}
     * @memberof PSSysSFPubAuthService
     */
    private static basicUIServiceInstance: PSSysSFPubAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof PSSysSFPubAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  PSSysSFPubAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  PSSysSFPubAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof PSSysSFPubAuthService
     */
     public static getInstance(context: any): PSSysSFPubAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new PSSysSFPubAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!PSSysSFPubAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                PSSysSFPubAuthService.AuthServiceMap.set(context.srfdynainstid, new PSSysSFPubAuthService({context:context}));
            }
            return PSSysSFPubAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}