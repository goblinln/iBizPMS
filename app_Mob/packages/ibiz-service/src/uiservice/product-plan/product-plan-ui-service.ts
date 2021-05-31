import { ProductPlanUIServiceBase } from './product-plan-ui-service-base';

/**
 * 产品计划UI服务对象
 *
 * @export
 * @class ProductPlanUIService
 */
export default class ProductPlanUIService extends ProductPlanUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductPlanUIService
     */
    private static basicUIServiceInstance: ProductPlanUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductPlanUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductPlanUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductPlanUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductPlanUIService
     */
    public static getInstance(context: any): ProductPlanUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductPlanUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductPlanUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductPlanUIService.UIServiceMap.set(context.srfdynainstid, new ProductPlanUIService({context:context}));
            }
            return ProductPlanUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}