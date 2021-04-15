import { IBizMainViewModel } from "./ibiz-main-view-model";

/**
 * 选项操作视图模型类
 * 
 * @class IBizOptViewModel
 */
export class IBizOptViewModel extends IBizMainViewModel {

    /**
     * 实体编辑表单
     * 
     * @memberof IBizOptViewModel
     */
    private $editForm: any = {};

    /**
     * 加载模型数据（实体表单）
     * 
     * @memberof IBizOptViewModel
     */
    public async loaded() {
        await super.loaded();
        this.$editForm = this.getControlByName('form');
    }

    /**
     * 获取视图表单
     * 
     * @memberof IBizOptViewModel
     */
    get viewForm() {
        return this.$editForm;
    }

} 