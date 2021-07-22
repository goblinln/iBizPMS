import { DocContentBaseService } from './doc-content-base.service';

/**
 * 文档内容服务
 *
 * @export
 * @class DocContentService
 * @extends {DocContentBaseService}
 */
export class DocContentService extends DocContentBaseService {
    /**
     * Creates an instance of DocContentService.
     * @memberof DocContentService
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
     * @return {*}  {DocContentService}
     * @memberof DocContentService
     */
    static getInstance(context?: any): DocContentService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}DocContentService` : `DocContentService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new DocContentService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default DocContentService;
