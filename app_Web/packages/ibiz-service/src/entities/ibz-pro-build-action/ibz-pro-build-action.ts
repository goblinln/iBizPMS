import { IbzProBuildActionBase } from './ibz-pro-build-action-base';

/**
 * 版本日志
 *
 * @export
 * @class IbzProBuildAction
 * @extends {IbzProBuildActionBase}
 * @implements {IIbzProBuildAction}
 */
export class IbzProBuildAction extends IbzProBuildActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzProBuildAction
     */
    clone(): IbzProBuildAction {
        return new IbzProBuildAction(this);
    }
}
export default IbzProBuildAction;
