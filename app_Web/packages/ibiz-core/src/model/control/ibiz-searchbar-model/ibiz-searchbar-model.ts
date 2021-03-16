import { IBizMainControlModel } from '../ibiz-main-control-model';

export class IBizSearchBarModel extends IBizMainControlModel {

    /**
     * 是否开启快速搜索
     * 
     * @readonly
     * @memberof IBizSearchBarModel
     */
    get enableQuickSearch() {
        return this.controlModelData.enableQuickSearch;
    }

    /**
     * 是否开启分组
     * 
     * @readonly
     * @memberof IBizSearchBarModel
     */
    get enableGroup() {
        return this.controlModelData.enableGroup;
    }

    /**
     * 快速搜索模式
     * 
     * @readonly
     * @memberof IBizSearchBarModel
     */
    get quickSearchMode() {
        return this.controlModelData.quickSearchMode;
    }

    /**
     * 搜索栏过滤项
     * 
     * @readonly
     * @memberof IBizSearchBarModel
     */
    get searchBarFilters() {
        return this.controlModelData.getPSSearchBarFilters;
    }

    /**
     * 是否是移动端搜索栏
     * 
     * @readonly
     * @memberof IBizSearchBarModel
     */
    get mobileSearchBar() {
        return this.controlModelData.mobileSearchBar;
    }

    /**
     * 快速搜索宽度
     * 
     * @readonly
     * @memberof IBizSearchBarModel
     */
    get quickSearchWidth() {
        return this.controlModelData.quickSearchWidth;
    }

    /**
     * 开启过滤
     * 
     * @readonly
     * @memberof IBizSearchBarModel
     */
    get enableFilter() {
        return this.controlModelData.enableFilter;
    }

}