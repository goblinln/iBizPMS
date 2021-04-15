import { TestRunAuthServiceBase } from './test-run-auth-service-base';


/**
 * 测试运行权限服务对象
 *
 * @export
 * @class TestRunAuthService
 * @extends {TestRunAuthServiceBase}
 */
export default class TestRunAuthService extends TestRunAuthServiceBase {

    /**
     * Creates an instance of  TestRunAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestRunAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}