import { IBizActionModel, UIActionTool, Util } from "ibiz-core";
import { Subject } from "rxjs";
import { AppGlobalService } from "../app-service";

export class AppFrontAction {

    /**
     * 模型数据
     * 
     * @memberof AppFrontAction
     */
    private actionModel !: IBizActionModel;

    /**
     * 初始化AppFrontAction
     * 
     * @memberof AppFrontAction
     */
    constructor(opts: any, context?: any) {
        this.actionModel = new IBizActionModel(opts, context);
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
    public async execute(args: any[], context: any = {}, params: any = {}, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string, deUIService?:any) {
        await this.actionModel.loaded();
        const actionTarget: string | null = this.actionModel.actionTarget;
        if (Object.is(actionTarget, "SINGLEDATA")) {
            actionContext.$Notice.error({ title: '错误', desc: '不支持单项数据' });
        } else if (Object.is(actionTarget, "MULTIDATA")) {
            actionContext.$Notice.error({ title: '错误', desc: '不支持多项数据' });
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
            if (this.actionModel.getPSAppDataEntity && (Object.is(actionTarget, "SINGLEKEY") || Object.is(actionTarget, "MULTIKEY"))) {
                Object.assign(context, { [this.actionModel.getPSAppDataEntity.codeName.toLowerCase()]: `%${this.actionModel.getPSAppDataEntity.codeName.toLowerCase()}%` });
                Object.assign(params, { [this.actionModel.getPSAppDataEntity.keyField.codeName.toLowerCase()]: `%${this.actionModel.getPSAppDataEntity.codeName.toLowerCase()}%` });
                Object.assign(params, { [this.actionModel.getPSAppDataEntity.majorField.codeName.toLowerCase()]: `%${this.actionModel.getPSAppDataEntity.majorField.codeName.toLowerCase()}%` });
            }
            // 自定义导航参数优先级大于预置导航参数
            if (this.actionModel.getPSNavigateContexts && this.actionModel.getPSNavigateContexts.length >0) {
                const localContext = Util.formatNavParam(this.actionModel.getPSNavigateContexts);
                Object.assign(context,localContext);
            }
            if (this.actionModel.getPSNavigateParams && this.actionModel.getPSNavigateParams.length >0) {
                const localParam = Util.formatNavParam(this.actionModel.getPSNavigateParams);
                Object.assign(params,localParam);
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
            let parentObj: any = { srfparentdename: srfParentDeName ? srfParentDeName : null, srfparentkey: srfParentDeName ? context[srfParentDeName.toLowerCase()] : null };
            Object.assign(data, parentObj);
            Object.assign(context, parentObj);
            // 打开HTML
            if (Object.is(this.actionModel.frontProcessType, "OPENHTMLPAGE") && this.actionModel.htmlPageUrl) {
                window.open(this.actionModel.htmlPageUrl, '_blank');
                return null;
                // 打开顶级视图，打开顶级视图或向导（模态）
            } else if (Object.is(this.actionModel.frontProcessType, "TOP") || Object.is(this.actionModel.frontProcessType, "WIZARD")) {
                if (!this.actionModel.getFrontPSAppView) {
                    actionContext.$Notice.warning({ title: '警告', desc: `${this.actionModel.caption}无打开视图` });
                    return;
                }
                const frontPSAppView: any = this.actionModel.getFrontPSAppView;
                await frontPSAppView.loaded();
                // 准备deResParameters参数和parameters参数
                let deResParameters: any[] = [];
                let parameters: any[] = [];
                if (frontPSAppView.appDataEntity) {
                // 处理视图关系参数 （只是路由打开模式才计算）
                    if (!frontPSAppView.openMode || frontPSAppView.openMode == 'INDEXVIEWTAB' || frontPSAppView.openMode == 'POPUPAPP') {
                        await frontPSAppView.loadedAppDERSPathParam();
                        deResParameters = Util.formatAppDERSPath(context, frontPSAppView.getPSAppDERSPaths);
                    }
                }
                if (!frontPSAppView.openMode || frontPSAppView.openMode == 'INDEXVIEWTAB') {
                    if (frontPSAppView.appDataEntity) {
                        parameters = [
                            { pathName: Util.srfpluralize(frontPSAppView.appDataEntity.codeName).toLowerCase(), parameterName: frontPSAppView.appDataEntity.codeName.toLowerCase() },
                            { pathName: 'views', parameterName: frontPSAppView.getPSDEViewCodeName.toLowerCase() },
                        ];
                    } else {
                        parameters = [
                            { pathName: "views", parameterName: frontPSAppView.codeName.toLowerCase() },
                        ];
                    }
                } else {
                    if (frontPSAppView.appDataEntity) {
                        parameters = [
                            { pathName: Util.srfpluralize(frontPSAppView.appDataEntity.codeName).toLowerCase(), parameterName: frontPSAppView.appDataEntity.codeName.toLowerCase() },
                        ];
                    }
                    if (frontPSAppView && frontPSAppView.dynaModelFilePath) {
                        Object.assign(context, { viewpath: frontPSAppView.dynaModelFilePath });
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
                    if(this.actionModel.getNextPSUIAction){
                        const nextUIaction = this.actionModel.getNextPSUIAction;
                        if(nextUIaction.getPSAppDataEntity){
                            let [tag, appDeName] = this.actionModel.getNextPSUIAction.id.split('@');
                            if(deUIService){
                                deUIService.excuteAction(tag,resultDatas, context, params, $event, xData, actionContext, undefined, deUIService);
                            }
                        }else{
                            (AppGlobalService.getInstance() as any).executeGlobalAction(nextUIaction.id, resultDatas, context, params, $event, xData, actionContext, undefined);
                        }
                    }
                }
                // 打开视图
                if (frontPSAppView.redirectView) {
                    // todo 重定向视图
                } else if (!frontPSAppView.openMode || frontPSAppView.openMode == 'INDEXVIEWTAB') {
                    const routePath = actionContext.$viewTool.buildUpRoutePath(actionContext.$route, context, deResParameters, parameters, _args, data);
                    actionContext.$router.push(routePath);
                    openViewNextLogic(this.actionModel, actionContext, xData, data);
                    return null;
                } else if (frontPSAppView.openMode == 'POPUPMODAL') {
                    const view: any = {
                        viewname: 'app-view-shell',
                        height: frontPSAppView.height,
                        width: frontPSAppView.width,
                        title: frontPSAppView.title
                    };
                    let container: Subject<any> = actionContext.$appmodal.openModal(view, context, data);
                    container.subscribe((result: any) => {
                        if (!result || !Object.is(result.ret, 'OK')) {
                            return;
                        }
                        openViewNextLogic(this.actionModel, actionContext, xData, result.datas);
                        return result.datas;
                    })
                } else if (frontPSAppView.openMode.indexOf('DRAWER') !== -1) {
                    const view: any = {
                        viewname: 'app-view-shell',
                        height: frontPSAppView.height,
                        width: frontPSAppView.width,
                        title: frontPSAppView.title,
                        placement: frontPSAppView.openMode
                    };
                    let container: Subject<any> = actionContext.$appdrawer.openDrawer(view, Util.getViewProps(context, data));
                    container.subscribe((result: any) => {
                        if (!result || !Object.is(result.ret, 'OK')) {
                            return;
                        }
                        openViewNextLogic(this.actionModel, actionContext, xData, result.datas);
                        return result.datas;
                    })
                } else if (frontPSAppView.openMode == 'POPOVER') {
                    const view: any = {
                        viewname: 'app-view-shell',
                        height: frontPSAppView.height,
                        width: frontPSAppView.width,
                        title: frontPSAppView.title,
                        placement: frontPSAppView.openMode
                    };
                    let container: Subject<any> = actionContext.$apppopover.openPop($event, view, context, data);
                    container.subscribe((result: any) => {
                        if (!result || !Object.is(result.ret, 'OK')) {
                            return;
                        }
                        openViewNextLogic(this.actionModel, actionContext, xData, result.datas);
                        return result.datas;
                    })
                } else {
                    actionContext.$Notice.warning({ title: '警告', desc: `${frontPSAppView.title}不支持该模式打开` });
                }
                // 用户自定义
            } else {
                actionContext.$Notice.warning({ title: '警告', desc: `${this.actionModel.caption}自定义未实现` });
            }
        }
    }

}