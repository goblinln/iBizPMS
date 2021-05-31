import { ProjectTaskBase } from './project-task-base';

/**
 * 任务
 *
 * @export
 * @class ProjectTask
 * @extends {ProjectTaskBase}
 * @implements {IProjectTask}
 */
export class ProjectTask extends ProjectTaskBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectTask
     */
    clone(): ProjectTask {
        return new ProjectTask(this);
    }
}
export default ProjectTask;
