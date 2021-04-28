import { getPSUIActionByModelObject, IPSAppDataEntity, IPSAppDEField, IPSAppDEMethod, IPSAppDEUIAction, IPSAppView, IPSNavigateContext, IPSNavigateParam } from "@ibiz/dynamic-model-api";
import {  ModelTool, UIActionTool, Util } from "ibiz-core";
import { GlobalService } from "ibiz-service";
import {  AppGlobalService } from "../app-service";

export class AppBackEndAction {

    /**
     * 模型数据
     * 
     * @memberof AppBackEndAction
     */
     private actionModel !:IPSAppDEUIAction;
    
     /**
      * 初始化AppBackEndAction
      * 
      * @memberof AppBackEndAction
      */
     constructor(opts:any,context?: any){
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
     * @memberof AppBackEndAction
     */
    public async execute(args: any[], context: any = {}, params: any = {}, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string, deUIService?: any) {
        const actionTarget: string | null = this.actionModel.actionTarget;
        if (this.actionModel.enableConfirm && this.actionModel.confirmMsg) {
            let confirmResult: boolean = await actionContext.$Notice.confirm(
                '警告',
                this.actionModel.confirmMsg
              );
            if (!confirmResult) {
                return;
            }
        }
        if (Object.is(actionTarget, "SINGLEDATA")) {
            actionContext.$Notice.error('不支持单项数据');
        } else if (Object.is(actionTarget, "MULTIDATA")) {
            actionContext.$Notice.error('不支持多项数据');
        } else {
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
                Object.assign(context, { [(this.actionModel.getPSAppDataEntity() as IPSAppDataEntity)?.codeName.toLowerCase()]: `%${(this.actionModel.getPSAppDataEntity() as IPSAppDataEntity)?.codeName.toLowerCase()}%` });
                Object.assign(params, { [(ModelTool.getAppEntityKeyField(this.actionModel.getPSAppDataEntity()) as IPSAppDEField)?.codeName.toLowerCase()]: `%${(this.actionModel.getPSAppDataEntity() as IPSAppDataEntity)?.codeName.toLowerCase()}%` });
                Object.assign(params, { [(ModelTool.getAppEntityMajorField(this.actionModel.getPSAppDataEntity()) as IPSAppDEField)?.codeName.toLowerCase()]: `%${(ModelTool.getAppEntityMajorField(this.actionModel.getPSAppDataEntity()) as IPSAppDEField)?.codeName.toLowerCase()}%` });
            }
            // 自定义导航参数优先级大于预置导航参数
            if (this.actionModel.getPSNavigateContexts() && (this.actionModel.getPSNavigateContexts() as IPSNavigateContext[])?.length > 0) {
                const localContext = Util.formatNavParam(this.actionModel.getPSNavigateContexts());
                Object.assign(context, localContext);
            }
            if (this.actionModel.getPSNavigateParams() && (this.actionModel.getPSNavigateParams() as IPSNavigateParam[]).length > 0) {
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
            // 多项数据主键转换数据
            // TODO
            // if (Object.is(actionTarget, "MULTIKEY") && this.actionModel?.getPSDEAction?.actionType == 'USERCUSTOM') {
            if (Object.is(actionTarget, "MULTIKEY")) {
                let tempDataArray: Array<any> = [];
                if ((_args.length > 1) && (Object.keys(data).length > 0)) {
                    for (let i = 0; i < _args.length; i++) {
                        let tempObject: any = {};
                        Object.keys(data).forEach((key: string) => {
                            Object.assign(tempObject, { [key]: data[key].split(',')[i] });
                        })
                        tempDataArray.push(tempObject);
                    }
                } else {
                    tempDataArray.push(data);
                }
                data = tempDataArray;
            }
            context = Object.assign({}, actionContext.context, context);
            // 构建srfparentdename和srfparentkey
            let parentObj: any = { srfparentdename: srfParentDeName ? srfParentDeName : null, srfparentkey: srfParentDeName ? context[srfParentDeName.toLowerCase()] : null };
            if (!Object.is(actionTarget, "MULTIKEY")) {
                Object.assign(data, parentObj);
            }
            Object.assign(context, parentObj);
            if (context && context.srfsessionid) {
                context.srfsessionkey = context.srfsessionid;
                delete context.srfsessionid;
            }
            const backend = () => {
                if (this.actionModel.getPSAppDataEntity() && this.actionModel.getPSAppDEMethod()) {
                    new GlobalService().getService((this.actionModel.getPSAppDataEntity() as IPSAppDataEntity)?.codeName).then((curService: any) => {
                        curService[(this.actionModel.getPSAppDEMethod() as IPSAppDEMethod)?.codeName](context, data, this.actionModel.showBusyIndicator).then((response: any) => {
                            if (!response || response.status !== 200) {
                                actionContext.$Notice.error(response.message);
                                return;
                            }
                            if (this.actionModel.showBusyIndicator) {
                                if (this.actionModel.successMsg) {
                                    actionContext.$Notice.success(this.actionModel.successMsg);
                                } else {
                                    actionContext.$Notice.success(`${this.actionModel.caption}成功！`);
                                }
                            }
                            if (this.actionModel.reloadData && xData && xData.refresh && xData.refresh instanceof Function) {
                                xData.refresh(args);
                            }
                            if (this.actionModel.closeEditView) {
                                actionContext.closeView(null);
                            }
                            // 后续界面行为
                            if (this.actionModel?.M?.getNextPSUIAction) {
                                const { data: result } = response;
                                let _args: any[] = [];
                                if (Object.is(actionContext.$util.typeOf(result), 'array')) {
                                    _args = [...result];
                                } else if (Object.is(actionContext.$util.typeOf(result), 'object')) {
                                    _args = [{ ...result }];
                                } else {
                                    _args = [...args];
                                }
                                getPSUIActionByModelObject(this.actionModel).then((nextUIaction:any)=>{
                                  if (nextUIaction.getPSAppDataEntity()) {
                                      let [tag, appDeName] = nextUIaction.id.split('@');
                                      if (deUIService) {
                                          deUIService.excuteAction(tag, _args, context, params, $event, xData, actionContext, undefined, deUIService);
                                      }
                                  } else {
                                      (AppGlobalService.getInstance() as any).executeGlobalAction(nextUIaction.id, _args, context, params, $event, xData, actionContext, undefined);
                                  }
                                })
                            }
                        }).catch((response: any) => {
                            if (response && response.data && response.data.message) {
                                actionContext.$Notice.error(response.data.message);
                            }
                        })
                    })
                }
            }
            if (this.actionModel.getFrontPSAppView()) {
                const frontPSAppView: IPSAppView | null = this.actionModel.getFrontPSAppView();
                await frontPSAppView?.fill(true);
                if (!frontPSAppView) {
                    return;
                }
                if (frontPSAppView.openMode && frontPSAppView.openMode.indexOf('DRAWER') !== -1) {
                    const view: any = {
                        viewname: 'app-view-shell',
                        height: frontPSAppView.height,
                        width: frontPSAppView.width,
                        title: frontPSAppView.title,
                        placement: frontPSAppView.openMode
                    };
                    if (frontPSAppView && frontPSAppView.modelPath) {
                        Object.assign(context, { viewpath: frontPSAppView.modelPath });
                    }
                    const result: any = await actionContext.$appdrawer.openDrawer(view, Util.getViewProps(context, data));
                    if (result && Object.is(result.ret, 'OK')) {
                        Object.assign(data, { srfactionparam: result.datas });
                        backend();
                    }
                } else {
                    const view: any = {
                        viewname: 'app-view-shell',
                        height: frontPSAppView.height,
                        width: frontPSAppView.width,
                        title: frontPSAppView.title
                    };
                    if (frontPSAppView && frontPSAppView.modelPath) {
                        Object.assign(context, { viewpath: frontPSAppView.modelPath });
                    }
                    const result: any = await actionContext.$appmodal.openModal(view, context, data);
                    if (result && Object.is(result.ret, 'OK')) {
                        Object.assign(data, { srfactionparam: result.datas });
                        backend();
                    }
                }
            } else {
                backend();
            }
        }
    }

}