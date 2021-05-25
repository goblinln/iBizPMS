import { IBZDailyActionUIServiceBase } from './ibzdaily-action-ui-service-base';

/**
 * 日报日志UI服务对象
 *
 * @export
 * @class IBZDailyActionUIService
 */
export default class IBZDailyActionUIService extends IBZDailyActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZDailyActionUIService
     */
    private static basicUIServiceInstance: IBZDailyActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZDailyActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZDailyActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZDailyActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZDailyActionUIService
     */
    public static getInstance(context: any): IBZDailyActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZDailyActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZDailyActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZDailyActionUIService.UIServiceMap.set(context.srfdynainstid, new IBZDailyActionUIService({context:context}));
            }
            return IBZDailyActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}