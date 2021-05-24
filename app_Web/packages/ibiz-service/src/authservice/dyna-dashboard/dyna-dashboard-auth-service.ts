import { DynaDashboardAuthServiceBase } from './dyna-dashboard-auth-service-base';


/**
 * 动态数据看板权限服务对象
 *
 * @export
 * @class DynaDashboardAuthService
 * @extends {DynaDashboardAuthServiceBase}
 */
export default class DynaDashboardAuthService extends DynaDashboardAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {DynaDashboardAuthService}
     * @memberof DynaDashboardAuthService
     */
    private static basicUIServiceInstance: DynaDashboardAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof DynaDashboardAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DynaDashboardAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  DynaDashboardAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DynaDashboardAuthService
     */
     public static getInstance(context: any): DynaDashboardAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new DynaDashboardAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!DynaDashboardAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                DynaDashboardAuthService.AuthServiceMap.set(context.srfdynainstid, new DynaDashboardAuthService({context:context}));
            }
            return DynaDashboardAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}