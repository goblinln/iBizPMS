import { ProductSumUIServiceBase } from './product-sum-ui-service-base';

/**
 * 产品汇总表UI服务对象
 *
 * @export
 * @class ProductSumUIService
 */
export default class ProductSumUIService extends ProductSumUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductSumUIService
     */
    private static basicUIServiceInstance: ProductSumUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductSumUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductSumUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductSumUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductSumUIService
     */
    public static getInstance(context: any): ProductSumUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductSumUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductSumUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductSumUIService.UIServiceMap.set(context.srfdynainstid, new ProductSumUIService({context:context}));
            }
            return ProductSumUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}