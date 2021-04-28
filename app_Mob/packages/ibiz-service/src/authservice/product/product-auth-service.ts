import { ProductAuthServiceBase } from './product-auth-service-base';


/**
 * 产品权限服务对象
 *
 * @export
 * @class ProductAuthService
 * @extends {ProductAuthServiceBase}
 */
export default class ProductAuthService extends ProductAuthServiceBase {

    /**
     * Creates an instance of  ProductAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}