import { IBZProStoryModuleAuthServiceBase } from './ibzpro-story-module-auth-service-base';


/**
 * 需求模块权限服务对象
 *
 * @export
 * @class IBZProStoryModuleAuthService
 * @extends {IBZProStoryModuleAuthServiceBase}
 */
export default class IBZProStoryModuleAuthService extends IBZProStoryModuleAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZProStoryModuleAuthService}
     * @memberof IBZProStoryModuleAuthService
     */
    private static basicUIServiceInstance: IBZProStoryModuleAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZProStoryModuleAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProStoryModuleAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProStoryModuleAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProStoryModuleAuthService
     */
     public static getInstance(context: any): IBZProStoryModuleAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProStoryModuleAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProStoryModuleAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZProStoryModuleAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZProStoryModuleAuthService({context:context}));
            }
            return IBZProStoryModuleAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}