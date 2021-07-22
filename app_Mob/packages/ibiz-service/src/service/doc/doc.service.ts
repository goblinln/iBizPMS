import { DocBaseService } from './doc-base.service';

/**
 * 文档服务
 *
 * @export
 * @class DocService
 * @extends {DocBaseService}
 */
export class DocService extends DocBaseService {
    /**
     * Creates an instance of DocService.
     * @memberof DocService
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
     * @return {*}  {DocService}
     * @memberof DocService
     */
    static getInstance(context?: any): DocService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}DocService` : `DocService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new DocService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default DocService;
