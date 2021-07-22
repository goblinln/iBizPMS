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
    constructor(opts?: any) {
        const { context: context, tag: cacheKey } = opts;
        super(context);
        if (___ibz___.sc.has(cacheKey)) {
            return ___ibz___.sc.get(cacheKey);
        }
        ___ibz___.sc.set(cacheKey, this);
    }

    /**
     * 获取实例
     *
     * @static
     * @param 应用上下文
     * @return {*}  {IbzFavoritesService}
     * @memberof IbzFavoritesService
     */
    static getInstance(context?: any): IbzFavoritesService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IbzFavoritesService` : `IbzFavoritesService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IbzFavoritesService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IbzFavoritesService;
