import { Prop } from 'vue-property-decorator';
import { Watch } from '@/studio-core';
import { ViewTool } from '@/utils';
import { MDControlBase } from './MDControlBase';
import Sortable from 'sortablejs';
import { Environment } from '@/environments/environment';

/**
 * 表格部件基类
 *
 * @export
 * @class GridControlBase
 * @extends {MDControlBase}
 */
export class GridControlBase extends MDControlBase {
    /**
     * 界面服务
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public appUIService: any;

    /**
     * 是否显示标题
     *
     * @type {boolean}
     * @memberof GridControlBase
     */
    public isHideHeader: boolean = false;

    /**
     * 主信息表格列
     *
     * @type {string}
     * @memberof GridControlBase
     */
    public majorInfoColName: string = '';

    /**
     * 本地缓存标识
     *
     * @protected
     * @type {string}
     * @memberof GridControlBase
     */
    protected localStorageTag: string = '';

    /**
     * 是否单选
     *
     * @type {boolean}
     * @memberof GridControlBase
     */
    @Prop()
    public isSingleSelect?: boolean;

    /**
     * 选中数据字符串
     *
     * @type {string}
     * @memberof GridControlBase
     */
    @Prop()
    public selectedData?: string;

    /**
     * 是否是关系界面
     *
     * @type {boolean}
     * @memberof GridControlBase
     */
    @Prop({ default: false })
    public isformDruipart?: boolean;

    /**
     * 行编辑值校验错误信息
     *
     * @type {Array<any>}
     * @memberof GridControlBase
     */
    public errorMessages: Array<any> = [];

    /**
     * 列主键属性名称
     *
     * @param {string}
     * @memberof GridControlBase
     */
    public columnKeyName: string = '';

    /**
     * 属性值规则
     *
     * @param {*}
     * @memberof GridControlBase
     */
    public deRules: any;

    /**
     * 拷贝数据（用于处理树数据）
     * 
     * @memberof GridControlBase
     */
    public copydata: any[] = [];

    /**
     * 当前编辑行数据
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public curEditRowData: any = {};

    /**
     * 选中值变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof GridControlBase
     */
    @Watch('selectedData')
    public onValueChange(newVal: any, oldVal: any) {
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
     * 表格行数据默认激活模式
     * 0 不激活
     * 1 单击激活
     * 2 双击激活
     *
     * @type {number}
     * @memberof GridControlBase
     */
    @Prop({ default: 2 })
    public gridRowActiveMode!: number;

    /**
     * 是否开启行编辑
     *
     * @type {boolean}
     * @memberof GridControlBase
     */
    @Prop({ default: false })
    public isOpenEdit!: boolean;

    /**
     * 实际是否开启行编辑
     *
     * @type {boolean}
     * @memberof GridControlBase
     */
    public actualIsOpenEdit: boolean = this.isOpenEdit;

    /**
     * 拦截行选中
     *
     * @type {boolean}
     * @memberof GridControlBase
     */
    public stopRowClick: boolean = false;

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
     * 选项框列宽
     *
     * @type {number}
     * @memberof GridControlBase
     */
    public checkboxColWidth: number = 40;

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
     * 表格模型集合
     *
     * @type {any[]}
     * @memberof GridControlBase
     */
    public gridItemsModel: any[] = [];

    /**
     * 属性值规则
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public rules() {}

    /**
     * 是否为实体导出对象
     *
     * @protected
     * @type {boolean}
     * @memberof GridControlBase
     */
    protected isDeExport: boolean = false;

    /**
     * 界面行为模型
     *
     * @type {*}
     * @memberof GridControlBase
     */
    public ActionModel: any = {};

    /**
     * 所有导出列成员
     *
     * @type {any[]}
     * @memberof GridControlBase
     */
    public allExportColumns: any[] = [];

    /**
     * 表格高度
     *
     * @readonly
     * @memberof GridControlBase
     */
    get tableHeight() {
        return this.isEnablePagingBar ? 'calc(100% - 36px)' : '100%';
    }

    /**
     * 获取表格行模型
     *
     * @returns {*}
     * @memberof GridControlBase
     */
    public getGridRowModel(): any {
        return {};
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
     * 消息中心
     *
     * @protected
     * @param {*} data
     * @memberof GridControlBase
     */
    protected accChange(data: any): void {
        if (this.isOpenEdit) {
            this.$Modal.confirm({
                title: '数据已变更',
                content: '数据已变更，是否刷新数据?',
                onOk: () => {
                    this.refresh();
                },
            });
        } else {
            this.refresh();
        }
    }

    /**
     * 部件创建完毕
     *
     * @protected
     * @memberof GridControlBase
     */
    protected ctrlCreated(): void {
        super.ctrlCreated();
        this.setColState();
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }) => {
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
     * 表格行编辑项校验
     *
     * @param {string} property 属性名
     * @param {*} data 行数据
     * @param {number} rowIndex 索引
     * @returns {Promise<any>}
     * @memberof GridControlBase
     */
    public validate(property: string, data: any, rowIndex: number): Promise<any> {
        return new Promise((resolve) => {
            this.$util
                .validateItem(property, data, this.rules())
                .then(() => {
                    this.gridItemsModel[rowIndex][property].setError(null);
                    resolve(true);
                })
                .catch((res: any) => {
                    this.gridItemsModel[rowIndex][property].setError(res.errors[0].message);
                    resolve(false);
                });
        });
    }

    /**
     * 校验所有修改过的编辑项
     *
     * @returns
     * @memberof GridControlBase
     */
    public async validateAll() {
        this.errorMessages = [];
        let validateState = true;
        let index = -1;
        for (let item of this.items) {
            let tempMessage: string = '';
            index++;
            if (item.rowDataState === 'create' || item.rowDataState === 'update') {
                for (let property of Object.keys(this.rules() as any)) {
                    if (!(await this.validate(property, item, index))) {
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
     * 获取列状态
     *
     * @param {string} name
     * @returns {boolean}
     * @memberof GridControlBase
     */
    public getColumnState(name: string): boolean {
        let column = this.allColumns.find((col: any) => Object.is(name, col.name));
        return column.show ? true : false;
    }

    /**
     * 表格列是否自适应布局
     *
     * @readonly
     * @type {boolean}
     * @memberof GridControlBase
     */
    get adaptiveState(): boolean {
        return !this.allColumns.find((column: any) => column.show && Object.is(column.unit, 'STAR'));
    }

    /**
     * 保存
     *
     * @param {any[]} args
     * @param {*} [params]
     * @param {*} [$event]
     * @param {*} [xData]
     * @returns
     * @memberof GridControlBase
     */
    public async save(args: any[], params?: any, $event?: any, xData?: any, showBusyIndicator: boolean = true) {
        if (!(await this.validateAll())) {
            if (this.errorMessages && this.errorMessages.length > 0) {
                this.$Notice.error({ title: this.$t('app.commonWords.wrong') as string, desc: this.errorMessages[0] });
            } else {
                this.$Notice.error({
                    title: this.$t('app.commonWords.wrong') as string,
                    desc: this.$t('app.commonWords.rulesException') as string,
                });
            }
            return [];
        }
        let successItems: any = [];
        let errorItems: any = [];
        let errorMessage: any = [];
        for (const item of this.items) {
            try {
                if (Object.is(item.rowDataState, 'create')) {
                    if (!this.createAction) {
                        this.$Notice.error({
                            title: this.$t('app.commonWords.wrong') as string,
                            desc: this.$t('app.gridpage.notConfig.createAction') as string,
                        });
                    } else {
                        Object.assign(item, { viewparams: this.viewparams });
                        let response = await this.service.add(
                            this.createAction,
                            JSON.parse(JSON.stringify(this.context)),
                            item,
                            this.showBusyIndicator
                        );
                        successItems.push(JSON.parse(JSON.stringify(response.data)));
                    }
                } else if (Object.is(item.rowDataState, 'update')) {
                    if (!this.updateAction) {
                        this.$Notice.error({
                            title: this.$t('app.commonWords.wrong') as string,
                            desc: this.$t('app.gridpage.notConfig.updateAction') as string,
                        });
                    } else {
                        Object.assign(item, { viewparams: this.viewparams });
                        if (item[this.appDeName]) {
                            Object.assign(this.context, { [this.appDeName]: item[this.appDeName] });
                        }
                        let response = await this.service.update(
                            this.updateAction,
                            JSON.parse(JSON.stringify(this.context)),
                            item,
                            this.showBusyIndicator
                        );
                        successItems.push(JSON.parse(JSON.stringify(response.data)));
                    }
                }
            } catch (error) {
                errorItems.push(JSON.parse(JSON.stringify(item)));
                errorMessage.push(error);
            }
        }
        this.$emit('save', successItems);
        this.refresh([]);
        if (errorItems.length === 0 && !this.isformDruipart && showBusyIndicator) {
            this.$Notice.success({ title: '', desc: this.$t('app.commonWords.saveSuccess') as string });
        } else {
            errorItems.forEach((item: any, index: number) => {
                if (errorMessage[index] && errorMessage[index].data) {
                    if (Object.is(errorMessage[index].data.errorKey, 'DupCheck')) {
                        let errorProp: string = errorMessage[index].data.message.match(/\[[a-zA-Z]*\]/)[0];
                        let name: string = errorProp
                            ? this.service.getNameByProp(errorProp.substr(1, errorProp.length - 2))
                            : '';
                        if (name) {
                            let desc: any = this.allColumns.find((column: any) => {
                                return Object.is(column.name, name);
                            });
                            this.$Notice.error({
                                title: this.$t('app.commonWords.createFailed') as string,
                                desc:
                                    (desc ? desc.label : '') +
                                    ' : ' +
                                    item[name] +
                                    (this.$t('app.commonWords.isExist') as string) +
                                    '!',
                            });
                        } else {
                            this.$Notice.error({
                                title: this.$t('app.commonWords.createFailed') as string,
                                desc: errorMessage[index].data.message
                                    ? errorMessage[index].data.message
                                    : (this.$t('app.commonWords.sysException') as string),
                            });
                        }
                    } else if (Object.is(errorMessage[index].data.errorKey, 'DuplicateKeyException')) {
                        let name: string = this.service.getNameByProp(this.columnKeyName);
                        if (name) {
                            let desc: any = this.allColumns.find((column: any) => {
                                return Object.is(column.name, name);
                            });
                            this.$Notice.error({
                                title: this.$t('app.commonWords.createFailed') as string,
                                desc:
                                    (desc ? desc.label : '') +
                                    ' : ' +
                                    item[name] +
                                    (this.$t('app.commonWords.isExist') as string) +
                                    '!',
                            });
                        }
                    } else {
                        this.$Notice.error({
                            title: this.$t('app.commonWords.saveFailed') as string,
                            desc: errorMessage[index].data.message
                                ? errorMessage[index].data.message
                                : (this.$t('app.commonWords.sysException') as string),
                        });
                    }
                } else {
                    this.$Notice.error({
                        title: this.$t('app.commonWords.saveFailed') as string,
                        desc:
                            (item[this.majorInfoColName] ? item[this.majorInfoColName] : '') +
                            (this.$t('app.commonWords.saveFailed') as string) +
                            '!',
                    });
                }
            });
        }
        return successItems;
    }

    /**
     * 表格数据加载
     *
     * @param {*} [opt={}]
     * @param {boolean} [pageReset=false]
     * @returns {void}
     * @memberof GridControlBase
     */
    public load(opt: any = {}, pageReset: boolean = false): void {
        if (!this.fetchAction) {
            this.$Notice.error({
                title: this.$t('app.commonWords.wrong') as string,
                desc: this.$t('app.gridpage.notConfig.fetchAction') as string,
            });
            return;
        }
        if (pageReset) {
            this.curPage = 1;
        }
        const arg: any = { ...opt };
        const page: any = {};
        if (this.isEnablePagingBar) {
            Object.assign(page, { page: this.curPage - 1, size: this.limit });
        } else {
            Object.assign(page, { size: this.limit });
        }
        // 设置排序
        if (!this.isNoSort && !Object.is(this.minorSortDir, '') && !Object.is(this.minorSortPSDEF, '')) {
            const sort: string = this.minorSortPSDEF + ',' + this.minorSortDir;
            Object.assign(page, { sort: sort });
        }
        Object.assign(arg, page);
        const parentdata: any = {};
        this.$emit('beforeload', parentdata);
        Object.assign(arg, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
        Object.assign(tempViewParams, JSON.parse(JSON.stringify(this.viewparams)));
        Object.assign(arg, { viewparams: tempViewParams });
        const post: Promise<any> = this.service.search(
            this.fetchAction,
            JSON.parse(JSON.stringify(this.context)),
            arg,
            this.showBusyIndicator
        );
        post.then((response: any) => {
            if (!response.status || response.status !== 200) {
                if (response.message) {
                    this.$Notice.error({ title: this.$t('app.commonWords.wrong') as string, desc: response.message });
                }
                return;
            }
            const data: any = response.data;
            if (data.length === 0 && this.curPage > 1) {
                this.curPage--;
                this.load(opt, pageReset);
                return;
            }
            this.totalRecord = response.total;
            this.items = JSON.parse(JSON.stringify(data));
            // 清空selections,gridItemsModel
            this.selections = [];
            this.gridItemsModel = [];
            this.items.forEach(() => {
                this.gridItemsModel.push(this.getGridRowModel());
            });
            this.items.forEach((item: any) => {
                Object.assign(item, this.getActionState(item));
            });
            this.$emit('load', this.items);
            // 向上下文中填充当前数据
            this.$appService.contextStore.setContextData(this.context, this.appDeName, { items: this.items });
            // 设置默认选中
            setTimeout(() => {
                if (this.isSelectFirstDefault) {
                    this.rowClick(this.items[0]);
                }
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
            }, 300);
            // <#if ctrl.getAggMode() == "ALL">
            // this.getAggData();
            // </#if>
            const _this: any = this;
            if (_this.isEnableGroup && _this.group && _this.group instanceof Function) {
                _this.group();
            }
            this.copyData();
        }).catch((response: any) => {
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error({ title: this.$t('app.commonWords.wrong') as string, desc: response.data.message });
        });
    }

    /**
     * 删除
     *
     * @param {any[]} items
     * @returns {Promise<any>}
     * @memberof GridControlBase
     */
    public async remove(items: any[]): Promise<any> {
        if (!this.removeAction) {
            this.$Notice.error({
                title: this.$t('app.commonWords.wrong') as string,
                desc: this.$t('app.gridpage.notConfig.removeAction') as string,
            });
            return;
        }
        const arr: any[] = [];
        items.forEach((record: any, index: number) => {
            if (Object.is(record.srfuf, '0')) {
                this.items.some((val: any, num: number) => {
                    if (JSON.stringify(val) == JSON.stringify(record)) {
                        this.items.splice(num, 1);
                        this.gridItemsModel.splice(num, 1);
                        return true;
                    }
                });
            } else {
                arr.push(items[index]);
            }
        });
        if (arr.length === 0) {
            return;
        }
        let dataInfo = '';
        arr.forEach((record: any, index: number) => {
            let srfmajortext = record.srfmajortext;
            if (index < 5) {
                if (!Object.is(dataInfo, '')) {
                    dataInfo += '、';
                }
                dataInfo += srfmajortext;
            } else {
                return false;
            }
        });

        if (arr.length < 5) {
            dataInfo =
                dataInfo +
                ' ' +
                (this.$t('app.gridpage.totle') as string) +
                arr.length +
                (this.$t('app.gridpage.records') as string) +
                (this.$t('app.gridpage.data') as string);
        } else {
            dataInfo = dataInfo + '... 等' + (this.$t('app.gridpage.totle') as string) + arr.length + '条';
        }

        const removeData = () => {
            const keys: any[] = [];
            arr.forEach((data: any) => {
                keys.push(data.srfkey);
            });
            const _removeAction = keys.length > 1 ? 'removeBatch' : this.removeAction;
            const _keys = keys.length > 1 ? keys : keys[0];
            const context: any = JSON.parse(JSON.stringify(this.context));
            const post: Promise<any> = this.service.delete(
                _removeAction,
                Object.assign(context, { [this.appDeName]: _keys }),
                Object.assign({ [this.appDeName]: _keys }, { viewparams: this.viewparams }),
                this.showBusyIndicator
            );
            return new Promise((resolve: any, reject: any) => {
                post.then((response: any) => {
                    if (!response || response.status !== 200) {
                        this.$Notice.error({
                            title: '',
                            desc: (this.$t('app.gridpage.delDataFail') as string) + ',' + response.info,
                        });
                        return;
                    } else {
                        this.$Notice.success({ title: '', desc: this.$t('app.gridpage.delSuccess') as string });
                    }
                    //删除items中已删除的项
                    arr.forEach((data: any) => {
                        this.items.some((item: any, index: number) => {
                            if (Object.is(item.srfkey, data.srfkey)) {
                                this.items.splice(index, 1);
                                this.gridItemsModel.splice(index, 1);
                                return true;
                            }
                        });
                    });
                    this.totalRecord -= arr.length;
                    this.$emit('remove', null);
                    this.selections = [];
                    this.$emit('selectionchange', this.selections);
                    resolve(response);
                }).catch((response: any) => {
                    if (response && response.status === 401) {
                        return;
                    }
                    if (!response || !response.status || !response.data) {
                        this.$Notice.error({
                            title: this.$t('app.commonWords.wrong') as string,
                            desc: this.$t('app.commonWords.sysException') as string,
                        });
                        reject(response);
                        return;
                    }
                    this.$Notice.error({
                        title: this.$t('app.commonWords.wrong') as string,
                        desc: response.data.message,
                    });
                    reject(response);
                });
            });
        };

        dataInfo = dataInfo.replace(/[null]/g, '').replace(/[undefined]/g, '');
        this.$Modal.confirm({
            title: this.$t('app.commonWords.warning') as string,
            content:
                (this.$t('app.gridpage.confirmDel') as string) +
                ' ' +
                dataInfo +
                '，' +
                (this.$t('app.gridpage.notRecoverable') as string),
            onOk: () => {
                removeData();
            },
            onCancel: () => {},
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
        console.error(this.$t('app.gridpage.notBatch') as string);
    }

    /**
     * 数据导入
     *
     * @param {*} [data={}]
     * @param {*} [importMode={}]
     * @returns {void}
     * @memberof GridControlBase
     */
    public importExcel(data: any = {}, importMode: any = {}): void {
        if (Object.keys(importMode).length == 0) {
            this.$Notice.warning({
                title: this.$t('app.utilview.warning') as string,
                desc: this.$t('app.utilview.info') as string,
            });
            return;
        }
        const view: any = {
            viewname: 'app-data-upload',
            title: this.$t('app.utilview.importview'),
            width: 900,
            height: 700,
        };
        const container = this.$appmodal.openModal(view, JSON.parse(JSON.stringify(this.context)), importMode);
        container.subscribe((result: any) => {
            if (Object.is(result.ret, 'OK')) {
                this.refresh(result.datas);
            }
        });
    }

    /**
     * 数据导出
     *
     * @param {*} [data={}]
     * @returns {void}
     * @memberof GridControlBase
     */
    public exportExcel(data: any = {}): void {
        // 导出Excel
        const doExport = async (_data: any) => {
            const tHeader: Array<any> = [];
            const filterVal: Array<any> = [];
            (this.isDeExport ? this.allExportColumns : this.allColumns).forEach((item: any) => {
                item.show && item.label ? tHeader.push(this.$t(item.langtag)) : '';
                item.show && item.name ? filterVal.push(item.name) : '';
            });
            const data = await this.formatExcelData(filterVal, _data);
            this.$export.exportExcel().then((excel: any) => {
                excel.export_json_to_excel({
                    header: tHeader, //表头 必填
                    data, //具体数据 必填
                    filename: (this.$t('entities.'+this.appDeName+'.appdename') as string) + (this.$t('app.gridpage.grid') as string), //非必填
                    autoWidth: true, //非必填
                    bookType: 'xlsx', //非必填
                });
            });
        };
        const page: any = {};
        // 设置page，size
        if (Object.is(data.type, 'maxRowCount')) {
            Object.assign(page, { page: 0, size: data.maxRowCount });
        } else if (Object.is(data.type, 'activatedPage')) {
            if (this.isDeExport) {
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
            const sort: string = this.minorSortPSDEF + ',' + this.minorSortDir;
            Object.assign(page, { sort: sort });
        }
        const arg: any = {};
        Object.assign(arg, page);
        // 获取query,搜索表单，viewparams等父数据
        const parentdata: any = {};
        this.$emit('beforeload', parentdata);
        Object.assign(arg, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
        Object.assign(tempViewParams, JSON.parse(JSON.stringify(this.viewparams)));
        Object.assign(arg, { viewparams: tempViewParams });
        let post: any;
        if (this.isDeExport) {
            post = this.service.searchDEExportData(
                this.fetchAction,
                JSON.parse(JSON.stringify(this.context)),
                arg,
                this.showBusyIndicator
            );
        } else {
            post = this.service.search(
                this.fetchAction,
                JSON.parse(JSON.stringify(this.context)),
                arg,
                this.showBusyIndicator
            );
        }
        post.then((response: any) => {
            if (!response || response.status !== 200) {
                this.$Notice.error({
                    title: '',
                    desc: (this.$t('app.gridpage.exportFail') as string) + ',' + response.info,
                });
                return;
            }
            try {
                doExport(JSON.parse(JSON.stringify(response.data)));
            } catch (error) {
                console.error(error);
            }
        }).catch((response: any) => {
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error({ title: '', desc: this.$t('app.gridpage.exportFail') as string });
        });
    }

    /**
     * 工作流提交
     *
     * @param {*} [data={}]
     * @param {*} [localdata={}]
     * @returns {Promise<any>}
     * @memberof ${srfclassname('${ctrl.codeName}')}Base
     */
    public async submitbatch(data: any,localdata:any): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
        const _this: any = this;
        const arg: any = data;
        const result: Promise<any> = this.service.submitbatch(_this.WFSubmitAction, JSON.parse(JSON.stringify(this.context)),arg,localdata,this.showBusyIndicator);
        result.then((response: any) => {
            if (!response || response.status !== 200) {
                if(response.data){
                    this.$Notice.error({ title: '', desc: (this.$t('app.formpage.workflow.submiterror') as string) + ', ' + response.data.message });
                }
                return;
            }
            this.$Notice.info({ title: '', desc: (this.$t('app.formpage.workflow.submitsuccess') as string) });
            resolve(response);
        }).catch((response: any) => {
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
     * 导出数据格式化
     *
     * @param {*} filterVal
     * @param {*} jsonData
     * @param {any[]} [codelistColumns=[]]
     * @returns {Promise<any>}
     * @memberof GridControlBase
     */
    public async formatExcelData(filterVal: any, jsonData: any, codelistColumns: any[] = []): Promise<any> {
        for (const codelist of codelistColumns) {
            // 动态代码表处理
            if (Object.is(codelist.codelistType, 'DYNAMIC')) {
                let items = await this.codeListService.getItems(codelist.srfkey);
                jsonData.forEach((row: any) => {
                    row[codelist.name] = this.getCodelistValue(items, row[codelist.name], codelist);
                });
                // 静态处理
            } else if (Object.is(codelist.codelistType, 'STATIC')) {
                let items = await this.$store.getters.getCodeListItems(codelist.srfkey);
                jsonData.forEach((row: any) => {
                    row[codelist.name] = this.getCodelistValue(items, row[codelist.name], codelist);
                });
            }
        }
        return jsonData.map((v: any) => filterVal.map((j: any) => v[j]));
    }

    /**
     * 解析代码表和vlaue，设置items
     *
     * @param {any[]} items 代码表数据
     * @param {*} value
     * @param {*} codelist
     * @returns
     * @memberof GridControlBase
     */
    public getCodelistValue(items: any[], value: any, codelist: any) {
        if (!value && value !== 0 && value !== false) {
            return this.$t('codelist.' + codelist.srfkey + '.empty');
        }
        if (items) {
            let result: any = [];
            if (Object.is(codelist.renderMode, 'number')) {
                items.map((_item: any, index: number) => {
                    const nValue = parseInt(value as any, 10);
                    const codevalue = _item.value;
                    if ((parseInt(codevalue, 10) & nValue) > 0) {
                        result.push(_item);
                    }
                });
            } else if (Object.is(codelist.renderMode, 'string')) {
                const arrayValue: Array<any> = (value as any).split(codelist.valueSeparator);
                arrayValue.map((value: any, index: number) => {
                    result.push([]);
                    let values: any[] = Object.is(this.$util.typeOf(value), 'number')
                        ? [value]
                        : [...(value as any).split(codelist.valueSeparator)];
                    values.map((val: any, num: number) => {
                        const item = this.getItem(items, val, codelist);
                        if (item) {
                            result[index].push(item);
                        }
                    });
                });
            } else {
                let values: any[] = Object.is(this.$util.typeOf(value), 'number')
                    ? [value]
                    : [...(value as any).split(codelist.valueSeparator)];
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
     * @param {any[]} items
     * @param {*} value
     * @param {*} codelist
     * @returns {*}
     * @memberof GridControlBase
     */
    public getItem(items: any[], value: any, codelist: any): any {
        const arr: Array<any> = items.filter((item) => {
            return item.value == value;
        });
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

        const refs: any = this.$refs;
        if (refs.multipleTable) {
            refs.multipleTable.clearSelection();
            refs.multipleTable.toggleRowSelection($event);
        }

        this.$emit('rowdblclick', this.selections);
        this.$emit('selectionchange', this.selections);
    }

    /**
     * 单个复选框选中
     *
     * @param {*} selection 所有选中行数据
     * @param {*} row 当前选中行数据
     * @memberof MainBase
     */
    public select(selection: any, row: any): void {
        let _this: any = this;
        if (_this.groupAppField) {
            let isContain: boolean = selection.some((item: any) => {
                return item == row;
            });
            // 是否选中当前行，选中为true，否则为false
            if (isContain) {
                // 当前行为分组行
                if (row.children && row.children.length > 0) {
                    _this.toggleSelection(row.children, true);
                    row.children.forEach((children: any) => {
                        _this.selections.push(children);
                    });
                } else {
                    _this.selections.push(row);
                }
            } else {
                if (row.children && row.children.length > 0) {
                    _this.toggleSelection(row.children, false);
                    _this.selections = _this.computeCheckedData(this.selections, row.children);
                } else {
                    _this.selections = _this.computeCheckedData(this.selections, row);
                }
            }
            _this.selections = [...new Set(this.selections)];
        } else {
            if (!selection) {
                return;
            }
            _this.selections = [...JSON.parse(JSON.stringify(selection))];
        }
        this.$emit('selectionchange', _this.selections);
    }

    /**
     * 计算当前选中数据
     *
     * @param {*} selectionArray 所有选中行数据
     * @param {*} cancelData 被取消选中行数据，分组行为数组，非分组行为对象
     * @memberof MainBase
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
                    });
                    if (tempFlag) targetArray.push(selection);
                });
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
                });
            }
        }
        return targetArray;
    }

    /**
     * 设置非分组行checkbox选中状态
     *
     * @param {*} rows 选中数据数组
     * @param {boolean} flag 是否选中
     * @memberof MainBase
     */
    public toggleSelection(rows?: any, flag?: boolean) {
        if (rows) {
            rows.forEach((row: any) => {
                (this.$refs.multipleTable as any).toggleRowSelection(row, flag);
            });
        } else {
            (this.$refs.multipleTable as any).clearSelection();
        }
    }

    /**
     * 复选框数据全部选中
     *
     * @param {*} $event
     * @memberof MainBase
     */
    public selectAll(selection: any): void {
        let _this: any = this;
        _this.selections = [];
        if (_this.groupAppField) {
            let flag: boolean = true;
            if (selection && selection.length === _this.items.length) {
                selection.forEach((element: any) => {
                    if (element.children) {
                        _this.toggleSelection(element.children, flag);
                        element.children.forEach((children: any) => {
                            _this.selections.push(children);
                        });
                    } else {
                        flag = false;
                    }
                });
            } else {
                flag = false;
            }
            if (!flag) {
                _this.toggleSelection();
            }
        } else {
            if (!selection) {
                return;
            }
            _this.selections = [...JSON.parse(JSON.stringify(selection))];
        }
        this.$emit('selectionchange', _this.selections);
    }

    /**
     * 行单击选中
     *
     * @param {*} $event
     * @param {boolean} [ifAlways=false]
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
        if(this.isSingleSelect){
            this.selections = [];
        }
        // 已选中则删除，没选中则添加
        let selectIndex = this.selections.findIndex((item: any) => {
            return Object.is(item[this.appDeName], $event[this.appDeName]);
        });
        if (Object.is(selectIndex,-1)){
            this.selections.push(JSON.parse(JSON.stringify($event)));
        } else {
            this.selections.splice(selectIndex,1);
        }
        const refs: any = this.$refs;
        if (refs.multipleTable) {
            if(this.isSingleSelect){
                refs.multipleTable.clearSelection();
                refs.multipleTable.setCurrentRow($event);
            }else{
                  refs.multipleTable.toggleRowSelection($event); 
            }
        }
        this.$emit('selectionchange', this.selections);
    }

    /**
     * 页面变化
     *
     * @param {*} $event
     * @returns {void}
     * @memberof GridControlBase
     */
    public pageOnChange($event: any): void {
        if (!$event || $event === this.curPage) {
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
        if (!$event || $event === this.limit) {
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
        this.load({});
    }

    /**
     * 排序变化
     *
     * @param {{ column: any, prop: any, order: any }} { column, prop, order }
     * @returns {void}
     * @memberof GridControlBase
     */
    public onSortChange({ column, prop, order }: { column: any; prop: any; order: any }): void {
        const dir = Object.is(order, 'ascending') ? 'asc' : Object.is(order, 'descending') ? 'desc' : '';
        if (Object.is(dir, this.minorSortDir) && Object.is(this.minorSortPSDEF, prop)) {
            return;
        }
        this.minorSortDir = dir;
        this.minorSortPSDEF = prop ? prop : '';
        this.load({});
    }

    /**
     * 表格行选中样式
     *
     * @param {{ row: any, rowIndex: any }} { row, rowIndex }
     * @returns {string}
     * @memberof GridControlBase
     */
    public onRowClassName({ row, rowIndex }: { row: any; rowIndex: any }): string {
        const index = this.selections.findIndex((select: any) => Object.is(select.srfkey, row.srfkey));
        return index !== -1 ? 'grid-row-select' : '';
    }

    /**
     * 设置列状态
     *
     * @memberof GridControlBase
     */
    public setColState(): void {
        const _data: any = localStorage.getItem(this.localStorageTag);
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
    public onColChange(): void {
        localStorage.setItem(this.localStorageTag, JSON.stringify(this.allColumns));
    }

    /**
     * 新建行
     *
     * @param {any[]} args
     * @param {*} [params]
     * @param {*} [$event]
     * @param {*} [xData]
     * @returns {void}
     * @memberof GridControlBase
     */
    public newRow(args: any[], params?: any, $event?: any, xData?: any): void {
        if (!this.loaddraftAction) {
            this.$Notice.error({
                title: this.$t('app.commonWords.wrong') as string,
                desc: this.$t('app.gridpage.notConfig.loaddraftAction') as string,
            });
            return;
        }
        Object.assign(args[0], { viewparams: this.viewparams });
        let post: Promise<any> = this.service.loadDraft(
            this.loaddraftAction,
            JSON.parse(JSON.stringify(this.context)),
            args[0],
            this.showBusyIndicator
        );
        post.then((response: any) => {
            if (!response.status || response.status !== 200) {
                if (response.message) {
                    this.$Notice.error({ title: this.$t('app.commonWords.wrong') as string, desc: response.message });
                }
                return;
            }
            const data = response.data;
            this.createDefault(data);
            data.rowDataState = 'create';
            Object.assign(data, this.getActionState(data));
            let tempItems: any[] = [];
            tempItems.push(data);
            this.items = tempItems.concat(this.items);
            this.gridItemsModel.push(this.getGridRowModel());
        }).catch((response: any) => {
            if (response && response.status === 401) {
                return;
            }
            if (!response || !response.status || !response.data) {
                this.$Notice.error({
                    title: this.$t('app.commonWords.wrong') as string,
                    desc: this.$t('app.commonWords.sysException') as string,
                });
                return;
            }
        });
    }

    /**
     * 表格编辑项值变更
     *
     * @param {*} row 行数据
     * @param {{ name: string, value: any }} $event
     * @param {number} rowIndex
     * @returns {void}
     * @memberof GridControlBase
     */
    public onGridItemValueChange(row: any, $event: { name: string; value: any }, rowIndex: number): void {
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
        let tempContext: any = this.$util.deepCopy(this.context);
        if (this.appDeName && this.columnKeyName) {
            tempContext[this.appDeName] = data[this.columnKeyName];
        }
        const arg: any = JSON.parse(JSON.stringify(data));
        Object.assign(arg, { viewparams: this.viewparams });
        const post: Promise<any> = this.service.frontLogic(
            mode,
            JSON.parse(JSON.stringify(tempContext)),
            arg,
            showloading
        );
        post.then((response: any) => {
            if (!response || response.status !== 200) {
                this.$Notice.error({
                    title: this.$t('app.commonWords.wrong') as string,
                    desc: this.$t('app.gridpage.formitemFailed') as string,
                });
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
            if (response && response.status === 401) {
                return;
            }
            if (!response || !response.status || !response.data) {
                this.$Notice.error({
                    title: this.$t('app.commonWords.wrong') as string,
                    desc: this.$t('app.commonWords.sysException') as string,
                });
                return;
            }
        });
    }

    /**
     * 获取界面行为权限状态
     *
     * @param {*} data
     * @returns
     * @memberof GridControlBase
     */
    public getActionState(data: any) {
        let tempActionModel: any = JSON.parse(JSON.stringify(this.ActionModel));
        let targetData: any = this.transformData(data);
        ViewTool.calcActionItemAuthState(targetData, tempActionModel, this.appUIService);
        return tempActionModel;
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
    public gridEditItemChange(row: any, property: string, value: any, rowIndex: number): void {
        if(this.majorInfoColName == property) {
            this.items[rowIndex].srfmajortext = value;
        }
        row.rowDataState = row.rowDataState ? row.rowDataState : 'update';
        this.curEditRowData = row;
        this.validate(property, row, rowIndex);
    }

    /**
     * 获取对应行class
     *
     * @param {{ row: any, rowIndex: number }} args row 行数据，rowIndex 行索引
     * @returns {string}
     * @memberof GridControlBase
     */
    public getRowClassName(args: { row: any; rowIndex: number }): string {
        let isSelected = this.selections.some((item: any) => {
            return Object.is(item[this.appDeName], args.row[this.appDeName]);
        });
        return isSelected ? 'grid-selected-row' : '';
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
            });
            if (curColumn.hasOwnProperty('enableCond')) {
                return data.srfuf == 1 ? (curColumn.enableCond & 2) !== 2 : (curColumn.enableCond & 1) !== 1;
            } else {
                return false;
            }
        }
    }

    /**
     * 新建默认值
     *
     * @param {*} row 行数据
     * @memberof GridControlBase
     */
    public createDefault(row: any): void {}

    /**
     * 界面行为
     *
     * @param {*} row
     * @param {*} tag
     * @param {*} $event
     * @memberof GridControlBase
     */
    public uiAction(row: any, tag: any, $event: any) {}

    /**
     * 校验属性值规则
     *
     * @public
     * @param {{ name: string }} { name }
     * @memberof GridControlBase
     */
    public verifyDeRules(name: string, rule: any = this.deRules, op: string = 'AND', value: any): { isPast: boolean } {
        let falg: any = {};
        if (!rule || !rule[name]) {
            return falg;
        }
        let opValue = op == 'AND' ? true : false;
        let startOp = (val: boolean) => {
            if (falg.isPast) {
                if (opValue) {
                    falg.isPast = falg.isPast && val;
                } else {
                    falg.isPast = falg.isPast || val;
                }
            } else {
                falg.isPast = val;
            }
        };
        for (let i = 0; i < rule[name].length; i++) {
            let item: any = rule[name][i];
            // let dataValue = item.deName?this.data[this.service.getItemNameByDeName(item.deName)]:"";
            // 为空值时，属性值规则不做校验
            if ((value === null || value === undefined || value === '') && item.type != 'GROUP') {
                startOp(true);
                return falg;
            }
            // 常规规则
            if (item.type == 'SIMPLE') {
                startOp(
                    !this.$verify.checkFieldSimpleRule(
                        value,
                        item.condOP,
                        item.paramValue,
                        item.ruleInfo,
                        item.paramType,
                        this.curEditRowData,
                        item.isKeyCond
                    )
                );
                falg.infoMessage = item.ruleInfo;
                if (!falg.isPast) return falg;
            }
            // 数值范围
            if (item.type == 'VALUERANGE2') {
                startOp(
                    !this.$verify.checkFieldValueRangeRule(
                        value,
                        item.minValue,
                        item.isIncludeMinValue,
                        item.maxValue,
                        item.isIncludeMaxValue,
                        item.ruleInfo,
                        item.isKeyCond
                    )
                );
                falg.infoMessage = item.ruleInfo;
                if (!falg.isPast) return falg;
            }
            // 正则式
            if (item.type == 'REGEX') {
                startOp(!this.$verify.checkFieldRegExRule(value, item.regExCode, item.ruleInfo, item.isKeyCond));
                falg.infoMessage = item.ruleInfo;
                if (!falg.isPast) return falg;
            }
            // 长度
            if (item.type == 'STRINGLENGTH') {
                startOp(
                    !this.$verify.checkFieldStringLengthRule(
                        value,
                        item.minValue,
                        item.isIncludeMinValue,
                        item.maxValue,
                        item.isIncludeMaxValue,
                        item.ruleInfo,
                        item.isKeyCond
                    )
                );
                falg.infoMessage = item.ruleInfo;
                if (!falg.isPast) return falg;
            }
            // 系统值规则
            if (item.type == 'SYSVALUERULE') {
                startOp(
                    !this.$verify.checkFieldSysValueRule(value, item.sysRule.regExCode, item.ruleInfo, item.isKeyCond)
                );
                falg.infoMessage = item.ruleInfo;
                if (!falg.isPast) return falg;
            }
            // 分组
            if (item.type == 'GROUP') {
                falg = this.verifyDeRules('group', item, item.condOP ? item.condOP : 'AND', value);
                if (item.isNotMode) {
                    falg.isPast = !falg.isPast;
                }
                if (!falg.isPast) return falg;
            }
        }
        if (!falg.hasOwnProperty('isPast')) {
            falg.isPast = true;
        }
        if (!value) {
            falg.isPast = true;
        }
        return falg;
    }

    /**
     * 部件挂载完毕
     * 
     * @memberof GridControlBase
     */
    public ctrlMounted() {
        super.ctrlMounted();
        this.rowDrop();
    }

    /**
     * 拷贝数据
     * 
     * @memberof GridControlBase
     */
    public copyData() {
        this.copydata = [];
        if (this.items.length > 0) {
            const copyData: any[] = [];
            this.items.forEach((item: any) => {
                copyData.push(item);
                if (item.items?.length > 0) {
                    item.items.forEach((_item: any) => {
                        copyData.push(_item);
                    });
                }
            });
            this.copydata = this.$util.deepCopy(copyData);
        }
    }

    /**
     * 行拖拽
     * 
     * @memberof GridControlBase
     */
    public rowDrop() {
        const tbody = document.querySelector('.el-table__body-wrapper tbody');
        const _this = this;
        Sortable.create(tbody, {
            onEnd({ newIndex, oldIndex }: any) {
                let dragSuccess: boolean = false;
                let newGridData = _this.$util.deepCopy(_this.copydata);
                if (_this.minorSortPSDEF) {
                    if (_this.copydata.length == _this.items.length) {
                        // 不是树表格
                        _this.sortData(newGridData, newIndex, oldIndex);
                        dragSuccess = true;
                    } else if (_this.copydata.length > _this.items.length) {
                        // 树表格
                        if (parseInt(newGridData[oldIndex].parent) > 0) {
                            // 拖拽子节点 (只能在当前父节点下拖拽)
                            const id = parseInt(newGridData[oldIndex].parent);
                            const parentIndex = newGridData.findIndex((data: any) => id == data.id);
                            if (parentIndex != -1 && newGridData[parentIndex].items?.length > 1) {
                                if (parentIndex < oldIndex && oldIndex <= parentIndex + newGridData[parentIndex].items.length) {
                                    _this.sortData(newGridData, newIndex, oldIndex);
                                    dragSuccess = true;
                                }
                            }
                        } else {
                            // 拖拽非子节点（不能拖拽到子节点上）
                            if (parseInt(newGridData[newIndex].parent) <= 0) {
                                _this.sortData(newGridData, newIndex, oldIndex);
                                dragSuccess = true;
                            }
                        }
                        // 拖拽成功后将数据重新处理为树形数据
                        if (dragSuccess) {
                            let parentArray: any[] = [];
                            newGridData.forEach((data: any) => {
                                if (parseInt(data.parent) <= 0) {
                                    parentArray.push(data);
                                }
                            });
                            parentArray.forEach((item: any) => {
                                if (item.items?.length > 0) {
                                    const items: any[] = [];
                                    newGridData.forEach((data: any) => {
                                        if (data.parent == item.id) {
                                            items.push(data);
                                        }
                                    });
                                    item.items = items;
                                }
                            });
                            newGridData = _this.$util.deepCopy(parentArray);
                        }
                    }
                }
                const items: any[] = _this.items;
                _this.items = [];
                _this.$nextTick(() => {
                    if (dragSuccess) {
                        _this.items = newGridData;
                        _this.save([], {} ,{}, {}, false).then(() => {
                            _this.refresh();
                        });
                    } else {
                        _this.items = items;
                        if (!_this.minorSortPSDEF) {
                            console.warn('未配置排序属性');
                        } else {
                            console.warn('排序失败');
                        }
                    }
                });
            }
        });
    }

    /**
     * 根据拖拽结果对数据排序
     * 
     * @param newGridData 表格数据
     * @param newIndex 拖拽后下标
     * @param oldIndex 拖拽前下标
     */
    public sortData(newGridData: any[],newIndex: number, oldIndex: number) {
        const currRow = newGridData.splice(oldIndex, 1)[0];
        newGridData.splice(newIndex, 0, currRow);
        newGridData.forEach((item: any, index: number) => {
            item[this.minorSortPSDEF] = this.copydata[index][this.minorSortPSDEF];
            item['rowDataState'] = 'update';
        });
    }
}
