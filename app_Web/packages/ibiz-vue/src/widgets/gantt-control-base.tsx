import { Watch } from 'vue-property-decorator';
import { Util, CodeListServiceBase, ViewTool, ModelTool, GanttControlInterface } from 'ibiz-core';
import { IPSAppDataEntity, IPSAppDERedirectView, IPSAppDEView, IPSAppUILogicRefView, IPSAppUIOpenDataLogic, IPSAppView, IPSAppViewLogic, IPSAppViewRef, IPSDEGantt, IPSDETreeColumn, IPSDETreeNode, IPSDETreeNodeDataItem, IPSNavigateContext, IPSNavigateParam } from '@ibiz/dynamic-model-api';
import { MDControlBase } from './md-control-base';
import { AppGanttService } from '../ctrl-service';
import { UIServiceRegister } from 'ibiz-service';
import { Subject } from 'rxjs';

export class GanttControlBase extends MDControlBase implements GanttControlInterface{
    
    /**
     * 甘特图模型实例
     * 
     * @type {*}
     * @memberof GanttControlBase
     */
    public controlInstance!: IPSDEGantt;

    /**
     * 语言
     *
     * @public
     * @type {string}
     * @memberof GanttControlBase
     */  
    public locale: string = 'zh-CN';

     /**
     * 语言资源 - zh-cn
     *
     * @public
     * @type {*}
     * @memberof GanttControlBase
     */   
    public localeZH: any =  {
        weekdays: ['星期一','星期二','星期三','星期四','星期五','星期六','星期日'],
        months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        Now: "现在",
        "X-Scale": "时间颗粒度",
        "Display task list": "显示列表",
        "Before/After": "数据范围"
    };

    /**
     * 语言资源 - en-us
     *
     * @public
     * @type {*}
     * @memberof GanttControlBase
     */   
    public localeEN: any = {
        weekdays: ['Monday','Tuesday','Wednesday','Thursday','Friday','Saturday','Sunday'],
        months: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
        Now: "Now",
        "X-Scale": "X-Scale",
        "Display task list": "Display task list",
        "Before/After": "Before/After"
    }

    /**
     * 配置参数
     *
     * @public
     * @type {any[]}
     * @memberof GanttControlBase
     */
    public options: any = {
        isflex: true,
        maxRows: 1000,
        dataType: 'treegrid',
        title: {
            label: ""
        },
        header: {
            yScole: false,
            taskListWidth: false,
        },
        calendar: {
            hour: {
                display: false
            }
        },
        chart: {
            progress: {
                bar: false
            },
            expander: {
                display: true
            },
        },
        taskList: {
            expander: {
                straight: false
            },
            labelField: 'text',
            columns: []
        }
    };

    /**
     * 样式
     *
     * @public
     * @type {any[]}
     * @memberof GanttControlBase
     */
    public dynamicStyle: any = {};

    /**
     * 日程事件集合
     *
     * @public
     * @type {any[]}
     * @memberof GanttControlBase
     */
    public tasks: any[] = [];

    /**
     * 代码表数据
     * 
     * @memberof GanttControlBase
     */
    public codeListData: Map<string, any> = new Map();

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof GanttControlBase
     */
    public async ctrlModelInit() {
        super.ctrlModelInit();
        if (!(this.Environment && this.Environment.isPreviewMode)) {
            this.service = new AppGanttService(this.controlInstance, this.context);
        }
        await this.initColumnsCodeList();
        this.initOptions();
    }

    /**
     * 初始化甘特图部件配置参数
     * 
     * @memberof GanttControlBase
     */
    public initOptions() {
        const treeColumns: IPSDETreeColumn[] = this.controlInstance.getPSDETreeColumns() || [];
        if(treeColumns?.length>0) {
            let tempOptions: any[] = [];
            treeColumns.forEach((column: IPSDETreeColumn) => {
                const name = column.name == "begin" ? "start" : column.name;
                let option: any = {
                    label: this.$tl(column.getCapPSLanguageRes()?.lanResTag, column?.caption),
                    value: name,
                    render: (task: any) => {
                        if(column?.getRenderPSSysPFPlugin()){
                            let plugin:any = column.getRenderPSSysPFPlugin();
                            const pluginInstance:any = this.PluginFactory.getPluginInstance("CONTROLITEM",plugin.pluginCode);
                            if(pluginInstance){
                                return pluginInstance.renderCtrlItem(this.$createElement,column,this,task);
                            }
                        }else{
                            return this.getColumnValue(task, name);
                        }
                    },
                    width: column.width && column.width>0 ? column.width : 100,
                };
                if(column.name == "text") {
                    option["expander"] = true;
                }
                if(column.hideDefault) {
                    option["hidden"] = true;
                }
                if(column.getHeaderPSSysCss()?.cssName) {
                    option["headerCls"] = column.getHeaderPSSysCss()?.cssName;
                }
                if(column.getCellPSSysCss()?.cssName) {
                    option["cellCls"] = column.getCellPSSysCss()?.cssName;
                }
                tempOptions.push(option);
            })
            if(tempOptions) {
                this.options.taskList.columns = [...tempOptions];
            }
        } else {
            const majorField = ModelTool.getAppEntityMajorField(this.controlInstance.getPSAppDataEntity());
            this.options.taskList.columns = [{
                label: majorField ? this.$tl(majorField.getLNPSLanguageRes()?.lanResTag, majorField.logicName) : this.$t('app.commonwords.srfmajortext'),
                value: 'srfmajortext',
                width: 100,
                render: (task: any) => {
                    return task['srfmajortext'];
                }
            }]
        }
    }

    /**
     * 初始化列代码表
     * 
     * @public
     * @memberof GanttControlBase
     */
    public async initColumnsCodeList(){
        let _this: any = this;
        this.codeListData = new Map();
        this.codeListService = new CodeListServiceBase({ $store: _this.$store });
        const treeNodes =  this.controlInstance.getPSDETreeNodes() || [];
        for(let i = 0; i < treeNodes.length; i++){
            const dataItems = treeNodes[i].getPSDETreeNodeDataItems();
            if(dataItems && dataItems.length > 0){
                for(let j = 0; j < dataItems.length; j++){
                    const dataItem = dataItems[j]
                    if(dataItem.getFrontPSCodeList && dataItem.getFrontPSCodeList()){
                        const codelistJson = dataItem.getFrontPSCodeList();
                        await codelistJson?.fill();
                        if(codelistJson){
                            let items = await this.codeListService.getDataItems({ type: codelistJson.codeListType, tag: codelistJson.codeName,data:codelistJson, context: this.context });
                            this.codeListData.set(dataItem.name,items);
                        }
                    }
                }
            }      
        }     
    }

    /**
     * 部件初始化
     *
     * @public
     * @memberof GanttControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        //TODO  国际化暂未支持
        let that: any = this;
        that.locale = that.$i18n.locale;
        this.updateOptions();
        this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
            if (!Object.is(tag, this.name)) {
                return;
            }
            if (Object.is('load', action)) {
                this.load(data);
            }
        });
    }

    /**
     * 加载数据
     *
     *
     * @param {*} [task={}] 节点数据
     * @memberof GanttControlBase
     */
    public load(task: any = {}, isRefresh: boolean = false) {
        const params: any = {
            srfnodeid: task && task.id ? task.id : "#",
            srfnodefilter: ''
        };
        let tempViewParams:any = Util.deepCopy(this.viewparams);
        let curNode:any = {}; 
        Util.deepObjectMerge(curNode, task);
        let tempContext:any = this.computecurNodeContext(curNode);
        if(curNode && curNode.srfparentdename) {
            Object.assign(tempContext,{ srfparentdename: curNode.srfparentdename });
            Object.assign(tempViewParams,{ srfparentdename: curNode.srfparentdename });
        }
        if(curNode && curNode.srfparentdemapname) {
            Object.assign(tempContext,{ srfparentdemapname: curNode.srfparentdemapname });
            Object.assign(tempViewParams,{ srfparentdemapname: curNode.srfparentdemapname });
        }
        if(curNode && curNode.srfparentkey) {
            Object.assign(tempContext,{ srfparentkey: curNode.srfparentkey });
            Object.assign(tempViewParams,{ srfparentkey: curNode.srfparentkey });
        }
        Object.assign(params,{ viewparams: tempViewParams });
        this.onControlRequset('load', tempContext, params);
        this.service.getNodes(tempContext,params).then((response: any) => {
            this.onControlResponse('load', response);
            if (!response || response.status !== 200) {
                this.$throw(response,'load');
                return;
            }
            if (isRefresh) {
              this.tasks = [...response.data];
            } else {
              this.tasks = [...this.tasks, ...response.data];
            }
            response.data.forEach((item: any) => {
                if(!item.collapsed) {
                    this.load(item);
                }
            })
            this.ctrlEvent({
                controlname: this.name,
                action: 'load',
                data: this.tasks,
            });
        }).catch((response: any) => {
            this.onControlResponse('load', response);
            this.$throw(response,'load');
        });
    }

    /**
     * 刷新功能
     *
     * @param {*} [args] 额外参数
     * @memberof GanttControlBase
     */
    public refresh(args?: any) {
        this.load({},true);
    }

    /**
     * 节点点击事件
     *
     * @param {{event: any, data: any}} {event, data} 事件源，节点数据
     * @memberof GanttControlBase
     */
    public taskClick({event, data}: {event: any, data: any}) {
        let logicTag: string = data.id.split(';')[0]?.toLowerCase() + '_opendata';
        this.ganttOpendata([data], logicTag);
    }

    /**
     * 节点展开事件
     *
     * @param {*} task 当前节点
     * @memberof GanttControlBase
     */
    public taskItemExpand(task: any) {
        if(!task.collapsed) {
            let index: number = this.tasks.findIndex((item: any) => Object.is(task.id, item.parentId));
            if(index < 0) {
                this.load(task);
            }
        }
    }

    /**
     * 计算当前节点的上下文
     *
     * @param {*} curNode 当前节点
     * @memberof GanttControlBase
     */
    public computecurNodeContext(curNode:any){
        let tempContext:any = {};
        if(curNode && curNode.srfappctx){
            tempContext = Util.deepCopy(curNode.srfappctx);
        }else{
            tempContext = Util.deepCopy(this.context);
        }
        return tempContext;
    }

    /**
     * 获取列属性值
     *
     * @public
     * @memberof GanttControlBase
     */
    public getColumnValue(task: any, field: string) {
        const treeNodes =  this.controlInstance.getPSDETreeNodes() || [];
        const node = treeNodes?.find((_node: IPSDETreeNode) => {
            return task.id.split(";")[0] == _node.nodeType;
        });
        const dataItems = node?.getPSDETreeNodeDataItems() || [];
        if(dataItems?.length > 0) {
            const dataItem = dataItems.find((_item: IPSDETreeNodeDataItem) => {
                return _item.name == field;
            });
            if(dataItem){
                const codelistData = this.codeListData?.get(dataItem.name);
                if(codelistData) {
                    const item = codelistData.find((item: any) => {
                        return item.value == task[field];
                    })
                    if(item){
                        return item.text;
                    }
                }
            }
        }
        return task[field];
    }

    /**
     * 更新部件参数
     *
     * @returns {any}
     * @memberof GanttControlBase
     */
    public updateOptions() {
        if(Object.is(this.locale, 'zh-CN')) {
            this.options.locale = this.localeZH
        }else{
            this.options.locale = this.localeEN;
        }
    }

    /**
     * 打开编辑数据视图
     *
     * @param {any[]} args 数据参数
     * @param {*} [fullargs] 全量参数
     * @param {*} [params]  额外参数
     * @param {*} [$event] 事件源数据
     * @param {*} [xData] 数据部件
     * @memberof GanttControlBase
     */
    public async ganttOpendata(args: any[], logicTag: string, fullargs?: any, params?: any, $event?: any, xData?: any) {
        const openAppViewLogic: IPSAppViewLogic | undefined = this.controlInstance.getPSAppViewLogics()?.find((item: any) => {
            return item.name == logicTag;
        })
        if (!openAppViewLogic || !openAppViewLogic.getPSAppUILogic()) {
            return;
        }
        let viewOpenAppUIlogic:
            | IPSAppUIOpenDataLogic
            | undefined
            | null = openAppViewLogic.getPSAppUILogic() as IPSAppUIOpenDataLogic;
        if (viewOpenAppUIlogic && viewOpenAppUIlogic?.getParentPSModelObject()?.M.viewType) {
            // todo
        }
        if (viewOpenAppUIlogic?.getOpenDataPSAppView()) {
            const openViewRef: IPSAppUILogicRefView = viewOpenAppUIlogic.getOpenDataPSAppView() as IPSAppUILogicRefView;
            const data: any = {};
            let tempContext = Util.deepCopy(this.context);
            // 准备参数
            if (args.length > 0) {
                Object.assign(tempContext, args[0]);
            }
            if (
                openViewRef?.getPSNavigateContexts() &&
                (openViewRef?.getPSNavigateContexts() as IPSNavigateContext[])?.length > 0
            ) {
                const localContext = Util.formatNavParam(openViewRef.getPSNavigateContexts());
                let _context: any = Util.computedNavData(fullargs[0], this.context, this.viewparams, localContext);
                Object.assign(tempContext, _context);
            }
            if (
                openViewRef?.getPSNavigateParams() &&
                (openViewRef.getPSNavigateParams() as IPSNavigateParam[])?.length > 0
            ) {
                const localViewParam = Util.formatNavParam(openViewRef.getPSNavigateParams());
                let _param: any = Util.computedNavData(fullargs[0], this.context, this.viewparams, localViewParam);
                Object.assign(data, _param);
            }
            if (
                fullargs &&
                fullargs.length > 0 &&
                fullargs[0]['srfprocessdefinitionkey'] &&
                fullargs[0]['srftaskdefinitionkey']
            ) {
                Object.assign(data, { processDefinitionKey: fullargs[0]['srfprocessdefinitionkey'] });
                Object.assign(data, { taskDefinitionKey: fullargs[0]['srftaskdefinitionkey'] });
                // 将待办任务标记为已读准备参数
                const that: any = this;
                if (that.quickGroupData && that.quickGroupData.hasOwnProperty('srfwf') && fullargs[0]['srftaskid']) {
                    Object.assign(data, { srfwf: that.quickGroupData['srfwf'] });
                    Object.assign(data, { srftaskid: fullargs[0]['srftaskid'] });
                }
            }
            let deResParameters: any[] = [];
            let parameters: any[] = [];
            const openView: IPSAppView | null = openViewRef.getRefPSAppView();
            if (!openView) return;
            await openView.fill();
            if (openView.getPSAppDataEntity()) {
                // 处理视图关系参数 （只是路由打开模式才计算）
                if (!openView.openMode || openView.openMode == 'INDEXVIEWTAB' || openView.openMode == 'POPUPAPP') {
                    deResParameters = Util.formatAppDERSPath(
                        tempContext,
                        (openView as IPSAppDEView).getPSAppDERSPaths(),
                    );
                }
            }
            if (!openView?.openMode || openView.openMode == 'INDEXVIEWTAB') {
                if (openView.getPSAppDataEntity()) {
                    parameters = [
                        {
                            pathName: Util.srfpluralize(
                                (openView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName,
                            ).toLowerCase(),
                            parameterName: (openView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName.toLowerCase(),
                        },
                        {
                            pathName: 'views',
                            parameterName: ((openView as IPSAppDEView).getPSDEViewCodeName() as string).toLowerCase(),
                        },
                    ];
                } else {
                    parameters = [{ pathName: 'views', parameterName: openView.name?.toLowerCase() }];
                }
            } else {
                if (openView?.getPSAppDataEntity()) {
                    parameters = [
                        {
                            pathName: Util.srfpluralize(
                                (openView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName,
                            )?.toLowerCase(),
                            parameterName: (openView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName?.toLowerCase(),
                        },
                    ];
                }
                if (openView && openView.modelPath) {
                    Object.assign(tempContext, { viewpath: openView.modelPath });
                }
            }
            // 关闭视图回调
            let callback: Function = (result: any, xData: any) => {
                if (!result || !Object.is(result.ret, 'OK')) {
                    return;
                }
                if (!xData || !(xData.refresh instanceof Function)) {
                    return;
                }
                xData.refresh(result.datas);
            };
            // 重定向视图
            if (openView?.redirectView) {
                let targetRedirectView: IPSAppDERedirectView = openView as IPSAppDERedirectView;
                await targetRedirectView.fill();
                if (
                    targetRedirectView.getRedirectPSAppViewRefs() &&
                    targetRedirectView.getRedirectPSAppViewRefs()?.length === 0
                ) {
                    return;
                }
                const redirectUIService: any = await UIServiceRegister.getInstance().getService(this.context, (ModelTool.getViewAppEntityCodeName(targetRedirectView) as string)?.toLowerCase());
                await redirectUIService.loaded();
                const redirectAppEntity: IPSAppDataEntity | null = targetRedirectView.getPSAppDataEntity();
                await ViewTool.calcRedirectContext(tempContext, fullargs[0], redirectAppEntity);
                redirectUIService.getRDAppView(
                        tempContext,
                        args[0][this.appDeCodeName.toLowerCase()],
                        params,
                    )
                    .then(async (result: any) => {
                        if (!result) {
                            return;
                        }
                        let targetOpenViewRef:
                            | IPSAppViewRef
                            | undefined = targetRedirectView.getRedirectPSAppViewRefs()?.find((item: IPSAppViewRef) => {
                            return item.name === result.param.split(':')[0];
                        });
                        if (!targetOpenViewRef) {
                            return;
                        }
                        if (
                            targetOpenViewRef.getPSNavigateContexts() &&
                            (targetOpenViewRef.getPSNavigateContexts() as IPSNavigateContext[]).length > 0
                        ) {
                            let localContextRef: any = Util.formatNavParam(
                                targetOpenViewRef.getPSNavigateContexts(),
                                true,
                            );
                            let _context: any = Util.computedNavData(fullargs[0], tempContext, data, localContextRef);
                            Object.assign(tempContext, _context);
                        }
                        if (result && result.hasOwnProperty('srfsandboxtag')) {
                            Object.assign(tempContext, { 'srfsandboxtag': result['srfsandboxtag'] });
                            Object.assign(data, { 'srfsandboxtag': result['srfsandboxtag'] });
                        }
                        let targetOpenView: IPSAppView | null = targetOpenViewRef.getRefPSAppView();
                        if (!targetOpenView) {
                            return;
                        }
                        await targetOpenView.fill();
                        const view: any = {
                            viewname: Util.srfFilePath2(targetOpenView.codeName),
                            height: targetOpenView.height,
                            width: targetOpenView.width,
                            title: this.$tl(targetOpenView.getCapPSLanguageRes()?.lanResTag, targetOpenView.caption),
                        };
                        if (!targetOpenView.openMode || targetOpenView.openMode == 'INDEXVIEWTAB') {
                            if (targetOpenView.getPSAppDataEntity()) {
                                deResParameters = Util.formatAppDERSPath(
                                    tempContext,
                                    (targetOpenView as IPSAppDEView).getPSAppDERSPaths(),
                                );
                                parameters = [
                                    {
                                        pathName: Util.srfpluralize(
                                            (targetOpenView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName,
                                        ).toLowerCase(),
                                        parameterName: (targetOpenView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName.toLowerCase(),
                                    },
                                    {
                                        pathName: 'views',
                                        parameterName: ((targetOpenView as IPSAppDEView).getPSDEViewCodeName() as string).toLowerCase(),
                                    },
                                ];
                            } else {
                                parameters = [
                                    {
                                        pathName: targetOpenView.codeName.toLowerCase(),
                                        parameterName: targetOpenView.codeName.toLowerCase(),
                                    },
                                ];
                            }
                        } else {
                            if (targetOpenView.getPSAppDataEntity()) {
                                parameters = [
                                    {
                                        pathName: Util.srfpluralize(
                                            (targetOpenView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName,
                                        ).toLowerCase(),
                                        parameterName: (targetOpenView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName.toLowerCase(),
                                    },
                                ];
                            }
                            if (targetOpenView && targetOpenView.modelPath) {
                                Object.assign(tempContext, { viewpath: targetOpenView.modelPath });
                            }
                        }
                        this.openTargtView(targetOpenView, view, tempContext, data, xData, $event, deResParameters, parameters, args, callback);
                    });
            } else {
                if (fullargs && fullargs.copymode) {
                    Object.assign(data, { copymode: true });
                }
                let view: any = {
                    viewname: 'app-view-shell',
                    height: openView.height,
                    width: openView.width,
                    title: this.$tl(openView.getCapPSLanguageRes()?.lanResTag, openView.caption),
                };
                this.openTargtView(openView, view, tempContext, data, xData, $event, deResParameters, parameters, args, callback);
            }
        } else {
            this.$warning(this.$t('app.nosupport.unassign'),'ganttOpendata');
        }
    }

    /**
     * 监听语言变化
     *
     * @public
     * @memberof GanttControlBase
     */
    @Watch('$i18n.locale')
    public onLocaleChange(newval: any, val: any) {
        if(newval != val){
            this.locale = newval;
            this.updateOptions();
        }
    }

    /**
     * 打开目标视图
     *
     * @param {*} openView 目标视图模型对象
     * @param {*} view 视图对象
     * @param {*} tempContext 临时上下文
     * @param {*} data 数据
     * @param {*} xData 数据部件实例
     * @param {*} $event 事件源
     * @param {*} deResParameters 
     * @param {*} parameters
     * @param {*} args 额外参数
     * @param {Function} callback 回调
     * @memberof GanttControlBase
     */
    public openTargtView(openView: any, view: any, tempContext: any, data: any, xData: any, $event: any, deResParameters: any, parameters: any, args: any, callback: Function) {
        const _this: any = this;
        if (!openView?.openMode || openView.openMode == 'INDEXVIEWTAB') {
            if (tempContext.srfdynainstid) {
                Object.assign(data, { srfdynainstid: tempContext.srfdynainstid });
            }
            const routePath = ViewTool.buildUpRoutePath(_this.$route, tempContext, deResParameters, parameters, args, data);
            _this.$router.push(routePath);
        } else if (openView.openMode == 'POPUPAPP') {
            const routePath = ViewTool.buildUpRoutePath(_this.$route, tempContext, deResParameters, parameters, args, data);
            window.open('./#' + routePath, '_blank');
        } else if (openView.openMode == 'POPUPMODAL') {
            // 打开模态
            let container: Subject<any> = _this.$appmodal.openModal(view, tempContext, data);
            container.subscribe((result: any) => {
                if (!result || !Object.is(result.ret, 'OK')) {
                    return;
                }
                callback(result, xData);
            });
        } else if (openView.openMode.indexOf('DRAWER') !== -1) {
            // 打开抽屉
            if (Object.is(openView.openMode, 'DRAWER_TOP')) {
                Object.assign(view, { isfullscreen: true });
                let container: Subject<any> = _this.$appdrawer.openTopDrawer(
                    view,
                    Util.getViewProps(tempContext, data),
                );
                container.subscribe((result: any) => {
                    callback(result, xData);
                });
            } else {
                Object.assign(view, { placement: openView.openMode });
                let container: Subject<any> = _this.$appdrawer.openDrawer(view, Util.getViewProps(tempContext, data));
                container.subscribe((result: any) => {
                    callback(result, xData);
                });
            }
        } else if (openView.openMode == 'POPOVER') {
            // 打开气泡卡片
            Object.assign(view, { placement: openView.openMode });
            let container: Subject<any> = _this.$apppopover.openPop($event, view, tempContext, data);
            container.subscribe((result: any) => {
                callback(result, xData);
            });
        } else {
            this.$warning(`${openView.title}`+this.$t('app.nosupport.unopen'),'openTargtView');
        }
    }
}