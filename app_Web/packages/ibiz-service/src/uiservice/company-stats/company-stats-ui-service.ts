import { CompanyStatsUIServiceBase } from './company-stats-ui-service-base';

/**
 * 公司动态汇总UI服务对象
 *
 * @export
 * @class CompanyStatsUIService
 */
export default class CompanyStatsUIService extends CompanyStatsUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof CompanyStatsUIService
     */
    private static basicUIServiceInstance: CompanyStatsUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof CompanyStatsUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  CompanyStatsUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  CompanyStatsUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof CompanyStatsUIService
     */
    public static getInstance(context: any): CompanyStatsUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new CompanyStatsUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!CompanyStatsUIService.UIServiceMap.get(context.srfdynainstid)) {
                CompanyStatsUIService.UIServiceMap.set(context.srfdynainstid, new CompanyStatsUIService({context:context}));
            }
            return CompanyStatsUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}