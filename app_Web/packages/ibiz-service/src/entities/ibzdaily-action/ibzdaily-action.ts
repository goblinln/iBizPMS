import { IBZDailyActionBase } from './ibzdaily-action-base';

/**
 * 日报日志
 *
 * @export
 * @class IBZDailyAction
 * @extends {IBZDailyActionBase}
 * @implements {IIBZDailyAction}
 */
export class IBZDailyAction extends IBZDailyActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZDailyAction
     */
    clone(): IBZDailyAction {
        return new IBZDailyAction(this);
    }
}
export default IBZDailyAction;
