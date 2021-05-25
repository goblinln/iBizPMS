import { IBZTestSuiteActionBase } from './ibztest-suite-action-base';

/**
 * 套件日志
 *
 * @export
 * @class IBZTestSuiteAction
 * @extends {IBZTestSuiteActionBase}
 * @implements {IIBZTestSuiteAction}
 */
export class IBZTestSuiteAction extends IBZTestSuiteActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZTestSuiteAction
     */
    clone(): IBZTestSuiteAction {
        return new IBZTestSuiteAction(this);
    }
}
export default IBZTestSuiteAction;
