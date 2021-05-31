import { CodeListServiceBase, ModelTool, Util } from 'ibiz-core';
import { IPSDESearchForm, IPSSearchBar, IPSAppDEMultiDataView, IPSAppCodeList, IPSCodeItem, IPSAppDEField, IPSDEMobMDCtrl, IPSDEListItem, IPSDEListDataItem } from '@ibiz/dynamic-model-api';
import { MainViewBase } from "./main-view-base";

/**
 * 多数据视图基类
 *
 * @export
 * @class MDViewBase
 * @extends {MainViewBase}
 */
export class MDViewBase extends MainViewBase {

   /**
    * 视图实例
    * 
    * @memberof MDViewBase
    */
    public viewInstance!: any;

    /**
     * 多数据部件是否单选
     *
     * @type {boolean}
     * @memberof MDViewBase
     */
    public isSingleSelect?: boolean;

    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof MDViewBase
     */
    public codeListService!: CodeListServiceBase;

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
     * @type {IBizMobSearchFormModel}
     * @memberof MDViewBase
     */
    public searchFormInstance !: IPSDESearchForm;

    /**
     * 快速搜索表单实例
     *
     * @type {IBizMobSearchFormModel}
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
                this.onSearch();
            }
        }
        this.isEmitQuickGroupValue = true;
    }

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
     * 引擎初始化
     *
     * @param {*} [opts={}] 引擎参数
     * @memberof MDViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
            return;
        }
        if (this.engine) {
            let engineOpts = Object.assign({
                opendata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                    this.opendata(args, fullargs, params, $event, xData);
                },
                newdata: (args: any[], fullargs?: any[], params?: any, $event?: any, xData?: any) => {
                    this.newdata(args, fullargs, params, $event, xData);
                },
            }, opts)
            if (this.searchFormInstance?.name && this.$refs[this.searchFormInstance.name]) {
                engineOpts.searchform = ((this.$refs[this.searchFormInstance.name] as any).ctrl);
            } else if (this.quickSearchFormInstance?.name && this.$refs[this.quickSearchFormInstance.name]) {
                engineOpts.searchform = ((this.$refs[this.quickSearchFormInstance.name] as any).ctrl);
            }
            if (this.searchBarInstance?.name && this.$refs[this.searchBarInstance.name]) {
                engineOpts.searchbar = ((this.$refs[this.searchBarInstance.name] as any).ctrl);
            }
            super.engineInit(engineOpts);
        }
    }


    /**
     * 初始化多数据视图实例
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
        this.quickGroupCodeList = (this.viewInstance as IPSAppDEMultiDataView).getQuickGroupPSCodeList();
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
    public async viewInit() {
        super.viewInit();
        // 初始化属性值
        this.query = '';
        this.isEnableQuickGroup = this.viewInstance?.enableQuickGroup;
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
                    this.placeholder += (_field.logicName + (index === quickSearchFields.length - 1 ? '' : ', '))
                }
            })
        }
    }

    /**
     * 可搜索字段名称
     * 
     * @type {(string)}
     * @memberof MDViewBase
     */
    public placeholder: string = "";

    /**
     * 快速搜索值变化
     *
     * @param {*} event
     * @returns
     * @memberof MDViewBase
     */
    public async quickValueChange(event: any) {
        let { detail } = event;
        if (!detail) {
            return;
        }
        let { value } = detail;
        this.query = value;
        this.viewState.next({ tag: 'mdctrl', action: 'quicksearch', data: this.query });
    }

    /**
     * 渲染快速搜索
     * 
     * @memberof MDViewBase
     */
    public renderQuickSearch() {
        if (!this.viewInstance.enableQuickSearch) {
            return;
        }
        let showfilter = this.searchFormInstance ? true : false;
        return this.model && this.enableControlUIAuth  ? <app-search-history on-quickValueChange={(event: any) => this.quickValueChange(event)} on-openSearchform={() => { }} viewmodel={this.model} showfilter={showfilter} slot="quicksearch"></app-search-history> : null;
    }

    /**
    * 部件计数
    *
    * @memberof MDViewBase
    */
    public pageTotal: number = 0;

    /**
    * 获取部件计数
    *
    * @memberof MDViewBase
    */
    public pageTotalChange($event: any) {
        this.pageTotal = $event;
    }

    /**
     * 渲染快速分组
     * 
     * @memberof MDViewBase
     */
    public renderQuickGroup() {
        if (!this.viewInstance.enableQuickGroup) {
            return;
        }
        return <app-quick-group-tab items={this.quickGroupModel} on-valuechange={this.quickGroupValueChange.bind(this)} pageTotal={this.pageTotal} slot="quickGroupSearch"></app-quick-group-tab>;
    }

    /**
     * 分类值
     *
     * @type {boolean}
     * @memberof MDViewBase
     */
    public categoryValue: any = {};

    /**
     * 排序值
     *
     * @type {boolean}
     * @memberof MDViewBase
     */
    public sortValue: any = {};

    /**
     * 排序对象
     *
     * @type {*}
     * @memberof MDViewBase
     */
    public sort: any = { asc: "", desc: "" };

    /**
     * 点击优先级加主题色
     *
     * @memberof MDViewBase
     */
    public hasColor: boolean = false;

    /**
     * 视图加载（排序|分类）
     * @memberof MDViewBase
     */
    public onViewLoad() {
        let value = Object.assign(this.categoryValue, this.sortValue);
        this.engine.onViewEvent('mdctrl', 'viewload', value);
    }

    /**
     * 分类搜索
     *
     * @param {*} value
     * @memberof MDViewBase
     */
    public onCategory(value: any) {
        Object.assign(this.categoryValue, value);
        this.onViewLoad();
    }

    /**
     * 排序
     *
     * @param {*} field
     * @memberof MDViewBase
     */
    public onSort(field: any) {
        if (this.sort.desc == field) {
            this.sort.desc = "";
            this.sortValue = {};
            this.onViewLoad();
            this.hasColor = false;
            return
        }
        if (this.sort.asc == field) {
            this.sort.asc = "";
            this.sort.desc = field;
            this.sortValue = { sort: field + ",desc" };
            this.onViewLoad();
            this.hasColor = true;
        } else {
            this.sort.asc = field;
            this.sort.desc = "";
            this.sortValue = { sort: field + ",asc" };
            this.onViewLoad();
            this.hasColor = true;
        }
    }

    /**
     * 渲染视图下拉刷新
     * 
     * @memberof ViewBase
     */
    public renderPullDownRefresh() {
        const h = this.$createElement;
        return h("ion-refresher", {
          "slot": "fixed",
          "ref": "refresher",
          "attrs": {
            "pull-factor": "0.5",
            "pull-min": "50",
            "pull-max": "100",
            "closeDuration": '280ms',
            "disabled": (!this.enableControlUIAuth) as unknown as string,
            "slot":"fixed"
          },
          "on": {
            "ionRefresh": ($event:any) => {
              this.pullDownToRefresh($event);
            }
          }
        }, [h("ion-refresher-content", {
          "attrs": {
            "pulling-icon": "arrow-down-outline",
            "pulling-text": '加载中',
            "refreshing-spinner": "circles",
            "refreshing-text": ""
          }
        })]);
    }

    /**
     * 下拉刷新
     *
     * @param {*} $event
     * @returns {Promise<any>}
     * @memberof ViewBase
     */
    public async pullDownToRefresh($event: any): Promise<any> {
        await this.refresh($event);
        const refresher:any = this.$refs.refresher;
        if(refresher){
            refresher.cancel()
        }
    }

    /**
     * 渲染搜索表单
     * 
     * @memberof MDViewBase
     */
    public renderSearchForm() {
        if (!this.searchFormInstance) {
            return;
        }
        return <van-popup slot="searchForm" get-container="#app" lazy-render={false} duration="0.2" v-model={this.searchformState} position="right" class="searchform" style="height: 100%; width: 85%;"  >
            <ion-header>
                <ion-toolbar translucent>
                    <ion-title>条件搜索</ion-title>
                </ion-toolbar>
            </ion-header>
            <div class="searchform_content">
                {this.renderSearchFormContent()}
            </div>
            <ion-footer>
                <div class="search-btn">
                    <app-mob-button
                        class="search-btn-item"
                        text="重置"
                        color="light"
                        shape="round"
                        size="small"
                        on-click={() => { this.onReset() }} />
                    <app-mob-button
                        class="search-btn-item"
                        shape="round"
                        size="small"
                        text="搜索"
                        on-click={() => { this.onSearch() }} />
                </div>
            </ion-footer>
        </van-popup>
    }

    /**
     * 搜索表单状态
     *
     * @type {get}
     * @memberof MDViewBase
     */
    get searchformState() {
        return this.$store.state.searchformStatus;
    }

    /**
     * 搜索表单状态
     *
     * @type {set}
     * @memberof MDViewBase
     */
    set searchformState(val: any) {
        this.$store.commit('setSearchformStatus', val);
    }

    /**
     * 打开搜索表单
     *
     * @memberof MDViewBase
     */
    public openSearchform() {
        let search: any = this.$refs.searchform;
        if (search?.ctrl) {
            search.ctrl.open();
        }
    }

    /**
     * 执行搜索表单
     *
     * @memberof MDViewBase
     */
    public onSearch(): void {
        this.$store.commit('setSearchformStatus', false);
        this.isExpandSearchForm = true;
        const form: any = this.$refs.searchform;
        if (form?.ctrl) {
            form.ctrl.onSearch();
        }
    }

    /**
     * 重置搜索表单
     *
     * @memberof MDViewBase
     */
    public onReset(): void {
        this.$store.commit('setSearchformStatus', false);
        this.isExpandSearchForm = false;
        const form: any = this.$refs.searchform;
        if (form?.ctrl) {
            form.ctrl.onReset();
        }
    }

    /**
     * 渲染搜索表单内容区
     * 
     * @memberof MobMDViewBase
     */
    public renderSearchFormContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.searchFormInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.searchFormInstance.name, on: targetCtrlEvent });
    }

    /**
     * 渲染多数据工具
     * 
     * @memberof MDViewBase
     */
    public renderViewHeaderButton() {
        const controlClassNames: any = {
            'default-sort': true,
            [`${Util.srfFilePath2(this.viewInstance.codeName)}-toolbar`]: true,
        };
        const mdctrl = ModelTool.findPSControlByName('mdctrl', this.viewInstance.getPSControls()) as IPSDEMobMDCtrl;
        const listItems = mdctrl?.getPSDEListItems();
        const listDataItems = mdctrl?.getPSDEListDataItems();
        return <div class="mdview-tools" slot="mdviewtools">
            <div class={controlClassNames}>
                <div class="view-tool">
                    <div class="view-tool-sorts">
                        {listItems && listItems.map((item: IPSDEListItem) => {
                            if (item.enableSort && item.itemType == 'ACTIONITEM') {
                                return <div class="view-tool-sorts-item">
                                    <span class={{ text: true, active: this.hasColor }} on-click={() => this.onSort(item?.name)}>{item?.caption}</span>
                                    <span class="sort-icon" on-click={() => this.onSort(item?.name)}>
                                        <ion-icon class={{ 'ios': true, 'hydrated': true, 'sort-select': this.sort.asc == item?.name }} name="chevron-up-outline" ></ion-icon>
                                        <ion-icon class={{ 'ios': true, 'hydrated': true, 'sort-select': this.sort.desc == item?.name }} name="chevron-down-outline" ></ion-icon>
                                    </span>
                                </div>
                            }
                        })}
                    </div>
                </div>
            </div>
            <div class="mdview-tools-select">
                {listItems && listItems.map((item: IPSDEListItem) => {
                    // 列表项需要代码表，需要根据key去数据项中找到对应的然后去取代码表
                    const dataItem = listDataItems?.find((dataItem: IPSDEListDataItem) => {
                        return dataItem.name == item.name.toLowerCase();
                    })
                    const codelist = dataItem?.getFrontPSCodeList?.() as IPSAppCodeList;
                    let items: Array<any> = [];
                    if (codelist && codelist?.getPSCodeItems?.()) {
                        const codeItems = codelist?.getPSCodeItems() as IPSCodeItem[];
                        codeItems.forEach((codeitem: IPSCodeItem) => {
                            items.push({
                                value: codeitem?.value,
                                label: codeitem?.text
                            })
                        })
                    }
                    if (codelist?.codeListType == 'STATIC' && item.itemType == 'TEXTITEM') {
                        return <app-van-select name={'n_' + item.name.toLowerCase() + '_eq'} title={item?.caption} items={items} on-onConfirm={this.onCategory.bind(this)}></app-van-select>
                    }
                })}
            </div>
        </div>
    }

    /**
     * 多选状态改变事件
     *
     * @memberof MDViewBase
     */
    public isChooseChange(value: any) {
        this.isChoose = value;
    }

    /**
     * 多选状态
     *
     * @memberof MDViewBase
     */
    public isChoose = false;

    /**
     * 取消选择状态
     * @memberof MDViewBase
     */
    public cancelSelect() {
        this.isChooseChange(false);
    }


    /**
     * 当前滚动条是否是停止状态
     *
     * @memberof MDViewBase
     */
    public isScrollStop = true;

    /**
     * 是否应该显示返回顶部按钮
     *
     * @memberof MDViewBase
     */
    public isShouleBackTop = false;

    /**
     * 返回顶部
     *
     * @memberof MDViewBase
     */
    public onScrollToTop() {
        let ionScroll: any = this.$refs.ionScroll;
        if (ionScroll && ionScroll.scrollToTop && Util.isFunction(ionScroll.scrollToTop)) {
            ionScroll.scrollToTop(500);
        }
    }

    /**
     * onScroll滚动结束事件
     *
     * @memberof MDViewBase
     */
    public onScrollEnd() {
        this.isScrollStop = true;
    }

    /**
     * onScroll滚动事件
     *
     * @memberof MDViewBase
     */
    public async onScroll(e: any) {
        this.isScrollStop = false;
        if (e.detail.scrollTop > 600) {
            this.isShouleBackTop = true;
        } else {
            this.isShouleBackTop = false;
        }
        // 多数据、选择多数据有额外逻辑scroll_block
    }

    /**
     * 绘制多数据置顶
     * 
     * @memberof MDViewBase
     */
    public renderScrollTool() {
        return <div class="scroll_tool" slot="scrollTool">
            {this.isShouleBackTop ? <div class="scrollToTop" on-click={this.onScrollToTop.bind(this)} style={{ right: this.isScrollStop ? '-18px' : '-70px' }} > <van-icon name="back-top" /></div> : null}
        </div>
    }


}
