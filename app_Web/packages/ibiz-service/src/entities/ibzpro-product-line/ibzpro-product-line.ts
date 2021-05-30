import { IBZProProductLineBase } from './ibzpro-product-line-base';

/**
 * 产品线
 *
 * @export
 * @class IBZProProductLine
 * @extends {IBZProProductLineBase}
 * @implements {IIBZProProductLine}
 */
export class IBZProProductLine extends IBZProProductLineBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZProProductLine
     */
    clone(): IBZProProductLine {
        return new IBZProProductLine(this);
    }
}
export default IBZProProductLine;
