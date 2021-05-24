import { ProductSumAuthServiceBase } from './product-sum-auth-service-base';


/**
 * 产品汇总表权限服务对象
 *
 * @export
 * @class ProductSumAuthService
 * @extends {ProductSumAuthServiceBase}
 */
export default class ProductSumAuthService extends ProductSumAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductSumAuthService}
     * @memberof ProductSumAuthService
     */
    private static basicUIServiceInstance: ProductSumAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductSumAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductSumAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductSumAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductSumAuthService
     */
     public static getInstance(context: any): ProductSumAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductSumAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductSumAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductSumAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductSumAuthService({context:context}));
            }
            return ProductSumAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}