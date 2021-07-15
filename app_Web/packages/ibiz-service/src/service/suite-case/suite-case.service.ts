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
     * @return {*}  {SuiteCaseService}
     * @memberof SuiteCaseService
     */
    static getInstance(context?: any): SuiteCaseService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}SuiteCaseService` : `SuiteCaseService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new SuiteCaseService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default SuiteCaseService;
