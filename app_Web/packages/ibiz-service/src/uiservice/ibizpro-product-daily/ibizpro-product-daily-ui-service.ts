import { IbizproProductDailyUIServiceBase } from './ibizpro-product-daily-ui-service-base';

/**
 * 产品日报UI服务对象
 *
 * @export
 * @class IbizproProductDailyUIService
 */
export default class IbizproProductDailyUIService extends IbizproProductDailyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbizproProductDailyUIService
     */
    private static basicUIServiceInstance: IbizproProductDailyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbizproProductDailyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbizproProductDailyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProductDailyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbizproProductDailyUIService
     */
    public static getInstance(context: any): IbizproProductDailyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbizproProductDailyUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbizproProductDailyUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbizproProductDailyUIService.UIServiceMap.set(context.srfdynainstid, new IbizproProductDailyUIService({context:context}));
            }
            return IbizproProductDailyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}