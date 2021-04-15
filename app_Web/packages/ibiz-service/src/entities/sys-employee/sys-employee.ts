import { SysEmployeeBase } from './sys-employee-base';

/**
 * 人员
 *
 * @export
 * @class SysEmployee
 * @extends {SysEmployeeBase}
 * @implements {ISysEmployee}
 */
export class SysEmployee extends SysEmployeeBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof SysEmployee
     */
    clone(): SysEmployee {
        return new SysEmployee(this);
    }
}
export default SysEmployee;
