import { MyTaskBase } from './my-task-base';

/**
 * 任务
 *
 * @export
 * @class MyTask
 * @extends {MyTaskBase}
 * @implements {IMyTask}
 */
export class MyTask extends MyTaskBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof MyTask
     */
    clone(): MyTask {
        return new MyTask(this);
    }
}
export default MyTask;
