import { IBZProToDoActionAuthServiceBase } from './ibzpro-to-do-action-auth-service-base';


/**
 * ToDo日志权限服务对象
 *
 * @export
 * @class IBZProToDoActionAuthService
 * @extends {IBZProToDoActionAuthServiceBase}
 */
export default class IBZProToDoActionAuthService extends IBZProToDoActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZProToDoActionAuthService}
     * @memberof IBZProToDoActionAuthService
     */
    private static basicUIServiceInstance: IBZProToDoActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZProToDoActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProToDoActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProToDoActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProToDoActionAuthService
     */
     public static getInstance(context: any): IBZProToDoActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProToDoActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProToDoActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZProToDoActionAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZProToDoActionAuthService({context:context}));
            }
            return IBZProToDoActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}