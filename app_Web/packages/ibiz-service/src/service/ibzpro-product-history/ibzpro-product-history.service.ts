import { IBZProProductHistoryBaseService } from './ibzpro-product-history-base.service';

/**
 * 产品操作历史服务
 *
 * @export
 * @class IBZProProductHistoryService
 * @extends {IBZProProductHistoryBaseService}
 */
export class IBZProProductHistoryService extends IBZProProductHistoryBaseService {
    /**
     * Creates an instance of IBZProProductHistoryService.
     * @memberof IBZProProductHistoryService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('IBZProProductHistoryService')) {
            return ___ibz___.sc.get('IBZProProductHistoryService');
        }
        ___ibz___.sc.set('IBZProProductHistoryService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {IBZProProductHistoryService}
     * @memberof IBZProProductHistoryService
     */
    static getInstance(): IBZProProductHistoryService {
        if (!___ibz___.sc.has('IBZProProductHistoryService')) {
            new IBZProProductHistoryService();
        }
        return ___ibz___.sc.get('IBZProProductHistoryService');
    }
}
export default IBZProProductHistoryService;
