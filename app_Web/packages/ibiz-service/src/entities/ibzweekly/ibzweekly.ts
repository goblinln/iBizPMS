import { IBZWEEKLYBase } from './ibzweekly-base';

/**
 * 周报
 *
 * @export
 * @class IBZWEEKLY
 * @extends {IBZWEEKLYBase}
 * @implements {IIBZWEEKLY}
 */
export class IBZWEEKLY extends IBZWEEKLYBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZWEEKLY
     */
    clone(): IBZWEEKLY {
        return new IBZWEEKLY(this);
    }
}
export default IBZWEEKLY;
