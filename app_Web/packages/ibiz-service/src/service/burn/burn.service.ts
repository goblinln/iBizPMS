import { BurnBaseService } from './burn-base.service';

/**
 * burn服务
 *
 * @export
 * @class BurnService
 * @extends {BurnBaseService}
 */
export class BurnService extends BurnBaseService {
    /**
     * Creates an instance of BurnService.
     * @memberof BurnService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('BurnService')) {
            return ___ibz___.sc.get('BurnService');
        }
        ___ibz___.sc.set('BurnService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {BurnService}
     * @memberof BurnService
     */
    static getInstance(): BurnService {
        if (!___ibz___.sc.has('BurnService')) {
            new BurnService();
        }
        return ___ibz___.sc.get('BurnService');
    }
}
export default BurnService;
