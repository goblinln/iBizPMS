import { IbizproProductWeeklyAuthServiceBase } from './ibizpro-product-weekly-auth-service-base';


/**
 * 产品周报权限服务对象
 *
 * @export
 * @class IbizproProductWeeklyAuthService
 * @extends {IbizproProductWeeklyAuthServiceBase}
 */
export default class IbizproProductWeeklyAuthService extends IbizproProductWeeklyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbizproProductWeeklyAuthService}
     * @memberof IbizproProductWeeklyAuthService
     */
    private static basicUIServiceInstance: IbizproProductWeeklyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbizproProductWeeklyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbizproProductWeeklyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProductWeeklyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbizproProductWeeklyAuthService
     */
     public static getInstance(context: any): IbizproProductWeeklyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbizproProductWeeklyAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbizproProductWeeklyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbizproProductWeeklyAuthService.AuthServiceMap.set(context.srfdynainstid, new IbizproProductWeeklyAuthService({context:context}));
            }
            return IbizproProductWeeklyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}