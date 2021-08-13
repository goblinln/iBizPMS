import { StoryStageAuthServiceBase } from './story-stage-auth-service-base';


/**
 * 需求阶段权限服务对象
 *
 * @export
 * @class StoryStageAuthService
 * @extends {StoryStageAuthServiceBase}
 */
export default class StoryStageAuthService extends StoryStageAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {StoryStageAuthService}
     * @memberof StoryStageAuthService
     */
    private static basicUIServiceInstance: StoryStageAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof StoryStageAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  StoryStageAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  StoryStageAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof StoryStageAuthService
     */
     public static getInstance(context: any): StoryStageAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new StoryStageAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!StoryStageAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                StoryStageAuthService.AuthServiceMap.set(context.srfdynainstid, new StoryStageAuthService({context:context}));
            }
            return StoryStageAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}