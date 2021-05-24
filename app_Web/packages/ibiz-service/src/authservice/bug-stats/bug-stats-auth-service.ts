import { BugStatsAuthServiceBase } from './bug-stats-auth-service-base';


/**
 * Bug统计权限服务对象
 *
 * @export
 * @class BugStatsAuthService
 * @extends {BugStatsAuthServiceBase}
 */
export default class BugStatsAuthService extends BugStatsAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {BugStatsAuthService}
     * @memberof BugStatsAuthService
     */
    private static basicUIServiceInstance: BugStatsAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof BugStatsAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  BugStatsAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  BugStatsAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof BugStatsAuthService
     */
     public static getInstance(context: any): BugStatsAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new BugStatsAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!BugStatsAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                BugStatsAuthService.AuthServiceMap.set(context.srfdynainstid, new BugStatsAuthService({context:context}));
            }
            return BugStatsAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}