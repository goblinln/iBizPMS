import { ProductBranchAuthServiceBase } from './product-branch-auth-service-base';


/**
 * 产品的分支和平台信息权限服务对象
 *
 * @export
 * @class ProductBranchAuthService
 * @extends {ProductBranchAuthServiceBase}
 */
export default class ProductBranchAuthService extends ProductBranchAuthServiceBase {

    /**
     * Creates an instance of  ProductBranchAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductBranchAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}