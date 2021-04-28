import { IbzWeeklyBase } from './ibz-weekly-base';

/**
 * 周报
 *
 * @export
 * @class IbzWeekly
 * @extends {IbzWeeklyBase}
 * @implements {IIbzWeekly}
 */
export class IbzWeekly extends IbzWeeklyBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IbzWeekly
     */
    clone(): IbzWeekly {
        return new IbzWeekly(this);
    }
}
export default IbzWeekly;
