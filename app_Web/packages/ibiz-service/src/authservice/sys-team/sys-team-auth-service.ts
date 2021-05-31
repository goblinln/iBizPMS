import { SysTeamAuthServiceBase } from './sys-team-auth-service-base';


/**
 * 组权限服务对象
 *
 * @export
 * @class SysTeamAuthService
 * @extends {SysTeamAuthServiceBase}
 */
export default class SysTeamAuthService extends SysTeamAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {SysTeamAuthService}
     * @memberof SysTeamAuthService
     */
    private static basicUIServiceInstance: SysTeamAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof SysTeamAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysTeamAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysTeamAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysTeamAuthService
     */
     public static getInstance(context: any): SysTeamAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysTeamAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysTeamAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                SysTeamAuthService.AuthServiceMap.set(context.srfdynainstid, new SysTeamAuthService({context:context}));
            }
            return SysTeamAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}