import { ViewTool, Util, ListControlInterface } from 'ibiz-core';
import { MDControlBase } from "./md-control-base";
import { AppViewLogicService } from '../app-service';
import { AppListService } from '../ctrl-service';
import { IPSDEList, IPSDEListItem, IPSDEUIAction, IPSDEUIActionGroup, IPSUIAction, IPSUIActionGroupDetail } from '@ibiz/dynamic-model-api';

/**
 * 列表部件基类
 *
 * @export
 * @class ListControlBase
 * @extends {MDControlBase}
 */
export class ListControlBase extends MDControlBase implements ListControlInterface {

    /**
     * 列表的模型对象
     *
     * @type {*}
     * @memberof ListControlBase
     */
    public controlInstance!: IPSDEList;

    /**
     * 列表服务对象
     *
     * @type {*}
     * @memberof ListControlBase
     */
    public service !: any;

    /**
     * 分组数据
     *
     * @type {Array<any>}
     * @memberof ListControlBase
     */
    public groupData: Array<any> = [];

    /**
      * 列表数据
      * 
      * @type {*}
      * @memberof ListControlBase
      */
    public items: Array<any> = [];

    /**
     * 加载的数据是否附加在items之后
     *
     * @type {boolean}
     * @memberof ListControlBase
     */
     public isAddBehind: boolean = false;

    /**
     * 默认排序方向
     *
     * @readonly
     * @memberof ListControlBase
     */
     public minorSortDir: any = '';

     /**
      * 默认排序应用实体属性
      *
      * @readonly
      * @memberof ListControlBase
      */
     public minorSortPSDEF: any = '';

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof ListControlBase
     */
     public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isSelectFirstDefault = newVal.isSelectFirstDefault;
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
      * 部件模型数据初始化实例
      *
      * @memberof ListControlBase
      */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        if (!(this.Environment && this.Environment.isPreviewMode)) {
            this.service = new AppListService(this.controlInstance, this.context);
            await this.service.loaded(this.controlInstance);
        }
        this.minorSortPSDEF = this.controlInstance.getMinorSortPSAppDEField()?.codeName;
        this.minorSortDir = this.controlInstance.minorSortDir;
        this.limit = this.controlInstance?.pagingSize || this.limit;
    }

    /**
     * 初始化
     *
     * @memberof ListControlBase
     */
    public ctrlInit(args?: any) {
        super.ctrlInit();
        // 绑定this
        this.transformData = this.transformData.bind(this);
        this.remove = this.remove.bind(this);
        this.refresh = this.refresh.bind(this);

        // 初始化默认值
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: { tag: string, action: string, data: any }) => {
                if (!Object.is(this.name, tag)) {
                    return;
                }
                if (Object.is(action, 'load')) {
                    this.curPage = 1;
                    this.items = [];
                    this.load(data);
                }
                if (Object.is(action, 'save')) {
                    this.save(data);
                }
                if (Object.is(action, 'refresh')) {
                    this.refresh(data);
                }
            });
        }
    }

    /** 
     * 部件挂载完毕
     *
     * @protected
     * @memberof ListControlBase
     */
     public ctrlMounted(): void {
        super.ctrlMounted();
        const loadMoreCallBack: any = this.throttle(this.loadMore, 3000);
        this.$el.addEventListener('scroll', () => {
            if (this.$el.scrollTop + this.$el.clientHeight >= this.$el.scrollHeight) {
                loadMoreCallBack();
            }
        });
    }

    /**
     * 初始化界面行为模型
     *
     * @type {*}
     * @memberof ListControlBase
     */
     public initCtrlActionModel() {
        if (this.controlInstance.getPSDEListItems() && (this.controlInstance.getPSDEListItems() as any)?.length > 0) {
            for (let index = 0; index < (this.controlInstance.getPSDEListItems() as any).length; index++) {
                const listItem: IPSDEListItem = (this.controlInstance.getPSDEListItems() as any)[index];
                if (listItem.getPSDEUIActionGroup() && ((listItem.getPSDEUIActionGroup() as IPSDEUIActionGroup).getPSUIActionGroupDetails() as any)?.length > 0) {
                    for (let index = 0; index < ((listItem.getPSDEUIActionGroup() as IPSDEUIActionGroup).getPSUIActionGroupDetails() as any).length; index++) {
                        const uiActionDetail: IPSUIActionGroupDetail = ((listItem.getPSDEUIActionGroup() as IPSDEUIActionGroup).getPSUIActionGroupDetails() as any)[index];
                        if (uiActionDetail?.getPSUIAction()) {
                            const uiAction: IPSDEUIAction = uiActionDetail.getPSUIAction() as IPSDEUIAction;
                            if (uiAction) {
                                const appUIAction: IPSDEUIAction = Util.deepCopy(uiAction) as IPSDEUIAction;
                                this.actionModel[uiAction.uIActionTag] = Object.assign(appUIAction, { disabled: false, visabled: true, getNoPrivDisplayMode: appUIAction.noPrivDisplayMode ? appUIAction.noPrivDisplayMode : 6 });
                            }
                        }
                    }
                }

            }
        }
    }


    /**
     * 列表数据加载
     *
     * @param {*} [opt={}] 额外参数
     * @returns {void}
     * @memberof ListControlBase
     */
     public load(opt: any = {}): void {
        if (!this.fetchAction) {
            this.$throw(this.$t('app.list.notconfig.fetchaction'),'load');
            return;
        }
        const arg: any = { ...opt };
        const page: any = {};
        const size = this.controlInstance?.pagingSize;
        if (this.isEnablePagingBar) {
            Object.assign(page, { page: this.curPage - 1, size: size ? size : 20 });
        }
        // 设置排序
        if (Util.isExistAndNotEmpty(this.minorSortDir) && Util.isExistAndNotEmpty(this.minorSortPSDEF)) {
            const sort: string = this.minorSortPSDEF + ',' + this.minorSortDir;
            Object.assign(page, { sort: sort });
        }
        Object.assign(arg, page);
        const parentdata: any = {};
        this.ctrlEvent({
            controlname: this.name,
            action: 'beforeload',
            data: parentdata,
        });
        Object.assign(arg, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
        if (this.viewparams) {
            Object.assign(tempViewParams, JSON.parse(JSON.stringify(this.viewparams)));
        }
        const _this: any = this;
        Object.assign(arg, { viewparams: tempViewParams });
        let tempContext:any = JSON.parse(JSON.stringify(this.context));
        this.onControlRequset('load', tempContext, arg);
        const post: Promise<any> = this.service.search(this.fetchAction, tempContext, arg, this.showBusyIndicator);
        post.then(
            (response: any) => {
                _this.onControlResponse('load', response);
                if (!response || response.status !== 200) {
                    this.$throw(response,'load');
                    return;
                }
                const data: any = response.data;
                if (!_this.isAddBehind) {
                    _this.items = [];
                }
                if (data && data.length > 0) {
                    let datas = JSON.parse(JSON.stringify(data));
                    datas.map((item: any) => {
                        Object.assign(item, { isselected: false });
                    });
                    _this.totalRecord = response.total;
                    _this.items.push(...datas);
                }
                _this.isControlLoaded = true;
                _this.isAddBehind = false;
                _this.items.forEach((item: any) => {
                    Object.assign(item, _this.getActionState(item));
                })
                _this.ctrlEvent({
                    controlname: _this.name,
                    action: 'load',
                    data: _this.items,
                });
                if (_this.isSelectFirstDefault) {
                    if (_this.selections && _this.selections.length > 0) {
                        _this.selections.forEach((select: any) => {
                            const index = _this.items.findIndex((item: any) => Object.is(item.srfkey, select.srfkey));
                            if (index != -1) {
                                _this.handleClick(_this.items[index]);
                            }
                        });
                    } else {
                        _this.handleClick(_this.items[0]);
                    }
                }
            },
            (response: any) => {
                _this.onControlResponse('load', response);
                this.$throw(response,'load');
            }
        )
    }

    /**
     * 删除
     *
     * @param {any[]} items 删除数据
     * @returns {Promise<any>}
     * @memberof ListControlBase
     */
    public async remove(items: any[]): Promise<any> {
        if (!this.removeAction) {
            this.$throw(`${this.name}${this.$t('app.list.notconfig.removeaction')}`,'remove');
            return;
        }
        if (items.length === 0) {
            return;
        }
        let dataInfo = '';
        items.forEach((record: any, index: number) => {
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

        if (items.length < 5) {
            dataInfo = dataInfo + this.$t('app.dataview.sum') + items.length + this.$t('app.dataview.data');
        } else {
            dataInfo = dataInfo + '...' + this.$t('app.dataview.sum') + items.length + this.$t('app.dataview.data');
        }
        const removeData = () => {
            let keys: any[] = [];
            items.forEach((data: any) => {
                keys.push(data.srfkey);
            });
            let _removeAction = keys.length > 1 ? 'removeBatch' : this.removeAction;
            let tempContext:any = JSON.parse(JSON.stringify(this.context));
            Object.assign(tempContext, { [this.appDeCodeName?.toLowerCase()]: keys.join(';') });
            let arg: any = { [this.appDeCodeName.toLowerCase()]: keys.join(';') };
            Object.assign(arg, { viewparams: this.viewparams });
            this.onControlRequset('remove', tempContext, arg);
            const post: Promise<any> = this.service.delete(_removeAction, tempContext, arg, this.showBusyIndicator);
            return new Promise((resolve: any, reject: any) => {
                post.then((response: any) => {
                    this.onControlResponse('remove', response);
                    if (!response || response.status !== 200) {
                        this.$throw(this.$t('app.commonwords.deldatafail') + response.info,'remove');
                        return;
                    } else {
                        this.$success(this.$t('app.commonwords.deletesuccess'),'remove');
                    }
                    //删除items中已删除的项
                    items.forEach((data: any) => {
                        this.items.some((item: any, index: number) => {
                            if (Object.is(item.srfkey, data.srfkey)) {
                                this.items.splice(index, 1);
                                return true;
                            }
                        });
                    });
                    this.ctrlEvent({
                        controlname: this.name,
                        action: 'remove',
                        data: null,
                    });
                    this.selections = [];
                    resolve(response);
                }).catch((response: any) => {
                    this.onControlResponse('remove', response);
                    this.$throw(response,'remove');
                    reject(response);
                });
            });
        };

        dataInfo = dataInfo
            .replace(/[null]/g, '')
            .replace(/[undefined]/g, '')
            .replace(/[ ]/g, '');
        this.$Modal.confirm({
            title: (this.$t('app.commonwords.warning') as string),
            content: this.$t('app.grid.confirmdel') + dataInfo + this.$t('app.grid.notrecoverable'),
            onOk: () => {
                removeData();
            },
            onCancel: () => { },
        });
        return removeData;
    }

    /**
     * 保存
     *
     * @param {*} args 额外参数
     * @return {*} 
     * @memberof ListControlBase
     */
    public async save(args: any) {
        let _this = this;
        let successItems: any = [];
        let errorItems: any = [];
        let errorMessage: any = [];
        for (const item of _this.items) {
            try {
                if (Object.is(item.rowDataState, 'create')) {
                    if (!this.createAction) {
                        this.$throw(`${this.controlInstance.codeName}` + (this.$t('app.list.notconfig.createaction') as string),'save');
                    } else {
                        Object.assign(item, { viewparams: this.viewparams });
                        let tempContext:any = JSON.parse(JSON.stringify(this.context));
                        this.onControlRequset('create', tempContext, item);
                        let response = await this.service.add(this.createAction, tempContext, item, this.showBusyIndicator);
                        this.onControlResponse('create', response);
                        successItems.push(JSON.parse(JSON.stringify(response.data)));
                    }
                } else if (Object.is(item.rowDataState, 'update')) {
                    if (!this.updateAction) {
                        this.$throw(`${this.controlInstance.codeName}` + (this.$t('app.list.notconfig.updateaction') as string),'save');
                    } else {
                        Object.assign(item, { viewparams: this.viewparams });
                        if (this.appDeCodeName && item[this.appDeCodeName]) {
                            Object.assign(this.context, { [this.appDeCodeName]: item[this.appDeCodeName] });
                        }
                        let tempContext:any = JSON.parse(JSON.stringify(this.context));
                        this.onControlRequset('update', tempContext, item);
                        let response = await this.service.add(this.updateAction, tempContext, item, this.showBusyIndicator);
                        this.onControlResponse('update', response);
                        successItems.push(JSON.parse(JSON.stringify(response.data)));
                    }
                }
            } catch (error) {
                this.onControlResponse('save', error);
                errorItems.push(JSON.parse(JSON.stringify(item)));
                errorMessage.push(error);
            }
        }
        this.$emit('ctrl-event', { controlname: this.name, action: "save", data: successItems });
        this.refresh();
        if (errorItems.length === 0) {
            if(args?.showResultInfo || (args && !args.hasOwnProperty('showResultInfo'))){
                this.$success((this.$t('app.commonwords.savesuccess') as string),'save');
            }
        } else {
            errorItems.forEach((item: any, index: number) => {
                this.$throw(item.majorentityname + (this.$t('app.commonwords.savefailed') as string) + '!','save');
                this.$throw(errorMessage[index],'save');
            });
        }
        return successItems;
    }


    /**
     * 获取选中数据
     *
     * @returns {any[]}
     * @memberof GridControlBase
     */
    public getSelection(): any[] {
        return this.selections;
    }

    /**
     * 清除当前所有选中状态
     *
     * @memberof ListControlBase
     */
     public clearSelection() {
        this.items.map((item: any) => {
            Object.assign(item, { isselected: false });
        });
    }

    /**
     * 加载更多
     *
     * @memberof ListControlBase
     */
    public loadMore() {
        if (this.totalRecord > this.items.length) {
            this.curPage = ++this.curPage;
            this.isAddBehind = true;
            this.load({});
        }
    }

    /**
     * 刷新
     *
     * @param {*} [args] 额外参数
     * @memberof ListControlBase
     */
    public refresh(args?: any) {
        this.isAddBehind = false;
        this.load(args);
    }

    /**
     * 行单击事件
     *
     * @param {*} args 行数据
     * @memberof ListControlBase
     */
    public handleClick(args: any) {
        if (this.isSingleSelect) {
            this.clearSelection();
        }
        args.isselected = !args.isselected;
        this.selectchange();
    }

    /**
     * 触发事件
     * @memberof ListControlBase
     *
     */
     public selectchange() {
        this.selections = [];
        this.items.map((item: any) => {
            if (item.isselected) {
                this.selections.push(item);
            }
        });
        this.ctrlEvent({
            controlname: this.name,
            action: 'selectionchange',
            data: this.selections,
        });
    }

    /**
     * 双击事件
     *
     * @param {*} args 数据
     * @memberof ListControlBase
     */
    public handleDblClick(args: any) {
        this.ctrlEvent({ controlname: this.name as string, action: 'rowdblclick', data: args });
    }

    /**
     * 处理操作列点击
     * 
     * @param {*} data 行数据
     * @param {*} event 事件源
     * @param {*} item 列表项模型
     * @param {*} detail 操作列模型
     * @memberof ListControlBase
     */
    public handleActionClick(data: any, event: any, item: any, detail: any) {
        AppViewLogicService.getInstance().executeViewLogic(this.getViewLogicTag('list', item.dataItemName, detail.name), event, this, data, (this.controlInstance?.getPSAppViewLogics() as any));
    }

    /**
     * 面板数据变化处理事件
     * @param {any} item 当前列数据
     * @param {any} $event 面板事件数据
     *
     * @memberof ListControlBase
     */
    public onPanelDataChange(item: any, $event: any) {
        Object.assign(item, $event, { rowDataState: 'update' });
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
    
    /**
     * 获取界面行为权限状态
     *
     * @param {*} data 当前列表行数据
     * @memberof ListControlBase
     */
     public getActionState(data: any) {
        let tempActionModel: any = JSON.parse(JSON.stringify(this.actionModel));
        let targetData: any = this.transformData(data);
        ViewTool.calcActionItemAuthState(targetData, tempActionModel, this.appUIService);
        return tempActionModel;
    }

    /**
     * 节流
     *
     * @param {*} fn 方法
     * @param {number} wait 等待时间
     * @return {*} 
     * @memberof ListControlBase
     */
    public throttle(fn: any, wait: number) {
        let time = 0;
        return () => {
            let now = Date.now();
            let args = arguments;
            if (now - time > wait) {
                fn.apply(this, args);
                time = now;
            }
        };
    }

}
