import { ProductModuleAuthServiceBase } from './product-module-auth-service-base';


/**
 * 需求模块权限服务对象
 *
 * @export
 * @class ProductModuleAuthService
 * @extends {ProductModuleAuthServiceBase}
 */
export default class ProductModuleAuthService extends ProductModuleAuthServiceBase {

    /**
     * Creates an instance of  ProductModuleAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductModuleAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}