import { EmpLoyeeloadAuthServiceBase } from './emp-loyeeload-auth-service-base';


/**
 * 员工负载表权限服务对象
 *
 * @export
 * @class EmpLoyeeloadAuthService
 * @extends {EmpLoyeeloadAuthServiceBase}
 */
export default class EmpLoyeeloadAuthService extends EmpLoyeeloadAuthServiceBase {

    /**
     * Creates an instance of  EmpLoyeeloadAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  EmpLoyeeloadAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}