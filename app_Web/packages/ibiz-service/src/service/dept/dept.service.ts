import { DeptBaseService } from './dept-base.service';

/**
 * 部门服务
 *
 * @export
 * @class DeptService
 * @extends {DeptBaseService}
 */
export class DeptService extends DeptBaseService {
    /**
     * Creates an instance of DeptService.
     * @memberof DeptService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('DeptService')) {
            return ___ibz___.sc.get('DeptService');
        }
        ___ibz___.sc.set('DeptService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {DeptService}
     * @memberof DeptService
     */
    static getInstance(): DeptService {
        if (!___ibz___.sc.has('DeptService')) {
            new DeptService();
        }
        return ___ibz___.sc.get('DeptService');
    }
}
export default DeptService;