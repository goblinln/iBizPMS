import { EmpLoyeeloadBaseService } from './emp-loyeeload-base.service';

/**
 * 员工负载表服务
 *
 * @export
 * @class EmpLoyeeloadService
 * @extends {EmpLoyeeloadBaseService}
 */
export class EmpLoyeeloadService extends EmpLoyeeloadBaseService {
    /**
     * Creates an instance of EmpLoyeeloadService.
     * @memberof EmpLoyeeloadService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('EmpLoyeeloadService')) {
            return ___ibz___.sc.get('EmpLoyeeloadService');
        }
        ___ibz___.sc.set('EmpLoyeeloadService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {EmpLoyeeloadService}
     * @memberof EmpLoyeeloadService
     */
    static getInstance(): EmpLoyeeloadService {
        if (!___ibz___.sc.has('EmpLoyeeloadService')) {
            new EmpLoyeeloadService();
        }
        return ___ibz___.sc.get('EmpLoyeeloadService');
    }
}
export default EmpLoyeeloadService;