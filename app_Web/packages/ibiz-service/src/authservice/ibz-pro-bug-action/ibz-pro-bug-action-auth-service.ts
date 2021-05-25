import { IbzProBugActionAuthServiceBase } from './ibz-pro-bug-action-auth-service-base';


/**
 * Bug日志权限服务对象
 *
 * @export
 * @class IbzProBugActionAuthService
 * @extends {IbzProBugActionAuthServiceBase}
 */
export default class IbzProBugActionAuthService extends IbzProBugActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzProBugActionAuthService}
     * @memberof IbzProBugActionAuthService
     */
    private static basicUIServiceInstance: IbzProBugActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzProBugActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzProBugActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzProBugActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzProBugActionAuthService
     */
     public static getInstance(context: any): IbzProBugActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzProBugActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzProBugActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzProBugActionAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzProBugActionAuthService({context:context}));
            }
            return IbzProBugActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}