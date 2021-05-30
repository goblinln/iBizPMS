import { ProductBugAuthServiceBase } from './product-bug-auth-service-base';


/**
 * Bug权限服务对象
 *
 * @export
 * @class ProductBugAuthService
 * @extends {ProductBugAuthServiceBase}
 */
export default class ProductBugAuthService extends ProductBugAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductBugAuthService}
     * @memberof ProductBugAuthService
     */
    private static basicUIServiceInstance: ProductBugAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductBugAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductBugAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductBugAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductBugAuthService
     */
     public static getInstance(context: any): ProductBugAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductBugAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductBugAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductBugAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductBugAuthService({context:context}));
            }
            return ProductBugAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}