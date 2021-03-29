import { IBizMainViewModel } from "./ibiz-main-view-model";

/**
 * 列表视图模型类
 * 
 * @class IBizListViewModel
 */
export class IBizHtmlViewModel extends IBizMainViewModel {

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
        this.$propertyPanel = this.getControlByName('panel');
    }

    /**
     * 视图属性面板
     * 
     * @memberof IBizListViewModel
     */
    get viewPropertyPanel(): any {
        return this.$propertyPanel;
    }

    /**
     * 嵌入视图路径
     *
     * @public
     * @type string
     * @memberof ${srfclassname('${view.name}')}Base
     */
    get iframeUrl(): any {
        return this.viewModelData.htmlUrl;
    }

}