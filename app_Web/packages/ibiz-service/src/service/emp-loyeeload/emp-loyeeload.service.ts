import { EmpLoyeeloadBaseService } from './emp-loyeeload-base.service';

/**
 * 员工负载表服务
 *
 * @export
 * @class EmpLoyeeloadService
 * @extends {EmpLoyeeloadBaseService}
 */
export class EmpLoyeeloadService extends EmpLoyeeloadBaseService {
    /**
     * Creates an instance of EmpLoyeeloadService.
     * @memberof EmpLoyeeloadService
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
     * @return {*}  {EmpLoyeeloadService}
     * @memberof EmpLoyeeloadService
     */
    static getInstance(context?: any): EmpLoyeeloadService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}EmpLoyeeloadService` : `EmpLoyeeloadService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new EmpLoyeeloadService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default EmpLoyeeloadService;
