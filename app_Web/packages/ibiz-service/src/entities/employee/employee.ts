import { EmployeeBase } from './employee-base';

/**
 * 人员
 *
 * @export
 * @class Employee
 * @extends {EmployeeBase}
 * @implements {IEmployee}
 */
export class Employee extends EmployeeBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Employee
     */
    clone(): Employee {
        return new Employee(this);
    }
}
export default Employee;
