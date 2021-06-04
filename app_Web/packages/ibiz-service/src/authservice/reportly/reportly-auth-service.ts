import { ReportlyAuthServiceBase } from './reportly-auth-service-base';


/**
 * 汇报权限服务对象
 *
 * @export
 * @class ReportlyAuthService
 * @extends {ReportlyAuthServiceBase}
 */
export default class ReportlyAuthService extends ReportlyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {ReportlyAuthService}
     * @memberof ReportlyAuthService
     */
    private static basicUIServiceInstance: ReportlyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof ReportlyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ReportlyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ReportlyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ReportlyAuthService
     */
     public static getInstance(context: any): ReportlyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ReportlyAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ReportlyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                ReportlyAuthService.AuthServiceMap.set(context.srfdynainstid, new ReportlyAuthService({context:context}));
            }
            return ReportlyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}