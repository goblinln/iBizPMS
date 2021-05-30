import { ProductBugUIServiceBase } from './product-bug-ui-service-base';

/**
 * BugUI服务对象
 *
 * @export
 * @class ProductBugUIService
 */
export default class ProductBugUIService extends ProductBugUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductBugUIService
     */
    private static basicUIServiceInstance: ProductBugUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductBugUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductBugUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductBugUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductBugUIService
     */
    public static getInstance(context: any): ProductBugUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductBugUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductBugUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductBugUIService.UIServiceMap.set(context.srfdynainstid, new ProductBugUIService({context:context}));
            }
            return ProductBugUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}