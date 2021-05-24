import { ProductAuthServiceBase } from './product-auth-service-base';


/**
 * 产品权限服务对象
 *
 * @export
 * @class ProductAuthService
 * @extends {ProductAuthServiceBase}
 */
export default class ProductAuthService extends ProductAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductAuthService}
     * @memberof ProductAuthService
     */
    private static basicUIServiceInstance: ProductAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductAuthService
     */
     public static getInstance(context: any): ProductAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductAuthService({context:context}));
            }
            return ProductAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}