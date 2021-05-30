import { IbizproProjectMonthlyAuthServiceBase } from './ibizpro-project-monthly-auth-service-base';


/**
 * 项目月报权限服务对象
 *
 * @export
 * @class IbizproProjectMonthlyAuthService
 * @extends {IbizproProjectMonthlyAuthServiceBase}
 */
export default class IbizproProjectMonthlyAuthService extends IbizproProjectMonthlyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbizproProjectMonthlyAuthService}
     * @memberof IbizproProjectMonthlyAuthService
     */
    private static basicUIServiceInstance: IbizproProjectMonthlyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbizproProjectMonthlyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbizproProjectMonthlyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProjectMonthlyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbizproProjectMonthlyAuthService
     */
     public static getInstance(context: any): IbizproProjectMonthlyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbizproProjectMonthlyAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbizproProjectMonthlyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbizproProjectMonthlyAuthService.AuthServiceMap.set(context.srfdynainstid, new IbizproProjectMonthlyAuthService({context:context}));
            }
            return IbizproProjectMonthlyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}