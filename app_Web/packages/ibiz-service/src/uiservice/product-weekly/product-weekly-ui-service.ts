import { ProductWeeklyUIServiceBase } from './product-weekly-ui-service-base';

/**
 * 产品周报UI服务对象
 *
 * @export
 * @class ProductWeeklyUIService
 */
export default class ProductWeeklyUIService extends ProductWeeklyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductWeeklyUIService
     */
    private static basicUIServiceInstance: ProductWeeklyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductWeeklyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductWeeklyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductWeeklyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductWeeklyUIService
     */
    public static getInstance(context: any): ProductWeeklyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductWeeklyUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductWeeklyUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductWeeklyUIService.UIServiceMap.set(context.srfdynainstid, new ProductWeeklyUIService({context:context}));
            }
            return ProductWeeklyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}