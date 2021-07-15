import { IbzCaseBaseService } from './ibz-case-base.service';

/**
 * 测试用例服务
 *
 * @export
 * @class IbzCaseService
 * @extends {IbzCaseBaseService}
 */
export class IbzCaseService extends IbzCaseBaseService {
    /**
     * Creates an instance of IbzCaseService.
     * @memberof IbzCaseService
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
     * @return {*}  {IbzCaseService}
     * @memberof IbzCaseService
     */
    static getInstance(context?: any): IbzCaseService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IbzCaseService` : `IbzCaseService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IbzCaseService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IbzCaseService;
