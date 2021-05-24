import { IBZProProductHistoryBase } from './ibzpro-product-history-base';

/**
 * 产品操作历史
 *
 * @export
 * @class IBZProProductHistory
 * @extends {IBZProProductHistoryBase}
 * @implements {IIBZProProductHistory}
 */
export class IBZProProductHistory extends IBZProProductHistoryBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZProProductHistory
     */
    clone(): IBZProProductHistory {
        return new IBZProProductHistory(this);
    }
}
export default IBZProProductHistory;
