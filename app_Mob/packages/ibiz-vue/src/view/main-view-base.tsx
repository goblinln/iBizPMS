import { Subject } from 'rxjs';
import { ModelTool, Util, ViewTool } from 'ibiz-core';
import { AppViewLogicService } from '../app-service/logic-service/app-viewlogic-service';
import { ViewBase } from './view-base';
import { GlobalService, UIServiceRegister } from 'ibiz-service';
import { IPSAppDataEntity, IPSAppDEField, IPSAppDERedirectView, IPSAppDEView, IPSAppUILogicRefView, IPSAppUINewDataLogic, IPSAppUIOpenDataLogic, IPSAppView, IPSAppViewLogic, IPSAppViewNavContext, IPSAppViewNavParam, IPSAppViewRef, IPSDEToolbar, IPSNavigateContext, IPSNavigateParam } from '@ibiz/dynamic-model-api';

/**
 * 编辑视图基类
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
     * 视图实例
     * 
     * @memberof MainViewBase
     */
    public viewInstance!: IPSAppDEView;

    /**
     * 应用实体codeName
     *
     * @readonly
     * @memberof MainViewBase
     */
    get appDeCodeName() {
        return ModelTool.getViewAppEntityCodeName(this.viewInstance);
    }

    /**
     * 应用实体主键属性codeName
     *
     * @readonly
     * @memberof MainViewBase
     */
    get appDeKeyFieldName() {
        return (ModelTool.getAppEntityKeyField(this.viewInstance?.getPSAppDataEntity() as IPSAppDataEntity) as IPSAppDEField)?.codeName || '';
    }

    /**
     * 应用实体主信息属性codeName
     *
     * @readonly
     * @memberof MainViewBase
     */
    get appDeMajorFieldName() {
        return (ModelTool.getAppEntityMajorField(this.viewInstance?.getPSAppDataEntity() as IPSAppDataEntity) as IPSAppDEField)?.codeName || '';
    }

    /**
     * 引擎初始化
     *
     * @param {*} [opts={}] 引擎参数
     * @memberof MainViewBase
     */
    public engineInit(opts: any = {}): void {
        if (this.Environment?.isPreviewMode) {
            return;
        }
        if (this.engine) {
            let engineOpts = Object.assign({
                view: this,
                parentContainer: this.$parent,
                p2k: '0',
                isLoadDefault: this.staticProps.hasOwnProperty('isLoadDefault') ? this.staticProps.isLoadDefault : true,
                keyPSDEField: this.appDeCodeName.toLowerCase(),
                majorPSDEField: this.appDeMajorFieldName.toLowerCase(),
            }, opts)
            this.engine.init(engineOpts);
            this.$emit('view-event', { viewName: this.viewCodeName, action: 'viewLoaded', data: null });
        }
    }

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
     * 视图模型数据初始化实例
     *
     * @memberof MainViewBase
     */
    public async viewModelInit() {
        await super.viewModelInit();
        if (!(this.Environment?.isPreviewMode)) {
            this.appEntityService = await new GlobalService().getService(ModelTool.getViewAppEntityCodeName(this.viewInstance) as string);
        }
        this.initViewToolBar();
    }

    /**
     * 工具栏模型数据
     *
     * @protected
     * @type {*}
     * @memberof MainViewBase
     */
    protected toolbarModels: any = [];



    /**
     * 初始化工具栏数据
     * 
     * @memberof MainViewBase
     */
    public initViewToolBar() {
        const parseToolModel = (model: any) => {
            let targetViewToolbarItems: any[] = [];
            model.forEach((item: any) => {
                if (item.itemType == 'ITEMS' && item.getPSDEToolbarItems()?.length > 0) {
                    targetViewToolbarItems.push(...parseToolModel(item.getPSDEToolbarItems()));
                } else {
                    targetViewToolbarItems.push(initToolBarItems(item, viewToolBar.name, viewToolBar.toolbarStyle))
                }
            })
            return targetViewToolbarItems
        }
        const viewToolBar: IPSDEToolbar = ModelTool.findPSControlByType("TOOLBAR", this.viewInstance.getPSControls());
        const initToolBarItems = (item: any, toolbarName: string, toolbarStyle: string): any => {
            const uiaction = item.getPSUIAction?.();
            let tempModel: any = { name: item.name, toolbarName: toolbarName, toolbarStyle: toolbarStyle, showCaption: item.showCaption, caption: item.caption, disabled: false, visabled: true, itemType: item.itemType, dataaccaction: uiaction?.dataAccessAction, noprivdisplaymode: item.noPrivDisplayMode, showIcon: item.showIcon, getPSSysImage: { cssClass: item.getPSSysImage()?.cssClass, imagePath: item.getPSSysImage()?.imagePath }, uiaction: uiaction };
            return tempModel;
        }
        if (viewToolBar && viewToolBar.getPSDEToolbarItems()) {
            this.toolbarModels = parseToolModel(viewToolBar.getPSDEToolbarItems());
        }
    }

    /**
     * 渲染视图头部
     * 
     * @memberof MainViewBase
     */
    public renderViewHeader() {
        return [
            this.viewInstance.showCaptionBar ? <span class='caption-info'>{this.viewInstance.title}</span> : null
        ]
    }

    /**
     * 初始化导航栏标题
     *
     * @param {*} val
     * @param {boolean} isCreate
     * @returns
     * @memberof ${srfclassname('${view.name}')}Base
     */
    public initNavCaption(val?: any, isCreate?: boolean) {
        this.thirdPartyInit();
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
        if (this.Environment?.isPreviewMode) {
            return;
        }
        AppViewLogicService.getInstance().executeViewLogic(`${data?.item?.toolbarName}_${data.tag}_click`, $event, this, undefined, this.viewInstance.getPSAppViewLogics());
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
        if (controlInstance.controlType != 'SEARCHFORM') {
            Object.assign(targetCtrlParam.staticProps, {
                opendata: this.opendata,
                newdata: this.newdata
            });
        }
        return { targetCtrlName: targetCtrlName, targetCtrlParam: targetCtrlParam, targetCtrlEvent: targetCtrlEvent };
    }

    /**
     * 初始化应用界面服务
     *
     * @memberof ViewBase
     */
    public async initAppUIService() {
        if (this.appDeCodeName) {
            this.appUIService = await UIServiceRegister.getInstance().getService(this.context, this.appDeCodeName?.toLowerCase());
            if (this.appUIService) {
                await this.appUIService.loaded();
            }
        }
    }

    /**
      * 打开目标视图
      *
      * @memberof MainViewBase
      */
    public openTargtView(openView: any, view: any, tempContext: any, data: any, xData: any, $event: any, deResParameters: any, parameters: any, args: any, callback: Function) {
        const _this: any = this;
        if (!openView.openMode || openView.openMode == 'INDEXVIEWTAB') {
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
            _this.$appmodal.openModal(view, tempContext, data).then((result: any) => {
                callback(result, xData);
            });
        } else if (openView.openMode.indexOf('DRAWER') !== -1) {
            // 打开抽屉
            Object.assign(view, { placement: openView.openMode });
            _this.$appdrawer.openDrawer(view, Util.getViewProps(tempContext, data)).then((result: any) => {
                callback(result, xData);
            });
        } else {
            this.$Notice.warning(openView.title + '不支持该模式打开');
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
        const openAppViewLogic: IPSAppViewLogic | null = this.viewInstance.findPSAppViewLogic("opendata");
        if (!openAppViewLogic || !openAppViewLogic.getPSAppUILogic()) {
            this.$Notice.warning('编辑应用界面逻辑不存在');
            return;
        }
        let viewOpenAppUIlogic: IPSAppUIOpenDataLogic | undefined | null = (openAppViewLogic.getPSAppUILogic() as IPSAppUIOpenDataLogic);
        if (viewOpenAppUIlogic && viewOpenAppUIlogic?.getParentPSModelObject()?.M.viewType) {
            // todo
        }
        if (viewOpenAppUIlogic?.getOpenDataPSAppView()) {
            const openViewRef: IPSAppUILogicRefView = viewOpenAppUIlogic.getOpenDataPSAppView() as IPSAppUILogicRefView;
            const data: any = {};
            let tempContext = JSON.parse(JSON.stringify(this.context));
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
            if (fullargs && (fullargs.length > 0) && fullargs[0]['srfprocessdefinitionkey'] && fullargs[0]['srftaskdefinitionkey']) {
                Object.assign(data, { 'processDefinitionKey': fullargs[0]['srfprocessdefinitionkey'] });
                Object.assign(data, { 'taskDefinitionKey': fullargs[0]['srftaskdefinitionkey'] });
                // 将待办任务标记为已读准备参数
                const that: any = this;
                if (that.quickGroupData && that.quickGroupData.hasOwnProperty("srfwf") && fullargs[0]['srftaskid']) {
                    Object.assign(data, { 'srfwf': that.quickGroupData['srfwf'] });
                    Object.assign(data, { 'srftaskid': fullargs[0]['srftaskid'] });
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
                    deResParameters = Util.formatAppDERSPath(tempContext, (openView as any).getPSAppDERSPaths);
                }
            }
            if (!openView?.openMode || openView.openMode == 'INDEXVIEWTAB') {
                if (openView.getPSAppDataEntity()) {
                    parameters = [
                        { pathName: Util.srfpluralize((openView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName).toLowerCase(), parameterName: (openView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName.toLowerCase() },
                        { pathName: "views", parameterName: ((openView as IPSAppDEView).getPSDEViewCodeName() as string).toLowerCase() },
                    ];
                } else {
                    parameters = [
                        { pathName: "views", parameterName: openView.name?.toLowerCase() }
                    ];
                }
            } else {
                if (openView?.getPSAppDataEntity()) {
                    parameters = [{ pathName: Util.srfpluralize((openView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName)?.toLowerCase(), parameterName: (openView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName?.toLowerCase() }];
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
            }
            // 重定向视图
            if (openView?.redirectView) {
                let targetRedirectView: IPSAppDERedirectView = openView as IPSAppDERedirectView;
                await targetRedirectView.fill();
                if (targetRedirectView.getRedirectPSAppViewRefs() && (targetRedirectView.getRedirectPSAppViewRefs()?.length === 0)) {
                    return;
                }
                this.appUIService.getRDAppView(args[0][(ModelTool.getViewAppEntityCodeName(this.viewInstance) as string)?.toLowerCase()], targetRedirectView.enableWorkflow).then(async (result: any) => {
                    if (!result) {
                        return;
                    }
                    let targetOpenViewRef: IPSAppViewRef | undefined = targetRedirectView.getRedirectPSAppViewRefs()?.find((item: IPSAppViewRef) => {
                        return item.name === result;
                    })
                    if (!targetOpenViewRef) {
                        return;
                    }
                    if (targetOpenViewRef.getPSNavigateContexts() && (targetOpenViewRef.getPSNavigateContexts() as IPSNavigateContext[]).length > 0) {
                        let localContextRef: any = Util.formatNavParam(targetOpenViewRef.getPSNavigateContexts(), true);
                        let _context: any = Util.computedNavData(fullargs[0], tempContext, data, localContextRef);
                        Object.assign(tempContext, _context);
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
                        title: targetOpenView.title
                    };
                    if (!targetOpenView.openMode || targetOpenView.openMode == 'INDEXVIEWTAB') {
                        if (targetOpenView.getPSAppDataEntity()) {
                            parameters = [
                                { pathName: Util.srfpluralize((targetOpenView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName).toLowerCase(), parameterName: (targetOpenView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName.toLowerCase() },
                                { pathName: "views", parameterName: ((targetOpenView as IPSAppDEView).getPSDEViewCodeName() as string).toLowerCase() },
                            ];
                        } else {
                            parameters = [
                                { pathName: targetOpenView.codeName.toLowerCase(), parameterName: targetOpenView.codeName.toLowerCase() }
                            ];
                        }
                    } else {
                        if (targetOpenView.getPSAppDataEntity()) {
                            parameters = [{ pathName: Util.srfpluralize((targetOpenView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName).toLowerCase(), parameterName: (targetOpenView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName.toLowerCase() }];
                        }
                        if (targetRedirectView && targetRedirectView.modelPath) {
                            Object.assign(tempContext, { viewpath: targetRedirectView.modelPath });
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
            // this.$Notice.warning('未指定关系视图');
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
        const newAppViewLogic: IPSAppViewLogic | null = this.viewInstance.findPSAppViewLogic("newdata");
        if (!newAppViewLogic || !newAppViewLogic.getPSAppUILogic()) {
            this.$Notice.warning('新建应用界面逻辑不存在');
            return;
        }
        let viewNewAppUIlogic: IPSAppUINewDataLogic | undefined | null = (newAppViewLogic.getPSAppUILogic() as IPSAppUINewDataLogic);
        if (viewNewAppUIlogic) {
            if (viewNewAppUIlogic.enableWizardAdd) {
                let wizardPSAppView: IPSAppView | null;
                if (viewNewAppUIlogic.getWizardPSAppView()) {
                    wizardPSAppView = (viewNewAppUIlogic.getWizardPSAppView() as IPSAppUILogicRefView).getRefPSAppView();
                    if (!wizardPSAppView) return;
                    await wizardPSAppView.fill();
                    const view: any = {
                        viewname: 'app-view-shell',
                        height: wizardPSAppView.height,
                        width: wizardPSAppView.width,
                        title: wizardPSAppView.title
                    };
                    const tempContext: any = JSON.parse(JSON.stringify(this.context));
                    if (wizardPSAppView && wizardPSAppView.modelPath) {
                        Object.assign(tempContext, { viewpath: wizardPSAppView.modelPath });
                    }
                    let container: Subject<any> = this.$appmodal.openModal(view, tempContext, args[0]);
                    container.subscribe(async (result: any) => {
                        if (!result || !Object.is(result.ret, 'OK')) {
                            return;
                        }
                        if (result && result.datas && result.datas.length > 0) {
                            const newDataAppViews: Array<IPSAppUILogicRefView> | null = (viewNewAppUIlogic as IPSAppUINewDataLogic).getNewDataPSAppViews();
                            if (newDataAppViews) {
                                const targetNewDataAppViewRef: IPSAppUILogicRefView | undefined | null = newDataAppViews.find((item: IPSAppUILogicRefView) => {
                                    return item.refMode === result.datas[0].srfkey;
                                });
                                if (!targetNewDataAppViewRef) return;
                                Object.assign(tempContext, Util.formatNavParam(targetNewDataAppViewRef.getPSNavigateContexts()));
                                const targetNewDataAppView: IPSAppView | null = targetNewDataAppViewRef.getRefPSAppView();
                                if (!targetNewDataAppView) return;
                                await targetNewDataAppView.fill();
                                const view: any = {
                                    viewname: 'app-view-shell',
                                    height: targetNewDataAppView.height,
                                    width: targetNewDataAppView.width,
                                    title: targetNewDataAppView.title
                                };
                                if (targetNewDataAppView && targetNewDataAppView.modelPath) {
                                    Object.assign(tempContext, { viewpath: targetNewDataAppView.modelPath });
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
                        }
                    });
                }
            } else if (viewNewAppUIlogic.enableBatchAdd) {
                let batchAddPSAppViews: Array<IPSAppUILogicRefView> = [];
                if (viewNewAppUIlogic.getBatchAddPSAppViews() && (viewNewAppUIlogic.getBatchAddPSAppViews() as IPSAppUILogicRefView[]).length > 0) {
                    batchAddPSAppViews = viewNewAppUIlogic.getBatchAddPSAppViews() as IPSAppUILogicRefView[];
                }
                if (batchAddPSAppViews.length == 0 || !this.context.srfparentdename) {
                    this.$Notice.warning('批量添加需添加N:N关系');
                    return;
                }
                let openViewModel: IPSAppUILogicRefView | undefined = batchAddPSAppViews.find((item: IPSAppUILogicRefView) => {
                    return (item.refMode && (item.refMode !== this.context.srfparentdename.toUpperCase()));
                })
                let otherViewModel: IPSAppUILogicRefView | undefined = batchAddPSAppViews.find((item: IPSAppUILogicRefView) => {
                    return (item.refMode && (item.refMode == this.context.srfparentdename.toUpperCase()));
                })
                if (!openViewModel || !otherViewModel) {
                    return;
                }
                let openView: IPSAppDEView = openViewModel.getRefPSAppView() as IPSAppDEView;
                await openView.fill();
                let view: any = {
                    viewname: 'app-view-shell',
                    height: openView.height,
                    width: openView.width,
                    title: openView.title
                };
                let tempContext: any = Util.deepCopy(this.context)
                if (openView && openView.modelPath) {
                    Object.assign(tempContext, { viewpath: openView.modelPath });
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
                        tempParam[(ModelTool.getAppEntityKeyField((otherViewModel as IPSAppUILogicRefView).getPSAppDataEntity()) as IPSAppDEField)?.codeName?.toLowerCase()] = this.context['srfparentkey'];
                        tempParam[(ModelTool.getAppEntityKeyField((openViewModel as IPSAppUILogicRefView).getPSAppDataEntity()) as IPSAppDEField)?.codeName?.toLowerCase()] = record.srfkey;
                        requestParam.push(tempParam);
                    });
                    this.appEntityService.createBatch(JSON.parse(JSON.stringify(this.context)), requestParam, true).then((response: any) => {
                        if (!response || response.status !== 200) {
                            this.$Notice.error('批处理操作失败');
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
            } else if (viewNewAppUIlogic.getNewDataPSAppView()) {
                const _this: any = this;
                const newviewRef: IPSAppUILogicRefView | null = viewNewAppUIlogic.getNewDataPSAppView();
                if (!newviewRef) return;
                const data: any = {};
                if (args[0].srfsourcekey) {
                    data.srfsourcekey = args[0].srfsourcekey;
                }
                if (fullargs && (fullargs as any).copymode) {
                    Object.assign(data, { copymode: (fullargs as any).copymode });
                }
                let tempContext = JSON.parse(JSON.stringify(this.context));
                if (args.length > 0) {
                    Object.assign(tempContext, args[0]);
                }
                if (newviewRef.getPSAppViewNavContexts() && (newviewRef.getPSAppViewNavContexts() as IPSAppViewNavContext[]).length > 0) {
                    const localContext = Util.formatNavParam(newviewRef.getPSAppViewNavContexts());
                    let _context: any = Util.computedNavData(fullargs[0], this.context, this.viewparams, localContext);
                    Object.assign(tempContext, _context);
                }
                if (newviewRef.getPSAppViewNavParams() && (newviewRef.getPSAppViewNavParams() as IPSAppViewNavParam[]).length > 0) {
                    const localViewParam = Util.formatNavParam(newviewRef.getPSAppViewNavParams());
                    let _param: any = Util.computedNavData(fullargs[0], this.context, this.viewparams, localViewParam);
                    Object.assign(data, _param);
                }
                let deResParameters: any[] = [];
                let parameters: any[] = [];
                const dataview: IPSAppView | null = newviewRef.getRefPSAppView();
                if (!dataview) return;
                if (dataview.getPSAppDataEntity() && tempContext[(dataview.getPSAppDataEntity() as IPSAppDataEntity)?.codeName.toLowerCase()]) {
                    delete tempContext[(dataview.getPSAppDataEntity() as IPSAppDataEntity)?.codeName.toLowerCase()];
                }
                if (dataview.getPSAppDataEntity()) {
                    // 处理视图关系参数 （只是路由打开模式才计算）
                    if (!dataview.openMode || dataview.openMode == 'INDEXVIEWTAB' || dataview.openMode == 'POPUPAPP') {
                        deResParameters = Util.formatAppDERSPath(tempContext, (dataview.getRefPSAppView() as IPSAppDEView)?.getPSAppDERSPaths());
                    }
                }
                if (!dataview.openMode || dataview.openMode == 'INDEXVIEWTAB') {
                    if (dataview.getPSAppDataEntity()) {
                        parameters = [
                            { pathName: Util.srfpluralize((dataview.getPSAppDataEntity() as IPSAppDataEntity)?.codeName).toLowerCase(), parameterName: ((dataview.getPSAppDataEntity() as IPSAppDataEntity))?.codeName.toLowerCase() },
                            { pathName: "views", parameterName: ((dataview as IPSAppDEView).getPSDEViewCodeName() as string).toLowerCase() },
                        ];
                    } else {
                        parameters = [
                            { pathName: "views", parameterName: dataview?.codeName.toLowerCase() },
                        ];
                    }
                } else {
                    if (dataview.getPSAppDataEntity()) {
                        parameters = [
                            { pathName: Util.srfpluralize((dataview.getPSAppDataEntity() as IPSAppDataEntity)?.codeName).toLowerCase(), parameterName: (dataview.getPSAppDataEntity() as IPSAppDataEntity)?.codeName.toLowerCase() },
                        ];
                    }
                    if (dataview && dataview.modelPath) {
                        Object.assign(tempContext, { viewpath: dataview.modelPath });
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
                    let result: any = await _this.$appmodal.openModal(view, tempContext, data);
                    callback(result, xData);
                } else if (dataview.openMode.indexOf('DRAWER') !== -1) {
                    // 打开抽屉
                    let result: any = await _this.$appdrawer.openDrawer(view, Util.getViewProps(tempContext, data));
                    callback(result, xData);
                } else {
                    this.$Notice.warning(`${dataview.title}不支持该模式打开`);
                }
            }
        } else {
            // this.$Notice.warning('未指定关系视图');
        }
    }
}
