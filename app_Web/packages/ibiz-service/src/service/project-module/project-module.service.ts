import { ProjectModuleBaseService } from './project-module-base.service';

/**
 * 任务模块服务
 *
 * @export
 * @class ProjectModuleService
 * @extends {ProjectModuleBaseService}
 */
export class ProjectModuleService extends ProjectModuleBaseService {
    /**
     * Creates an instance of ProjectModuleService.
     * @memberof ProjectModuleService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectModuleService')) {
            return ___ibz___.sc.get('ProjectModuleService');
        }
        ___ibz___.sc.set('ProjectModuleService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectModuleService}
     * @memberof ProjectModuleService
     */
    static getInstance(): ProjectModuleService {
        if (!___ibz___.sc.has('ProjectModuleService')) {
            new ProjectModuleService();
        }
        return ___ibz___.sc.get('ProjectModuleService');
    }
}
export default ProjectModuleService;
