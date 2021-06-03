import { AccountTestTaskUIServiceBase } from './account-test-task-ui-service-base';

/**
 * 测试版本UI服务对象
 *
 * @export
 * @class AccountTestTaskUIService
 */
export default class AccountTestTaskUIService extends AccountTestTaskUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof AccountTestTaskUIService
     */
    private static basicUIServiceInstance: AccountTestTaskUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof AccountTestTaskUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountTestTaskUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountTestTaskUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountTestTaskUIService
     */
    public static getInstance(context: any): AccountTestTaskUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountTestTaskUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountTestTaskUIService.UIServiceMap.get(context.srfdynainstid)) {
                AccountTestTaskUIService.UIServiceMap.set(context.srfdynainstid, new AccountTestTaskUIService({context:context}));
            }
            return AccountTestTaskUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}