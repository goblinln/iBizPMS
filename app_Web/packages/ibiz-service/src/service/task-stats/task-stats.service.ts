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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('TaskStatsService')) {
            return ___ibz___.sc.get('TaskStatsService');
        }
        ___ibz___.sc.set('TaskStatsService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {TaskStatsService}
     * @memberof TaskStatsService
     */
    static getInstance(): TaskStatsService {
        if (!___ibz___.sc.has('TaskStatsService')) {
            new TaskStatsService();
        }
        return ___ibz___.sc.get('TaskStatsService');
    }
}
export default TaskStatsService;