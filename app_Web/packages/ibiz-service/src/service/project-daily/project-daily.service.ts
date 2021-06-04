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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectDailyService')) {
            return ___ibz___.sc.get('ProjectDailyService');
        }
        ___ibz___.sc.set('ProjectDailyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectDailyService}
     * @memberof ProjectDailyService
     */
    static getInstance(): ProjectDailyService {
        if (!___ibz___.sc.has('ProjectDailyService')) {
            new ProjectDailyService();
        }
        return ___ibz___.sc.get('ProjectDailyService');
    }
}
export default ProjectDailyService;
