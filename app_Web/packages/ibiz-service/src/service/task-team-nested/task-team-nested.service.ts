import { TaskTeamNestedBaseService } from './task-team-nested-base.service';

/**
 * 任务团队服务
 *
 * @export
 * @class TaskTeamNestedService
 * @extends {TaskTeamNestedBaseService}
 */
export class TaskTeamNestedService extends TaskTeamNestedBaseService {
    /**
     * Creates an instance of TaskTeamNestedService.
     * @memberof TaskTeamNestedService
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
     * @return {*}  {TaskTeamNestedService}
     * @memberof TaskTeamNestedService
     */
    static getInstance(context?: any): TaskTeamNestedService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TaskTeamNestedService` : `TaskTeamNestedService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TaskTeamNestedService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TaskTeamNestedService;
