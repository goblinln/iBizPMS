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
     * 基础权限服务实例
     * 
     * @private
     * @type {StorySpecAuthService}
     * @memberof StorySpecAuthService
     */
    private static basicUIServiceInstance: StorySpecAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof StorySpecAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  StorySpecAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  StorySpecAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof StorySpecAuthService
     */
     public static getInstance(context: any): StorySpecAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new StorySpecAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!StorySpecAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                StorySpecAuthService.AuthServiceMap.set(context.srfdynainstid, new StorySpecAuthService({context:context}));
            }
            return StorySpecAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}