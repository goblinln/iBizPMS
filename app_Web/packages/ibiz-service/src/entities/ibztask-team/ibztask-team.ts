import { IBZTaskTeamBase } from './ibztask-team-base';

/**
 * 任务团队
 *
 * @export
 * @class IBZTaskTeam
 * @extends {IBZTaskTeamBase}
 * @implements {IIBZTaskTeam}
 */
export class IBZTaskTeam extends IBZTaskTeamBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZTaskTeam
     */
    clone(): IBZTaskTeam {
        return new IBZTaskTeam(this);
    }
}
export default IBZTaskTeam;
