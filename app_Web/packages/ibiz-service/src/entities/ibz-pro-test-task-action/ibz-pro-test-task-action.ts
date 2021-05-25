import { IbzProTestTaskActionBase } from './ibz-pro-test-task-action-base';

/**
 * 测试单日志
 *
 * @export
 * @class IbzProTestTaskAction
 * @extends {IbzProTestTaskActionBase}
 * @implements {IIbzProTestTaskAction}
 */
export class IbzProTestTaskAction extends IbzProTestTaskActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzProTestTaskAction
     */
    clone(): IbzProTestTaskAction {
        return new IbzProTestTaskAction(this);
    }
}
export default IbzProTestTaskAction;
