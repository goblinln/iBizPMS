import { IbizproProductWeeklyUIServiceBase } from './ibizpro-product-weekly-ui-service-base';

/**
 * 产品周报UI服务对象
 *
 * @export
 * @class IbizproProductWeeklyUIService
 */
export default class IbizproProductWeeklyUIService extends IbizproProductWeeklyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbizproProductWeeklyUIService
     */
    private static basicUIServiceInstance: IbizproProductWeeklyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbizproProductWeeklyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbizproProductWeeklyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbizproProductWeeklyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbizproProductWeeklyUIService
     */
    public static getInstance(context: any): IbizproProductWeeklyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbizproProductWeeklyUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbizproProductWeeklyUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbizproProductWeeklyUIService.UIServiceMap.set(context.srfdynainstid, new IbizproProductWeeklyUIService({context:context}));
            }
            return IbizproProductWeeklyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}