import { ProductLifeUIServiceBase } from './product-life-ui-service-base';

/**
 * 产品生命周期UI服务对象
 *
 * @export
 * @class ProductLifeUIService
 */
export default class ProductLifeUIService extends ProductLifeUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductLifeUIService
     */
    private static basicUIServiceInstance: ProductLifeUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductLifeUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductLifeUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductLifeUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductLifeUIService
     */
    public static getInstance(context: any): ProductLifeUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductLifeUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductLifeUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductLifeUIService.UIServiceMap.set(context.srfdynainstid, new ProductLifeUIService({context:context}));
            }
            return ProductLifeUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}