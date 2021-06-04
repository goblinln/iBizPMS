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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectMonthlyService')) {
            return ___ibz___.sc.get('ProjectMonthlyService');
        }
        ___ibz___.sc.set('ProjectMonthlyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectMonthlyService}
     * @memberof ProjectMonthlyService
     */
    static getInstance(): ProjectMonthlyService {
        if (!___ibz___.sc.has('ProjectMonthlyService')) {
            new ProjectMonthlyService();
        }
        return ___ibz___.sc.get('ProjectMonthlyService');
    }
}
export default ProjectMonthlyService;
