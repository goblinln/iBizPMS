import { IbzproConfigBaseService } from './ibzpro-config-base.service';

/**
 * 系统配置表服务
 *
 * @export
 * @class IbzproConfigService
 * @extends {IbzproConfigBaseService}
 */
export class IbzproConfigService extends IbzproConfigBaseService {
    /**
     * Creates an instance of IbzproConfigService.
     * @memberof IbzproConfigService
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
     * @return {*}  {IbzproConfigService}
     * @memberof IbzproConfigService
     */
    static getInstance(context?: any): IbzproConfigService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IbzproConfigService` : `IbzproConfigService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IbzproConfigService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IbzproConfigService;
