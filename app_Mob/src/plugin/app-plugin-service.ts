import { CUSTOM01e334068e } from './plugin/custom/custom01e334068e';
/**
 * 插件实例工厂（部件项，界面行为）
 *
 * @export
 * @class AppPluginService
 */
export class AppPluginService {

    /**
     * 单例变量声明
     *
     * @private
     * @static
     * @type {AppPluginService}
     * @memberof AppPluginService
     */
    private static AppPluginService: AppPluginService;

    /**
     * 部件成员Map
     *
     * @private
     * @static
     * @type Map<string,any>
     * 
     * @memberof AppPluginService
     */
    private controlItemMap:Map<string,any> = new Map();

    /**
     * 界面行为Map
     *
     * @private
     * @static
     * @type Map<string,any>
     * 
     * @memberof AppPluginService
     */
    private UIActionMap:Map<string,any> = new Map();

    /**
     * Creates an instance of AppPluginService.
     * 私有构造，拒绝通过 new 创建对象
     * 
     * @memberof AppPluginService
     */
    private constructor() {
        this.registerControlItemPlugin();
        this.registerUIActionPlugin();
    }

    /**
     * 获取 AppPluginService 单例对象
     *
     * @static
     * @returns {AppPluginService}
     * @memberof AppPluginService
     */
    public static getInstance(): AppPluginService {
        if (!AppPluginService.AppPluginService) {
            AppPluginService.AppPluginService = new AppPluginService();
        }
        return this.AppPluginService;
    }

    /**
     * 注册部件成员插件
     * 
     * @memberof AppPluginService
     */
    private registerControlItemPlugin(){
        this.controlItemMap.set('',new CUSTOM01e334068e());
    }

    /**
     * 注册界面行为插件
     * 
     * @memberof AppPluginService
     */
    private registerUIActionPlugin(){
    }
    
}
export const installPlugin:Function = () =>{
    (window as any).plugin = AppPluginService.getInstance();
}