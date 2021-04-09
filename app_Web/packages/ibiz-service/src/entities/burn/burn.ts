import { BurnBase } from './burn-base';

/**
 * burn
 *
 * @export
 * @class Burn
 * @extends {BurnBase}
 * @implements {IBurn}
 */
export class Burn extends BurnBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof Burn
     */
    clone(): Burn {
        return new Burn(this);
    }
}
export default Burn;
