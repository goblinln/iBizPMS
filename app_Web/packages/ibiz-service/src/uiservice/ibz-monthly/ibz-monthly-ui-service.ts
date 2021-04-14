import { IbzMonthlyUIServiceBase } from './ibz-monthly-ui-service-base';

/**
 * 月报UI服务对象
 *
 * @export
 * @class IbzMonthlyUIService
 */
export default class IbzMonthlyUIService extends IbzMonthlyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzMonthlyUIService
     */
    private static basicUIServiceInstance: IbzMonthlyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzMonthlyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzMonthlyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzMonthlyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzMonthlyUIService
     */
    public static getInstance(context: any): IbzMonthlyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzMonthlyUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzMonthlyUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzMonthlyUIService.UIServiceMap.set(context.srfdynainstid, new IbzMonthlyUIService({context:context}));
            }
            return IbzMonthlyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}