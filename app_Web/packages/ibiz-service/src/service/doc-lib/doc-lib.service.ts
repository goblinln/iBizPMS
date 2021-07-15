import { DocLibBaseService } from './doc-lib-base.service';

/**
 * 文档库服务
 *
 * @export
 * @class DocLibService
 * @extends {DocLibBaseService}
 */
export class DocLibService extends DocLibBaseService {
    /**
     * Creates an instance of DocLibService.
     * @memberof DocLibService
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
     * @return {*}  {DocLibService}
     * @memberof DocLibService
     */
    static getInstance(context?: any): DocLibService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}DocLibService` : `DocLibService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new DocLibService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default DocLibService;
