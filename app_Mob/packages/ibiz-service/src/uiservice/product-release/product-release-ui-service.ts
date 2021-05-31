import { ProductReleaseUIServiceBase } from './product-release-ui-service-base';

/**
 * 发布UI服务对象
 *
 * @export
 * @class ProductReleaseUIService
 */
export default class ProductReleaseUIService extends ProductReleaseUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductReleaseUIService
     */
    private static basicUIServiceInstance: ProductReleaseUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductReleaseUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductReleaseUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductReleaseUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductReleaseUIService
     */
    public static getInstance(context: any): ProductReleaseUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductReleaseUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductReleaseUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductReleaseUIService.UIServiceMap.set(context.srfdynainstid, new ProductReleaseUIService({context:context}));
            }
            return ProductReleaseUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}