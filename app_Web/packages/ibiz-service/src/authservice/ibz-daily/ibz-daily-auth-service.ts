import { IbzDailyAuthServiceBase } from './ibz-daily-auth-service-base';


/**
 * 日报权限服务对象
 *
 * @export
 * @class IbzDailyAuthService
 * @extends {IbzDailyAuthServiceBase}
 */
export default class IbzDailyAuthService extends IbzDailyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzDailyAuthService}
     * @memberof IbzDailyAuthService
     */
    private static basicUIServiceInstance: IbzDailyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzDailyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzDailyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzDailyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzDailyAuthService
     */
     public static getInstance(context: any): IbzDailyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzDailyAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzDailyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzDailyAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzDailyAuthService({context:context}));
            }
            return IbzDailyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}