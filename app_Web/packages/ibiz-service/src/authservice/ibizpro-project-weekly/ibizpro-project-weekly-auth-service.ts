import { IbizproProjectWeeklyAuthServiceBase } from './ibizpro-project-weekly-auth-service-base';


/**
 * 项目周报权限服务对象
 *
 * @export
 * @class IbizproProjectWeeklyAuthService
 * @extends {IbizproProjectWeeklyAuthServiceBase}
 */
export default class IbizproProjectWeeklyAuthService extends IbizproProjectWeeklyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbizproProjectWeeklyAuthService}
     * @memberof IbizproProjectWeeklyAuthService
     */
    private static basicUIServiceInstance: IbizproProjectWeeklyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbizproProjectWeeklyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbizproProjectWeeklyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProjectWeeklyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbizproProjectWeeklyAuthService
     */
     public static getInstance(context: any): IbizproProjectWeeklyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbizproProjectWeeklyAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbizproProjectWeeklyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbizproProjectWeeklyAuthService.AuthServiceMap.set(context.srfdynainstid, new IbizproProjectWeeklyAuthService({context:context}));
            }
            return IbizproProjectWeeklyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}