import { IbzProMonthlyActionAuthServiceBase } from './ibz-pro-monthly-action-auth-service-base';


/**
 * 月报日志权限服务对象
 *
 * @export
 * @class IbzProMonthlyActionAuthService
 * @extends {IbzProMonthlyActionAuthServiceBase}
 */
export default class IbzProMonthlyActionAuthService extends IbzProMonthlyActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzProMonthlyActionAuthService}
     * @memberof IbzProMonthlyActionAuthService
     */
    private static basicUIServiceInstance: IbzProMonthlyActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzProMonthlyActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzProMonthlyActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzProMonthlyActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzProMonthlyActionAuthService
     */
     public static getInstance(context: any): IbzProMonthlyActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzProMonthlyActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzProMonthlyActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzProMonthlyActionAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzProMonthlyActionAuthService({context:context}));
            }
            return IbzProMonthlyActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}