import { ProjectWeeklyBaseService } from './project-weekly-base.service';

/**
 * 项目周报服务
 *
 * @export
 * @class ProjectWeeklyService
 * @extends {ProjectWeeklyBaseService}
 */
export class ProjectWeeklyService extends ProjectWeeklyBaseService {
    /**
     * Creates an instance of ProjectWeeklyService.
     * @memberof ProjectWeeklyService
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
     * @return {*}  {ProjectWeeklyService}
     * @memberof ProjectWeeklyService
     */
    static getInstance(context?: any): ProjectWeeklyService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProjectWeeklyService` : `ProjectWeeklyService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProjectWeeklyService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProjectWeeklyService;
