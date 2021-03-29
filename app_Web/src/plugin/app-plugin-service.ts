import { GridPlanType } from './plugin/grid-colrender/grid-plan-type';
import { TASKTYPEFATHERORCHILD } from './plugin/grid-colrender/tasktypefatherorchild';
import { GridUserColorRed } from './plugin/grid-colrender/grid-user-color-red';
import { Gridmilepost } from './plugin/grid-colrender/gridmilepost';
import { GirdCounmColor } from './plugin/grid-colrender/gird-counm-color';
import { TASKASSPlugin } from './plugin/grid-colrender/taskassplugin';
import { Associatedform } from './plugin/custom/associatedform';
import { MeditCollapseArrow } from './plugin/editform-render/medit-collapse-arrow';
import { Listicon } from './plugin/list-itemrender/listicon';
import { GridProgress } from './plugin/grid-colrender/grid-progress';
import { GridIcon } from './plugin/grid-colrender/grid-icon';
import { RedCloumnPlugin } from './plugin/grid-colrender/red-cloumn-plugin';
import { StoryGridType } from './plugin/grid-colrender/story-grid-type';
import { Kanbantitle } from './plugin/custom/kanbantitle';
import { GirdCoumLJ } from './plugin/grid-colrender/gird-coum-lj';
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
        this.controlItemMap.set('gridPlanType',new GridPlanType());
        this.controlItemMap.set('TASKTYPEFATHERORCHILD',new TASKTYPEFATHERORCHILD());
        this.controlItemMap.set('gridUserColorRed',new GridUserColorRed());
        this.controlItemMap.set('Gridmilepost',new Gridmilepost());
        this.controlItemMap.set('girdCounmColor',new GirdCounmColor());
        this.controlItemMap.set('TASKASSPlugin',new TASKASSPlugin());
        this.controlItemMap.set('Associatedform',new Associatedform());
        this.controlItemMap.set('meditCollapseArrow',new MeditCollapseArrow());
        this.controlItemMap.set('listicon',new Listicon());
        this.controlItemMap.set('gridProgress',new GridProgress());
        this.controlItemMap.set('gridIcon',new GridIcon());
        this.controlItemMap.set('redCloumnPlugin',new RedCloumnPlugin());
        this.controlItemMap.set('storyGridType',new StoryGridType());
        this.controlItemMap.set('kanbantitle',new Kanbantitle());
        this.controlItemMap.set('girdCoumLJ',new GirdCoumLJ());
    }

    /**
     * 注册界面行为插件
     * 
     * @memberof AppPluginService
     */
    private registerUIActionPlugin(){
        this.UIActionMap.set('downloadall',() => import('./plugin/deuiaction/downloadall'));
        this.UIActionMap.set('fileibzdownload',() => import('./plugin/deuiaction/fileibzdownload'));
        this.UIActionMap.set('downloadselect',() => import('./plugin/deuiaction/downloadselect'));
    }
    
}
export const installPlugin:Function = () =>{
    (window as any).plugin = AppPluginService.getInstance();
}