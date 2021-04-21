import { UserYearWorkStatsUIServiceBase } from './user-year-work-stats-ui-service-base';

/**
 * 用户年度工作内容统计UI服务对象
 *
 * @export
 * @class UserYearWorkStatsUIService
 */
export default class UserYearWorkStatsUIService extends UserYearWorkStatsUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof UserYearWorkStatsUIService
     */
    private static basicUIServiceInstance: UserYearWorkStatsUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof UserYearWorkStatsUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  UserYearWorkStatsUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  UserYearWorkStatsUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof UserYearWorkStatsUIService
     */
    public static getInstance(context: any): UserYearWorkStatsUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new UserYearWorkStatsUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!UserYearWorkStatsUIService.UIServiceMap.get(context.srfdynainstid)) {
                UserYearWorkStatsUIService.UIServiceMap.set(context.srfdynainstid, new UserYearWorkStatsUIService({context:context}));
            }
            return UserYearWorkStatsUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}