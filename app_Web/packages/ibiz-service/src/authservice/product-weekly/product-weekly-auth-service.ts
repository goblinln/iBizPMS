import { ProductWeeklyAuthServiceBase } from './product-weekly-auth-service-base';


/**
 * 产品周报权限服务对象
 *
 * @export
 * @class ProductWeeklyAuthService
 * @extends {ProductWeeklyAuthServiceBase}
 */
export default class ProductWeeklyAuthService extends ProductWeeklyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductWeeklyAuthService}
     * @memberof ProductWeeklyAuthService
     */
    private static basicUIServiceInstance: ProductWeeklyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductWeeklyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductWeeklyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductWeeklyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductWeeklyAuthService
     */
     public static getInstance(context: any): ProductWeeklyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductWeeklyAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductWeeklyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductWeeklyAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductWeeklyAuthService({context:context}));
            }
            return ProductWeeklyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}