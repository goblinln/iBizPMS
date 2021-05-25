import { IbzProBuildActionAuthServiceBase } from './ibz-pro-build-action-auth-service-base';


/**
 * 版本日志权限服务对象
 *
 * @export
 * @class IbzProBuildActionAuthService
 * @extends {IbzProBuildActionAuthServiceBase}
 */
export default class IbzProBuildActionAuthService extends IbzProBuildActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzProBuildActionAuthService}
     * @memberof IbzProBuildActionAuthService
     */
    private static basicUIServiceInstance: IbzProBuildActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzProBuildActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzProBuildActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzProBuildActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzProBuildActionAuthService
     */
     public static getInstance(context: any): IbzProBuildActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzProBuildActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzProBuildActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzProBuildActionAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzProBuildActionAuthService({context:context}));
            }
            return IbzProBuildActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}