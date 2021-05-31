import { ProductReleaseAuthServiceBase } from './product-release-auth-service-base';


/**
 * 发布权限服务对象
 *
 * @export
 * @class ProductReleaseAuthService
 * @extends {ProductReleaseAuthServiceBase}
 */
export default class ProductReleaseAuthService extends ProductReleaseAuthServiceBase {

    /**
     * Creates an instance of  ProductReleaseAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductReleaseAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}