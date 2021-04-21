import { CompanyBaseService } from './company-base.service';

/**
 * 公司服务
 *
 * @export
 * @class CompanyService
 * @extends {CompanyBaseService}
 */
export class CompanyService extends CompanyBaseService {
    /**
     * Creates an instance of CompanyService.
     * @memberof CompanyService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('CompanyService')) {
            return ___ibz___.sc.get('CompanyService');
        }
        ___ibz___.sc.set('CompanyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {CompanyService}
     * @memberof CompanyService
     */
    static getInstance(): CompanyService {
        if (!___ibz___.sc.has('CompanyService')) {
            new CompanyService();
        }
        return ___ibz___.sc.get('CompanyService');
    }
}
export default CompanyService;
