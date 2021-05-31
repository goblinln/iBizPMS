import { ProductTeamUIServiceBase } from './product-team-ui-service-base';

/**
 * 产品团队UI服务对象
 *
 * @export
 * @class ProductTeamUIService
 */
export default class ProductTeamUIService extends ProductTeamUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProductTeamUIService
     */
    private static basicUIServiceInstance: ProductTeamUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProductTeamUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProductTeamUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductTeamUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProductTeamUIService
     */
    public static getInstance(context: any): ProductTeamUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProductTeamUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProductTeamUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProductTeamUIService.UIServiceMap.set(context.srfdynainstid, new ProductTeamUIService({context:context}));
            }
            return ProductTeamUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}