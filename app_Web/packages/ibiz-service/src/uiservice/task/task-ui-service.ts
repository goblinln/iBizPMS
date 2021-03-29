import { TaskUIServiceBase } from './task-ui-service-base';

/**
 * 任务UI服务对象
 *
 * @export
 * @class TaskUIService
 */
export default class TaskUIService extends TaskUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TaskUIService
     */
    private static basicUIServiceInstance: TaskUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TaskUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TaskUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TaskUIService
     */
    public static getInstance(context: any): TaskUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TaskUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TaskUIService.UIServiceMap.get(context.srfdynainstid)) {
                TaskUIService.UIServiceMap.set(context.srfdynainstid, new TaskUIService({context:context}));
            }
            return TaskUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}