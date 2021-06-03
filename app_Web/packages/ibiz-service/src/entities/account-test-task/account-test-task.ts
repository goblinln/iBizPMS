import { AccountTestTaskBase } from './account-test-task-base';

/**
 * 测试版本
 *
 * @export
 * @class AccountTestTask
 * @extends {AccountTestTaskBase}
 * @implements {IAccountTestTask}
 */
export class AccountTestTask extends AccountTestTaskBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof AccountTestTask
     */
    clone(): AccountTestTask {
        return new AccountTestTask(this);
    }
}
export default AccountTestTask;
