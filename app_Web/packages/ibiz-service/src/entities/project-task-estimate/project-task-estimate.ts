import { ProjectTaskEstimateBase } from './project-task-estimate-base';

/**
 * 任务预计
 *
 * @export
 * @class ProjectTaskEstimate
 * @extends {ProjectTaskEstimateBase}
 * @implements {IProjectTaskEstimate}
 */
export class ProjectTaskEstimate extends ProjectTaskEstimateBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectTaskEstimate
     */
    clone(): ProjectTaskEstimate {
        return new ProjectTaskEstimate(this);
    }
}
export default ProjectTaskEstimate;
