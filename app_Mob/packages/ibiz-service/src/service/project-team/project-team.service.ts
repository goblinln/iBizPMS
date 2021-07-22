import { ProjectTeamBaseService } from './project-team-base.service';

/**
 * 项目团队服务
 *
 * @export
 * @class ProjectTeamService
 * @extends {ProjectTeamBaseService}
 */
export class ProjectTeamService extends ProjectTeamBaseService {
    /**
     * Creates an instance of ProjectTeamService.
     * @memberof ProjectTeamService
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
     * @return {*}  {ProjectTeamService}
     * @memberof ProjectTeamService
     */
    static getInstance(context?: any): ProjectTeamService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProjectTeamService` : `ProjectTeamService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProjectTeamService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProjectTeamService;
