import { IbizproIndexBase } from './ibizpro-index-base';

/**
 * 索引检索
 *
 * @export
 * @class IbizproIndex
 * @extends {IbizproIndexBase}
 * @implements {IIbizproIndex}
 */
export class IbizproIndex extends IbizproIndexBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbizproIndex
     */
    clone(): IbizproIndex {
        return new IbizproIndex(this);
    }
}
export default IbizproIndex;
