import { DynamicService } from '../../../service';
import { IBizEntityModel } from '../../entity';
import { IBizToolBarItemModel } from '../../control';
import { IBizMainControlModel } from '../ibiz-main-control-model';

export class IBizDataViewModel extends IBizMainControlModel {

    /**
     * 初始化 IBizDataViewModel 对象
     * 
     * @param opts 额外参数
     * @memberof IBizDataViewModel
     */
    public constructor(opts: any = {}, viewRef?: any, parentRef?: any, runtimeData?: any) {
        super(opts, viewRef, parentRef, runtimeData);
        //设置项集合
        this.initDataViewItem();
    }

    /**
     * 加载模型数据(导航视图)
     *
     * @memberof IBizMainControlModel
     */
    public async loaded() {
        await super.loaded();
        if (this.controlModelData?.getNavPSAppView?.modelref && this.controlModelData.getNavPSAppView.path) {
            const targetView = await DynamicService.getInstance(this.context).getAppViewModelJsonData(this.controlModelData.getNavPSAppView.path);
            Object.assign(this.controlModelData.getNavPSAppView, targetView);
        }

        // 加载面板实体
        if(this.getItemPSLayoutPanel?.getPSAppDataEntity){
            if(this.getItemPSLayoutPanel.getPSAppDataEntity.modelref && this.getItemPSLayoutPanel.getPSAppDataEntity.path){
                let targetAppEntity: any = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(this.getItemPSLayoutPanel.getPSAppDataEntity.path);
                Object.assign(this.getItemPSLayoutPanel.getPSAppDataEntity, targetAppEntity);
                delete this.getItemPSLayoutPanel.getPSAppDataEntity.modelref;
            }
            if(this.getItemPSLayoutPanel.getPSAppDataEntity){
                this.getItemPSLayoutPanel.$appDataEntity = new IBizEntityModel(this.getItemPSLayoutPanel.getPSAppDataEntity);
                this.getItemPSLayoutPanel.appDeCodeName = this.getItemPSLayoutPanel.$appDataEntity.codeName.toLowerCase();
            }
        }
    }

    /**
     * 卡片视图项集合
     * 
     * @protected
     * @type {Map<string, any>}
     * @memberof IBizDataViewModel
     */
    protected $cardViewItemMap: Map<string, any> = new Map();

    /**
     * 数据项实例集合
     * 
     * @protected
     * @type {Map<string, any>}
     * @memberof IBizDataViewModel
     */
    protected $dataviewItemMap: Map<string, any> = new Map();

    /**
     * 所有卡片视图项实例
     * 
     * @memberof IBizDataViewModel
     */
    get cardViewItems() {
        return [...this.$cardViewItemMap.values()];
    }

    /**
     * 所有数据项实例
     * 
     * @memberof IBizDataViewModel
     */
    get dataViewItems() {
        return [...this.$dataviewItemMap.values()];
    }

    /**
     * 是否开启分组
     * 
     * @memberof IBizDataViewModel
     */
    get enableGroup() {
        return this.controlModelData.enableGroup;
    }

    /**
     * 分组模式
     * 
     * @memberof IBizDataViewModel
     */
    get groupMode() {
        return this.controlModelData.groupMode;
    }

    /**
     * 分页条数
     * 
     * @memberof IBizDataViewModel
     */
    get limit() {
        return this.controlModelData.pagingSize;
    }

    //todo 待补充
    /**
     * 排序方向
     * 
     * @memberof IBizDataViewModel
     */
    get minorSortDir() {
        return this.controlModelData.minorSortDir;
    }

    //  todo    待补充
    get groupField() {
        return this.controlModelData.groupField;
    }

    //todo 待补充
    /**
     * 排序字段
     * 
     * @memberof IBizDataViewModel
     */
    get minorSortPSDEF() {
        return this.controlModelData.minorSortPSDEF;
    }

    //todo  待确认标识
    /**
     * 分组代码表
     * 
     * @memberof IBizDataViewModel
     */
    get groupCodeList() {
        return this.controlModelData.groupCodeList;
    }

    //todo 
    /**
     * 获取二级排序属性
     */
    get getMinorSortPSDEF() {
        return this.controlModelData.getMinorSortPSDEF;
    }

    //todo 
    /**
     * 默认排序方向
     */
    get getMinorSortDir() {
        return this.controlModelData.getMinorSortDir;
    }

    //todo
    /**
     * 卡片高度
     */
    get getCardHeight() {
        return this.controlModelData.getCardHeight;
    }

    //todo
    /**
     * 卡片宽度
     */
    get getCardWidth() {
        return this.controlModelData.getCardWidth;
    }

    /**
     * 是否默认选中第一条数据
     */
    get isSelectFirstDefault() {
        return this.controlModelData.isSelectFirstDefault;
    }

    /**
     * 卡片视图项集合
     * 
     * @memberof IBizDataViewModel
     */
    get getPSDEDataViewItems() {
        return this.controlModelData.getPSDEDataViewItems;
    }

    /**
     * 是否支持卡片新建
     * 
     * @memberof IBizDataViewModel
     */
    get enableCardNew() {
        return this.controlModelData.enableCardNew;
    }

    /**
     * 是否附加实体默认数据项
     * 
     * @memberof IBizDataViewModel
     */
    get appendDEItems() {
        return this.controlModelData.appendDEItems;
    }

    /**
     * 是否支持卡片分组调整
     * 
     * @memberof IBizDataViewModel
     */
    get enableCardEditGroup() {
        return this.controlModelData.enableCardEditGroup;
    }

    /**
     * 是否支持卡片编辑
     * 
     * @memberof IBizDataViewModel
     */
    get enableCardEdit() {
        return this.controlModelData.enableCardEdit;
    }

    /**
     * 是否支持卡片次序调整
     * 
     * @memberof IBizDataViewModel
     */
    get enableCardEditOrder() {
        return this.controlModelData.enableCardEditOrder;
    }

    /**
     * 部件类型
     * 
     * @memberof IBizDataViewModel
     */
    get controlType() {
        return this.controlModelData.controlType;
    }

    /**
     * 是否支持分页栏
     * 
     * @memberof IBizDataViewModel
     */
    get enablePagingBar() {
        return this.controlModelData.enablePagingBar;
    }

    /**
     * 是否输出预置流程数据项
     * 
     * @memberof IBizDataViewModel
     */
    get hasWFDataItems() {
        return this.controlModelData.hasWFDataItems;
    }

    /**
     * 是否单选
     * 
     * @memberof IBizDataViewModel
     */
    get singleSelect() {
        return this.controlModelData.singleSelect;
    }

    /**
     * 是否默认禁用排序
     * 
     * @memberof IBizDataViewModel
     */
    get noSort() {
        return this.controlModelData.noSort;
    }

    /**
     * 是否只读模式
     * 
     * @memberof IBizDataViewModel
     */
    get readOnly() {
        return this.controlModelData.readOnly;
    }

    /**
     * 是否启用项权限
     * 
     * @memberof IBizDataViewModel
     */
    get enableItemPrivilege() {
        return this.controlModelData.enableItemPrivilege;
    }

    /**
     * 是否默认加载
     * 
     * @memberof IBizDataViewModel
     */
    get autoLoad() {
        return this.controlModelData.autoLoad;
    }

    /**
     * 是否显示处理提示
     * 
     * @memberof IBizDataViewModel
     */
    get showBusyIndicator() {
        return this.controlModelData.showBusyIndicator;
    }

    /**
     * 动态模型文件路径
     * 
     * @memberof IBizDataViewModel
     */
    get dynaModelFilePath() {
        return this.controlModelData.dynaModelFilePath;
    }

    /**
     * 部件逻辑名称
     * 
     * @memberof IBizDataViewModel 
     */
    get logicName() {
        return this.controlModelData.logicName;
    }

    /**
     * 数据导入对象
     * 
     * @memberof IBizDataViewModel
     */
    get getPSDEDataImport() {
        return this.controlModelData.getPSDEDataImport;
    }

    /**
     * 建立数据行为
     * 
     * @memberof IBizDataViewModel
     */
    get getCreatePSControlAction() {
        return this.controlModelData.getCreatePSControlAction;
    }

    /**
     * 更新数据行为
     * 
     * @memberof IBizDataViewModel
     */
    get getUpdatePSControlAction() {
        return this.controlModelData.getUpdatePSControlAction;
    }

    /**
     * 查询数据行为
     * 
     * @memberof IBizDataViewModel
     */
    get getFetchPSControlAction() {
        return this.controlModelData.getFetchPSControlAction;
    }

    /**
     * 删除数据行为
     * 
     * @memberof IBizDataViewModel
     */
    get getRemovePSControlAction() {
        return this.controlModelData.getRemovePSControlAction;
    }

    /**
     * 获取数据行为
     * 
     * @memberof IBizDataViewModel
     */
    get getGetPSControlAction() {
        return this.controlModelData.getGetPSControlAction;
    }

    /**
     * 界面样式
     * 
     * @memberof IBizDataViewModel
     */
    get getPSSysCss() {
        return this.controlModelData.getPSSysCss;
    }

    /**
     * 应用实体
     * 
     * @memberof IBizDataViewModel
     */
    get getPSAppDataEntity() {
        return this.controlModelData.getPSAppDataEntity;
    }

    /**
     * 获取部件工具栏项
     * 
     * @memberof IBizListModel
     */
    public controlToolBarItems(ctrlName: string){
        let targetItems:any[] = [];
        const tool: any = this.getControlDataByName(ctrlName);
        if(tool?.getPSDEToolbarItems){
            tool.getPSDEToolbarItems.forEach((element:any) => {
                targetItems.push(new IBizToolBarItemModel(element));
            });
        }
        return targetItems;
    }

    /**
     * 快速工具栏部件
     * 
     * @memberof IBizDataViewModel
     */
	get getQuickPSDEToolbar() {
		return this.getControlDataByName('dataview_quicktoolbar');
    }

    /**
     * 批操作工具栏部件
     * 
     * @memberof IBizDataViewModel
     */
    get getBatchPSDEToolbar() {
		return this.getControlDataByName('dataview_batchtoolbar');
	}

    /**
     * 初始化所有项集合
     * 
     * @memberof IBizDataViewModel
     */
    public initDataViewItem() {
        if (this.controlModelData.getPSDEDataViewItems?.length > 0) {
            for (let item of this.controlModelData.getPSDEDataViewItems) {
                this.$cardViewItemMap.set(item.name ? item.name.toLowerCase() : item.codeName.toLowerCase(), JSON.parse(JSON.stringify(item)))
            }
        }

        if (this.controlModelData.getPSDEDataViewDataItems?.length > 0) {
            for (let item of this.controlModelData.getPSDEDataViewDataItems) {
                this.$dataviewItemMap.set(item.name ? item.name.toLowerCase() : item.codeName.toLowerCase(), JSON.parse(JSON.stringify(item)))
            }
        }
    }

    /**
     * 根据name查找卡片视图项
     * 
     * @memberof IBizDataViewModel
     */
    public getCardViewItem(name: string) {
        return this.$cardViewItemMap.get(name);
    }

    /**
     * 根据name查找数据项
     * 
     * @memberof IBizDataViewModel
     */
    public getDataViewItem(name: string) {
        return this.$dataviewItemMap.get(name);
    }

    /**
     * 项布局面板
     *
     * @memberof IBizDataViewModel
     */
    get getItemPSLayoutPanel() {
        return this.controlModelData.getItemPSLayoutPanel;
    }

    /**
     * 获取导航视图
     *
     * @memberof IBizDataViewModel
     */    
    get navAppView(){
      return this.controlModelData.getNavPSAppView;
    }

    /**
     * 获取项绘制插件
     * 
     * @readonly
     * @memberof IBizDataViewModel
     */
    get getItemPSSysPFPlugin(){
        return this.controlModelData.getItemPSSysPFPlugin;
    }

}