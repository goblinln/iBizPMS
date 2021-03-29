import { TestResultAuthServiceBase } from './test-result-auth-service-base';


/**
 * 测试结果权限服务对象
 *
 * @export
 * @class TestResultAuthService
 * @extends {TestResultAuthServiceBase}
 */
export default class TestResultAuthService extends TestResultAuthServiceBase {

    /**
     * Creates an instance of  TestResultAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestResultAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}