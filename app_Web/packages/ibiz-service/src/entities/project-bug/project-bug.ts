import { ProjectBugBase } from './project-bug-base';

/**
 * Bug
 *
 * @export
 * @class ProjectBug
 * @extends {ProjectBugBase}
 * @implements {IProjectBug}
 */
export class ProjectBug extends ProjectBugBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectBug
     */
    clone(): ProjectBug {
        return new ProjectBug(this);
    }
}
export default ProjectBug;
