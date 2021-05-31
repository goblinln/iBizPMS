import { ProjectTaskReportBase } from './project-task-report-base';

/**
 * 任务
 *
 * @export
 * @class ProjectTaskReport
 * @extends {ProjectTaskReportBase}
 * @implements {IProjectTaskReport}
 */
export class ProjectTaskReport extends ProjectTaskReportBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectTaskReport
     */
    clone(): ProjectTaskReport {
        return new ProjectTaskReport(this);
    }
}
export default ProjectTaskReport;
