import { SysTeamBase } from './sys-team-base';

/**
 * 组
 *
 * @export
 * @class SysTeam
 * @extends {SysTeamBase}
 * @implements {ISysTeam}
 */
export class SysTeam extends SysTeamBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof SysTeam
     */
    clone(): SysTeam {
        return new SysTeam(this);
    }
}
export default SysTeam;
