import { IBizMainControlModel } from '../ibiz-main-control-model';
import { DynamicService } from '../../../service';
import { ViewFactory } from '../../../utils';
/**
 * 多编辑视图面板
 */
export class IBizMEditViewPanelModel extends IBizMainControlModel {

    /**
     * 嵌入视图
     * 
     * @memberof IBizMEditViewPanelModel
     */
    private $embeddedPSAppView: any;

    /**
     * 嵌入视图实体
     * 
     * @memberof IBizMEditViewPanelModel
     */
    private $embeddedPSAppViewEntity: any;

    /**
     * 嵌入视图
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get embeddedPSAppView(){
        return this.$embeddedPSAppView;
    }

    /**
     * 嵌入视图实体
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get embeddedPSAppViewEntity(){
        return this.$embeddedPSAppViewEntity;
    }

    /**
     * 显示处理提示
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get showBusyIndicator() {
        return this.controlModelData.showBusyIndicator;
    }

    /**
     * 面板样式
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get panelStyle(){
        return this.controlModelData.panelStyle;
    }

    /**
     * 排序方向
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get minorSortDir(){
        return this.controlModelData.minorSortDir;
    }

    /**
     * 是否启用分组
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get enableGroup(){
        return this.controlModelData.enableGroup;
    }

    /**
     * 是否支持行编辑
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get enableRowEdit(){
        return this.controlModelData.enableRowEdit;
    }

    /**
     * 表格数据项集合
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get getPSDEGridDataItems(){
        return this.controlModelData.getPSDEGridDataItems;
    }

    /**
     * 是否禁用排序
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get noSort(){
        return this.controlModelData.noSort;
    }

    /**
     * 表格列集合
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get getPSDEGridColumns(){
        return this.controlModelData.getPSDEGridColumns;
    }

    /**
     * 是否支持分页栏
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get enablePagingBar(){
        return this.controlModelData.enablePagingBar;
    }

    /**
     * 隐藏头部
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get hideHeader(){
        return this.controlModelData.hideHeader;
    }

    /**
     * 适应屏幕宽度
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get forceFit(){
        return this.controlModelData.forceFit;
    }

    /**
     * 是否支持行次序调整
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get enableRowEditOrder(){
        return this.controlModelData.enableRowEditOrder;
    }

    /**
     * 表格编辑项集合
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get getPSDEGridEditItems(){
        return this.controlModelData.getPSDEGridEditItems;
    }

    /**
     * 列链接模式
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get columnEnableLink(){
        return this.controlModelData.columnEnableLink;
    }

    /**
     * 表格聚合模式
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get aggMode(){
        return this.controlModelData.aggMode;
    }

    /**
     * 分组模式
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get groupMode(){
        return this.controlModelData.groupMode;
    }

    /**
     * 排序模式
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get sortMode(){
        return this.controlModelData.sortMode;
    }

    /**
     * 分页大小
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get pagingSize(){
        return this.controlModelData.pagingSize;
    }

    /**
     * 附加排序应用实体属性
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get getMinorSortPSAppDEField(){
        return this.controlModelData.getMinorSortPSAppDEField;
    }

    /**
     * 是否输出预置流程数据项
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get hasWFDataItems(){
        return this.controlModelData.hasWFDataItems;
    }

    /**
     * 分组实体属性
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get getGroupPSDEField(){
        return this.controlModelData.getGroupPSDEField;
    }

    /**
     * 是否单项选择
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get singleSelect(){
        return this.controlModelData.singleSelect;
    }

    /**
     * 是否启用列过滤器
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get enableColFilter(){
        return this.controlModelData.enableColFilter;
    }

    /**
     * 是否支持行新建
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get enableRowNew(){
        return this.controlModelData.enableRowNew;
    }

    /**
     * 是否为只读模式
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get readOnly(){
        return this.controlModelData.readOnly;
    }

    /**
     * 嵌入视图
     * 
     * @memberof IBizMEditViewPanelModel
     */
    get getEmbeddedPSAppView(){
        return this.controlModelData.getEmbeddedPSAppView;
    }

    /**
     * 加载模型数据
     * 
     * @memberof IBizMEditViewPanelModel
     */
    public async loaded(){
        await super.loaded();
        if(this.getEmbeddedPSAppView && this.getEmbeddedPSAppView?.modelref){
            let appView: any = await DynamicService.getInstance(this.context).getAppViewModelJsonData(this.getEmbeddedPSAppView.path ? this.getEmbeddedPSAppView.path : this.getEmbeddedPSAppView.id);
            this.getEmbeddedPSAppView.modelref = false;
            Object.assign(this.getEmbeddedPSAppView, appView);
            let appViewModel: any = ViewFactory.getInstance(appView, this.context);
            if (appViewModel) {
                await appViewModel.loadedAppDERSPathParam();
                this.$embeddedPSAppView = appViewModel;
            }
        }
        if (this.$embeddedPSAppView?.appDataEntity?.modelref && this.$embeddedPSAppView?.appDataEntity?.path) {
            this.$embeddedPSAppViewEntity = await DynamicService.getInstance(this.context).getAppEntityModelJsonData( this.$embeddedPSAppView.appDataEntity.path);
        }
    }

    /**
     * 获取多编辑视图面板增加行为
     *
     * @memberof IBizMEditViewPanelModel
     */
    get loaddraftAction(){
        return this.appDataEntity.enableTempData ? 'GetDraftTemp': 'GetDraft';
    }
}