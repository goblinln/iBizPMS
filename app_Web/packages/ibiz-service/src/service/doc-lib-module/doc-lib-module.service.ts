import { DocLibModuleBaseService } from './doc-lib-module-base.service';

/**
 * 文档库分类服务
 *
 * @export
 * @class DocLibModuleService
 * @extends {DocLibModuleBaseService}
 */
export class DocLibModuleService extends DocLibModuleBaseService {
    /**
     * Creates an instance of DocLibModuleService.
     * @memberof DocLibModuleService
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
     * @return {*}  {DocLibModuleService}
     * @memberof DocLibModuleService
     */
    static getInstance(context?: any): DocLibModuleService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}DocLibModuleService` : `DocLibModuleService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new DocLibModuleService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default DocLibModuleService;
