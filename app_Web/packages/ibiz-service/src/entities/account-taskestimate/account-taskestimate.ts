import { AccountTaskestimateBase } from './account-taskestimate-base';

/**
 * 用户工时统计
 *
 * @export
 * @class AccountTaskestimate
 * @extends {AccountTaskestimateBase}
 * @implements {IAccountTaskestimate}
 */
export class AccountTaskestimate extends AccountTaskestimateBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof AccountTaskestimate
     */
    clone(): AccountTaskestimate {
        return new AccountTaskestimate(this);
    }
}
export default AccountTaskestimate;
