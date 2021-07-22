import { IbzMyTerritoryBaseService } from './ibz-my-territory-base.service';

/**
 * 我的地盘服务
 *
 * @export
 * @class IbzMyTerritoryService
 * @extends {IbzMyTerritoryBaseService}
 */
export class IbzMyTerritoryService extends IbzMyTerritoryBaseService {
    /**
     * Creates an instance of IbzMyTerritoryService.
     * @memberof IbzMyTerritoryService
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
     * @return {*}  {IbzMyTerritoryService}
     * @memberof IbzMyTerritoryService
     */
    static getInstance(context?: any): IbzMyTerritoryService {
        const cacheKey: string = context?.srfdynainstid ? `${context.srfdynainstid}IbzMyTerritoryService` : `IbzMyTerritoryService`;
        if (!___ibz___.sc.has(cacheKey)) {
            new IbzMyTerritoryService({ context: context, tag: cacheKey });
        }
        return ___ibz___.sc.get(cacheKey);
    }
}
export default IbzMyTerritoryService;
