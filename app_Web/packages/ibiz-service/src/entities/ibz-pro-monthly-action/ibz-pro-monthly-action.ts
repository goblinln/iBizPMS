import { IbzProMonthlyActionBase } from './ibz-pro-monthly-action-base';

/**
 * 月报日志
 *
 * @export
 * @class IbzProMonthlyAction
 * @extends {IbzProMonthlyActionBase}
 * @implements {IIbzProMonthlyAction}
 */
export class IbzProMonthlyAction extends IbzProMonthlyActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzProMonthlyAction
     */
    clone(): IbzProMonthlyAction {
        return new IbzProMonthlyAction(this);
    }
}
export default IbzProMonthlyAction;
