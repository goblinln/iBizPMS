import { IBZStoryActionAuthServiceBase } from './ibzstory-action-auth-service-base';


/**
 * 需求日志权限服务对象
 *
 * @export
 * @class IBZStoryActionAuthService
 * @extends {IBZStoryActionAuthServiceBase}
 */
export default class IBZStoryActionAuthService extends IBZStoryActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZStoryActionAuthService}
     * @memberof IBZStoryActionAuthService
     */
    private static basicUIServiceInstance: IBZStoryActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZStoryActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZStoryActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZStoryActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZStoryActionAuthService
     */
     public static getInstance(context: any): IBZStoryActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZStoryActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZStoryActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZStoryActionAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZStoryActionAuthService({context:context}));
            }
            return IBZStoryActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}