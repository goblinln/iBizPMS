import { IBizMdViewModel } from './ibiz-md-view-model';

/**
 * 列表视图模型类
 * 
 * @class IBizListViewModel
 */
export class IBizListViewModel extends IBizMdViewModel {

    /**
     * 实体列表
     * 
     * @memberof IBizListViewModel
     */
    private $list: any = {};

    /**
     * 实体面板
     * 
     * @memberof IBizListViewModel
     */
    private $propertyPanel: any = {};

    /**
     * 加载模型数据（实体部件）
     * 
     * @memberof IBizListViewModel
     */
    public async loaded() {
        await super.loaded();
        this.$list = this.getControlByName('list');
        this.$propertyPanel = this.getControlByName('panel');
    }

    /**
     * 视图列表
     * 
     * @memberof IBizListViewModel
     */
    get viewList(): any {
        return this.$list;
    }

    /**
     * 视图属性面板
     * 
     * @memberof IBizListViewModel
     */
    get viewPropertyPanel(): any {
        return this.$propertyPanel;
    }

}