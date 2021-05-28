import { ProjectBugBaseService } from './project-bug-base.service';

/**
 * Bug服务
 *
 * @export
 * @class ProjectBugService
 * @extends {ProjectBugBaseService}
 */
export class ProjectBugService extends ProjectBugBaseService {
    /**
     * Creates an instance of ProjectBugService.
     * @memberof ProjectBugService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectBugService')) {
            return ___ibz___.sc.get('ProjectBugService');
        }
        ___ibz___.sc.set('ProjectBugService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectBugService}
     * @memberof ProjectBugService
     */
    static getInstance(): ProjectBugService {
        if (!___ibz___.sc.has('ProjectBugService')) {
            new ProjectBugService();
        }
        return ___ibz___.sc.get('ProjectBugService');
    }
}
export default ProjectBugService;
