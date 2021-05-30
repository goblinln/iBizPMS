import { ProjectTaskReportUIServiceBase } from './project-task-report-ui-service-base';

/**
 * 任务UI服务对象
 *
 * @export
 * @class ProjectTaskReportUIService
 */
export default class ProjectTaskReportUIService extends ProjectTaskReportUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ProjectTaskReportUIService
     */
    private static basicUIServiceInstance: ProjectTaskReportUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ProjectTaskReportUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ProjectTaskReportUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ProjectTaskReportUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ProjectTaskReportUIService
     */
    public static getInstance(context: any): ProjectTaskReportUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ProjectTaskReportUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ProjectTaskReportUIService.UIServiceMap.get(context.srfdynainstid)) {
                ProjectTaskReportUIService.UIServiceMap.set(context.srfdynainstid, new ProjectTaskReportUIService({context:context}));
            }
            return ProjectTaskReportUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}