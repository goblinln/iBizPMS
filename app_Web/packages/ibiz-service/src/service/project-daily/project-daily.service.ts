import { ProjectDailyBaseService } from './project-daily-base.service';

/**
 * 项目日报服务
 *
 * @export
 * @class ProjectDailyService
 * @extends {ProjectDailyBaseService}
 */
export class ProjectDailyService extends ProjectDailyBaseService {
    /**
     * Creates an instance of ProjectDailyService.
     * @memberof ProjectDailyService
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
     * @return {*}  {ProjectDailyService}
     * @memberof ProjectDailyService
     */
    static getInstance(context?: any): ProjectDailyService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProjectDailyService` : `ProjectDailyService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProjectDailyService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProjectDailyService;
