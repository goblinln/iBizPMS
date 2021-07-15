import { GroupBaseService } from './group-base.service';

/**
 * 群组服务
 *
 * @export
 * @class GroupService
 * @extends {GroupBaseService}
 */
export class GroupService extends GroupBaseService {
    /**
     * Creates an instance of GroupService.
     * @memberof GroupService
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
     * @return {*}  {GroupService}
     * @memberof GroupService
     */
    static getInstance(context?: any): GroupService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}GroupService` : `GroupService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new GroupService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default GroupService;
