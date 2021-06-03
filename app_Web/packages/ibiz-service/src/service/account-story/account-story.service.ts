import { AccountStoryBaseService } from './account-story-base.service';

/**
 * 需求服务
 *
 * @export
 * @class AccountStoryService
 * @extends {AccountStoryBaseService}
 */
export class AccountStoryService extends AccountStoryBaseService {
    /**
     * Creates an instance of AccountStoryService.
     * @memberof AccountStoryService
     */
    constructor() {
        super();
        // 全局唯一实例，new 返回已存在实例。确保全局单例!
        if (___ibz___.sc.has('AccountStoryService')) {
            return ___ibz___.sc.get('AccountStoryService');
        }
        ___ibz___.sc.set('AccountStoryService', this);
    }

    /**
     * 获取实例
     *
     * @static
     * @return {*}  {AccountStoryService}
     * @memberof AccountStoryService
     */
    static getInstance(): AccountStoryService {
        if (!___ibz___.sc.has('AccountStoryService')) {
            new AccountStoryService();
        }
        return ___ibz___.sc.get('AccountStoryService');
    }
}
export default AccountStoryService;
