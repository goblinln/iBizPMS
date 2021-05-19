import { ProductLineAuthServiceBase } from './product-line-auth-service-base';


/**
 * 产品线（废弃）权限服务对象
 *
 * @export
 * @class ProductLineAuthService
 * @extends {ProductLineAuthServiceBase}
 */
export default class ProductLineAuthService extends ProductLineAuthServiceBase {

    /**
     * Creates an instance of  ProductLineAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductLineAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}