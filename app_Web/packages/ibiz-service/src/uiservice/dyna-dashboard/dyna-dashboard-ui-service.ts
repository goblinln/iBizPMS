import { DynaDashboardUIServiceBase } from './dyna-dashboard-ui-service-base';

/**
 * 动态数据看板UI服务对象
 *
 * @export
 * @class DynaDashboardUIService
 */
export default class DynaDashboardUIService extends DynaDashboardUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof DynaDashboardUIService
     */
    private static basicUIServiceInstance: DynaDashboardUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof DynaDashboardUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DynaDashboardUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  DynaDashboardUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DynaDashboardUIService
     */
    public static getInstance(context: any): DynaDashboardUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new DynaDashboardUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!DynaDashboardUIService.UIServiceMap.get(context.srfdynainstid)) {
                DynaDashboardUIService.UIServiceMap.set(context.srfdynainstid, new DynaDashboardUIService({context:context}));
            }
            return DynaDashboardUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}