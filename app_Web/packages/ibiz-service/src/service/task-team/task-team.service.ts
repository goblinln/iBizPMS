import { TaskTeamBaseService } from './task-team-base.service';

/**
 * 任务团队服务
 *
 * @export
 * @class TaskTeamService
 * @extends {TaskTeamBaseService}
 */
export class TaskTeamService extends TaskTeamBaseService {
    /**
     * Creates an instance of TaskTeamService.
     * @memberof TaskTeamService
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
     * @return {*}  {TaskTeamService}
     * @memberof TaskTeamService
     */
    static getInstance(context?: any): TaskTeamService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}TaskTeamService` : `TaskTeamService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new TaskTeamService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default TaskTeamService;
