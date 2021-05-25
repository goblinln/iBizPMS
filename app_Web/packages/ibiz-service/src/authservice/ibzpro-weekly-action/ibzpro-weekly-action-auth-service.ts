import { IBZProWeeklyActionAuthServiceBase } from './ibzpro-weekly-action-auth-service-base';


/**
 * 周报日志权限服务对象
 *
 * @export
 * @class IBZProWeeklyActionAuthService
 * @extends {IBZProWeeklyActionAuthServiceBase}
 */
export default class IBZProWeeklyActionAuthService extends IBZProWeeklyActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZProWeeklyActionAuthService}
     * @memberof IBZProWeeklyActionAuthService
     */
    private static basicUIServiceInstance: IBZProWeeklyActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZProWeeklyActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProWeeklyActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProWeeklyActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProWeeklyActionAuthService
     */
     public static getInstance(context: any): IBZProWeeklyActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProWeeklyActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProWeeklyActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZProWeeklyActionAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZProWeeklyActionAuthService({context:context}));
            }
            return IBZProWeeklyActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}