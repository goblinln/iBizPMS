import { TaskEstimateBaseService } from './task-estimate-base.service';

/**
 * 任务预计服务
 *
 * @export
 * @class TaskEstimateService
 * @extends {TaskEstimateBaseService}
 */
export class TaskEstimateService extends TaskEstimateBaseService {
    /**
     * Creates an instance of TaskEstimateService.
     * @memberof TaskEstimateService
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
     * @return {*}  {TaskEstimateService}
     * @memberof TaskEstimateService
     */
    static getInstance(context?: any): TaskEstimateService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TaskEstimateService` : `TaskEstimateService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TaskEstimateService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TaskEstimateService;
