import { SysPostBaseService } from './sys-post-base.service';

/**
 * 岗位服务
 *
 * @export
 * @class SysPostService
 * @extends {SysPostBaseService}
 */
export class SysPostService extends SysPostBaseService {
    /**
     * Creates an instance of SysPostService.
     * @memberof SysPostService
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
     * @return {*}  {SysPostService}
     * @memberof SysPostService
     */
    static getInstance(context?: any): SysPostService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}SysPostService` : `SysPostService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new SysPostService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default SysPostService;
