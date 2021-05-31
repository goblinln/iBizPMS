import { ProductBranchUIServiceBase } from './product-branch-ui-service-base';

/**
 * 产品的分支和平台信息UI服务对象
 *
 * @export
 * @class ProductBranchUIService
 */
export default class ProductBranchUIService extends ProductBranchUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductBranchUIService
     */
    private static basicUIServiceInstance: ProductBranchUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductBranchUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductBranchUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductBranchUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductBranchUIService
     */
    public static getInstance(context: any): ProductBranchUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductBranchUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductBranchUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductBranchUIService.UIServiceMap.set(context.srfdynainstid, new ProductBranchUIService({context:context}));
            }
            return ProductBranchUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}