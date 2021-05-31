import { TestAuthServiceBase } from './test-auth-service-base';


/**
 * 产品权限服务对象
 *
 * @export
 * @class TestAuthService
 * @extends {TestAuthServiceBase}
 */
export default class TestAuthService extends TestAuthServiceBase {

    /**
     * Creates an instance of  TestAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TestAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}