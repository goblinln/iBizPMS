import { ProjectTaskestimateBase } from './project-taskestimate-base';

/**
 * 项目工时统计
 *
 * @export
 * @class ProjectTaskestimate
 * @extends {ProjectTaskestimateBase}
 * @implements {IProjectTaskestimate}
 */
export class ProjectTaskestimate extends ProjectTaskestimateBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectTaskestimate
     */
    clone(): ProjectTaskestimate {
        return new ProjectTaskestimate(this);
    }
}
export default ProjectTaskestimate;
