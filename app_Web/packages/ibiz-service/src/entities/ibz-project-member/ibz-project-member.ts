import { IbzProjectMemberBase } from './ibz-project-member-base';

/**
 * 项目相关成员
 *
 * @export
 * @class IbzProjectMember
 * @extends {IbzProjectMemberBase}
 * @implements {IIbzProjectMember}
 */
export class IbzProjectMember extends IbzProjectMemberBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzProjectMember
     */
    clone(): IbzProjectMember {
        return new IbzProjectMember(this);
    }
}
export default IbzProjectMember;
