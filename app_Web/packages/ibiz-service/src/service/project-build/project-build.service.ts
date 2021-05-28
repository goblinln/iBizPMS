import { ProjectBuildBaseService } from './project-build-base.service';

/**
 * 版本服务
 *
 * @export
 * @class ProjectBuildService
 * @extends {ProjectBuildBaseService}
 */
export class ProjectBuildService extends ProjectBuildBaseService {
    /**
     * Creates an instance of ProjectBuildService.
     * @memberof ProjectBuildService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectBuildService')) {
            return ___ibz___.sc.get('ProjectBuildService');
        }
        ___ibz___.sc.set('ProjectBuildService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectBuildService}
     * @memberof ProjectBuildService
     */
    static getInstance(): ProjectBuildService {
        if (!___ibz___.sc.has('ProjectBuildService')) {
            new ProjectBuildService();
        }
        return ___ibz___.sc.get('ProjectBuildService');
    }
}
export default ProjectBuildService;
