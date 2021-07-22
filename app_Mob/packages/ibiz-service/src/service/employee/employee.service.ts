import { EmployeeBaseService } from './employee-base.service';

/**
 * 人员服务
 *
 * @export
 * @class EmployeeService
 * @extends {EmployeeBaseService}
 */
export class EmployeeService extends EmployeeBaseService {
    /**
     * Creates an instance of EmployeeService.
     * @memberof EmployeeService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('EmployeeService')) {
            return ___ibz___.sc.get('EmployeeService');
        }
        ___ibz___.sc.set('EmployeeService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {EmployeeService}
     * @memberof EmployeeService
     */
    static getInstance(): EmployeeService {
        if (!___ibz___.sc.has('EmployeeService')) {
            new EmployeeService();
        }
        return ___ibz___.sc.get('EmployeeService');
    }
}
export default EmployeeService;
