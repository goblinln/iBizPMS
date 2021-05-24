import { IbzproProductUserTaskAuthServiceBase } from './ibzpro-product-user-task-auth-service-base';


/**
 * 产品汇报用户任务权限服务对象
 *
 * @export
 * @class IbzproProductUserTaskAuthService
 * @extends {IbzproProductUserTaskAuthServiceBase}
 */
export default class IbzproProductUserTaskAuthService extends IbzproProductUserTaskAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {IbzproProductUserTaskAuthService}
     * @memberof IbzproProductUserTaskAuthService
     */
    private static basicUIServiceInstance: IbzproProductUserTaskAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof IbzproProductUserTaskAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzproProductUserTaskAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzproProductUserTaskAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzproProductUserTaskAuthService
     */
     public static getInstance(context: any): IbzproProductUserTaskAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzproProductUserTaskAuthService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzproProductUserTaskAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                IbzproProductUserTaskAuthService.AuthServiceMap.set(context.srfdynainstid, new IbzproProductUserTaskAuthService({context:context}));
            }
            return IbzproProductUserTaskAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}