import { ReportSummaryUIServiceBase } from './report-summary-ui-service-base';

/**
 * 汇报汇总UI服务对象
 *
 * @export
 * @class ReportSummaryUIService
 */
export default class ReportSummaryUIService extends ReportSummaryUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ReportSummaryUIService
     */
    private static basicUIServiceInstance: ReportSummaryUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ReportSummaryUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ReportSummaryUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ReportSummaryUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ReportSummaryUIService
     */
    public static getInstance(context: any): ReportSummaryUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ReportSummaryUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ReportSummaryUIService.UIServiceMap.get(context.srfdynainstid)) {
                ReportSummaryUIService.UIServiceMap.set(context.srfdynainstid, new ReportSummaryUIService({context:context}));
            }
            return ReportSummaryUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}