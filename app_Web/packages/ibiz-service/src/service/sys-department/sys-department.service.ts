import { SysDepartmentBaseService } from './sys-department-base.service';

/**
 * 部门服务
 *
 * @export
 * @class SysDepartmentService
 * @extends {SysDepartmentBaseService}
 */
export class SysDepartmentService extends SysDepartmentBaseService {
    /**
     * Creates an instance of SysDepartmentService.
     * @memberof SysDepartmentService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('SysDepartmentService')) {
            return ___ibz___.sc.get('SysDepartmentService');
        }
        ___ibz___.sc.set('SysDepartmentService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {SysDepartmentService}
     * @memberof SysDepartmentService
     */
    static getInstance(): SysDepartmentService {
        if (!___ibz___.sc.has('SysDepartmentService')) {
            new SysDepartmentService();
        }
        return ___ibz___.sc.get('SysDepartmentService');
    }
}
export default SysDepartmentService;