import { TestTaskAuthServiceBase } from './test-task-auth-service-base';


/**
 * 测试版本权限服务对象
 *
 * @export
 * @class TestTaskAuthService
 * @extends {TestTaskAuthServiceBase}
 */
export default class TestTaskAuthService extends TestTaskAuthServiceBase {

    /**
     * Creates an instance of  TestTaskAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestTaskAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}