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
    constructor(opts?: any) {
        const { context: context, tag: cacheKey } = opts;
        super(context);
        if (___ibz___.sc.has(cacheKey)) {
            return ___ibz___.sc.get(cacheKey);
        }
        ___ibz___.sc.set(cacheKey, this);
    }

    /**
     * 获取实例
     *
     * @static
     * @param 应用上下文
     * @return {*}  {ProjectModuleService}
     * @memberof ProjectModuleService
     */
    static getInstance(context?: any): ProjectModuleService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProjectModuleService` : `ProjectModuleService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProjectModuleService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProjectModuleService;
