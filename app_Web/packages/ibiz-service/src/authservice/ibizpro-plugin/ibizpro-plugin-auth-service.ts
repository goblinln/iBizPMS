import { IBIZProPluginAuthServiceBase } from './ibizpro-plugin-auth-service-base';


/**
 * 系统插件权限服务对象
 *
 * @export
 * @class IBIZProPluginAuthService
 * @extends {IBIZProPluginAuthServiceBase}
 */
export default class IBIZProPluginAuthService extends IBIZProPluginAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBIZProPluginAuthService}
     * @memberof IBIZProPluginAuthService
     */
    private static basicUIServiceInstance: IBIZProPluginAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBIZProPluginAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBIZProPluginAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBIZProPluginAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBIZProPluginAuthService
     */
     public static getInstance(context: any): IBIZProPluginAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBIZProPluginAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBIZProPluginAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBIZProPluginAuthService.AuthServiceMap.set(context.srfdynainstid, new IBIZProPluginAuthService({context:context}));
            }
            return IBIZProPluginAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}