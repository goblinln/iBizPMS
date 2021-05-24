import { CaseStatsAuthServiceBase } from './case-stats-auth-service-base';


/**
 * 测试用例统计权限服务对象
 *
 * @export
 * @class CaseStatsAuthService
 * @extends {CaseStatsAuthServiceBase}
 */
export default class CaseStatsAuthService extends CaseStatsAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {CaseStatsAuthService}
     * @memberof CaseStatsAuthService
     */
    private static basicUIServiceInstance: CaseStatsAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof CaseStatsAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  CaseStatsAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  CaseStatsAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof CaseStatsAuthService
     */
     public static getInstance(context: any): CaseStatsAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new CaseStatsAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!CaseStatsAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                CaseStatsAuthService.AuthServiceMap.set(context.srfdynainstid, new CaseStatsAuthService({context:context}));
            }
            return CaseStatsAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}