import { SysTeamMemberBase } from './sys-team-member-base';

/**
 * 组成员
 *
 * @export
 * @class SysTeamMember
 * @extends {SysTeamMemberBase}
 * @implements {ISysTeamMember}
 */
export class SysTeamMember extends SysTeamMemberBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof SysTeamMember
     */
    clone(): SysTeamMember {
        return new SysTeamMember(this);
    }
}
export default SysTeamMember;
