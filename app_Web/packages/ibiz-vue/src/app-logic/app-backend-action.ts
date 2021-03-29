import { IBizActionModel, UIActionTool, Util } from "ibiz-core";
import { GlobalService } from "ibiz-service";
import { AppGlobalService } from "../app-service";

export class AppBackEndAction {

    /**
     * 模型数据
     * 
     * @memberof AppBackEndAction
     */
    private actionModel !:IBizActionModel;
    
    /**
     * 初始化AppBackEndAction
     * 
     * @memberof AppBackEndAction
     */
    constructor(opts:any,context?: any){
        this.actionModel = new IBizActionModel(opts,context);
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
    public async execute(args: any[], context: any = {}, params: any = {}, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string, deUIService?:any){
        await this.actionModel.loaded();
        const actionTarget: string | null = this.actionModel.actionTarget;
        if (this.actionModel.enableConfirm && this.actionModel.confirmMsg) {
            let confirmResult: boolean = await new Promise((resolve: any, reject: any) => {
                actionContext.$Modal.confirm({
                    title: '警告',
                    content: `${this.actionModel.confirmMsg}`,
                    onOk: () => { resolve(true); },
                    onCancel: () => { resolve(false); }
                });
            });
            if (!confirmResult) {
                return;
            }
        }
        if (Object.is(actionTarget, "SINGLEDATA")) {
            actionContext.$Notice.error({ title: '错误', desc: '不支持单项数据' });
        } else if (Object.is(actionTarget, "MULTIDATA")) {
            actionContext.$Notice.error({ title: '错误', desc: '不支持多项数据' });
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
            // 多项数据主键转换数据
            if (Object.is(actionTarget, "MULTIKEY") && this.actionModel?.getPSDEAction?.actionType == 'USERCUSTOM') {
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
                if (this.actionModel.getPSAppDataEntity && this.actionModel.getPSAppDEMethod) {
                    new GlobalService().getService(this.actionModel.getPSAppDataEntity.codeName).then((curService:any) =>{
                        curService[this.actionModel.getPSAppDEMethod.id](context, data, this.actionModel.showBusyIndicator).then((response: any) => {
                            if (!response || response.status !== 200) {
                                actionContext.$Notice.error({ title: '错误', desc: response.message });
                                return;
                            }
                            if (this.actionModel.showBusyIndicator) {
                                if (this.actionModel.successMsg) {
                                    actionContext.$Notice.success({ title: '成功', desc: this.actionModel.successMsg });
                                } else {
                                    actionContext.$Notice.success({ title: '成功', desc: `${this.actionModel.caption}成功！` });
                                }
                            }
                            if (this.actionModel.reloadData && xData && xData.refresh && xData.refresh instanceof Function) {
                                xData.refresh(args);
                            }
                            if (this.actionModel.closeEditView) {
                                actionContext.closeView(null);
                            }
                            // 后续界面行为
                            if(this.actionModel.getNextPSUIAction){
                                const { data: result } = response;
                                let _args: any[] = [];
                                if (Object.is(actionContext.$util.typeOf(result), 'array')) {
                                    _args = [...result];
                                } else if (Object.is(actionContext.$util.typeOf(result), 'object')) {
                                    _args = [{...result}];
                                } else {
                                    _args = [...args];
                                }
                                const nextUIaction = this.actionModel.getNextPSUIAction;
                                if(nextUIaction.getPSAppDataEntity){
                                    let [tag, appDeName] = this.actionModel.getNextPSUIAction.id.split('@');
                                    if(deUIService){
                                        deUIService.excuteAction(tag,_args, context, params, $event, xData, actionContext, undefined, deUIService);
                                    }
                                }else{
                                    (AppGlobalService.getInstance() as any).executeGlobalAction(nextUIaction.id, _args, context, params, $event, xData, actionContext, undefined);
                                }
                            }
                        }).catch((response: any) => {
                            if (response && response.data && response.data.message) {
                                actionContext.$Notice.error({ title: '错误', desc: response.data.message });
                            }
                        })
                    })
                }
            }
            if (this.actionModel.getFrontPSAppView) {
                const frontPSAppView: any = this.actionModel.getFrontPSAppView;
                await frontPSAppView.loaded();
                if (frontPSAppView.openMode.indexOf('DRAWER') !== -1) {
                    const view: any = {
                        viewname: 'app-view-shell',
                        height: frontPSAppView.height,
                        width: frontPSAppView.width,
                        title: frontPSAppView.title,
                        placement: frontPSAppView.openMode
                    };
                    if (frontPSAppView && frontPSAppView.dynaModelFilePath) {
                        Object.assign(context, { viewpath: frontPSAppView.dynaModelFilePath });
                    }
                    const appdrawer = actionContext.$appdrawer.openDrawer(view, Util.getViewProps(context, data));
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
                        title: frontPSAppView.title
                    };
                    const appmodal = actionContext.$appmodal.openModal(view, context, data);
                    if (frontPSAppView && frontPSAppView.dynaModelFilePath) {
                        Object.assign(context, { viewpath: frontPSAppView.dynaModelFilePath });
                    }
                    appmodal.subscribe((result: any) => {
                        if (result && Object.is(result.ret, 'OK')) {
                            Object.assign(data, { srfactionparam: result.datas });
                            backend();
                        }
                    });
                }
            }else{
                backend();
            }
        }
    }
    
}