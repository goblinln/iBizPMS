import { ProductStoryAuthServiceBase } from './product-story-auth-service-base';


/**
 * 需求权限服务对象
 *
 * @export
 * @class ProductStoryAuthService
 * @extends {ProductStoryAuthServiceBase}
 */
export default class ProductStoryAuthService extends ProductStoryAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductStoryAuthService}
     * @memberof ProductStoryAuthService
     */
    private static basicUIServiceInstance: ProductStoryAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductStoryAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductStoryAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductStoryAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductStoryAuthService
     */
     public static getInstance(context: any): ProductStoryAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductStoryAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductStoryAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductStoryAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductStoryAuthService({context:context}));
            }
            return ProductStoryAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}