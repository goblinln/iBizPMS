import { CaseBaseService } from './case-base.service';

/**
 * 测试用例服务
 *
 * @export
 * @class CaseService
 * @extends {CaseBaseService}
 */
export class CaseService extends CaseBaseService {
    /**
     * Creates an instance of CaseService.
     * @memberof CaseService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('CaseService')) {
            return ___ibz___.sc.get('CaseService');
        }
        ___ibz___.sc.set('CaseService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {CaseService}
     * @memberof CaseService
     */
    static getInstance(): CaseService {
        if (!___ibz___.sc.has('CaseService')) {
            new CaseService();
        }
        return ___ibz___.sc.get('CaseService');
    }
}
export default CaseService;