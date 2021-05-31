import { EmpLoyeeloadAuthServiceBase } from './emp-loyeeload-auth-service-base';


/**
 * 员工负载表权限服务对象
 *
 * @export
 * @class EmpLoyeeloadAuthService
 * @extends {EmpLoyeeloadAuthServiceBase}
 */
export default class EmpLoyeeloadAuthService extends EmpLoyeeloadAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {EmpLoyeeloadAuthService}
     * @memberof EmpLoyeeloadAuthService
     */
    private static basicUIServiceInstance: EmpLoyeeloadAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof EmpLoyeeloadAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  EmpLoyeeloadAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  EmpLoyeeloadAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof EmpLoyeeloadAuthService
     */
     public static getInstance(context: any): EmpLoyeeloadAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new EmpLoyeeloadAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!EmpLoyeeloadAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                EmpLoyeeloadAuthService.AuthServiceMap.set(context.srfdynainstid, new EmpLoyeeloadAuthService({context:context}));
            }
            return EmpLoyeeloadAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}