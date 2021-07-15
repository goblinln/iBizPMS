import { IBIZProPluginBaseService } from './ibizpro-plugin-base.service';

/**
 * 系统插件服务
 *
 * @export
 * @class IBIZProPluginService
 * @extends {IBIZProPluginBaseService}
 */
export class IBIZProPluginService extends IBIZProPluginBaseService {
    /**
     * Creates an instance of IBIZProPluginService.
     * @memberof IBIZProPluginService
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
     * @return {*}  {IBIZProPluginService}
     * @memberof IBIZProPluginService
     */
    static getInstance(context?: any): IBIZProPluginService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IBIZProPluginService` : `IBIZProPluginService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IBIZProPluginService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IBIZProPluginService;
