import { ProductLineUIServiceBase } from './product-line-ui-service-base';

/**
 * 产品线（废弃）UI服务对象
 *
 * @export
 * @class ProductLineUIService
 */
export default class ProductLineUIService extends ProductLineUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductLineUIService
     */
    private static basicUIServiceInstance: ProductLineUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductLineUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductLineUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductLineUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductLineUIService
     */
    public static getInstance(context: any): ProductLineUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductLineUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductLineUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductLineUIService.UIServiceMap.set(context.srfdynainstid, new ProductLineUIService({context:context}));
            }
            return ProductLineUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}