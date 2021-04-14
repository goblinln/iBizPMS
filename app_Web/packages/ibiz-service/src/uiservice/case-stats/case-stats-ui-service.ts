import { CaseStatsUIServiceBase } from './case-stats-ui-service-base';

/**
 * 测试用例统计UI服务对象
 *
 * @export
 * @class CaseStatsUIService
 */
export default class CaseStatsUIService extends CaseStatsUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof CaseStatsUIService
     */
    private static basicUIServiceInstance: CaseStatsUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof CaseStatsUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  CaseStatsUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  CaseStatsUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof CaseStatsUIService
     */
    public static getInstance(context: any): CaseStatsUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new CaseStatsUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!CaseStatsUIService.UIServiceMap.get(context.srfdynainstid)) {
                CaseStatsUIService.UIServiceMap.set(context.srfdynainstid, new CaseStatsUIService({context:context}));
            }
            return CaseStatsUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}