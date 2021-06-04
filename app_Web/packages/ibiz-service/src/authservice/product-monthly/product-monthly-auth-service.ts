import { ProductMonthlyAuthServiceBase } from './product-monthly-auth-service-base';


/**
 * 产品月报权限服务对象
 *
 * @export
 * @class ProductMonthlyAuthService
 * @extends {ProductMonthlyAuthServiceBase}
 */
export default class ProductMonthlyAuthService extends ProductMonthlyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductMonthlyAuthService}
     * @memberof ProductMonthlyAuthService
     */
    private static basicUIServiceInstance: ProductMonthlyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductMonthlyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductMonthlyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductMonthlyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductMonthlyAuthService
     */
     public static getInstance(context: any): ProductMonthlyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductMonthlyAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductMonthlyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductMonthlyAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductMonthlyAuthService({context:context}));
            }
            return ProductMonthlyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}