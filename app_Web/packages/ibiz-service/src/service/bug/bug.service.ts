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
     * @return {*}  {BugService}
     * @memberof BugService
     */
    static getInstance(context?: any): BugService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}BugService` : `BugService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new BugService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default BugService;
