import { HistoryBaseService } from './history-base.service';

/**
 * 操作历史服务
 *
 * @export
 * @class HistoryService
 * @extends {HistoryBaseService}
 */
export class HistoryService extends HistoryBaseService {
    /**
     * Creates an instance of HistoryService.
     * @memberof HistoryService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('HistoryService')) {
            return ___ibz___.sc.get('HistoryService');
        }
        ___ibz___.sc.set('HistoryService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {HistoryService}
     * @memberof HistoryService
     */
    static getInstance(): HistoryService {
        if (!___ibz___.sc.has('HistoryService')) {
            new HistoryService();
        }
        return ___ibz___.sc.get('HistoryService');
    }
}
export default HistoryService;