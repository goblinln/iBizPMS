import { IBZTaskActionAuthServiceBase } from './ibztask-action-auth-service-base';


/**
 * 任务日志权限服务对象
 *
 * @export
 * @class IBZTaskActionAuthService
 * @extends {IBZTaskActionAuthServiceBase}
 */
export default class IBZTaskActionAuthService extends IBZTaskActionAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IBZTaskActionAuthService}
     * @memberof IBZTaskActionAuthService
     */
    private static basicUIServiceInstance: IBZTaskActionAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IBZTaskActionAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZTaskActionAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZTaskActionAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZTaskActionAuthService
     */
     public static getInstance(context: any): IBZTaskActionAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZTaskActionAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZTaskActionAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IBZTaskActionAuthService.AuthServiceMap.set(context.srfdynainstid, new IBZTaskActionAuthService({context:context}));
            }
            return IBZTaskActionAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}