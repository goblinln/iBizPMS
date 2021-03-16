import { IBizMdViewModel } from "./ibiz-md-view-model";
/**
 * 日历导航视图模型类
 * 
 * @class IBizCalendarExpViewModel
 */
export class IBizCalendarExpViewModel extends IBizMdViewModel {
    /**
     * 日历导航模型数据
     * 
     * @memberof IBizCalendarExpViewModel
     */
    private $calenDarExpBar: any = {};

    /**
     * 导航边栏位置
     * 
     * @memberof IBizCalendarExpViewModel
     */
    get sideBarLayout() {
        return this.viewModelData.sideBarLayout;
    }

    /**
     * 加载模型数据（实体日历导航）
     * 
     * @memberof IBizCalendarExpViewModel
     */
    public async loaded(){
        await super.loaded();
        this.$calenDarExpBar = this.getControlByName('calendarexpbar');
    }

    /**
     * 日历导航模型数据
     * 
     * @memberof IBizCalendarExpViewModel
     */
    get viewCalenDarExpBar() {
        return this.$calenDarExpBar;
    }
}