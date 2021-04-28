import { BranchAuthServiceBase } from './branch-auth-service-base';


/**
 * 产品的分支和平台信息权限服务对象
 *
 * @export
 * @class BranchAuthService
 * @extends {BranchAuthServiceBase}
 */
export default class BranchAuthService extends BranchAuthServiceBase {

    /**
     * Creates an instance of  BranchAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  BranchAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}