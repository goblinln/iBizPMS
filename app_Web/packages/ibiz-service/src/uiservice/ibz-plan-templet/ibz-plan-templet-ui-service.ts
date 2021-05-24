import { IbzPlanTempletUIServiceBase } from './ibz-plan-templet-ui-service-base';

/**
 * 计划模板UI服务对象
 *
 * @export
 * @class IbzPlanTempletUIService
 */
export default class IbzPlanTempletUIService extends IbzPlanTempletUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzPlanTempletUIService
     */
    private static basicUIServiceInstance: IbzPlanTempletUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzPlanTempletUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzPlanTempletUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzPlanTempletUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzPlanTempletUIService
     */
    public static getInstance(context: any): IbzPlanTempletUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzPlanTempletUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzPlanTempletUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzPlanTempletUIService.UIServiceMap.set(context.srfdynainstid, new IbzPlanTempletUIService({context:context}));
            }
            return IbzPlanTempletUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}