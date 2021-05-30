import { IbzPlanTempletDetailAuthServiceBase } from './ibz-plan-templet-detail-auth-service-base';


/**
 * 计划模板详情权限服务对象
 *
 * @export
 * @class IbzPlanTempletDetailAuthService
 * @extends {IbzPlanTempletDetailAuthServiceBase}
 */
export default class IbzPlanTempletDetailAuthService extends IbzPlanTempletDetailAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzPlanTempletDetailAuthService}
     * @memberof IbzPlanTempletDetailAuthService
     */
    private static basicUIServiceInstance: IbzPlanTempletDetailAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzPlanTempletDetailAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzPlanTempletDetailAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzPlanTempletDetailAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzPlanTempletDetailAuthService
     */
     public static getInstance(context: any): IbzPlanTempletDetailAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzPlanTempletDetailAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzPlanTempletDetailAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzPlanTempletDetailAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzPlanTempletDetailAuthService({context:context}));
            }
            return IbzPlanTempletDetailAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}