import { IbzproProjectUserTaskAuthServiceBase } from './ibzpro-project-user-task-auth-service-base';


/**
 * 项目汇报用户任务权限服务对象
 *
 * @export
 * @class IbzproProjectUserTaskAuthService
 * @extends {IbzproProjectUserTaskAuthServiceBase}
 */
export default class IbzproProjectUserTaskAuthService extends IbzproProjectUserTaskAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzproProjectUserTaskAuthService}
     * @memberof IbzproProjectUserTaskAuthService
     */
    private static basicUIServiceInstance: IbzproProjectUserTaskAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzproProjectUserTaskAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzproProjectUserTaskAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzproProjectUserTaskAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzproProjectUserTaskAuthService
     */
     public static getInstance(context: any): IbzproProjectUserTaskAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzproProjectUserTaskAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzproProjectUserTaskAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzproProjectUserTaskAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzproProjectUserTaskAuthService({context:context}));
            }
            return IbzproProjectUserTaskAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}