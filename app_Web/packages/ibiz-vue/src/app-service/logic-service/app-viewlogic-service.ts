import { IBizActionModel, IBizViewActionModel, IBizViewLogicModel } from "ibiz-core";
import { UIServiceRegister } from "ibiz-service";
import { AppGlobalService } from "./app-global-action-service";

/**
 * 视图逻辑服务
 * 
 * @export
 * @class AppViewLogicService
 */
export class AppViewLogicService {

    /**
     * 单例变量声明
     *
     * @private
     * @static
     * @type {AppViewLogicService}
     * @memberof AppViewLogicService
     */
    private static appViewLogicService: AppViewLogicService;

    /**
     * 获取 AppViewLogicService 单例对象
     *
     * @static
     * @returns {AppViewLogicService}
     * @memberof AppViewLogicService
     */
    public static getInstance(): AppViewLogicService {
        if (!AppViewLogicService.appViewLogicService) {
            AppViewLogicService.appViewLogicService = new AppViewLogicService();
        }
        return this.appViewLogicService;
    }

    /**
     * 构造AppViewLogicService对象
     *
     * @memberof AppViewLogicService
     */
    constructor(opts?: any) { }

    /**
     * 执行视图逻辑
     * 
     * @param itemTag 触发标识
     * @param $event 触发事件源
     * @param actionContext 操作上下文环境
     * @param params 附加参数
     * @param viewlogicData 当前容器视图逻辑集合 
     * @memberof AppViewLogicService
     */
    public executeViewLogic(itemTag: string, $event: any, actionContext: any, params: any = {}, viewlogicData: Array<any>) {
        let targetViewLogic: any = viewlogicData.find((item: any) => {
            return item.name === itemTag;
        })
        this.prepareActionParams(targetViewLogic, $event, actionContext, params);
    }

    /**
     * 准备界面行为参数
     *
     * @param viewLogic  视图逻辑
     * @param $event 触发事件源
     * @param actionContext 操作上下文环境
     * @param params 附加参数
     * 
     * @memberof AppViewLogicService
     */
    public async prepareActionParams(viewLogic: IBizViewLogicModel, $event: any, actionContext: any, params: any = {}) {
        if (!viewLogic) {
            console.warn("无事件参数未支持")
            return;
        }
        if (!Object.is(viewLogic.logicType, "APPVIEWUIACTION") || !viewLogic.getPSAppViewUIAction) {
            return;
        }
        let targetViewAction: IBizViewActionModel = viewLogic.getPSAppViewUIAction;
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = actionContext;
        let paramJO: any = {};
        let contextJO: any = {};
        if (!targetViewAction.xDataControlName) {
            if (_this.getDatas && _this.getDatas instanceof Function) {
                datas = [..._this.getDatas()];
            }
        } else {
            // 界面行为数据部件与控件容器不同时，即逻辑事件在视图里时
            if (!Object.is(targetViewAction.getParentContainer.name, targetViewAction.xDataControlName)) {
                xData = _this.$refs[targetViewAction.xDataControlName.toLowerCase()].ctrl;
            } else {
                xData = _this;
            }
            if (xData.getDatas && xData.getDatas instanceof Function) {
                datas = [...xData.getDatas()];
            }
        }
        if (params && Object.keys(params).length >0) { datas = [params]; }
        if (targetViewAction.getPSUIAction && targetViewAction.getPSUIAction.getPSAppDataEntity) {
            let targetUIAction: any = new IBizActionModel(targetViewAction.getPSUIAction, actionContext.context);
            await targetUIAction.loaded();
            let targetUIService: any = await UIServiceRegister.getInstance().getService(actionContext.context, `${targetUIAction.getPSAppDataEntity.codeName.toLowerCase()}`);
            await targetUIService.loaded();
            targetUIService.excuteAction(targetUIAction.uIActionTag, datas, contextJO, paramJO, $event, xData, actionContext, viewLogic.getParentContainer.appDataEntity.codeName.toLowerCase());
        } else {
            const uiActionTag = targetViewAction.getPSUIAction.codeName ? targetViewAction.getPSUIAction.codeName : targetViewAction.getPSUIAction?.id;
            if (viewLogic.getParentContainer && viewLogic.getParentContainer.appDataEntity) {
                (AppGlobalService.getInstance() as any).executeGlobalAction(uiActionTag, datas, contextJO, paramJO, $event, xData, actionContext, viewLogic.getParentContainer.appDataEntity.codeName.toLowerCase());
            } else {
                (AppGlobalService.getInstance() as any).executeGlobalAction(uiActionTag, datas, contextJO, paramJO, $event, xData, actionContext, undefined);
            }
        }
    }

}