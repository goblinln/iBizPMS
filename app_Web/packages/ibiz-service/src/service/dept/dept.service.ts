import { DeptBaseService } from './dept-base.service';

/**
 * 部门服务
 *
 * @export
 * @class DeptService
 * @extends {DeptBaseService}
 */
export class DeptService extends DeptBaseService {
    /**
     * Creates an instance of DeptService.
     * @memberof DeptService
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
     * @return {*}  {DeptService}
     * @memberof DeptService
     */
    static getInstance(context?: any): DeptService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}DeptService` : `DeptService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new DeptService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default DeptService;
