import { AccountTaskestimateUIServiceBase } from './account-taskestimate-ui-service-base';

/**
 * 用户工时统计UI服务对象
 *
 * @export
 * @class AccountTaskestimateUIService
 */
export default class AccountTaskestimateUIService extends AccountTaskestimateUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof AccountTaskestimateUIService
     */
    private static basicUIServiceInstance: AccountTaskestimateUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof AccountTaskestimateUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  AccountTaskestimateUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  AccountTaskestimateUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof AccountTaskestimateUIService
     */
    public static getInstance(context: any): AccountTaskestimateUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new AccountTaskestimateUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!AccountTaskestimateUIService.UIServiceMap.get(context.srfdynainstid)) {
                AccountTaskestimateUIService.UIServiceMap.set(context.srfdynainstid, new AccountTaskestimateUIService({context:context}));
            }
            return AccountTaskestimateUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}