import { IbzDailyUIServiceBase } from './ibz-daily-ui-service-base';

/**
 * 日报UI服务对象
 *
 * @export
 * @class IbzDailyUIService
 */
export default class IbzDailyUIService extends IbzDailyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzDailyUIService
     */
    private static basicUIServiceInstance: IbzDailyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzDailyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzDailyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzDailyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzDailyUIService
     */
    public static getInstance(context: any): IbzDailyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzDailyUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzDailyUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzDailyUIService.UIServiceMap.set(context.srfdynainstid, new IbzDailyUIService({context:context}));
            }
            return IbzDailyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}