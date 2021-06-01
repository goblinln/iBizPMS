import {
    getPSUIActionByModelObject,
    IPSAppDEDataView,
    IPSAppDEUIAction,
    IPSAppViewLogic,
    IPSAppViewUIAction,
    IPSControl,
} from '@ibiz/dynamic-model-api';
import { LogUtil } from 'ibiz-core';
import { UIServiceRegister } from 'ibiz-service';
import { AppGlobalService } from './app-global-action-service';

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
    constructor(opts?: any) {}

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
    public executeViewLogic(
        itemTag: string,
        $event: any,
        actionContext: any,
        params: any = {},
        viewlogicData: Array<IPSAppViewLogic> | null,
    ) {
        if (!viewlogicData) {
            return;
        }
        let targetViewLogic: any = viewlogicData.find((item: any) => {
            return item.name === itemTag;
        });
        this.prepareActionParams(targetViewLogic, $event, actionContext, params);
    }

    /**
     * 执行逻辑
     *
     * @author chitanda
     * @date 2021-04-23 15:04:34
     * @param {IPSAppViewLogic} logic
     * @param {*} $event
     * @param {*} actionContext
     * @param {*} [params]
     */
    executeLogic(logic: IPSAppViewLogic, $event: any, actionContext: any, params?: any): void {
        this.prepareActionParams(logic, $event, actionContext, params);
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
    public async prepareActionParams(viewLogic: IPSAppViewLogic, $event: any, actionContext: any, params: any = {}) {
        if (!viewLogic) {
            LogUtil.warn('无事件参数未支持');
            return;
        }
        if (!Object.is(viewLogic.logicType, 'APPVIEWUIACTION') || !viewLogic.getPSAppViewUIAction) {
            return;
        }
        const targetViewAction: IPSAppViewUIAction | null = viewLogic.getPSAppViewUIAction();
        if (!targetViewAction) {
            LogUtil.warn('视图界面行为不存在');
            return;
        }
        await (targetViewAction as IPSAppViewUIAction).fill();
        // 取数
        let datas: any[] = [];
        let xData: any = null;
        // _this 指向容器对象
        const _this: any = actionContext;
        const paramJO: any = {};
        const contextJO: any = {};
        if (!targetViewAction.xDataControlName) {
            if (_this.getDatas && _this.getDatas instanceof Function) {
                datas = [..._this.getDatas()];
            }
        } else {
            // 界面行为数据部件与控件容器不同时，即逻辑事件在视图里时
            if (!Object.is(viewLogic.getParentPSModelObject().name, targetViewAction.xDataControlName)) {
                xData = _this.$refs[targetViewAction.xDataControlName.toLowerCase()].ctrl;
            } else {
                xData = _this;
            }
            if (xData.getDatas && xData.getDatas instanceof Function) {
                datas = [...xData.getDatas()];
            }
        }
        if (params && Object.keys(params).length > 0) {
            datas = [params];
        }
        const targetUIAction = (await getPSUIActionByModelObject(targetViewAction)) as IPSAppDEUIAction;
        if (targetUIAction && targetUIAction.getPSAppDataEntity()) {
            const targetParentObject: IPSAppDEDataView | IPSControl = viewLogic.getParentPSModelObject() as
                | IPSAppDEDataView
                | IPSControl;
            const targetUIService: any = await UIServiceRegister.getInstance().getService(
                actionContext.context,
                `${targetUIAction.getPSAppDataEntity()?.codeName.toLowerCase()}`,
            );
            await targetUIService.loaded();
            Object.assign(contextJO, { srfparentdemapname: targetParentObject?.getPSAppDataEntity()?.getPSDEName() });
            targetUIService.excuteAction(
                targetUIAction.uIActionTag,
                datas,
                contextJO,
                paramJO,
                $event,
                xData,
                actionContext,
                targetParentObject?.getPSAppDataEntity()?.codeName.toLowerCase(),
            );
        } else {
            if (viewLogic.getParentPSModelObject() && viewLogic.getParentPSModelObject().M.getPSAppDataEntity) {
                const targetParentObject: IPSAppDEDataView | IPSControl = viewLogic.getParentPSModelObject() as
                    | IPSAppDEDataView
                    | IPSControl;
                Object.assign(contextJO, { srfparentdemapname: targetParentObject?.getPSAppDataEntity()?.getPSDEName() });
                (AppGlobalService.getInstance() as any).executeGlobalAction(
                    targetUIAction.uIActionTag,
                    datas,
                    contextJO,
                    paramJO,
                    $event,
                    xData,
                    actionContext,
                    targetParentObject?.getPSAppDataEntity()?.codeName.toLowerCase(),
                );
            } else {
                (AppGlobalService.getInstance() as any).executeGlobalAction(
                    targetUIAction.uIActionTag,
                    datas,
                    contextJO,
                    paramJO,
                    $event,
                    xData,
                    actionContext,
                    undefined,
                );
            }
        }
    }
}
