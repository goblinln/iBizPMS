import { IBZWEEKLYAuthServiceBase } from './ibzweekly-auth-service-base';


/**
 * 周报权限服务对象
 *
 * @export
 * @class IBZWEEKLYAuthService
 * @extends {IBZWEEKLYAuthServiceBase}
 */
export default class IBZWEEKLYAuthService extends IBZWEEKLYAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZWEEKLYAuthService}
     * @memberof IBZWEEKLYAuthService
     */
    private static basicUIServiceInstance: IBZWEEKLYAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZWEEKLYAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZWEEKLYAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZWEEKLYAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZWEEKLYAuthService
     */
     public static getInstance(context: any): IBZWEEKLYAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZWEEKLYAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZWEEKLYAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZWEEKLYAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZWEEKLYAuthService({context:context}));
            }
            return IBZWEEKLYAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}