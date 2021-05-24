import { ProductLineAuthServiceBase } from './product-line-auth-service-base';


/**
 * 产品线（废弃）权限服务对象
 *
 * @export
 * @class ProductLineAuthService
 * @extends {ProductLineAuthServiceBase}
 */
export default class ProductLineAuthService extends ProductLineAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductLineAuthService}
     * @memberof ProductLineAuthService
     */
    private static basicUIServiceInstance: ProductLineAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductLineAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductLineAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductLineAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductLineAuthService
     */
     public static getInstance(context: any): ProductLineAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductLineAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductLineAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductLineAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductLineAuthService({context:context}));
            }
            return ProductLineAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}