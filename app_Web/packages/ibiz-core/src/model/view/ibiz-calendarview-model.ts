import { IBizMdViewModel } from "./ibiz-md-view-model";

/**
 * 日历视图模型类
 * 
 * @class IBizCalendarViewModel
 */
export class IBizCalendarViewModel extends IBizMdViewModel {

    /**
     * 日历部件
     * 
     * @memberof IBizCalendarViewModel
     */
    private $calendar: any = {};

    /**
     * 加载模型数据（实体表格）
     * 
     * @memberof IBizGridView
     */
    public async loaded(){
        await super.loaded();
        this.$calendar = this.getControlByName("calendar");
    }

    /**
     * 获取日历部件实例
     * 
     * @memberof IBizCalendarViewModel
     */
    get viewCalendar() {
        return this.$calendar;
    }

}