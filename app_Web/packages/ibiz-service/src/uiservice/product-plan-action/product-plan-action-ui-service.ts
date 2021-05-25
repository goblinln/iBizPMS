import { ProductPlanActionUIServiceBase } from './product-plan-action-ui-service-base';

/**
 * 产品计划日志UI服务对象
 *
 * @export
 * @class ProductPlanActionUIService
 */
export default class ProductPlanActionUIService extends ProductPlanActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductPlanActionUIService
     */
    private static basicUIServiceInstance: ProductPlanActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductPlanActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductPlanActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductPlanActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductPlanActionUIService
     */
    public static getInstance(context: any): ProductPlanActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductPlanActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductPlanActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductPlanActionUIService.UIServiceMap.set(context.srfdynainstid, new ProductPlanActionUIService({context:context}));
            }
            return ProductPlanActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}