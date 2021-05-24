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
     * 基础权限服务实例
     * 
     * @private
     * @type {SubStoryAuthService}
     * @memberof SubStoryAuthService
     */
    private static basicUIServiceInstance: SubStoryAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof SubStoryAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  SubStoryAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  SubStoryAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof SubStoryAuthService
     */
     public static getInstance(context: any): SubStoryAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new SubStoryAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!SubStoryAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                SubStoryAuthService.AuthServiceMap.set(context.srfdynainstid, new SubStoryAuthService({context:context}));
            }
            return SubStoryAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}