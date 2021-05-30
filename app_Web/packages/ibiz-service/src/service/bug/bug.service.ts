import { BugBaseService } from './bug-base.service';

/**
 * Bug服务
 *
 * @export
 * @class BugService
 * @extends {BugBaseService}
 */
export class BugService extends BugBaseService {
    /**
     * Creates an instance of BugService.
     * @memberof BugService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('BugService')) {
            return ___ibz___.sc.get('BugService');
        }
        ___ibz___.sc.set('BugService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {BugService}
     * @memberof BugService
     */
    static getInstance(): BugService {
        if (!___ibz___.sc.has('BugService')) {
            new BugService();
        }
        return ___ibz___.sc.get('BugService');
    }
}
export default BugService;
