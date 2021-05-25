import { IbzProBugActionBase } from './ibz-pro-bug-action-base';

/**
 * Bug日志
 *
 * @export
 * @class IbzProBugAction
 * @extends {IbzProBugActionBase}
 * @implements {IIbzProBugAction}
 */
export class IbzProBugAction extends IbzProBugActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzProBugAction
     */
    clone(): IbzProBugAction {
        return new IbzProBugAction(this);
    }
}
export default IbzProBugAction;
