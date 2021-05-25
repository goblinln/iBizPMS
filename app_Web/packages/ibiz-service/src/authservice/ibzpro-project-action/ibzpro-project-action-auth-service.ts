import { IBZProProjectActionAuthServiceBase } from './ibzpro-project-action-auth-service-base';


/**
 * 项目日志权限服务对象
 *
 * @export
 * @class IBZProProjectActionAuthService
 * @extends {IBZProProjectActionAuthServiceBase}
 */
export default class IBZProProjectActionAuthService extends IBZProProjectActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZProProjectActionAuthService}
     * @memberof IBZProProjectActionAuthService
     */
    private static basicUIServiceInstance: IBZProProjectActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZProProjectActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProProjectActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProProjectActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProProjectActionAuthService
     */
     public static getInstance(context: any): IBZProProjectActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProProjectActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProProjectActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZProProjectActionAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZProProjectActionAuthService({context:context}));
            }
            return IBZProProjectActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}