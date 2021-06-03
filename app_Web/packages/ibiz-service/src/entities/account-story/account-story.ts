import { AccountStoryBase } from './account-story-base';

/**
 * 需求
 *
 * @export
 * @class AccountStory
 * @extends {AccountStoryBase}
 * @implements {IAccountStory}
 */
export class AccountStory extends AccountStoryBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof AccountStory
     */
    clone(): AccountStory {
        return new AccountStory(this);
    }
}
export default AccountStory;
