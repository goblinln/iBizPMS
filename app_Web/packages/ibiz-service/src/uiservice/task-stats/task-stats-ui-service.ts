import { TaskStatsUIServiceBase } from './task-stats-ui-service-base';

/**
 * 任务统计UI服务对象
 *
 * @export
 * @class TaskStatsUIService
 */
export default class TaskStatsUIService extends TaskStatsUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TaskStatsUIService
     */
    private static basicUIServiceInstance: TaskStatsUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TaskStatsUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TaskStatsUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskStatsUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TaskStatsUIService
     */
    public static getInstance(context: any): TaskStatsUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TaskStatsUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TaskStatsUIService.UIServiceMap.get(context.srfdynainstid)) {
                TaskStatsUIService.UIServiceMap.set(context.srfdynainstid, new TaskStatsUIService({context:context}));
            }
            return TaskStatsUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}