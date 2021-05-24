import { IbizproProductMonthlyAuthServiceBase } from './ibizpro-product-monthly-auth-service-base';


/**
 * 产品月报权限服务对象
 *
 * @export
 * @class IbizproProductMonthlyAuthService
 * @extends {IbizproProductMonthlyAuthServiceBase}
 */
export default class IbizproProductMonthlyAuthService extends IbizproProductMonthlyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbizproProductMonthlyAuthService}
     * @memberof IbizproProductMonthlyAuthService
     */
    private static basicUIServiceInstance: IbizproProductMonthlyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbizproProductMonthlyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbizproProductMonthlyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProductMonthlyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbizproProductMonthlyAuthService
     */
     public static getInstance(context: any): IbizproProductMonthlyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbizproProductMonthlyAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbizproProductMonthlyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbizproProductMonthlyAuthService.AuthServiceMap.set(context.srfdynainstid, new IbizproProductMonthlyAuthService({context:context}));
            }
            return IbizproProductMonthlyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}