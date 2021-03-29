import { PRODUCTTEAMBase } from './productteam-base';

/**
 * 产品团队
 *
 * @export
 * @class PRODUCTTEAM
 * @extends {PRODUCTTEAMBase}
 * @implements {IPRODUCTTEAM}
 */
export class PRODUCTTEAM extends PRODUCTTEAMBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof PRODUCTTEAM
     */
    clone(): PRODUCTTEAM {
        return new PRODUCTTEAM(this);
    }
}
export default PRODUCTTEAM;
