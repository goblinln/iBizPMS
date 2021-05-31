import { ProductReleaseAuthServiceBase } from './product-release-auth-service-base';


/**
 * 发布权限服务对象
 *
 * @export
 * @class ProductReleaseAuthService
 * @extends {ProductReleaseAuthServiceBase}
 */
export default class ProductReleaseAuthService extends ProductReleaseAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductReleaseAuthService}
     * @memberof ProductReleaseAuthService
     */
    private static basicUIServiceInstance: ProductReleaseAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductReleaseAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductReleaseAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductReleaseAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductReleaseAuthService
     */
     public static getInstance(context: any): ProductReleaseAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductReleaseAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductReleaseAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductReleaseAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductReleaseAuthService({context:context}));
            }
            return ProductReleaseAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}