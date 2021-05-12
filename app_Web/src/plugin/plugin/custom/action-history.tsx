
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppListBase } from 'ibiz-vue/src/components/control/app-common-control/app-list-base';
import { AppListService } from 'ibiz-vue';
import { IPSAppDEListView,IPSDEList, IPSDEListDataItem } from '@ibiz/dynamic-model-api';
import { ModelTool } from 'ibiz-core';
import '../plugin-style.less';

/**
 * 操作历史记录-列表部件插件类
 *
 * @export
 * @class ActionHistory
 * @class ActionHistory
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class ActionHistory extends AppListBase {


    /**
     * 行为类型代码表
     *
     * @protected
     * @type {any[]}
     * @memberof ActionHistory
     */
    protected actionType: any[] = [];

    /**
     * 列表模型
     * 
     * @memberof ActionHistory
     */
    public listModel!: any[];

    /**
     * '解决了' 显示模型数据
     *
     * @memberof ActionHistory
     */
    public dynaModel: any = [
        {
            objecttype: "bug",
            action: "resolved",
            othertext: "方案为",
            codelistId: 'Bug__resolution'
        }
    ];

	/**
     * 刷新
     *
     * @memberof ActionHistory
     */
    public ctrlInit() {
        super.ctrlInit();
        if (AppCenterService.getMessageCenter()) {
            const _this = this;
            _this.appStateEvent = AppCenterService.getMessageCenter().subscribe(({ name, action, data }: { name: string, action: string, data: any }) => {
                if (Object.is(action, 'appRefresh')) {
                    _this.refresh(data);
                }
            })
        }
    }

    /**
     * 列表数据加载 根据Action加载对应History
     *
     * @public
     * @param {*} [item={}]
     * @returns {Promise<any>}
     * @memberof ActionHistory
     */
    protected async loadList(item: any = {}): Promise<any> {     
        const arg: any = {
            viewparams: this.viewparams
        };
        const context = this.context || {};
        context.action = item.id;
        let items: any[] = [];
        try {
            let listView = this.controlInstance?.getParentPSModelObject() as IPSAppDEListView;
            let _list = ModelTool.findPSControlByName("history",listView.getPSControls()) as IPSDEList;
            let appde = _list.getPSAppDataEntity();
            await appde?.fill();
            let service: AppListService = new AppListService(_list);
            await service.loaded(_list);
            const response = await service.search('FetchDefault', {...context}, arg, this.showBusyIndicator);
            if (response && response.status === 200) {
                items = response.data || [];
            }
        } catch (error) {
            console.log(error);
        } finally {
            return items;
        }
    }

    /**
     * 列表数据加载
     *
     * @param {*} [opt={}]
     * @returns {void}
     * @memberof ActionHistory
     */
    public load(opt: any = {}): void {
        if (!this.fetchAction) {
            this.$Notice.error({ title: '错误', desc: '视图列表fetchAction参数未配置' });
            return;
        }
        const arg: any = { ...opt };
        const page: any = {};
        if (this.isEnablePagingBar) {
            Object.assign(page, { page: this.curPage - 1, size: this.limit });
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
                if(this.items.length > 0){
                    this.listModel = this.controlInstance.getPSDEListDataItems() as IPSDEListDataItem[];
                    this.formatData();
                }
            },
            (response: any) => {
				this.ctrlEndLoading();
                if (response && response.status === 401) {
                    return;
                }
                this.$Notice.error({ title: '错误', desc: response.errorMessage });
            }
        );
    }

    /**
     * 格式化数据
     *
     * @protected
     * @memberof ActionHistory
     */
    protected async formatData() {
        if (this.actionType.length === 0) {
            const codeList2 = await this.codeListService.getDataItems({ tag: 'Action__type', type: 'STATIC', context: this.context });
            if (codeList2) {
                this.actionType.push(...codeList2);
            }
        }
        this.items.forEach((item: any) => {
            let actionType: string = item.actiontype ? item.actiontype : item.action;
            const data = this.actionType.find(code => Object.is(code.value, actionType));
            if (data) {
                item.actionText = data.text;
            }
            this.listItemCodelist(item);
        })
    }

    /**
     * 列表项代码表处理
     * 
     * @memberof ActionHistory
     */
    public listItemCodelist(item: any){
        if(this.listModel && this.listModel.length > 0){
            this.listModel.forEach((listItem:IPSDEListDataItem)=>{
                for(const key in item){
                    if(Object.is(key,listItem.name) && listItem.getFrontPSCodeList()){  
                        this.codeListService.getDataItems({tag: listItem.getFrontPSCodeList()?.codeName, type:listItem.getFrontPSCodeList()?.codeListType}).then((res: any)=>{
                            if(res){
                                const data = res.find((code:any) => Object.is(code.value, item[key]));
                                if(data){
                                    item[key] = data.text;
                                }
                            }
                        }).catch((error: any)=>{
                            console.log(`----${listItem.getFrontPSCodeList()?.codeName}----代码表不存在`);
                        });
                    }
                }
            })
        }
    }

    /**
     * 加载子数据
     *
     * @param {ActionItem} item
     * @memberof ActionHistory
     */
    public async loadChildren(item: any): Promise<void> {
        if (item && item.isLoadedChildren === true) {
            item.expand = !item.expand;
            this.$forceUpdate();
            return;
        }
        if (item && this.loadList) {
            try {
                let items: any[] = await this.loadList(item);
                if (items) {
                    item.children = items;
                    item.expand = true;
                    item.isLoadedChildren = true;
                    this.$forceUpdate();
                }
            } catch (error) {
                console.log(error);
            }
        }
    }

    /**
     * 编辑数据
     *
     * @param {*} item
     * @memberof ActionHistory
     */
    public editData(item: any) {
        this.$emit('edit-data', item)
    }

    /**
     * 绘制操作历史项
     *
     * @protected
     * @param {ActionItem} action
     * @param {History} item
     * @returns {*}
     * @memberof ActionHistory
     */
    protected renderHistoryItem(action: any, item: any): any {
        const fieldText = this.$t(`entities.${action.objecttype.toLowerCase()}.fields.${item.field.toLowerCase()}`);
        if (item.diff) {
            return <div class="history-content">
                {action.actionText}&nbsp;<strong>{fieldText}</strong>，区别为：<span><action-history-diff content={item.diff} /></span>
            </div>
        }
        const old = isNaN(item.old) && !isNaN(Date.parse(item.old)) ? this.handleDate(item.old) : "\""+item.old+"\"";
        const ibiznew = isNaN(item.ibiznew) && !isNaN(Date.parse(item.ibiznew)) ? this.handleDate(item.ibiznew) : "\""+item.ibiznew+"\"";
        return <div class="history-content">修改了&nbsp;<strong>{fieldText}</strong>，旧值为 { old }，新值为 { ibiznew }。</div>;
    }

    /**
     * 时间处理
     *
     * @public
     * @param {*} date
     * @returns {*}
     * @memberof ActionHistory
     */
    public handleDate(date: any) {
        const reg = /\d{4}-\d{1,2}-\d{1,2} \d{1,2}:\d{1,2}:\d{1,2}/;
        if(date.toString().search(reg) !== -1) {
            date = date.toString().match(reg);
        }
        return date;
    }

    /**
     * 绘制操作历史
     *
     * @protected
     * @param {ActionItem} action
     * @param {HistoryItem[]} items
     * @returns {*}
     * @memberof ActionHistory
     */
    protected renderHistory(action: any, items: any[]): any {
        return <div class="history-wrapper">
            {items.map((item: any) => {
                return <div class="history-item">
                    {this.renderHistoryItem(action, item)}
                </div>;
            })}
        </div>;
    }

    /**
     * 绘制操作项内容
     *
     * @protected
     * @param {ActionItem} item
     * @returns {*}
     * @memberof ActionHistory
     */
    protected renderActionContent(item: any): any {
        let actionType: string = item.actiontype ? item.actiontype : item.action;
        return <div class="action-content">
            <div class="text">{item.date}，由&nbsp;<strong>{item.actor}</strong>&nbsp;{item.actionText}{this.renderFixedContent(item)}</div>
            { (Object.is(actionType, 'changed') || Object.is(actionType, 'edited')  || Object.is(actionType, 'commented') || Object.is(actionType, 'assigned') || Object.is(actionType, 'reviewed') || Object.is(actionType, 'activated') || Object.is(actionType, 'resolved') || Object.is(actionType, 'bugconfirmed')) && this.load ? <div class="show-history">
                <i-button title="切换显示" type="text" ghost icon={item.expand === true ? 'md-remove-circle' : 'md-add-circle'} on-click={() => this.loadChildren(item)} />
            </div> : null}
        </div>;
    }

    /**
     * 绘制处理信息
     *
     * @protected
     * @param {ActionItem} item
     * @param {any} actionContent
     * @returns {*}
     * @memberof ActionHistory
     */
    public renderFixedContent(item: any) {
        let actionType: string = item.actiontype ? item.actiontype : item.action;
        if(item.extra && this.dynaModel) {
            const model = this.dynaModel.find((modelFix:any) => (Object.is(modelFix.objecttype, item.objecttype) && Object.is(modelFix.action, actionType)));
            if(model && model.codelistId) {
                let codeList = this.$store.getters.getCodeList(model.codelistId);
                if(codeList) {
                    const code = codeList.items.find((code: any) => Object.is(item.extra, code.value));
                    if(code) {
                        return <span>，{model.othertext}&nbsp;<b>{code.text}</b></span>;
                    }
                }
            }
        }
    }

    /**
     * @protected
     * @param {ActionItem} item
     *
     */
    protected  renderActionComment(item : any, isEdit: boolean): any {
        return <div class="action-comment">
            <html-container content={item.comment}></html-container>
            { isEdit ? <icon class="action-comment-edit" type="ios-create-outline" on-click={() => this.editData(item)}/> : null }
        </div>;
    }

    /**
     * 绘制操作项
     *
     * @protected
     * @returns {*}
     * @memberof ActionHistory
     */
    protected renderAction(): any {
        return <div class="action-wrapper">
            {this.items.map((item: any, index: number) => {
                return <div class="action-item">
                    {this.renderActionContent(item)}
                    {(item.children && item.expand) ? this.renderHistory(item, item.children) : null}
                    {item.comment ? this.renderActionComment(item, (this.items.length - 1 == index && item.isactorss === 1)) : null}
                </div>;
            })}
        </div>;
    }

    /**
     * 绘制actionhistory插件
     * 
     * @memberof ActionHistory
     */
    public renderActionHistory(){
        return <div class="action-history-wrapper">
            <div class="action-history-header">
                <span class="title">历史记录</span>
            </div>
            {this.renderAction()}
        </div>;
    }

    /**
     * 部件挂载
     *
     * @memberof ActionHistory
     */
    public ctrlMounted(args?: any) {
        this.ctrlEvent({
            controlname: 'history',
            action: 'controlIsMounted',
            data: true
        })
        super.ctrlMounted();
    }

    /**
     * 绘制
     * 
     * @memberof ActionHistory
     */
    public render(): any{
        if(!this.controlIsLoaded){
            return null;
        }
        if(this.items.length > 0){
            return <div class="app-list" style="justify-content: start;">
                {this.renderActionHistory()}
            </div>
        } else {
            return <div class="app-list app-list-empty">
                <div>暂无数据</div>
            </div>
        }
    }


}

