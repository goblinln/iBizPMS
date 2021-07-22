import { DailyUIServiceBase } from './daily-ui-service-base';

/**
 * 日报UI服务对象
 *
 * @export
 * @class DailyUIService
 */
export default class DailyUIService extends DailyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof DailyUIService
     */
    private static basicUIServiceInstance: DailyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof DailyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DailyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  DailyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DailyUIService
     */
    public static getInstance(context: any): DailyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new DailyUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!DailyUIService.UIServiceMap.get(context.srfdynainstid)) {
                DailyUIService.UIServiceMap.set(context.srfdynainstid, new DailyUIService({context:context}));
            }
            return DailyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}