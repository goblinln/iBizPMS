import { IbzproProductUserTaskBase } from './ibzpro-product-user-task-base';

/**
 * 产品汇报用户任务
 *
 * @export
 * @class IbzproProductUserTask
 * @extends {IbzproProductUserTaskBase}
 * @implements {IIbzproProductUserTask}
 */
export class IbzproProductUserTask extends IbzproProductUserTaskBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzproProductUserTask
     */
    clone(): IbzproProductUserTask {
        return new IbzproProductUserTask(this);
    }
}
export default IbzproProductUserTask;
