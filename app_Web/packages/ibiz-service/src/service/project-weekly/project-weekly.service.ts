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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectWeeklyService')) {
            return ___ibz___.sc.get('ProjectWeeklyService');
        }
        ___ibz___.sc.set('ProjectWeeklyService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectWeeklyService}
     * @memberof ProjectWeeklyService
     */
    static getInstance(): ProjectWeeklyService {
        if (!___ibz___.sc.has('ProjectWeeklyService')) {
            new ProjectWeeklyService();
        }
        return ___ibz___.sc.get('ProjectWeeklyService');
    }
}
export default ProjectWeeklyService;
