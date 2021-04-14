import { IBizMdViewModel } from "./ibiz-md-view-model";

/**
 * 表格导航视图模型类
 * 
 * @class IBizEditViewModel
 */
export class IBizGridExpViewModel extends IBizMdViewModel {

    /**
     * 表格导航模型数据
     * 
     * @memberof IBizGridViewModel
     */
    private $gridExpBar: any = {};

    /**
     * 导航边栏位置
     * 
     * @memberof IBizGridViewModel
     */
    get sideBarLayout() {
        return this.viewModelData.sideBarLayout;
    }

    /**
     * 加载模型数据（实体表格导航）
     * 
     * @memberof IBizGridViewModel
     */
    public async loaded(){
        await super.loaded();
        this.$gridExpBar = this.getControlByName('gridexpbar');
    }

    /**
     * 表格导航模型数据
     * 
     * @memberof IBizGridViewModel
     */
    get viewGridExpBar() {
        return this.$gridExpBar;
    }
}