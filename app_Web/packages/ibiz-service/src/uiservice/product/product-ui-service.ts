import { ProductUIServiceBase } from './product-ui-service-base';

/**
 * 产品UI服务对象
 *
 * @export
 * @class ProductUIService
 */
export default class ProductUIService extends ProductUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductUIService
     */
    private static basicUIServiceInstance: ProductUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductUIService
     */
    public static getInstance(context: any): ProductUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductUIService.UIServiceMap.set(context.srfdynainstid, new ProductUIService({context:context}));
            }
            return ProductUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}