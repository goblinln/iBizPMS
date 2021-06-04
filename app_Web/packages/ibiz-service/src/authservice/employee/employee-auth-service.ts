import { EmployeeAuthServiceBase } from './employee-auth-service-base';


/**
 * 人员权限服务对象
 *
 * @export
 * @class EmployeeAuthService
 * @extends {EmployeeAuthServiceBase}
 */
export default class EmployeeAuthService extends EmployeeAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {EmployeeAuthService}
     * @memberof EmployeeAuthService
     */
    private static basicUIServiceInstance: EmployeeAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof EmployeeAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  EmployeeAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  EmployeeAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof EmployeeAuthService
     */
     public static getInstance(context: any): EmployeeAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new EmployeeAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!EmployeeAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                EmployeeAuthService.AuthServiceMap.set(context.srfdynainstid, new EmployeeAuthService({context:context}));
            }
            return EmployeeAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}