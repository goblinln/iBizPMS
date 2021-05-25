import { IBZTaskActionBase } from './ibztask-action-base';

/**
 * 任务日志
 *
 * @export
 * @class IBZTaskAction
 * @extends {IBZTaskActionBase}
 * @implements {IIBZTaskAction}
 */
export class IBZTaskAction extends IBZTaskActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZTaskAction
     */
    clone(): IBZTaskAction {
        return new IBZTaskAction(this);
    }
}
export default IBZTaskAction;
