import { ReportSummaryAuthServiceBase } from './report-summary-auth-service-base';


/**
 * 汇报汇总权限服务对象
 *
 * @export
 * @class ReportSummaryAuthService
 * @extends {ReportSummaryAuthServiceBase}
 */
export default class ReportSummaryAuthService extends ReportSummaryAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ReportSummaryAuthService}
     * @memberof ReportSummaryAuthService
     */
    private static basicUIServiceInstance: ReportSummaryAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ReportSummaryAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ReportSummaryAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ReportSummaryAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ReportSummaryAuthService
     */
     public static getInstance(context: any): ReportSummaryAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ReportSummaryAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ReportSummaryAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ReportSummaryAuthService.AuthServiceMap.set(context.srfdynainstid, new ReportSummaryAuthService({context:context}));
            }
            return ReportSummaryAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}