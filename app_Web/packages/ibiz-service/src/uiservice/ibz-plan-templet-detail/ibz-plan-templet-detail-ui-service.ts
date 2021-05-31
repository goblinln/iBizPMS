import { IbzPlanTempletDetailUIServiceBase } from './ibz-plan-templet-detail-ui-service-base';

/**
 * 计划模板详情UI服务对象
 *
 * @export
 * @class IbzPlanTempletDetailUIService
 */
export default class IbzPlanTempletDetailUIService extends IbzPlanTempletDetailUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzPlanTempletDetailUIService
     */
    private static basicUIServiceInstance: IbzPlanTempletDetailUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzPlanTempletDetailUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzPlanTempletDetailUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzPlanTempletDetailUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzPlanTempletDetailUIService
     */
    public static getInstance(context: any): IbzPlanTempletDetailUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzPlanTempletDetailUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzPlanTempletDetailUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzPlanTempletDetailUIService.UIServiceMap.set(context.srfdynainstid, new IbzPlanTempletDetailUIService({context:context}));
            }
            return IbzPlanTempletDetailUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}