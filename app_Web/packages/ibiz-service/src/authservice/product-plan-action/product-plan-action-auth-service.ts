import { ProductPlanActionAuthServiceBase } from './product-plan-action-auth-service-base';


/**
 * 产品计划日志权限服务对象
 *
 * @export
 * @class ProductPlanActionAuthService
 * @extends {ProductPlanActionAuthServiceBase}
 */
export default class ProductPlanActionAuthService extends ProductPlanActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductPlanActionAuthService}
     * @memberof ProductPlanActionAuthService
     */
    private static basicUIServiceInstance: ProductPlanActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductPlanActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductPlanActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductPlanActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductPlanActionAuthService
     */
     public static getInstance(context: any): ProductPlanActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductPlanActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductPlanActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductPlanActionAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductPlanActionAuthService({context:context}));
            }
            return ProductPlanActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}