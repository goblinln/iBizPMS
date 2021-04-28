import { ProjectTeamBase } from './project-team-base';

/**
 * 项目团队
 *
 * @export
 * @class ProjectTeam
 * @extends {ProjectTeamBase}
 * @implements {IProjectTeam}
 */
export class ProjectTeam extends ProjectTeamBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof ProjectTeam
     */
    clone(): ProjectTeam {
        return new ProjectTeam(this);
    }
}
export default ProjectTeam;
