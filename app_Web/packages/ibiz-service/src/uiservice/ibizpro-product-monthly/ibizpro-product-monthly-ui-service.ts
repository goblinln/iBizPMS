import { IbizproProductMonthlyUIServiceBase } from './ibizpro-product-monthly-ui-service-base';

/**
 * 产品月报UI服务对象
 *
 * @export
 * @class IbizproProductMonthlyUIService
 */
export default class IbizproProductMonthlyUIService extends IbizproProductMonthlyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbizproProductMonthlyUIService
     */
    private static basicUIServiceInstance: IbizproProductMonthlyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbizproProductMonthlyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbizproProductMonthlyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProductMonthlyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbizproProductMonthlyUIService
     */
    public static getInstance(context: any): IbizproProductMonthlyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbizproProductMonthlyUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbizproProductMonthlyUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbizproProductMonthlyUIService.UIServiceMap.set(context.srfdynainstid, new IbizproProductMonthlyUIService({context:context}));
            }
            return IbizproProductMonthlyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}