import { TaskBase } from './task-base';

/**
 * 任务
 *
 * @export
 * @class Task
 * @extends {TaskBase}
 * @implements {ITask}
 */
export class Task extends TaskBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Task
     */
    clone(): Task {
        return new Task(this);
    }
}
export default Task;
