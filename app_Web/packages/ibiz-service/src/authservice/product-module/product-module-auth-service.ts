import { ProductModuleAuthServiceBase } from './product-module-auth-service-base';


/**
 * 需求模块权限服务对象
 *
 * @export
 * @class ProductModuleAuthService
 * @extends {ProductModuleAuthServiceBase}
 */
export default class ProductModuleAuthService extends ProductModuleAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductModuleAuthService}
     * @memberof ProductModuleAuthService
     */
    private static basicUIServiceInstance: ProductModuleAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductModuleAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductModuleAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductModuleAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductModuleAuthService
     */
     public static getInstance(context: any): ProductModuleAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductModuleAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductModuleAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductModuleAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductModuleAuthService({context:context}));
            }
            return ProductModuleAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}