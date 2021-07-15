import { ProjectMonthlyBaseService } from './project-monthly-base.service';

/**
 * 项目月报服务
 *
 * @export
 * @class ProjectMonthlyService
 * @extends {ProjectMonthlyBaseService}
 */
export class ProjectMonthlyService extends ProjectMonthlyBaseService {
    /**
     * Creates an instance of ProjectMonthlyService.
     * @memberof ProjectMonthlyService
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
     * @return {*}  {ProjectMonthlyService}
     * @memberof ProjectMonthlyService
     */
    static getInstance(context?: any): ProjectMonthlyService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProjectMonthlyService` : `ProjectMonthlyService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProjectMonthlyService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProjectMonthlyService;
