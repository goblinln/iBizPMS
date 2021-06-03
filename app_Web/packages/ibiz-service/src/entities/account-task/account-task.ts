import { AccountTaskBase } from './account-task-base';

/**
 * 任务
 *
 * @export
 * @class AccountTask
 * @extends {AccountTaskBase}
 * @implements {IAccountTask}
 */
export class AccountTask extends AccountTaskBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof AccountTask
     */
    clone(): AccountTask {
        return new AccountTask(this);
    }
}
export default AccountTask;
