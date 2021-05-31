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
     * 基础权限服务实例
     * 
     * @private
     * @type {StoryAuthService}
     * @memberof StoryAuthService
     */
    private static basicUIServiceInstance: StoryAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof StoryAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  StoryAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  StoryAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof StoryAuthService
     */
     public static getInstance(context: any): StoryAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new StoryAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!StoryAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                StoryAuthService.AuthServiceMap.set(context.srfdynainstid, new StoryAuthService({context:context}));
            }
            return StoryAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}