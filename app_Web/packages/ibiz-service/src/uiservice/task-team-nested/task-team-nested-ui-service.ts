import { TaskTeamNestedUIServiceBase } from './task-team-nested-ui-service-base';

/**
 * 任务团队UI服务对象
 *
 * @export
 * @class TaskTeamNestedUIService
 */
export default class TaskTeamNestedUIService extends TaskTeamNestedUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TaskTeamNestedUIService
     */
    private static basicUIServiceInstance: TaskTeamNestedUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TaskTeamNestedUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TaskTeamNestedUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskTeamNestedUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TaskTeamNestedUIService
     */
    public static getInstance(context: any): TaskTeamNestedUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TaskTeamNestedUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TaskTeamNestedUIService.UIServiceMap.get(context.srfdynainstid)) {
                TaskTeamNestedUIService.UIServiceMap.set(context.srfdynainstid, new TaskTeamNestedUIService({context:context}));
            }
            return TaskTeamNestedUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}