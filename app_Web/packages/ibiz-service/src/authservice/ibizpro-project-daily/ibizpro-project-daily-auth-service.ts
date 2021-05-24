import { IbizproProjectDailyAuthServiceBase } from './ibizpro-project-daily-auth-service-base';


/**
 * 项目日报权限服务对象
 *
 * @export
 * @class IbizproProjectDailyAuthService
 * @extends {IbizproProjectDailyAuthServiceBase}
 */
export default class IbizproProjectDailyAuthService extends IbizproProjectDailyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbizproProjectDailyAuthService}
     * @memberof IbizproProjectDailyAuthService
     */
    private static basicUIServiceInstance: IbizproProjectDailyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbizproProjectDailyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbizproProjectDailyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProjectDailyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbizproProjectDailyAuthService
     */
     public static getInstance(context: any): IbizproProjectDailyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbizproProjectDailyAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbizproProjectDailyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbizproProjectDailyAuthService.AuthServiceMap.set(context.srfdynainstid, new IbizproProjectDailyAuthService({context:context}));
            }
            return IbizproProjectDailyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}