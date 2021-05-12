
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { ViewTool, AppServiceBase, Util, ImgurlBase64 } from 'ibiz-core';
import { AppDefaultTree } from 'ibiz-vue/src/components/control/app-default-treeview/app-default-treeview';
import { AppViewLogicService } from 'ibiz-vue';
import { UIServiceRegister } from 'ibiz-service';

import '../plugin-style.less';

/**
 * 目录树插件插件类
 *
 * @export
 * @class DirectoryTree
 * @class DirectoryTree
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class DirectoryTree extends AppDefaultTree {

    /**
     * 面包屑数据(默认第一项为图标)
     * 
     * @type {Array<any>}
     * @memberof DirectoryTree
     */
    public breadcrumbs: Array<any> = [{curData: ''}];

    /**
     * 展现形式(默认为图表)
     * 
     * @type {string}
     * @memberof DirectoryTree
     */
    public mode: string = 'chart';

    /**
     * 当前文件夹
     *
     * @type {*}
     * @memberof DirectoryTree
     */
    public currentNode = {};

    /**
     * 当前页数据
     *
     * @type {Array<any>}
     * @memberof DirectoryTree
     */
    public curPageItems: any[] = [];

    /**
     * 图片加载路径
     *
     * @type {string}
     * @memberof DirectoryTree
     */
    public downloadUrl = AppServiceBase.getInstance().getAppEnvironment().ExportFile;

    /**
     * 目录树部件初始化
     *
     * @memberof DirectoryTree
     */
    public ctrlInit() {
        super.ctrlInit();
        if (this.viewState) {
            this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
                if (!Object.is(tag, this.name)) {
                    return;
                }
                if (Object.is('load', action)) {
                    this.initData();
                    this.load();
                }
                if (Object.is('filter', action)) {
                    this.srfnodefilter  = data.srfnodefilter;
                    this.refresh();
                }
                if (Object.is('refresh_parent', action)) {
                    this.refresh();
                }
                if (Object.is('refresh_current', action)) {
                    this.refresh();
                }
            });
        }
    }

    /**
     * 初始化目录树参数
     * 
     * @memberof DirectoryTree
     */
    public initData(){
        this.breadcrumbs.splice(1);
        this.mode = 'chart';
        this.curPage = 1;
        this.totalRecord = 0;
        this.limit = 20;
        this.srfnodefilter = '';
    }

    /**
     * 刷新功能
     *
     * @param {any[]} args
     * @memberof DirectoryTree
     */
     public refresh(args?: any): void {
        const node = this.currentNode;
        this.load(node);
    }

    /**
     * 加载当前文件夹所有文件
     *
     * @param {*} node 当前文件夹对象
     * @memberof DirectoryTree
     */
    public async load(node: any = {}, resolve?: any) {
        this.items = [];
        this.currentNode = node;
        if (node.data && node.data.children) {
            return;
        }
        const params: any = {
            srfnodeid: node.data && node.data.id ? node.data.id : "#",
            srfnodefilter: this.srfnodefilter
        };
        let tempViewParams:any = JSON.parse(JSON.stringify(this.viewparams));
        let curNode:any = {}; 
        curNode = this.$util.deepObjectMerge(curNode,node);
        let tempContext:any = this.computecurNodeContext(curNode);
        if(curNode.data && curNode.data.srfparentdename){
            Object.assign(tempContext,{srfparentdename:curNode.data.srfparentdename});
            Object.assign(tempViewParams,{srfparentdename:curNode.data.srfparentdename});
        }
        if(curNode.data && curNode.data.srfparentkey){
            Object.assign(tempContext,{srfparentkey:curNode.data.srfparentkey});
            Object.assign(tempViewParams,{srfparentkey:curNode.data.srfparentkey});
        }
        Object.assign(params,{viewparams:tempViewParams});
		this.ctrlBeginLoading();
		try {
			const response = await this.service.getNodes(tempContext,params)
			this.ctrlEndLoading();
			if (!response || response.status !== 200) {
				this.$Notice.error({ title: (this.$t('app.commonWords.wrong') as string), desc: response.info });
				return;
			}
			const _items = response.data;
			this.items = [..._items];
			this.totalRecord = _items.length; 
			await this.computeCurPageNodeState();
			this.$emit("load", _items);
		} catch (error) {
			this.ctrlEndLoading();
        }
    }

    /**
     * 搜索
     * 
     * @param query 搜索值
     * @memberof DirectoryTree
     */
    public onSearch(query: string){
        const node = this.currentNode;
        this.curPage = 1;
        this.load(node);
    }

    /**
     * 计算当前节点的上下文
     *
     * @param {*} curNode 当前节点
     * @memberof DirectoryTree
     */
    public computecurNodeContext(curNode:any) {
        let tempContext:any = {};
        if (curNode && curNode.data && curNode.data.srfappctx) {
            tempContext = JSON.parse(JSON.stringify(curNode.data.srfappctx));
        } else {
            tempContext = JSON.parse(JSON.stringify(this.context));
        }
        return tempContext;
    }

    /**
     * 添加面包屑数据
     * 
     * @param node 文件夹对象
     * @memberof DirectoryTree
     */
    public addBreadcrumbs(node: any) {
        if (this.breadcrumbs.length > 0) {
            const index: number = this.breadcrumbs.findIndex((item: any) => Object.is(item.srfkey, node.srfkey));
            if(index === -1){
                this.breadcrumbs.push(node);
            }
        } else {
            this.breadcrumbs.push(node);
        }
    }

    /**
     * 移除面包屑数据
     * 
     * @param node 文件夹对象
     * @memberof DirectoryTree
     */
    public removeBreadcrumbs(node: any) {
        if (this.breadcrumbs.length > 0) {
            const index: number = this.breadcrumbs.findIndex((item: any) => Object.is(item.srfkey, node.srfkey));
            if (index !== -1) {
                this.breadcrumbs.splice(index+1);
            }
        }
    }

    /**
     * 面包屑跳转文件夹
     * 
     * @param node 文件夹对象
     * @memberof DirectoryTree
     */
    public handleLink(node: any) {
        this.removeBreadcrumbs(node);
        this.srfnodefilter = '';
        this.load({data: node});
    }

    /**
     * 切换展现形式
     * 
     * @param model 展现形式
     * @memberof DirectoryTree
     */
    public modeChange(mode: string) {
        this.mode = mode;
        this.computeCurPageNodeState();
    }

    /**
     * 计算当前页显示数据(若为list模式则计算当前页的工具栏权限状态)
     * 
     * @memberof DirectoryTree
     */
    public async computeCurPageNodeState(){
        this.curPageItems = [];
        let curPageItems: Array<any> = [];
        const start = (this.curPage-1) * this.limit;
        let end = this.curPage * this.limit;
        end = end > this.items.length ? this.items.length : end;
        curPageItems = this.items.slice(start,end);
        if (Object.is(this.mode,'list')) {
            await this.computeAllNodeState(curPageItems);
        }
        this.curPageItems = [...curPageItems];
    }

    /**
     * 打开目标文件
     * 
     * @param file 目标文件
     * @memberof DirectoryTree
     */
    public openNode(node: any) {
        this.addBreadcrumbs(node);
        this.srfnodefilter = '';
        this.load({data: node});
    }

    /**
     * 计算指定文件夹的工具栏状态
     * 
     * @memberof DirectoryTree
     */
    public async computeAllNodeState(items: Array<any>) {
        if (items && items.length > 0) {
            for (let i=0; i < items.length; i++) {
                if (!items[i].curData || !items[i].curData.copyActionModel) {
                    await this.getNodeState(items[i]);
                }
            }
        }
    }

    /**
     * 获取当前文件夹指定文件的工具栏状态
     * 
     * @param node 指定的文件对象
     * @memberof DirectoryTree
     */
    public async getNodeState(node: any) {
        this.copyActionModel = {};
        const tags: string[] = node.id.split(';');
        Object.values(this.actionModel).forEach((item:any) =>{
            if(Object.is(item.nodeOwner,tags[0])){
                this.copyActionModel[item.name] = item;
            }
        })
        if(Object.keys(this.copyActionModel).length === 0){
            return;
        }
        const result = await this.computeNodeState(node,node.nodeType,node.appEntityName)
        if(Object.values(result).length>0){
            node.curData.copyActionModel = JSON.parse(JSON.stringify(this.copyActionModel));
        }
    }

    /**
     * 计算文件的工具栏状态
     *
     * @param {*} node 指定的文件对象
     * @param {*} nodeType 指定的文件类型
     * @param {*} appEntityName 应用实体名称  
     * @returns
     * @memberof DirectoryTree
     */
    public async computeNodeState(node: any, nodeType: string, appEntityName: string) {
        if(Object.is(nodeType,"STATIC")){
            return this.copyActionModel;
        }
        if(this.copyActionModel && Object.keys(this.copyActionModel).length > 0) {
            let tempContext:any = Util.deepCopy(this.context);
            tempContext[appEntityName] = node.srfkey;
            let targetData = node.curData;
            let uiservice:any = await UIServiceRegister.getInstance().getService(this.context, appEntityName.toLowerCase());
            if (uiservice) {
                await uiservice.loaded();
            }
            ViewTool.calcTreeActionItemAuthState(targetData, this.copyActionModel, uiservice);
            return this.copyActionModel;
        }
    }

    /**
     * 图表模式下工具栏的显示
     *
     * @param {*} index 工具栏标识
     * @memberof DirectoryTree
     */
    public async showToolBar(item: any,index: number){
        if(!item.curData || !item.curData.copyActionModel){
            await this.getNodeState(item);
            this.$forceUpdate();
        }
        let el: any = this.$el.getElementsByClassName('chart-item-operate')[index];
        if (el) {
            el.style.display = 'block'; 
        }
    }

    /**
     * 图表模式下工具栏的隐藏
     *
     * @param {*} index 工具栏标识
     * @memberof DirectoryTree
     */
    public hideToolBar(index: number){
        let el: any = this.$el.getElementsByClassName('chart-item-operate')[index];
        if (el) {
            el.style.display = 'none'; 
        }
    }

    /**
     * 页面变化
     *
     * @param {*} $event
     * @returns {void}
     * @memberof DirectoryTree
     */
    public pageOnChange($event: any): void {
        if (!$event || $event === this.curPage) {
            return;
        }
        this.curPage = $event;
        this.computeCurPageNodeState();
    }

    /**
     * 分页条数变化
     *
     * @param {*} $event
     * @returns {void}
     * @memberof DirectoryTree
     */
    public onPageSizeChange($event: any): void {
        if (!$event || $event === this.limit) {
            return;
        }
        this.limit = $event;
        this.computeCurPageNodeState();
    }

    /**
     * 分页刷新
     *
     * @memberof DirectoryTree
     */
    public pageRefresh(): void {
        const node = this.currentNode;
        this.load(node);
    }

    /**
     * 工具栏触发行为
     *
     * @param {*} item 触发行为文件对象
     * @param {*} tag 触发行为标识
     * @memberof DirectoryTree
     */
    public onAction(item: any, ctrlname: string, tag: string, $event: any) {
        this.currentselectedNode = Util.deepCopy(item);
        AppViewLogicService.getInstance().executeViewLogic(`${ctrlname}_${tag}_click`, $event, this, this.currentselectedNode.curData, this.controlInstance.getPSAppViewLogics() || []);
    }

    /**
     * 绘制目录树操作栏
     * 
     * @memberof DirectoryTree
     */
    public renderOperation(){
        return (
            <div class="documents-library-operation">
                <breadcrumb separator=">">
                    {
                        this.breadcrumbs.map((item: any, index: number) => {
                            return (
                                <breadcrumbItem>
                                    {index === 0 ? <icon on-click={()=>this.handleLink(item)} type="md-folder" size="25" color="rgb(255, 202, 40)" /> : null }
                                    {index < this.breadcrumbs.length-1 ? <a on-click={()=>this.handleLink(item)}>{item.text}</a> : null}
                                    {index !== 0 && index >= this.breadcrumbs.length-1 ? <span>{item.text}</span> : null}
                                </breadcrumbItem>
                            );
                        })
                    }
                </breadcrumb>
                <div class="documents-library-toolbar">
                    <div class="documents-library-search">
                        <i-input size="small" value={this.srfnodefilter} clearable search on-on-search={()=>this.onSearch('')} placeholder="文件名"></i-input>
                    </div>
                    <div class="documents-library-button">
                        <buttonGroup size="small">
                            <i-button icon="md-apps" class={Object.is(this.mode,'chart') ? 'button-active-background' : ''} on-click={()=>this.modeChange('chart')}></i-button>
                            <i-button icon="md-menu" class={Object.is(this.mode,'list') ? 'button-active-background' : ''} on-click={()=>this.modeChange('list')}></i-button>
                        </buttonGroup>
                    </div>  
                </div>
            </div>
        );
    }

    /**
     * 绘制目录树图表模式
     * 
     * @memberof DirectoryTree
     */
    public renderChartMode(){
        return (
            <div class="documents-library-chart">
                {
                    this.curPageItems.map((item: any, index: number) => {
		                  if (item.curData && (Object.is(item.curData.doclibtype,'file') || Object.is(item.curData.docqtype,'doc'))) {
                            this.getImgurlBase64(item.curData);
                        }
                        return (
                            <div class="chart-item" on-mouseleave={()=>this.hideToolBar(index)} on-mouseenter={()=>this.showToolBar(item,index)}>
                                {
                                    Object.is(item.nodeType,'STATIC') || (item.curData && (Object.is(item.curData.doclibtype,'doc') || Object.is(item.curData.docqtype,'module'))) ?
                                    <div on-dblclick={()=>this.openNode(item)}>
                                        <icon type="md-folder" size="70" color="rgb(255, 202, 40)" />
                                    </div> : null
                                }
                                {
                                    item.curData && (Object.is(item.curData.doclibtype,'file') || Object.is(item.curData.docqtype,'doc')) ?
                                    <div>
                                        {
                                            !item.curData.pathname ? <icon type="md-document" size="70" color="darkgrey" /> : <img style="width: 70px;height: 70px;" src={item.curData.imgBase64} onerror="/assets/img/noimage.png"/>
                                        }
                                    </div> : null
                                }
                                <div class="text-description">
                                    {
                                        Object.is(item.nodeType,'STATIC') || (item.curData && (Object.is(item.curData.doclibtype,'doc') || Object.is(item.curData.docqtype,'module'))) ?
                                        <a on-click={()=>this.openNode(item)}>
                                            <span>{item.text}</span>
                                        </a> : null
                                    }
                                    {
                                        item.curData && (Object.is(item.curData.doclibtype,'file') || Object.is(item.curData.docqtype,'doc')) ?
                                        <span>{item.text}</span> : null
                                    }
                                </div>
                                <div class="chart-item-operate">
                                    {
                                        item.curData && item.curData.copyActionModel ?
                                        <div>
                                            {
                                                 Object.values(item.curData.copyActionModel).map((actionModel: any, _index: number) =>{
                                                    return (
                                                        actionModel.visabled ? 
                                                        <a
                                                            class="chart-item-action"
                                                            disabled={actionModel.disabled}
                                                            on-click={($event: any)=>this.onAction(item,actionModel.ctrlname,actionModel.name,$event)}>
                                                            {
                                                                actionModel.imgclass ? <i title={actionModel.title} class={actionModel.imgclass}/> : null
                                                            }
                                                            <span>{actionModel.caption}</span>
                                                        </a> : null
                                                    );
                                                })
                                            }
                                        </div> : null
                                    }
                                </div>
                            </div>
                        );
                    })
                }
            </div>
        );
    }

    /**
     * 绘制目录树列表模式
     * 
     * @memberof DirectoryTree
     */
    public renderListMode(){
        return (
            <div class="documents-library-list">
                {
                    this.curPageItems.map((item: any, index: number) => {
                        return (
                            <div class="list-item">
                                <div>
                                    {
                                        Object.is(item.nodeType,'STATIC') || (item.curData && (Object.is(item.curData.doclibtype,'doc') || Object.is(item.curData.docqtype,'module'))) ?
                                        <span on-click={()=>this.openNode(item)}>
                                            <icon type="md-folder" color="rgb(255, 202, 40)" size="20" /><a>{item.text}</a>
                                        </span> : null
                                    }
                                    {
                                        item.curData && (Object.is(item.curData.doclibtype,'file') || Object.is(item.curData.docqtype,'doc')) ?
                                        <span>
                                            <icon type="md-document" color="darkgrey" size="20" />{item.text}
                                        </span> : null
                                    }
                                </div>
                                {
                                    item.curData && item.curData.copyActionModel ?
                                    <div>
                                        {
                                            
                                            Object.values(item.curData.copyActionModel).map((actionModel: any, _index: any) =>{
                                                return (
                                                    actionModel.visabled ? 
                                                    <a
                                                        class="list-item-action"
                                                        disabled={actionModel.disabled}
                                                        on-click={($event: any)=>this.onAction(item,actionModel.ctrlname,actionModel.name,$event)}>
                                                        {
                                                            actionModel.imgclass ? <i title={actionModel.title} class={actionModel.imgclass}/> : null
                                                        }
                                                        <span>{actionModel.caption}</span>
                                                    </a> : null
                                                );
                                            })
                                        }
                                    </div> : null
                                }     
                            </div>
                        );
                    })
                }
            </div>
        );
    }  

    /**
     * 绘制页码分页
     * 
     * @memberof DirectoryTree
     */
    public renderPagination(){
        return (
            <row class="pagination">
                <page
                    show-sizer
                    class="pull-right"
                    current={this.curPage} 
                    page-size={this.limit}
                    total={this.totalRecord}
                    on-on-change={($event: any) => this.pageOnChange($event)} 
                    on-on-page-size-change={($event: any) => this.onPageSizeChange($event)}
                    page-size-opts={[10, 20, 30, 40, 50, 60, 70, 80, 90, 100]} show-elevator show-total>
                    <span>
                        <span class="page-button"><i-button icon="md-refresh" title="刷新" on-click={()=>this.pageRefresh()}></i-button></span>&nbsp;
                        <span>
                            显示&nbsp;
                            <span>
                                { this.items.length === 0 ? 0 : <span>{(this.curPage - 1) * this.limit + 1}&nbsp;-&nbsp;{this.totalRecord > this.curPage * this.limit ? this.curPage * this.limit : this.totalRecord}</span>}
                            </span>&nbsp;
                            条，共&nbsp;{this.totalRecord}&nbsp;条
                        </span>
                    </span>
                </page>
            </row>
        );
    }

    /**
     * 绘制无数据
     * 
     * @memberof DirectoryTree
     */
    public renderNoData(){
        return (
            <div class="documents-library-no-data">
                <span>无数据</span>
            </div>
        );
    }

    /**
     * 绘制目录树插件
     * 
     * @memberof DirectoryTree
     */
    public render(){
        if(!this.controlIsLoaded){
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        const { name } = this.controlInstance;
        return (
            <div
                ref={name}
                class={{'documents-library': true, ...controlClassNames }}>
                    {this.renderOperation()}
                    {   
                        this.items.length > 0 ?
                        [
                            Object.is(this.mode,'chart') ? this.renderChartMode() : this.renderListMode(),
                            this.renderPagination()
                        ] : this.renderNoData()
                    }
            </div>
        );
    }
	
    /**
     * 获取当前数据图片
     * 
     * @param curData 
     * @memberof DirectoryTree
     */
    public getImgurlBase64(curData: any){
        const path = this.downloadUrl + '/' + curData.id;
        ImgurlBase64.getInstance().getImgURLOfBase64(path).then((res: any) => {
            if (Object.is(res, './')) {
                this.$set(curData, 'imgBase64', '/assets/img/noimage.png');
            } else {
                this.$set(curData, 'imgBase64', res);
            }
        })
    }

}

