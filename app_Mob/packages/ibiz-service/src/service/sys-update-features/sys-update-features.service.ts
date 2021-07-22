import { SysUpdateFeaturesBaseService } from './sys-update-features-base.service';

/**
 * 系统更新功能服务
 *
 * @export
 * @class SysUpdateFeaturesService
 * @extends {SysUpdateFeaturesBaseService}
 */
export class SysUpdateFeaturesService extends SysUpdateFeaturesBaseService {
    /**
     * Creates an instance of SysUpdateFeaturesService.
     * @memberof SysUpdateFeaturesService
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
     * @return {*}  {SysUpdateFeaturesService}
     * @memberof SysUpdateFeaturesService
     */
    static getInstance(context?: any): SysUpdateFeaturesService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}SysUpdateFeaturesService` : `SysUpdateFeaturesService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new SysUpdateFeaturesService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default SysUpdateFeaturesService;
