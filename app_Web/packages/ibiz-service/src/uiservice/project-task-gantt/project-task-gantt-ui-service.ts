import { ProjectTaskGanttUIServiceBase } from './project-task-gantt-ui-service-base';

/**
 * 任务UI服务对象
 *
 * @export
 * @class ProjectTaskGanttUIService
 */
export default class ProjectTaskGanttUIService extends ProjectTaskGanttUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectTaskGanttUIService
     */
    private static basicUIServiceInstance: ProjectTaskGanttUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectTaskGanttUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectTaskGanttUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTaskGanttUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectTaskGanttUIService
     */
    public static getInstance(context: any): ProjectTaskGanttUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectTaskGanttUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectTaskGanttUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectTaskGanttUIService.UIServiceMap.set(context.srfdynainstid, new ProjectTaskGanttUIService({context:context}));
            }
            return ProjectTaskGanttUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}