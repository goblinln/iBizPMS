import { BugBase } from './bug-base';

/**
 * Bug
 *
 * @export
 * @class Bug
 * @extends {BugBase}
 * @implements {IBug}
 */
export class Bug extends BugBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Bug
     */
    clone(): Bug {
        return new Bug(this);
    }
}
export default Bug;
