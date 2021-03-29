import { ProjectTaskestimateBaseService } from './project-taskestimate-base.service';

/**
 * 项目工时统计服务
 *
 * @export
 * @class ProjectTaskestimateService
 * @extends {ProjectTaskestimateBaseService}
 */
export class ProjectTaskestimateService extends ProjectTaskestimateBaseService {
    /**
     * Creates an instance of ProjectTaskestimateService.
     * @memberof ProjectTaskestimateService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectTaskestimateService')) {
            return ___ibz___.sc.get('ProjectTaskestimateService');
        }
        ___ibz___.sc.set('ProjectTaskestimateService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectTaskestimateService}
     * @memberof ProjectTaskestimateService
     */
    static getInstance(): ProjectTaskestimateService {
        if (!___ibz___.sc.has('ProjectTaskestimateService')) {
            new ProjectTaskestimateService();
        }
        return ___ibz___.sc.get('ProjectTaskestimateService');
    }
}
export default ProjectTaskestimateService;
