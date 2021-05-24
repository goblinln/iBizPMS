import { IBIZProPluginUIServiceBase } from './ibizpro-plugin-ui-service-base';

/**
 * 系统插件UI服务对象
 *
 * @export
 * @class IBIZProPluginUIService
 */
export default class IBIZProPluginUIService extends IBIZProPluginUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof IBIZProPluginUIService
     */
    private static basicUIServiceInstance: IBIZProPluginUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof IBIZProPluginUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  IBIZProPluginUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  IBIZProPluginUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof IBIZProPluginUIService
     */
    public static getInstance(context: any): IBIZProPluginUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new IBIZProPluginUIService({context:context});
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!IBIZProPluginUIService.UIServiceMap.get(context.srfdynainstid)) {
                IBIZProPluginUIService.UIServiceMap.set(context.srfdynainstid, new IBIZProPluginUIService({context:context}));
            }
            return IBIZProPluginUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

}