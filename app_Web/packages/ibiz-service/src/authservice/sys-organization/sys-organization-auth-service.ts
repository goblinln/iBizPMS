import { SysOrganizationAuthServiceBase } from './sys-organization-auth-service-base';


/**
 * 单位权限服务对象
 *
 * @export
 * @class SysOrganizationAuthService
 * @extends {SysOrganizationAuthServiceBase}
 */
export default class SysOrganizationAuthService extends SysOrganizationAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {SysOrganizationAuthService}
     * @memberof SysOrganizationAuthService
     */
    private static basicUIServiceInstance: SysOrganizationAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof SysOrganizationAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysOrganizationAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysOrganizationAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysOrganizationAuthService
     */
     public static getInstance(context: any): SysOrganizationAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysOrganizationAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysOrganizationAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                SysOrganizationAuthService.AuthServiceMap.set(context.srfdynainstid, new SysOrganizationAuthService({context:context}));
            }
            return SysOrganizationAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}