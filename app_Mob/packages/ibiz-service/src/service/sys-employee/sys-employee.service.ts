import { SysEmployeeBaseService } from './sys-employee-base.service';

/**
 * 人员服务
 *
 * @export
 * @class SysEmployeeService
 * @extends {SysEmployeeBaseService}
 */
export class SysEmployeeService extends SysEmployeeBaseService {
    /**
     * Creates an instance of SysEmployeeService.
     * @memberof SysEmployeeService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('SysEmployeeService')) {
            return ___ibz___.sc.get('SysEmployeeService');
        }
        ___ibz___.sc.set('SysEmployeeService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {SysEmployeeService}
     * @memberof SysEmployeeService
     */
    static getInstance(): SysEmployeeService {
        if (!___ibz___.sc.has('SysEmployeeService')) {
            new SysEmployeeService();
        }
        return ___ibz___.sc.get('SysEmployeeService');
    }
}
export default SysEmployeeService;
