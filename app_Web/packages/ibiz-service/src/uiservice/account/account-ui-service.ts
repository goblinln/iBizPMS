import { AccountUIServiceBase } from './account-ui-service-base';

/**
 * 系统用户UI服务对象
 *
 * @export
 * @class AccountUIService
 */
export default class AccountUIService extends AccountUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof AccountUIService
     */
    private static basicUIServiceInstance: AccountUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof AccountUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountUIService
     */
    public static getInstance(context: any): AccountUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountUIService.UIServiceMap.get(context.srfdynainstid)) {
                AccountUIService.UIServiceMap.set(context.srfdynainstid, new AccountUIService({context:context}));
            }
            return AccountUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}