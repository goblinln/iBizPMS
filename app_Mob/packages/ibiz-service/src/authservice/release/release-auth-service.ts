import { ReleaseAuthServiceBase } from './release-auth-service-base';


/**
 * 发布权限服务对象
 *
 * @export
 * @class ReleaseAuthService
 * @extends {ReleaseAuthServiceBase}
 */
export default class ReleaseAuthService extends ReleaseAuthServiceBase {

    /**
     * Creates an instance of  ReleaseAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  ReleaseAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}