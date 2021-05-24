import { ProductPlanAuthServiceBase } from './product-plan-auth-service-base';


/**
 * 产品计划权限服务对象
 *
 * @export
 * @class ProductPlanAuthService
 * @extends {ProductPlanAuthServiceBase}
 */
export default class ProductPlanAuthService extends ProductPlanAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductPlanAuthService}
     * @memberof ProductPlanAuthService
     */
    private static basicUIServiceInstance: ProductPlanAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductPlanAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductPlanAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductPlanAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductPlanAuthService
     */
     public static getInstance(context: any): ProductPlanAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductPlanAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductPlanAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductPlanAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductPlanAuthService({context:context}));
            }
            return ProductPlanAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}