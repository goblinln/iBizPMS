import { SysRoleUIServiceBase } from './sys-role-ui-service-base';

/**
 * 系统角色UI服务对象
 *
 * @export
 * @class SysRoleUIService
 */
export default class SysRoleUIService extends SysRoleUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof SysRoleUIService
     */
    private static basicUIServiceInstance: SysRoleUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof SysRoleUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysRoleUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysRoleUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysRoleUIService
     */
    public static getInstance(context: any): SysRoleUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysRoleUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysRoleUIService.UIServiceMap.get(context.srfdynainstid)) {
                SysRoleUIService.UIServiceMap.set(context.srfdynainstid, new SysRoleUIService({context:context}));
            }
            return SysRoleUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}