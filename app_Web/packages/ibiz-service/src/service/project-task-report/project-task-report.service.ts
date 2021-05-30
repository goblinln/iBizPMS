import { ProjectTaskReportBaseService } from './project-task-report-base.service';

/**
 * 任务服务
 *
 * @export
 * @class ProjectTaskReportService
 * @extends {ProjectTaskReportBaseService}
 */
export class ProjectTaskReportService extends ProjectTaskReportBaseService {
    /**
     * Creates an instance of ProjectTaskReportService.
     * @memberof ProjectTaskReportService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectTaskReportService')) {
            return ___ibz___.sc.get('ProjectTaskReportService');
        }
        ___ibz___.sc.set('ProjectTaskReportService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectTaskReportService}
     * @memberof ProjectTaskReportService
     */
    static getInstance(): ProjectTaskReportService {
        if (!___ibz___.sc.has('ProjectTaskReportService')) {
            new ProjectTaskReportService();
        }
        return ___ibz___.sc.get('ProjectTaskReportService');
    }
}
export default ProjectTaskReportService;
