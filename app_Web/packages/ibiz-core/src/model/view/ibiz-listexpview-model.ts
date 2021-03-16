import { IBizMainViewModel } from "./ibiz-main-view-model";

/**
 * 列表导航视图模型类
 * 
 * @class IBizKanbanViewModel
 */
export class IBizListExpViewModel extends IBizMainViewModel {

    /**
     * 实体列表导航栏
     * 
     * @memberof IBizListExpViewModel
     */
    private $listExpBar: any = {};

    /**
     * 加载模型数据
     * 
     * @memberof IBizListExpViewModel
     */
    public async loaded() {
        await super.loaded();
        this.$listExpBar = this.getControlByName('listexpbar');
    }

    /**
     * 获取实体列表导航栏
     * 
     * @memberof IBizListExpViewModel
     */
    get viewListExpBar() {
        return this.$listExpBar;
    }

    /**
     * 侧边栏布局
     * 
     * @memberof IBizListExpViewModel
     */
    get sideBarLayout(){
      return this.viewModelData.sideBarLayout;
    }

}