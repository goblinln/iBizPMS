import { Subject } from 'rxjs';
import { ViewTool, IBizGridModel, FormItemModel, Util, Verify } from 'ibiz-core';
import { MDControlBase } from './md-control-base';
import { AppGridService } from '../ctrl-service/app-grid-service';
import { AppViewLogicService } from 'ibiz-vue';

/**
 * 表格部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {MDControlBase}
 */
export class GridControlBase extends MDControlBase {

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
     * 表格实例
     * 
     * @type {IBizGridModel}
     * @memberof GridControlBase
     */
    public controlInstance!: IBizGridModel;

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
    public allColumnsInstance: Array<any> = [];

    /**
     * 表格模型集合
     *
     * @type {any[]}
     * @memberof GridControlBase
     */
    public gridItemsModel: any[] = [];

    /**
     * srfkey列名
     * 
     * @memberof GridControlBase
     */
    public columnKeyName: string = "";

    /**
     * 获取表格行模型
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public getGridRowModel() {
        let tempModel: any = {};
        const { allEditColumns: editItems } = this.controlInstance;
        if (editItems?.length > 0) {
            editItems.forEach((item: any) => {
                tempModel[item.name] = new FormItemModel();
            });
        }
        return tempModel;
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
     * 值规则集合
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public rules: any = {}

    /**
     * 初始化值规则
     *
     * @memberof EditFormControlBase
     */
    public initRules() {
        // 先初始化系统值规则和属性值规则
        let staticRules: any = {};
        const { allGridEditItemVRs } = this.controlInstance;
        allGridEditItemVRs?.forEach((item: any) => {
            const { getPSDEGridEditItemName: gridEditItemName, checkMode, valueRuleType, getPSSysValueRule: sysRule, getPSDEFValueRule: deRule } = item;
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
                                console.error(error);
                            }
                            return true;
                        },
                        trigger: ['change', 'blur']
                    })
                }
                // 属性值规则
            } else if (valueRuleType == 'DEFVALUERULE' && deRule) {
                // 有值项的情况，校验值项的值
                let editItem = this.controlInstance.getEditColumnByName(gridEditItemName);
                let valueName = editItem?.valueItemName || gridEditItemName;
                staticRules[gridEditItemName].push({
                    validator: (rule: any, value: any, callback: any, source: any) => {
                        // 空值时不校验
                        if (Util.isEmpty(source[valueName])) {
                            return true
                        }
                        const { isPast, infoMessage } = Verify.verifyDeRules(valueName, source, deRule.getPSDEFVRGroupCondition);
                        if (!isPast) {
                            callback(new Error(infoMessage || deRule.ruleInfo));
                        }
                        return true;
                    },
                    trigger: ['change', 'blur']
                })
            }
        })

        // 初始化非空值规则和数据类型值规则
        this.rules = {};
        const { allEditColumns, appDataEntity } = this.controlInstance;
        if (allEditColumns?.length > 0) {
            for (const editColumn of allEditColumns) {
                let editorRules = [];
                if (editColumn && editColumn.editorInstance) {
                    editorRules = Verify.buildVerConditions(editColumn.editorInstance);
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
     * 表格ref名称
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
     * selectedData选中值变化
     *
     * @param {*} newVal
     * @memberof TreeControlBase
     */
    public onSelectedDataValueChange(newVal: any) {
        this.selections = [];
        if (this.selectedData) {
            const refs: any = this.$refs;
            if (refs.multipleTable) {
                refs.multipleTable.clearSelection();
                JSON.parse(this.selectedData).forEach((selection: any) => {
                    let selectedItem = this.items.find((item: any) => {
                        return Object.is(item.srfkey, selection.srfkey);
                    });
                    if (selectedItem) {
                        this.rowClick(selectedItem);
                    }
                });
            }
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
        this.gridRowActiveMode = newVal.gridRowActiveMode;
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
            this.service = new AppGridService(this.controlInstance);
        }
        this.isSingleSelect = this.staticProps.isSingleSelect === true ? true : this.staticProps.isSingleSelect === false ? false : this.controlInstance.singleSelect;
        this.autosave = this.controlInstance.autoLoad;
        this.fetchAction = this.controlInstance.fetchAction ? this.controlInstance.fetchAction : "FetchDefault";
        this.WFSubmitAction = this.controlInstance.WFSubmitAction,
        this.WFStartAction = this.controlInstance.WFStartAction,
        this.isNoSort = this.controlInstance.noSort;
        this.gridRefName = `${this.controlInstance.codeName.toLowerCase()}grid`;
        this.aggMode = this.controlInstance.aggMode;
        this.aggAction = this.controlInstance.aggAction;
        this.allColumnsInstance = this.controlInstance.allColumns;
        this.isEnableGroup = (this.controlInstance.enableGroup && this.controlInstance.groupField) ? true : false;
        //开启分组
        if (this.isEnableGroup) {
            this.groupMode = this.controlInstance.groupMode;
            this.groupAppField = this.controlInstance.groupField;
            this.codelistTag = this.controlInstance.groupCodeList?.codeName;
            this.codelistType = this.controlInstance.groupCodeList?.codeListType;
            this.codelist = this.controlInstance.groupCodeList;
            this.groupAppFieldCodelistTag = this.controlInstance.groupField?.codeList?.codeName;
            this.groupAppFieldCodelistType = this.controlInstance.groupField?.codeList?.codeListType;
            this.groupAppFieldCodelist = this.controlInstance.groupField?.codeList;
        }
        this.codelistType = this.controlInstance.groupCodeList?.type ? this.controlInstance.groupCodeList?.type : 'STATIC';
        this.initAllColumns();
        this.initColumnKeyName();
        this.initAllExportColumns();
        this.initRules();
    }

    /**
     * 获取界面行为权限状态
     *
     * @memberof GridControlBase
     */
    public getActionState(data: any) {
        let tempActionModel: any = JSON.parse(JSON.stringify(this.actionModel));
        let targetData: any = this.transformData(data);
        ViewTool.calcActionItemAuthState(targetData, tempActionModel, this.appUIService);
        return tempActionModel;
    }

    /**
     * 初始化界面行为模型
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public initCtrlActionModel() {
        if (this.controlInstance && this.controlInstance.allColumns && this.controlInstance.allColumns.length > 0) {
            let UAGridColumn: any = this.controlInstance.allColumns.find((item: any) => {
                return item.columnType === "UAGRIDCOLUMN";
            })
            if (UAGridColumn && UAGridColumn.getPSDEUIActionGroup && UAGridColumn.getPSDEUIActionGroup.getPSUIActionGroupDetails && UAGridColumn.getPSDEUIActionGroup.getPSUIActionGroupDetails.length > 0) {
                UAGridColumn.getPSDEUIActionGroup.getPSUIActionGroupDetails.forEach((element: any) => {
                    if (element?.getPSUIAction) {
                        const appUIAction: any = Util.deepCopy(element.getPSUIAction);
                        this.actionModel[appUIAction.uIActionTag] = Object.assign(appUIAction, { disabled: false, visabled: true, getNoPrivDisplayMode: appUIAction.getNoPrivDisplayMode ? appUIAction.getNoPrivDisplayMode : 6 });
                    }
                });
            }
            // 具有内置界面行为的数据表格列
            let UIActionColumn: any = this.controlInstance.allColumns.find((item: any) => {
                return item.columnType === "DEFGRIDCOLUMN" && item.hasOwnProperty('getPSDEUIAction');
            })
            if (UIActionColumn) {
                const appUIAction: any = Util.deepCopy(UIActionColumn.getPSDEUIAction);
                this.actionModel[appUIAction.uIActionTag] = Object.assign(appUIAction, { disabled: false, visabled: true, getNoPrivDisplayMode: appUIAction.getNoPrivDisplayMode ? appUIAction.getNoPrivDisplayMode : 6 });
            }
        }
    }

    /**
     * 部件刷新
     *
     * @param {*} args
     * @memberof GridControlBase
     */
    public refresh(args?: any): void {
        this.load();
    }


    /**
     * 表格行编辑项校验
     *
     * @param {string} property 属性名
     * @param {*} data 行数据
     * @param {number} rowIndex 行索引
     * @returns Promise<any>
     * 
     * @memberof GridControlBase
     */
    public validate(property: string, data: any, rowIndex: number): Promise<any> {
        return new Promise((resolve, reject) => {
            Util.validateItem(property, data, this.rules).then(() => {
                this.gridItemsModel[rowIndex][property].setError(null);
                resolve(true);
            }).catch(({ errors, fields }: any) => {
                this.gridItemsModel[rowIndex][property].setError(errors[0].message);
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
     * @param {*} [arg={}]
     * @memberof GridControlBase
     */
    public load(opt: any = {}, pageReset: boolean = false): void {
        if (!this.fetchAction) {
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: '${view.getName()}' + (this.$t('app.gridpage.notConfig.fetchAction') as string) });
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
        if (!this.isNoSort && !Object.is(this.minorSortDir, '') && !Object.is(this.minorSortPSDEF, '')) {
            const sort: string = this.minorSortPSDEF + "," + this.minorSortDir;
            Object.assign(page, { sort: sort });
        }
        Object.assign(arg, page);
        const parentdata: any = {};
        this.ctrlEvent({ controlname: this.controlInstance.name, action: "beforeload", data: parentdata });
        Object.assign(arg, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
        Object.assign(tempViewParams, JSON.parse(JSON.stringify(this.viewparams)));
        Object.assign(arg, { viewparams: tempViewParams });
        this.ctrlBeginLoading();
        const post: Promise<any> = this.service.search(this.fetchAction, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (!response.status || response.status !== 200) {
                if (response.errorMessage) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.errorMessage });
                }
                return;
            }
            const data: any = response.data;
            this.totalRecord = response.total;
            this.items = JSON.parse(JSON.stringify(data));
            // 清空selections,gridItemsModel
            this.selections = [];
            this.gridItemsModel = [];
            this.items.forEach(() => { this.gridItemsModel.push(this.getGridRowModel()) });
            this.items.forEach((item: any) => {
                Object.assign(item, this.getActionState(item));
            });
            this.ctrlEvent({ controlname: this.controlInstance.name, action: "load", data: this.items });
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
                                this.ctrlEvent({ controlname: _this.controlInstance.name, action: "selectionchange", data: [emptyItem] });
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
                this.getAggData();
            }
            if (this.isEnableGroup) {
                this.group();
            }
        }).catch((response: any) => {
            this.ctrlEndLoading();
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.errorMessage });
        });
    }


    /**
     * 删除
     *
     * @param {any[]} datas
     * @returns {Promise<any>}
     * @memberof GridControlBase
     */
    public async remove(datas: any[]): Promise<any> {
        if (!this.removeAction) {
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: '${view.getName()}' + (this.$t('app.gridpage.notConfig.removeAction') as string) });
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
            // let srfmajortext = record.${ctrl.getPSAppDataEntity().getMajorPSAppDEField().getName()?lower_case};
            let srfmajortext = "";
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
            dataInfo = dataInfo + ' ' + (this.$t('app.gridpage.totle') as string) + _datas.length + (this.$t('app.gridpage.records') as string) + (this.$t('app.gridpage.data') as string);
        } else {
            dataInfo = ' ... ' + (this.$t('app.gridpage.totle') as string) + _datas.length + (this.$t('app.gridpage.records') as string) + (this.$t('app.gridpage.data') as string);
        }

        const removeData = () => {
            let keys: any[] = [];
            _datas.forEach((data: any) => {
                keys.push(data.srfkey);
            });
            let _removeAction = keys.length > 1 ? 'removeBatch' : this.removeAction;
            let _keys = keys.length > 1 ? keys : keys[0];
            const context: any = JSON.parse(JSON.stringify(this.context));
            const post: Promise<any> = this.service.delete(_removeAction, Object.assign(context, { [this.controlInstance.appDeCodeName]: _keys }), Object.assign({ [this.controlInstance.appDeCodeName]: _keys }, { viewparams: this.viewparams }), this.showBusyIndicator);
            return new Promise((resolve: any, reject: any) => {
                this.ctrlBeginLoading();
                post.then((response: any) => {
                    this.ctrlEndLoading();
                    if (!response || response.status !== 200) {
                        this.$Notice.error({ title: '', desc: (this.$t('app.gridpage.delDataFail') as string) + ',' + response.info });
                        return;
                    } else {
                        this.$Notice.success({ title: '', desc: (this.$t('app.gridpage.delSuccess') as string) });
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
                    this.ctrlEvent({ controlname: this.controlInstance.name, action: "remove", data: {} });
                    this.selections = [];
                    resolve(response);
                }).catch((response: any) => {
                    this.ctrlEndLoading();
                    if (response && response.status === 401) {
                        return;
                    }
                    if (!response || !response.status || !response.data) {
                        this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.sysException') as string) });
                        reject(response);
                        return;
                    }
                    reject(response);
                });
            });
        }

        dataInfo = dataInfo.replace(/[null]/g, '').replace(/[undefined]/g, '');
        this.$Modal.confirm({
            title: (this.$t('app.commonWords.warning') as string),
            content: (this.$t('app.gridpage.confirmDel') as string) + ' ' + dataInfo + '，' + (this.$t('app.gridpage.notRecoverable') as string),
            onOk: () => {
                removeData();
            },
            onCancel: () => { }
        });
        return removeData;
    }


    /**
     * 批量添加
     *
     * @param {*} [arg={}]
     * @memberof GridControlBase
     */
    public addBatch(arg: any = {}): void {
        if (!this.fetchAction) {
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: '${view.getName()}' + (this.$t('app.gridpage.notConfig.fetchAction') as string) });
            return;
        }
        if (!arg) {
            arg = {};
        }
        console.error((this.$t('app.gridpage.notBatch') as string));
    }

    /**
     * 导入模型
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public importDataModel: any = {};

    /**
     * 数据导入
     *
     * @param {*} data
     * @memberof GridControlBase
     */
    public importExcel(data: any = {}): void {
        let _this: any = this;
        if (Object.keys(this.importDataModel).length == 0) {
            this.$Notice.warning({ 'title': (this.$t("app.utilview.warning") as string), 'desc': (this.$t("app.utilview.info") as string) });
            return;
        }
        const view: any = {
            viewname: 'app-data-upload',
            title: this.$t("app.utilview.importview"),
            width: 900,
            height: 700
        }
        let container: Subject<any> = _this.$appmodal.openModal(view, JSON.parse(JSON.stringify(this.context)), this.importDataModel);
        container.subscribe((result: any) => {
            if (Object.is(result.ret, 'OK')) {
                this.refresh(result.datas);
            }
        });
    }

    /**
     * 数据导出
     *
     * @param {*} data
     * @memberof GridControlBase
     */
    public exportExcel(data: any = {}): void {
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
            _this.$export.exportExcel().then((excel: any) => {
                excel.export_json_to_excel({
                    header: tHeader, //表头 必填
                    data, //具体数据 必填
                    filename: `${this.controlInstance.appDataEntity.logicName}` + (this.$t('app.gridpage.grid') as string), //非必填
                    autoWidth: true, //非必填
                    bookType: "xlsx" //非必填
                });
            });
        };
        const page: any = {};
        // 设置page，size
        if (Object.is(data.type, 'maxRowCount')) {
            Object.assign(page, { page: 0, size: data.maxRowCount });
        } else if (Object.is(data.type, 'activatedPage')) {
            if (this.allExportColumns?.length > 0) {
                Object.assign(page, { page: this.curPage - 1, size: this.limit });
            } else {
                try {
                    doExport(JSON.parse(JSON.stringify(this.items)));
                } catch (error) {
                    console.error(error);
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
        this.ctrlEvent({ controlname: this.controlInstance.name, action: "beforeload", data: parentdata });
        Object.assign(arg, parentdata);
        const post: Promise<any> = this.allExportColumns?.length > 0 ?
            this.service.searchDEExportData(this.fetchAction, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator) :
            this.service.search(this.fetchAction, JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
        this.ctrlBeginLoading();
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (!response || response.status !== 200) {
                this.$Notice.error({ title: '', desc: (this.$t('app.gridpage.exportFail') as string) + ',' + response.info });
                return;
            }
            try {
                doExport(JSON.parse(JSON.stringify(response.data)));
            } catch (error) {
                console.error(error);
            }
        }).catch((response: any) => {
            this.ctrlEndLoading();
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error({ title: '', desc: (this.$t('app.gridpage.exportFail') as string) });
        });
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
        let exportItems: Array<any> = this.controlInstance.dataExport?.getPSDEDataExportItems;
        if (exportItems?.length > 0) {
            exportItems.forEach((item: any) => {
                const codeList = item.getPSCodeList;
                if (codeList) {
                    let codeListColumn = {
                        name: item.name.toLowerCase(),
                        srfkey: codeList.codeName,
                        codelistType: codeList.codeListType,
                    }
                    if (Object.is('STR', codeList.orMode)) {
                        Object.assign(codeListColumn, {
                            renderMode: 'string',
                            textSeparator: codeList.textSeparator,
                            valueSeparator: codeList.valueSeparator,
                        })
                    } else if (Object.is('NUM', codeList.orMode)) {
                        Object.assign(codeListColumn, {
                            renderMode: 'number',
                            textSeparator: codeList.textSeparator,
                            valueSeparator: ',',
                        })
                    } else {
                        Object.assign(codeListColumn, {
                            renderMode: 'other',
                            textSeparator: '、',
                            valueSeparator: ',',
                        })
                    }
                    codelistColumns.push(codeListColumn);
                }
            })
        } else {
            if (this.controlInstance.allColumns.length > 0) {
                this.controlInstance.allColumns.forEach((item: any) => {
                    if (!item.hideDefault && item.name != '' && item.codeList && item.cLConvertMode == "FRONT") {
                        let codeListColumn = {
                            name: item.name.toLowerCase(),
                            srfkey: item.codeList.codeName,
                            codelistType: item.codeList.codeListType,
                        }
                        if (Object.is('STR', item.codeList.orMode)) {
                            Object.assign(codeListColumn, {
                                renderMode: 'string',
                                textSeparator: item.codeList.textSeparator,
                                valueSeparator: item.codeList.valueSeparator,
                            })
                        } else if (Object.is('NUM', item.codeList.orMode)) {
                            Object.assign(codeListColumn, {
                                renderMode: 'number',
                                textSeparator: item.codeList.textSeparator,
                                valueSeparator: ',',
                            })
                        } else {
                            Object.assign(codeListColumn, {
                                renderMode: 'other',
                                textSeparator: '、',
                                valueSeparator: ',',
                            })
                        }
                        codelistColumns.push(codeListColumn);
                    }
                })
            }
        }
        let _this = this;
        for (const codelist of codelistColumns) {
            let items: any = await this.codeListService.getDataItems({ tag: codelist.srfkey, type: codelist.codelistType, data: codelist, context: this.context, viewparams: this.viewparams });
            jsonData.forEach((row: any) => {
                row[codelist.name] = _this.getCodelistValue(items, row[codelist.name], codelist);
            })
        }
        return jsonData.map((v: any) => filterVal.map((j: any) => v[j]))
    }


    /**
     * 解析代码表和vlaue，设置items
     *
     * @public
     * @param {any[]} items 代码表数据
     * @param {*} value
     * @returns {*}
     * @memberof GridControlBase
     */
    public getCodelistValue(items: any[], value: any, codelist: any,) {
        if (!value && value !== 0 && value !== false) {
            return this.$t('codelist.' + codelist.srfkey + '.empty');
        }
        if (items) {
            let result: any = [];
            if (Object.is(codelist.renderMode, "number")) {
                items.map((_item: any, index: number) => {
                    const nValue = parseInt((value as any), 10);
                    const codevalue = _item.value;
                    if ((parseInt(codevalue, 10) & nValue) > 0) {
                        result.push(_item);
                    }
                });
            } else if (Object.is(codelist.renderMode, "string")) {
                const arrayValue: Array<any> = (value as any).split(codelist.valueSeparator);
                arrayValue.map((value: any, index: number) => {
                    result.push([]);
                    let values: any[] = Object.is(Util.typeOf(value), 'number') ? [value] : [...(value as any).split(codelist.valueSeparator)];
                    values.map((val: any, num: number) => {
                        const item = this.getItem(items, val, codelist);
                        if (item) {
                            result[index].push(item);
                        }
                    });
                });
            } else {
                let values: any[] = Object.is(Util.typeOf(value), 'number') ? [value] : [...(value as any).split(codelist.valueSeparator)];
                values.map((value: any, index: number) => {
                    const item = this.getItem(items, value, codelist);
                    if (item) {
                        result.push(item);
                    }
                });
            }
            // 设置items
            if (result.length != 0) {
                return result.join(codelist.valueSeparator);
            } else {
                return value;
            }
        }
    }

    /**
     * 获取代码项
     *
     * @public
     * @param {any[]} items
     * @param {*} value
     * @returns {*}
     * @memberof GridControlBase
     */
    public getItem(items: any[], value: any, codelist: any): any {
        const arr: Array<any> = items.filter(item => { return item.value == value });
        if (arr.length !== 1) {
            return undefined;
        }
        if (Object.is(codelist.codelistType, 'STATIC')) {
            return this.$t('codelist.' + codelist.srfkey + '.' + arr[0].value);
        } else {
            return arr[0].text;
        }
    }

    /**
     * 获取所有列成员模型
     *
     * @param {IBizGridModel} gridInstance 表格部件实例对象
     */
    public initAllColumns() {
        this.allColumns = [];
        let columnsInstanceArr: Array<any> = this.controlInstance.allColumns;
        if (columnsInstanceArr && columnsInstanceArr.length > 0) {
            for (const columnInstance of columnsInstanceArr) {
                let editItem: any = this.controlInstance.getEditColumnByName(columnInstance.name.toLowerCase());
                //表格列
                const column = {
                    name: columnInstance.name.toLowerCase(),
                    label: columnInstance.caption,
                    //todo  国际化标识待补充(需求部件实体信息)
                    langtag: '',
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

    /**
     * 初始化主键属性列名
     * 
     * @memberof GridControlBase
     */
    public initColumnKeyName() {
        this.columnKeyName = "";
        const dataItem: any = this.controlInstance.allDataItems.find((data: any) => {
            return Object.is('srfkey', data.name)
        })
        if (dataItem) {
            const srfKeyColumn: any = this.allColumnsInstance.find((columnInstance: any) => {
                return Object.is(columnInstance.columnType, 'DEFGRIDCOLUMN') && Object.is(columnInstance.getPSAppDEField.codeName, dataItem.getPSAppDEField.codeName);
            })
            if (srfKeyColumn) {
                this.columnKeyName = srfKeyColumn.name.toLowerCase();
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
        const exportModel = this.controlInstance.dataExport;
        if (exportModel && exportModel.getPSDEDataExportItems && exportModel.getPSDEDataExportItems.length > 0) {
            exportModel.getPSDEDataExportItems.forEach((item: any) => {
                this.allExportColumns.push({
                    name: item.name.toLowerCase(),
                    label: item.caption,
                    langtag: "",
                    show: true
                })
            })
        }
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
     * 获取选中行胡数据
     *
     * @returns {any[]}
     * @memberof GridControlBase
     */
    public getSelection(): any[] {
        return this.selections;
    }

    /**
     * 行双击事件
     *
     * @param {*} $event
     * @returns {void}
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
        this.selections = [];
        this.selections.push(JSON.parse(JSON.stringify($event)));
        const table: any = (this.$refs as any)[this.gridRefName];
        if (table) {
            table.clearSelection();
            table.toggleRowSelection($event);
        }
        this.ctrlEvent({ controlname: this.controlInstance.name, action: "rowdblclick", data: this.selections });
        this.ctrlEvent({ controlname: this.controlInstance.name, action: "selectionchange", data: this.selections });
    }

    /**
     * 分组方法
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
            allGroup = await this.codeListService.getDataItems({ tag: this.codelistTag, type: this.codelistType, data: this.codelist });
        }
        if (this.groupAppFieldCodelistTag && this.groupAppFieldCodelistType) {
            allGroupField = await this.codeListService.getDataItems({ tag: this.groupAppFieldCodelistTag, type: this.groupAppFieldCodelistType, data: this.groupAppFieldCodelist });
        }
        if (!this.items || this.items.length == 0) {
            return;
        }
        if (allGroup.length == 0) {
            console.warn("分组数据无效");
            this.items.forEach((item: any, index: number) => {
                Object.assign(item, {
                    groupById: index + 1,
                    group: ""
                });
                otherItems.push(item);
            })
        }
        allGroup.forEach((group: any, index: number) => {
            let children: Array<any> = [];
            this.items.forEach((item: any, _index: number) => {
                Object.assign(item, {
                    groupById: Number((index + 1) * 3 + (_index + 1) * 2),
                    group: ""
                });
                const field = allGroupField && allGroupField.length > 0 ? group.label : group.value;
                if (Object.is(item[this.groupAppField], field)) {
                    children.push(item);
                } else {
                    otherItems.push(item);
                }
            })
            const tree = this.initTree(group.label, index, children);
            groupTree.push(tree);
        })
        if (otherItems.length > 0) {
            otherItems = [...new Set(otherItems)];
            const tree = this.initTree("其他", allGroup.length + 1, otherItems);
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
            console.warn("分组数据无效");
        }
        // 组装数据
        allGroup.forEach((group: any, groupIndex: number) => {
            let children: Array<any> = [];
            this.items.forEach((item: any, itemIndex: number) => {
                if (Object.is(group, item[this.groupAppField])) {
                    item.groupById = Number((groupIndex + 1) * 100 + (itemIndex + 1) * 1);
                    item.group = '';
                    children.push(item);
                }
            });
            group = group ? group : this.$t('app.gridpage.other');
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
            groupById: Number((groupIndex + 1) * 100),
            group: group,
            children: children,
        }
        if (children) {
            tree["hasChildren"] = true;
        }
        let { allColumns } = this.controlInstance;
        if (allColumns?.length > 0) {
            for (let singleColumn of allColumns) {
                if (singleColumn.columnType && singleColumn.columnType == "UAGRIDCOLUMN") {
                    if (singleColumn.getPSDEUIActionGroup && singleColumn.getPSDEUIActionGroup.getPSUIActionGroupDetails && singleColumn.getPSDEUIActionGroup.getPSUIActionGroupDetails.length > 0) {
                        singleColumn.getPSDEUIActionGroup.getPSUIActionGroupDetails.forEach((element: any) => {
                            tree[element.getPSUIAction.uIActionTag] = { visabled: false };
                        });
                    }
                } else if (singleColumn.columnType && singleColumn.columnType == "DEFGRIDCOLUMN") {
                    if (singleColumn.hasOwnProperty('getPSDEUIAction')) {
                        tree[singleColumn.getPSDEUIAction.uIActionTag] = { visabled: false };
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
            this.selections = [...JSON.parse(JSON.stringify(selection))];
        }
        this.ctrlEvent({ controlname: this.controlInstance.name, action: "selectionchange", data: this.selections });
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
     * @param {*} $event
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
            this.selections = [...JSON.parse(JSON.stringify(selection))];
        }
        this.ctrlEvent({ controlname: this.controlInstance.name, action: "selectionchange", data: this.selections });
    }

    /**
     * 表格行选中样式
     *
     * @param {{ row: any, rowIndex: any }} { row, rowIndex }
     * @returns {string}
     * @memberof MainBase
     */
    public onRowClassName({ row, rowIndex }: { row: any, rowIndex: any }): string {
        const index = this.selections.findIndex((select: any) => Object.is(select.srfkey, row.srfkey));
        return index !== -1 ? 'grid-row-select' : '';
    }

    /**
     * 行单击选中
     *
     * @param {*} $event
     * @returns {void}
     * @memberof GridControlBase
     */
    public rowClick($event: any, ifAlways: boolean = false): void {
        // 分组行跳过
        if ($event && $event.children) {
            return;
        }
        if (!ifAlways && (!$event || this.actualIsOpenEdit)) {
            return;
        }
        if (this.stopRowClick) {
            this.stopRowClick = false;
            return;
        }
        if (this.isSingleSelect) {
            this.selections = [];
        }
        // 已选中则删除，没选中则添加
        let selectIndex = this.selections.findIndex((item: any) => {
            return Object.is(item[this.controlInstance.appDeCodeName], $event[this.controlInstance.appDeCodeName]);
        });
        if (Object.is(selectIndex, -1)) {
            this.selections.push(JSON.parse(JSON.stringify($event)));
        } else {
            this.selections.splice(selectIndex, 1);
        }

        const table: any = (this.$refs as any)[this.gridRefName];
        if (table) {
            if (this.isSingleSelect) {
                table.clearSelection();
                table.setCurrentRow($event);
            } else {
                table.toggleRowSelection($event);
            }
        }
        this.ctrlEvent({ controlname: this.controlInstance.name, action: "selectionchange", data: this.selections });
    }

    /**
     * 获取对应行class
     *
     * @param {*} $args row 行数据，rowIndex 行索引
     * @returns {void}
     * @memberof GridControlBase
     */
    public getRowClassName(args: { row: any, rowIndex: number }) {
        const appde = this.controlInstance.appDataEntity;
        let isSelected = this.selections.some((item: any) => {
            return Object.is(item[appde?.codeName.toLowerCase()], args.row[appde?.codeName.toLowerCase()]);
        });
        return isSelected ? "grid-selected-row" : "";
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
     * @param {*} $event
     * @returns {void}
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
     * @param {*} $event
     * @returns {void}
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
     * @param {{ column: any, prop: any, order: any }} { column, prop, order }
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
    public getAggData() {
        this.ctrlBeginLoading();
        this.service.getAggData(this.aggAction, JSON.parse(JSON.stringify(this.context)), this.showBusyIndicator).then((response: any) => {
            this.ctrlEndLoading();
            if (!response.status || response.status !== 200) {
                if (response.errorMessage) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.errorMessage });
                }
                return;
            }
            this.remoteData = response.data;
            this.isDisplay = true;
        }).catch((response: any) => {
            this.ctrlEndLoading();
            if (response && response.status === 401) {
                return;
            }
            this.remoteData = {};
            this.isDisplay = true;
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.errorMessage });
        })
    }

    /**
     * 界面行为
     *
     * @param {*} row
     * @param {*} tag
     * @param {*} $event
     * @memberof GridControlBase
     */
    public uiAction(row: any, tag: any, $event: any) {

    }

    /**
     * 设置列状态
     *
     * @memberof GridControlBase
     */
    public setColState() {
        const { appDataEntity: appde, codeName, name } = this.controlInstance;
        const _data: any = localStorage.getItem(`${appde.codeName.toLowerCase()}_${codeName.toLowerCase()}_${name.toLowerCase()}`);
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
        const { appDataEntity: appde, codeName, name } = this.controlInstance;
        localStorage.setItem(`${appde.codeName.toLowerCase()}_${codeName.toLowerCase()}_${name.toLowerCase()}`, JSON.stringify(this.allColumns));
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
     * 保存
     *
     * @param {*} $event
     * @returns {Promise<any>}
     * @memberof GridControlBase
     */
    public async save(args: any[], params?: any, $event?: any, xData?: any) {
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
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: this.errorMessages[0] });
            } else {
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.rulesException') as string) });
            }
            return [];
        }
        let successItems: any = [];
        let errorItems: any = [];
        let errorMessage: any = [];
        for (const item of _this.items) {
            try {
                if (Object.is(item.rowDataState, 'create')) {
                    if (!this.createAction) {
                        this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: '${view.getName()}' + (this.$t('app.gridpage.notConfig.createAction') as string) });
                    } else {
                        Object.assign(item, { viewparams: this.viewparams });
                        this.ctrlBeginLoading();
                        let response = await this.service.add(this.createAction, JSON.parse(JSON.stringify(this.context)), item, this.showBusyIndicator);
                        this.ctrlEndLoading();
                        successItems.push(JSON.parse(JSON.stringify(response.data)));
                    }
                } else if (Object.is(item.rowDataState, 'update')) {
                    if (!this.updateAction) {
                        this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: '${view.getName()}' + (this.$t('app.gridpage.notConfig.updateAction') as string) });
                    } else {
                        Object.assign(item, { viewparams: this.viewparams });
                        if (item[this.controlInstance.appDeCodeName.toLowerCase()]) {
                            Object.assign(this.context, { [this.controlInstance.appDeCodeName.toLowerCase()]: item[this.controlInstance.appDeCodeName.toLowerCase()] });
                        }
                        this.ctrlBeginLoading();
                        let response = await this.service.update(this.updateAction, JSON.parse(JSON.stringify(this.context)), item, this.showBusyIndicator);
                        this.ctrlEndLoading();
                        successItems.push(JSON.parse(JSON.stringify(response.data)));
                    }
                }
            } catch (error) {
                this.ctrlEndLoading();
                errorItems.push(JSON.parse(JSON.stringify(item)));
                errorMessage.push(error);
            }
        }
        this.ctrlEvent({ controlname: this.controlInstance.name, action: "save", data: successItems });
        this.refresh();
        if (errorItems.length === 0 && successItems.length > 0) {
            this.$Notice.success({ title: '', desc: (this.$t('app.commonWords.saveSuccess') as string) });
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
                            this.$Notice.error({
                                title: (this.$t('app.commonWords.createFailed') as string),
                                desc: (desc ? desc.label : '') + " : " + item[name] + (this.$t('app.commonWords.isExist') as string) + '!',
                            });
                        } else {
                            this.$Notice.error({
                                title: (this.$t('app.commonWords.createFailed') as string),
                                desc: errorMessage[index].data.message ? errorMessage[index].data.message : (this.$t('app.commonWords.sysException') as string),
                            });
                        }
                    } else if (Object.is(errorMessage[index].data.errorKey, 'DuplicateKeyException')) {
                        if (Util.isEmpty(this.columnKeyName)) {
                            this.$Notice.error({
                                title: (this.$t('app.commonWords.saveFailed') as string),
                                desc: errorMessage[index].data.message ? errorMessage[index].data.message : (this.$t('app.commonWords.sysException') as string),
                            });
                        } else {
                            let name: string = this.service.getNameByProp(this.columnKeyName);
                            if (name) {
                                let desc: any = this.allColumns.find((column: any) => {
                                    return Object.is(column.name, name);
                                });
                                this.$Notice.error({
                                    title: (this.$t('app.commonWords.createFailed') as string),
                                    desc: (desc ? desc.label : '') + " : " + item[name] + (this.$t('app.commonWords.isExist') as string) + '!',
                                });
                            }
                        }
                    } else {
                        this.$Notice.error({
                            title: (this.$t('app.commonWords.saveFailed') as string),
                            desc: errorMessage[index].data.message ? errorMessage[index].data.message : (this.$t('app.commonWords.sysException') as string),
                        });
                    }
                } else {
                    this.$Notice.error({ title: (this.$t('app.commonWords.saveFailed') as string), desc: (item[this.majorInfoColName] ? item[this.majorInfoColName] : "") + (this.$t('app.commonWords.saveFailed') as string) + '!' });
                }
            });
        }
        return successItems;
    }

    /**
     * 新建行
     *
     * @param {*} $event
     * @returns {void}
     * @memberof GridControlBase
     */
    public newRow(args: any[], params?: any, $event?: any, xData?: any): void {
        if (!this.loaddraftAction) {
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: '${view.getName()}' + (this.$t('app.gridpage.notConfig.loaddraftAction') as string) });
            return;
        }
        let _this = this;
        Object.assign(args[0], { viewparams: this.viewparams });
        let post: Promise<any> = this.service.loadDraft(this.loaddraftAction, JSON.parse(JSON.stringify(this.context)), args[0], this.showBusyIndicator);
        this.ctrlBeginLoading();
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (!response.status || response.status !== 200) {
                if (response.errorMessage) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.errorMessage });
                }
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
            this.ctrlEndLoading();
            if (response && response.status === 401) {
                return;
            }
            if (!response || !response.status || !response.data) {
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.sysException') as string) });
                return;
            }
        });
    }

    /**
     * 表格编辑项值变更
     *  
     * @param row 行数据
     * @param {{ name: string, value: any }} $event
     * @returns {void}
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
     * @returns {void}
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
        const { allEditColumns } = this.controlInstance;
        if (allEditColumns?.length > 0) {
            allEditColumns.forEach((item: any) => {
                if (item.getPSDEGridEditItemUpdate?.modelref && Object.is(property, item.name)) {
                    const editItemUpdate: any = this.controlInstance.allGridEditItemUpdates.find((gridEditItemUpdate: any) => {
                        return item.getPSDEGridEditItemUpdate.id = gridEditItemUpdate.codeName;
                    })
                    if (editItemUpdate) {
                        let details: string[] = [];
                        editItemUpdate.getPSDEGEIUpdateDetails.forEach((updateDetail: any) => {
                            details.push(updateDetail.name);
                        })
                        const showBusyIndicator = editItemUpdate.hasOwnProperty('showBusyIndicator') ? editItemUpdate.showBusyIndicator : true;
                        this.updateGridEditItem(editItemUpdate.getPSAppDEMethod.id, row, details, showBusyIndicator);
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
     * @returns {void}
     * @memberof GridControlBase
     */
    public updateGridEditItem(mode: string, data: any = {}, updateDetails: string[], showloading?: boolean): void {
        if (!mode || (mode && Object.is(mode, ''))) {
            return;
        }
        let tempContext: any = Util.deepCopy(this.context);
        if (!Util.isEmpty(this.columnKeyName)) {
            Object.is(tempContext, { [this.controlInstance.appDeCodeName.toLowerCase()]: data[this.columnKeyName] });
        }
        const arg: any = JSON.parse(JSON.stringify(data));
        Object.assign(arg, { viewparams: this.viewparams });
        const post: Promise<any> = this.service.frontLogic(mode, JSON.parse(JSON.stringify(tempContext)), arg, showloading);
        this.ctrlBeginLoading();
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (!response || response.status !== 200) {
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.gridpage.formitemFailed') as string) });
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
            this.ctrlEndLoading();
            if (response && response.status === 401) {
                return;
            }
            if (!response || !response.status || !response.data) {
                this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.sysException') as string) });
                return;
            }
        });
    }

    /**
     * 新建默认值
     * @param {*}  row 行数据
     * @memberof GridControlBase
     */
    public createDefault(row: any) {
        const { allEditColumns: editItems } = this.controlInstance;
        if (editItems?.length > 0) {
            for (const item of editItems) {
                const property = item.codeName.toLowerCase();
                //新建默认值(具备类型)
                if (item.createDVT && row.hasOwnProperty(property)) {
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
                            row[property] = Util.dateFormat(new Date());
                            break;
                        case "PARAM":
                            if (item.createDV) {
                                row[property] = this.computeDefaultValueWithParam("CREATE", item.createDV, row);
                            }
                            break;
                    }
                } else if (item.createDV && row.hasOwnProperty(property)) {
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
        const { allEditColumns: editItems } = this.controlInstance;
        if (editItems?.length > 0) {
            for (const item of editItems) {
                const property = item.codeName.toLowerCase();
                if (item.updateDVT && row.hasOwnProperty(property)) {
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
                            row[property] = Util.dateFormat(new Date());
                            break;
                        case "PARAM":
                            if (item.updateDV) {
                                row[property] = this.computeDefaultValueWithParam("CREATE", item.updateDV, row);
                            }
                            break;
                    }
                } else if (item.updateDV && row.hasOwnProperty(property)) {
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
     * 工作流提交
     *
     * @param {*} [data={}]
     * @param {*} [localdata={}]
     * @returns {Promise<any>}
     * @memberof GridControlBase
     */
    public async submitbatch(data: any, localdata: any): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            const _this: any = this;
            const arg: any = data;
            const result: Promise<any> = this.service.submitbatch(_this.WFSubmitAction, JSON.parse(JSON.stringify(this.context)), arg, localdata, this.showBusyIndicator);
            this.ctrlBeginLoading();
            result.then((response: any) => {
                this.ctrlEndLoading();
                if (!response || response.status !== 200) {
                    if (response.data) {
                        this.$Notice.error({ title: '', desc: (this.$t('app.formpage.workflow.submiterror') as string) + ', ' + response.data.message });
                    }
                    return;
                }
                this.$Notice.info({ title: '', desc: (this.$t('app.formpage.workflow.submitsuccess') as string) });
                resolve(response);
            }).catch((response: any) => {
                this.ctrlEndLoading();
                if (response && response.status && response.data) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.data.message });
                    reject(response);
                    return;
                }
                if (!response || !response.status || !response.data) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: (this.$t('app.commonWords.sysException') as string) });
                    reject(response);
                    return;
                }
                reject(response);
            });
        })
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
        const { allColumns } = this.controlInstance;
        if (this.actualIsOpenEdit && allColumns?.length > 0) {
            allColumns.forEach((item: any) => {
                const editItem: any = this.controlInstance.getEditColumnByName(item.name.toLowerCase());
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
        if (Object.is(this.aggMode, "PAGE")) {
            const { columns, data } = param;
            const sums: Array<any> = [];
            columns.forEach((column: any, index: number) => {
                if (index === 0) {
                    sums[index] = (this.$t('app.gridpage.sum') as string);
                    return;
                }
                this.allColumnsInstance.forEach((columnInstance: any) => {
                    if (Object.is(columnInstance.columnType, "UAGRIDCOLUMN") && column.columnKey && Object.is(column.columnKey, columnInstance.name.toLowerCase())) {
                        sums[index] = '';
                        return;
                    }
                })
                const values = data.map((item: any) => Number(item[column.property]));
                if (!values.every((value: any) => isNaN(value))) {
                    this.allColumnsInstance.forEach((columnInstance: any) => {
                        if (Object.is(columnInstance.aggMode, "SUM")) {
                            if (Object.is(column.property, columnInstance.codeName.toLowerCase())) {
                                let tempData = values.reduce((prev: any, curr: any) => {
                                    const value = Number(curr);
                                    if (!isNaN(value)) {
                                        return prev + curr;
                                    } else {
                                        return prev;
                                    }
                                }, 0);
                                sums[index] = tempData.toFixed(3);
                            }
                        } else if (Object.is(columnInstance.aggMode, "AVG")) {
                            if (Object.is(column.property, columnInstance.codeName.toLowerCase())) {
                                let tempData = values.reduce((prev: any, curr: any) => {
                                    const value = Number(curr);
                                    if (!isNaN(value)) {
                                        return prev + curr;
                                    } else {
                                        return prev;
                                    }
                                }, 0);
                                sums[index] = (tempData / data.length).toFixed(2);
                            }
                        } else if (Object.is(columnInstance.aggMode, "MAX")) {
                            if (Object.is(column.property, columnInstance.codeName.toLowerCase())) {
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
                                sums[index] = tempData;
                            }
                        } else if (Object.is(columnInstance.aggMode, "MIN")) {
                            if (Object.is(column.property, columnInstance.codeName.toLowerCase())) {
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
                                sums[index] = tempData;
                            }
                        }
                    })
                } else {
                    sums[index] = '';
                }
            });
            return sums;
        } else if (Object.is(this.aggMode, "ALL")) {
            const { columns } = param;
            const sums: Array<any> = [];
            columns.forEach((column: any, index: number) => {
                if (index === 0) {
                    sums[index] = (this.$t('app.gridpage.sum') as string);
                    return;
                } else if (index === (columns.length - 1)) {
                    sums[index] = '';
                    return;
                } else {
                    sums[index] = '';
                    this.allColumnsInstance.forEach((columnInstance: any) => {
                        if (columnInstance.aggMode && Object.is(column.property, columnInstance.codeName.toLowerCase())) {
                            const value = Number(this.remoteData.columnInstance.codeName.toLowerCase());
                            if (!isNaN(value)) {
                                switch (columnInstance.aggMode) {
                                    case 'SUM':
                                        sums[index] = value;
                                        break;
                                    case 'AVG':
                                        sums[index] = value;
                                        break;
                                    case 'MAX':
                                        sums[index] = value;
                                        break;
                                    case 'MIN':
                                        sums[index] = value;
                                        break;
                                }
                            }
                        }
                    })
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
                return [1, this.controlInstance.allColumns.length + 1];
            } else if (columnIndex > (this.isSingleSelect ? 0 : 1)) {
                return [0, 0];
            }
        }
    }

    /**
     * 处理操作列点击
     * 
     * @memberof GridControlBase
     */
    public handleActionClick(data: any, event: any, column: any, detail: any) {
        if (this.Environment && this.Environment.isPreviewMode) {
            return;
        }
        AppViewLogicService.getInstance().executeViewLogic(this.getViewLogicTag(this.controlInstance.name, column.codeName, detail.name), event, this, data, this.controlInstance.getPSAppViewLogics);
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
        AppViewLogicService.getInstance().executeViewLogic(tag, event, this, data, this.controlInstance.getPSAppViewLogics);
    }
}
