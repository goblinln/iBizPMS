import { ProjectTaskGanttBase } from './project-task-gantt-base';

/**
 * 任务
 *
 * @export
 * @class ProjectTaskGantt
 * @extends {ProjectTaskGanttBase}
 * @implements {IProjectTaskGantt}
 */
export class ProjectTaskGantt extends ProjectTaskGanttBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectTaskGantt
     */
    clone(): ProjectTaskGantt {
        return new ProjectTaskGantt(this);
    }
}
export default ProjectTaskGantt;
