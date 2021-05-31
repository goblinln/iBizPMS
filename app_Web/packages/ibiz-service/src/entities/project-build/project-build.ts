import { ProjectBuildBase } from './project-build-base';

/**
 * 版本
 *
 * @export
 * @class ProjectBuild
 * @extends {ProjectBuildBase}
 * @implements {IProjectBuild}
 */
export class ProjectBuild extends ProjectBuildBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectBuild
     */
    clone(): ProjectBuild {
        return new ProjectBuild(this);
    }
}
export default ProjectBuild;
