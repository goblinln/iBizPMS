import { ProjectBurnBaseService } from './project-burn-base.service';

/**
 * burn服务
 *
 * @export
 * @class ProjectBurnService
 * @extends {ProjectBurnBaseService}
 */
export class ProjectBurnService extends ProjectBurnBaseService {
    /**
     * Creates an instance of ProjectBurnService.
     * @memberof ProjectBurnService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectBurnService')) {
            return ___ibz___.sc.get('ProjectBurnService');
        }
        ___ibz___.sc.set('ProjectBurnService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectBurnService}
     * @memberof ProjectBurnService
     */
    static getInstance(): ProjectBurnService {
        if (!___ibz___.sc.has('ProjectBurnService')) {
            new ProjectBurnService();
        }
        return ___ibz___.sc.get('ProjectBurnService');
    }
}
export default ProjectBurnService;
