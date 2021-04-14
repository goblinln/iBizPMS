import { SysUserRoleUIServiceBase } from './sys-user-role-ui-service-base';

/**
 * 用户角色关系UI服务对象
 *
 * @export
 * @class SysUserRoleUIService
 */
export default class SysUserRoleUIService extends SysUserRoleUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof SysUserRoleUIService
     */
    private static basicUIServiceInstance: SysUserRoleUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof SysUserRoleUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysUserRoleUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysUserRoleUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysUserRoleUIService
     */
    public static getInstance(context: any): SysUserRoleUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysUserRoleUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysUserRoleUIService.UIServiceMap.get(context.srfdynainstid)) {
                SysUserRoleUIService.UIServiceMap.set(context.srfdynainstid, new SysUserRoleUIService({context:context}));
            }
            return SysUserRoleUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}