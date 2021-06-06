import {
    getPSUIActionByModelObject,
    IPSAppDataEntity,
    IPSAppDEField,
    IPSAppDERedirectView,
    IPSAppDEUIAction,
    IPSAppDEView,
    IPSAppView,
    IPSAppViewRef,
    IPSNavigateContext,
    IPSNavigateParam,
} from '@ibiz/dynamic-model-api';
import { ModelTool, StringUtil, UIActionTool, Util } from 'ibiz-core';
import { Subject } from 'rxjs';
import { AppGlobalService } from '../app-service';
import { appPopup } from '../utils';

export class AppFrontAction {
    /**
     * 模型数据
     *
     * @memberof AppFrontAction
     */
    private actionModel!: IPSAppDEUIAction;

    /**
     * 初始化AppFrontAction
     *
     * @memberof AppFrontAction
     */
    constructor(opts: IPSAppDEUIAction, context?: any) {
        this.actionModel = opts;
    }

    /**
     * 执行界面行为
     *
     * @param args
     * @param context
     * @param params
     * @param $event
     * @param xData
     * @param actionContext
     * @param srfParentDeName
     *
     * @memberof AppFrontAction
     */
    public async execute(
        args: any[],
        context: any = {},
        params: any = {},
        $event?: any,
        xData?: any,
        actionContext?: any,
        srfParentDeName?: string,
        deUIService?: any,
    ) {
        const actionTarget: string | null = this.actionModel.actionTarget;
        if (Object.is(actionTarget, 'SINGLEDATA')) {
            actionContext.$throw(actionContext.$t('app.commonwords.nosupportsingle'), 'AppFrontAction');
        } else if (Object.is(actionTarget, 'MULTIDATA')) {
            actionContext.$throw(actionContext.$t('app.commonwords.nosupportmultile'), 'AppFrontAction');
        } else {
            // 处理数据
            let data: any = {};
            let parentContext: any = {};
            let parentViewParam: any = {};
            const _this: any = actionContext;
            if (this.actionModel.saveTargetFirst) {
                const result: any = await xData.save(args, false);
                args = [result.data];
            }
            const _args: any[] = Util.deepCopy(args);
            if (
                this.actionModel.getPSAppDataEntity() &&
                (Object.is(actionTarget, 'SINGLEKEY') || Object.is(actionTarget, 'MULTIKEY'))
            ) {
                const entityName = this.actionModel.getPSAppDataEntity()?.codeName.toLowerCase();
                const key = (ModelTool.getAppEntityKeyField(
                    this.actionModel.getPSAppDataEntity(),
                ) as IPSAppDEField)?.codeName.toLowerCase();
                const majorKey = (ModelTool.getAppEntityMajorField(
                    this.actionModel.getPSAppDataEntity(),
                ) as IPSAppDEField)?.codeName.toLowerCase();
                if (_args[0]?.[key]) {
                    Object.assign(context, { [entityName!]: `%${key}%` });
                } else {
                    Object.assign(context, { [entityName!]: `%${entityName}%` });
                }
                Object.assign(params, { [key!]: `%${key}%` });
                Object.assign(params, { [majorKey]: `%${majorKey}%` });
            }
            // 自定义导航参数优先级大于预置导航参数
            if (
                this.actionModel.getPSNavigateContexts() &&
                (this.actionModel.getPSNavigateContexts() as IPSNavigateContext[]).length > 0
            ) {
                const localContext = Util.formatNavParam(this.actionModel.getPSNavigateContexts());
                Object.assign(context, localContext);
            }
            if (
                this.actionModel.getPSNavigateParams() &&
                (this.actionModel.getPSNavigateParams() as IPSNavigateParam[]).length > 0
            ) {
                const localParam = Util.formatNavParam(this.actionModel.getPSNavigateParams());
                Object.assign(params, localParam);
            }
            if (_this.context) {
                parentContext = _this.context;
            }
            if (_this.viewparams) {
                parentViewParam = _this.viewparams;
            }
            context = UIActionTool.handleContextParam(actionTarget, _args, parentContext, parentViewParam, context);
            data = UIActionTool.handleActionParam(actionTarget, _args, parentContext, parentViewParam, params);
            context = Object.assign({}, actionContext.context, context);
            // 构建srfparentdename和srfparentkey
            let parentObj: any = {
                srfparentdename: srfParentDeName ? srfParentDeName : null,
                srfparentkey: srfParentDeName ? context[srfParentDeName.toLowerCase()] : null,
            };
            Object.assign(data, parentObj);
            Object.assign(context, parentObj);
            const frontPSAppView: IPSAppView | null = this.actionModel.getFrontPSAppView();
            this.oPenView(
                frontPSAppView,
                actionContext,
                context,
                args,
                deUIService,
                params,
                $event,
                data,
                xData,
                _args,
            );
        }
    }

    /**
     * 打开视图
     *
     * @param {*} actionContext
     * @param {*} context
     * @param {*} args
     * @param {*} deUIService
     * @param {*} params
     * @param {*} $event
     * @param {*} data
     * @param {*} xData
     * @param {*} _args
     * @return {*}
     * @memberof AppFrontAction
     */
    async oPenView(
        frontPSAppView: any,
        actionContext: any,
        context: any,
        args: any,
        deUIService: any,
        params: any,
        $event: any,
        data: any,
        xData: any,
        _args: any,
    ) {
        // 打开HTML
        const $event1 = $event;
        if (Object.is(this.actionModel.frontProcessType, 'OPENHTMLPAGE') && this.actionModel.htmlPageUrl) {
            const url = StringUtil.fillStrData(this.actionModel.htmlPageUrl, context, data);
            window.open(url, '_blank');
            return null;
            // 打开顶级视图，打开顶级视图或向导（模态）
        } else if (
            Object.is(this.actionModel.frontProcessType, 'TOP') ||
            Object.is(this.actionModel.frontProcessType, 'WIZARD')
        ) {
            if (!this.actionModel.getFrontPSAppView()) {
                actionContext.$warning(
                    `${this.actionModel.caption}${actionContext.$t('app.warn.unopenview')}`,
                    'oPenView',
                );
                return;
            }
            await frontPSAppView?.fill(true);
            if (!frontPSAppView) {
                return;
            }
            // 准备deResParameters参数和parameters参数
            let deResParameters: any[] = [];
            let parameters: any[] = [];
            if (frontPSAppView.getPSAppDataEntity()) {
                // 处理视图关系参数 （只是路由打开模式才计算）
                if (
                    !frontPSAppView.openMode ||
                    frontPSAppView.openMode == 'INDEXVIEWTAB' ||
                    frontPSAppView.openMode == 'POPUPAPP'
                ) {
                    deResParameters = Util.formatAppDERSPath(
                        context,
                        (frontPSAppView as IPSAppDEView).getPSAppDERSPaths(),
                    );
                }
            }
            if (!frontPSAppView.openMode || frontPSAppView.openMode == 'INDEXVIEWTAB') {
                if (frontPSAppView.getPSAppDataEntity()) {
                    parameters = [
                        {
                            pathName: Util.srfpluralize(
                                (frontPSAppView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName,
                            ).toLowerCase(),
                            parameterName: (frontPSAppView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName.toLowerCase(),
                        },
                        {
                            pathName: 'views',
                            parameterName: ((frontPSAppView as IPSAppDEView).getPSDEViewCodeName() as string).toLowerCase(),
                        },
                    ];
                } else {
                    parameters = [{ pathName: 'views', parameterName: frontPSAppView.codeName.toLowerCase() }];
                }
            } else {
                if (frontPSAppView.getPSAppDataEntity()) {
                    parameters = [
                        {
                            pathName: Util.srfpluralize(
                                (frontPSAppView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName,
                            ).toLowerCase(),
                            parameterName: (frontPSAppView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName.toLowerCase(),
                        },
                    ];
                }
                if (frontPSAppView && frontPSAppView.modelPath) {
                    Object.assign(context, { viewpath: frontPSAppView.modelPath });
                }
            }
            //打开视图后续逻辑
            const openViewNextLogic: any = (actionModel: any, actionContext: any, xData: any, resultDatas?: any) => {
                if (actionModel.reloadData && xData && xData.refresh && xData.refresh instanceof Function) {
                    xData.refresh(args);
                }
                if (actionModel.closeEditView) {
                    actionContext.closeView(null);
                }
                // 后续界面行为
                if (this.actionModel.M?.getNextPSUIAction) {
                    getPSUIActionByModelObject(this.actionModel).then((nextUIaction: any) => {
                        if (nextUIaction.getPSAppDataEntity()) {
                            let [tag, appDeName] = (actionModel.getNextPSUIAction() as IPSAppDEUIAction).id.split('@');
                            if (deUIService) {
                                deUIService.excuteAction(
                                    tag,
                                    resultDatas,
                                    context,
                                    params,
                                    $event,
                                    xData,
                                    actionContext,
                                    undefined,
                                    deUIService,
                                );
                            }
                        } else {
                            (AppGlobalService.getInstance() as any).executeGlobalAction(
                                nextUIaction.id,
                                resultDatas,
                                context,
                                params,
                                $event,
                                xData,
                                actionContext,
                                undefined,
                            );
                        }
                    });
                }
            };
            // 打开重定向视图
            if (frontPSAppView.redirectView) {
                const data = args[0];
                const field: IPSAppDEField | null = (frontPSAppView as IPSAppDERedirectView).getTypePSAppDEField();
                if (field) {
                    const type = data[field.codeName.toLocaleLowerCase()];
                    const appViewRefs = (frontPSAppView as IPSAppDERedirectView).getRedirectPSAppViewRefs()!;
                    let appViewRef: IPSAppViewRef | undefined = appViewRefs?.find(
                        (appViewRef: IPSAppViewRef) =>
                            appViewRef.name === type || appViewRef.name === `EDITVIEW:${type}`,
                    );
                    if (!appViewRef) {
                        appViewRef = appViewRefs?.find((appViewRef: IPSAppViewRef) => {
                            const entityName = frontPSAppView.getPSAppDataEntity()?.name;
                            return appViewRef.name === type || appViewRef.name === `${entityName}:EDITVIEW:${type}`;
                        });
                    }
                    if (appViewRef) {
                        const appView: any = await appViewRef.getRefPSAppView()?.fill();
                        this.oPenView(
                            appView,
                            actionContext,
                            context,
                            args,
                            deUIService,
                            params,
                            $event,
                            data,
                            xData,
                            _args,
                        );
                    }
                }
            } else if (!frontPSAppView.openMode || frontPSAppView.openMode == 'INDEXVIEWTAB') {
                const routePath = actionContext.$viewTool.buildUpRoutePath(
                    actionContext.$route,
                    context,
                    deResParameters,
                    parameters,
                    _args,
                    data,
                );
                actionContext.$router.push(routePath);
                openViewNextLogic(this.actionModel, actionContext, xData, data);
                return null;
            } else if (frontPSAppView.openMode == 'POPUPMODAL') {
                const view: any = {
                    viewname: 'app-view-shell',
                    height: frontPSAppView.height,
                    width: frontPSAppView.width,
                    title: actionContext.$tl(frontPSAppView.getCapPSLanguageRes()?.lanResTag, frontPSAppView.caption),
                };
                let container: Subject<any> = actionContext.$appmodal.openModal(view, context, data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    openViewNextLogic(this.actionModel, actionContext, xData, result.datas);
                    return result.datas;
                });
            } else if (frontPSAppView.openMode.indexOf('DRAWER') !== -1) {
                const view: any = {
                    viewname: 'app-view-shell',
                    height: frontPSAppView.height,
                    width: frontPSAppView.width,
                    title: actionContext.$tl(frontPSAppView.getCapPSLanguageRes()?.lanResTag, frontPSAppView.caption),
                    placement: frontPSAppView.openMode,
                };
                let container: Subject<any> = actionContext.$appdrawer.openDrawer(
                    view,
                    Util.getViewProps(context, data),
                );
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    openViewNextLogic(this.actionModel, actionContext, xData, result.datas);
                    return result.datas;
                });
            } else if (frontPSAppView.openMode === 'POPUP') {
                const view: any = {
                    viewname: 'app-view-shell',
                    height: frontPSAppView.height,
                    width: frontPSAppView.width,
                    title: actionContext.$tl(frontPSAppView.getCapPSLanguageRes()?.lanResTag, frontPSAppView.caption),
                    placement: frontPSAppView.openMode,
                };
                const container = appPopup.openDrawer(
                    view,
                    Util.getViewProps(context, data),
                );
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    openViewNextLogic(this.actionModel, actionContext, xData, result.datas);
                    return result.datas;
                });
            } else if (frontPSAppView.openMode == 'POPOVER') {
                const view: any = {
                    viewname: 'app-view-shell',
                    height: frontPSAppView.height,
                    width: frontPSAppView.width,
                    title: actionContext.$tl(frontPSAppView.getCapPSLanguageRes()?.lanResTag, frontPSAppView.caption),
                    placement: frontPSAppView.openMode,
                };
                let container: Subject<any> = actionContext.$apppopover.openPop($event, view, context, data);
                container.subscribe((result: any) => {
                    if (!result || !Object.is(result.ret, 'OK')) {
                        return;
                    }
                    openViewNextLogic(this.actionModel, actionContext, xData, result.datas);
                    return result.datas;
                });
            } else {
                actionContext.$warning(
                    `${frontPSAppView.title}${actionContext.$t('app.nosupport.unopen')}`,
                    'oPenView',
                );
            }
            // 用户自定义
        } else {
            actionContext.$warning(
                `${this.actionModel.caption}${actionContext.$t('app.nosupport.uncustom')}`,
                'oPenView',
            );
        }
    }
}
