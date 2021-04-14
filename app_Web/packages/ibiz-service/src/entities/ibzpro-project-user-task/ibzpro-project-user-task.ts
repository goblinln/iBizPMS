import { IbzproProjectUserTaskBase } from './ibzpro-project-user-task-base';

/**
 * 项目汇报用户任务
 *
 * @export
 * @class IbzproProjectUserTask
 * @extends {IbzproProjectUserTaskBase}
 * @implements {IIbzproProjectUserTask}
 */
export class IbzproProjectUserTask extends IbzproProjectUserTaskBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzproProjectUserTask
     */
    clone(): IbzproProjectUserTask {
        return new IbzproProjectUserTask(this);
    }
}
export default IbzproProjectUserTask;
