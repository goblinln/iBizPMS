import { IBZDailyActionAuthServiceBase } from './ibzdaily-action-auth-service-base';


/**
 * 日报日志权限服务对象
 *
 * @export
 * @class IBZDailyActionAuthService
 * @extends {IBZDailyActionAuthServiceBase}
 */
export default class IBZDailyActionAuthService extends IBZDailyActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZDailyActionAuthService}
     * @memberof IBZDailyActionAuthService
     */
    private static basicUIServiceInstance: IBZDailyActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZDailyActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZDailyActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZDailyActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZDailyActionAuthService
     */
     public static getInstance(context: any): IBZDailyActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZDailyActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZDailyActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZDailyActionAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZDailyActionAuthService({context:context}));
            }
            return IBZDailyActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}