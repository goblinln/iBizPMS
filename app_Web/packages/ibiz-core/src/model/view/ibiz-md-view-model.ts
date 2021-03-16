import { DynamicService } from "../..";
import { IBizMainViewModel } from "./ibiz-main-view-model";

/**
 * 多数据数据视图模型类
 * 
 *  @class IBizMdViewModel
 */
export class IBizMdViewModel extends IBizMainViewModel {

    /**
     * 搜索表单实例引用
     *
     * @private
     * @type {*}
     * @memberof IBizMdViewModel
     */
    protected $searchForm: any;

    /**
     * 初始化AppMainView对象
     * @param opts 额外参数
     * 
     * @memberof IBizMdViewModel
     */
    public constructor(opts: any, context: any) {
        super(opts, context);
    }

    /**
     * 加载模型数据（实体）
     * 
     * @memberof IBizMdViewModel
     */
    public async loaded() {
        await super.loaded();
        this.$searchForm = this.getControlByName('searchform');
        //快速分组代码表
        if(this.enableQuickGroup && this.quickGroupCodeList?.path && this.quickGroupCodeList?.modelref){
            let targetQuickGroupCodeList = await DynamicService.getInstance(this.context).getAppCodeListJsonData(this.quickGroupCodeList.path);
            Object.assign(this.quickGroupCodeList,targetQuickGroupCodeList);
            delete this.quickGroupCodeList.modelref;
        }
    }

    /**
     * 获取搜索表单
     *
     * @memberof IBizMdViewModel
     */
    public getSearchForm() {
        return this.$searchForm;
    }

    /**
     * 是否支持快速分组
     * 
     * @memberof IBizMdViewModel
     */
    get enableQuickGroup() {
        return this.viewModelData.enableQuickGroup;
    }

    /**
     * 只支持批添加
     * 
     * @memberof IBizMdViewModel
     */
    get batchAddOnly() {
        return this.viewModelData.batchAddOnly;
    }

    /**
     * 支持批添加
     * 
     * @memberof IBizMdViewModel
     */
    get enableBatchAdd() {
        return this.viewModelData.enableBatchAdd;
    }

    /**
     * 启用快速建立
     * 
     * @memberof IBizMdViewModel
     */
    get enableQuickCreate() {
        return this.viewModelData.enableQuickCreate;
    }

    /**
     * 是否支持新建数据
     * 
     * @memberof IBizMdViewModel
     */
    get enableNewData() {
        return this.viewModelData.enableNewData;
    }

    /**
     * 是否支持查看数据
     * 
     * @memberof IBizMdViewModel
     */
    get enbaleViewData() {
        return this.viewModelData.enbaleViewData;
    }

    /**
     * 支持编辑数据
     * 
     * @memberof IBizMdViewModel
     */
    get enableEditData() {
        return this.viewModelData.enableEditData;
    }

    /**
     * 是否支持数据过滤
     * 
     * @memberof IBizMdViewModel
     */
    get enableFilter() {
        return this.viewModelData.enableFilter;
    }

    /**
     * 是否支持查看数据
     * 
     * @memberof IBizMdViewModel
     */
    get enableViewData() {
        return this.viewModelData.enableViewData;
    }

    /**
     * 是否支持数据导出
     * 
     * @memberof IBizMdViewModel
     */
    get enableExport() {
        return this.viewModelData.enableExport;
    }

    /**
     * 是否支持数据导入
     * 
     * @memberof IBizMdViewModel
     */
    get enableImport() {
        return this.viewModelData.enableImport;
    }

    /**
     * 是否支持快速搜索
     * 
     * @memberof IBizMdViewModel
     */
    get enableQuickSearch() {
        return this.viewModelData.enableQuickSearch;
    }

    /**
     * 是否支持搜索
     * 
     * @memberof IBizMdViewModel
     */    
    get enableSearch(){
        return this.viewModelData.enableSearch;
    }

    /**
     * 启用数据权限
     * 
     * @memberof IBizCalendarViewModel
     */
    get enableDP() {
        return this.viewModelData.enableDP;
    }

   /**
     * 是否展开搜索表单
     * 
     * @memberof IBizMdViewModel
     */
    get expandSearchForm() {
        return this.viewModelData.expandSearchForm;
    }

    /**
     * 多数据部件激活模式
     * 
     * @memberof IBizMdViewModel
     */
    get mDCtrlActiveMode() {
        return this.viewModelData.mDCtrlActiveMode;
    }

    /**
     * 获取分组代码表
     * 
     * @memberof IBizMdViewModel
     */
    get quickGroupCodeList() {
        return this.viewModelData.getQuickGroupPSCodeList;
    }

}
