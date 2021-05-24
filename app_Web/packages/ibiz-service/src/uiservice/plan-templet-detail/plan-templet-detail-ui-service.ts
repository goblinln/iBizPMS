import { PlanTempletDetailUIServiceBase } from './plan-templet-detail-ui-service-base';

/**
 * 计划模板详情UI服务对象
 *
 * @export
 * @class PlanTempletDetailUIService
 */
export default class PlanTempletDetailUIService extends PlanTempletDetailUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof PlanTempletDetailUIService
     */
    private static basicUIServiceInstance: PlanTempletDetailUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof PlanTempletDetailUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  PlanTempletDetailUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  PlanTempletDetailUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof PlanTempletDetailUIService
     */
    public static getInstance(context: any): PlanTempletDetailUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new PlanTempletDetailUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!PlanTempletDetailUIService.UIServiceMap.get(context.srfdynainstid)) {
                PlanTempletDetailUIService.UIServiceMap.set(context.srfdynainstid, new PlanTempletDetailUIService({context:context}));
            }
            return PlanTempletDetailUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}