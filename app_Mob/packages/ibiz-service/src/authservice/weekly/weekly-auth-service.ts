import { WeeklyAuthServiceBase } from './weekly-auth-service-base';


/**
 * 周报权限服务对象
 *
 * @export
 * @class WeeklyAuthService
 * @extends {WeeklyAuthServiceBase}
 */
export default class WeeklyAuthService extends WeeklyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {WeeklyAuthService}
     * @memberof WeeklyAuthService
     */
    private static basicUIServiceInstance: WeeklyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof WeeklyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  WeeklyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  WeeklyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof WeeklyAuthService
     */
     public static getInstance(context: any): WeeklyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new WeeklyAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!WeeklyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                WeeklyAuthService.AuthServiceMap.set(context.srfdynainstid, new WeeklyAuthService({context:context}));
            }
            return WeeklyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}