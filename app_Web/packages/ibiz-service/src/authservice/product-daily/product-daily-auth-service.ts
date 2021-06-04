import { ProductDailyAuthServiceBase } from './product-daily-auth-service-base';


/**
 * 产品日报权限服务对象
 *
 * @export
 * @class ProductDailyAuthService
 * @extends {ProductDailyAuthServiceBase}
 */
export default class ProductDailyAuthService extends ProductDailyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductDailyAuthService}
     * @memberof ProductDailyAuthService
     */
    private static basicUIServiceInstance: ProductDailyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductDailyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductDailyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductDailyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductDailyAuthService
     */
     public static getInstance(context: any): ProductDailyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductDailyAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductDailyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductDailyAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductDailyAuthService({context:context}));
            }
            return ProductDailyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}