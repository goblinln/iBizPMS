import { IBizMdViewModel } from './ibiz-md-view-model';

/**
 * 选择表格视图模型类
 * 
 * @class IBizPickupGridViewModel
 */
export class IBizPickupGridViewModel extends IBizMdViewModel {

    /**
     * 表格部件
     * 
     * @private
     * @type {*}
     * @memberof IBizPickupGridViewModel
     */
    private $grid: any = {};

    /**
     * 表格行数据默认激活模式
     * 
     * @memberof IBizPickupGridViewModel
     */
    get gridRowActiveMode() {
        return this.viewModelData.gridRowActiveMode;
    }

    /**
     * 是否开启行编辑
     * 
     * @memberof IBizPickupGridViewModel
     */
    get rowEditDefault() {
        return this.viewModelData.rowEditDefault;
    }

    /**
     * 开启行编辑
     * 
     * @memberof IBizPickupGridViewModel
     */
    get enableRowEdit() {
        return this.viewModelData.enableRowEdit;
    }

    /**
     * 加载模型数据
     * 
     * @memberof IBizPickupGridViewModel
     */
    public async loaded() {
        await super.loaded();
        this.$grid = this.getControlByName("grid");
    }

    /**
     * 获取表格JSON
     * 
     * @memberof IBizPickupGridViewModel
     */
    get viewGrid() {
        return this.$grid;
    }
}