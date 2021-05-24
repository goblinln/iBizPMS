import { ProductStatsAuthServiceBase } from './product-stats-auth-service-base';


/**
 * 产品统计权限服务对象
 *
 * @export
 * @class ProductStatsAuthService
 * @extends {ProductStatsAuthServiceBase}
 */
export default class ProductStatsAuthService extends ProductStatsAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductStatsAuthService}
     * @memberof ProductStatsAuthService
     */
    private static basicUIServiceInstance: ProductStatsAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductStatsAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductStatsAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductStatsAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductStatsAuthService
     */
     public static getInstance(context: any): ProductStatsAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductStatsAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductStatsAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductStatsAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductStatsAuthService({context:context}));
            }
            return ProductStatsAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}