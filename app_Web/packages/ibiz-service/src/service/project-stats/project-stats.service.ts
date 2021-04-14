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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectStatsService')) {
            return ___ibz___.sc.get('ProjectStatsService');
        }
        ___ibz___.sc.set('ProjectStatsService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectStatsService}
     * @memberof ProjectStatsService
     */
    static getInstance(): ProjectStatsService {
        if (!___ibz___.sc.has('ProjectStatsService')) {
            new ProjectStatsService();
        }
        return ___ibz___.sc.get('ProjectStatsService');
    }
}
export default ProjectStatsService;