import { IBizMdViewModel } from "./ibiz-md-view-model";

/**
 * 卡片导航视图模型类
 * 
 * @class IBizDataViewExpViewModel
 */
export class IBizDataViewExpViewModel extends IBizMdViewModel {

    /**
     * 实体卡片导航部件
     * 
     * @memberof IBizDataViewViewModel
     */
    private $dataviewExpBar: any;

    /**
     * 导航边栏位置
     * 
     * @memberof IBizDataViewViewModel
     */
    get sideBarLayout() {
        return this.viewModelData.sideBarLayout;
    }

    /**
     * 加载模型数据（实体卡片）
     * 
     * @memberof IBizDataViewViewModel
     */
    public async loaded() {
        await super.loaded();
        this.$dataviewExpBar = this.getControlByName('dataviewexpbar');
    }

    /**
     * 获取卡片导航部件
     * 
     * @memberof IBizDataViewViewModel
     */
    get viewDataviewExpBar() {
        return this.$dataviewExpBar;
    }
}