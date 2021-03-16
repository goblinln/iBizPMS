import { IbzFavoritesBase } from './ibz-favorites-base';

/**
 * 收藏
 *
 * @export
 * @class IbzFavorites
 * @extends {IbzFavoritesBase}
 * @implements {IIbzFavorites}
 */
export class IbzFavorites extends IbzFavoritesBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzFavorites
     */
    clone(): IbzFavorites {
        return new IbzFavorites(this);
    }
}
export default IbzFavorites;
