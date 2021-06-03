import { AccountStoryUIServiceBase } from './account-story-ui-service-base';

/**
 * 需求UI服务对象
 *
 * @export
 * @class AccountStoryUIService
 */
export default class AccountStoryUIService extends AccountStoryUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof AccountStoryUIService
     */
    private static basicUIServiceInstance: AccountStoryUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof AccountStoryUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountStoryUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountStoryUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountStoryUIService
     */
    public static getInstance(context: any): AccountStoryUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountStoryUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountStoryUIService.UIServiceMap.get(context.srfdynainstid)) {
                AccountStoryUIService.UIServiceMap.set(context.srfdynainstid, new AccountStoryUIService({context:context}));
            }
            return AccountStoryUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}