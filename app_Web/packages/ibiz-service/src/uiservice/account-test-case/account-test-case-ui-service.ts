import { AccountTestCaseUIServiceBase } from './account-test-case-ui-service-base';

/**
 * 测试用例UI服务对象
 *
 * @export
 * @class AccountTestCaseUIService
 */
export default class AccountTestCaseUIService extends AccountTestCaseUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof AccountTestCaseUIService
     */
    private static basicUIServiceInstance: AccountTestCaseUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof AccountTestCaseUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountTestCaseUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountTestCaseUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountTestCaseUIService
     */
    public static getInstance(context: any): AccountTestCaseUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountTestCaseUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountTestCaseUIService.UIServiceMap.get(context.srfdynainstid)) {
                AccountTestCaseUIService.UIServiceMap.set(context.srfdynainstid, new AccountTestCaseUIService({context:context}));
            }
            return AccountTestCaseUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}