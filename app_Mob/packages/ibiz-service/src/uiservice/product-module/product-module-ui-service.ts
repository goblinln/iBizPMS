import { ProductModuleUIServiceBase } from './product-module-ui-service-base';

/**
 * 需求模块UI服务对象
 *
 * @export
 * @class ProductModuleUIService
 */
export default class ProductModuleUIService extends ProductModuleUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductModuleUIService
     */
    private static basicUIServiceInstance: ProductModuleUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductModuleUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductModuleUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductModuleUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductModuleUIService
     */
    public static getInstance(context: any): ProductModuleUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductModuleUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductModuleUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductModuleUIService.UIServiceMap.set(context.srfdynainstid, new ProductModuleUIService({context:context}));
            }
            return ProductModuleUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}