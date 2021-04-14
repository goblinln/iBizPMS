import { TestSuiteAuthServiceBase } from './test-suite-auth-service-base';


/**
 * 测试套件权限服务对象
 *
 * @export
 * @class TestSuiteAuthService
 * @extends {TestSuiteAuthServiceBase}
 */
export default class TestSuiteAuthService extends TestSuiteAuthServiceBase {

    /**
     * Creates an instance of  TestSuiteAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestSuiteAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}