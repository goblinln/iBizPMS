import { PSSysAppBaseService } from './pssys-app-base.service';

/**
 * 系统应用服务
 *
 * @export
 * @class PSSysAppService
 * @extends {PSSysAppBaseService}
 */
export class PSSysAppService extends PSSysAppBaseService {
    /**
     * Creates an instance of PSSysAppService.
     * @memberof PSSysAppService
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
     * @return {*}  {PSSysAppService}
     * @memberof PSSysAppService
     */
    static getInstance(context?: any): PSSysAppService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}PSSysAppService` : `PSSysAppService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new PSSysAppService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default PSSysAppService;
