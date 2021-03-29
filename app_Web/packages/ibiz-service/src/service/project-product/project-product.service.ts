import { ProjectProductBaseService } from './project-product-base.service';

/**
 * 项目产品服务
 *
 * @export
 * @class ProjectProductService
 * @extends {ProjectProductBaseService}
 */
export class ProjectProductService extends ProjectProductBaseService {
    /**
     * Creates an instance of ProjectProductService.
     * @memberof ProjectProductService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectProductService')) {
            return ___ibz___.sc.get('ProjectProductService');
        }
        ___ibz___.sc.set('ProjectProductService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectProductService}
     * @memberof ProjectProductService
     */
    static getInstance(): ProjectProductService {
        if (!___ibz___.sc.has('ProjectProductService')) {
            new ProjectProductService();
        }
        return ___ibz___.sc.get('ProjectProductService');
    }
}
export default ProjectProductService;