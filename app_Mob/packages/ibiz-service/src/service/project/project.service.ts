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
     * @return {*}  {ProjectService}
     * @memberof ProjectService
     */
    static getInstance(context?: any): ProjectService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProjectService` : `ProjectService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProjectService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProjectService;
