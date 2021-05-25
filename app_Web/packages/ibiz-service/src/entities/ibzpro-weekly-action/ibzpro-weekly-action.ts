import { IBZProWeeklyActionBase } from './ibzpro-weekly-action-base';

/**
 * 周报日志
 *
 * @export
 * @class IBZProWeeklyAction
 * @extends {IBZProWeeklyActionBase}
 * @implements {IIBZProWeeklyAction}
 */
export class IBZProWeeklyAction extends IBZProWeeklyActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZProWeeklyAction
     */
    clone(): IBZProWeeklyAction {
        return new IBZProWeeklyAction(this);
    }
}
export default IBZProWeeklyAction;
