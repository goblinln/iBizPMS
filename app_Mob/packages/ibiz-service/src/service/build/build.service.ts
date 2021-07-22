import { BuildBaseService } from './build-base.service';

/**
 * 版本服务
 *
 * @export
 * @class BuildService
 * @extends {BuildBaseService}
 */
export class BuildService extends BuildBaseService {
    /**
     * Creates an instance of BuildService.
     * @memberof BuildService
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
     * @return {*}  {BuildService}
     * @memberof BuildService
     */
    static getInstance(context?: any): BuildService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}BuildService` : `BuildService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new BuildService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default BuildService;
