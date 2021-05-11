import { DynaFilterUIServiceBase } from './dyna-filter-ui-service-base';

/**
 * 动态搜索栏UI服务对象
 *
 * @export
 * @class DynaFilterUIService
 */
export default class DynaFilterUIService extends DynaFilterUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof DynaFilterUIService
     */
    private static basicUIServiceInstance: DynaFilterUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof DynaFilterUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  DynaFilterUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  DynaFilterUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof DynaFilterUIService
     */
    public static getInstance(context: any): DynaFilterUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new DynaFilterUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!DynaFilterUIService.UIServiceMap.get(context.srfdynainstid)) {
                DynaFilterUIService.UIServiceMap.set(context.srfdynainstid, new DynaFilterUIService({context:context}));
            }
            return DynaFilterUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}