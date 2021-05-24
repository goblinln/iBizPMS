import { IbizproProductDailyAuthServiceBase } from './ibizpro-product-daily-auth-service-base';


/**
 * 产品日报权限服务对象
 *
 * @export
 * @class IbizproProductDailyAuthService
 * @extends {IbizproProductDailyAuthServiceBase}
 */
export default class IbizproProductDailyAuthService extends IbizproProductDailyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbizproProductDailyAuthService}
     * @memberof IbizproProductDailyAuthService
     */
    private static basicUIServiceInstance: IbizproProductDailyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbizproProductDailyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbizproProductDailyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProductDailyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbizproProductDailyAuthService
     */
     public static getInstance(context: any): IbizproProductDailyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbizproProductDailyAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbizproProductDailyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbizproProductDailyAuthService.AuthServiceMap.set(context.srfdynainstid, new IbizproProductDailyAuthService({context:context}));
            }
            return IbizproProductDailyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}