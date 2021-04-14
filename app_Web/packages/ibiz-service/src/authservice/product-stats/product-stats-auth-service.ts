import { ProductStatsAuthServiceBase } from './product-stats-auth-service-base';


/**
 * 产品统计权限服务对象
 *
 * @export
 * @class ProductStatsAuthService
 * @extends {ProductStatsAuthServiceBase}
 */
export default class ProductStatsAuthService extends ProductStatsAuthServiceBase {

    /**
     * Creates an instance of  ProductStatsAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductStatsAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}