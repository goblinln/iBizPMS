import { IBZProReleaseActionAuthServiceBase } from './ibzpro-release-action-auth-service-base';


/**
 * 发布日志权限服务对象
 *
 * @export
 * @class IBZProReleaseActionAuthService
 * @extends {IBZProReleaseActionAuthServiceBase}
 */
export default class IBZProReleaseActionAuthService extends IBZProReleaseActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZProReleaseActionAuthService}
     * @memberof IBZProReleaseActionAuthService
     */
    private static basicUIServiceInstance: IBZProReleaseActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZProReleaseActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProReleaseActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProReleaseActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProReleaseActionAuthService
     */
     public static getInstance(context: any): IBZProReleaseActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProReleaseActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProReleaseActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZProReleaseActionAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZProReleaseActionAuthService({context:context}));
            }
            return IBZProReleaseActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}