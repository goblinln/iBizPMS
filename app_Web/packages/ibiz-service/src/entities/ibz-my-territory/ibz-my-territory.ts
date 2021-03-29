import { IbzMyTerritoryBase } from './ibz-my-territory-base';

/**
 * 我的地盘
 *
 * @export
 * @class IbzMyTerritory
 * @extends {IbzMyTerritoryBase}
 * @implements {IIbzMyTerritory}
 */
export class IbzMyTerritory extends IbzMyTerritoryBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzMyTerritory
     */
    clone(): IbzMyTerritory {
        return new IbzMyTerritory(this);
    }
}
export default IbzMyTerritory;
