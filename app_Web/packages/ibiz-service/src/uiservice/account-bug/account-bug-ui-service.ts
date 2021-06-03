import { AccountBugUIServiceBase } from './account-bug-ui-service-base';

/**
 * BugUI服务对象
 *
 * @export
 * @class AccountBugUIService
 */
export default class AccountBugUIService extends AccountBugUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof AccountBugUIService
     */
    private static basicUIServiceInstance: AccountBugUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof AccountBugUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountBugUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountBugUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountBugUIService
     */
    public static getInstance(context: any): AccountBugUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountBugUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountBugUIService.UIServiceMap.get(context.srfdynainstid)) {
                AccountBugUIService.UIServiceMap.set(context.srfdynainstid, new AccountBugUIService({context:context}));
            }
            return AccountBugUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}