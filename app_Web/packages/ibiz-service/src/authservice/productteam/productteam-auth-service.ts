import { PRODUCTTEAMAuthServiceBase } from './productteam-auth-service-base';


/**
 * 产品团队权限服务对象
 *
 * @export
 * @class PRODUCTTEAMAuthService
 * @extends {PRODUCTTEAMAuthServiceBase}
 */
export default class PRODUCTTEAMAuthService extends PRODUCTTEAMAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {PRODUCTTEAMAuthService}
     * @memberof PRODUCTTEAMAuthService
     */
    private static basicUIServiceInstance: PRODUCTTEAMAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof PRODUCTTEAMAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  PRODUCTTEAMAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  PRODUCTTEAMAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof PRODUCTTEAMAuthService
     */
     public static getInstance(context: any): PRODUCTTEAMAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new PRODUCTTEAMAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!PRODUCTTEAMAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                PRODUCTTEAMAuthService.AuthServiceMap.set(context.srfdynainstid, new PRODUCTTEAMAuthService({context:context}));
            }
            return PRODUCTTEAMAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}