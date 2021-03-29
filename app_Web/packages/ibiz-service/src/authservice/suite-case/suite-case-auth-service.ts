import { SuiteCaseAuthServiceBase } from './suite-case-auth-service-base';


/**
 * 套件用例权限服务对象
 *
 * @export
 * @class SuiteCaseAuthService
 * @extends {SuiteCaseAuthServiceBase}
 */
export default class SuiteCaseAuthService extends SuiteCaseAuthServiceBase {

    /**
     * Creates an instance of  SuiteCaseAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SuiteCaseAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}