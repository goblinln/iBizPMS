import { HistoryBase } from './history-base';

/**
 * 操作历史
 *
 * @export
 * @class History
 * @extends {HistoryBase}
 * @implements {IHistory}
 */
export class History extends HistoryBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof History
     */
    clone(): History {
        return new History(this);
    }
}
export default History;
