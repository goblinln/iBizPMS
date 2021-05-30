import { IbzLibBase } from './ibz-lib-base';

/**
 * 用例库
 *
 * @export
 * @class IbzLib
 * @extends {IbzLibBase}
 * @implements {IIbzLib}
 */
export class IbzLib extends IbzLibBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzLib
     */
    clone(): IbzLib {
        return new IbzLib(this);
    }
}
export default IbzLib;
