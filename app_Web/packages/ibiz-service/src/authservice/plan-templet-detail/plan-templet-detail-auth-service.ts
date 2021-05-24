import { PlanTempletDetailAuthServiceBase } from './plan-templet-detail-auth-service-base';


/**
 * 计划模板详情权限服务对象
 *
 * @export
 * @class PlanTempletDetailAuthService
 * @extends {PlanTempletDetailAuthServiceBase}
 */
export default class PlanTempletDetailAuthService extends PlanTempletDetailAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {PlanTempletDetailAuthService}
     * @memberof PlanTempletDetailAuthService
     */
    private static basicUIServiceInstance: PlanTempletDetailAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof PlanTempletDetailAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  PlanTempletDetailAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  PlanTempletDetailAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof PlanTempletDetailAuthService
     */
     public static getInstance(context: any): PlanTempletDetailAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new PlanTempletDetailAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!PlanTempletDetailAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                PlanTempletDetailAuthService.AuthServiceMap.set(context.srfdynainstid, new PlanTempletDetailAuthService({context:context}));
            }
            return PlanTempletDetailAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}