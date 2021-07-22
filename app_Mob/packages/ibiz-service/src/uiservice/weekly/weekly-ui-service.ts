import { WeeklyUIServiceBase } from './weekly-ui-service-base';

/**
 * 周报UI服务对象
 *
 * @export
 * @class WeeklyUIService
 */
export default class WeeklyUIService extends WeeklyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof WeeklyUIService
     */
    private static basicUIServiceInstance: WeeklyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof WeeklyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  WeeklyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  WeeklyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof WeeklyUIService
     */
    public static getInstance(context: any): WeeklyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new WeeklyUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!WeeklyUIService.UIServiceMap.get(context.srfdynainstid)) {
                WeeklyUIService.UIServiceMap.set(context.srfdynainstid, new WeeklyUIService({context:context}));
            }
            return WeeklyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}