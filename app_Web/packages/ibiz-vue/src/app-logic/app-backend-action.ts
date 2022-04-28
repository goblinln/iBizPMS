import {
    getPSUIActionByModelObject,
    IPSAppDataEntity,
    IPSAppDEField,
    IPSAppDEMethod,
    IPSAppView,
    IPSNavigateContext,
    IPSNavigateParam,
} from '@ibiz/dynamic-model-api';
import { ModelTool, UIActionTool, Util } from 'ibiz-core';
import { GlobalService } from 'ibiz-service';
import { AppGlobalService } from '../app-service';
import { AppDEUIAction } from './app-ui-action';
import { UIActionResult } from './appuilogic';

export class AppBackEndAction extends AppDEUIAction {

    /**
     * 是否合并参数
     *
     * @memberof AppBackEndAction
     */
    public isMergeParam: boolean = false;

    /**
     * 初始化AppBackEndAction
     *
     * @memberof AppBackEndAction
     */
    constructor(opts: any, context?: any) {
        super(opts, context);
        const method: IPSAppDEMethod = this.actionModel.getPSAppDEMethod() as IPSAppDEMethod;
        if (method?.M && (method.M.customCode || !method.M.getPSDEServiceAPIMethod)) {
            this.isMergeParam = true;
        }
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
     * @memberof AppBackEndAction
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
        if (Object.is(this.actionModel?.uILogicAttachMode, 'REPLACE')) {
            if (actionContext.context) {
                Object.assign(context, actionContext.context);
            }
            return this.executeDEUILogic(args, context, params, $event, xData, actionContext, srfParentDeName);
        }
        const actionTarget: string | null = this.actionModel.actionTarget;
        if (this.actionModel.enableConfirm && this.actionModel.confirmMsg) {
            let confirmResult: boolean = await new Promise((resolve: any, reject: any) => {
                actionContext.$Modal.confirm({
                    title: actionContext.$t('app.commonwords.warning'),
                    content: `${this.actionModel.confirmMsg}`,
                    onOk: () => {
                        resolve(true);
                    },
                    onCancel: () => {
                        resolve(false);
                    },
                });
            });
            if (!confirmResult) {
                return;
            }
        }
        if (Object.is(actionTarget, 'MULTIDATA')) {
            actionContext.$throw(actionContext.$t('app.commonwords.nosupportmultile'), 'AppBackEndAction');
        } else {
            let data: any = {};
            let tempData: any = {};
            let parentContext: any = {};
            let parentViewParam: any = {};
            const _this: any = actionContext;
            if (this.actionModel.saveTargetFirst) {
                const result: any = await xData.save(args, false);
                if (Object.is(actionTarget, 'SINGLEDATA')) {
                    Object.assign(args[0], result.data);
                } else {
                    args = [result.data];
                }
            }
            if (Object.is(actionTarget, 'SINGLEKEY') || Object.is(actionTarget, 'MULTIKEY')) {
                // todo 后台调用获取主键及主信息属性的name
                const entityName = this.actionModel.getPSAppDataEntity()?.codeName.toLowerCase();
                const key = (ModelTool.getAppEntityKeyField(
                    this.actionModel.getPSAppDataEntity(),
                ) as IPSAppDEField)?.name.toLowerCase();
                const majorKey = (ModelTool.getAppEntityMajorField(
                    this.actionModel.getPSAppDataEntity(),
                ) as IPSAppDEField)?.name.toLowerCase();
                if (args[0]?.[key]) {
                    Object.assign(context, { [entityName!]: `%${key}%` });
                } else {
                    Object.assign(context, { [entityName!]: `%${entityName}%` });
                }
                Object.assign(params, { [key!]: `%${key}%` });
                Object.assign(params, { [majorKey]: `%${majorKey}%` });
            } else if (Object.is(actionTarget, 'SINGLEDATA')) {
                data = args[0];
            }
            // 自定义导航参数优先级大于预置导航参数
            if (
                this.actionModel.getPSNavigateContexts() &&
                (this.actionModel.getPSNavigateContexts() as IPSNavigateContext[])?.length > 0
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
            context = UIActionTool.handleContextParam(actionTarget, args, parentContext, parentViewParam, context);
            if (Object.is(actionTarget, 'SINGLEDATA')) {
                tempData = UIActionTool.handleActionParam(actionTarget, args, parentContext, parentViewParam, params);
                Object.assign(data, tempData);
            } else {
                data = UIActionTool.handleActionParam(actionTarget, args, parentContext, parentViewParam, params);
            }
            // 多项数据主键转换数据
            if (Object.is(actionTarget, 'MULTIKEY')) {
                let tempDataArray: Array<any> = [];
                if (args.length > 1 && Object.keys(data).length > 0) {
                    for (let i = 0; i < args.length; i++) {
                        let tempObject: any = {};
                        Object.keys(data).forEach((key: string) => {
                            Object.assign(tempObject, { [key]: data[key].split(',')[i] });
                        });
                        tempDataArray.push(tempObject);
                    }
                } else {
                    tempDataArray.push(data);
                }
                data = tempDataArray;
            }
            context = Object.assign({}, actionContext.context, context);
            // 构建srfparentdename和srfparentkey
            let parentObj: any = {
                srfparentdename: srfParentDeName ? srfParentDeName : null,
                srfparentkey: srfParentDeName ? context[srfParentDeName.toLowerCase()] : null,
            };
            if (!Object.is(actionTarget, 'MULTIKEY')) {
                Object.assign(data, parentObj);
            }
            Object.assign(context, parentObj);
            if (context && context.srfsessionid) {
                context.srfsessionkey = context.srfsessionid;
                delete context.srfsessionid;
            }
            const backend = () => {
                if (xData && xData.formValidateStatus instanceof Function) {
                  if (!xData.formValidateStatus()) {
                    actionContext.$throw(actionContext.$t('app.searchform.globalerrortip') as string, 'save', { dangerouslyUseHTMLString: true });
                    return;
                  }
                }
                if (this.actionModel.getPSAppDataEntity() && this.actionModel.getPSAppDEMethod()) {
                    new GlobalService()
                        .getService((this.actionModel.getPSAppDataEntity() as IPSAppDataEntity)?.codeName)
                        .then(async (curService: any) => {
                            // todo 后台调用实体行为类型缺失getPSDEAction.getActionMode 暂时使用多数据做批操作Batch判断
                            const method: IPSAppDEMethod = this.actionModel.getPSAppDEMethod() as IPSAppDEMethod;
                            const methodCodeName = Object.is(actionTarget, 'MULTIKEY') ? method.codeName + 'Batch' : method?.codeName;
                            let viewLoadingService = actionContext.viewLoadingService ? actionContext.viewLoadingService : {};
                            viewLoadingService.isLoading = true;
                            curService[methodCodeName](
                                context,
                                data,
                                this.actionModel.showBusyIndicator,
                            )
                                .then(async (response: any) => {
                                    if (Object.is(actionTarget, 'SINGLEDATA')) {
                                        Util.clearAdditionalData(tempData, args[0]);
                                    }
                                    if (!response || response.status !== 200) {
                                        actionContext.$throw(response, 'AppBackEndAction');
                                        return;
                                    }
                                    const { data } = response;
                                    if (this.isMergeParam && args && args.length > 0) {
                                        Object.assign(args[0], data);
                                        actionContext.$forceUpdate();
                                    }
                                    viewLoadingService.isLoading = false;
                                    if (this.actionModel.showBusyIndicator) {
                                        if (this.actionModel.successMsg) {
                                            actionContext.$success(this.actionModel.successMsg, 'AppBackEndAction');
                                        }
                                    }
                                    if (
                                        this.actionModel.reloadData &&
                                        xData &&
                                        xData.refresh &&
                                        xData.refresh instanceof Function
                                    ) {
                                        xData.refresh(args);
                                    }
                                    if (this.actionModel.closeEditView) {
                                        actionContext.closeView(null);
                                    }
                                    // 后续界面行为
                                    if (this.actionModel.M?.getNextPSUIAction) {
                                        const { data: result } = response;
                                        let _args: any[] = [];
                                        if (Object.is(actionContext.$util.typeOf(result), 'array')) {
                                            _args = [...result];
                                        } else if (Object.is(actionContext.$util.typeOf(result), 'object')) {
                                            _args = [{ ...result }];
                                        } else {
                                            _args = [...args];
                                        }
                                        getPSUIActionByModelObject(this.actionModel).then((nextUIaction: any) => {
                                            if (nextUIaction) {
                                                let [tag, appDeName] = nextUIaction.id.split('@');
                                                if (deUIService) {
                                                    return deUIService.excuteAction(
                                                        tag,
                                                        _args,
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
                                                return (AppGlobalService.getInstance() as any).executeGlobalAction(
                                                    nextUIaction.id,
                                                    _args,
                                                    context,
                                                    params,
                                                    $event,
                                                    xData,
                                                    actionContext,
                                                    undefined,
                                                );
                                            }
                                        });
                                    } else {
                                        if (Object.is(this.actionModel?.uILogicAttachMode, 'AFTER')) {
                                            if (actionContext.context) {
                                                Object.assign(context, actionContext.context);
                                            }
                                            return this.executeDEUILogic(args, context, params, $event, xData, actionContext, context?.srfparentdename);
                                        } else {
                                            return new UIActionResult({ ok: true, result: args });
                                        }
                                    }
                                })
                                .catch((response: any) => {
                                    viewLoadingService.isLoading = false;
                                    if (response) {
                                        actionContext.$throw(response, 'AppBackEndAction');
                                    }
                                });
                        });
                }
            };
            if (this.actionModel.getFrontPSAppView()) {
                const frontPSAppView: IPSAppView | null = this.actionModel.getFrontPSAppView();
                await frontPSAppView?.fill(true);
                if (!frontPSAppView) {
                    return;
                }
                if (frontPSAppView.openMode?.indexOf('DRAWER') !== -1) {
                    const view: any = {
                        viewname: 'app-view-shell',
                        height: frontPSAppView.height,
                        width: frontPSAppView.width,
                        title: actionContext.$tl(frontPSAppView.getCapPSLanguageRes()?.lanResTag, frontPSAppView.caption),
                        placement: frontPSAppView.openMode,
                    };
                    if (frontPSAppView && frontPSAppView.modelPath) {
                        Object.assign(context, { viewpath: frontPSAppView.modelPath });
                    }
                    const appdrawer = actionContext.$appdrawer.openDrawer(view, Util.getViewProps(context, data, args));
                    appdrawer.subscribe((result: any) => {
                        if (result && Object.is(result.ret, 'OK')) {
                            Object.assign(data, { srfactionparam: result.datas });
                            backend();
                        }
                    });
                } else {
                    const view: any = {
                        viewname: 'app-view-shell',
                        height: frontPSAppView.height,
                        width: frontPSAppView.width,
                        title: actionContext.$tl(frontPSAppView.getCapPSLanguageRes()?.lanResTag, frontPSAppView.caption),
                    };
                    if (frontPSAppView && frontPSAppView.modelPath) {
                        Object.assign(context, { viewpath: frontPSAppView.modelPath });
                    }
                    const appmodal = actionContext.$appmodal.openModal(view, context, data, args);
                    appmodal.subscribe((result: any) => {
                        if (result && Object.is(result.ret, 'OK')) {
                            Object.assign(data, { srfactionparam: result.datas });
                            backend();
                        }
                    });
                }
            } else {
                backend();
            }
        }
    }

}
