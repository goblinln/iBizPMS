import { ProductBuildUIServiceBase } from './product-build-ui-service-base';

/**
 * 版本UI服务对象
 *
 * @export
 * @class ProductBuildUIService
 */
export default class ProductBuildUIService extends ProductBuildUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductBuildUIService
     */
    private static basicUIServiceInstance: ProductBuildUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductBuildUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductBuildUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductBuildUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductBuildUIService
     */
    public static getInstance(context: any): ProductBuildUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductBuildUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductBuildUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductBuildUIService.UIServiceMap.set(context.srfdynainstid, new ProductBuildUIService({context:context}));
            }
            return ProductBuildUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}