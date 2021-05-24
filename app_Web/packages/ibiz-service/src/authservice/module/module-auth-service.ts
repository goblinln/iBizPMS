import { ModuleAuthServiceBase } from './module-auth-service-base';


/**
 * 模块权限服务对象
 *
 * @export
 * @class ModuleAuthService
 * @extends {ModuleAuthServiceBase}
 */
export default class ModuleAuthService extends ModuleAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ModuleAuthService}
     * @memberof ModuleAuthService
     */
    private static basicUIServiceInstance: ModuleAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ModuleAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ModuleAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ModuleAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ModuleAuthService
     */
     public static getInstance(context: any): ModuleAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ModuleAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ModuleAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ModuleAuthService.AuthServiceMap.set(context.srfdynainstid, new ModuleAuthService({context:context}));
            }
            return ModuleAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}