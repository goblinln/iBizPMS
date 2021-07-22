import { ModuleBaseService } from './module-base.service';

/**
 * 模块服务
 *
 * @export
 * @class ModuleService
 * @extends {ModuleBaseService}
 */
export class ModuleService extends ModuleBaseService {
    /**
     * Creates an instance of ModuleService.
     * @memberof ModuleService
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
     * @return {*}  {ModuleService}
     * @memberof ModuleService
     */
    static getInstance(context?: any): ModuleService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ModuleService` : `ModuleService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ModuleService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ModuleService;
