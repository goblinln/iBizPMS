import { Subject } from 'rxjs';
import { GlobalService } from 'ibiz-service';
import { IBizAppUILogicModel, IBizToolBarItemModel, Util, ViewFactory, ViewTool } from 'ibiz-core';
import { AppViewLogicService } from '../app-service/logic-service/app-viewlogic-service';
import { ViewBase } from './view-base';


/**
 * 主数据视图基类
 *
 * @export
 * @class MainViewBase
 * @extends {ViewBase}
 */
export class MainViewBase extends ViewBase {

    /**
     * 视图引擎
     *
     * @public
     * @type {*}
     * @memberof MainViewBase
     */
    public engine!: any;

    /**
     * 引擎初始化
     *
     * @param {*} [opts={}] 引擎参数
     * @memberof MainViewBase
     */
    public engineInit(opts: any = {}): void { }

    /**
     * 视图初始化
     *
     * @memberof MainViewBase
     */
    public viewInit() {
        super.viewInit();
        this.opendata = this.opendata.bind(this);
        this.newdata = this.newdata.bind(this);
    }

    /**
     * 工具栏模型数据
     *
     * @protected
     * @type {*}
     * @memberof MainViewBase
     */
    protected toolbarModels: any;

    /**
     * 初始化工具栏数据
     * 
     * @memberof MainViewBase
     */
    public initViewToolBar() {
        let targetViewToolbarItems: any[] = [];
        const initToolBarItems = (item: IBizToolBarItemModel) => {
            if (item.items?.length > 0) {
                let models: Array<any> = [];
                let tempModel: any = { name: item.name, showCaption: item.showCaption, caption: item.caption, disabled: false, visabled: true, itemType: item.itemType, dataaccaction: ''  };
                item.items.forEach((_item: any) => {
                    models.push(initToolBarItems(_item));
                })
                Object.assign(tempModel, {
                    model: models
                });
                return tempModel;
            }
            let gridInstance: any = this.viewInstance.getControl("grid");
            let tempItem: any = { name: item.name, showCaption: item.showCaption, showIcon: item.showIcon, tooltip: item.tooltip, iconcls: item.iconcls, icon: item.icon, actiontarget: item.uIActionTarget, caption: item.caption, disabled: false, itemType: item.itemType, visabled: true, noprivdisplaymode: item.noPrivDisplayMode, dataaccaction: item?.getPSUIAction?.dataAccessAction, uiaction: item.getPSUIAction };
            if (item.getPSUIAction && item.getPSUIAction.uIActionTag && Object.is(item.getPSUIAction.uIActionTag, "ExportExcel") && gridInstance) {
                Object.assign(tempItem, {
                    MaxRowCount: gridInstance.dataExport && gridInstance.dataExport.maxRowCount ? gridInstance.dataExport.maxRowCount : 1000
                });
            }
            return tempItem;
        }
        if (this.viewInstance && this.viewInstance.viewToolBarItems) {
            this.viewInstance.viewToolBarItems.forEach((item: IBizToolBarItemModel) => {
                targetViewToolbarItems.push(initToolBarItems(item));
            })
        }
        this.toolbarModels = targetViewToolbarItems;
    }

    /**
     * 视图模型数据初始化实例
     *
     * @memberof MainViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        if (!(this.Environment && this.Environment.isPreviewMode)) {
            this.appEntityService = await new GlobalService().getService(this.viewInstance?.appDeCodeName)
        }
        this.initViewToolBar();
    }

    /**
     * 渲染视图头部
     * 
     * @memberof MainViewBase
     */
    public renderViewHeader() {
        return [
            this.viewInstance?.showCaptionBar ? <span class='caption-info'>{this.viewInstance?.title}</span> : null
        ]
    }

    /**
     * 渲染视图工具栏
     * 
     * @memberof MainViewBase
     */
    public renderToolBar() {
        if (!(this.toolbarModels && this.toolbarModels.length > 0)) {
            return null;
        }
        return (<view-toolbar slot='toolbar' mode={this.viewInstance?.viewStyle || "DEFAULT"} 
        counterServiceArray={this.counterServiceArray} isViewLoading={this.viewLoadingService?.isLoading} toolbarModels={this.toolbarModels} on-item-click={(data: any, $event: any) => { this.handleItemClick(data, $event) }}></view-toolbar>);
    }

    /**
     * 渲染主信息标题
     * 
     * @memberof MainViewBase
     */
    public renderCaptionInfo() {
        return this.$createElement('span', {
            slot: 'captionInfo', domProps: {
                innerHTML: this.model.dataInfo || this.viewInstance?.caption
            }
        });
    }

    /**
     * 工具栏点击
     * 
     * @param ctrl 部件 
     * @param action  行为
     * @param data 数据
     * @param $event 事件源对象
     * 
     * @memberof MainViewBase
     */
    public handleItemClick(data: any, $event: any) {
        AppViewLogicService.getInstance().executeViewLogic(`toolbar_${data.tag}_click`, $event, this, undefined, this.viewInstance?.getPSAppViewLogics);
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {*} controlInstance
     * @returns
     * @memberof MainViewBase
     */
    public computeTargetCtrlData(controlInstance: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        if (controlInstance?.controlType != 'SEARCHFORM') {
            Object.assign(targetCtrlParam.staticProps, {
                opendata: this.opendata,
                newdata: this.newdata
            });
        }
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }

    /**
     * 打开目标视图
     *
     * @memberof MainViewBase
     */
    public openTargtView(openView: any, view: any, tempContext: any, data: any, xData: any, $event: any, deResParameters: any, parameters: any, args: any, callback: Function) {
        const _this: any = this;
        if (!openView?.openMode || openView.openMode == 'INDEXVIEWTAB') {
            if (tempContext.srfdynainstid) {
                Object.assign(data, { srfdynainstid: tempContext.srfdynainstid });
            }
            const routePath = ViewTool.buildUpRoutePath(_this.$route, tempContext, deResParameters, parameters, args, data);
            _this.$router.push(routePath);
        } else if (openView.openMode == "POPUPAPP") {
            const routePath = ViewTool.buildUpRoutePath(_this.$route, tempContext, deResParameters, parameters, args, data);
            window.open('./#' + routePath, '_blank');
        } else if (openView.openMode == "POPUPMODAL") {
            // 打开模态
            let container: Subject<any> = _this.$appmodal.openModal(view, tempContext, data);
            container.subscribe((result: any) => {
                callback(result, xData);
            });
        } else if (openView.openMode.indexOf('DRAWER') !== -1) {
            // 打开抽屉
            if (Object.is(openView.openMode, 'DRAWER_TOP')) {
                Object.assign(view, { isfullscreen: true });
                let container: Subject<any> = _this.$appdrawer.openTopDrawer(view, Util.getViewProps(tempContext, data));
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
        } else if (openView.openMode == "POPOVER") {
            // 打开气泡卡片
            Object.assign(view, { placement: openView.openMode });
            let container: Subject<any> = _this.$apppopover.openPop($event, view, tempContext, data);
            container.subscribe((result: any) => {
                callback(result, xData);
            });
        } else {
            this.$Notice.warning({ title: '错误', desc: openView.title + '不支持该模式打开' });
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
     * @memberof MainViewBase
     */
    public async opendata(args: any[], fullargs?: any, params?: any, $event?: any, xData?: any) {
        if (!this.viewInstance.viewOpenAppUIlogic) {
            this.$Notice.warning({ title: '错误', desc: '编辑应用界面逻辑不存在' });
            return;
        }
        const viewOpenAppUIlogic: IBizAppUILogicModel = this.viewInstance?.viewOpenAppUIlogic;
        await viewOpenAppUIlogic.loaded();
        if (viewOpenAppUIlogic && viewOpenAppUIlogic?.getParentContainer?.viewType) {
            // todo
        }
        if (viewOpenAppUIlogic?.getOpenDataPSAppView) {
            const openView: any = viewOpenAppUIlogic.getOpenDataPSAppView;
            await openView.loaded();
            const data: any = {};
            let tempContext = JSON.parse(JSON.stringify(this.context));
            // 准备参数
            if (args.length > 0) {
                Object.assign(tempContext, args[0]);
            }
            if (openView?.getPSAppViewNavContexts && openView?.getPSAppViewNavContexts?.length > 0) {
                const localContext = Util.formatNavParam(openView.getPSAppViewNavContexts);
                let _context: any = Util.computedNavData(fullargs[0], this.context, this.viewparams, localContext);
                Object.assign(tempContext, _context);
            }
            if (openView?.getPSAppViewNavParams && openView.getPSAppViewNavParams?.length > 0) {
                const localViewParam = Util.formatNavParam(openView.getPSAppViewNavParams);
                let _param: any = Util.computedNavData(fullargs[0], this.context, this.viewparams, localViewParam);
                Object.assign(data, _param);
            }
            if (fullargs && (fullargs.length > 0) && fullargs[0]['srfprocessdefinitionkey'] && fullargs[0]['srftaskdefinitionkey']) {
                Object.assign(data, { 'processDefinitionKey': fullargs[0]['srfprocessdefinitionkey'] });
                Object.assign(data, { 'taskDefinitionKey': fullargs[0]['srftaskdefinitionkey'] });
            }
            let deResParameters: any[] = [];
            let parameters: any[] = [];
            // 实体视图
            if (openView?.appDataEntity) {
                // 处理视图关系参数 （只是路由打开模式才计算）
                if (!openView.openMode || openView.openMode == 'INDEXVIEWTAB' || openView.openMode == 'POPUPAPP') {
                    await openView.loadedAppDERSPathParam();
                    deResParameters = Util.formatAppDERSPath(tempContext, openView.getPSAppDERSPaths);
                }
            }
            if (!openView?.openMode || openView.openMode == 'INDEXVIEWTAB') {
                if (openView.appDataEntity) {
                    parameters = [
                        { pathName: Util.srfpluralize(openView.appDataEntity.codeName).toLowerCase(), parameterName: openView.appDataEntity.codeName.toLowerCase() },
                        { pathName: "views", parameterName: openView.getPSDEViewCodeName.toLowerCase() },
                    ];
                } else {
                    parameters = [
                        { pathName: "views", parameterName: openView.name.toLowerCase() }
                    ];
                }
            } else {
                if (openView?.appDataEntity) {
                    parameters = [{ pathName: Util.srfpluralize(openView.appDataEntity.codeName)?.toLowerCase(), parameterName: openView.appDataEntity.codeName?.toLowerCase() }];
                }
                if (openView && openView.dynaModelFilePath) {
                    Object.assign(tempContext, { viewpath: openView.dynaModelFilePath });
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
            }
            // 重定向视图
            if (openView?.redirectView) {
                if (openView.getRedirectPSAppViewRefs && (openView.getRedirectPSAppViewRefs?.length === 0)) {
                    return;
                }
                this.appUIService.getRDAppView(args[0][this.viewInstance?.appDeCodeName?.toLowerCase()], openView.enableWorkflow).then(async (result: any) => {
                    if (!result) {
                        return;
                    }
                    let targetOpenViewRef: any = openView.getRedirectPSAppViewRefs.find((item: any) => {
                        return item.name === result;
                    })
                    if (targetOpenViewRef && targetOpenViewRef.getPSNavigateContexts && targetOpenViewRef.getPSNavigateContexts.length > 0) {
                        let localContextRef: any = Util.formatNavParam(targetOpenViewRef.getPSNavigateContexts, true);
                        let _context: any = Util.computedNavData(fullargs[0], tempContext, data, localContextRef);
                        Object.assign(tempContext, _context);
                    }
                    let targetOpenView: any = await viewOpenAppUIlogic.loadedTargetAppView(this.context, targetOpenViewRef.getRefPSAppView);
                    targetOpenView = ViewFactory.getInstance(targetOpenView, this.context);
                    await targetOpenView.loaded()
                    const view: any = {
                        viewname: Util.srfFilePath2(targetOpenView.codeName),
                        height: targetOpenView.height,
                        width: targetOpenView.width,
                        title: targetOpenView.title
                    };
                    if (!targetOpenView.openMode || targetOpenView.openMode == 'INDEXVIEWTAB') {
                        if (targetOpenView.appDataEntity) {
                            parameters = [
                                { pathName: Util.srfpluralize(targetOpenView.appDataEntity.codeName).toLowerCase(), parameterName: targetOpenView.appDataEntity.codeName.toLowerCase() },
                                { pathName: "views", parameterName: targetOpenView.getPSDEViewCodeName.toLowerCase() },
                            ];
                        } else {
                            parameters = [
                                { pathName: targetOpenView.codeName.toLowerCase(), parameterName: targetOpenView.codeName.toLowerCase() }
                            ];
                        }
                    } else {
                        if (targetOpenView.appDataEntity) {
                            parameters = [{ pathName: Util.srfpluralize(targetOpenView.appDataEntity.codeName).toLowerCase(), parameterName: targetOpenView.appDataEntity.codeName.toLowerCase() }];
                        }
                        if (openView && openView.dynaModelFilePath) {
                            Object.assign(tempContext, { viewpath: openView.dynaModelFilePath });
                        }
                    }
                    this.openTargtView(targetOpenView, view, tempContext, data, xData, $event, deResParameters, parameters, args, callback);
                })
            } else {
                if (fullargs && fullargs.copymode) {
                    Object.assign(data, { copymode: true });
                }
                let view: any = {
                    viewname: 'app-view-shell',
                    height: openView.height,
                    width: openView.width,
                    title: openView.title
                };
                this.openTargtView(openView, view, tempContext, data, xData, $event, deResParameters, parameters, args, callback);
            }
        } else {
            this.$Notice.warning({ title: '错误', desc: '未指定关系视图' });
        }
    }

    /**
     * 打开新建数据视图
     *
     * @param {any[]} args 数据参数
     * @param {*} [fullargs] 全量参数
     * @param {*} [params]  额外参数
     * @param {*} [$event] 事件源数据
     * @param {*} [xData] 数据部件
     * @memberof MainViewBase
     */
    public async newdata(args: any[], fullargs?: any, params?: any, $event?: any, xData?: any) {
        if (!this.viewInstance.viewNewAppUIlogic) {
            this.$Notice.warning({ title: '错误', desc: '新建应用界面逻辑不存在' });
            return;
        }
        const viewNewAppUIlogic: IBizAppUILogicModel = this.viewInstance?.viewNewAppUIlogic;
        await viewNewAppUIlogic.loaded();
        // todo 应用视图参数
        if (viewNewAppUIlogic) {
            if (viewNewAppUIlogic.enableWizardAdd) {
                await viewNewAppUIlogic.loadedWizardPSAppView();
                const wizardPSAppView: any = viewNewAppUIlogic.getWizardPSAppView;
                const view: any = {
                    viewname: 'app-view-shell',
                    height: wizardPSAppView.height,
                    width: wizardPSAppView.width,
                    title: wizardPSAppView.title
                };
                const tempContext: any = JSON.parse(JSON.stringify(this.context));
                if (wizardPSAppView && wizardPSAppView.dynaModelFilePath) {
                    Object.assign(tempContext, { viewpath: wizardPSAppView.dynaModelFilePath });
                }
                let container: Subject<any> = this.$appmodal.openModal(view, tempContext, args[0]);
                container.subscribe(async (result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    if (result && result.datas && result.datas.length > 0) {
                        const newDataAppViews: Array<any> = viewNewAppUIlogic.getNewDataPSAppViews;
                        const targetNewDataAppViewRef: any = newDataAppViews.find((item: any) => {
                            return item.refMode === result.datas[0].srfkey;
                        });
                        Object.assign(tempContext, Util.formatNavParam(targetNewDataAppViewRef.getPSNavigateContexts));
                        const targetNewDataAppView: any = await viewNewAppUIlogic.loadedTargetAppView(tempContext, targetNewDataAppViewRef.getRefPSAppView);
                        const view: any = {
                            viewname: 'app-view-shell',
                            height: targetNewDataAppView.height,
                            width: targetNewDataAppView.width,
                            title: targetNewDataAppView.title
                        };
                        if (targetNewDataAppView && targetNewDataAppView.dynaModelFilePath) {
                            Object.assign(tempContext, { viewpath: targetNewDataAppView.dynaModelFilePath });
                        }
                        let container: Subject<any> = this.$appmodal.openModal(view, tempContext, args[0]);
                        container.subscribe((result: any) => {
                            if (!result || !Object.is(result.ret, 'OK')) {
                                return;
                            }
                            if (result && result.datas && result.datas.length > 0) {
                                if (!xData || !(xData.refresh instanceof Function)) {
                                    return;
                                }
                                xData.refresh(result.datas);
                            }
                        })
                    }
                });
            } else if (viewNewAppUIlogic.enableBatchAdd) {
                let batchAddPSAppViews: Array<any> = [];
                if (viewNewAppUIlogic.getBatchAddPSAppViews && viewNewAppUIlogic.getBatchAddPSAppViews.length > 0) {
                    batchAddPSAppViews = viewNewAppUIlogic.getBatchAddPSAppViews;
                }
                if (batchAddPSAppViews.length == 0 || !this.context.srfparentdename) {
                    this.$Notice.warning({ title: '错误', desc: '批量添加需添加N:N关系' });
                    return;
                }
                let openViewModel: any = batchAddPSAppViews.find((item: any) => {
                    return (item.refMode && (item.refMode !== this.context.srfparentdename));
                })
                let otherViewModel: any = batchAddPSAppViews.find((item: any) => {
                    return (item.refMode && (item.refMode == this.context.srfparentdename));
                })
                await viewNewAppUIlogic.loadedBatchAddPSAppViews();
                let view: any = {
                    viewname: 'app-view-shell',
                    height: openViewModel.view.height,
                    width: openViewModel.view.width,
                    title: openViewModel.view.title
                };
                let tempContext:any = Util.deepCopy(this.context)
                if (openViewModel && openViewModel.dynaModelFilePath) {
                    Object.assign(tempContext, { viewpath: openViewModel.dynaModelFilePath });
                }
                let container: Subject<any> = this.$appmodal.openModal(view, tempContext, args[0]);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    if (result.datas && result.datas.length == 0) {
                        return;
                    }
                    let requestParam: Array<any> = [];
                    result.datas.forEach((record: any) => {
                        let tempParam: any = {};
                        tempParam[otherViewModel?.view?.appDataEntity?.keyField?.codeName?.toLowerCase()] = this.context['srfparentkey'];
                        tempParam[openViewModel?.view?.appDataEntity?.keyField?.codeName?.toLowerCase()] = record.srfkey;
                        requestParam.push(tempParam);
                    });
                    this.appEntityService.createBatch(JSON.parse(JSON.stringify(this.context)), requestParam, true).then((response: any) => {
                        if (!response || response.status !== 200) {
                            this.$Notice.error({ title: '错误', desc: '批处理操作失败' });
                            return;
                        }
                        if (!xData || !(xData.refresh instanceof Function)) {
                            return;
                        }
                        xData.refresh(result.datas);
                    });
                });
            } else if (viewNewAppUIlogic.batchAddOnly) {
                console.warn("只支持批添加未实现");
            } else if (viewNewAppUIlogic.getNewDataPSAppView) {
                const _this: any = this;
                const dataview: any = viewNewAppUIlogic.getNewDataPSAppView;
                await dataview.loaded();
                const data: any = {};
                if (args[0].srfsourcekey) {
                    data.srfsourcekey = args[0].srfsourcekey;
                }
                if (fullargs && (fullargs as any).copymode) {
                    Object.assign(data, { copymode: (fullargs as any).copymode });
                }
                let tempContext = JSON.parse(JSON.stringify(this.context));
                if (dataview.appDataEntity && tempContext[dataview.appDataEntity.codeName.toLowerCase()]) {
                    delete tempContext[dataview.appDataEntity.codeName.toLowerCase()];
                }
                if (args.length > 0) {
                    Object.assign(tempContext, args[0]);
                }
                if (dataview.getPSAppViewNavContexts && dataview.getPSAppViewNavContexts.length > 0) {
                    const localContext = Util.formatNavParam(dataview.getPSAppViewNavContexts);
                    let _context: any = Util.computedNavData(fullargs[0], this.context, this.viewparams, localContext);
                    Object.assign(tempContext, _context);
                }
                if (dataview.getPSAppViewNavParams && dataview.getPSAppViewNavParams.length > 0) {
                    const localViewParam = Util.formatNavParam(dataview.getPSAppViewNavParams);
                    let _param: any = Util.computedNavData(fullargs[0], this.context, this.viewparams, localViewParam);
                    Object.assign(data, _param);
                }
                let deResParameters: any[] = [];
                let parameters: any[] = [];
                if (dataview.appDataEntity) {
                    // 处理视图关系参数 （只是路由打开模式才计算）
                    if (!dataview.openMode || dataview.openMode == 'INDEXVIEWTAB' || dataview.openMode == 'POPUPAPP') {
                        await dataview.loadedAppDERSPathParam();
                        deResParameters = Util.formatAppDERSPath(tempContext, dataview.getPSAppDERSPaths);
                    }
                }
                if (!dataview.openMode || dataview.openMode == 'INDEXVIEWTAB') {
                    if (dataview.appDataEntity) {
                        parameters = [
                            { pathName: Util.srfpluralize(dataview.appDataEntity.codeName).toLowerCase(), parameterName: dataview.appDataEntity.codeName.toLowerCase() },
                            { pathName: "views", parameterName: dataview.getPSDEViewCodeName.toLowerCase() },
                        ];
                    } else {
                        parameters = [
                            { pathName: "views", parameterName: dataview.codeName.toLowerCase() },
                        ];
                    }
                } else {
                    if (dataview.appDataEntity) {
                        parameters = [
                            { pathName: Util.srfpluralize(dataview.appDataEntity.codeName).toLowerCase(), parameterName: dataview.appDataEntity.codeName.toLowerCase() },
                        ];
                    }
                    if (dataview && dataview.dynaModelFilePath) {
                        Object.assign(tempContext, { viewpath: dataview.dynaModelFilePath });
                    }
                }
                let view: any = {
                    viewname: 'app-view-shell',
                    height: dataview.height,
                    width: dataview.width,
                    title: dataview.title
                };
                // 关闭视图回调
                let callback: Function = (result: any, xData: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    if (!xData || !(xData.refresh instanceof Function)) {
                        return;
                    }
                    xData.refresh(result.datas);
                }
                if (!dataview.openMode || dataview.openMode == 'INDEXVIEWTAB') {
                    // 打开顶级分页视图
                    const _data: any = { w: (new Date().getTime()) };
                    Object.assign(_data, data);
                    if (tempContext.srfdynainstid) {
                        Object.assign(_data, { srfdynainstid: tempContext.srfdynainstid });
                    }
                    const routePath = ViewTool.buildUpRoutePath(_this.$route, tempContext, deResParameters, parameters, args, _data);
                    _this.$router.push(routePath);
                } else if (dataview.openMode == 'POPUPAPP') {
                    // 独立程序打开
                    const routePath = ViewTool.buildUpRoutePath(_this.$route, tempContext, deResParameters, parameters, args, data);
                    window.open('./#' + routePath, '_blank');
                } else if (dataview.openMode == 'POPUPMODAL') {
                    // 打开模态
                    let container: Subject<any> = _this.$appmodal.openModal(view, tempContext, data);
                    container.subscribe((result: any) => {
                        callback(result, xData);
                    });
                } else if (dataview.openMode.indexOf('DRAWER') !== -1) {
                    // 打开抽屉
                    if (Object.is(dataview.openMode, 'DRAWER_TOP')) {
                        Object.assign(view, { isfullscreen: true });
                        let container: Subject<any> = _this.$appdrawer.openTopDrawer(view, Util.getViewProps(tempContext, data));
                        container.subscribe((result: any) => {
                            callback(result, xData);
                        });
                    } else {
                        Object.assign(view, { placement: dataview.openMode });
                        let container: Subject<any> = _this.$appdrawer.openDrawer(view, Util.getViewProps(tempContext, data));
                        container.subscribe((result: any) => {
                            callback(result, xData);
                        });
                    }
                } else if (dataview.openMode == 'POPOVER') {
                    // 打开气泡卡片
                    Object.assign(view, { placement: dataview.openMode });
                    let container: Subject<any> = _this.$apppopover.openPop($event, view, tempContext, data);
                    container.subscribe((result: any) => {
                        callback(result, xData);
                    });
                } else {
                    this.$Notice.warning({ title: '错误', desc: `${dataview.title}不支持该模式打开` });
                }
            }
        } else {
            this.$Notice.warning({ title: '错误', desc: '未指定关系视图' });
        }
    }

}
