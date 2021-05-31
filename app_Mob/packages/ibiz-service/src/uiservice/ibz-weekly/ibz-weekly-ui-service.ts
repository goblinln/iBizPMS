import { IbzWeeklyUIServiceBase } from './ibz-weekly-ui-service-base';

/**
 * 周报UI服务对象
 *
 * @export
 * @class IbzWeeklyUIService
 */
export default class IbzWeeklyUIService extends IbzWeeklyUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IbzWeeklyUIService
     */
    private static basicUIServiceInstance: IbzWeeklyUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IbzWeeklyUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IbzWeeklyUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IbzWeeklyUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IbzWeeklyUIService
     */
    public static getInstance(context: any): IbzWeeklyUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IbzWeeklyUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IbzWeeklyUIService.UIServiceMap.get(context.srfdynainstid)) {
                IbzWeeklyUIService.UIServiceMap.set(context.srfdynainstid, new IbzWeeklyUIService({context:context}));
            }
            return IbzWeeklyUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}