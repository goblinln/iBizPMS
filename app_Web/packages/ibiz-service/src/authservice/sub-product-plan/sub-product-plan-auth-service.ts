import { SubProductPlanAuthServiceBase } from './sub-product-plan-auth-service-base';


/**
 * 产品计划权限服务对象
 *
 * @export
 * @class SubProductPlanAuthService
 * @extends {SubProductPlanAuthServiceBase}
 */
export default class SubProductPlanAuthService extends SubProductPlanAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {SubProductPlanAuthService}
     * @memberof SubProductPlanAuthService
     */
    private static basicUIServiceInstance: SubProductPlanAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof SubProductPlanAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SubProductPlanAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SubProductPlanAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SubProductPlanAuthService
     */
     public static getInstance(context: any): SubProductPlanAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SubProductPlanAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SubProductPlanAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                SubProductPlanAuthService.AuthServiceMap.set(context.srfdynainstid, new SubProductPlanAuthService({context:context}));
            }
            return SubProductPlanAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}