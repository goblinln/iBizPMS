import { ModuleUIServiceBase } from './module-ui-service-base';

/**
 * 模块UI服务对象
 *
 * @export
 * @class ModuleUIService
 */
export default class ModuleUIService extends ModuleUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof ModuleUIService
     */
    private static basicUIServiceInstance: ModuleUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof ModuleUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  ModuleUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  ModuleUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof ModuleUIService
     */
    public static getInstance(context: any): ModuleUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new ModuleUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!ModuleUIService.UIServiceMap.get(context.srfdynainstid)) {
                ModuleUIService.UIServiceMap.set(context.srfdynainstid, new ModuleUIService({context:context}));
            }
            return ModuleUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}