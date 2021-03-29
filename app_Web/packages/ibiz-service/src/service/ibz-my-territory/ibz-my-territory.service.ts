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
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IbzMyTerritoryService')) {
            return ___ibz___.sc.get('IbzMyTerritoryService');
        }
        ___ibz___.sc.set('IbzMyTerritoryService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IbzMyTerritoryService}
     * @memberof IbzMyTerritoryService
     */
    static getInstance(): IbzMyTerritoryService {
        if (!___ibz___.sc.has('IbzMyTerritoryService')) {
            new IbzMyTerritoryService();
        }
        return ___ibz___.sc.get('IbzMyTerritoryService');
    }
}
export default IbzMyTerritoryService;