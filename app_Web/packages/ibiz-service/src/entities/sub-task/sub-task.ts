import { SubTaskBase } from './sub-task-base';

/**
 * 任务
 *
 * @export
 * @class SubTask
 * @extends {SubTaskBase}
 * @implements {ISubTask}
 */
export class SubTask extends SubTaskBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof SubTask
     */
    clone(): SubTask {
        return new SubTask(this);
    }
}
export default SubTask;
