import { SuiteCaseBaseService } from './suite-case-base.service';

/**
 * 套件用例服务
 *
 * @export
 * @class SuiteCaseService
 * @extends {SuiteCaseBaseService}
 */
export class SuiteCaseService extends SuiteCaseBaseService {
    /**
     * Creates an instance of SuiteCaseService.
     * @memberof SuiteCaseService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('SuiteCaseService')) {
            return ___ibz___.sc.get('SuiteCaseService');
        }
        ___ibz___.sc.set('SuiteCaseService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {SuiteCaseService}
     * @memberof SuiteCaseService
     */
    static getInstance(): SuiteCaseService {
        if (!___ibz___.sc.has('SuiteCaseService')) {
            new SuiteCaseService();
        }
        return ___ibz___.sc.get('SuiteCaseService');
    }
}
export default SuiteCaseService;
