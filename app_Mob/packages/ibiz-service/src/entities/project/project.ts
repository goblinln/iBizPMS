import { ProjectBase } from './project-base';

/**
 * 项目
 *
 * @export
 * @class Project
 * @extends {ProjectBase}
 * @implements {IProject}
 */
export class Project extends ProjectBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Project
     */
    clone(): Project {
        return new Project(this);
    }
}
export default Project;
