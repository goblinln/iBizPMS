import { ProjectStoryBaseService } from './project-story-base.service';

/**
 * 需求服务
 *
 * @export
 * @class ProjectStoryService
 * @extends {ProjectStoryBaseService}
 */
export class ProjectStoryService extends ProjectStoryBaseService {
    /**
     * Creates an instance of ProjectStoryService.
     * @memberof ProjectStoryService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('ProjectStoryService')) {
            return ___ibz___.sc.get('ProjectStoryService');
        }
        ___ibz___.sc.set('ProjectStoryService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {ProjectStoryService}
     * @memberof ProjectStoryService
     */
    static getInstance(): ProjectStoryService {
        if (!___ibz___.sc.has('ProjectStoryService')) {
            new ProjectStoryService();
        }
        return ___ibz___.sc.get('ProjectStoryService');
    }
}
export default ProjectStoryService;
