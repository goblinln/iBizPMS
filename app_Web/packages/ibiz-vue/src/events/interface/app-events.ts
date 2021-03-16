import { HistoryItem } from '../../app-service/common-service/app-nav-history';

/**
 * 事件定义
 *
 * @export
 * @interface AppEvents
 */
export interface AppEvents {
    /**
     * 删除单个历史搜索项
     *
     * @type {HistoryItem}
     * @memberof AppEvents
     */
    'navHistoryItemChange': HistoryItem;
}
