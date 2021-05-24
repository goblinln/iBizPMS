import { ProductLifeAuthServiceBase } from './product-life-auth-service-base';


/**
 * 产品生命周期权限服务对象
 *
 * @export
 * @class ProductLifeAuthService
 * @extends {ProductLifeAuthServiceBase}
 */
export default class ProductLifeAuthService extends ProductLifeAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductLifeAuthService}
     * @memberof ProductLifeAuthService
     */
    private static basicUIServiceInstance: ProductLifeAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductLifeAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductLifeAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductLifeAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductLifeAuthService
     */
     public static getInstance(context: any): ProductLifeAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductLifeAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductLifeAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductLifeAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductLifeAuthService({context:context}));
            }
            return ProductLifeAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}