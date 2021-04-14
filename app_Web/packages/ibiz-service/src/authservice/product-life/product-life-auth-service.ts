import { ProductLifeAuthServiceBase } from './product-life-auth-service-base';


/**
 * 产品生命周期权限服务对象
 *
 * @export
 * @class ProductLifeAuthService
 * @extends {ProductLifeAuthServiceBase}
 */
export default class ProductLifeAuthService extends ProductLifeAuthServiceBase {

    /**
     * Creates an instance of  ProductLifeAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductLifeAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}