import { IBZProReleaseActionBase } from './ibzpro-release-action-base';

/**
 * 发布日志
 *
 * @export
 * @class IBZProReleaseAction
 * @extends {IBZProReleaseActionBase}
 * @implements {IIBZProReleaseAction}
 */
export class IBZProReleaseAction extends IBZProReleaseActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZProReleaseAction
     */
    clone(): IBZProReleaseAction {
        return new IBZProReleaseAction(this);
    }
}
export default IBZProReleaseAction;
