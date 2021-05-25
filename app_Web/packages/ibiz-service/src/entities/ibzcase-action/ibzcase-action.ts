import { IBZCaseActionBase } from './ibzcase-action-base';

/**
 * 测试用例日志
 *
 * @export
 * @class IBZCaseAction
 * @extends {IBZCaseActionBase}
 * @implements {IIBZCaseAction}
 */
export class IBZCaseAction extends IBZCaseActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZCaseAction
     */
    clone(): IBZCaseAction {
        return new IBZCaseAction(this);
    }
}
export default IBZCaseAction;
