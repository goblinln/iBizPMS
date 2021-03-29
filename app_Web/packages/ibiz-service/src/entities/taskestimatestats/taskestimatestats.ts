import { TaskestimatestatsBase } from './taskestimatestats-base';

/**
 * 任务工时统计
 *
 * @export
 * @class Taskestimatestats
 * @extends {TaskestimatestatsBase}
 * @implements {ITaskestimatestats}
 */
export class Taskestimatestats extends TaskestimatestatsBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Taskestimatestats
     */
    clone(): Taskestimatestats {
        return new Taskestimatestats(this);
    }
}
export default Taskestimatestats;
