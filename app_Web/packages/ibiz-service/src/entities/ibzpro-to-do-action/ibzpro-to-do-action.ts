import { IBZProToDoActionBase } from './ibzpro-to-do-action-base';

/**
 * ToDo日志
 *
 * @export
 * @class IBZProToDoAction
 * @extends {IBZProToDoActionBase}
 * @implements {IIBZProToDoAction}
 */
export class IBZProToDoAction extends IBZProToDoActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZProToDoAction
     */
    clone(): IBZProToDoAction {
        return new IBZProToDoAction(this);
    }
}
export default IBZProToDoAction;
