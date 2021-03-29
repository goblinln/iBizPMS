import { DynaDashboardBaseService } from './dyna-dashboard-base.service';

/**
 * 动态数据看板服务
 *
 * @export
 * @class DynaDashboardService
 * @extends {DynaDashboardBaseService}
 */
export class DynaDashboardService extends DynaDashboardBaseService {
    /**
     * Creates an instance of DynaDashboardService.
     * @memberof DynaDashboardService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('DynaDashboardService')) {
            return ___ibz___.sc.get('DynaDashboardService');
        }
        ___ibz___.sc.set('DynaDashboardService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {DynaDashboardService}
     * @memberof DynaDashboardService
     */
    static getInstance(): DynaDashboardService {
        if (!___ibz___.sc.has('DynaDashboardService')) {
            new DynaDashboardService();
        }
        return ___ibz___.sc.get('DynaDashboardService');
    }
}
export default DynaDashboardService;
