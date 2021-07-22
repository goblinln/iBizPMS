import { MonthlyAuthServiceBase } from './monthly-auth-service-base';


/**
 * 月报权限服务对象
 *
 * @export
 * @class MonthlyAuthService
 * @extends {MonthlyAuthServiceBase}
 */
export default class MonthlyAuthService extends MonthlyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {MonthlyAuthService}
     * @memberof MonthlyAuthService
     */
    private static basicUIServiceInstance: MonthlyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof MonthlyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  MonthlyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  MonthlyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof MonthlyAuthService
     */
     public static getInstance(context: any): MonthlyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new MonthlyAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!MonthlyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                MonthlyAuthService.AuthServiceMap.set(context.srfdynainstid, new MonthlyAuthService({context:context}));
            }
            return MonthlyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}