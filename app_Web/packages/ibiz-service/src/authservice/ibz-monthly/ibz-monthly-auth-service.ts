import { IbzMonthlyAuthServiceBase } from './ibz-monthly-auth-service-base';


/**
 * 月报权限服务对象
 *
 * @export
 * @class IbzMonthlyAuthService
 * @extends {IbzMonthlyAuthServiceBase}
 */
export default class IbzMonthlyAuthService extends IbzMonthlyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzMonthlyAuthService}
     * @memberof IbzMonthlyAuthService
     */
    private static basicUIServiceInstance: IbzMonthlyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzMonthlyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzMonthlyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzMonthlyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzMonthlyAuthService
     */
     public static getInstance(context: any): IbzMonthlyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzMonthlyAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzMonthlyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzMonthlyAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzMonthlyAuthService({context:context}));
            }
            return IbzMonthlyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}