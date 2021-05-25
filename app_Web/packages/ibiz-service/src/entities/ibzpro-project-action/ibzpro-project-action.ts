import { IBZProProjectActionBase } from './ibzpro-project-action-base';

/**
 * 项目日志
 *
 * @export
 * @class IBZProProjectAction
 * @extends {IBZProProjectActionBase}
 * @implements {IIBZProProjectAction}
 */
export class IBZProProjectAction extends IBZProProjectActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZProProjectAction
     */
    clone(): IBZProProjectAction {
        return new IBZProProjectAction(this);
    }
}
export default IBZProProjectAction;
