import { ProductSumAuthServiceBase } from './product-sum-auth-service-base';


/**
 * 产品汇总表权限服务对象
 *
 * @export
 * @class ProductSumAuthService
 * @extends {ProductSumAuthServiceBase}
 */
export default class ProductSumAuthService extends ProductSumAuthServiceBase {

    /**
     * Creates an instance of  ProductSumAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductSumAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}