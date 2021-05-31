import { IbzWeeklyAuthServiceBase } from './ibz-weekly-auth-service-base';


/**
 * 周报权限服务对象
 *
 * @export
 * @class IbzWeeklyAuthService
 * @extends {IbzWeeklyAuthServiceBase}
 */
export default class IbzWeeklyAuthService extends IbzWeeklyAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzWeeklyAuthService}
     * @memberof IbzWeeklyAuthService
     */
    private static basicUIServiceInstance: IbzWeeklyAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzWeeklyAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzWeeklyAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzWeeklyAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzWeeklyAuthService
     */
     public static getInstance(context: any): IbzWeeklyAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzWeeklyAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzWeeklyAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzWeeklyAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzWeeklyAuthService({context:context}));
            }
            return IbzWeeklyAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}