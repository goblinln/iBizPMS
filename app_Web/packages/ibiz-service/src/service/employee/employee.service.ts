import { EmployeeBaseService } from './employee-base.service';

/**
 * 人员服务
 *
 * @export
 * @class EmployeeService
 * @extends {EmployeeBaseService}
 */
export class EmployeeService extends EmployeeBaseService {
    /**
     * Creates an instance of EmployeeService.
     * @memberof EmployeeService
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
     * @return {*}  {EmployeeService}
     * @memberof EmployeeService
     */
    static getInstance(context?: any): EmployeeService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}EmployeeService` : `EmployeeService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new EmployeeService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default EmployeeService;
