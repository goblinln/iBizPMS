import { BuildAuthServiceBase } from './build-auth-service-base';


/**
 * 版本权限服务对象
 *
 * @export
 * @class BuildAuthService
 * @extends {BuildAuthServiceBase}
 */
export default class BuildAuthService extends BuildAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {BuildAuthService}
     * @memberof BuildAuthService
     */
    private static basicUIServiceInstance: BuildAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof BuildAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  BuildAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  BuildAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof BuildAuthService
     */
     public static getInstance(context: any): BuildAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new BuildAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!BuildAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                BuildAuthService.AuthServiceMap.set(context.srfdynainstid, new BuildAuthService({context:context}));
            }
            return BuildAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}