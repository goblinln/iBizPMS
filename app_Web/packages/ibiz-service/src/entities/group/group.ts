import { GroupBase } from './group-base';

/**
 * 群组
 *
 * @export
 * @class Group
 * @extends {GroupBase}
 * @implements {IGroup}
 */
export class Group extends GroupBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Group
     */
    clone(): Group {
        return new Group(this);
    }
}
export default Group;
