import { IBZTestReportActionUIServiceBase } from './ibztest-report-action-ui-service-base';

/**
 * 报告日志UI服务对象
 *
 * @export
 * @class IBZTestReportActionUIService
 */
export default class IBZTestReportActionUIService extends IBZTestReportActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZTestReportActionUIService
     */
    private static basicUIServiceInstance: IBZTestReportActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZTestReportActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZTestReportActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZTestReportActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZTestReportActionUIService
     */
    public static getInstance(context: any): IBZTestReportActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZTestReportActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZTestReportActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZTestReportActionUIService.UIServiceMap.set(context.srfdynainstid, new IBZTestReportActionUIService({context:context}));
            }
            return IBZTestReportActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}