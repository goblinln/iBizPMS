import { IbzReportRoleConfigAuthServiceBase } from './ibz-report-role-config-auth-service-base';


/**
 * 汇报角色配置权限服务对象
 *
 * @export
 * @class IbzReportRoleConfigAuthService
 * @extends {IbzReportRoleConfigAuthServiceBase}
 */
export default class IbzReportRoleConfigAuthService extends IbzReportRoleConfigAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzReportRoleConfigAuthService}
     * @memberof IbzReportRoleConfigAuthService
     */
    private static basicUIServiceInstance: IbzReportRoleConfigAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzReportRoleConfigAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzReportRoleConfigAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzReportRoleConfigAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzReportRoleConfigAuthService
     */
     public static getInstance(context: any): IbzReportRoleConfigAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzReportRoleConfigAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzReportRoleConfigAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzReportRoleConfigAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzReportRoleConfigAuthService({context:context}));
            }
            return IbzReportRoleConfigAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}