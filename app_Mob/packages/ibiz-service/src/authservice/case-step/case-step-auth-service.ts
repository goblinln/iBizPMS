import { CaseStepAuthServiceBase } from './case-step-auth-service-base';


/**
 * 用例步骤权限服务对象
 *
 * @export
 * @class CaseStepAuthService
 * @extends {CaseStepAuthServiceBase}
 */
export default class CaseStepAuthService extends CaseStepAuthServiceBase {

    /**
     * Creates an instance of  CaseStepAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  CaseStepAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}