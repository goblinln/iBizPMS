import { StorySpecAuthServiceBase } from './story-spec-auth-service-base';


/**
 * 需求描述权限服务对象
 *
 * @export
 * @class StorySpecAuthService
 * @extends {StorySpecAuthServiceBase}
 */
export default class StorySpecAuthService extends StorySpecAuthServiceBase {

    /**
     * Creates an instance of  StorySpecAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  StorySpecAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

}