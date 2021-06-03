import { MyTaskAuthServiceBase } from './my-task-auth-service-base';


/**
 * 任务权限服务对象
 *
 * @export
 * @class MyTaskAuthService
 * @extends {MyTaskAuthServiceBase}
 */
export default class MyTaskAuthService extends MyTaskAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {MyTaskAuthService}
     * @memberof MyTaskAuthService
     */
    private static basicUIServiceInstance: MyTaskAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof MyTaskAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  MyTaskAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  MyTaskAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof MyTaskAuthService
     */
     public static getInstance(context: any): MyTaskAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new MyTaskAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!MyTaskAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                MyTaskAuthService.AuthServiceMap.set(context.srfdynainstid, new MyTaskAuthService({context:context}));
            }
            return MyTaskAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}