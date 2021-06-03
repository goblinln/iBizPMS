import { AccountProductUIServiceBase } from './account-product-ui-service-base';

/**
 * 产品UI服务对象
 *
 * @export
 * @class AccountProductUIService
 */
export default class AccountProductUIService extends AccountProductUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof AccountProductUIService
     */
    private static basicUIServiceInstance: AccountProductUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof AccountProductUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountProductUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountProductUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountProductUIService
     */
    public static getInstance(context: any): AccountProductUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountProductUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountProductUIService.UIServiceMap.get(context.srfdynainstid)) {
                AccountProductUIService.UIServiceMap.set(context.srfdynainstid, new AccountProductUIService({context:context}));
            }
            return AccountProductUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}