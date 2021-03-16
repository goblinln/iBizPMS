import { ViewTool, IBizDataViewModel, Util, Verify, IBizToolBarItemModel } from 'ibiz-core';
import { MDControlBase } from './MDControlBase';
import { AppDataViewService } from '../ctrl-service';
import { AppViewLogicService } from '../app-service';

/**
 * 数据视图部件基类
 *
 * @export
 * @class DataViewControlBase
 * @extends {MDControlBase}
 */
export class DataViewControlBase extends MDControlBase {

    /**
     * 部件行为--submit
     *
     * @type {*}
     * @memberof DataViewControlBase
     */
    public WFSubmitAction ?: any;

    /**
     * 部件行为--start
     *
     * @type {*}
     * @memberof DataViewControlBase
     */
    public WFStartAction ?: any;
   
    /**
     * 是否单选
     *
     * @type {boolean}
     * @memberof DataViewControlBase
     */
    public isSingleSelect?: boolean;

    /**
     * 数据视图模型实例
     * 
     * @type {*}
     * @memberof DataViewControlBase
     */
    public controlInstance!: IBizDataViewModel;

    /**
     * 分组属性
     * 
     * @type {string}
     * @memberof DataViewControlBase
     */
    public groupAppField: string = "";

    /**
     * 分组属性代码表标识
     *
     * @type {string}
     * @memberof DataViewControlBase
     */
    public groupAppFieldCodelistTag:string ="";

    /**
     * 分组属性是否配置代码表
     * 
     * @type {string}
     * @memberof DataViewControlBase
     */
    public groupFieldCodelist: boolean = false;

    /**
     * 分组属性代码表类型
     * 
     * @type {string}
     * @memberof DataViewControlBase
     */
    public groupAppFieldCodelistType: string = "";

    /**
     * 分组代码表标识
     * 
     * @type {string}
     * @memberof DataViewControlBase
     */
    public codelistTag: string = "";

    /**
     * 分组代码表类型
     * 
     * @type {string}
     * @memberof DataViewControlBase
     */
    public codelistType: string = "";

    /**
     * 分组数据
     * 
     * @type {*}
     * @memberof DataViewControlBase
     */
    public groupData: Array<any> = [];

    /**
     * 分组模式
     * 
     * @type {string}
     * @memberof DataViewControlBase
     */
    public groupMode: string = "";

    /**
     * 分组代码表
     *
     * @type {string}
     * @memberof DataViewControlBase
     */ 
    public groupCodelist: any = {};

    /**
     * 加载的数据是否附加在items之后
     *
     * @type {boolean}
     * @memberof DataViewControlBase
     */
    public isAddBehind:boolean = false;

    /**
     * 是否启用分组
     *
     * @type {boolean}
     * @memberof DataViewControlBase
     */
    public isEnableGroup:boolean = false;

    /**
     * 排序字段
     *
     * @type {string}
     * @memberof DataViewControlBase
     */    
    public sortField: string = '';

    /**
     * 排序模型数据集
     *
     * @type {string}
     * @memberof DataViewControlBase
     */  
    public sortModel: any[] = [];

    /**
     * 排序方向
     * 
     * @type {string}
     * @memberof DataViewControlBase
     */
    public sortDir: string = '';

    /**
     * 默认隐藏批量操作工具栏
     *
     * @type {boolean}
     * @memberof DataViewControlBase
     */
    public flag: boolean = false;

    /**
     * this引用
     *
     * @type {number}
     * @memberof DataViewControlBase
     */
    public thisRef: any = this;

    /**
     * 快速行为模型数据
     *
     * @protected
     * @type {[]}
     * @memberof DataViewControlBase
     */
    public quickToolbarModels: Array<any> = [];

    /**
     * 批操作行为模型数据
     *
     * @protected
     * @type {[]}
     * @memberof DataViewControlBase
     */
    public batchToolbarModels: Array<any> = [];

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof DataViewControlBase
     */
    public onStaticPropsChange(newVal: any, oldVal: any) {
        this.isSingleSelect = newVal.isSingleSelect !== false;
        this.isSelectFirstDefault = newVal.isSelectFirstDefault;
        super.onStaticPropsChange(newVal, oldVal);
    }

    /**
     * 部件模型初始化
     * 
     * @memberof DataViewControlBase
     */
    public async ctrlModelInit() {
        super.ctrlModelInit();
        this.service = new AppDataViewService(this.controlInstance);
        this.initSortModel();
        // // this.showBusyIndicator = this.controlInstance.showBusyIndicator;
        this.loaddraftAction = this.controlInstance.loaddraftAction;
        this.updateAction = this.controlInstance.updateAction;
        this.removeAction = this.controlInstance.removeAction;
        this.loadAction = this.controlInstance.loadAction;
        this.sortField = this.controlInstance.getMinorSortPSDEF?.getCodeName().toLowerCase();
        this.sortDir = this.controlInstance.getMinorSortDir?.toLowerCase();
        this.fetchAction = this.controlInstance.fetchAction;
        this.createAction = this.controlInstance.createAction;
        this.WFSubmitAction = this.controlInstance.WFSubmitAction;
        this.WFStartAction = this.controlInstance.WFStartAction;
        this.isEnableGroup = (this.controlInstance.enableGroup) ? true : false;
        this.initToolAction();
    }

    /**
     * 初始化操作栏
     * 
     * @memberof DataViewControlBase
     */
    public initToolAction() {
        const quickTool: any = this.controlInstance.controlToolBarItems('dataview_quicktoolbar');
        const batchTool: any = this.controlInstance.controlToolBarItems('dataview_batchtoolbar');
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
     * 初始化排序模型数据
     * 
     * @memberof DataViewControlBase
     */
    public initSortModel() {
        this.sortModel = [];
        this.controlInstance.cardViewItems?.forEach((cardViewItem: any) => {
            if (cardViewItem.enableSort) {
                this.sortModel.push(cardViewItem.caption)
            }
        })
    }

    /**
     * 数据视图部件初始化
     *
     * @memberof DataViewControlBase
     */
    public ctrlInit(){
        super.ctrlInit();
        // 绑定this
        this.transformData = this.transformData.bind(this);
        this.remove = this.remove.bind(this);
        this.refresh = this.refresh.bind(this);

        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(this.name, tag)) {
                    return;
                }
                if (Object.is(action,'load')) {
                    this.refresh(data)
                }
                if (Object.is(action,'filter')) {
                    this.refresh(data)
                }
            });
        }
    }

    /**
     * 拖拽元素对象
     *
     * @type {boolean}
     * @memberof DataViewControlBase
     */
    public dragEle:any;


    /**
     * 拖拽后位置left
     *
     * @type {boolean}
     * @memberof DataViewControlBase
     */
    public leftP:any;

    /**
     * 拖拽后位置top
     *
     * @type {boolean}
     * @memberof DataViewControlBase
     */
    public topP:any;

    /**
     * 拖拽标识
     * 
     * @type {boolean}
     * @memberof DataViewControlBase
     */
    public dragflag:boolean=false;

    /**
     * 为拖拽不是点击
     * 
     * @type {boolean}
     * @memberof DataViewControlBase
     */
    public moveflag:boolean = false;

    /**
     * 更改批量操作工具栏显示状态
     *
     * @param $event
     * @memberof DataViewControlBase
     */
    public onClick($event: any){
        if(!this.moveflag){
            this.flag = !this.flag;
        } 
        this.moveflag = false;
    }

    /**
     * 排序点击事件
     * @param {string} field 属性名
     *
     * @memberof DataViewControlBase
     */
    public sortClick(field:string) {
        if(this.sortField !== field){
            this.sortField = field;
            this.sortDir = 'asc';
        }else if(this.sortDir === 'asc'){
            this.sortDir = 'desc';
        }else if(this.sortDir === 'desc'){
            this.sortDir = '';
        }else{
            this.sortDir = 'asc';
        }
        this.refresh();
    }

    /**
     * 排序class变更
     * @param {string} field 属性名
     *
     * @memberof DataViewControlBase
     */
    public getsortClass(field:string) {
        if(this.sortField !== field || this.sortDir === ''){
            return '';
        }else if(this.sortDir === 'asc'){
            return 'sort-ascending'
        }else if(this.sortDir === 'desc'){
            return 'sort-descending'
        }
    }

    /**
     * 部件挂载
     *
     *  @memberof DataViewControlBase
     */    
    public ctrlMounted(){
        super.ctrlMounted();
    }

    /**
     * 鼠标移动+放下
     * 
     * @memberof DataViewControlBase
     */
    public mouseEvent(){
        this.dragEle = this.$el.getElementsByClassName('drag-filed')[0];
        document.onmousemove=(e:any)=>{
            if(this.dragflag){
                this.dragEle.style.left = (e.clientX - this.leftP) + 'px';
                this.dragEle.style.top = (e.clientY - this.topP) + 'px';
                this.moveflag = true;
            }
        }
        document.onmouseup=(e:any)=>{
            this.dragflag = false;
        }
    }

    /**
     * mousedown事件
     * 
     * @param $event
     * @memberof DataViewControlBase
     */
    public down(e:any){
        this.leftP = e.clientX - this.dragEle.offsetLeft;
        this.topP = e.clientY - this.dragEle.offsetTop;
        this.dragflag = true;
    }

    /**
     * 数据加载
     * 
     * @memberof DataViewControlBase
     */
    public load(opt: any = {}, isReset: boolean = false) {
        if(!this.fetchAction){
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: '${view.getName()}' + (this.$t('app.list.notConfig.fetchAction') as string) });
            return;
        } 
        const arg: any = {...opt};
        const page: any = {};
        if (this.isEnablePagingBar) {
            Object.assign(page, { page: this.curPage-1, size: this.limit });
        }
        // 设置排序
        if (!this.isNoSort && !Object.is(this.minorSortDir, '') && !Object.is(this.minorSortPSDEF, '')) {
            const sort: string = this.minorSortPSDEF+","+this.minorSortDir;
            Object.assign(page, { sort: sort });
        }
        Object.assign(arg, page);
        const parentdata: any = {};
        this.$emit("ctrl-event", { controlname: this.controlInstance.name , action: "beforeload", data: parentdata });
        Object.assign(arg, parentdata);
        let tempViewParams:any = parentdata.viewparams?parentdata.viewparams:{};
        Object.assign(tempViewParams,JSON.parse(JSON.stringify(this.viewparams)));
        Object.assign(arg,{viewparams:tempViewParams});
        this.ctrlBeginLoading();
        const post: Promise<any> = this.service.search(this.fetchAction,JSON.parse(JSON.stringify(this.context)), arg, this.showBusyIndicator);
        post.then((response: any) => {
            this.endLoading();
            if (!response || response.status !== 200) {
                if (response.errorMessage) {
                    this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.errorMessage });
                }
                return;
            }
            const data: any = response.data;
            if(!this.isAddBehind){
                this.items = [];
            }
            if (Object.keys(data).length > 0) {
                let datas = JSON.parse(JSON.stringify(data));
                datas.map((item: any) => {
                    Object.assign(item, { isselected: false });
                });
                this.totalRecord = response.total;
                if(isReset){
                    this.items = datas;
                }else{
                    this.items.push(...datas);
                }
            }
            this.isAddBehind = false;
            this.items.forEach((item: any) => {
                Object.assign(item,this.getActionState(item));    
            })
            this.$emit("ctrl-event", { controlname: this.controlInstance.name, action: "load", data: this.items })
            //在导航视图中，如已有选中数据，则右侧展开已选中数据的视图，如无选中数据则默认选中第一条
            if(this.isSelectFirstDefault){
                if(this.selections && this.selections.length > 0){
                    this.selections.forEach((select: any)=>{
                        const index = this.items.findIndex((item:any) => Object.is(item.srfkey,select.srfkey));
                        if(index != -1){
                            this.handleClick(this.items[index]);
                        }
                    })
                }else{
                    this.handleClick(this.items[0]);
                } 
            }
            if(this.controlInstance.enableGroup){
                this.group();
            }
        }, (response: any) => {
            this.endLoading();
            if (response && response.status === 401) {
                return;
            }
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.errorMessage });
        });

    }

    /**
     * 获取界面行为权限状态
     *
     * @param {*} data 当前列表行数据
     * @memberof DataViewControlBase
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
     * @memberof DataViewControlBase
     */
    public initCtrlActionModel() {
        if (this.controlInstance.cardViewItems?.length > 0){
            for (let cardItem of this.controlInstance.cardViewItems) {
                if (cardItem.getPSDEUIActionGroup?.getPSUIActionGroupDetails?.length > 0) {
                    for (let uiActionDetail of cardItem.getPSDEUIActionGroup.getPSUIActionGroupDetails) {
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
     * 选择数据
     * @memberof DataViewControlBase
     *
     */
    public handleClick(args: any) {
        args.isselected = !args.isselected;
        if(this.isSingleSelect) {
            this.items.forEach((item:any) =>{
                if(item.srfkey !== args.srfkey){
                    item.isselected =false;
                }
            })
        }
        this.selectchange();
    }

    /**
     * 触发事件
     * @memberof DataViewControlBase
     *
     */
    public selectchange() {
        this.selections = [];
        this.items.map((item: any) => {
            if (item.isselected) {
                this.selections.push(item);
            }
        });
        this.$emit("ctrl-event", {controlname: this.controlInstance.name, action: "selectionchange", data:this.selections});
    }

    /**
     * 面板数据变化处理事件
     * @param {any} item 当前卡片数据
     * @param {any} $event 面板事件数据
     *
     * @memberof ${srfclassname('${ctrl.codeName}')}Base
     */
    public onPanelDataChange(item:any,$event:any) {
        Object.assign(item, $event, {rowDataState:'update'});
    }

    /**
     * 删除
     *
     * @param {any[]} datas
     * @returns {Promise<any>}
     * @memberof DataViewControlBase
     */
    public async remove(datas: any[]): Promise<any> {
        if(!this.removeAction){
            this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: '${view.getName()}' + (this.$t('app.gridpage.notConfig.removeAction') as string) });
            return;
        }
        let _datas:any[] = [];
        datas.forEach((record: any, index: number) => {
            if (Object.is(record.srfuf, '0')) {
                this.items.some((val: any, num: number) =>{
                    if(JSON.stringify(val) == JSON.stringify(record)){
                        this.items.splice(num,1);
                        return true;
                    }
                }); 
            }else{
               _datas.push(datas[index]);
            }
        });
        if (_datas.length === 0) {
            return;
        }
        let dataInfo = '';
        _datas.forEach((record: any, index: number) => {
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

        if (_datas.length < 5) {
            dataInfo = dataInfo + ' ' + (this.$t('app.dataView.sum') as string) + _datas.length + (this.$t('app.dataView.data') as string);
        } else {
            dataInfo = dataInfo + '...' + ' ' + (this.$t('app.dataView.sum') as string) + _datas.length + (this.$t('app.dataView.data') as string);
        }

        const removeData = () => {
            let keys: any[] = [];
            _datas.forEach((data: any) => {
                keys.push(data.srfkey);
            });
            let _removeAction = keys.length > 1 ? 'removeBatch' : this.removeAction ;
            const context:any = JSON.parse(JSON.stringify(this.context));
            const post: Promise<any> = this.service.delete(_removeAction,Object.assign(context,{ [this.controlInstance.appDeCodeName]: keys.join(';') }),Object.assign({ [this.controlInstance.appDeCodeName]: keys.join(';') },{viewparams:this.viewparams}), this.showBusyIndicator);
            return new Promise((resolve: any, reject: any) => {
                post.then((response: any) => {
                    if (!response || response.status !== 200) {
                        this.$Notice.error({ title: '', desc: (this.$t('app.commonWords.delDataFail') as string) + ',' + response.info });
                        return;
                    } else {
                        this.$Notice.success({ title: '', desc: (this.$t('app.commonWords.deleteSuccess') as string) });
                    }
                    //删除items中已删除的项
                    _datas.forEach((data: any) => {
                        this.items.some((item:any,index:number)=>{
                            if(Object.is(item.srfkey,data.srfkey)){
                                this.items.splice(index,1);
                                // <#if ctrl.isEnableGroup?? && ctrl.isEnableGroup()>
                                // this.group();
                                // </#if>
                                return true;
                            }
                        });
                    });
                    this.$emit('ctrl-event', { controlname: this.controlInstance.name, action: "remove", data: {} })
                    this.selections = [];
                    resolve(response);
                }).catch((response: any) => {
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

        dataInfo = dataInfo.replace(/[null]/g, '').replace(/[undefined]/g, '').replace(/[ ]/g, '');
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
     * 保存
     *
     * @param {*} $event
     * @returns {Promise<any>}
     * @memberof DataViewControlBase
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
                      let response = await this.service.add(this.createAction, JSON.parse(JSON.stringify(this.context)),item, this.showBusyIndicator);
                      successItems.push(JSON.parse(JSON.stringify(response.data)));
                    }
                }else if(Object.is(item.rowDataState, 'update')){
                    if(!this.updateAction){
                        this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: '${view.getName()}' + (this.$t('app.list.notConfig.updateAction') as string) });
                    }else{
                        Object.assign(item,{viewparams:this.viewparams});
//<#if de??>            if(this.controlInstance.getPSAppDataEntity){}
                        //if(item.${appde.getCodeName()?lower_case}){
                           // Object.assign(this.context,{${appde.getCodeName()?lower_case}:item.${appde.getCodeName()?lower_case}});
                       // }
//</#if>
                        // let de: any = this.controlInstance.getPSAppDataEntity;
                        // if(de){
                        //     Object.assign(this.context,{de.getCodeName():item.de.getCodeName()})
                        // }
                        let response = await this.service.add(this.updateAction,JSON.parse(JSON.stringify(this.context)),item, this.showBusyIndicator);
                        successItems.push(JSON.parse(JSON.stringify(response.data)));
                    }
                }
            } catch (error) {
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
            this.$Notice.error({ title: (this.$t('app.commonWords.saveFailed') as string), desc: item.majorentityname + (this.$t('app.commonWords.saveFailed') as string) + '！' });
            console.error(errorMessage[index]);
          });
        }
        return successItems;
    }


    /**
     * 刷新
     *
     * @param {*} [args={}]
     * @memberof DataViewControlBase
     */
    public refresh(args?: any) {
        this.curPage = 1;
        this.load(args, true);
    }

    /**
	 * 加载更多
	 *
	 * @memberof DataViewControlBase
	 */
    public loadMore(){
        if(this.totalRecord>this.items.length)
        {
            this.curPage = ++this.curPage;
            this.isAddBehind = true;
            this.load({});
        }
    }

    /**
     * 双击数据
     * 
     * @memberof DataViewControlBase
     */
    public handleDblClick(args: any) {
        this.$emit('ctrl-event', { controlname: this.controlInstance.name, action: "rowdblclick", data: args }); 
    }

    /**
     * 根据分组代码表绘制分组列表
     * 
     * @memberof DataViewControlBase
     */
    public async drawCodelistGroup(){
        let groups: Array<any> = [];
        let fields: Array<any> = [];
        let groupTree:Array<any> = [];
        let data:Array<any> = [...this.items];
        if(Object.keys(this.groupCodelist).length > 0){
            let groupCodelist: any = await this.codeListService.getDataItems(this.groupCodelist);
            groups = Util.deepCopy(groupCodelist);
        }
        if(groups.length == 0){
            console.warn("分组数据无效");
        }
        groups.forEach((group: any,i: number)=>{
            let children:Array<any> = [];
            data.forEach((item: any,j: number)=>{
                if(this.groupFieldCodelist){
                    if(Object.is(group.label,item[this.groupAppField])) {
                        children.push(item);
                    }
                }else if(Object.is(group.value,item[this.groupAppField])){
                    children.push(item);
                }
            });
            const tree: any ={
                group: group.label,
                children: children
            }
            groupTree.push(tree);
        });
        let child:Array<any> = [];
        data.forEach((item: any)=>{
            let i: number = 0;
            if(this.groupFieldCodelist){
                i = groups.findIndex((group: any)=>Object.is(group.label,item[this.groupAppField]));
            }else{
                i = groups.findIndex((group: any)=>Object.is(group.value,item[this.groupAppField]));
            }
            if(i < 0){
                child.push(item);
            }
        })
        const Tree: any = {
            group: this.$t('app.commonWords.other'),
            children: child
        }
        if(child && child.length > 0){
            groupTree.push(Tree);
        }
        this.groupData = [...groupTree];
    }

    /**
     * 绘制分组列表
     * 
     * @memberof DataViewControlBase
     */
    public async drawGroup(){
        let data:Array<any> = [...this.items];
        let groups:Array<any> = [];
        let fields: Array<any> = [];
        data.forEach((item: any)=>{
            if(item.hasOwnProperty(this.groupAppField)){
                groups.push(item[this.groupAppField]);
            }
        });
        groups = [...new Set(groups)];
        if(groups.length == 0){
            console.warn("分组数据无效");
        }
        let groupTree:Array<any> = [];
        groups.forEach((group: any,i: number)=>{
            let children:Array<any> = [];
            data.forEach((item: any,j: number)=>{
                if(Object.is(group,item[this.groupAppField])){
                    children.push(item);
                }
            });
            group = group ? group : this.$t('app.commonWords.other');
            const tree: any ={
                group: group,
                children: children
            }
            groupTree.push(tree);
        });
        this.groupData = [...groupTree];
    }

    /**
     * 分组方法
     * 
     * @memberof DataViewControlBase
     */
    public group(){
        let _this:any = this;
        if(_this.drawGroup && _this.drawGroup instanceof Function && Object.is(_this.groupMode,"AUTO")){
            _this.drawGroup();
        }else if(_this.drawCodelistGroup && _this.drawCodelistGroup instanceof Function && Object.is(_this.groupMode,"CODELIST")){
            _this.drawCodelistGroup();
        }
    }

    /**
     * 操作列界面行为
     *
     * @param {*} data
     * @param {*} tag
     * @param {*} $event
     * @memberof DataViewControlBase
     */
	public uiAction(data: any, tag: any, $event: any) {
        $event.stopPropagation();
        // <#if ctrl.getPSAppViewLogics()??>
        // <#list ctrl.getPSAppViewLogics() as logic>
        // <#if logic.getPSAppViewUIAction().getPSUIAction()??>
        // <#assign action = logic.getPSAppViewUIAction().getPSUIAction()>
        // if(Object.is('${action.getUIActionTag()}', tag)) {
        //     this.${logic.getName()}(data, tag, $event);
        // }
        // </#if>
        // </#list>
        // </#if>
        
        if(this.controlInstance.getPSAppViewLogics){
            for(let logic of this.controlInstance.getPSAppViewLogics){
                if(logic.getPSAppViewUIAction){
                    //todo
                    
                }
            }
        }
    }

    /**
     * 部件事件
     * 
     * @param {string} controlname
     * @param {string} action
     * @param {*} data
     * @memberof DataViewControlBase
     */
    public onCtrlEvent(controlname: string, action: string, data: any) {
        super.onCtrlEvent(controlname, action, data);
        if (action == 'panelDataChange') {
            this.onPanelDataChange(data.item, data.data);
        }
    }
    
    /**
     * 计算卡片视图部件所需参数
     *
     * @param {*} controlInstance 部件模型对象
     * @param {*} item 单条卡片数据
     * @returns
     * @memberof DataViewControlBase
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

    /**
     * 绘制数据视图内容
     * 
     * @memberof DataViewControlBase
     */
    public renderDataViewContent(h: any) {
      if (!this.isEnableGroup) {
          return this.items.map((item: any, index: number) => {
              let dataViewItem:any = this.controlInstance.getPSDEDataViewItems ? this.controlInstance.getPSDEDataViewItems[index] : null;
              return (
                  <a href={item.starturl}>
                      <i-col style='margin-bottom: 10px' >{this.renderCard(h, item,dataViewItem)}</i-col>
                  </a>
              )
          })
      } else {
          return (
              <el-collapse>
                  {this.renderGroup(h)}
              </el-collapse>
          )
      }
  }

  /**
   * 批操作工具栏
   * 
   * @memberof DataViewControlBase
   */
  public renderBatchToolbar(h: any) {
      if (this.controlInstance.getBatchPSDEToolbar) {
          return (
              <div class="drag-filed" on-mousedown={($event: any)=>this.down($event)}>
                  <row class='dataview-pagination'>
                    <div v-show={this.flag} class="batch-toolbar">
                        <view-toolbar toolbarModels={this.batchToolbarModels} on-item-click={(data: any, $event: any) => { this.handleItemClick('batchtoolbar',data, $event) }}></view-toolbar>
                    </div>
                    <div class="dataview-pagination-icon">
                        <icon type="md-code-working" on-click={($event: any)=>this.onClick($event)}/>
                    </div>
                  </row>
              </div>
          )
      }
  }

    /**
     * 快速操作工具栏
     * 
     * @memberof DataViewControlBase
     */
    public renderQuickToolbar(h: any){
        return (
            <div v-show={this.items.length == 0} calss="app-data-empty">
                {"无数据"}
                {
                    this.controlInstance.getQuickPSDEToolbar ?
                    <span class="quick-toolbar">
                        <view-toolbar toolbarModels={this.quickToolbarModels} on-item-click={(data: any, $event: any) => { this.handleItemClick('quicktoolbar',data, $event) }}></view-toolbar>
                    </span>
                    :null
                }
            </div>
        )
    }

  /**
   * 分组
   *  
   */
  public renderGroup(h: any) {
      this.groupData.map((group:any, index:number) => {
        let dataViewItem:any = this.controlInstance.getPSDEDataViewItems[index];
          return (
              <el-collapse-item>
                  <template slot="title">
                      <div style="margin: 0 0 0 12px;"><b>{group.group}</b></div>
                  </template>
                  {this.hasChildrenRender(h, group, dataViewItem)}
              </el-collapse-item>
          )
      })
  }

  /**
   * 分组项
   * 
   */
  public hasChildrenRender(h: any, group: any, dataViewItem:any) {
      if (group.children.length > 0) {
          group.children.map((groupChild: any, index: number) => {
              return (
                  <a href={groupChild.starturl}>
                      <i-col style="min-height: 170px;margin-bottom: 10px;">
                          {this.renderCard(h, groupChild, dataViewItem)}
                      </i-col>
                  </a>
              )
          })
      } else {
          return (
              <div v-else class="item-nodata">nodata</div>
          )
      }
  }

  /**
   * 绘制卡片
   * 
   * @memberof DataViewControlBase
   */
  public renderCard(h: any, args: any,dataViewItem:any) {
      return (
          <el-card
              shadow="always"
              class={[args.isselected === true ? 'isselected' : false, 'single-card-data']}
              nativeOnClick={() => this.handleClick(args)}
              nativeOnDblclick={() => this.handleDblClick(args)} >
              {this.controlInstance.getItemPSLayoutPanel ? this.renderItemPSLayoutPanel(args) : this.renderDataViewItem(h, args, dataViewItem)}
          </el-card>
      )
  }

  /**
   * 绘制DataViewItem
   * 
   * @memberof DataViewControlBase
   */
  public renderDataViewItem(h: any, args: any,dataViewItem:any) {
    if(this.controlInstance.getItemPSSysPFPlugin){
        const pluginInstance:any = this.PluginFactory.getPluginInstance("CONTROLITEM",this.controlInstance.getItemPSSysPFPlugin.pluginCode);
        if(pluginInstance){
            return pluginInstance.renderCtrlItem(this.$createElement,dataViewItem,this,args);
        }
    }else{
        let itemImage: any = args.srficonpath ? args.srficonpath : '/assets/img/noimage.png';
        return [<div class="data-view-item">
            <img src={itemImage} class="single-card-img" />
            <div class="single-card-default">
                <tooltip content={args.srfmajortext}>
                    {args.srfmajortext}
                </tooltip>
            </div>
        </div>,
        <div class="data-view-item-action">{dataViewItem ? this.renderDataViewItemAction(h, args, dataViewItem) : ''}</div>]
    }
  }

  /**
   * 绘制卡片视图项布局面板部件
   *
   * @returns {*}
   * @memberof DataViewControlBase
   */
  public renderItemPSLayoutPanel(item: any) {
      let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.controlInstance.getItemPSLayoutPanel,item);
      return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.controlInstance.getItemPSLayoutPanel?.name, on: targetCtrlEvent });
  }

  /**
   * 绘制列表项行为
   * @param item
   * @param dataViewItem
   * @memberof DataViewControlBase
   */
  public renderDataViewItemAction(h: any, args: any, dataViewItem:any) {
      const { getPSDEUIActionGroup: UIActionGroup } = dataViewItem;
      return UIActionGroup && UIActionGroup.getPSUIActionGroupDetails.map((uiactionDetail: any, index: number) => {
          const uiaction = uiactionDetail.getPSUIAction;
          if(args[uiaction.uIActionTag].visabled){
            return (
                <button type="info" disabled={args[uiaction.uIActionTag].disabled} on-click={($event: any) => { this.handleActionClick(args, $event, dataViewItem, uiactionDetail) }}>
                    {uiactionDetail.showIcon ? <i class={uiaction?.iconCls} style='margin-right:2px;'></i> : ''}
                    <span>{uiactionDetail.showCaption ? uiaction?.caption ? uiaction.caption : "" : ""}</span>
                </button>
            )
          }
      })
  }

  /**
   * 处理操作列点击
   * 
   * @memberof DataViewControlBase
   */
  public handleActionClick(data: any, event: any, item: any, detail: any) {
      AppViewLogicService.getInstance().excuteViewLogic(this.getViewLogicTag('dataviewexpbar_dataview',item.dataItemName, detail.name),event,this,data,this.controlInstance.getPSAppViewLogics);
  }

  /**
   * 部件工具栏点击
   * 
   * @param ctrl 部件 
   * @param data 数据
   * @param $event 事件源对象
   * @memberof AppDefaultKanban
   */
  public handleItemClick(ctrl: string, data: any,$event: any) {
      AppViewLogicService.getInstance().excuteViewLogic(this.getViewLogicTag(this.controlInstance.name, ctrl, data.tag),$event,this,undefined,this.controlInstance.getPSAppViewLogics);
  }

  /**
   * sortbar
   * 
   * @memberof DataViewControlBase
   */
  public renderSortBar(h: any) {
      const { appDataEntity, dataViewItems } = this.controlInstance;
      let hasSortBar: boolean = false;
      if (dataViewItems.length > 0) {
          hasSortBar = dataViewItems.some((dataItem: any) => {
              return dataItem.dataType != 'FRONTKEY';
          })
      }
      if (hasSortBar) {
          return (
              <app-sort-bar
                  sortModel={this.sortModel}
                  sortField={this.sortField}
                  sortDir={this.sortDir}
                  entityName={appDataEntity?.codeName}
                  on-clickSort={(val: any) => this.sortClick(val)}>
              </app-sort-bar>
          )
      }
  }
}
