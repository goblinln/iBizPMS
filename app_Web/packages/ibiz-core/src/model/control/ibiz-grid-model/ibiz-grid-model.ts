import { IBizMainControlModel } from '../ibiz-main-control-model';
import { AppServiceBase, DynamicService } from '../../../service';
import { IBizEntityModel } from '../../entity';
import { IBizEditorModel } from '../../common';
import { ViewFactory } from '../../../utils';

export class IBizGridModel extends IBizMainControlModel {

    /**
     * 表格列集合
     * 
     * @protected
     * @type {Map<string, any>}
     * @memberof IBizGridModel
     */
    protected $gridColumnMap: Map<string, any> = new Map();

    /**
     * 编辑列集合
     * 
     * @protected
     * @type {Map<string, any>}
     * @memberof IBizGridModel
     */
    protected $editColumnMap: Map<string, any> = new Map();

    /**
     * 数据列集合
     * 
     * @protected
     * @type {Map<string, any>}
     * @memberof IBizGridModel
     */
    protected $dataColumnMap: Map<string, any> = new Map();

    /**
     * 导出项集合
     * 
     * @type {any}
     * @memberof IBizGridModel
     */
    protected $dataExportModel: any = {};

    /**
     * 初始化IBizGrid对象
     * 
     * @param opts 额外参数
     * @memberof IBizGridModel
     */
    public constructor(opts: any = {}, viewRef?: any, parentRef?: any, runtimeData?: any) {
        super(opts, viewRef, parentRef, runtimeData);
        //  设置列集合
        this.initColumn();
    }

    /**
     * 是否开启分组
     * 
     * @memberof IBizGridModel
     */
    get enableGroup() {
        return this.controlModelData.enableGroup;
    }

    /**
     * 分组模式
     * 
     * @memberof IBizGridModel
     */
    get groupMode() {
        return this.controlModelData.groupMode;
    }

    /**
     * 分组属性
     * 
     * @memberof IBizGridModel
     */
    get groupField() {
        return this.controlModelData.getGroupPSDEField.codeName.toLowerCase();
    }

    //todo  待确认标识
    /**
     * 分组代码表
     * 
     * @memberof IBizGridModel
     */
    get groupCodeList() {
        return this.controlModelData.getGroupPSCodeList;
    }

    /**
     * 是否显示标题
     * 
     * @memberof IBizGridModel
     */
    get hideHeader() {
        return this.controlModelData.hideHeader;
    }

    /**
     * 适应屏幕宽度
     * 
     * @memberof IBizGridModel
     */
    get forceFit() {
        return this.controlModelData.forceFit;
    }

    /**
     * 分页条数
     * 
     * @memberof IBizGridModel
     */
    get limit() {
        return this.controlModelData.pagingSize;
    }

    /**
     * 聚合模式
     * 
     * @memberof IBizGridModel
     */
    get aggMode() {
        return this.controlModelData.aggMode;
    }

    /**
     * 聚合行为
     * 
     * @memberof IBizGridModel
     */
    get aggAction() {
        return this.controlModelData.getAggPSDEAction?.codeName;
    }

    /**
     * 排序模式
     * 
     * @memberof IBizGridModel
     */
    get sortMode() {
        return this.controlModelData.sortMode;
    }

    /**
     * 是否支持分页
     * 
     * @memberof IBizGridModel
     */
    get enablePagingBar() {
        return this.controlModelData.enablePagingBar;
    }

    /**
     * 是否支持行编辑
     * 
     * @memberof IBizGridModel
     */
    get enableRowEdit() {
        return this.controlModelData.enableRowEdit;
    }

    /**
     * 输出预置流程数据项
     * 
     * @memberof IBizGridModel
     */
    get hasWFDataItems() {
        return this.controlModelData.hasWFDataItems;
    }

    /**
     * 是否单选
     * 
     * @memberof IBizGridModel
     */
    get singleSelect() {
        return this.controlModelData.singleSelect;
    }

    /**
     * 启用列过滤器
     * 
     * @memberof IBizGridModel
     */
    get enableColFilter() {
        return this.controlModelData.enableColFilter;
    }

    /**
     * 支持行次序调整
     * 
     * @memberof IBizGridModel
     */
    get enableRowEditOrder() {
        return this.controlModelData.enableRowEditOrder;
    }

    /**
     * 支持行新建
     * 
     * @memberof IBizGridModel
     */
    get enableRowNew() {
        return this.controlModelData.enableRowNew;
    }

    /**
     * 列链接模式
     * 
     * @memberof IBizGridModel
     */
    get columnEnableLink() {
        return this.controlModelData.columnEnableLink;
    }

    /**
     * 是否禁用排序
     * 
     * @memberof IBizGridModel
     */
    get noSort() {
        return this.controlModelData.noSort;
    }

    /**
     * 只读模式
     * 
     * @memberof IBizGridModel
     */
    get readOnly() {
        return this.controlModelData.readOnly;
    }

    /**
     * 是否启用项权限
     * 
     * @memberof IBizGridModel
     */
    get enableItemPrivilege() {
        return this.controlModelData.enableItemPrivilege;
    }

    /**
     * 默认加载
     * 
     * @memberof IBizGridModel
     */
    get autoLoad() {
        return this.controlModelData.autoLoad;
    }

    /**
     * 显示处理提示
     * 
     * @memberof IBizGridModel
     */
    get showBusyIndicator() {
        return this.controlModelData.showBusyIndicator;
    }

    /**
     * 部件集合
     * 
     * @memberof IBizGridModel
     */
    get getPSControls() {
        return this.controlModelData.getPSControls;
    }

    /**
     * 数据导出对象
     * 
     * @memberof IBizGridModel
     */
    get getPSDEDataExport() {
        return this.controlModelData.getPSDEDataExport;
    }

    /**
     * 监控事件名称集合
     * 
     * @memberof IBizGridModel
     */
    get hookEventNames() {
        return this.controlModelData.hookEventNames;
    }

    /**
     * 主信息属性
     * 
     * @memberof IBizGridModel
     */
    get majorInfoColName() {
        return this.controlModelData.majotInfoColName;
    }

    /**
     * 获取所有表格列实例
     * 
     * @returns {Array<any>}
     * @memberof IBizGridModel
     */
    get allColumns(): Array<any> {
        return [...this.$gridColumnMap.values()];
    }

    /**
     * 获取所有编辑列实例
     * 
     * @returns {Array<any>}
     * @memberof IBizGridModel
     */
    get allEditColumns(): Array<any> {
        return [...this.$editColumnMap.values()];
    }

    /**
     * 所有数据项
     * 
     * @returns {Array<any>}
     * @memberof IBizGridModel
     */
    get allDataItems() {
        return [...this.$dataColumnMap.values()];
    }

    /**
     * 导航视图
     * 
     * @memberof IBizGridModel
     */
    get navAppView() {
        return this.controlModelData.getNavPSAppView;
    }

    /**
     * 表格行编辑项的所有值规则
     * 
     * @memberof IBizGridModel
     */
    get allGridEditItemVRs() {
        return this.controlModelData.getPSDEGridEditItemVRs;
    }

    /**
     * 获取表格更新项对象集合
     * 
     * @memberof IBizGridModel
     */
    get allGridEditItemUpdates(){
        return this.controlModelData.getPSDEGridEditItemUpdates;
    }

    /**
     * 初始化所有列集合
     * 
     * @memberof IBizGridModel
     */
    public initColumn() {
        if (this.controlModelData.getPSDEGridEditItems?.length > 0) {
            for (const item of this.controlModelData.getPSDEGridEditItems) {
                this.$editColumnMap.set(item.name ? item.name.toLowerCase() : item.codeName.toLowerCase(), JSON.parse(JSON.stringify(item)));
            }
        }

        if (this.controlModelData.getPSDEGridColumns?.length > 0) {
            for (const item of this.controlModelData.getPSDEGridColumns) {
                this.$gridColumnMap.set(item.dataItemName ? item.dataItemName.toLowerCase() : item.codeName.toLowerCase(), JSON.parse(JSON.stringify(item)));
            }
        }

        if (this.controlModelData.getPSDEGridDataItems?.length > 0) {
            for (const item of this.controlModelData.getPSDEGridDataItems) {
                this.$dataColumnMap.set(item.name ? item.name.toLowerCase() : item.codeName.toLowerCase(), JSON.parse(JSON.stringify(item)));
            }
        }
    }

    /**
     * 根据name查找表格列 
     * 
     * @param name 列名
     * @memberof IBizGridModel
     */
    public getColumnByName(name: string) {
        return this.$gridColumnMap.get(name);
    }

    /**
     * 根据name查找编辑列
     * 
     * @param name 列名
     * @memberof IBizGridModel
     */
    public getEditColumnByName(name: string) {
        return this.$editColumnMap.get(name);
    }

    /**
     * 根据name查找数据列
     * 
     * @param name 列名
     * @memberof IBizGridModel
     */
    public getDataColumnByName(name: string) {
        return this.$dataColumnMap.get(name);
    }

    /**
     * 获取代码表
     * 
     * @memberof IBizGridModel
     */
    public async loaded() {
        await super.loaded();
        if (this.enableGroup) {
            if (this.groupMode == "CODELIST" && this.groupCodeList?.path) {
                let targetAppCodeList = await DynamicService.getInstance(this.context).getAppCodeListJsonData(this.groupCodeList.path);
                Object.assign(this.groupCodeList, targetAppCodeList);
            }
            //TODO  分组属性代码表待确认
            if (this.groupField?.getPSCodeList?.path) {
                let targetAppCodeList = await DynamicService.getInstance(this.context).getAppCodeListJsonData(this.groupField.getPSCodeList.path);
                Object.assign(this.groupField, {
                    codeList: targetAppCodeList
                });
            }
        }
        //导航视图
        if (this.navAppView) {
            const targetNavAppView: any = await DynamicService.getInstance(this.context).getAppViewModelJsonData(this.navAppView.path);
            Object.assign(this.navAppView, targetNavAppView);
        }
        //列代码表
        if(this.allColumns?.length > 0) {
            for(let column of this.allColumns){
                if(column.getPSCodeList?.path) {
                    let targetAppCodeList = await DynamicService.getInstance(this.context).getAppCodeListJsonData(column.getPSCodeList.path);
                    Object.assign(column, { codeList: targetAppCodeList})
                }
            }
        }
        //编辑列实体
        if(this.allEditColumns?.length>0) {
            for(let column of this.allEditColumns) {
                if(column.getPSEditor) {
                    let editor: IBizEditorModel = new IBizEditorModel(column.getPSEditor, this.context);
                    await editor.loaded();
                    Object.assign(column, { editorInstance: editor });
                }
                
            }
        }
        //列链接视图
        if(this.allColumns?.length>0) {
            for(let column of this.allColumns) {
                if(column.enableLinkView && column.getLinkPSAppView?.modelref && column.getLinkPSAppView?.path) {
                    let targetLinkview: any = await DynamicService.getInstance(this.context).getAppViewModelJsonData(column.getLinkPSAppView.path);
                    let linkViewAppDataEntity: any;
                    if(targetLinkview.getPSAppDataEntity && targetLinkview.getPSAppDataEntity.path && targetLinkview.getPSAppDataEntity.modelref) {
                        let appDe: any = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(targetLinkview.getPSAppDataEntity.path);
                        linkViewAppDataEntity = new IBizEntityModel(appDe);
                    }
                    let viewMode: any = ViewFactory.getInstance(targetLinkview, this.context);
                    if (viewMode?.getPSAppDERSPaths) {
                        await viewMode.loadedAppDERSPathParam();
                    }
                    Object.assign(column, { $linkView: viewMode, linkViewEntity: linkViewAppDataEntity });
                    delete column.getLinkPSAppView.modelref;
                }
            }
        }
        //加载导出项代码表
        let dataExport: any = this.getDataExport();
        if(dataExport && dataExport.getPSDEDataExportItems?.length>0) {
            dataExport.getPSDEDataExportItems.forEach(async (item: any) => {
                if(item.getPSCodeList && item.getPSCodeList.path && item.getPSCodeList.modelref) {
                    const targetCodeList = await DynamicService.getInstance(this.context).getAppCodeListJsonData(item.getPSCodeList.path);
                    if(targetCodeList) {
                        Object.assign(item.getPSCodeList, targetCodeList);
                        delete item.getPSCodeList.modelref;
                    }
                }
            })
            this.$dataExportModel = dataExport;
        }
    }

    /**
     * 获取导出模型数据
     * 
     * @memberof IBizGridModel
     */
    public getDataExport() {
        if(this.getPSDEDataExport && this.getPSDEDataExport.path) {
            const path = this.getPSDEDataExport.path;
            const dataExport = this.appDataEntity.getAllPSAppDEDataExports.find((item: any) => {
                return item.dynaModelFilePath == path;
            })
            if(dataExport) {
                return dataExport;
            }
        }
    }

    /**
     * 获取导出模型数据
     * 
     * @readonly
     * @memberof IBizGridModel
     */
    get dataExport() {
        return this.$dataExportModel;
    }
}
