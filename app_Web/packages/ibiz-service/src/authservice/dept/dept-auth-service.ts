import { DeptAuthServiceBase } from './dept-auth-service-base';


/**
 * 部门权限服务对象
 *
 * @export
 * @class DeptAuthService
 * @extends {DeptAuthServiceBase}
 */
export default class DeptAuthService extends DeptAuthServiceBase {

    /**
     * Creates an instance of  DeptAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  DeptAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}