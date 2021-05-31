import { DeptBase } from './dept-base';

/**
 * 部门
 *
 * @export
 * @class Dept
 * @extends {DeptBase}
 * @implements {IDept}
 */
export class Dept extends DeptBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Dept
     */
    clone(): Dept {
        return new Dept(this);
    }
}
export default Dept;
