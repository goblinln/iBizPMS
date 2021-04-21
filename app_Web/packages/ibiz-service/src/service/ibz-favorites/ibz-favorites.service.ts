import { IbzFavoritesBaseService } from './ibz-favorites-base.service';

/**
 * 收藏服务
 *
 * @export
 * @class IbzFavoritesService
 * @extends {IbzFavoritesBaseService}
 */
export class IbzFavoritesService extends IbzFavoritesBaseService {
    /**
     * Creates an instance of IbzFavoritesService.
     * @memberof IbzFavoritesService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzFavoritesService')) {
            return ___ibz___.sc.get('IbzFavoritesService');
        }
        ___ibz___.sc.set('IbzFavoritesService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzFavoritesService}
     * @memberof IbzFavoritesService
     */
    static getInstance(): IbzFavoritesService {
        if (!___ibz___.sc.has('IbzFavoritesService')) {
            new IbzFavoritesService();
        }
        return ___ibz___.sc.get('IbzFavoritesService');
    }
}
export default IbzFavoritesService;
