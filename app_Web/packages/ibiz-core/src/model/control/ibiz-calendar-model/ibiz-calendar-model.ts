import { IBizMainControlModel } from '../ibiz-main-control-model';
import { DynamicService } from '../../../service';
import { IBizEntityModel } from '../../entity';

/**
 * 日历部件实例
 */
export class IBizCalendarModel extends IBizMainControlModel {

    /**
     * 日历项集合
     * 
     * @protected
     * @memberof IBizCalendarModel
     */
    protected $calendarItemMap: Map<string, any> = new Map();

    /**
     * 快速工具栏
     * 
     * @protected
     * @memberof IBizCalendarModel
     */
    protected $quickToolbar: any = {};

    /**
     * 日历部件类型
     * 
     * @memberof IBizCalendarModel
     */
    get calendarStyle() {
        return this.controlModelData.calendarStyle;
    }

    /**
     * 是否自动加载
     * 
     * @memberof IBizCalendarModel
     */
    get autoLoad() {
        return this.controlModelData.autoLoad;
    }

    /**
     * 是否开启项权限
     * 
     * @memberof IBizCalendarModel
     */
    get enableItemPrivilege() {
        return this.controlModelData.enableItemPrivilege;
    }

    /**
     * 显示处理提示
     * 
     * @memberof IBizCalendarModel
     */
    get showBusyIndicator() {
        return this.controlModelData.showBusyIndicator;
    }

    /**
     * 日历项集合
     * 
     * @memberof IBizCalendarModel
     */
    get getPSSysCalendarItems() {
        return this.controlModelData.getPSSysCalendarItems;
    }

    get getPSAppDataEntity() {
        return this.controlModelData.getPSAppDataEntity;
    }

    /**
     * 加载模型数据
     * 
     * @memberof IBizCalendarModel
     */
    public async loaded() {
        await super.loaded();
        await this.initCalendarItems();
        await this.initQuickToolbar();
    }

    /**
     * 初始化所有日历项
     * 
     * @memberof IBizCalendarModel
     */
    public async initCalendarItems() {
        if(this.getPSSysCalendarItems?.length>0) {
            for(const item of this.getPSSysCalendarItems) {
                // 加载实体
                if(item.getPSAppDataEntity?.modelref && item.getPSAppDataEntity.path){
                    const targetAppEntity: any = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(item.getPSAppDataEntity.path);
                    Object.assign(item.getPSAppDataEntity,targetAppEntity);
                    delete item.getPSAppDataEntity.modelref;
                }
                // 创建实体实例
                if(item.getPSAppDataEntity){
                    item.$appDataEntity = new IBizEntityModel(item.getPSAppDataEntity)
                }
                // 加载导航视图
                if(item.getNavPSAppView?.modelref && item.getNavPSAppView.path){
                    const targetNavAppView: any = await DynamicService.getInstance(this.context).getAppViewModelJsonData(item.getNavPSAppView.path);
                    Object.assign(item.getNavPSAppView,targetNavAppView);
                    delete item.getNavPSAppView.modelref;
                }
                this.$calendarItemMap.set(item.itemType, item);
            }
        }
    }

    /**
     * 初始化快速工具栏
     * 
     * @memberof IBizCalendarModel
     */
    public async initQuickToolbar() {
        if(this.controls?.length>0) {
            let quickToolbar: any = this.controls.find((_item: any) => {
                return _item.name == "calendar_quicktoolbar";
            })
            if(quickToolbar) {
                let { getPSAppDataEntity: appde } = quickToolbar;
                if(appde && appde.path) {
                    const targetEntity: any = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(appde.path);
                    Object.assign(appde, new IBizEntityModel(targetEntity));
                    quickToolbar.$appDataEntity = appde;
                }
                this.$quickToolbar = quickToolbar;
            }
        }
    }

    /**
     * 获取指定日历项
     *
     * @param {string} itemType
     * @returns
     * @memberof IBizCalendarModel
     */
    public getCalendarItem(itemType: string){
        return this.$calendarItemMap.get(itemType);
    }

    /**
     * 获取日历项集合
     * 
     * @memberof IBizCalendarModel
     */
    get calendarItems() {
        return [...this.$calendarItemMap.values()];
    }

    /**
     * 获取快速工具栏
     * 
     * @memberof IBizCalendarModel
     */
    get quickToolbar() {
        return this.$quickToolbar;
    }

    /**
     * 日历部件类型
     * 
     * @memberof IBizCalendarModel
     */
    get controlStyle() {
        return this.calendarStyle && this.calendarStyle == "TIMELINE" ? "TIMELINE" : this.controlModelData.controlStyle ? this.controlModelData.controlStyle : "DEFAULT";
    }
}