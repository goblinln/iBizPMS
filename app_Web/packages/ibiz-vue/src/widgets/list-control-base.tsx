import { IBizListModel, ViewTool, Util, IBizToolBarItemModel } from 'ibiz-core';
import { MDControlBase } from "./md-control-base";
import { AppViewLogicService } from '../app-service';
import { AppListService } from '../ctrl-service';

/**
 * 列表部件基类
 *
 * @export
 * @class ListControlBase
 * @extends {MDControlBase}
 */
export class ListControlBase extends MDControlBase {

    /**
     * 列表的模型对象
     *
     * @type {*}
     * @memberof ListControlBase
     */
    public controlInstance!: IBizListModel;

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
      * 表格数据
      * 
      * @type {*}
      * @memberof ListControlBase
      */
    public items: Array<any> = [];

    /**
     * 快速行为模型数据
     *
     * @protected
     * @type {[]}
     * @memberof ListControlBase
     */
    public quickToolbarModels: Array<any> = [];

    /**
     * 批操作行为模型数据
     *
     * @protected
     * @type {[]}
     * @memberof ListControlBase
     */
    public batchToolbarModels: Array<any> = [];

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
     * 加载的数据是否附加在items之后
     *
     * @type {boolean}
     * @memberof ListControlBase
     */
    public isAddBehind: boolean = false;

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
     * 部件挂载完毕
     *
     * @protected
     * @memberof ListControlBase
     */
    public ctrlMounted(): void {
        const loadMoreCallBack: any = this.throttle(this.loadMore, 3000);
        this.$el.addEventListener('scroll', () => {
            if (this.$el.scrollTop + this.$el.clientHeight >= this.$el.scrollHeight) {
                loadMoreCallBack();
            }
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
     * 消息中心
     *
     * @protected
     * @param {*} data
     * @memberof ListControlBase
     */
    protected accChange(data: any): void {
        this.refresh();
    }

    /**
     * 刷新
     *
     * @param {*} [args]
     * @memberof ListControlBase
     */
    public refresh(args?: any) {
        this.isAddBehind = false;
        this.load(args);
    }

    /**
     * 选择数据
     * @memberof ListControlBase
     *
     */
    public handleClick(args: any) {
        if (this.isSingleSelect) {
            this.clearSelection();
        }
        args.isselected = !args.isselected;
        this.selectchange();
    }

    /**
     * 保存
     *
     * @param {*} $event
     * @returns {Promise<any>}
     * @memberof ListControlBase
     */
    public async save(args: any[], params?: any, $event?: any, xData?: any){
        let _this = this;
        let successItems:any = [];
        let errorItems:any = [];
        let errorMessage:any = [];
        for (const item of _this.items) {
            try {
                if(Object.is(item.rowDataState, 'create')){
                    if(!this.createAction){
                        this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: '${view.getName()}' + (this.$t('app.list.notConfig.createAction') as string) });
                    }else{
                      Object.assign(item,{viewparams:this.viewparams});
                      this.ctrlBeginLoading();
                      let response = await this.service.add(this.createAction, JSON.parse(JSON.stringify(this.context)),item, this.showBusyIndicator);
                      this.ctrlEndLoading();
                      successItems.push(JSON.parse(JSON.stringify(response.data)));
                    }
                }else if(Object.is(item.rowDataState, 'update')){
                    if(!this.updateAction){
                        this.$Notice.error({ title: (this.$t('app.commonWords.warning') as string), desc: '${view.getName()}' + (this.$t('app.list.notConfig.updateAction') as string) });
                    }else{
                        Object.assign(item,{viewparams:this.viewparams});
                        if(this.controlInstance.appDeCodeName && item[this.controlInstance.appDeCodeName]){
                            Object.assign(this.context,{ [this.controlInstance.appDeCodeName]: item[this.controlInstance.appDeCodeName] });
                        }
                        this.ctrlBeginLoading();
                        let response = await this.service.add(this.updateAction,JSON.parse(JSON.stringify(this.context)),item, this.showBusyIndicator);
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
        this.$emit('ctrl-event', { controlname: this.controlInstance.name, action: "save", data: successItems }); 
        this.refresh();
        if(errorItems.length === 0){
            this.$Notice.success({ title: '', desc: (this.$t('app.commonWords.saveSuccess') as string) });
        }else{
          errorItems.forEach((item:any,index:number)=>{
            this.$Notice.error({ title: (this.$t('app.commonWords.saveFailed') as string), desc: item.majorentityname+ (this.$t('app.commonWords.saveFailed') as string) + '!' });
            console.error(errorMessage[index]);
          });
        }
        return successItems;
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
            controlname: this.controlInstance.name,
            action: 'selectionchange',
            data: this.selections,
        });
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
     * 列表数据加载
     *
     * @param {*} [opt={}]
     * @returns {void}
     * @memberof ListControlBase
     */
    public load(opt: any = {}): void {
        if (!this.fetchAction) {
            this.$Notice.error({ title: '错误', desc: '视图列表fetchAction参数未配置' });
            return;
        }
        const arg: any = { ...opt };
        const page: any = {};
        const size = this.controlInstance?.controlModelData?.pagingSize;
        if (this.isEnablePagingBar) {
            Object.assign(page, { page: this.curPage - 1, size: size ? size : 20 });
        }
        // 设置排序
        if (!Object.is(this.minorSortDir, '') && !Object.is(this.minorSortPSDEF, '')) {
            const sort: string = this.minorSortPSDEF + ',' + this.minorSortDir;
            Object.assign(page, { sort: sort });
        }
        Object.assign(arg, page);
        const parentdata: any = {};
        this.ctrlEvent({
            controlname: this.controlInstance.name,
            action: 'beforeload',
            data: parentdata,
        });
        Object.assign(arg, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
        if (this.viewparams) {
            Object.assign(tempViewParams, JSON.parse(JSON.stringify(this.viewparams)));
        }
        Object.assign(arg, { viewparams: tempViewParams });
        this.ctrlBeginLoading()
        const post: Promise<any> = this.service.search(
            this.fetchAction,
            this.context ? JSON.parse(JSON.stringify(this.context)) : {},
            arg,
            this.showBusyIndicator
        );
        post.then(
            (response: any) => {
                this.ctrlEndLoading();
                if (!response || response.status !== 200) {
                    if (response.errorMessage) {
                        this.$Notice.error({ title: '错误', desc: response.errorMessage });
                    }
                    return;
                }
                const data: any = response.data;
                if (!this.isAddBehind) {
                    this.items = [];
                }
                if (data && data.length > 0) {
                    let datas = JSON.parse(JSON.stringify(data));
                    datas.map((item: any) => {
                        Object.assign(item, { isselected: false });
                    });
                    this.totalRecord = response.total;
                    this.items.push(...datas);
                    this.items = this.arrayNonRepeatfy(this.items);
                }
                this.isAddBehind = false;
                this.items.forEach((item: any) => {
                    Object.assign(item,this.getActionState(item));    
                })
                this.ctrlEvent({
                    controlname: this.controlInstance.name,
                    action: 'load',
                    data: this.items,
                });
                if (this.isSelectFirstDefault) {
                    if (this.selections && this.selections.length > 0) {
                        this.selections.forEach((select: any) => {
                            const index = this.items.findIndex((item: any) => Object.is(item.srfkey, select.srfkey));
                            if (index != -1) {
                                this.handleClick(this.items[index]);
                            }
                        });
                    } else {
                        this.handleClick(this.items[0]);
                    }
                }
            },
            (response: any) => {
                this.ctrlEndLoading();
                if (response && response.status === 401) {
                    return;
                }
                this.$Notice.error({ title: '错误', desc: response.errorMessage });
            }
        )
    }

    /**
     * 获取界面行为权限状态
     *
     * @param {*} data 当前列表行数据
     * @memberof ListControlBase
     */
    public getActionState(data:any){
        let tempActionModel:any = JSON.parse(JSON.stringify(this.actionModel));
        let targetData:any = this.transformData(data);
        ViewTool.calcActionItemAuthState(targetData,tempActionModel,this.appUIService);
        return tempActionModel;
    }

    /**
     * 初始化界面行为模型
     *
     * @type {*}
     * @memberof ListControlBase
     */
    public initCtrlActionModel() {
        if (this.controlInstance.getPSDEListItems?.length > 0){
            for (let listItem of this.controlInstance.getPSDEListItems) {
                if (listItem.getPSDEUIActionGroup?.getPSUIActionGroupDetails?.length > 0) {
                    for (let uiActionDetail of listItem.getPSDEUIActionGroup.getPSUIActionGroupDetails) {
                        if(uiActionDetail?.getPSUIAction){
                            const appUIAction: any = Util.deepCopy(uiActionDetail.getPSUIAction);
                            this.actionModel[appUIAction.uIActionTag] = Object.assign(appUIAction, { disabled: false, visabled: true, getNoPrivDisplayMode: appUIAction.getNoPrivDisplayMode ? appUIAction.getNoPrivDisplayMode : 6 });
                        }
                    }
                }
            }
        }
    }

    /**
     * 列表数据去重
     *
     * @param {Array<any>} [arr]
     * @returns {void}
     * @memberof ListControlBase
     */
    public arrayNonRepeatfy(arr: Array<any>) {
        let map = new Map();
        let array = new Array();
        for (let i = 0; i < arr.length; i++) {
            map.set(arr[i].srfkey, arr[i]);
        }
        map.forEach((value: any, key: string, map: any) => {
            array.push(value);
        });
        return array;
    }

    /**
     * 节流
     *
     * @param {Array<any>} [arr]
     * @returns {void}
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

    /**
     * 删除
     *
     * @param {any[]} items
     * @returns {Promise<any>}
     * @memberof ListControlBase
     */
    public async remove(items: any[]): Promise<any> {
        if (!this.removeAction) {
            this.$Notice.error({ title: '错误', desc: `${this.name}列表removeAction参数未配置` });
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
            dataInfo = dataInfo + ' 共' + items.length + '条数据';
        } else {
            dataInfo = dataInfo + '...' + ' 共' + items.length + '条数据';
        }
        const removeData = () => {
            let keys: any[] = [];
            items.forEach((data: any) => {
                keys.push(data.srfkey);
            });
            let _removeAction = keys.length > 1 ? 'removeBatch' : this.removeAction;
            const context: any = JSON.parse(JSON.stringify(this.context));
            const post: Promise<any> = this.service.delete(
                _removeAction,
                Object.assign(context, { [this.controlInstance.appDataEntity?.codeName.toLowerCase()]: keys.join(';') }),
                Object.assign({ [this.controlInstance.appDataEntity?.codeName.toLowerCase()]: keys.join(';') }, { viewparams: this.viewparams }),
                this.showBusyIndicator
            );
            return new Promise((resolve: any, reject: any) => {
                this.ctrlBeginLoading();
                post.then((response: any) => {
                    this.ctrlEndLoading();
                    if (!response || response.status !== 200) {
                        this.$Notice.error({ title: '', desc: '删除数据失败,' + response.info });
                        return;
                    } else {
                        this.$Notice.success({ title: '', desc: '删除成功!' });
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
                        controlname: this.controlInstance.name,
                        action: 'remove',
                        data: null,
                    });
                    this.selections = [];
                    resolve(response);
                }).catch((response: any) => {
                    this.ctrlEndLoading();
                    if (response && response.status === 401) {
                        return;
                    }
                    if (!response || !response.status || !response.data) {
                        this.$Notice.error({ title: '错误', desc: '系统异常' });
                        reject(response);
                        return;
                    }
                    reject(response);
                });
            });
        };

        dataInfo = dataInfo
            .replace(/[null]/g, '')
            .replace(/[undefined]/g, '')
            .replace(/[ ]/g, '');
        this.$Modal.confirm({
            title: '警告',
            content: '确认要删除 ' + dataInfo + '，删除操作将不可恢复？',
            onOk: () => {
                removeData();
            },
            onCancel: () => { },
        });
        return removeData;
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
     * 双击数据
     * @memberof ListControlBase
     *
     */
    public handleDblClick($event: any) {
        this.ctrlEvent({ controlname: this.name as string, action: 'rowdblclick', data: $event });
    }

    /**
      * 部件模型数据初始化实例
      *
      * @memberof ListControlBase
      */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit();
        if (!(this.Environment && this.Environment.isPreviewMode)) {
            this.service = new AppListService(this.controlInstance);
            await this.service.loaded(this.controlInstance);
        }
        this.updateAction = this.controlInstance?.updateAction;
        this.removeAction = this.controlInstance?.removeAction;
        this.loadAction = this.controlInstance?.loadAction;
        this.fetchAction = this.controlInstance?.fetchAction;
        this.createAction = this.controlInstance?.createAction;
        this.actionModel = this.controlInstance?.actionModel;
        this.initToolAction();
    }

    /**
     * 初始化操作栏
     * 
     * @memberof ListControlBase
     */
    public initToolAction() {
        const quickTool: any = this.controlInstance?.controlToolBarItems('list_quicktoolbar');
        const batchTool: any = this.controlInstance?.controlToolBarItems('list_batchtoolbar');
        if (quickTool) {
            let targetViewToolbarItems: any[] = [];
            quickTool.forEach((item: IBizToolBarItemModel) => {
                targetViewToolbarItems.push({ name: item.name, showCaption: item.showCaption, showIcon: item.showIcon, tooltip: item.tooltip, iconcls: item.iconcls, icon: item.icon, actiontarget: item.uIActionTarget, caption: item.caption, disabled: false, itemType: item.itemType, visabled: true, noprivdisplaymode: item.noPrivDisplayMode, dataaccaction: '', uiaction: {} });
            })
            this.quickToolbarModels = targetViewToolbarItems;
        }
        if (batchTool) {
            let targetViewToolbarItems: any[] = [];
            batchTool.forEach((item: IBizToolBarItemModel) => {
                targetViewToolbarItems.push({ name: item.name, showCaption: item.showCaption, showIcon: item.showIcon, tooltip: item.tooltip, iconcls: item.iconcls, icon: item.icon, actiontarget: item.uIActionTarget, caption: item.caption, disabled: false, itemType: item.itemType, visabled: true, noprivdisplaymode: item.noPrivDisplayMode, dataaccaction: '', uiaction: {} });
            })
            this.batchToolbarModels = targetViewToolbarItems;
        } 
    }

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
     * 处理操作列点击
     * 
     * @memberof ListControlBase
     */
    public handleActionClick(data: any, event: any, item: any, detail: any) {
        AppViewLogicService.getInstance().executeViewLogic(this.getViewLogicTag('list', item.dataItemName, detail.name), event, this, data, this.controlInstance?.getPSAppViewLogics);
    }

    /**
     * 部件工具栏点击
     * 
     * @param ctrl 工具栏部件名称
     * @param data 数据
     * @param $event 事件源对象
     * @memberof ListControlBase
     */
    public handleItemClick(ctrl: string, data: any,$event: any) {
        AppViewLogicService.getInstance().executeViewLogic(this.getViewLogicTag(this.controlInstance.name, ctrl, data.tag),$event,this,undefined,this.controlInstance?.getPSAppViewLogics);
    }  

    /**
     * 计算卡片视图部件所需参数
     *
     * @param {*} controlInstance 部件模型对象
     * @param {*} item 单条卡片数据
     * @returns
     * @memberof ListControlBase
     */
    public computeTargetCtrlData(controlInstance:any, item?: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.dynamicProps,{
            inputData : item,
        })
        Object.assign(targetCtrlParam.staticProps,{
            transformData: this.transformData,
            opendata: this.opendata,
            newdata: this.newdata,
            remove: this.remove,
            refresh: this.refresh,

        })
        targetCtrlEvent['ctrl-event'] = ({ controlname, action, data }: { controlname: string, action: string, data: any }) => {
            this.onCtrlEvent(controlname, action, {item: item, data: data});
        };
        return { targetCtrlName, targetCtrlParam, targetCtrlEvent };
    }

}
