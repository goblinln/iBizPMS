import { TaskestimatestatsBaseService } from './taskestimatestats-base.service';

/**
 * 任务工时统计服务
 *
 * @export
 * @class TaskestimatestatsService
 * @extends {TaskestimatestatsBaseService}
 */
export class TaskestimatestatsService extends TaskestimatestatsBaseService {
    /**
     * Creates an instance of TaskestimatestatsService.
     * @memberof TaskestimatestatsService
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
     * @return {*}  {TaskestimatestatsService}
     * @memberof TaskestimatestatsService
     */
    static getInstance(context?: any): TaskestimatestatsService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TaskestimatestatsService` : `TaskestimatestatsService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TaskestimatestatsService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TaskestimatestatsService;
