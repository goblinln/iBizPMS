import { AccountTaskUIServiceBase } from './account-task-ui-service-base';

/**
 * 任务UI服务对象
 *
 * @export
 * @class AccountTaskUIService
 */
export default class AccountTaskUIService extends AccountTaskUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof AccountTaskUIService
     */
    private static basicUIServiceInstance: AccountTaskUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof AccountTaskUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountTaskUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountTaskUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountTaskUIService
     */
    public static getInstance(context: any): AccountTaskUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountTaskUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountTaskUIService.UIServiceMap.get(context.srfdynainstid)) {
                AccountTaskUIService.UIServiceMap.set(context.srfdynainstid, new AccountTaskUIService({context:context}));
            }
            return AccountTaskUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}