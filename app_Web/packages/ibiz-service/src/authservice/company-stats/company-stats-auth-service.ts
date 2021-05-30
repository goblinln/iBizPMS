import { CompanyStatsAuthServiceBase } from './company-stats-auth-service-base';


/**
 * 公司动态汇总权限服务对象
 *
 * @export
 * @class CompanyStatsAuthService
 * @extends {CompanyStatsAuthServiceBase}
 */
export default class CompanyStatsAuthService extends CompanyStatsAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {CompanyStatsAuthService}
     * @memberof CompanyStatsAuthService
     */
    private static basicUIServiceInstance: CompanyStatsAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof CompanyStatsAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  CompanyStatsAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  CompanyStatsAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof CompanyStatsAuthService
     */
     public static getInstance(context: any): CompanyStatsAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new CompanyStatsAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!CompanyStatsAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                CompanyStatsAuthService.AuthServiceMap.set(context.srfdynainstid, new CompanyStatsAuthService({context:context}));
            }
            return CompanyStatsAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}