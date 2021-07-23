import { TaskBaseService } from './task-base.service';

/**
 * 任务服务
 *
 * @export
 * @class TaskService
 * @extends {TaskBaseService}
 */
export class TaskService extends TaskBaseService {
    /**
     * Creates an instance of TaskService.
     * @memberof TaskService
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
     * @return {*}  {TaskService}
     * @memberof TaskService
     */
    static getInstance(context?: any): TaskService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TaskService` : `TaskService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TaskService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TaskService;
