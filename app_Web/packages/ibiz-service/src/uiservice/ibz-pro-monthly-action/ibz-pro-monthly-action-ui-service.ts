import { IbzProMonthlyActionUIServiceBase } from './ibz-pro-monthly-action-ui-service-base';

/**
 * 月报日志UI服务对象
 *
 * @export
 * @class IbzProMonthlyActionUIService
 */
export default class IbzProMonthlyActionUIService extends IbzProMonthlyActionUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzProMonthlyActionUIService
     */
    private static basicUIServiceInstance: IbzProMonthlyActionUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzProMonthlyActionUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzProMonthlyActionUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzProMonthlyActionUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzProMonthlyActionUIService
     */
    public static getInstance(context: any): IbzProMonthlyActionUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzProMonthlyActionUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzProMonthlyActionUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzProMonthlyActionUIService.UIServiceMap.set(context.srfdynainstid, new IbzProMonthlyActionUIService({context:context}));
            }
            return IbzProMonthlyActionUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}