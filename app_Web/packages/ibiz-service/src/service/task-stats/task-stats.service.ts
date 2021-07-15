import { TaskStatsBaseService } from './task-stats-base.service';

/**
 * 任务统计服务
 *
 * @export
 * @class TaskStatsService
 * @extends {TaskStatsBaseService}
 */
export class TaskStatsService extends TaskStatsBaseService {
    /**
     * Creates an instance of TaskStatsService.
     * @memberof TaskStatsService
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
     * @return {*}  {TaskStatsService}
     * @memberof TaskStatsService
     */
    static getInstance(context?: any): TaskStatsService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TaskStatsService` : `TaskStatsService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TaskStatsService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TaskStatsService;
