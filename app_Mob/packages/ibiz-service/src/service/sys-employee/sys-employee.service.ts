import { SysEmployeeBaseService } from './sys-employee-base.service';

/**
 * 人员服务
 *
 * @export
 * @class SysEmployeeService
 * @extends {SysEmployeeBaseService}
 */
export class SysEmployeeService extends SysEmployeeBaseService {
    /**
     * Creates an instance of SysEmployeeService.
     * @memberof SysEmployeeService
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
     * @return {*}  {SysEmployeeService}
     * @memberof SysEmployeeService
     */
    static getInstance(context?: any): SysEmployeeService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}SysEmployeeService` : `SysEmployeeService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new SysEmployeeService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default SysEmployeeService;
