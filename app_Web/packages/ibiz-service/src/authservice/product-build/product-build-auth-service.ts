import { ProductBuildAuthServiceBase } from './product-build-auth-service-base';


/**
 * 版本权限服务对象
 *
 * @export
 * @class ProductBuildAuthService
 * @extends {ProductBuildAuthServiceBase}
 */
export default class ProductBuildAuthService extends ProductBuildAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductBuildAuthService}
     * @memberof ProductBuildAuthService
     */
    private static basicUIServiceInstance: ProductBuildAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductBuildAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductBuildAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductBuildAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductBuildAuthService
     */
     public static getInstance(context: any): ProductBuildAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductBuildAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductBuildAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductBuildAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductBuildAuthService({context:context}));
            }
            return ProductBuildAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}