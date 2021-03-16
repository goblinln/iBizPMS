import { BuildAuthServiceBase } from './build-auth-service-base';


/**
 * 版本权限服务对象
 *
 * @export
 * @class BuildAuthService
 * @extends {BuildAuthServiceBase}
 */
export default class BuildAuthService extends BuildAuthServiceBase {

    /**
     * Creates an instance of  BuildAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  BuildAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}