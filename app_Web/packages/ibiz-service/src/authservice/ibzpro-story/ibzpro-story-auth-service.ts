import { IBZProStoryAuthServiceBase } from './ibzpro-story-auth-service-base';


/**
 * 需求权限服务对象
 *
 * @export
 * @class IBZProStoryAuthService
 * @extends {IBZProStoryAuthServiceBase}
 */
export default class IBZProStoryAuthService extends IBZProStoryAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZProStoryAuthService}
     * @memberof IBZProStoryAuthService
     */
    private static basicUIServiceInstance: IBZProStoryAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZProStoryAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProStoryAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProStoryAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProStoryAuthService
     */
     public static getInstance(context: any): IBZProStoryAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProStoryAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProStoryAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZProStoryAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZProStoryAuthService({context:context}));
            }
            return IBZProStoryAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}