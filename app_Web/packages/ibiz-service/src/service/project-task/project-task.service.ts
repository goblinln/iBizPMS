import { ProjectTaskBaseService } from './project-task-base.service';

/**
 * 任务服务
 *
 * @export
 * @class ProjectTaskService
 * @extends {ProjectTaskBaseService}
 */
export class ProjectTaskService extends ProjectTaskBaseService {
    /**
     * Creates an instance of ProjectTaskService.
     * @memberof ProjectTaskService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectTaskService')) {
            return ___ibz___.sc.get('ProjectTaskService');
        }
        ___ibz___.sc.set('ProjectTaskService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectTaskService}
     * @memberof ProjectTaskService
     */
    static getInstance(): ProjectTaskService {
        if (!___ibz___.sc.has('ProjectTaskService')) {
            new ProjectTaskService();
        }
        return ___ibz___.sc.get('ProjectTaskService');
    }
}
export default ProjectTaskService;
