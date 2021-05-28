import { ProductStoryUIServiceBase } from './product-story-ui-service-base';

/**
 * 需求UI服务对象
 *
 * @export
 * @class ProductStoryUIService
 */
export default class ProductStoryUIService extends ProductStoryUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductStoryUIService
     */
    private static basicUIServiceInstance: ProductStoryUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductStoryUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductStoryUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductStoryUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductStoryUIService
     */
    public static getInstance(context: any): ProductStoryUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductStoryUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductStoryUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductStoryUIService.UIServiceMap.set(context.srfdynainstid, new ProductStoryUIService({context:context}));
            }
            return ProductStoryUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}