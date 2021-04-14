import { IBizMainViewModel } from "./ibiz-main-view-model";

/**
 * 分页导航视图模型类
 * 
 * @class IBizPickUpViewModel
 */
export class IBizTabExpViewModel extends IBizMainViewModel {

    /**
     * 实体分页导航面板
     * 
     * @memberof IBizTabExpViewModel
     */
    private $tabExpPanel: any = {};

    /**
     * 加载模型数据（实体分页导航面板）
     * 
     * @memberof IBizTabExpViewModel
     */
    public async loaded() {
        await super.loaded();
        this.$tabExpPanel = this.getControlByName('tabexppanel');
    }

    /**
     * 获取视图分页导航面板
     * 
     * @memberof IBizTabExpViewModel
     */
    get viewTabExpPanel() {
        return this.$tabExpPanel;
    }
}