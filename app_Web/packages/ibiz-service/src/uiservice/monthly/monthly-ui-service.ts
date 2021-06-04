import { MonthlyUIServiceBase } from './monthly-ui-service-base';

/**
 * 月报UI服务对象
 *
 * @export
 * @class MonthlyUIService
 */
export default class MonthlyUIService extends MonthlyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof MonthlyUIService
     */
    private static basicUIServiceInstance: MonthlyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof MonthlyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  MonthlyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  MonthlyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof MonthlyUIService
     */
    public static getInstance(context: any): MonthlyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new MonthlyUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!MonthlyUIService.UIServiceMap.get(context.srfdynainstid)) {
                MonthlyUIService.UIServiceMap.set(context.srfdynainstid, new MonthlyUIService({context:context}));
            }
            return MonthlyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}