import { TaskTeamUIServiceBase } from './task-team-ui-service-base';

/**
 * 任务团队UI服务对象
 *
 * @export
 * @class TaskTeamUIService
 */
export default class TaskTeamUIService extends TaskTeamUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof TaskTeamUIService
     */
    private static basicUIServiceInstance: TaskTeamUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof TaskTeamUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  TaskTeamUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  TaskTeamUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof TaskTeamUIService
     */
    public static getInstance(context: any): TaskTeamUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new TaskTeamUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!TaskTeamUIService.UIServiceMap.get(context.srfdynainstid)) {
                TaskTeamUIService.UIServiceMap.set(context.srfdynainstid, new TaskTeamUIService({context:context}));
            }
            return TaskTeamUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}