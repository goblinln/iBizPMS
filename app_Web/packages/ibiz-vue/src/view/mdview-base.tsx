import { IPSDESearchForm, IPSSearchBar, IPSAppDEMultiDataView, IPSAppCodeList, IPSCodeItem, IPSAppDEField } from '@ibiz/dynamic-model-api';
import { CodeListServiceBase, ModelTool, Util } from 'ibiz-core'
import { MainViewBase } from "./mainview-base";

/**
 * 多数据视图基类
 *
 * @export
 * @class MDViewBase
 * @extends {MainViewBase}
 */
export class MDViewBase extends MainViewBase {

    /**
     * 多数据部件是否单选
     *
     * @type {boolean}
     * @memberof MDViewBase
     */
    public isSingleSelect!: boolean;

    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof MDViewBase
     */
    public codeListService!: CodeListServiceBase;

    /**
     * 快速搜索值
     *
     * @type {string}
     * @memberof MDViewBase
     */
    public query: string = '';

    /**
     * 是否展开搜索表单
     *
     * @type {boolean}
     * @memberof MDViewBase
     */
    public isExpandSearchForm: boolean = false;

    /**
     * 是否启用快速分组
     *
     * @memberof MDViewBase
     */
    public isEnableQuickGroup!: boolean;

    /**
     * 快速分组数据对象
     *
     * @memberof MDViewBase
     */
    public quickGroupData: any;

    /**
     * 快速分组是否有抛值
     *
     * @memberof MDViewBase
     */
    public isEmitQuickGroupValue: boolean = false;

    /**
     * 快速分组模型数据
     *
     * @memberof MDViewBase
     */
    public quickGroupModel: Array<any> = [];

    /**
     * 快速分组代码表
     *
     * @type {boolean}
     * @memberof MDViewBase
     */
    public quickGroupCodeList: any = null;

    /**
     * 搜索表单实例
     *
     * @type {IBizSearchFormModel}
     * @memberof MDViewBase
     */
    public searchFormInstance !: IPSDESearchForm;

    /**
     * 快速搜索表单实例
     *
     * @type {IBizSearchFormModel}
     * @memberof MDViewBase
     */
    public quickSearchFormInstance !: IPSDESearchForm;

    /**
     * 搜索栏实例
     *
     * @type {IBizSearchBarModel}
     * @memberof MDViewBase
     */
    public searchBarInstance!: IPSSearchBar;

    /**
     * 可搜索字段名称
     * 
     * @type {(string)}
     * @memberof MDViewBase
     */
    public placeholder: string = "";

    /**
     * 加载快速分组模型
     *
     * @memberof MDViewBase
     */
    public async loadQuickGroupModel() {
        try {
            await this.quickGroupCodeList?.fill?.();
            if (!(this.quickGroupCodeList && this.quickGroupCodeList.codeName)) {
                return;
            }
            let res: any = await this.codeListService.getDataItems({ tag: this.quickGroupCodeList.codeName, type: this.quickGroupCodeList.codeListType, data: this.quickGroupCodeList });
            this.quickGroupModel = this.handleDynamicData(JSON.parse(JSON.stringify(res)));
        } catch (error: any) {
            console.log(`----${this.quickGroupCodeList.codeName}----代码表不存在`);
        }
    }

    /**
     * 处理快速分组模型动态数据部分(%xxx%)
     *
     * @memberof MDViewBase
     */
    public handleDynamicData(inputArray: Array<any>) {
        if (inputArray.length > 0) {
            const defaultSelect: any = this.getQuickGroupDefaultSelect();
            if (defaultSelect) {
                let select = inputArray.find((item: any) => { return item.value === defaultSelect.value; });
                if (select) select.default = true;
            }
            inputArray.forEach((item: any) => {
                if (item.data && Object.keys(item.data).length > 0) {
                    Object.keys(item.data).forEach((name: any) => {
                        let value: any = item.data[name];
                        if (value && typeof (value) == 'string' && value.startsWith('%') && value.endsWith('%')) {
                            const key = (value.substring(1, value.length - 1)).toLowerCase();
                            if (this.context[key]) {
                                value = this.context[key];
                            } else if (this.viewparams[key]) {
                                value = this.viewparams[key];
                            }
                        }
                        item.data[name] = value;
                    })
                }
            })
        }
        return inputArray;
    }

    /**
     * 获取快速分组默认选中项
     *
     * @memberof MDViewBase
     */
    public getQuickGroupDefaultSelect() {
        let defaultSelect: any = null;
        const codeItems: Array<IPSCodeItem> = (this.quickGroupCodeList as IPSAppCodeList).getPSCodeItems() || [];
        if (codeItems.length > 0) {
            for (const item of codeItems) {
                const childItems = item.getPSCodeItems?.() || [];
                if (childItems.length > 0) {
                    defaultSelect = childItems.find((_item: any) => { return _item.default; });
                }
                if (item.default || defaultSelect) {
                    defaultSelect = item;
                    break;
                }
            }
        }
        return defaultSelect;
    }

    /**
     * 快速分组值变化
     *
     * @memberof MDViewBase
     */
    public quickGroupValueChange($event: any) {
        if ($event) {
            this.quickGroupData = $event.data;
            if (this.isEmitQuickGroupValue) {
                this.onSearch($event);
            }
        }
        this.isEmitQuickGroupValue = true;
    }

    /**
     * 快速搜索
     *
     * @param {*} $event
     * @memberof MDViewBase
     */
    public onSearch($event: any): void { }

    /**
     * 快速搜索栏数据对象
     *
     * @memberof MDViewBase
     */
    public quickFormData: any;

    /**
     * 初始化日历视图实例
     * 
     * @param opts 
     * @memberof MDViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        this.searchFormInstance = ModelTool.findPSControlByName('searchform', this.viewInstance.getPSControls() || []) as IPSDESearchForm;
        this.quickSearchFormInstance = ModelTool.findPSControlByName('quicksearchform', this.viewInstance.getPSControls() || []) as IPSDESearchForm;
        this.searchBarInstance = ModelTool.findPSControlByType('SEARCHBAR', this.viewInstance.getPSControls() || []) as IPSSearchBar;
        this.isEnableQuickGroup = this.viewInstance?.enableQuickGroup;
        this.quickGroupCodeList = (this.viewInstance as IPSAppDEMultiDataView)?.getQuickGroupPSCodeList?.();
        let _this: any = this;
        if (this.isEnableQuickGroup) {
            this.codeListService = new CodeListServiceBase({ $store: _this.$store });
            await this.loadQuickGroupModel();
        }
    }

    /**
     *  多数据视图初始化
     *
     * @memberof MDViewBase
     */
    public viewInit() {
        super.viewInit();
        // 初始化属性值
        this.query = '';
        this.isExpandSearchForm = this.viewInstance?.expandSearchForm ? true : false;
        this.initQuickSearchPlaceholder();
    }

    /**
     *  初始化快速搜索栏空白填充内容
     *
     * @memberof MDViewBase
     */
    public initQuickSearchPlaceholder() {
        const quickSearchFields: Array<IPSAppDEField> = (this.viewInstance as IPSAppDEMultiDataView).getPSAppDataEntity()?.getQuickSearchPSAppDEFields() || [];
        if (quickSearchFields.length > 0) {
            quickSearchFields.forEach((field: IPSAppDEField, index: number) => {
                const _field: IPSAppDEField | null | undefined = (this.viewInstance as IPSAppDEMultiDataView).getPSAppDataEntity()?.findPSAppDEField(field.codeName);
                if (_field) {
                    this.placeholder += (_field.logicName + (index === quickSearchFields.length-1 ? '' : ', '))
                }
            })
        }
    }

    /**
     * 快速搜索栏值变化
     *
     * @memberof MDViewBase
     */
    public quickFormValueChange($event: any) {
        this.quickFormData = $event;
        this.onSearch($event);
    }

    /**
        * 视图实例
        * 
        * @memberof MDViewBase
        */
    public viewInstance!: any;

    /**
     * 渲染快速分组
     * 
     * @memberof MDViewBase
     */
    public renderQuickGroup() {
        if (!this.viewInstance?.enableQuickGroup) {
            return;
        }
        let counterService: any;
        if (this.viewInstance?.getPSAppCounterRef?.()?.id) {
            counterService = Util.findElementByField(this.counterServiceArray, 'id', this.viewInstance?.getPSAppCounterRef?.()?.id)?.service;
        }
        return <div class="quick-group-container" slot="quickGroupSearch">
            <app-quick-group items={this.quickGroupModel} on-valuechange={this.quickGroupValueChange.bind(this)} counterService={counterService}></app-quick-group>
        </div>;
    }

    /**
     * 渲染快速搜索
     * 
     * @memberof MDViewBase
     */
    public renderQuickSearch() {
        if (!this.viewInstance?.enableQuickSearch) {
            return;
        }
        return !this.isExpandSearchForm ? <i-input slot="quickSearch" className='pull-left' style='max-width: 400px;margin-top:6px;padding-left: 24px' search enter-button on-on-search={($event: any) => this.onSearch($event)} v-model={this.query} placeholder={this.placeholder} /> : null;
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof GridViewBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        if (controlInstance.controlType == 'SEARCHFORM') {
            Object.assign(targetCtrlParam.dynamicProps, {
                isExpandSearchForm: this.isExpandSearchForm
            });
        }
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }

    /**
     * 渲染搜索表单
     * 
     * @memberof MDViewBase
     */
    public renderSearchForm() {
        if (!this.searchFormInstance) {
            return
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.searchFormInstance);
        return this.$createElement(targetCtrlName, { slot: 'searchForm', props: targetCtrlParam, ref: this.searchFormInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 渲染快速搜索表单
     * 
     * @memberof MDViewBase
     */
    public renderQuickSearchForm() {
        if (!this.quickSearchFormInstance) {
            return
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.quickSearchFormInstance);
        return this.$createElement(targetCtrlName, { slot: 'quickSearchForm', props: targetCtrlParam, ref: this.quickSearchFormInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 渲染搜索栏
     * 
     * @memberof MDViewBase
     */
    public renderSearchBar() {
        if (!this.searchBarInstance) {
            return
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.searchBarInstance);
        Object.assign(targetCtrlParam.dynamicProps, {
            isExpandSearchForm: this.isExpandSearchForm
        });
        return this.$createElement(targetCtrlName, { slot: 'searchBar', props: targetCtrlParam, ref: this.searchBarInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 部件事件
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * 
     * @memberof MDViewBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        super.onCtrlEvent(controlname, action, data);
        if (Object.is(controlname, 'quicksearchform') && Object.is(action, 'valuechange')) {
            this.quickFormValueChange(data);
        }
    }
}
