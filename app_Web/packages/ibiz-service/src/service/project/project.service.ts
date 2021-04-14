import { ProjectBaseService } from './project-base.service';

/**
 * 项目服务
 *
 * @export
 * @class ProjectService
 * @extends {ProjectBaseService}
 */
export class ProjectService extends ProjectBaseService {
    /**
     * Creates an instance of ProjectService.
     * @memberof ProjectService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectService')) {
            return ___ibz___.sc.get('ProjectService');
        }
        ___ibz___.sc.set('ProjectService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectService}
     * @memberof ProjectService
     */
    static getInstance(): ProjectService {
        if (!___ibz___.sc.has('ProjectService')) {
            new ProjectService();
        }
        return ___ibz___.sc.get('ProjectService');
    }
}
export default ProjectService;