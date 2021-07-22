import { DailyAuthServiceBase } from './daily-auth-service-base';


/**
 * 日报权限服务对象
 *
 * @export
 * @class DailyAuthService
 * @extends {DailyAuthServiceBase}
 */
export default class DailyAuthService extends DailyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {DailyAuthService}
     * @memberof DailyAuthService
     */
    private static basicUIServiceInstance: DailyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof DailyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DailyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  DailyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DailyAuthService
     */
     public static getInstance(context: any): DailyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new DailyAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!DailyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                DailyAuthService.AuthServiceMap.set(context.srfdynainstid, new DailyAuthService({context:context}));
            }
            return DailyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}