import { ProjectStatsBaseService } from './project-stats-base.service';

/**
 * 项目统计服务
 *
 * @export
 * @class ProjectStatsService
 * @extends {ProjectStatsBaseService}
 */
export class ProjectStatsService extends ProjectStatsBaseService {
    /**
     * Creates an instance of ProjectStatsService.
     * @memberof ProjectStatsService
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
     * @return {*}  {ProjectStatsService}
     * @memberof ProjectStatsService
     */
    static getInstance(context?: any): ProjectStatsService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProjectStatsService` : `ProjectStatsService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProjectStatsService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProjectStatsService;
