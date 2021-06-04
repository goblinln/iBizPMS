import { TaskTeamNestedBase } from './task-team-nested-base';

/**
 * 任务团队
 *
 * @export
 * @class TaskTeamNested
 * @extends {TaskTeamNestedBase}
 * @implements {ITaskTeamNested}
 */
export class TaskTeamNested extends TaskTeamNestedBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TaskTeamNested
     */
    clone(): TaskTeamNested {
        return new TaskTeamNested(this);
    }
}
export default TaskTeamNested;
