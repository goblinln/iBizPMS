import { ViewTool, Util, ModelTool, MobMdctrlControlInterface, AppServiceBase } from 'ibiz-core';
import { Subscription } from 'rxjs';
import { AppMobMDCtrlService } from '../ctrl-service';
import { MDControlBase } from "./md-control-base";
import { AppViewLogicService } from '../app-service';
import { IPSDEMobMDCtrl, IPSAppDataEntity, IPSAppDEField, IPSDEUIAction, IPSUIAction, IPSUIActionGroupDetail, IPSCodeItem, DynamicInstanceConfig, IPSDEListDataItem } from '@ibiz/dynamic-model-api';

/**
 * 多数据部件基类
 *
 * @export
 * @class AppControlBase
 * @extends {MDCtrlControlBase}
 */
export class MobMDCtrlControlBase extends MDControlBase implements MobMdctrlControlInterface{

    /**
     * 菜单部件实例
     * 
     * @memberof MobMDCtrlControlBase
     */
    public controlInstance!: IPSDEMobMDCtrl;

    /**
     * 部件挂载
     *
     * @memberof MobMDCtrlControlBase
     */
    public ctrlInit(args?: any) {
        super.ctrlInit();
    }

    /**
     * 初始加载
     *
     * @type {string}
     * @memberof MobMDCtrlControlBase
     */
    public isFirstLoad: boolean = true;

    /**
     * 部件样式
     *
     * @type {string}
     * @memberof MobMDCtrlControlBase
     */
    protected controlStyle!: string | 'ICONVIEW' | 'LISTVIEW' | 'SWIPERVIEW' | 'LISTVIEW2' | 'LISTVIEW3' | 'LISTVIEW4';

    /**
     * 列表模式（列表导航、列表）
     *
     * @type {string}
     * @memberof MobMDCtrlControlBase
     */
    protected listMode?: string | 'LISTEXPBAR' | 'LIST' | 'SELECT';

    /**
     * 列表选中项的索引
     *
     * @type {number}
     * @memberof MobMDCtrlControlBase
     */
    protected listItem: number = 0;

    /**
    * 上级传递的选中项
    * @type {Array}
    * @memberof MobMDCtrlControlBase
    */
    public selectedData?: Array<any>;

    /**
     * 部件行为--update
     *
     * @type {string}
     * @memberof MobMDCtrlControlBase
     */
    protected needLoadMore?: boolean;

    /**
    * 新建打开视图
    *
    * @type {Function}
    * @memberof MobMDCtrlControlBase
    */
    public newdata?: Function;


    /**
    * 打开视图
    *
    * @type {Function}
    * @memberof MobMDCtrlControlBase
    */
    public opendata?: Function;

    /**
    * 加载显示状态
    *
    * @type {boolean}
    * @memberof MobMDCtrlControlBase
    */
    public loadStatus: boolean = false;

    /**
    * 关闭行为
    *
    * @type {Function}
    * @memberof MobMDCtrlControlBase
    */
    public close?: Function;

    /**
    * 是否显示加载文字
    *
    * @type {boolean}
    * @memberof MobMDCtrlControlBase
    */
    public isNeedLoaddingText?: boolean = true;

    /**
    * 是否为临时模式
    *
    * @type {boolean}
    * @memberof MobMDCtrlControlBase
    */
    public isTempMode?: boolean;

    /**
    * 存放多数据选择数组（多选）
    *
    * @type {array}
    * @memberof MobMDCtrlControlBase
    */
    public checkboxList: Array<string> = [];

    /**
    * 是否为分组模式
    *
    * @type {boolean}
    * @memberof MobMDCtrlControlBase
    */
    public isEnableGroup: boolean = false;

    /**
    * 代码表分组细节
    *
    * @type {Object}
    * @memberof MobMDCtrlControlBase
    */
    public group_detail: any = [];

    /**
    * 分组模式
    *
    * @type {string}
    * @memberof MobMDCtrlControlBase
    */
    public group_mode = '';

    /**
    * 分组标识
    *
    * @type {array}
    * @memberof MobMDCtrlControlBase
    */
    public group_field: string = '';

    /**
    * 分组数据
    *
    * @type {array}
    * @memberof MobMDCtrlControlBase
    */
    public group_data?: any = [];

    /**
     * 应用状态事件
     *
     * @public
     * @type {(Subscription | undefined)}
     * @memberof MobMDCtrlControlBase
     */
    public appStateEvent: Subscription | undefined;

    /**
    * 存放数据选择数组(单选)
    *
    * @type {object}
    * @memberof MobMDCtrlControlBase
    */
    public radio: any = '';

    /**
    * 判断底部数据是否全部加载完成，若为真，则 bottomMethod 不会被再次触发
    *
    * @type {number}
    * @memberof MobMDCtrlControlBase
    */
    get allLoaded() {
        return ((this.curPage + 1) * this.limit) >= this.totalRecord ? true : false;
    }

    /**
    * searchKey 搜索关键字
    *
    * @type {string}
    * @memberof MobMDCtrlControlBase
    */
    public searchKey: string = '';

    /**
    * 列表数组
    *
    * @param {Array<any>}
    * @memberof MobMDCtrlControlBase
    */
    public items: Array<any> = [];

    /**
    * 选中数组
    *
    * @param {Array<any>}
    * @memberof MobMDCtrlControlBase
    */
    public selectedArray: Array<any> = [];

    /**
    * 搜索行为
    *
    * @param {string}
    * @memberof MobMDCtrlControlBase
    */
    public searchAction?: string;


    /**
    * 单选选择值
    *
    * @param {string} 
    * @memberof MobMDCtrlControlBase
    */
    public selectedValue: string = "";

    /**
    * 部件排序对象
    *
    * @param {object} 
    * @memberof MobMDCtrlControlBase
    */
    public sort: any = {};

    /**
      * 是否展示多选
      *
      * @memberof MobMDCtrlControlBase
      */
    public isChoose?: boolean = false;

    /**
     * 上拉加载更多数据
     *
     * @memberof MobMDCtrlControlBase
     */
    public async loadBottom(): Promise<any> {
        if (this.allLoaded) {
            return;
        }
        this.curPage++;
        let params = {};
        if (this.viewparams) {
            Object.assign(params, this.viewparams);
        }
        Object.assign(params, { query: this.searchKey, page: this.curPage, size: this.limit });
        let response: any = await this.load(params, 'bottom', false);
        let loadmoreBottom: any = this.$refs.loadmoreBottom;
        if (loadmoreBottom) {
            loadmoreBottom.complete();
        }
    }

    /**
     * 删除
     *
     * @param {any[]} datas
     * @returns {Promise<any>}
     * @memberof MobMDCtrlControlBase
     */
    public async remove(datas: any[]): Promise<any> {
        let arg: any = {};
        let keys: Array<string> = [];
        let infoStr: string = '';
        const appDataEntity = this.controlInstance.getPSAppDataEntity() as IPSAppDataEntity;
        datas.forEach((data: any, index: number) => {
            keys.push(data[appDataEntity.codeName.toLowerCase()]);
            if (index < 5) {
                if (!Object.is(infoStr, '')) {
                    infoStr += '、';
                }
                let majorField: any = ModelTool.getAppEntityMajorField(appDataEntity);
                infoStr += data[majorField];
            }
        });
        if (datas.length <= 0) {
            this.$Notice.error(this.$t('app.commonWords.chooseOne'));
            return
        }
        if (datas.length < 5) {
            infoStr = infoStr + this.$t('app.message.totle') + datas.length + this.$t('app.message.data');
        } else {
            infoStr = infoStr + '...' + this.$t('app.message.totle') + datas.length + this.$t('app.message.data');
        }
        return new Promise((resolve, reject) => {
            const _remove = async () => {
                let _context: any = { [appDataEntity.codeName.toLowerCase()]: keys.join(';') }
                const response: any = await this.service.delete(this.removeAction, Object.assign({}, this.context, _context), arg, this.showBusyIndicator);
                if (response && response.status === 200 && response.data) {
                    this.$Notice.success((this.$t('app.message.deleteSccess') as string));
                    this.load();
                    resolve(response);
                } else {
                    this.$Notice.error(response.message ? response.message :(this.$t('app.message.deleteFail') as string));
                    reject(response);
                }
            }

            this.$dialog.confirm({
                title: (this.$t('app.message.warning') as string),
                message: this.$t('app.message.confirmToDelete') + infoStr + ',' + this.$t('app.message.unrecoverable') + '？',
            }).then(() => {
                _remove();
            }).catch(() => {
            });
        });
    }

    /**
     * 刷新数据
     *
     * @returns {Promise<any>}
     * @memberof MobMDCtrlControlBase
     */
    public refresh(): Promise<any> {
        return new Promise((resolve: any, reject: any) => {
            this.load().then((res: any) => {
                resolve(res);
            }).catch((error: any) => {
                reject(error);
            })
        })
    }

    /**
     * 快速搜索
     *
     * @param {string} query
     * @returns {Promise<any>}
     * @memberof MobMDCtrlControlBase
     */
    public async quickSearch(query: string): Promise<any> {
        this.searchKey = query;
        this.curPage = 0;
        const response = await this.load(Object.assign({ query: query }, { page: this.curPage, size: this.limit }), "init");
        return response;
    }

    /**
     * 数据加载
     *
     * @private
     * @param {*} [data={}]
     * @param {string} [type=""]
     * @returns {Promise<any>}
     * @memberof MobMDCtrlControlBase
     */
    public async load(data: any = {}, type: string = "", isloadding = this.showBusyIndicator): Promise<any> {
        if (!data.page) {
            Object.assign(data, { page: this.curPage });
        }
        if (!data.size) {
            Object.assign(data, { size: this.limit });
        }
        //部件排序
        if (this.sort) {
            Object.assign(data, this.sort);
        }
        //视图排序
        if (data.data && data.data.sort) {
            Object.assign(data, { sort: data.data.sort });
        }
        const parentdata: any = {};
        this.onCtrlEvent(this.controlInstance.name, 'beforeload', parentdata);
        Object.assign(data, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
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
            if (data.hasOwnProperty("srfwf")) {
                Object.assign(tempViewParams, { srfinsttag: "__srfstdinst__" });
            }
        }
        Object.assign(tempViewParams, JSON.parse(JSON.stringify(this.viewparams)));
        Object.assign(data, { viewparams: tempViewParams });
        this.onControlRequset('load', { ...this.context },  data);
        let response: any;
        try {
            response = await this.service.search(this.fetchAction, this.context, data, isloadding);
        } catch (error) {
            this.onControlResponse('load',response);
            this.$Notice.error(error?.data?.message || this.$t('app.commonWords.sysException') as string);
        }
        this.bottomLoadding = false;
        if (!response || response.status !== 200) {
            this.$Notice.error(response?.error?.message || this.$t('app.commonWords.sysException') as string);
            this.onControlResponse('load',response);
            return response;
        }
        this.onControlResponse('load',response);
        this.onCtrlEvent(this.controlInstance.name, 'load', response.data ? response.data : []);
        this.totalRecord = response.total;
        this.onCtrlEvent(this.controlInstance.name, 'totalRecordChange', this.totalRecord);
        if (type == 'top') {
            this.items = [];
            this.items = response.data;
        } else if (type == 'bottom') {
            for (let i = 0; i < response.data.length; i++) {
                this.items.push(response.data[i]);
            }
        } else {
            this.items = [];
            this.items = response.data;
        }
        this.items.forEach((item: any) => {
            // 计算是否选中
            let index = this.selectedArray.findIndex((temp: any) => { return temp.srfkey == item.srfkey });
            if (index != -1 || Object.is(this.selectedValue, item.srfkey)) {
                item.checked = true;
            } else {
                item.checked = false;
            }
            Object.assign(item, this.getActionState(item));
            // 计算权限   
            this.setSlidingDisabled(item);
        });
        if (this.isEnableGroup) {
            this.group();
        }
        this.isFirstLoad = false;
        return response;
    }

    /**
     * 计算部件所需参数
     *
     * @param {*} controlInstance 部件模型对象
     * @param {*} item 列表行数据
     * @returns
     * @memberof ListControlBase
     */
    public computeTargetCtrlData(controlInstance: any, item?: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.dynamicProps, {
            navdatas: [item],
        })
        Object.assign(targetCtrlParam.staticProps, {
            transformData: this.transformData,
            isLoadDefault: true,
            opendata: this.opendata,
            newdata: this.newdata,
            remove: this.remove,
            refresh: this.refresh,
            dataMap: this.dataMap,
        })
        targetCtrlEvent['ctrl-event'] = ({ controlname, action, data }: { controlname: string, action: string, data: any }) => {
            this.onCtrlEvent(controlname, action, { item: item, data: data });
        };
        return { targetCtrlName, targetCtrlParam, targetCtrlEvent };
    }

    /**
     * 获取界面行为权限状态
     *
     * @param {*} data 当前列表行数据
     * @memberof MobMDCtrlControlBase
     */
    public getActionState(data: any) {
        let tempActionModel: any = JSON.parse(JSON.stringify(this.actionModel));
        // let targetData: any = this.transformData(data);
        ViewTool.calcActionItemAuthState(data, tempActionModel, this.appUIService);
        return tempActionModel;
    }

    /**
     * 初始化界面行为模型
     *
     * @type {*}
     * @memberof MobMDCtrlControlBase
     */
    public initCtrlActionModel() {
        if (this.controlInstance.getPSDEUIActionGroup?.() && this.controlInstance.getPSDEUIActionGroup?.()?.getPSUIActionGroupDetails?.()) {
            this.controlInstance.getPSDEUIActionGroup?.()?.getPSUIActionGroupDetails()?.forEach((detail: IPSUIActionGroupDetail) => {
                this.initCtrlActionModelDetail(detail);
            });
        }
        if (this.controlInstance.getPSDEUIActionGroup2?.() && this.controlInstance.getPSDEUIActionGroup2?.()?.getPSUIActionGroupDetails?.()) {
            this.controlInstance.getPSDEUIActionGroup2?.()?.getPSUIActionGroupDetails()?.forEach((detail: IPSUIActionGroupDetail) => {
                this.initCtrlActionModelDetail(detail);
            });
        }
    }

    /**
     * 初始化数据映射
     * 
     * @memberof ListControlBase
     */
    public initDataMap() {
        const dataItems: IPSDEListDataItem[] | null = this.controlInstance.getPSDEListDataItems();
        if (dataItems && dataItems.length > 0) {
            dataItems.forEach((dataItem: IPSDEListDataItem) => {
                this.dataMap.set(dataItem.name,{ customCode: dataItem.customCode ? true : false });
            });
        };
    }

    /**
     * 初始化界面行为模型2
     *
     * @type {*}
     * @memberof MobMDCtrlControlBase
     */
    public initCtrlActionModelDetail(detail: IPSUIActionGroupDetail) {
        let item = detail.getPSUIAction() as IPSUIAction;
        let tempData: any = {};
        tempData.name = item.uIActionTag;
        tempData.disabled = false;
        tempData.visabled = true;
        tempData.noprivdisplaymode = (item as IPSDEUIAction)?.noPrivDisplayMode;
        tempData.dataaccaction = item?.dataAccessAction;
        tempData.target = item?.actionTarget;
        tempData.icon = item?.getPSSysImage()?.cssClass;
        tempData.isShowCaption = true;
        tempData.isShowIcon = true;
        if (this.controlInstance.getPSDEUIActionGroup2?.() && this.controlInstance.getPSDEUIActionGroup2?.()?.getPSUIActionGroupDetails()) {
            this.controlInstance.getPSDEUIActionGroup2?.()?.getPSUIActionGroupDetails()?.forEach((action: IPSUIActionGroupDetail) => {
                if (action.getPSUIAction()?.codeName == item.codeName) {
                    tempData.isShowCaption = action.showCaption;
                    tempData.isShowIcon = action.showIcon;
                }
            });
        }
        this.actionModel[item.uIActionTag] = tempData;
    }

    /**
    * 判断列表项左滑右滑禁用状态
    *
    * @memberof MobMDCtrlControlBase
    */
    public setSlidingDisabled(item: any) {
        item.sliding_disabled = true;
        Object.keys(this.actionModel).forEach((key, index) => {
            if (item[key].visabled && item.sliding_disabled) {
                item.sliding_disabled = false;
            }
        })

    }

    /**
     * 列表项左滑右滑触发行为
     *
     * @param {*} $event 点击鼠标事件源
     * @param {*} detail 界面行为模型对象
     * @param {*} item 行数据
     * @memberof MobMDCtrlControlBase
     */
    public mdctrl_click($event: any, detail: any, item: any): void {
        $event.stopPropagation();
        this.handleActionClick(item, $event, detail);
        this.closeSlidings(item);
    }

    /**
     * 处理操作列点击
     * 
     * @param {*} data 当前列数据
     * @param {*} event 事件源
     * @param {*} detail 界面行为模型对象
     * @memberof MobMobMDCtrlControlBase
     */
    public handleActionClick(data: any, $event: any, detail: any) {
        const { name } = this.controlInstance;
        const getPSAppViewLogics = this.controlInstance.getPSAppViewLogics();
        AppViewLogicService.getInstance().executeViewLogic(`${name}_${detail.name}_click`, $event, this, data, getPSAppViewLogics);
    }

    /**
     * 下拉刷新
     *
     * @returns {Promise<any>}
     * @memberof MobMDCtrlControlBase
     */
    public async pullDownToRefresh(): Promise<any> {
        this.curPage = 0;
        let params = {};
        if (this.viewparams) {
            Object.assign(params, this.viewparams);
        }
        Object.assign(params, { query: this.searchKey, curPage: this.curPage, limit: this.limit });
        let response: any = await this.load(params, 'top');
        return response;
    }

    /**
    * 点击回调事件
    *
    * @memberof MobMDCtrlControlBase
    */
    public item_click(item: any) {
        if (this.isChoose) {
            let count = this.selectedArray.findIndex((i) => {
                return i.mobentityid == item.mobentityid;
            });
            if (count === -1) {
                this.selectedArray.push(item);
            } else {
                this.selectedArray.splice(count, 1);
            }
        } else {
            this.goPage(item)
        }
    }


    /**
    * 点击列表数据跳转
    *
    * @memberof MobMDCtrlControlBase
    */
    public goPage(item: any) {
        this.$emit('ctrl-event', { controlname: this.controlInstance.name, action: "rowclick", data: item });
    }

    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.listMode = newVal.listMode ? newVal.listMode : "LIST";
        super.onStaticPropsChange(newVal, oldVal);

        this.isSingleSelect = newVal.isSingleSelect;

    }

    /**
    * 获取多项数据
    *
    * @memberof MobMDCtrlControlBase
    */
    public getDatas(): any[] {
        return this.selectedArray;
    }

    /**
    * 获取单项数据
    *
    * @memberof MobMDCtrlControlBase
    */
    public getData(): any {
        return this.selectedArray[0];
    }

    /**
    * vue生命周期created
    *
    * @memberof MobMDCtrlControlBase
    */
    public created() {
        this.afterCreated();
    }

    /**
     * 执行created后的逻辑
     *
     *  @memberof MobMDCtrlControlBase
     */
    protected afterCreated() {
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(this.name, tag)) {
                    return;
                }
                if (Object.is(action, "load")) {
                    this.curPage = 0;
                    this.load(Object.assign(data, { page: this.curPage, size: this.limit }), "init");
                }
                if (Object.is(action, "search")) {
                    this.curPage = 0;
                    this.load(Object.assign(data, { page: this.curPage, size: this.limit }), "init");
                }
                if (Object.is(action, "quicksearch")) {
                    this.quickSearch(data);
                }
                if (Object.is(action, "refresh")) {
                    this.refresh();
                }
                if (Object.is(action, "save")) {
                    this.ctrlEvent({
                        controlname: this.controlInstance.name,
                        action: 'save',
                        data: this.getDatas(),
                    });
                }
            });
        }
    }

    /**
     * ion-item-sliding拖动事件
     *
     * @memberof MobMDCtrlControlBase
     */
    public ionDrag() {
        this.$store.commit('setPopupStatus', false)
    }

    /**
     * 滚动条事件（计算是否到底部）
     *
     * @memberof MobMDCtrlControlBase
     */
    public scroll(e: any) {
        let list: any = this.$refs.mdctrl;
        if (list) {
            let scrollTop = list.scrollTop;
            let clientHeight = list.clientHeight;
            let scrollHeight = list.scrollHeight;
            if (scrollHeight > clientHeight && scrollTop + clientHeight === scrollHeight) {
                if (!this.allLoaded) {
                    this.bottomLoadding = true;
                    this.loadBottom();
                }
            }
        }
    }

    /**
     * 底部加载状态
     *
     * @memberof MobMDCtrlControlBase
     */
    public bottomLoadding = false;

    /**
     * vue 生命周期
     *
     * @memberof MobMDCtrlControlBase
     */
    public beforeDestroy() {
        let list: any = this.$refs.mdctrl;
        if (list) {
            list.removeEventListener('touchend', () => {
                this.$store.commit('setPopupStatus', true)
            })
        }
    }




    /**
     * 关闭列表项左滑右滑
     * @memberof MobMDCtrlControlBase
     */
    public closeSlidings(item: any) {
        const ele: any = this.$refs[item.srfkey];
        if (ele && this.$util.isFunction(ele.closeOpened)) {
            ele.closeOpened();
        }
    }

    /**
     * 单选选中变化
     * 
     * @memberof MobMDCtrlControlBase
     */
    public onSimpleSelChange(item: any = {}) {
        this.onCtrlEvent(this.controlInstance.name, 'selectionchange', [item]);
        this.selectedValue = item.srfkey;
        this.selectedArray = [];
    }



    /**
     * 选中或取消事件
     *
     * @memberof MobMDCtrlControlBase
     */
    public checkboxSelect(item: any) {
        const appDataEntity = this.controlInstance.getPSAppDataEntity() as IPSAppDataEntity;
        item.checked = !item.checked
        let count = this.selectedArray.findIndex((_item: any) => {
            const majorField: any = ModelTool.getAppEntityMajorField(appDataEntity);
            return _item[majorField.toLowerCase()] == item[majorField.toLowerCase()];
        });
        if (count == -1) {
            this.selectedArray.push(item);
        } else {
            this.selectedArray.splice(count, 1);
        }
        let _count = Object.is(this.items.length, this.selectedArray.length) ? 1 : this.selectedArray.length > 0 ? 2 : 0;
        this.onCtrlEvent(this.controlInstance.name, 'checkBoxChange', _count);
        this.$forceUpdate();
    }
    /** 
     * checkbox 选中回调
     *
     * @memberof MobMDCtrlControlBase
     */
    public checkboxChange(data: any) {
        const appDataEntity = this.controlInstance.getPSAppDataEntity() as IPSAppDataEntity;
        let { detail } = data;
        if (!detail) {
            return;
        }
        let { value } = detail;
        const selectItem = this.items.find((item: any) => { return Object.is(item.srfkey, value) });
        if (detail.checked) {
            this.selectedArray.push(selectItem);
        } else {
            const index = this.selectedArray.findIndex((i: any) => { return i.srfkey === selectItem.srfkey })
            if (index != -1) {
                this.selectedArray.splice(index, 1)
            }
        }
        this.onCtrlEvent(this.controlInstance.name, 'selectionchange', this.selectedArray);
    }

    /**
     * 全选事件
     *
     * @memberof MobMDCtrlControlBase
     */
    public checkboxAll(value: any) {
        for (let index = 0; index < this.items.length; index++) {
            const item = this.items[index];
            this.items[index].checked = value;
        }
        if (value) {
            this.selectedArray = [...this.items];
        } else {
            this.selectedArray = [];
        }
        this.$forceUpdate();
    }

    /**
      * 部件模型数据初始化实例
      *
      * @memberof MobMDCtrlControlBase
      */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        if (!(this.Environment?.isPreviewMode)) {
            this.service = new AppMobMDCtrlService(this.controlInstance);
            await this.service.loaded(this.controlInstance);
        }
        this.initSort();
        // 分组一系列属性
        this.isEnableGroup = this.controlInstance?.enableGroup;
        this.group_mode = this.controlInstance?.groupMode;
        this.initGroupField();
        this.initGroupDetail();
        this.limit = this.controlInstance.pagingSize;
    }

    /**
     * 初始化代码表分组细节
     * 
     * @memberof MobMDCtrlControlBase
     */
    public initGroupDetail() {
        if (this.controlInstance?.getGroupPSCodeList?.() && this.controlInstance?.getGroupPSCodeList?.()?.getPSCodeItems?.()) {
          const codelist = this.controlInstance?.getGroupPSCodeList?.()?.getPSCodeItems?.();
          if (codelist?.length) {
            codelist.forEach((item:IPSCodeItem)=>{
              if (item.value && item.text) {
                this.group_detail.push({
                  "value": item.value,
                  "text": item.text
                })
              }
            });
          }
        }
    }

    /**
     * 初始化分组标识
     * 
     * @memberof MobMDCtrlControlBase
     */
    public initGroupField() {
        if (this.controlInstance?.getGroupPSAppDEField()?.codeName) {
            this.group_field = this.controlInstance?.getGroupPSAppDEField()?.codeName.toLowerCase() as string;
        }
    }

    /**
     * 初始化排序模型数据
     * 
     * @memberof MobMDCtrlControlBase
     */
    public initSort() {
        if (!this.controlInstance.noSort && this.controlInstance.getMinorSortPSAppDEField?.()) {
            let sortableField = this.controlInstance.getMinorSortPSAppDEField() as IPSAppDEField;
            this.sort = { sort: '' };
            if (sortableField.name) {
                this.sort.sort = sortableField.name.toLowerCase() + ',';
            }
            if (this.controlInstance.minorSortDir) {
                let sortDir = this.controlInstance.minorSortDir.toLowerCase();
                this.sort.sort += sortDir;
            }
        }
    }

    /**
     * 列表切换回调
     * @param {number} listIndex
     * @memberof MobMDCtrlControlBase
     */
    public switchView(listIndex: number) {
        this.items.findIndex((item, index) => {
            if (index === listIndex) {
                this.onCtrlEvent(this.controlInstance.name, "selectionchange", [item]);
            }
        })
    }

    /**
     * vant折叠面板数据
     *
     * @memberof MobMDCtrlControlBase
     */
    public activeName: Array<any> = [];

    /**
     * 只需第一次赋值面板
     *
     * @memberof MobMDCtrlControlBase
     */
    public valve: number = 0;

    /**
     * 折叠面板改变时
     *
     * @memberof MobMDCtrlControlBase
     */
    public changeCollapse($event: any) {
        this.activeName = $event;
    }

    /**
     * 分组方法
     *
     * @memberof MobMDCtrlControlBase
     */
    public group() {
        if (this.getGroupDataByCodeList && this.getGroupDataByCodeList instanceof Function && Object.is(this.group_mode, "CODELIST")) {
            this.getGroupDataByCodeList(this.items);
        } else if (this.getGroupDataAuto && this.getGroupDataAuto instanceof Function && Object.is(this.group_mode, "AUTO")) {
            this.getGroupDataAuto(this.items);
        }
    }

    /**
     * 代码表分组，获取分组数据
     *
     * @memberof MobMDCtrlControlBase
     */
    public getGroupDataByCodeList(items: any) {
        let group: Array<any> = [];
        this.group_detail.forEach((obj: any, index: number) => {
            let data: any = [];
            items.forEach((item: any, i: number) => {
                if (item[this.group_field] === obj.value) {
                    data.push(item);
                }
            })
            group.push(data);
        })
        group.forEach((arr: any, index: number) => {
            this.group_data[index] = {};
            this.group_data[index].text = this.group_detail[index].text;
            this.group_data[index].items = arr;
        })
        this.group_data.forEach((item: any, i: number) => {
            if (item.items.length == 0) {
                this.group_data.splice(i, 1);
            }
        })
        // vant 折叠面板
        if (this.valve == 0) {
            this.activeName[0] = this.group_data[0].text;
            this.valve++;
        }
    }

    /**
     * 
     * 自动分组，获取分组数据
     *
     * @memberof MobMDCtrlControlBase
     */
    public getGroupDataAuto(items: any) {
        let groups: Array<any> = [];
        items.forEach((item: any) => {
            if (item.hasOwnProperty(this.group_field)) {
                groups.push(item[this.group_field]);
            }
        })
        groups = [...new Set(groups)];
        groups.forEach((group: any, index: number) => {
            this.group_data[index] = {};
            this.group_data[index].items = [];
            items.forEach((item: any, i: number) => {
                if (group == item[this.group_field]) {
                    this.group_data[index].text = group;
                    this.group_data[index].items.push(item);
                }
            })
        })
    }


}