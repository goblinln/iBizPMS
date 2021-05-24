import { SysEmployeeAuthServiceBase } from './sys-employee-auth-service-base';


/**
 * 人员权限服务对象
 *
 * @export
 * @class SysEmployeeAuthService
 * @extends {SysEmployeeAuthServiceBase}
 */
export default class SysEmployeeAuthService extends SysEmployeeAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {SysEmployeeAuthService}
     * @memberof SysEmployeeAuthService
     */
    private static basicUIServiceInstance: SysEmployeeAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof SysEmployeeAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysEmployeeAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysEmployeeAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysEmployeeAuthService
     */
     public static getInstance(context: any): SysEmployeeAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysEmployeeAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysEmployeeAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                SysEmployeeAuthService.AuthServiceMap.set(context.srfdynainstid, new SysEmployeeAuthService({context:context}));
            }
            return SysEmployeeAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}