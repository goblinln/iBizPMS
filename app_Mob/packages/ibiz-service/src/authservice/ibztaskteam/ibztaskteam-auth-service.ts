import { IbztaskteamAuthServiceBase } from './ibztaskteam-auth-service-base';


/**
 * 任务团队权限服务对象
 *
 * @export
 * @class IbztaskteamAuthService
 * @extends {IbztaskteamAuthServiceBase}
 */
export default class IbztaskteamAuthService extends IbztaskteamAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbztaskteamAuthService}
     * @memberof IbztaskteamAuthService
     */
    private static basicUIServiceInstance: IbztaskteamAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbztaskteamAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbztaskteamAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbztaskteamAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbztaskteamAuthService
     */
     public static getInstance(context: any): IbztaskteamAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbztaskteamAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbztaskteamAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbztaskteamAuthService.AuthServiceMap.set(context.srfdynainstid, new IbztaskteamAuthService({context:context}));
            }
            return IbztaskteamAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}