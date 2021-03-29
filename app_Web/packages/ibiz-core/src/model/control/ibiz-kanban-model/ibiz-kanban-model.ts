import { DynamicService } from '../../../service';
import { IBizToolBarItemModel } from '../../control';
import { IBizMainControlModel } from '../ibiz-main-control-model';
/**
 * 看板部件
 */
export class IBizKanbanModel extends IBizMainControlModel {
    
    /**
     * 代码表对象
     * 
     * @memberof IBizKanbanModel
     */
    private $codeList: any;

    /**
     * 代码表
     * 
     * @memberof IBizKanbanModel
     */
    get codeList(){
        return this.$codeList;
    }

    /**
     * 显示处理提示
     * 
     * @memberof IBizKanbanModel
     */
    get showBusyIndicator() {
        return this.controlModelData.showBusyIndicator;
    }

    /**
     * 是否附加实体默认数据项
     *
     * @memberof IBizKanbanModel
     */
    get appendDEItems() {
        return this.controlModelData.appendDEItems;
    }

    /**
     * 动态模型文件路径
     *
     * @memberof IBizKanbanModel
     */
    get dynaModelFilePath() {
        return this.controlModelData.dynaModelFilePath;
    }

    /**
     * 是否支持卡片编辑
     *
     * @memberof IBizKanbanModel
     */
    get enableCardEdit() {
        return this.controlModelData.enableCardEdit;
    }

    /**
     * 是否支持卡片分组调整
     *
     * @memberof IBizKanbanModel
     */
    get enableCardEditGroup() {
        return this.controlModelData.enableCardEditGroup;
    }

    /**
     * 是否支持卡片次序调整
     *
     * @memberof IBizKanbanModel
     */
    get enableCardEditOrder() {
        return this.controlModelData.enableCardEditOrder;
    }

    /**
     * 是否支持卡片新建
     *
     * @memberof IBizKanbanModel
     */
    get enableCardNew() {
        return this.controlModelData.enableCardNew;
    }

    /**
     * 是否启用分组
     *
     * @memberof IBizKanbanModel
     */
    get enableGroup() {
        return this.controlModelData.enableGroup;
    }

    /**
     * 是否支持分页栏
     *
     * @memberof IBizKanbanModel
     */
    get enablePagingBar() {
        return this.controlModelData.enablePagingBar;
    }

    /**
     * 分组布局
     *
     * @memberof IBizKanbanModel
     */
    get groupLayout() {
        return this.controlModelData.groupLayout;
    }

    /**
     * 分组模式
     *
     * @memberof IBizKanbanModel
     */
    get groupMode() {
        return this.controlModelData.groupMode;
    }

    /**
     * 是否输出预置流程数据项
     *
     * @memberof IBizKanbanModel
     */
    get hasWFDataItems() {
        return this.controlModelData.hasWFDataItems;
    }

    /**
     * 默认禁用排序
     *
     * @memberof IBizKanbanModel
     */
    get noSort() {
        return this.controlModelData.noSort;
    }

    /**
     * 分组属性
     *
     * @memberof IBizKanbanModel
     */ 
    get groupField() {
        return this.controlModelData.getGroupPSAppDEField;
    }

    //todo 待补充
    /**
     * 排序方向
     * 
     * @memberof IBizKanbanModel
     */
    get minorSortDir() {
        return this.controlModelData.minorSortDir;
    }

    //todo 待补充
    /**
     * 排序字段
     * 
     * @memberof IBizKanbanModel
     */
    get minorSortPSDEF() {
        return this.controlModelData.minorSortPSDEF;
    }

    /**
     * 分页大小
     *
     * @memberof IBizKanbanModel
     */
    get pagingSize() {
        return this.controlModelData.pagingSize;
    }

    /**
     * 只读模式
     *
     * @memberof IBizKanbanModel
     */
    get readOnly() {
        return this.controlModelData.readOnly;
    }

    /**
     * 是否单项选择
     *
     * @memberof IBizKanbanModel
     */
    get singleSelect() {
        return this.controlModelData.singleSelect;
    }

    /**
     * 获取看板项集合
     *
     * @memberof IBizKanbanModel
     */
    get getPSDEKanbanItems() {
        return this.controlModelData.getPSDEDataViewItems;
    }

    /**
     * 获取看板数据项集合
     *
     * @memberof IBizKanbanModel
     */
    get getPSKanbanDataItems() {
        return this.controlModelData.getPSDEDataViewDataItems;
    }

    /**
     * 获取应用实体
     *
     * @memberof IBizKanbanModel
     */
    get getPSAppDataEntity() {
        return this.appDataEntity;
    }

    /**
     * 获取分组代码表
     *
     * @memberof IBizKanbanModel
     */
    get getGroupPSCodeList() {
        return this.controlModelData.getGroupPSCodeList;
    }

    /**
     * 获取分组界面行为组
     *
     * @memberof IBizKanbanModel
     */
    get getGroupPSUIActionGroup() {
        return this.controlModelData.getGroupPSUIActionGroup;
    }

    /**
     * 获取项布局面板
     *
     * @memberof IBizKanbanModel
     */
    get getItemPSLayoutPanel() {
        return this.controlModelData.getItemPSLayoutPanel;
    }

    /**
     * 所有部件
     * 
     * @memberof IBizKanbanModel
     */
    get controls(){
        return this.controlModelData.getPSControls;
    }

    protected $uiActionGroup: Array<any> = [];

     /**
     * 通过control名称获取部件模型数据
     * 
     * @memberof IBizKanbanModel
     */
    public getUiAction(name: string) {
        if (this.$uiActionGroup.length > 0) {
            return this.$uiActionGroup.find((item: any) => {
                return item.caption == name;
            })
        }
    }

    /**
     * 加载模型数据(应用实体)
     *
     * @memberof IBizKanbanModel
     */
    public async loaded() {  
        await super.loaded();
        // 分组代码表
        if (this.getGroupPSCodeList?.modelref) {
            this.$codeList = await DynamicService.getInstance(this.context).getAppCodeListJsonData(this.getGroupPSCodeList.path);
        }
        // 数据项代码表
        if (this.getPSKanbanDataItems?.length > 0){
            for(const dataItem of this.getPSKanbanDataItems){
                if(dataItem.getFrontPSCodeList?.modelref && dataItem.getFrontPSCodeList.path){
                    const targetCodeList: any = await DynamicService.getInstance(this.context).getAppCodeListJsonData(dataItem.getFrontPSCodeList.path);
                    Object.assign(dataItem.getFrontPSCodeList, targetCodeList);
                }
            }
        }
    }

    /**
     * 获取部件工具栏项
     * 
     * @memberof IBizKanbanModel
     */
    get controlToolBarItems(){
        let targetItems:any[] = [];
        if(this.quickToolbar?.getPSDEToolbarItems){
            this.quickToolbar.getPSDEToolbarItems.forEach((element:any) => {
                targetItems.push(new IBizToolBarItemModel(element));
            });
        }
        return targetItems;
    }

    /**
     * 获取快速操纵栏
     * 
     * @memberof IBizKanbanModel
     */
    get quickToolbar(){
        return this.getControlDataByName("kanban_quicktoolbar");
    }

    /**
     * 看板高度
     * 
     * @memberof IBizKanbanModel
     */
    get groupHeight(){
        return this.controlModelData.groupHeight;
    }

    /**
     * 看板宽度
     * 
     * @memberof IBizKanbanModel
     */
    get groupWidth(){
        return this.controlModelData.groupWidth;
    }

    /**
     * 获取分组样式表
     * 
     * @memberof IBizKanbanModel
     */
    get getGroupPSSysCss(){
        return this.controlModelData.getGroupPSSysCss;
    }

    /**
     * 获取项绘制插件
     * 
     * @memberof IBizKanbanModel
     */
    get getItemPSSysPFPlugin(){
        return this.controlModelData.getItemPSSysPFPlugin;
    }

    /**
     * 获取分组绘制插件
     * 
     * @memberof IBizKanbanModel
     */
    get getGroupPSSysPFPlugin(){
        return this.controlModelData.getGroupPSSysPFPlugin;
    }
    
    /**
     * 获取更新分组行为
     * 
     * @memberof IBizKanbanModel
     */
    get updateGroupAction() {
        return this.findHandlerActionMethodCodeName("updategroup");
    }
}
