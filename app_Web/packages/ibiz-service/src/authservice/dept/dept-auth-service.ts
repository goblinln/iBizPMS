import { DeptAuthServiceBase } from './dept-auth-service-base';


/**
 * 部门权限服务对象
 *
 * @export
 * @class DeptAuthService
 * @extends {DeptAuthServiceBase}
 */
export default class DeptAuthService extends DeptAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {DeptAuthService}
     * @memberof DeptAuthService
     */
    private static basicUIServiceInstance: DeptAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof DeptAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DeptAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  DeptAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DeptAuthService
     */
     public static getInstance(context: any): DeptAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new DeptAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!DeptAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                DeptAuthService.AuthServiceMap.set(context.srfdynainstid, new DeptAuthService({context:context}));
            }
            return DeptAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}