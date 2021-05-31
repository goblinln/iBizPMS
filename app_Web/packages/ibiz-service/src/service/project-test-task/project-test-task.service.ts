import { ProjectTestTaskBaseService } from './project-test-task-base.service';

/**
 * 测试版本服务
 *
 * @export
 * @class ProjectTestTaskService
 * @extends {ProjectTestTaskBaseService}
 */
export class ProjectTestTaskService extends ProjectTestTaskBaseService {
    /**
     * Creates an instance of ProjectTestTaskService.
     * @memberof ProjectTestTaskService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectTestTaskService')) {
            return ___ibz___.sc.get('ProjectTestTaskService');
        }
        ___ibz___.sc.set('ProjectTestTaskService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectTestTaskService}
     * @memberof ProjectTestTaskService
     */
    static getInstance(): ProjectTestTaskService {
        if (!___ibz___.sc.has('ProjectTestTaskService')) {
            new ProjectTestTaskService();
        }
        return ___ibz___.sc.get('ProjectTestTaskService');
    }
}
export default ProjectTestTaskService;
