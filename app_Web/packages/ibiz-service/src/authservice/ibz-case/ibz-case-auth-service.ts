import { IbzCaseAuthServiceBase } from './ibz-case-auth-service-base';


/**
 * 测试用例权限服务对象
 *
 * @export
 * @class IbzCaseAuthService
 * @extends {IbzCaseAuthServiceBase}
 */
export default class IbzCaseAuthService extends IbzCaseAuthServiceBase {

    /**
     * Creates an instance of  IbzCaseAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzCaseAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}