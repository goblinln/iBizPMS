import { ActionBase } from './action-base';

/**
 * 系统日志
 *
 * @export
 * @class Action
 * @extends {ActionBase}
 * @implements {IAction}
 */
export class Action extends ActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Action
     */
    clone(): Action {
        return new Action(this);
    }
}
export default Action;
