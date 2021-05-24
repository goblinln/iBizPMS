import { BugAuthServiceBase } from './bug-auth-service-base';


/**
 * Bug权限服务对象
 *
 * @export
 * @class BugAuthService
 * @extends {BugAuthServiceBase}
 */
export default class BugAuthService extends BugAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {BugAuthService}
     * @memberof BugAuthService
     */
    private static basicUIServiceInstance: BugAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof BugAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  BugAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  BugAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof BugAuthService
     */
     public static getInstance(context: any): BugAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new BugAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!BugAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                BugAuthService.AuthServiceMap.set(context.srfdynainstid, new BugAuthService({context:context}));
            }
            return BugAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}