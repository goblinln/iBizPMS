import { StoryAuthServiceBase } from './story-auth-service-base';


/**
 * 需求权限服务对象
 *
 * @export
 * @class StoryAuthService
 * @extends {StoryAuthServiceBase}
 */
export default class StoryAuthService extends StoryAuthServiceBase {

    /**
     * Creates an instance of  StoryAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  StoryAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}