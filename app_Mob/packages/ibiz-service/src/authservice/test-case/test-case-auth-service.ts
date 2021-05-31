import { TestCaseAuthServiceBase } from './test-case-auth-service-base';


/**
 * 测试用例权限服务对象
 *
 * @export
 * @class TestCaseAuthService
 * @extends {TestCaseAuthServiceBase}
 */
export default class TestCaseAuthService extends TestCaseAuthServiceBase {

    /**
     * Creates an instance of  TestCaseAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestCaseAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}