import { ProductTeamAuthServiceBase } from './product-team-auth-service-base';


/**
 * 产品团队权限服务对象
 *
 * @export
 * @class ProductTeamAuthService
 * @extends {ProductTeamAuthServiceBase}
 */
export default class ProductTeamAuthService extends ProductTeamAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ProductTeamAuthService}
     * @memberof ProductTeamAuthService
     */
    private static basicUIServiceInstance: ProductTeamAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ProductTeamAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductTeamAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductTeamAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductTeamAuthService
     */
     public static getInstance(context: any): ProductTeamAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductTeamAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductTeamAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ProductTeamAuthService.AuthServiceMap.set(context.srfdynainstid, new ProductTeamAuthService({context:context}));
            }
            return ProductTeamAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}