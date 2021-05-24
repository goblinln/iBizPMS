import { TodoUIServiceBase } from './todo-ui-service-base';

/**
 * 待办UI服务对象
 *
 * @export
 * @class TodoUIService
 */
export default class TodoUIService extends TodoUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TodoUIService
     */
    private static basicUIServiceInstance: TodoUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TodoUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TodoUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TodoUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TodoUIService
     */
    public static getInstance(context: any): TodoUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TodoUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TodoUIService.UIServiceMap.get(context.srfdynainstid)) {
                TodoUIService.UIServiceMap.set(context.srfdynainstid, new TodoUIService({context:context}));
            }
            return TodoUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}