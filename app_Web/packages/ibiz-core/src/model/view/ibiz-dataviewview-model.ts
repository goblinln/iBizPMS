import { IBizMdViewModel } from "./ibiz-md-view-model";

/**
 * 数据视图模型类
 * 
 * @class IBizDataViewViewModel
 */
export class IBizDataViewViewModel extends IBizMdViewModel {

    /**
     * 实体卡片视图模型类
     * 
     * @memberof IBizDataViewViewModel
     */
    private $dataview: any = {};

    /**
     * 加载模型数据（实体卡片）
     * 
     * @memberof IBizDataViewViewModel
     */
    public async loaded(){
        await super.loaded();
        this.$dataview = this.getControlByName('dataview');
    }

    /**
     * 获取视图卡片
     * 
     * @memberof IBizDataViewViewModel
     */
    get viewDataView() {
        return this.$dataview;
    }
}