/**
 * 插件实例工厂（部件项，界面行为）
 *
 * @export
 * @class PluginService
 */
export class PluginService {

    /**
     * 单例变量声明
     *
     * @private
     * @static
     * @type {PluginService}
     * @memberof PluginService
     */
    private static PluginService: PluginService;

    /**
     * Creates an instance of PluginService.
     * 私有构造，拒绝通过 new 创建对象
     * 
     * @memberof PluginService
     */
    private constructor() { }

    /**
     * 获取 PluginService 单例对象
     *
     * @static
     * @returns {PluginService}
     * @memberof PluginService
     */
    public static getInstance(): PluginService {
        if (!PluginService.PluginService) {
            PluginService.PluginService = new PluginService();
        }
        return this.PluginService;
    }

    /**
     * 获取插件实例
     *
     * @static
     * @param {string} pluginType 插件类型 CONTROLITEM：部件成员 || UIACTION：界面行为
     * @param {string} pluginCode 插件code
     * 
     * @memberof PluginService
     */
    public getPluginInstance(pluginType: string, pluginCode: string) {
        const windowPlugin: any = (window as any).plugin;
        if (!windowPlugin) return undefined;
        switch (pluginType) {
            case 'CONTROLITEM':
                return windowPlugin.controlItemMap.get(pluginCode);
            case 'UIACTION':
                return windowPlugin.UIActionMap.get(pluginCode);
            default:
                return undefined;
        }
    }

}