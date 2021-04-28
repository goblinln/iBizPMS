import { TaskTeamBase } from './task-team-base';

/**
 * 任务团队
 *
 * @export
 * @class TaskTeam
 * @extends {TaskTeamBase}
 * @implements {ITaskTeam}
 */
export class TaskTeam extends TaskTeamBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof TaskTeam
     */
    clone(): TaskTeam {
        return new TaskTeam(this);
    }
}
export default TaskTeam;
