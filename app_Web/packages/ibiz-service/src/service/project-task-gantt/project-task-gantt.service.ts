import { ProjectTaskGanttBaseService } from './project-task-gantt-base.service';

/**
 * 任务服务
 *
 * @export
 * @class ProjectTaskGanttService
 * @extends {ProjectTaskGanttBaseService}
 */
export class ProjectTaskGanttService extends ProjectTaskGanttBaseService {
    /**
     * Creates an instance of ProjectTaskGanttService.
     * @memberof ProjectTaskGanttService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectTaskGanttService')) {
            return ___ibz___.sc.get('ProjectTaskGanttService');
        }
        ___ibz___.sc.set('ProjectTaskGanttService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectTaskGanttService}
     * @memberof ProjectTaskGanttService
     */
    static getInstance(): ProjectTaskGanttService {
        if (!___ibz___.sc.has('ProjectTaskGanttService')) {
            new ProjectTaskGanttService();
        }
        return ___ibz___.sc.get('ProjectTaskGanttService');
    }
}
export default ProjectTaskGanttService;
