import { IBZProWeeklyActionUIServiceBase } from './ibzpro-weekly-action-ui-service-base';

/**
 * 周报日志UI服务对象
 *
 * @export
 * @class IBZProWeeklyActionUIService
 */
export default class IBZProWeeklyActionUIService extends IBZProWeeklyActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBZProWeeklyActionUIService
     */
    private static basicUIServiceInstance: IBZProWeeklyActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBZProWeeklyActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBZProWeeklyActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBZProWeeklyActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBZProWeeklyActionUIService
     */
    public static getInstance(context: any): IBZProWeeklyActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBZProWeeklyActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBZProWeeklyActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBZProWeeklyActionUIService.UIServiceMap.set(context.srfdynainstid, new IBZProWeeklyActionUIService({context:context}));
            }
            return IBZProWeeklyActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}