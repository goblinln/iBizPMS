import { ProductStatsUIServiceBase } from './product-stats-ui-service-base';

/**
 * 产品统计UI服务对象
 *
 * @export
 * @class ProductStatsUIService
 */
export default class ProductStatsUIService extends ProductStatsUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductStatsUIService
     */
    private static basicUIServiceInstance: ProductStatsUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductStatsUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductStatsUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductStatsUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductStatsUIService
     */
    public static getInstance(context: any): ProductStatsUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductStatsUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductStatsUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductStatsUIService.UIServiceMap.set(context.srfdynainstid, new ProductStatsUIService({context:context}));
            }
            return ProductStatsUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}