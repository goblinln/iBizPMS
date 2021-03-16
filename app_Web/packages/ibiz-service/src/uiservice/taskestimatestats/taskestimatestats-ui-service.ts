import { TaskestimatestatsUIServiceBase } from './taskestimatestats-ui-service-base';

/**
 * 任务工时统计UI服务对象
 *
 * @export
 * @class TaskestimatestatsUIService
 */
export default class TaskestimatestatsUIService extends TaskestimatestatsUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TaskestimatestatsUIService
     */
    private static basicUIServiceInstance: TaskestimatestatsUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TaskestimatestatsUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TaskestimatestatsUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskestimatestatsUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TaskestimatestatsUIService
     */
    public static getInstance(context: any): TaskestimatestatsUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TaskestimatestatsUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TaskestimatestatsUIService.UIServiceMap.get(context.srfdynainstid)) {
                TaskestimatestatsUIService.UIServiceMap.set(context.srfdynainstid, new TaskestimatestatsUIService({context:context}));
            }
            return TaskestimatestatsUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}