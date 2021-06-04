import { ProductDailyUIServiceBase } from './product-daily-ui-service-base';

/**
 * 产品日报UI服务对象
 *
 * @export
 * @class ProductDailyUIService
 */
export default class ProductDailyUIService extends ProductDailyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductDailyUIService
     */
    private static basicUIServiceInstance: ProductDailyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductDailyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductDailyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductDailyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductDailyUIService
     */
    public static getInstance(context: any): ProductDailyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductDailyUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductDailyUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductDailyUIService.UIServiceMap.set(context.srfdynainstid, new ProductDailyUIService({context:context}));
            }
            return ProductDailyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}