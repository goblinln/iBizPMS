import { ProductProjectAuthServiceBase } from './product-project-auth-service-base';


/**
 * 项目产品权限服务对象
 *
 * @export
 * @class ProductProjectAuthService
 * @extends {ProductProjectAuthServiceBase}
 */
export default class ProductProjectAuthService extends ProductProjectAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductProjectAuthService}
     * @memberof ProductProjectAuthService
     */
    private static basicUIServiceInstance: ProductProjectAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductProjectAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductProjectAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductProjectAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductProjectAuthService
     */
     public static getInstance(context: any): ProductProjectAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductProjectAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductProjectAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductProjectAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductProjectAuthService({context:context}));
            }
            return ProductProjectAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}