import { SysDepartmentBaseService } from './sys-department-base.service';

/**
 * 部门服务
 *
 * @export
 * @class SysDepartmentService
 * @extends {SysDepartmentBaseService}
 */
export class SysDepartmentService extends SysDepartmentBaseService {
    /**
     * Creates an instance of SysDepartmentService.
     * @memberof SysDepartmentService
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
     * @return {*}  {SysDepartmentService}
     * @memberof SysDepartmentService
     */
    static getInstance(context?: any): SysDepartmentService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}SysDepartmentService` : `SysDepartmentService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new SysDepartmentService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default SysDepartmentService;
