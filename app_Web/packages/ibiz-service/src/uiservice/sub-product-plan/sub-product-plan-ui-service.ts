import { SubProductPlanUIServiceBase } from './sub-product-plan-ui-service-base';

/**
 * 产品计划UI服务对象
 *
 * @export
 * @class SubProductPlanUIService
 */
export default class SubProductPlanUIService extends SubProductPlanUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof SubProductPlanUIService
     */
    private static basicUIServiceInstance: SubProductPlanUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof SubProductPlanUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SubProductPlanUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  SubProductPlanUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SubProductPlanUIService
     */
    public static getInstance(context: any): SubProductPlanUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SubProductPlanUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SubProductPlanUIService.UIServiceMap.get(context.srfdynainstid)) {
                SubProductPlanUIService.UIServiceMap.set(context.srfdynainstid, new SubProductPlanUIService({context:context}));
            }
            return SubProductPlanUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}