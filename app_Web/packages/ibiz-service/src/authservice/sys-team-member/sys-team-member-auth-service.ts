import { SysTeamMemberAuthServiceBase } from './sys-team-member-auth-service-base';


/**
 * 组成员权限服务对象
 *
 * @export
 * @class SysTeamMemberAuthService
 * @extends {SysTeamMemberAuthServiceBase}
 */
export default class SysTeamMemberAuthService extends SysTeamMemberAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {SysTeamMemberAuthService}
     * @memberof SysTeamMemberAuthService
     */
    private static basicUIServiceInstance: SysTeamMemberAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof SysTeamMemberAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysTeamMemberAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysTeamMemberAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysTeamMemberAuthService
     */
     public static getInstance(context: any): SysTeamMemberAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysTeamMemberAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysTeamMemberAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                SysTeamMemberAuthService.AuthServiceMap.set(context.srfdynainstid, new SysTeamMemberAuthService({context:context}));
            }
            return SysTeamMemberAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}