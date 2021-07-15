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
     * @return {*}  {ProjectBurnService}
     * @memberof ProjectBurnService
     */
    static getInstance(context?: any): ProjectBurnService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}ProjectBurnService` : `ProjectBurnService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new ProjectBurnService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default ProjectBurnService;
