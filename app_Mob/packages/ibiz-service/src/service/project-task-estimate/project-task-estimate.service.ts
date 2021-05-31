import { ProjectTaskEstimateBaseService } from './project-task-estimate-base.service';

/**
 * 任务预计服务
 *
 * @export
 * @class ProjectTaskEstimateService
 * @extends {ProjectTaskEstimateBaseService}
 */
export class ProjectTaskEstimateService extends ProjectTaskEstimateBaseService {
    /**
     * Creates an instance of ProjectTaskEstimateService.
     * @memberof ProjectTaskEstimateService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectTaskEstimateService')) {
            return ___ibz___.sc.get('ProjectTaskEstimateService');
        }
        ___ibz___.sc.set('ProjectTaskEstimateService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectTaskEstimateService}
     * @memberof ProjectTaskEstimateService
     */
    static getInstance(): ProjectTaskEstimateService {
        if (!___ibz___.sc.has('ProjectTaskEstimateService')) {
            new ProjectTaskEstimateService();
        }
        return ___ibz___.sc.get('ProjectTaskEstimateService');
    }
}
export default ProjectTaskEstimateService;
