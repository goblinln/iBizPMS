import { ProjectBurnBase } from './project-burn-base';

/**
 * burn
 *
 * @export
 * @class ProjectBurn
 * @extends {ProjectBurnBase}
 * @implements {IProjectBurn}
 */
export class ProjectBurn extends ProjectBurnBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectBurn
     */
    clone(): ProjectBurn {
        return new ProjectBurn(this);
    }
}
export default ProjectBurn;
