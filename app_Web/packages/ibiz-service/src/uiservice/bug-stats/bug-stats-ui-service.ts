import { BugStatsUIServiceBase } from './bug-stats-ui-service-base';

/**
 * Bug统计UI服务对象
 *
 * @export
 * @class BugStatsUIService
 */
export default class BugStatsUIService extends BugStatsUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof BugStatsUIService
     */
    private static basicUIServiceInstance: BugStatsUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof BugStatsUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  BugStatsUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  BugStatsUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof BugStatsUIService
     */
    public static getInstance(context: any): BugStatsUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new BugStatsUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!BugStatsUIService.UIServiceMap.get(context.srfdynainstid)) {
                BugStatsUIService.UIServiceMap.set(context.srfdynainstid, new BugStatsUIService({context:context}));
            }
            return BugStatsUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}