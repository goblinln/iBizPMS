import { CaseAuthServiceBase } from './case-auth-service-base';


/**
 * 测试用例权限服务对象
 *
 * @export
 * @class CaseAuthService
 * @extends {CaseAuthServiceBase}
 */
export default class CaseAuthService extends CaseAuthServiceBase {

    /**
     * Creates an instance of  CaseAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  CaseAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}