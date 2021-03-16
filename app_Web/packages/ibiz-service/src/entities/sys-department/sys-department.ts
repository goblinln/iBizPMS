import { SysDepartmentBase } from './sys-department-base';

/**
 * 部门
 *
 * @export
 * @class SysDepartment
 * @extends {SysDepartmentBase}
 * @implements {ISysDepartment}
 */
export class SysDepartment extends SysDepartmentBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof SysDepartment
     */
    clone(): SysDepartment {
        return new SysDepartment(this);
    }
}
export default SysDepartment;
