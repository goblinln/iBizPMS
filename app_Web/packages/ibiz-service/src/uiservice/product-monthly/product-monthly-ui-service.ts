import { ProductMonthlyUIServiceBase } from './product-monthly-ui-service-base';

/**
 * 产品月报UI服务对象
 *
 * @export
 * @class ProductMonthlyUIService
 */
export default class ProductMonthlyUIService extends ProductMonthlyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductMonthlyUIService
     */
    private static basicUIServiceInstance: ProductMonthlyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductMonthlyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductMonthlyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductMonthlyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductMonthlyUIService
     */
    public static getInstance(context: any): ProductMonthlyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductMonthlyUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductMonthlyUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductMonthlyUIService.UIServiceMap.set(context.srfdynainstid, new ProductMonthlyUIService({context:context}));
            }
            return ProductMonthlyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}