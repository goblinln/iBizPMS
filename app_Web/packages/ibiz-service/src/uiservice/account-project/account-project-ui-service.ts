import { AccountProjectUIServiceBase } from './account-project-ui-service-base';

/**
 * 项目UI服务对象
 *
 * @export
 * @class AccountProjectUIService
 */
export default class AccountProjectUIService extends AccountProjectUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof AccountProjectUIService
     */
    private static basicUIServiceInstance: AccountProjectUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof AccountProjectUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountProjectUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountProjectUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountProjectUIService
     */
    public static getInstance(context: any): AccountProjectUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountProjectUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountProjectUIService.UIServiceMap.get(context.srfdynainstid)) {
                AccountProjectUIService.UIServiceMap.set(context.srfdynainstid, new AccountProjectUIService({context:context}));
            }
            return AccountProjectUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}