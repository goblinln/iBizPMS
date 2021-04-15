import { TaskEstimateUIServiceBase } from './task-estimate-ui-service-base';

/**
 * 任务预计UI服务对象
 *
 * @export
 * @class TaskEstimateUIService
 */
export default class TaskEstimateUIService extends TaskEstimateUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TaskEstimateUIService
     */
    private static basicUIServiceInstance: TaskEstimateUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TaskEstimateUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TaskEstimateUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskEstimateUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TaskEstimateUIService
     */
    public static getInstance(context: any): TaskEstimateUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TaskEstimateUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TaskEstimateUIService.UIServiceMap.get(context.srfdynainstid)) {
                TaskEstimateUIService.UIServiceMap.set(context.srfdynainstid, new TaskEstimateUIService({context:context}));
            }
            return TaskEstimateUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}