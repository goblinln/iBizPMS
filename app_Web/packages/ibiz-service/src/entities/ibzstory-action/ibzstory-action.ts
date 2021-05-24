import { IBZStoryActionBase } from './ibzstory-action-base';

/**
 * 需求日志
 *
 * @export
 * @class IBZStoryAction
 * @extends {IBZStoryActionBase}
 * @implements {IIBZStoryAction}
 */
export class IBZStoryAction extends IBZStoryActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZStoryAction
     */
    clone(): IBZStoryAction {
        return new IBZStoryAction(this);
    }
}
export default IBZStoryAction;
