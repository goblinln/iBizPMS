import { ProductBranchAuthServiceBase } from './product-branch-auth-service-base';


/**
 * 产品的分支和平台信息权限服务对象
 *
 * @export
 * @class ProductBranchAuthService
 * @extends {ProductBranchAuthServiceBase}
 */
export default class ProductBranchAuthService extends ProductBranchAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductBranchAuthService}
     * @memberof ProductBranchAuthService
     */
    private static basicUIServiceInstance: ProductBranchAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductBranchAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductBranchAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductBranchAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductBranchAuthService
     */
     public static getInstance(context: any): ProductBranchAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductBranchAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductBranchAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductBranchAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductBranchAuthService({context:context}));
            }
            return ProductBranchAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}