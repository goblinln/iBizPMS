import { IBZTestReportActionAuthServiceBase } from './ibztest-report-action-auth-service-base';


/**
 * 报告日志权限服务对象
 *
 * @export
 * @class IBZTestReportActionAuthService
 * @extends {IBZTestReportActionAuthServiceBase}
 */
export default class IBZTestReportActionAuthService extends IBZTestReportActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZTestReportActionAuthService}
     * @memberof IBZTestReportActionAuthService
     */
    private static basicUIServiceInstance: IBZTestReportActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZTestReportActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZTestReportActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZTestReportActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZTestReportActionAuthService
     */
     public static getInstance(context: any): IBZTestReportActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZTestReportActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZTestReportActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZTestReportActionAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZTestReportActionAuthService({context:context}));
            }
            return IBZTestReportActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}