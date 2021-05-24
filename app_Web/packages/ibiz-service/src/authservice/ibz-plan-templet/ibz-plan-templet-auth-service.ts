import { IbzPlanTempletAuthServiceBase } from './ibz-plan-templet-auth-service-base';


/**
 * 计划模板权限服务对象
 *
 * @export
 * @class IbzPlanTempletAuthService
 * @extends {IbzPlanTempletAuthServiceBase}
 */
export default class IbzPlanTempletAuthService extends IbzPlanTempletAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzPlanTempletAuthService}
     * @memberof IbzPlanTempletAuthService
     */
    private static basicUIServiceInstance: IbzPlanTempletAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzPlanTempletAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzPlanTempletAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzPlanTempletAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzPlanTempletAuthService
     */
     public static getInstance(context: any): IbzPlanTempletAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzPlanTempletAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzPlanTempletAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzPlanTempletAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzPlanTempletAuthService({context:context}));
            }
            return IbzPlanTempletAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}