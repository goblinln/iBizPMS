import { ProductPlanAuthServiceBase } from './product-plan-auth-service-base';


/**
 * 产品计划权限服务对象
 *
 * @export
 * @class ProductPlanAuthService
 * @extends {ProductPlanAuthServiceBase}
 */
export default class ProductPlanAuthService extends ProductPlanAuthServiceBase {

    /**
     * Creates an instance of  ProductPlanAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProductPlanAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}