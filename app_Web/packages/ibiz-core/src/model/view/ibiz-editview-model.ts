import { IBizMainViewModel } from "./ibiz-main-view-model";

/**
 * 编辑视图模型类
 * 
 * @class IBizEditViewModel
 */
export class IBizEditViewModel extends IBizMainViewModel{

    /**
     * 实体编辑表单
     * 
     * @memberof IBizEditViewModel
     */
    private $editForm:any = {};

    /**
     * 标题头信息表单部件
     * 
     * @memberof IBizEditViewModel
     */
    private $dataPanel:any = {};    

    /**
     * 加载模型数据（实体表单）
     * 
     * @memberof IBizEditViewModel
     */
    public async loaded(){
        await super.loaded();
        this.$editForm = this.getControlByName('form');
        this.$dataPanel = this.getControlByName('datapanel');
    }

    /**
     * 获取视图表单
     * 
     * @memberof IBizEditViewModel
     */
    get viewForm(){
        return this.$editForm;
    }

    /**
     * 标题头信息表单部件
     * 
     * @memberof IBizEditViewModel
     */
    get dataPanel(){
        return this.$dataPanel;
    }
}