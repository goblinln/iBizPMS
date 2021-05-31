import { TodoAuthServiceBase } from './todo-auth-service-base';


/**
 * 待办权限服务对象
 *
 * @export
 * @class TodoAuthService
 * @extends {TodoAuthServiceBase}
 */
export default class TodoAuthService extends TodoAuthServiceBase {

    /**
     * 基础权限服务实例
     * 
     * @private
     * @type {TodoAuthService}
     * @memberof TodoAuthService
     */
    private static basicUIServiceInstance: TodoAuthService;

     /**
      * 动态模型服务存储Map对象
      *
      * @private
      * @type {Map<string, any>}
      * @memberof TodoAuthService
      */
    private static AuthServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TodoAuthService.
     * 
     * @param {*} [opts={}]
     * @memberof  TodoAuthService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TodoAuthService
     */
     public static getInstance(context: any): TodoAuthService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TodoAuthService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TodoAuthService.AuthServiceMap.get(context.srfdynainstid)) {
                TodoAuthService.AuthServiceMap.set(context.srfdynainstid, new TodoAuthService({context:context}));
            }
            return TodoAuthService.AuthServiceMap.get(context.srfdynainstid);
        }
    }


}