import { IBizMdViewModel } from "./ibiz-md-view-model";

/**
 * 甘特视图模型类
 * 
 * @class IBizEditViewModel
 */
export class IBizGanttViewModel extends IBizMdViewModel {

    /**
     * 甘特图部件
     * 
     * @type {*}
     * @memberof IBizGanttViewModel
     */
    private $gantt: any = {};

    /**
     * 加载模型数据
     * 
     * @memberof IBizGanttViewModel
     */
    public async loaded(){
        await super.loaded();
        this.$gantt = this.getControlByName("gantt");
    }

    /**
     * 获取甘特部件
     * 
     * @memberof IBizGanttViewModel
     */
    get viewGantt() {
        return this.$gantt;
    }

}