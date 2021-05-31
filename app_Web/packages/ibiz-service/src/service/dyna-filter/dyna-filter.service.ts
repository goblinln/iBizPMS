import { DynaFilterBaseService } from './dyna-filter-base.service';

/**
 * 动态搜索栏服务
 *
 * @export
 * @class DynaFilterService
 * @extends {DynaFilterBaseService}
 */
export class DynaFilterService extends DynaFilterBaseService {
    /**
     * Creates an instance of DynaFilterService.
     * @memberof DynaFilterService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('DynaFilterService')) {
            return ___ibz___.sc.get('DynaFilterService');
        }
        ___ibz___.sc.set('DynaFilterService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {DynaFilterService}
     * @memberof DynaFilterService
     */
    static getInstance(): DynaFilterService {
        if (!___ibz___.sc.has('DynaFilterService')) {
            new DynaFilterService();
        }
        return ___ibz___.sc.get('DynaFilterService');
    }
}
export default DynaFilterService;
