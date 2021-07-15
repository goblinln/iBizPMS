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
    constructor(opts?: any) {
        const { context: context, tag: cacheKey } = opts;
        super(context);
        if (___ibz___.sc.has(cacheKey)) {
            return ___ibz___.sc.get(cacheKey);
        }
        ___ibz___.sc.set(cacheKey, this);
    }

    /**
     * 获取实例
     *
     * @static
     * @param 应用上下文
     * @return {*}  {CompanyService}
     * @memberof CompanyService
     */
    static getInstance(context?: any): CompanyService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}CompanyService` : `CompanyService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new CompanyService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default CompanyService;
