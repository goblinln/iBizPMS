import { TaskStatsBase } from './task-stats-base';

/**
 * 任务统计
 *
 * @export
 * @class TaskStats
 * @extends {TaskStatsBase}
 * @implements {ITaskStats}
 */
export class TaskStats extends TaskStatsBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TaskStats
     */
    clone(): TaskStats {
        return new TaskStats(this);
    }
}
export default TaskStats;
