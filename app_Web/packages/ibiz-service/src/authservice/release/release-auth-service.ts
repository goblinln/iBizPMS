import { ReleaseAuthServiceBase } from './release-auth-service-base';


/**
 * 发布权限服务对象
 *
 * @export
 * @class ReleaseAuthService
 * @extends {ReleaseAuthServiceBase}
 */
export default class ReleaseAuthService extends ReleaseAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ReleaseAuthService}
     * @memberof ReleaseAuthService
     */
    private static basicUIServiceInstance: ReleaseAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ReleaseAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ReleaseAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ReleaseAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ReleaseAuthService
     */
     public static getInstance(context: any): ReleaseAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ReleaseAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ReleaseAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ReleaseAuthService.AuthServiceMap.set(context.srfdynainstid, new ReleaseAuthService({context:context}));
            }
            return ReleaseAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}