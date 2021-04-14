import { IBizMdViewModel } from "./ibiz-md-view-model";

/**
 * 表格视图模型类
 * 
 * @class IBizEditViewModel
 */
export class IBizGridViewModel extends IBizMdViewModel {

    /**
     * 表格部件
     * 
     * @memberof IBizGridViewModel
     */
    private $grid: any = {};

    /**
     * 加载模型数据（实体表格）
     * 
     * @memberof IBizGridViewModel
     */
    public async loaded(){
        await super.loaded();
        this.$grid = this.getControlByName('grid');
    }

    /**
     * 是否自动加载
     * 
     * @memberof IBizGridViewModel
     */
    get isAutoLoad(){
        return this.viewGrid.getPSControlParam.autoLoad;
    }

    /**
     * 是否显示提示
     * 
     * @memberof IBizGridViewModel
     */
    get showBusyIndicator(){
        return this.viewGrid.getPSControlParam.showBusyIndicator;
    }

    /**
     * 默认开启行编辑
     * 
     * @memberof IBizGridViewModel
     */
    get rowEditDefault() {
        return this.viewModelData.rowEditDefault;
    }

    /**
     * 获取表格部件
     * 
     * @memberof IBizGridViewModel
     */
    get viewGrid() {
        return this.$grid;
    }

    /**
     * 是否开启行编辑
     * 
     * @memberof IBizGridViewModel
     */
    get enableRowEdit() {
        return this.viewModelData.enableRowEdit;
    }

    /**
     * 表格行数据激活模式
     * 
     * @memberof IBizGridViewModel
     */
    get gridRowActiveMode() {
        return this.viewModelData.gridRowActiveMode;
    }
  

} 