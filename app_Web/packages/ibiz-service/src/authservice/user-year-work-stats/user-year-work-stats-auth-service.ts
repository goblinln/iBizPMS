import { UserYearWorkStatsAuthServiceBase } from './user-year-work-stats-auth-service-base';


/**
 * 用户年度工作内容统计权限服务对象
 *
 * @export
 * @class UserYearWorkStatsAuthService
 * @extends {UserYearWorkStatsAuthServiceBase}
 */
export default class UserYearWorkStatsAuthService extends UserYearWorkStatsAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {UserYearWorkStatsAuthService}
     * @memberof UserYearWorkStatsAuthService
     */
    private static basicUIServiceInstance: UserYearWorkStatsAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof UserYearWorkStatsAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  UserYearWorkStatsAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  UserYearWorkStatsAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof UserYearWorkStatsAuthService
     */
     public static getInstance(context: any): UserYearWorkStatsAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new UserYearWorkStatsAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!UserYearWorkStatsAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                UserYearWorkStatsAuthService.AuthServiceMap.set(context.srfdynainstid, new UserYearWorkStatsAuthService({context:context}));
            }
            return UserYearWorkStatsAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}