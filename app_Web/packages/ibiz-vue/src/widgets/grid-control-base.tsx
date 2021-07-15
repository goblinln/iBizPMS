import { Subject } from 'rxjs';
import { ViewTool, FormItemModel, Util, Verify, ModelTool, AppServiceBase, LogUtil, AppErrorCode, EntityFieldErrorCode, GridControlInterface } from 'ibiz-core';
import { MDControlBase } from './md-control-base';
import { AppGridService } from '../ctrl-service/app-grid-service';
import { AppViewLogicService } from 'ibiz-vue';
import { GlobalService } from 'ibiz-service';
import { IPSDEDataImport,DynamicInstanceConfig, IPSDEDataImportItem, IPSAppCodeList, IPSAppDataEntity, IPSAppDEField, IPSCodeList, IPSDEDataExport, IPSDEDataExportItem, IPSDEGrid, IPSDEGridColumn, IPSDEGridDataItem, IPSDEGridEditItem, IPSDEGridFieldColumn, IPSDEGridUAColumn, IPSDEUIAction, IPSDEUIActionGroup, IPSUIAction, IPSUIActionGroupDetail, IPSDEGridGroupColumn, IPSDEGridEditItemUpdate, IPSAppDEDataSet, IPSDEFValueRule } from '@ibiz/dynamic-model-api';

/**
 * 表格部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {MDControlBase}
 */
export class GridControlBase extends MDControlBase implements GridControlInterface {

    /**
     * 表格实例
     * 
     * @type {IBizGridModel}
     * @memberof GridControlBase
     */
    public controlInstance!: IPSDEGrid;

    /**
     * 是否默认保存
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public autosave?: any;

    /**
     * 总条数
     *
     * @type {number}
     * @memberof GridControlBase
     */
    public totalrow: number = 0;

    /**
     * 部件行为--submit
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public WFSubmitAction?: any;

    /**
     * 部件行为--start
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public WFStartAction?: any;

    /**
     * 主信息表格列
     *
     * @type {string}
     * @memberof GridControlBase
     */
    public majorInfoColName: string = '';

    /**
     * 是否单选
     *
     * @type {boolean}
     * @memberof GridControlBase
     */
    public isSingleSelect?: boolean;

    /**
     * 是否默认选中第一条数据
     *
     * @type {boolean}
     * @memberof GridControlBase
     */
    public isSelectFirstDefault: boolean = false;

    /**
     * 选中数据字符串
     *
     * @type {string}
     * @memberof GridControlBase
     */
    public selectedData?: string;

    /**
     * 表格数据
     * 
     * @type {Array<any>}
     * @memberof GridControlBase
     */
    public items: Array<any> = [];

    /**
     * 表格行数据默认激活模式
     * 0 不激活
     * 1 单击激活
     * 2 双击激活
     *
     * @type {(number | 0 | 1 | 2)}
     * @memberof GridControlBase
     */
    public gridRowActiveMode: number | 0 | 1 | 2 = 2;

    /**
     * 是否开启行编辑
     *
     * @type {boolean}
     * @memberof GridControlBase
     */
    public isOpenEdit: boolean = false;

    /**
     * 新建行状态
     * 
     * @type {boolean}
     * @memberof GridControlBase
     */
    public newRowState: boolean = true;

    /**
     * 实际是否开启行编辑
     *
     * @type {boolean}
     * @memberof GridControlBase
     */
    public actualIsOpenEdit: boolean = this.isOpenEdit;

    /**
     * 表格更新默认值项
     *
     * @type {Array<any>}
     * @memberof GridControlBase
     */
    public defaultUpdateItems: Array<any> = [];

    /**
     * 行编辑值校验错误信息
     *
     * @type {Array<any>}
     * @memberof GridControlBase
     */
    public errorMessages: Array<any> = [];

    /**
     * 选项框列宽
     *
     * @type {number}
     * @memberof GridControlBase
     */
    public checkboxColWidth: number = 40;

    /**
     * 拦截行选中
     *
     * @type {boolean}
     * @memberof GridControlBase
     */
    public stopRowClick: boolean = false;

    /**
     * 当前编辑行数据
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public curEditRowData: any = {};

    /**
     * 是否允许拖动列宽
     *
     * @type {boolean}
     * @memberof GridControlBase
     */
    public isDragendCol: boolean = true;

    /**
     * 所有列成员
     *
     * @type {any[]}
     * @memberof GridControlBase
     */
    public allColumns: any[] = [];

    /**
     * 所有导出列成员
     *
     * @type {any[]}
     * @memberof GridControlBase
     */
    public allExportColumns: any[] = [];

    /**
     * 所有列实例对象
     * 
     * @type {Array<any>}
     * @memberof GridControlBase
     */
    public allColumnsInstance: Array<IPSDEGridColumn> = [];

    /**
     * 表格模型集合
     *
     * @type {any[]}
     * @memberof GridControlBase
     */
    public gridItemsModel: any[] = [];

    /**
     * 主键列名
     * 
     * @memberof GridControlBase
     */
    public columnKeyName: string = "";

    /**
     * 表格是否自适应宽度
     * 
     * @memberof GridControlBase
     */
    public renderEmptyColumn: boolean = false;

    /**
     * 值规则集合
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public rules: any = {};

    /**
     * 表格引用名称
     * 
     * @type 
     * @memberof GridControlBase
     */
    public gridRefName: string = "";

    /**
     * 聚合模式
     *
     * @type {any[]}
     * @memberof GridControlBase
     */
    public aggMode: any;

    /**
     * 表格聚合行为
     *
     * @type {string}
     * @memberof GridControlBase
     */
    public aggAction?: string;

    /**
     * 远程数据
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public remoteData: any = {};

    /**
     * 表格是否显示
     *
     * @type {boolean}
     * @memberof GridControlBase
     */
    public isDisplay: boolean = true;

    /**
     * 是否启用分组
     *
     * @type {boolean}
     * @memberof GridControlBase
     */
    public isEnableGroup: boolean = false;

    /**
     * 分组属性
     *
     * @type {string}
     * @memberof GridControlBase
     */
    public groupAppField: string = "";

    /**
     * 分组属性代码表标识
     *
     * @type {string}
     * @memberof GridControlBase
     */
    public groupAppFieldCodelistTag: string = "";

    /**
     * 分组属性代码表类型
     * 
     * @type {string}
     * @memberof GridControlBase
     */
    public groupAppFieldCodelistType: string = "";

    /**
     * 分组属性代码表
     * 
     * @type {*}
     * @memberof GridControlBase
     */
    public groupAppFieldCodelist: any;

    /**
     * 分组模式
     *
     * @type {string}
     * @memberof GridControlBase
     */
    public groupMode: string = "";

    /**
     * 分组代码表标识
     * 
     * @type {string}
     * @memberof GridControlBase
     */
    public codelistTag: string = "";

    /**
     * 分组代码表
     * 
     * @type {*}
     * @memberof GridControlBase
     */
    public codelist: any;

    /**
     * 分组代码表类型
     * 
     * @type {string}
     * @memberof GridControlBase
     */
    public codelistType: string = "";

    /**
     * 是否隐藏标题
     * 
     * @type {boolean}
     * @memberof GridControlBase
     */
    public isHideHeader: boolean = false;


    /**
     * 导入模型
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public importDataModel: any = {};

    /**
     * 获取表格行模型
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public getGridRowModel() {
        let tempModel: any = {};
        const editItems: Array<any> = this.controlInstance.getPSDEGridEditItems() || [];
        if (editItems.length > 0) {
            editItems.forEach((item: any) => {
                tempModel[item.name] = new FormItemModel();
            });
        }
        return tempModel;
    }

    /**
     * 获取选中行胡数据
     *
     * @returns {any[]}
     * @memberof GridControlBase
     */
    public getSelection(): any[] {
        return this.selections;
    }

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public deRules() {
        return {}
    }

    /**
     * 初始化值规则
     *
     * @memberof EditFormControlBase
     */
    public initRules() {
        // 先初始化系统值规则和属性值规则
        let staticRules: any = {};
        const allGridEditItemVRs = this.controlInstance.getPSDEGridEditItemVRs() || [];
        if (allGridEditItemVRs.length > 0) {
            allGridEditItemVRs.forEach((item: any) => {
                const { checkMode, valueRuleType, getPSSysValueRule: sysRule } = item;
                const deRule: IPSDEFValueRule | null = item.getPSDEFValueRule();
                const gridEditItemName = item.getPSDEGridEditItemName();
                if (!staticRules[gridEditItemName]) {
                    staticRules[gridEditItemName] = [];
                }
                // 排除后台检查的值规则
                if (checkMode == 2) {
                    return
                }
                // 系统值规则
                if (valueRuleType == 'SYSVALUERULE' && sysRule) {
                    // 正则值规则
                    if (sysRule.ruleType == 'REG') {
                        staticRules[gridEditItemName].push({
                            pattern: new RegExp(sysRule.regExCode),
                            message: sysRule.ruleInfo,
                            trigger: ['change', 'blur']
                        })
                        // 脚本值规则
                    } else if (sysRule.ruleType == 'SCRIPT') {
                        staticRules[gridEditItemName].push({
                            validator: (rule: any, value: any, callback: any, source: any) => {
                                // 空值时不校验
                                if (Util.isEmpty(source[gridEditItemName])) {
                                    return true
                                }
                                try {
                                    eval(sysRule.scriptCode);
                                } catch (error) {
                                    this.$throw(error, 'initRules');
                                }
                                return true;
                            },
                            trigger: ['change', 'blur']
                        })
                    }
                    // 属性值规则
                } else if (valueRuleType == 'DEFVALUERULE' && deRule) {
                    // 有值项的情况，校验值项的值
                    let editItem: IPSDEGridEditItem = (this.controlInstance.getPSDEGridEditItems() || []).find((item: IPSDEGridEditItem) => { return item.M.getPSDEGridEditItemName === gridEditItemName; }) as IPSDEGridEditItem;
                    let valueName = editItem && editItem.valueItemName ? editItem.valueItemName : gridEditItemName;
                    staticRules[gridEditItemName].push({
                        validator: (rule: any, value: any, callback: any, source: any) => {
                            // 空值时不校验
                            if (Util.isEmpty(source[valueName])) {
                                return true
                            }
                            const { isPast, infoMessage } = Verify.verifyDeRules(valueName, source, deRule?.getPSDEFVRGroupCondition());
                            if (!isPast) {
                                callback(new Error(infoMessage || deRule.ruleInfo));
                            }
                            return true;
                        },
                        trigger: ['change', 'blur']
                    })
                }
            })
        }

        // 初始化非空值规则和数据类型值规则
        this.rules = {};
        const appDataEntity = this.controlInstance.getPSAppDataEntity();
        const allEditColumns: Array<IPSDEGridEditItem> = this.controlInstance.getPSDEGridEditItems() || [];
        if (appDataEntity && allEditColumns?.length > 0) {
            for (const editColumn of allEditColumns) {
                let editorRules = [];
                if (editColumn) {
                    editorRules = Verify.buildVerConditions(editColumn);
                }
                let otherRules = staticRules[editColumn.name] || [];
                this.rules[editColumn.name] = [
                    // 非空值规则
                    { validator: (rule: any, value: any, callback: any) => { return (!editColumn.allowEmpty && (value === null || value === undefined || value === "")) ? false : true }, message: `${editColumn.caption || editColumn.name} 必须填写`, trigger: ['change', 'blur'] },
                    // 表格值规则
                    ...otherRules,
                    // 编辑器基础值规则
                    ...editorRules
                ]
            }
        }
    }

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof GridControlBase
     */
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        super.onDynamicPropsChange(newVal, oldVal);
        if (newVal?.selectedData && newVal.selectedData != oldVal?.selectedData) {
            this.selectedData = newVal.selectedData;
            this.onSelectedDataValueChange(newVal.selectedData);
        }
    }

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof GridControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isOpenEdit = newVal.isOpenEdit;
        this.actualIsOpenEdit = this.isOpenEdit;
        this.gridRowActiveMode = newVal.gridRowActiveMode ? newVal.gridRowActiveMode : 2;
        this.isSelectFirstDefault = newVal.isSelectFirstDefault;
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 初始化表格模型
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public async ctrlModelInit() {
        await super.ctrlModelInit();
        if (!(this.Environment && this.Environment.isPreviewMode)) {
            this.service = new AppGridService(this.controlInstance, this.context);
        }
        this.initGridBasicData();
        this.initAllColumns();
        this.initColumnKeyName();
        this.initAllExportColumns();
        this.initRules();
        this.initImportDataModel();
    }

    /**
     * 表格部件初始化
     *
     * @memberof GridControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        this.setColState();
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if (Object.is('load', action)) {
                    this.load(data);
                }
                if (Object.is('remove', action)) {
                    this.remove(data);
                }
                if (Object.is('save', action)) {
                    this.save(data);
                }
            });
        }
    }

    /**
     * 初始化表格基础数据
     *
     * @memberof GridControlBase
     */
    public initGridBasicData() {
        this.isSingleSelect = this.staticProps.isSingleSelect === true ? true : this.staticProps.isSingleSelect === false ? false : this.controlInstance.singleSelect;
        this.autosave = this.controlInstance.autoLoad;
        this.limit = this.controlInstance.pagingSize ? this.controlInstance.pagingSize : 20;
        this.isNoSort = this.controlInstance.noSort;
        this.minorSortDir = this.controlInstance.minorSortDir;
        this.minorSortPSDEF = (this.controlInstance.getMinorSortPSAppDEField() as IPSAppDEField)?.codeName?.toLowerCase();
        this.gridRefName = `${this.name.toLowerCase()}grid`;
        this.aggMode = this.controlInstance.aggMode;
        this.allColumnsInstance = this.controlInstance.getPSDEGridColumns() || [];
        this.isEnableGroup = (this.controlInstance.enableGroup && this.controlInstance.getGroupPSAppDEField()) ? true : false;
        //开启分组
        if (this.isEnableGroup) {
            this.groupMode = this.controlInstance.groupMode;
            const groupField: IPSAppDEField = this.controlInstance.getGroupPSAppDEField() as IPSAppDEField;
            if (groupField) {
                this.groupAppField = groupField.codeName?.toLowerCase();
            }
            const groupCodeList: IPSAppCodeList = this.controlInstance.getGroupPSCodeList() as IPSAppCodeList;
            if (groupCodeList && groupCodeList.codeName) {
                this.codelistTag = groupCodeList.codeName;
                this.codelistType = groupCodeList.codeListType || 'STATIC';
                this.codelist = groupCodeList;
            }
        }
    }

    /**
     * 获取所有列成员模型
     *
     * @memberof GridControlBase
     */
    public initAllColumns() {
        this.allColumns = [];
        const init = (columns: IPSDEGridColumn[]) => {
            if (columns && columns.length > 0) {
                for (const columnInstance of columns) {
                    if (columnInstance.columnType == 'GROUPGRIDCOLUMN') {
                        init((columnInstance as IPSDEGridGroupColumn).getPSDEGridColumns() || []);
                    }
                    let editItem: IPSDEGridEditItem = ModelTool.getGridItemByCodeName(columnInstance.codeName, this.controlInstance) as IPSDEGridEditItem;
                    //表格列
                    const column = {
                        name: columnInstance.name.toLowerCase(),
                        label: this.$tl(columnInstance.getCapPSLanguageRes()?.lanResTag, columnInstance.caption),
                        langtag: columnInstance.getCapPSLanguageRes()?.lanResTag,
                        show: !columnInstance.hideDefault,
                        unit: columnInstance.widthUnit,
                        isEnableRowEdit: columnInstance.enableRowEdit,
                        enableCond: editItem?.enableCond ? editItem?.enableCond : 3,
                        columnType: columnInstance.columnType
                    };
                    this.allColumns.push(column);
                }
            }
        }
        let columnsInstanceArr: Array<IPSDEGridColumn> = this.controlInstance.getPSDEGridColumns() || [];
        init(columnsInstanceArr);
    }

    /**
     * 初始化主键属性列名
     * 
     * @memberof GridControlBase
     */
    public initColumnKeyName() {
        this.columnKeyName = "";
        const dataItems: Array<IPSDEGridDataItem> = this.controlInstance.getPSDEGridDataItems() || [];
        if (dataItems.length > 0) {
            const dataItem: any = dataItems.find((data: any) => {
                return Object.is('srfkey', data.name)
            })
            if (dataItem && this.allColumnsInstance?.length > 0) {
                const srfKeyColumn: any = this.allColumnsInstance.find((columnInstance: any) => {
                    return Object.is(columnInstance.columnType, 'DEFGRIDCOLUMN') && Object.is(columnInstance.getPSAppDEField().codeName, dataItem.getPSAppDEField().codeName);
                })
                if (srfKeyColumn) {
                    this.columnKeyName = srfKeyColumn.name.toLowerCase();
                }
            }
        }
    }

    /**
     * 获取所有导出列成员模型
     *
     * @param {IBizGridModel} gridInstance 表格部件实例对象
     */
    public initAllExportColumns() {
        this.allExportColumns = [];
        const exportModel: IPSDEDataExport = this.controlInstance.getPSDEDataExport() as IPSDEDataExport;
        if (exportModel != null) {
            const items: Array<IPSDEDataExportItem> = exportModel.getPSDEDataExportItems() || [];
            if (items.length > 0) {
                items.forEach((item: any) => {
                    this.allExportColumns.push({
                        name: item.name.toLowerCase(),
                        label: this.$tl(item.getCapPSLanguageRes()?.lanResTag, item.caption),
                        langtag: item.getCapPSLanguageRes()?.lanResTag,
                        show: true
                    })
                })
            }
        }
    }

    /**
     * 初始化导入模型
     * 
     * 
     * @memberof GridControlBase
     */
    public initImportDataModel() {
        this.importDataModel = {};
        const importData: IPSDEDataImport = this.controlInstance.getPSDEDataImport() as IPSDEDataImport;
        if (importData) {
            const appDataEntity: IPSAppDataEntity = this.controlInstance.getPSAppDataEntity() as IPSAppDataEntity;
            const importDataModel: any = {
                importId: importData.codeName,
                serviceName: appDataEntity.codeName,
                appDeLogicName: appDataEntity.logicName,
                importData: {}
            }
            const items: Array<IPSDEDataImportItem> = importData.getPSDEDataImportItems() || [];
            if (items.length > 0) {
                items.forEach((item: any) => {
                    const importItem: any = {
                        headername: this.$tl(item.getCapPSLanguageRes()?.lanResTag, item.caption),
                        isuniqueitem: item.uniqueItem,
                    }
                    const codelist: IPSAppCodeList = item.getPSCodeList() as IPSAppCodeList;
                    if (codelist) {
                        Object.assign(importItem, {
                            codelist: {
                                type: codelist.codeListType,
                                tag: codelist.codeName,
                                isnumber: codelist.codeItemValueNumber,
                            }
                        })
                    }
                    const appDeField: IPSAppDEField = item.getPSAppDEField() as IPSAppDEField;
                    if (appDeField) {
                        Object.assign(importItem, {
                            name: appDeField.codeName.toLowerCase(),
                        })
                    }
                    Object.assign(importDataModel.importData, {
                        [item.name]: importItem,
                    })
                })
            }
            this.importDataModel = importDataModel;
        }
    }

    /**
     * 初始化界面行为模型
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public initCtrlActionModel() {
        const allColumns: Array<IPSDEGridColumn> = this.controlInstance.getPSDEGridColumns() || [];
        if (allColumns.length === 0) {
            return;
        }
        let UAGridColumn: IPSDEGridUAColumn = allColumns.find((item: any) => {
            return item.columnType === "UAGRIDCOLUMN";
        }) as IPSDEGridUAColumn;
        if (UAGridColumn) {
            const UIActionGroupDetails: IPSUIActionGroupDetail[] = (UAGridColumn.getPSDEUIActionGroup() as IPSDEUIActionGroup).getPSUIActionGroupDetails() || [];
            if (UIActionGroupDetails.length > 0) {
                UIActionGroupDetails.forEach((detail: IPSUIActionGroupDetail) => {
                    const uiAction: IPSUIAction | null = detail.getPSUIAction();
                    if (uiAction) {
                        const appUIAction: any = Util.deepCopy(uiAction) as IPSDEUIAction;
                        this.actionModel[uiAction.uIActionTag] = Object.assign(appUIAction?._data ? appUIAction._data : {}, { disabled: false, visabled: true, getNoPrivDisplayMode: appUIAction._data.noPrivDisplayMode ? appUIAction._data.noPrivDisplayMode : 6 });
                    }
                })
            }
        }

        // 具有内置界面行为的数据表格列
        let UIActionColumn: IPSDEGridFieldColumn = allColumns.find((item: IPSDEGridColumn) => {
            return item.columnType === "DEFGRIDCOLUMN" && (item as IPSDEGridFieldColumn).getPSDEUIAction();
        }) as IPSDEGridFieldColumn;
        const uiAction = UIActionColumn?.getPSDEUIAction();
        if (uiAction) {
            const appUIAction: any = Util.deepCopy(uiAction) as IPSDEUIAction;
            this.actionModel[uiAction.uIActionTag] = Object.assign(appUIAction?._data ? appUIAction._data : {}, { disabled: false, visabled: true, getNoPrivDisplayMode: appUIAction._data.noPrivDisplayMode ? appUIAction._data.noPrivDisplayMode : 6 });
        }
    }

    /**
     * selectedData选中值变化
     *
     * @param {*} newVal
     * @memberof TreeControlBase
     */
    public onSelectedDataValueChange(newVal: any) {
        this.selections = [];
        if (this.selectedData) {
            const refs: any = this.$refs;
            if (refs[this.gridRefName]) {
                refs[this.gridRefName].clearSelection();
                JSON.parse(this.selectedData).forEach((selection: any) => {
                    let selectedItem = this.items.find((item: any) => {
                        return Object.is(item.srfkey, selection.srfkey);
                    });
                    if (selectedItem) {
                        // this.rowClick(selectedItem);
                    }
                });
            }
        }
    }

    /**
     * 表格模型数据加载
     *
     * @memberof GridControlBase
     */
    public async ctrlModelLoad() {
        await super.ctrlModelLoad();
        const appDataEntity = this.controlInstance.getPSAppDataEntity();
        if (appDataEntity) {
            await appDataEntity.fill();
        }
        const editItems = this.controlInstance.getPSDEGridEditItems();
        if (editItems && editItems.length > 0) {
            for (const editItem of editItems) {
                await editItem.fill();
            }
        }
        const gridColumns = this.controlInstance?.getPSDEGridColumns?.();
        if (gridColumns && gridColumns.length > 0) {
            for (const gridColumn of gridColumns) {
                await (gridColumn as IPSDEGridFieldColumn)?.getPSAppCodeList?.()?.fill?.();
                if (gridColumn.columnType == "DEFGRIDCOLUMN") {
                    if ((gridColumn as IPSDEGridFieldColumn)?.getLinkPSAppView && (gridColumn as IPSDEGridFieldColumn)?.getLinkPSAppView()) {
                        await (gridColumn as IPSDEGridFieldColumn)?.getLinkPSAppView()?.fill?.();
                        await (gridColumn as IPSDEGridFieldColumn)?.getLinkPSAppView()?.getPSAppDataEntity?.()?.fill();
                    }
                }
            }
        }
    }

    /**
     * 获取界面行为权限状态
     *
     * @memberof GridControlBase
     */
    public getActionState(data: any) {
        let tempActionModel: any = Util.deepCopy(this.actionModel);
        let targetData: any = this.transformData(data);
        ViewTool.calcActionItemAuthState(targetData, tempActionModel, this.appUIService);
        return tempActionModel;
    }

    /**
     * 表格行编辑项校验
     *
     * @param {string} name 属性名
     * @param {*} data 行数据
     * @param {number} rowIndex 行索引
     * @returns Promise<any>
     * 
     * @memberof GridControlBase
     */
    public validate(name: string, data: any, rowIndex: number): Promise<any> {
        return new Promise((resolve, reject) => {
            Util.validateItem(name, data, this.rules).then(() => {
                this.gridItemsModel[rowIndex][name].setError(null);
                resolve(true);
            }).catch(({ errors, fields }: any) => {
                this.gridItemsModel[rowIndex][name].setError(errors?.[0].message);
                resolve(false);
            });
        });
    }

    /**
     * 校验所有修改过的编辑项
     *
     * @returns Promise<any>
     * @memberof GridControlBase
     */
    public async validateAll() {
        this.errorMessages = [];
        let validateState = true;
        let index = -1;
        for (let item of this.items) {
            let tempMessage: string = '';
            index++;
            if (item.rowDataState === "create" || item.rowDataState === "update") {
                for (let property of Object.keys(this.rules)) {
                    if (!await this.validate(property, item, index)) {
                        validateState = false;
                        tempMessage = tempMessage + '<p>' + this.gridItemsModel[index][property].error + '<p>';
                    }
                }
            }
            this.errorMessages.push(tempMessage);
        }
        return validateState;
    }

    /**
     * 表格数据加载
     *
     * @param {*} [opt] 参数
     * @param {boolean} [pageReset] 页码是否重置
     * @memberof GridControlBase
     */
    public load(opt: any = {}, pageReset: boolean = false): void {
        if (!this.fetchAction) {
            this.$throw(`${this.controlInstance.codeName}` + (this.$t('app.grid.notconfig.fetchaction') as string), 'load');
            return;
        }
        if (pageReset) {
            this.curPage = 1;
        }
        const arg: any = { ...opt };
        const page: any = {};
        if (this.isEnablePagingBar) {
            Object.assign(page, { page: this.curPage - 1, size: this.limit });
        }
        // 设置排序
        if (!this.isNoSort
            && (this.minorSortDir !== null && this.minorSortDir !== undefined && this.minorSortDir !== '')
            && (this.minorSortPSDEF !== null && this.minorSortPSDEF !== undefined && this.minorSortPSDEF !== '')) {
            const sort: string = this.minorSortPSDEF + "," + this.minorSortDir;
            Object.assign(page, { sort: sort });
        }
        Object.assign(arg, page);
        const parentdata: any = {};
        this.ctrlEvent({ controlname: this.name, action: "beforeload", data: parentdata });
        Object.assign(arg, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
        Object.assign(tempViewParams, Util.deepCopy(this.viewparams));
        // 多实例查询数据处理
        let appEnvironment = AppServiceBase.getInstance().getAppEnvironment();
        if (appEnvironment.bDynamic) {
            if (tempViewParams.hasOwnProperty("srfdynainstid")) {
                let dynainstParam: DynamicInstanceConfig = this.modelService?.getDynaInsConfig();
                Object.assign(tempViewParams, { srfinsttag: dynainstParam.instTag, srfinsttag2: dynainstParam.instTag2 });
                delete tempViewParams.srfdynainstid;
            } else {
                if (!tempViewParams.hasOwnProperty("srfinsttag")) {
                    Object.assign(tempViewParams, { srfinsttag: "__srfstdinst__" });
                }
            }
        } else {
            if (tempViewParams.hasOwnProperty("srfwf")) {
                Object.assign(tempViewParams, { srfinsttag: "__srfstdinst__" });
            }
        }
        Object.assign(arg, { viewparams: tempViewParams });
        let tempContext: any = Util.deepCopy(this.context);
        this.onControlRequset('load', tempContext, arg);
        const post: Promise<any> = this.service.search(this.fetchAction, tempContext, arg, this.showBusyIndicator);
        post.then((response: any) => {
            this.onControlResponse('load', response);
            if (!response.status || response.status !== 200) {
                this.$throw(response, 'load');
                return;
            }
            const data: any = response.data;
            this.totalRecord = response.total;
            this.items = Util.deepCopy(data);
            // 清空selections,gridItemsModel
            this.selections = [];
            this.gridItemsModel = [];
            this.items.forEach(() => { this.gridItemsModel.push(this.getGridRowModel()) });
            this.items.forEach((item: any) => {
                Object.assign(item, this.getActionState(item));
            });
            this.ctrlEvent({ controlname: this.name, action: "load", data: this.items });
            // 设置默认选中
            let _this = this;
            setTimeout(() => {
                //在导航视图中，如已有选中数据，则右侧展开已选中数据的视图，如无选中数据则默认选中第一条
                if (_this.isSelectFirstDefault) {
                    if (_this.selections && _this.selections.length > 0) {
                        _this.selections.forEach((select: any) => {
                            const index = _this.items.findIndex((item: any) => Object.is(item.srfkey, select.srfkey));
                            if (index != -1) {
                                _this.rowClick(_this.items[index]);
                            }
                        })
                    } else {
                        if (_this.items.length == 0) {
                            let models: Array<any> = this.service.getMode().getDataItems();
                            if (models?.length > 0) {
                                let emptyItem: any = {};
                                models.forEach((model: any) => {
                                    emptyItem[model.name] = null;
                                });
                                this.ctrlEvent({ controlname: _this.name, action: "selectionchange", data: [emptyItem] });
                            }
                        } else {
                            _this.rowClick(this.items[0]);
                        }
                    }
                }
                if (_this.selectedData) {
                    const table: any = (this.$refs as any)[this.gridRefName];
                    if (table) {
                        table.clearSelection();
                        JSON.parse(_this.selectedData).forEach((selection: any) => {
                            let selectedItem = _this.items.find((item: any) => {
                                return Object.is(item.srfkey, selection.srfkey);
                            });
                            if (selectedItem) {
                                _this.rowClick(selectedItem);
                            }
                        });
                    }
                }
            }, 300);
            if (this.aggMode && Object.is(this.aggMode, "ALL")) {
                this.getAggData(tempContext, tempViewParams);
            }
            if (this.isEnableGroup) {
                this.group();
            }
        }).catch((response: any) => {
            this.onControlResponse('load', response);
            this.$throw(response, 'load');
        });
    }

    /**
     * 删除
     *
     * @param {any[]} datas 删除数据
     * @memberof GridControlBase
     */
    public async remove(datas: any[]): Promise<any> {
        if (!this.removeAction) {
            this.$throw(`${this.controlInstance.codeName}` + (this.$t('app.grid.notconfig.removeaction') as string), 'remove');
            return;
        }
        let _datas: any[] = [];
        datas.forEach((record: any, index: number) => {
            if (Object.is(record.srfuf, "0")) {
                this.items.some((val: any, num: number) => {
                    if (JSON.stringify(val) == JSON.stringify(record)) {
                        this.items.splice(num, 1);
                        this.gridItemsModel.splice(num, 1);
                        return true;
                    }
                });
            } else {
                _datas.push(datas[index]);
            }
        });
        if (_datas.length === 0) {
            return;
        }
        let dataInfo = '';
        _datas.forEach((record: any, index: number) => {
            let srfmajortext = record[this.appDeMajorFieldName.toLowerCase()];
            if (index < 5) {
                if (!Object.is(dataInfo, '')) {
                    dataInfo += '、';
                }
                dataInfo += srfmajortext;
            } else {
                return false;
            }
        });

        if (_datas.length < 5) {
            dataInfo = dataInfo + ' ' + (this.$t('app.grid.totle') as string) + _datas.length + (this.$t('app.grid.records') as string) + (this.$t('app.grid.data') as string);
        } else {
            dataInfo = ' ... ' + (this.$t('app.grid.totle') as string) + _datas.length + (this.$t('app.grid.records') as string) + (this.$t('app.grid.data') as string);
        }

        const removeData = () => {
            let keys: any[] = [];
            _datas.forEach((data: any) => {
                keys.push(data.srfkey);
            });
            let _removeAction = keys.length > 1 ? 'removeBatch' : this.removeAction;
            let _keys = keys.length > 1 ? keys : keys[0];
            let tempContext: any = Util.deepCopy(this.context);
            Object.assign(tempContext, { [this.appDeCodeName.toLowerCase()]: _keys });
            const arg = { [this.appDeCodeName.toLowerCase()]: _keys };
            Object.assign(arg, { viewparams: this.viewparams });
            this.onControlRequset('remove', tempContext, arg);
            const post: Promise<any> = this.service.delete(_removeAction, tempContext, arg, this.showBusyIndicator);
            return new Promise((resolve: any, reject: any) => {
                post.then((response: any) => {
                    this.onControlResponse('remove', response);
                    if (!response || response.status !== 200) {
                        this.$throw((this.$t('app.grid.deldatafail') as string) + ',' + response.info, 'remove');
                        return;
                    } else {
                        this.$success((this.$t('app.grid.delsuccess') as string), 'remove');
                    }
                    //删除items中已删除的项
                    _datas.forEach((data: any) => {
                        this.items.some((item: any, index: number) => {
                            if (Object.is(item.srfkey, data.srfkey)) {
                                this.items.splice(index, 1);
                                this.gridItemsModel.splice(index, 1);
                                return true;
                            }
                        });
                    });
                    this.totalRecord -= _datas.length;
                    this.ctrlEvent({ controlname: this.name, action: "remove", data: {} });
                    this.selections = [];
                    resolve(response);
                }).catch((response: any) => {
                    this.onControlResponse('remove', response);
                    this.$throw(response, 'remove');
                    reject(response);
                });
            });
        }

        dataInfo = dataInfo.replace(/[null]/g, '').replace(/[undefined]/g, '');
        this.$Modal.confirm({
            title: (this.$t('app.commonwords.warning') as string),
            content: (this.$t('app.grid.confirmdel') as string) + ' ' + dataInfo + '，' + (this.$t('app.grid.notrecoverable') as string),
            onOk: () => {
                removeData();
            },
            onCancel: () => { }
        });
        return removeData;
    }

    /**
     * 保存
     *
     * @param {*} args 额外参数
     * @memberof GridControlBase
     */
    public async save(args: any) {
        let _this = this;
        // 拷贝模式
        if (_this.viewparams && _this.viewparams.copymode && Object.is(_this.viewparams.copymode, 'true') && _this.items && _this.items.length > 0) {
            for (const item of _this.items) {
                item.rowDataState = 'create';
            }
        }
        if (_this.items && _this.items.length > 0) {
            for (const item of _this.items) {
                if (Object.is(item.rowDataState, 'update')) {
                    _this.updateDefault(item);
                }
            }
        }
        if (!await this.validateAll()) {
            if (this.errorMessages && this.errorMessages.length > 0) {
                this.$throw(this.errorMessages[0], 'save');
            } else {
                this.$throw((this.$t('app.commonwords.rulesexception') as string), 'save');
            }
            return [];
        }
        let successItems: any = [];
        let errorItems: any = [];
        let errorMessage: any = [];
        const appDeCodeName: any = this.controlInstance.getPSAppDataEntity()?.codeName;
        for (const item of _this.items) {
            try {
                if (Object.is(item.rowDataState, 'create')) {
                    if (!this.createAction) {
                        this.$throw(`${this.controlInstance.codeName}` + (this.$t('app.grid.notconfig.createaction') as string), 'save');
                    } else {
                        Object.assign(item, { viewparams: this.viewparams });
                        let tempContext: any = Util.deepCopy(this.context);
                        this.onControlRequset('create', tempContext, item);
                        let response = await this.service.add(this.createAction, tempContext, item, this.showBusyIndicator);
                        this.onControlResponse('create', response);
                        successItems.push(Util.deepCopy(response.data));
                    }
                } else if (Object.is(item.rowDataState, 'update')) {
                    if (!this.updateAction) {
                        this.$throw(`${this.controlInstance.codeName}` + (this.$t('app.grid.notconfig.updateaction') as string), 'save');
                    } else {
                        Object.assign(item, { viewparams: this.viewparams });
                        if (item[appDeCodeName?.toLowerCase()]) {
                            Object.assign(this.context, { [appDeCodeName?.toLowerCase()]: item[appDeCodeName?.toLowerCase()] });
                        }
                        let tempContext: any = Util.deepCopy(this.context);
                        this.onControlRequset('update', tempContext, item);
                        let response = await this.service.update(this.updateAction, tempContext, item, this.showBusyIndicator);
                        this.onControlResponse('update', response);
                        successItems.push(Util.deepCopy(response.data));
                    }
                }
            } catch (error) {
                this.onControlResponse('save', error);
                errorItems.push(Util.deepCopy(item));
                errorMessage.push(error);
            }
        }
        this.ctrlEvent({ controlname: this.name, action: "save", data: successItems });
        if (errorItems.length === 0 && successItems.length > 0) {
            this.refresh();
            if (args?.showResultInfo || (args && !args.hasOwnProperty('showResultInfo'))) {
                this.$success((this.$t('app.commonwords.savesuccess') as string), 'save');
            }
        } else {
            errorItems.forEach((item: any, index: number) => {
                if (errorMessage[index] && errorMessage[index].data) {
                    if (Object.is(errorMessage[index].data.errorKey, 'DupCheck')) {
                        let errorProp: string = errorMessage[index].data.message.match(/\[[a-zA-Z]*\]/)[0];
                        let name: string = errorProp ? this.service.getNameByProp(errorProp.substr(1, errorProp.length - 2)) : '';
                        if (name) {
                            let desc: any = this.allColumns.find((column: any) => {
                                return Object.is(column.name, name);
                            });
                            this.$throw((desc ? desc.label : '') + " : " + item[name] + (this.$t('app.commonwords.isexist') as string) + '!', 'save');
                        } else {
                            this.$throw(errorMessage[index].data.message ? errorMessage[index].data.message : (this.$t('app.commonwords.sysexception') as string), 'save');
                        }
                    } else if (Object.is(errorMessage[index].data.errorKey, 'DuplicateKeyException')) {
                        if (Util.isEmpty(this.columnKeyName)) {
                            this.$throw(errorMessage[index].data.message ? errorMessage[index].data.message : (this.$t('app.commonwords.sysexception') as string), 'save');
                        } else {
                            let name: string = this.service.getNameByProp(this.columnKeyName);
                            if (name) {
                                let desc: any = this.allColumns.find((column: any) => {
                                    return Object.is(column.name, name);
                                });
                                this.$throw((desc ? desc.label : '') + " : " + item[name] + (this.$t('app.commonwords.isexist') as string) + '!', 'save');
                            }
                        }
                    } else {
                        this.$throw(errorMessage[index].data.message ? errorMessage[index].data.message : (this.$t('app.commonwords.sysexception') as string), 'save');
                    }
                } else {
                    this.$throw((item[this.majorInfoColName] ? item[this.majorInfoColName] : "") + (this.$t('app.commonwords.savefailed') as string) + '!', 'save');
                }
            });
        }
        return successItems;
    }

    /**
     * 新建行
     *
     * @param {any[]} args 新建数据
     * @memberof GridControlBase
     */
    public newRow(args: any[]): void {
        if (!this.loaddraftAction) {
            this.$throw(`${this.controlInstance.codeName}` + (this.$t('app.grid.notconfig.loaddraftaction') as string), 'newRow');
            return;
        }
        let _this = this;
        Object.assign(args[0], { viewparams: this.viewparams });
        let tempContext: any = Util.deepCopy(this.context);
        this.onControlRequset('newRow', tempContext, args[0]);
        let post: Promise<any> = this.service.loadDraft(this.loaddraftAction, tempContext, args[0], this.showBusyIndicator);
        post.then((response: any) => {
            this.onControlResponse('newRow', response);
            if (!response.status || response.status !== 200) {
                this.$throw(response, 'newRow');
                return;
            }
            const data = response.data;
            this.createDefault(data);
            data.rowDataState = "create";
            let gridDatas = [data, ...this.items];
            this.newRowState = false;
            this.items = [];
            this.$nextTick(() => {
                this.items = gridDatas;
                this.newRowState = true;
            });
            _this.gridItemsModel.push(_this.getGridRowModel());
        }).catch((response: any) => {
            this.onControlResponse('newRow', response);
            this.$throw(response, 'newRow');
        });
    }

    /**
     * 数据导入
     *
     * @memberof GridControlBase
     */
    public importExcel(): void {
        let _this: any = this;
        if (Object.keys(this.importDataModel).length == 0) {
            this.$warning((this.$t("app.utilview.info") as string), 'importExcel');
            return;
        }
        let customClass = 'app-data-upload-modal';
        customClass += this.viewStyle != 'STYLE2' ? ' view-default' : ' view-style2';
        const view: any = {
            viewname: 'app-data-upload',
            title: this.$t("app.utilview.importview"),
            width: 544,
            height: 368,
            customClass: customClass
        }
        let container: Subject<any> = _this.$appmodal.openModal(view, Util.deepCopy(this.context), this.importDataModel);
        container.subscribe((result: any) => {
            if (Object.is(result.ret, 'OK')) {
                this.refresh(result.datas);
            }
        });
    }

    /**
     * 数据导出
     *
     * @param {*} args 额外参数
     * @memberof GridControlBase
     */
    public exportExcel(args: any = {}): void {
        let _this: any = this;
        // 导出Excel
        const doExport = async (_data: any) => {
            const tHeader: Array<any> = [];
            const filterVal: Array<any> = [];
            let allColumns: Array<any> = this.allExportColumns?.length > 0 ? [...this.allExportColumns] : [...this.allColumns];
            allColumns.forEach((item: any) => {
                if (!item.columnType || (item.columnType && item.columnType != 'UAGRIDCOLUMN')) {
                    item.show && item.label ? tHeader.push(item.label) : "";
                    item.show && item.name ? filterVal.push(item.name) : "";
                }
            });
            const data = await this.formatExcelData(filterVal, _data);
            const appDataEntity: IPSAppDataEntity = this.controlInstance.getPSAppDataEntity() as IPSAppDataEntity;
            _this.$export.exportExcel().then((excel: any) => {
                excel.export_json_to_excel({
                    header: tHeader, //表头 必填
                    data, //具体数据 必填
                    filename: `${appDataEntity?.logicName}` + (this.$t('app.grid.grid') as string), //非必填
                    autoWidth: true, //非必填
                    bookType: "xlsx" //非必填
                });
            });
        };
        const page: any = {};
        // 设置page，size
        if (Object.is(args.type, 'maxRowCount')) {
            Object.assign(page, { page: 0, size: args.maxRowCount ? args.maxRowCount : 1000 });
        } else if (Object.is(args.type, 'activatedPage')) {
            if (this.allExportColumns?.length > 0) {
                Object.assign(page, { page: this.curPage - 1, size: this.limit });
            } else {
                try {
                    doExport(Util.deepCopy(this.items));
                } catch (error) {
                    this.$throw(error, 'exportExcel');
                }
                return;
            }
        }
        // 设置排序
        if (!this.isNoSort && !Object.is(this.minorSortDir, '') && !Object.is(this.minorSortPSDEF, '')) {
            const sort: string = this.minorSortPSDEF + "," + this.minorSortDir;
            Object.assign(page, { sort: sort });
        }
        const arg: any = {};
        Object.assign(arg, page);
        // 获取query,搜索表单，viewparams等父数据
        const parentdata: any = {};
        this.ctrlEvent({ controlname: this.name, action: "beforeload", data: parentdata });
        Object.assign(arg, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
        Object.assign(tempViewParams, Util.deepCopy(this.viewparams));
        // 多实例查询数据处理
        let appModelObj = AppServiceBase.getInstance().getAppModelDataObject();
        let appEnvironment = AppServiceBase.getInstance().getAppEnvironment();
        if (appEnvironment.bDynamic) {
            if (tempViewParams.hasOwnProperty("srfdynainstid")) {
                let dynainstParam: DynamicInstanceConfig = this.modelService?.getDynaInsConfig();
                Object.assign(tempViewParams, { srfinsttag: dynainstParam.instTag, srfinsttag2: dynainstParam.instTag2 });
                delete tempViewParams.srfdynainstid;
            } else {
                if (!tempViewParams.hasOwnProperty("srfinsttag")) {
                    Object.assign(tempViewParams, { srfinsttag: "__srfstdinst__" });
                }
            }
        } else {
            if (tempViewParams.hasOwnProperty("srfwf")) {
                Object.assign(tempViewParams, { srfinsttag: "__srfstdinst__" });
            }
        }
        Object.assign(arg, { viewparams: tempViewParams });
        let tempContext: any = Util.deepCopy(this.context);
        this.onControlRequset('exportExcel', tempContext, arg);
        const post: Promise<any> = this.allExportColumns?.length > 0 ?
            this.service.searchDEExportData(this.fetchAction, tempContext, arg, this.showBusyIndicator) :
            this.service.search(this.fetchAction, tempContext, arg, this.showBusyIndicator);
        post.then((response: any) => {
            this.onControlResponse('exportExcel', response);
            if (!response || response.status !== 200) {
                this.$throw((this.$t('app.grid.exportfail') as string) + ',' + response.info, 'exportExcel');
                return;
            }
            try {
                doExport(Util.deepCopy(response.data));
            } catch (error) {
                this.$throw(error, 'exportExcel');
            }
        }).catch((response: any) => {
            this.onControlResponse('exportExcel', response);
            this.$throw(response, 'exportExcel');
        });
    }

    /**
     * 部件刷新
     *
     * @param {*} args 额外参数
     * @memberof GridControlBase
     */
    public refresh(args?: any): void {
        this.load();
    }

    /**
     * 导出数据格式化
     * 
     * @param {*} filterVal
     * @param {*} jsonData
     * @returns {[]}
     * @memberof GridControlBase
     */
    public async formatExcelData(filterVal: any, jsonData: any) {
        let codelistColumns: Array<any> = [];
        let exportItems: Array<IPSDEDataExportItem> = this.controlInstance.getPSDEDataExport()?.getPSDEDataExportItems() || [];
        if (exportItems && exportItems.length > 0) {
            exportItems.forEach((item: IPSDEDataExportItem) => {
                const codeList = item.getPSCodeList();
                if (codeList) {
                    let codeListColumn = {
                        name: item.name.toLowerCase(),
                        codeList: codeList,
                    }
                    codelistColumns.push(codeListColumn);
                }
            })
        } else {
            const allColumns: Array<IPSDEGridColumn> = this.controlInstance.getPSDEGridColumns() || [];
            if (allColumns.length > 0) {
                allColumns.forEach((item: IPSDEGridColumn) => {
                    const codelist = (item as IPSDEGridFieldColumn)?.getPSAppCodeList?.();
                    if (!item.hideDefault && item.name != '' && codelist && (item as IPSDEGridFieldColumn).cLConvertMode == "FRONT") {
                        let codeListColumn = {
                            name: item.name.toLowerCase(),
                            codeList: codelist,
                        }
                        codelistColumns.push(codeListColumn);
                    }
                })
            }
        }
        let _this = this;
        for (const codelistColumn of codelistColumns) {
            const { codeList } = codelistColumn;
            let items: any = await this.codeListService.getDataItems({ tag: codeList.codeName, type: codeList.codeListType, data: codeList, context: this.context, viewparams: this.viewparams });
            jsonData.forEach((row: any) => {
                row[codelistColumn.name] = ModelTool.getCodelistValue(items, row[codelistColumn.name], codelistColumn.codeList, this);
            })
        }
        return jsonData.map((v: any) => filterVal.map((j: any) => v[j]))
    }

    /**
     * 表格分组
     * 
     * @memberof GridControlBase
     */
    public group() {
        if (Object.is(this.groupMode, "AUTO")) {
            this.drawGroup();
        } else if (Object.is(this.groupMode, "CODELIST")) {
            this.drawCodelistGroup();
        }
    }

    /**
     * 根据分组代码表绘制分组列表
     * 
     * @memberof GridControlBase
     */
    public async drawCodelistGroup() {
        // 分组
        let groupTree: Array<any> = [];
        let allGroup: Array<any> = [];
        let allGroupField: Array<any> = [];
        //其他
        let otherItems: Array<any> = [];
        if (this.codelistTag && this.codelistType) {
            allGroup = await this.codeListService.getDataItems({ tag: this.codelistTag, type: this.codelistType, data: this.codelist, context: this.context });
        }
        if (this.groupAppFieldCodelistTag && this.groupAppFieldCodelistType) {
            allGroupField = await this.codeListService.getDataItems({ tag: this.groupAppFieldCodelistTag, type: this.groupAppFieldCodelistType, data: this.groupAppFieldCodelist, context: this.context });
        }
        if (!this.items || this.items.length == 0) {
            return;
        }
        if (allGroup.length == 0) {
            LogUtil.warn(this.$t('app.dataview.useless'));
            this.items.forEach((item: any, index: number) => {
                Object.assign(item, {
                    groupById: Util.createUUID(),
                    group: ""
                });
                otherItems.push(item);
            })
        } else {
            // 其他
            this.items.forEach((item: any, index: number) => {
                Object.assign(item, {
                    groupById: Util.createUUID(),
                    group: ""
                });
                const i = allGroup.findIndex((group: any)=> !Object.is(allGroupField && allGroupField.length > 0 ? group.label : group.value, item[this.groupAppField]));
                if(i < 0){
                    otherItems.push(item);
                }
            });
            // 分组
            allGroup.forEach((group: any, index: number) => {
                let children: Array<any> = [];
                this.items.forEach((item: any, _index: number) => {
                    const field = allGroupField && allGroupField.length > 0 ? group.label : group.value;
                    if (Object.is(item[this.groupAppField], field)) {
                        children.push(item);
                    }
                })
                const tree = this.initTree(group.label, index, children);
                groupTree.push(tree);
            })
        }
        if (otherItems.length > 0) {
            otherItems = [...new Set(otherItems)];
            const tree = this.initTree(this.$t('app.commonwords.other'), allGroup.length + 1, otherItems);
            groupTree.push(tree);
        }
        this.items = groupTree;
        if (this.actualIsOpenEdit) {
            for (let i = 0; i < this.items.length; i++) {
                this.gridItemsModel.push(this.getGridRowModel());
            }
        }
    }

    /**
     * 绘制分组
     * 
     * @memberof GridControlBase
     */
    public drawGroup() {
        if (!this.isEnableGroup) return;
        // 分组
        let allGroup: Array<any> = [];
        this.items.forEach((item: any) => {
            if (item.hasOwnProperty(this.groupAppField)) {
                allGroup.push(item[this.groupAppField]);
            }
        });
        let groupTree: Array<any> = [];
        allGroup = [...new Set(allGroup)];
        if (allGroup.length == 0) {
            LogUtil.warn(this.$t('app.dataview.useless'));
        }
        // 组装数据
        allGroup.forEach((group: any, groupIndex: number) => {
            let children: Array<any> = [];
            this.items.forEach((item: any, itemIndex: number) => {
                if (Object.is(group, item[this.groupAppField])) {
                    item.groupById = Util.createUUID();
                    item.group = '';
                    children.push(item);
                }
            });
            group = group ? group : this.$t('app.grid.other');
            const tree: any = this.initTree(group, groupIndex, children);
            groupTree.push(tree);
        });
        this.items = groupTree;
        if (this.actualIsOpenEdit) {
            for (let i = 0; i < this.items.length; i++) {
                this.gridItemsModel.push(this.getGridRowModel());
            }
        }
    }

    /**
     * 初始化自动分组树
     * 
     * @param group 
     * @param groupIndex 
     * @param children 
     * @memberof GridControlBase
     */
    public initTree(group: any, groupIndex: number, children: Array<any>) {
        let tree: any = {
            groupById: Util.createUUID(),
            group: group,
            children: children,
        }
        if (children) {
            tree["hasChildren"] = true;
        }
        let allColumns: Array<IPSDEGridColumn> = this.controlInstance.getPSDEGridColumns() || [];
        if (allColumns.length > 0) {
            for (let singleColumn of allColumns) {
                if (singleColumn.columnType && singleColumn.columnType == "UAGRIDCOLUMN") {
                    const uiActionGroupDetails: Array<IPSUIActionGroupDetail> = (singleColumn as IPSDEGridUAColumn).getPSDEUIActionGroup()?.getPSUIActionGroupDetails() || [];
                    if (uiActionGroupDetails.length > 0) {
                        uiActionGroupDetails.forEach((element: IPSUIActionGroupDetail) => {
                            const uiAction = element.getPSUIAction();
                            if (uiAction) {
                                tree[uiAction.uIActionTag] = { visabled: false };
                            }
                        });
                    }
                } else if (singleColumn.columnType && singleColumn.columnType == "DEFGRIDCOLUMN") {
                    const uiAction = (singleColumn as IPSDEGridFieldColumn)?.getPSDEUIAction()
                    if (uiAction) {
                        tree[uiAction.uIActionTag] = { visabled: false };
                    }
                    tree[singleColumn.codeName] = '';
                }
            }
        }
        return tree;
    }

    /**
     * 单个复选框选中
     *
     * @param {*} selection 所有选中行数据
     * @param {*} row 当前选中行数据
     * @memberof GridControlBase
     */
    public select(selection: any, row: any): void {
        if (this.groupAppField) {
            let isContain: boolean = selection.some((item: any) => {
                return item == row;
            })
            // 是否选中当前行，选中为true，否则为false
            if (isContain) {
                // 当前行为分组行
                if (row.children && row.children.length > 0) {
                    this.toggleSelection(row.children, true);
                    row.children.forEach((children: any) => {
                        this.selections.push(children);
                    });
                } else {
                    this.selections.push(row);
                }
            } else {
                if (row.children && row.children.length > 0) {
                    this.toggleSelection(row.children, false);
                    this.selections = this.computeCheckedData(this.selections, row.children);
                } else {
                    this.selections = this.computeCheckedData(this.selections, row);
                }
            }
            this.selections = [...new Set(this.selections)]
        } else {
            if (!selection) {
                return;
            }
            this.selections = [...Util.deepCopy(selection)];
        }
        this.ctrlEvent({ controlname: this.name, action: "selectionchange", data: this.selections });
    }

    /**
     * 计算当前选中数据
     *
     * @param {*} selectionArray 所有选中行数据
     * @param {*} cancelData 被取消选中行数据，分组行为数组，非分组行为对象
     * @memberof GridControlBase
     */
    public computeCheckedData(selectionArray: any[], cancelData: Array<any> | any) {
        let targetArray: Array<any> = [];
        //  分组行
        if (Array.isArray(cancelData)) {
            if (selectionArray && selectionArray.length > 0) {
                selectionArray.forEach((selection: any) => {
                    let tempFlag: boolean = true;
                    cancelData.forEach((child: any) => {
                        if (selection.groupById === child.groupById) {
                            tempFlag = false;
                        }
                    })
                    if (tempFlag) targetArray.push(selection);
                })
            }
        } else {
            //  非分组行
            if (selectionArray && selectionArray.length > 0) {
                selectionArray.forEach((selection: any) => {
                    let tempFlag: boolean = true;
                    if (selection.groupById === cancelData.groupById) {
                        tempFlag = false;
                    }
                    if (tempFlag) targetArray.push(selection);
                })
            }
        }
        return targetArray;
    }

    /**
     * 设置非分组行checkbox选中状态
     *
     * @param {*} rows 选中数据数组
     * @param {boolean} flag 是否选中
     * @memberof GridControlBase
     */
    public toggleSelection(rows?: any, flag?: boolean) {
        const table: any = (this.$refs as any)[this.gridRefName];
        if (rows) {
            rows.forEach((row: any) => {
                table.toggleRowSelection(row, flag);
            });
        } else {
            table.clearSelection();
        }
    }

    /**
     * 复选框数据全部选中
     *
     * @param {*} selection 选中数据
     * @memberof GridControlBase
     */
    public selectAll(selection: any): void {
        this.selections = [];
        if (this.groupAppField) {
            let flag: boolean = true;
            if (selection && selection.length === this.items.length) {
                selection.forEach((element: any) => {
                    if (element.children) {
                        this.toggleSelection(element.children, flag);
                        element.children.forEach((children: any) => {
                            this.selections.push(children);
                        });
                    } else {
                        flag = false;
                    }
                });
            } else {
                flag = false;
            }
            if (!flag) {
                this.toggleSelection();
            }
        } else {
            if (!selection) {
                return;
            }
            this.selections = [...Util.deepCopy(selection)];
        }
        this.ctrlEvent({ controlname: this.name, action: "selectionchange", data: this.selections });
    }

    /**
     * 表格行选中样式
     *
     * @param {{ row: any, rowIndex: any }} { row, rowIndex }
     * @memberof GridControlBase
     */
    public onRowClassName({ row, rowIndex }: { row: any, rowIndex: any }): string {
        const index = this.selections.findIndex((select: any) => Object.is(select.srfkey, row.srfkey));
        return index !== -1 ? 'grid-row-select' : '';
    }

    /**
     * 行单击选中
     *
     * @param {*} row 行
     * @param {*} column 列
     * @param {*} event 点击事件
     * @param {boolean} isExecute 是否执行
     * @memberof GridControlBase
     */
    public rowClick(row: any, column?: any, event?: any, isExecute: boolean = false): void {
        //是否是分组列，是分组列时选中数据
        const isSelectColumn: boolean = column && Object.is(column.type, 'selection') ? true : false;
        // 分组行跳过
        if (row && row.children) {
            return;
        }
        if (!isExecute && (!row || this.actualIsOpenEdit)) {
            return;
        }
        if (this.stopRowClick) {
            this.stopRowClick = false;
            return;
        }
        this.selections = [];
        if (this.gridRowActiveMode == 1 && !isSelectColumn) {
            this.ctrlEvent({ controlname: this.name, action: "rowclick", data: Util.deepCopy(row) });
            this.ctrlEvent({ controlname: this.name, action: "selectionchange", data: [Util.deepCopy(row)] });
        } else if (this.gridRowActiveMode == 2 || isSelectColumn) {
            // 只选中当前行
            this.selections.push(Util.deepCopy(row));
            const table: any = (this.$refs as any)[this.gridRefName];
            if (table) {
                table.clearSelection();
                if (this.isSingleSelect) {
                    table.setCurrentRow(row);
                } else {
                    table.toggleRowSelection(row, true);
                }
            }
            this.ctrlEvent({ controlname: this.name, action: "selectionchange", data: this.selections });
        }
    }

    /**
     * 行双击事件
     *
     * @param {*} $event 点击事件
     * @memberof GridControlBase
     */
    public rowDBLClick($event: any): void {
        // 分组行跳过
        if ($event && $event.children) {
            return;
        }
        if (!$event || this.actualIsOpenEdit || Object.is(this.gridRowActiveMode, 0)) {
            return;
        }
        this.ctrlEvent({ controlname: this.name, action: "rowdblclick", data: Util.deepCopy($event) });
    }

    /**
     * 获取对应行class
     *
     * @param {*} $args row 行数据，rowIndex 行索引
     * @returns {void}
     * @memberof GridControlBase
     */
    public getRowClassName(args: { row: any, rowIndex: number }) {
        const appde = this.controlInstance.getPSAppDataEntity();
        if (appde != null) {
            let isSelected = this.selections.some((item: any) => {
                return Object.is(item[appde.codeName.toLowerCase()], args.row[appde?.codeName.toLowerCase()]);
            });
            return isSelected ? "grid-selected-row" : "";
        }
        return "";
    }

    /**
     * 获取对应单元格class
     *
     * @param {*} $args row 行数据，column 列数据，rowIndex 行索引，columnIndex 列索引
     * @returns {void}
     * @memberof GridControlBase
     */
    public getCellClassName(args: { row: any, column: any, rowIndex: number, columnIndex: number }) {
        let className: string = '';
        if (args.column.property) {
            let col = this.allColumns.find((item: any) => {
                return Object.is(args.column.property, item.name);
            })
            if (col !== undefined) {
                if (col.isEnableRowEdit && this.actualIsOpenEdit) {
                    className += 'edit-cell ';
                }
            } else {
                className += 'info-cell';
            }
        }
        if (this.groupAppField && args.columnIndex === 0 && !this.isSingleSelect) {
            if (args.row.children && args.row.children.length > 0) {
                className += this.computeGroupRow(args.row.children, args.row);
            }
        }
        return className;
    }

    /**
     * 计算分组行checkbox选中样式
     *
     * @param {*} rows 当前分组行下的所有数据
     * @returns {*} currentRow 当前分组行
     * @memberof GridControlBase
     */
    public computeGroupRow(rows: any[], currentRow: any) {
        const table: any = (this.$refs as any)[this.gridRefName];
        let count: number = 0;
        this.selections.forEach((select: any) => {
            rows.forEach((row: any) => {
                if (row.groupById === select.groupById) {
                    count++;
                }
            })
        })
        if (count === rows.length) {
            table.toggleRowSelection(currentRow, true);
            return 'cell-select-all ';
        } else if (count !== 0 && count < rows.length) {
            return 'cell-indeterminate '
        } else if (count === 0) {
            table.toggleRowSelection(currentRow, false);
            return '';
        }
    }

    /**
     * 页面变化
     *
     * @param {*} $event 事件源
     * @memberof GridControlBase
     */
    public pageOnChange($event: any): void {
        if (!$event) {
            return;
        }
        if ($event === this.curPage) {
            return;
        }
        this.curPage = $event;
        this.load({});
    }

    /**
     * 分页条数变化
     *
     * @param {*} $event 事件源
     * @memberof GridControlBase
     */
    public onPageSizeChange($event: any): void {
        if (!$event) {
            return;
        }
        if ($event === this.limit) {
            return;
        }
        this.limit = $event;
        if (this.curPage === 1) {
            this.load({});
        }
    }

    /**
     * 分页刷新
     *
     * @memberof GridControlBase
     */
    public pageRefresh(): void {
        this.load({}, true);
    }

    /**
     * 排序变化
     *
     * @param {{ column: any, prop: any, order: any }} { column, prop, order } UI回调数据
     * @memberof GridControlBase
     */
    public onSortChange({ column, prop, order }: { column: any, prop: any, order: any }): void {
        const dir = Object.is(order, 'ascending') ? 'asc' : Object.is(order, 'descending') ? 'desc' : '';
        if (Object.is(dir, this.minorSortDir) && Object.is(this.minorSortPSDEF, prop)) {
            return;
        }
        this.minorSortDir = dir;
        this.minorSortPSDEF = prop ? prop : '';
        this.load({});
    }

    /**
     * 远程获取合计行数据
     *
     * @memberof GridControlBase
     */
    public getAggData(context: any = {}, data: any = {}) {
        const _this: any = this;
        const dataEntity = _this.controlInstance.getAggPSAppDataEntity() as IPSAppDataEntity;
        const dataSet = _this.controlInstance.getAggPSAppDEDataSet() as IPSAppDEDataSet;
        if (!(dataEntity && dataSet && dataSet.codeName)) {
            return;
        }
        new GlobalService().getService(dataEntity.codeName, context).then((service: any) => {
            if (service && service[dataSet.codeName] && service[dataSet.codeName] instanceof Function) {
                service[dataSet.codeName](context, data).then((response: any) => {
                    _this.onControlResponse('getAggData', response);
                    if (!response.status || response.status !== 200) {
                        _this.$throw(response, 'getAggData');
                        return;
                    }
                    _this.remoteData = response.data;
                    _this.isDisplay = true;
                }).catch((response: any) => {
                    _this.onControlResponse('getAggData', response);
                    _this.remoteData = {};
                    _this.isDisplay = true;
                    _this.$throw(response, 'getAggData');
                })
            }
        });
    }

    /**
     * 设置列状态
     *
     * @memberof GridControlBase
     */
    public setColState() {
        const _data: any = localStorage.getItem(`${this.appDeCodeName.toLowerCase()}_${this.controlInstance.codeName.toLowerCase()}_${this.name.toLowerCase()}`);
        if (_data) {
            let columns = JSON.parse(_data);
            columns.forEach((col: any) => {
                let column = this.allColumns.find((item) => Object.is(col.name, item.name));
                if (column) {
                    Object.assign(column, col);
                }
            });
        }
    }

    /**
     * 列变化
     *
     * @memberof GridControlBase
     */
    public onColChange() {
        localStorage.setItem(`${this.appDeCodeName.toLowerCase()}_${this.controlInstance.codeName.toLowerCase()}_${this.name.toLowerCase()}`, JSON.stringify(this.allColumns));
    }

    /**
     * 获取列状态
     *
     * @param {string} name
     * @returns {boolean}
     * @memberof GridControlBase
     */
    public getColumnState(name: string): boolean {
        let column = this.allColumns.find((col: any) =>
            Object.is(name, col.name)
        );
        return column.show ? true : false;
    }

    /**
     * 表格编辑项值变更
     *  
     * @param row 行数据
     * @param {{ name: string, value: any }} 变化数据键值
     * @param rowIndex 行索引
     * @memberof GridControlBase
     */
    public onGridItemValueChange(row: any, $event: { name: string, value: any }, rowIndex: number): void {
        if (!$event) {
            return;
        }
        if (!$event.name || Object.is($event.name, '') || !row.hasOwnProperty($event.name)) {
            return;
        }
        row[$event.name] = $event.value;
        this.gridEditItemChange(row, $event.name, $event.value, rowIndex);
    }

    /**
     * 表格编辑项值变化
     *
     * @public
     * @param row 行数据
     * @param property 列编辑项名
     * @param row 列编辑项值
     * @memberof GridControlBase
     */
    public gridEditItemChange(row: any, property: string, value: any, rowIndex: number) {
        if (this.majorInfoColName == property) {
            this.items[rowIndex].srfmajortext = value;
        }
        row.rowDataState = row.rowDataState ? row.rowDataState : "update";
        if (Object.is(row.rowDataState, "update")) {
            if (this.defaultUpdateItems.includes(property)) {
                row.hasUpdated = true;
            }
        }
        this.curEditRowData = row;
        this.resetGridData(row, property, rowIndex);
        this.validate(property, row, rowIndex);
        //  表格项更新对象
        const allEditColumns: Array<IPSDEGridEditItem> = this.controlInstance.getPSDEGridEditItems() || [];
        if (allEditColumns && allEditColumns.length > 0) {
            allEditColumns.forEach((item: IPSDEGridEditItem) => {
                const itemUpdate: IPSDEGridEditItemUpdate | null = item.getPSDEGridEditItemUpdate?.();
                if (itemUpdate && Object.is(property, item.name)) {
                    if (itemUpdate.customCode) {
                        if (itemUpdate.scriptCode) {
                            const context = Util.deepCopy(this.context);
                            const viewparams = Util.deepCopy(this.viewparams);
                            let data = this.items[rowIndex];
                            eval(itemUpdate.scriptCode);
                        }
                    } else {
                        let details: string[] = [];
                        const updateDetails: any[] = itemUpdate.getPSDEGEIUpdateDetails() || [];
                        updateDetails.forEach((detail: any) => {
                            details.push(detail.name);
                        })
                        const showBusyIndicator = itemUpdate.hasOwnProperty('showBusyIndicator') ? itemUpdate.showBusyIndicator : true;
                        this.updateGridEditItem(itemUpdate.getPSAppDEMethod?.()?.codeName as string, row, details, showBusyIndicator);
                    }
                }
            })
        }
    }

    /**
     * 表格编辑项更新
     *
     * @param {string} mode 界面行为名称
     * @param {*} [data={}] 请求数据
     * @param {string[]} updateDetails 更新项
     * @param {boolean} [showloading] 是否显示加载状态
     * @memberof GridControlBase
     */
    public updateGridEditItem(mode: string, data: any = {}, updateDetails: string[], showloading?: boolean): void {
        if (!mode || (mode && Object.is(mode, ''))) {
            return;
        }
        let tempContext: any = Util.deepCopy(this.context);
        const appDeCodeName: string = this.controlInstance.getPSAppDataEntity()?.codeName || "";
        if (!Util.isEmpty(this.columnKeyName)) {
            Object.assign(tempContext, { [appDeCodeName?.toLowerCase()]: data[this.columnKeyName] });
        }
        const arg: any = Util.deepCopy(data);
        Object.assign(arg, { viewparams: this.viewparams });
        this.onControlRequset('updateGridEditItem', tempContext, arg);
        const post: Promise<any> = this.service.frontLogic(mode, tempContext, arg, showloading);
        post.then((response: any) => {
            this.onControlResponse('updateGridEditItem', response);
            if (!response || response.status !== 200) {
                this.$throw((this.$t('app.grid.formitemfailed') as string), 'updateGridEditItem');
                return;
            }
            const _data: any = response.data;
            if (!_data) {
                return;
            }
            updateDetails.forEach((name: string) => {
                if (!_data.hasOwnProperty(name)) {
                    return;
                }
                data[name] = _data[name];
            });
        }).catch((response: any) => {
            this.onControlResponse('updateGridEditItem', response);
            this.$throw(response, 'updateGridEditItem');
        });
    }

    /**
     * 新建默认值
     * @param {*}  row 行数据
     * @memberof GridControlBase
     */
    public createDefault(row: any) {
        const editItems: Array<any> = this.controlInstance.getPSDEGridEditItems() || [];
        if (editItems && editItems.length > 0) {
            for (const item of editItems) {
                const property = item?.codeName?.toLowerCase();
                //新建默认值(具备类型)
                if (item.createDVT && property && row.hasOwnProperty(property)) {
                    switch (item.createDVT) {
                        case "CONTEXT":
                            if (item.createDV) {
                                row[property] = this.viewparams[item.createDV];
                            }
                            break;
                        case "SESSION":
                        case "APPDATA":
                            if (item.createDV) {
                                row[property] = this.context[item.createDV];
                            }
                            break;
                        case "OPERATORNAME":
                            row[property] = this.context["srfusername"];
                            break;
                        case "OPERATOR":
                            row[property] = this.context["srfuserid"];
                            break;
                        case "CURTIME":
                            row[property] = Util.dateFormat(new Date(), item.getPSAppDEField()?.valueFormat);
                            break;
                        case "PARAM":
                            if (item.createDV) {
                                row[property] = this.computeDefaultValueWithParam("CREATE", item.createDV, row);
                            }
                            break;
                    }
                } else if (item.createDV && property && row.hasOwnProperty(property)) {
                    row[property] = item.appDEField?.isNumber ? Number(item.createDV) : item.createDV;
                }
            }
        }
    }

    /**
     * 更新默认值
     * @param {*}  row 行数据
     * @memberof GridControlBase
     */
    public updateDefault(row: any) {
        const editItems: Array<any> = this.controlInstance.getPSDEGridEditItems() || [];
        if (editItems && editItems.length > 0) {
            for (const item of editItems) {
                const property = item?.codeName?.toLowerCase();
                if (item.updateDVT && property && row.hasOwnProperty(property)) {
                    switch (item.updateDVT) {
                        case "CONTEXT":
                            if (item.updateDV) {
                                row[property] = this.viewparams[item.updateDV];
                            }
                            break;
                        case "SESSION":
                        case "APPDATA":
                            if (item.updateDV) {
                                row[property] = this.context[item.updateDV];
                            }
                            break;
                        case "OPERATORNAME":
                            row[property] = this.context["srfusername"];
                            break;
                        case "OPERATOR":
                            row[property] = this.context["srfuserid"];
                            break;
                        case "CURTIME":
                            row[property] = Util.dateFormat(new Date(), item.getPSAppDEField()?.valueFormat);
                            break;
                        case "PARAM":
                            if (item.updateDV) {
                                row[property] = this.computeDefaultValueWithParam("CREATE", item.updateDV, row);
                            }
                            break;
                    }
                } else if (item.updateDV && property && row.hasOwnProperty(property)) {
                    row[property] = item.appDEField?.isNumber ? Number(item.updateDV) : item.updateDV;
                }
            }
        }
    }


    /**
     * 计算数据对象类型的默认值
     * @param {string}  action 行为
     * @param {string}  param 默认值参数
     * @param {*}  data 当前行数据
     * @memberof GridControlBase
     */
    public computeDefaultValueWithParam(action: string, param: string, data: any) {
        if (Object.is(action, "UPDATE")) {
            const nativeData: any = this.service.getCopynativeData();
            if (nativeData && (nativeData instanceof Array) && nativeData.length > 0) {
                let targetData: any = nativeData.find((item: any) => {
                    // return item.${appde.getKeyPSAppDEField().getCodeName()?lower_case} === data.srfkey;
                })
                if (targetData) {
                    return targetData[param] ? targetData[param] : null;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else {
            return this.service.getRemoteCopyData()[param] ? this.service.getRemoteCopyData()[param] : null;
        }
    }

    /**
     * 获取表格列禁用状态
     *
     * @memberof GridControlBase
     */
    public getColumnDisabled(data: any, name: string) {
        if (this.allColumns || Array.isArray(this.allColumns)) {
            const curColumn: any = this.allColumns.find((item: any) => {
                return item.name === name;
            })
            if (curColumn.hasOwnProperty('enableCond')) {
                return data.srfuf == 1 ? (curColumn.enableCond & 2) !== 2 : (curColumn.enableCond & 1) !== 1
            } else {
                return false;
            }
        }
    }

    /**
     * 重置表格项值
     *
     * @param {*} row 当前行
     * @param {string} property 属性名
     * @param {number} rowIndex 行下标
     * @memberof GridControlBase
     */
    public resetGridData(row: any, property: string, rowIndex: number) {
        const allColumns: Array<IPSDEGridColumn> = this.controlInstance.getPSDEGridColumns() || [];
        if (this.actualIsOpenEdit && allColumns.length > 0) {
            allColumns.forEach((item: IPSDEGridColumn) => {
                const editItem: IPSDEGridEditItem = ModelTool.getGridItemByCodeName(item.codeName, this.controlInstance) as IPSDEGridEditItem;
                if (editItem?.resetItemName && Object.is(property, editItem?.resetItemName)) {
                    this.onGridItemValueChange(row, { name: item.name, value: null }, rowIndex);
                    if (editItem?.valueItemName) {
                        this.onGridItemValueChange(row, { name: editItem?.valueItemName, value: null }, rowIndex);
                    }
                }
            })
        }
    }

    /**
     * 合计行绘制
     *
     * @param {any} param
     * @memberof GridControlBase
     */
    public getSummaries(param: any) {
        const _this: any = this;
        const valueFormat = (value: any, format: any) => {
            try {
                return _this.textFormat(value, format);
            } catch {
                return value;
            }
        } 
        const { columns, data } = param;
        const sums: Array<any> = [];
        if (Object.is(this.aggMode, "PAGE")) {
            columns.forEach((column: any, index: number) => {
                if (index === 0) {
                    sums[index] = (this.$t('app.grid.dataaggregate.dataaggregate') as string);
                    return;
                }
                this.allColumnsInstance.forEach((columnInstance: any) => {
                    if (Object.is(columnInstance.columnType, "UAGRIDCOLUMN") && column.columnKey && Object.is(column.columnKey, columnInstance.name.toLowerCase())) {
                        sums[index] = '';
                        return;
                    }
                })
                const columnInstance: IPSDEGridColumn | undefined = this.allColumnsInstance.find((_column: any) => {
                    return Object.is(column.property, _column.codeName.toLowerCase());
                })
                const values = data.map((item: any) => Number(item[columnInstance && columnInstance.aggField ? columnInstance.aggField.toLowerCase() : column.property]));
                if (!values.every((value: any) => isNaN(value)) && columnInstance) {
                    if (Object.is(columnInstance.aggMode, "SUM")) {
                        let tempData = values.reduce((prev: any, curr: any) => {
                            const value = Number(curr);
                            if (!isNaN(value)) {
                                return prev + curr;
                            } else {
                                return prev;
                            }
                        }, 0);
                        sums[index] = columnInstance.aggValueFormat ? valueFormat(tempData, columnInstance.aggValueFormat) : (this.$t('app.grid.dataaggregate.sum') as string) + tempData.toFixed(3);
                    } else if (Object.is(columnInstance.aggMode, "AVG")) {
                        let tempData = values.reduce((prev: any, curr: any) => {
                            const value = Number(curr);
                            if (!isNaN(value)) {
                                return prev + curr;
                            } else {
                                return prev;
                            }
                        }, 0);
                        sums[index] = columnInstance.aggValueFormat ? valueFormat(tempData, columnInstance.aggValueFormat) : (this.$t('app.grid.dataaggregate.avg') as string) + (tempData / data.length).toFixed(2);
                    } else if (Object.is(columnInstance.aggMode, "MAX")) {
                        let tempData: any;
                        values.forEach((item: any) => {
                            const value = Number(item);
                            if (!isNaN(value)) {
                                if (!tempData) {
                                    tempData = value;
                                }
                                if (value > tempData) {
                                    tempData = value;
                                }
                            }
                        });
                        sums[index] = columnInstance.aggValueFormat ? valueFormat(tempData, columnInstance.aggValueFormat) : (this.$t('app.grid.dataaggregate.max') as string) + tempData;
                    } else if (Object.is(columnInstance.aggMode, "MIN")) {
                        let tempData: any;
                        values.forEach((item: any) => {
                            const value = Number(item);
                            if (!isNaN(value)) {
                                if (!tempData) {
                                    tempData = value;
                                }
                                if (value < tempData) {
                                    tempData = value;
                                }
                            }
                        });
                        sums[index] = columnInstance.aggValueFormat ? valueFormat(tempData, columnInstance.aggValueFormat) : (this.$t('app.grid.dataaggregate.min') as string) + tempData;
                    }
                } else {
                    sums[index] = '';
                }
            });
            return sums;
        } else if (Object.is(this.aggMode, "ALL")) {
            columns.forEach((column: any, index: number) => {
                if (index === 0) {
                    sums[index] = (this.$t('app.grid.dataaggregate.dataaggregate') as string);
                    return;
                } else if (index === (columns.length - 1)) {
                    sums[index] = '';
                    return;
                } else {
                    sums[index] = '';
                    const columnInstance: IPSDEGridColumn | undefined = this.allColumnsInstance.find((_column: any) => {
                        return Object.is(column.property, _column.codeName.toLowerCase());
                    });
                    if (columnInstance && this.remoteData) {
                        const value = this.remoteData[columnInstance.aggField ? columnInstance.aggField.toLowerCase() : columnInstance.codeName.toLowerCase()];
                        if (!isNaN(value)) {
                            switch (columnInstance.aggMode) {
                                case 'SUM':
                                    sums[index] = columnInstance.aggValueFormat ? valueFormat(value, columnInstance.aggValueFormat) : (this.$t('app.grid.dataaggregate.sum') as string) + value;
                                    break;
                                case 'AVG':
                                    sums[index] = columnInstance.aggValueFormat ? valueFormat(value, columnInstance.aggValueFormat) : (this.$t('app.grid.dataaggregate.avg') as string) + value;
                                    break;
                                case 'MAX':
                                    sums[index] = columnInstance.aggValueFormat ? valueFormat(value, columnInstance.aggValueFormat) : (this.$t('app.grid.dataaggregate.max') as string) + value;
                                    break;
                                case 'MIN':
                                    sums[index] = columnInstance.aggValueFormat ? valueFormat(value, columnInstance.aggValueFormat) : (this.$t('app.grid.dataaggregate.min') as string) + value;
                                    break;
                            }
                        }
                    }
                }
            });
            return sums;
        }
    }

    /**
     * 合并分组行
     * 
     * @memberof GridControlBase
     */
    public arraySpanMethod({ row, column, rowIndex, columnIndex }: any) {
        if (row && row.children) {
            if (columnIndex == (this.isSingleSelect ? 0 : 1)) {
                const allColumns: Array<any> = this.controlInstance.getPSDEGridColumns() || [];
                return [1, allColumns.length + (this.renderEmptyColumn ? 2 : 1)];
            } else if (columnIndex > (this.isSingleSelect ? 0 : 1)) {
                return [0, 0];
            }
        }
    }

    /**
     * 处理操作列点击
     * 
     * @param data 行数据
     * @param event 事件源
     * @param column 列对象
     * @param detail 触发成员对象
     * 
     * @memberof GridControlBase
     */
    public handleActionClick(data: any, event: any, column: any, detail: any) {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        AppViewLogicService.getInstance().executeViewLogic(this.getViewLogicTag(this.name, column.codeName, detail.name), event, this, data, this.controlInstance.getPSAppViewLogics() || []);
    }

    /**
     * 表格列内置界面行为
     * 
     * @param data 行数据
     * @param event 事件源
     * @param column 列对象
     * @memberof GridControlBase
     */
    public columnUIAction(data: any, event: any, column: any) {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        const tag = `grid_${column.codeName.toLowerCase()}_click`;
        AppViewLogicService.getInstance().executeViewLogic(tag, event, this, data, this.controlInstance.getPSAppViewLogics() || []);
    }


    /**
     * 通过属性名获取编辑列
     *
     * @memberof GridControlBase
     */
    public findEditItemByField(fieldName: string) {
        const editItems: Array<any> = this.controlInstance.getPSDEGridEditItems() || [];
        return editItems.find((editItem: any) => {
            return editItem.getPSAppDEField()?.name == fieldName;
        })
    }

    /**
     * 获取当前数据的行号
     * 
     * @param data 当前数据的JSON
     * @memberof GridControlBase
     */
    public findRowDataIndex(data: string) {
        const _data = JSON.parse(data);
        const indexs: any[] = [];
        this.items.forEach((item: any, index: number) => {
            let flag = item[this.appDeCodeName.toLowerCase()] == _data[this.appDeKeyFieldName.toLowerCase()];
            // 新建时判断除主键外数据是否一致
            if (flag && !_data[this.appDeKeyFieldName.toLowerCase()]) {
                for (const tempData in _data) {
                    if (!Object.is(tempData, this.appDeKeyFieldName.toLowerCase())) {
                        if (item[tempData] != _data[tempData]) {
                            flag = false;
                            return;
                        }
                    }
                }
            }
            if (flag) {
                indexs.push(index);
            }
        });
        return indexs;
    }

    /**
     * 处理部件UI响应
     *
     * @memberof GridControlBase
     */
    public onControlResponse(action: string, response: any) {
        super.onControlResponse(action, response);
        if (response && response.status && response.status != 200 && response.data) {
            const data: any = response.data;
            if (data.code && Object.is(AppErrorCode.INPUTERROR, data.code) && data.details?.length > 0) {
                let errorMsg: string = '';
                data.details.forEach((detail: any) => {
                    if (Object.is(EntityFieldErrorCode.ERROR_VALUERULE, detail.fielderrortype) && detail.fieldname) {
                        const tempEditItem: any = this.findEditItemByField(detail.fieldname);
                        const indexs = this.findRowDataIndex(response.config?.data);
                        if (tempEditItem && indexs.length > 0) {
                            indexs.forEach((_index: any) => {
                                Object.assign(this.gridItemsModel[_index][tempEditItem.name], { error: new String(detail.fielderrorinfo) });
                            })
                        } else {
                            errorMsg += `${detail.fieldlogicname}${detail.fielderrorinfo}<br/>`;
                        }
                    }
                })
                response.data.message = errorMsg ? errorMsg : (this.$t('app.searchform.globalerrortip') as string);
                this.$forceUpdate();
            }
        }
    }

    /**
     * 操作列按钮点击
     *
     * @memberof GridControlBase
     */    
    public handleActionButtonClick(row:any, $event:any, _column:IPSDEGridUAColumn, uiactionDetail:IPSUIActionGroupDetail){
        (this.$apppopover as any).popperDestroy2();
        this.handleActionClick(row, $event, _column, uiactionDetail);
    }

    /**
     * 计算目标部件参数
     *
     * @memberof GridControlBase
     */ 
    public computeTargetCtrlData(controlInstance: any, item?: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.dynamicProps, {
            inputData: item,
        })
        Object.assign(targetCtrlParam.staticProps, {
            transformData: this.transformData,
            opendata: this.opendata,
            newdata: this.newdata,
            remove: this.remove,
            refresh: this.refresh,

        })
        targetCtrlEvent['ctrl-event'] = ({ controlname, action, data }: { controlname: string, action: string, data: any }) => {
            this.onCtrlEvent(controlname, action, { item: item, data: data });
        };
        return { targetCtrlName, targetCtrlParam, targetCtrlEvent };
    }

}
