import { ProductProjectUIServiceBase } from './product-project-ui-service-base';

/**
 * 项目UI服务对象
 *
 * @export
 * @class ProductProjectUIService
 */
export default class ProductProjectUIService extends ProductProjectUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductProjectUIService
     */
    private static basicUIServiceInstance: ProductProjectUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductProjectUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductProjectUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductProjectUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductProjectUIService
     */
    public static getInstance(context: any): ProductProjectUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductProjectUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductProjectUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductProjectUIService.UIServiceMap.set(context.srfdynainstid, new ProductProjectUIService({context:context}));
            }
            return ProductProjectUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}