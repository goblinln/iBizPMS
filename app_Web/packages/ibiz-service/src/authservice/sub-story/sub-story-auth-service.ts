import { SubStoryAuthServiceBase } from './sub-story-auth-service-base';


/**
 * 需求权限服务对象
 *
 * @export
 * @class SubStoryAuthService
 * @extends {SubStoryAuthServiceBase}
 */
export default class SubStoryAuthService extends SubStoryAuthServiceBase {

    /**
     * Creates an instance of  SubStoryAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SubStoryAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}