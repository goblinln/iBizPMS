import { DynaFilterAuthServiceBase } from './dyna-filter-auth-service-base';


/**
 * 动态搜索栏权限服务对象
 *
 * @export
 * @class DynaFilterAuthService
 * @extends {DynaFilterAuthServiceBase}
 */
export default class DynaFilterAuthService extends DynaFilterAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {DynaFilterAuthService}
     * @memberof DynaFilterAuthService
     */
    private static basicUIServiceInstance: DynaFilterAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof DynaFilterAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DynaFilterAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  DynaFilterAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DynaFilterAuthService
     */
     public static getInstance(context: any): DynaFilterAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new DynaFilterAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!DynaFilterAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                DynaFilterAuthService.AuthServiceMap.set(context.srfdynainstid, new DynaFilterAuthService({context:context}));
            }
            return DynaFilterAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}