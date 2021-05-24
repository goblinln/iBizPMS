import { SysDepartmentAuthServiceBase } from './sys-department-auth-service-base';


/**
 * 部门权限服务对象
 *
 * @export
 * @class SysDepartmentAuthService
 * @extends {SysDepartmentAuthServiceBase}
 */
export default class SysDepartmentAuthService extends SysDepartmentAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {SysDepartmentAuthService}
     * @memberof SysDepartmentAuthService
     */
    private static basicUIServiceInstance: SysDepartmentAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof SysDepartmentAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SysDepartmentAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SysDepartmentAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SysDepartmentAuthService
     */
     public static getInstance(context: any): SysDepartmentAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SysDepartmentAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SysDepartmentAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                SysDepartmentAuthService.AuthServiceMap.set(context.srfdynainstid, new SysDepartmentAuthService({context:context}));
            }
            return SysDepartmentAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}