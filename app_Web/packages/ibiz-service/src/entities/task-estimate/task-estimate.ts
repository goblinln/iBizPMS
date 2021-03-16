import { TaskEstimateBase } from './task-estimate-base';

/**
 * 任务预计
 *
 * @export
 * @class TaskEstimate
 * @extends {TaskEstimateBase}
 * @implements {ITaskEstimate}
 */
export class TaskEstimate extends TaskEstimateBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TaskEstimate
     */
    clone(): TaskEstimate {
        return new TaskEstimate(this);
    }
}
export default TaskEstimate;
