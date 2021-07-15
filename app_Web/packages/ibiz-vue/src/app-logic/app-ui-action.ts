import { getPSAppDEUILogicByModelObject, IPSAppDEUIAction } from '@ibiz/dynamic-model-api';
import { LogUtil } from 'ibiz-core';
import { AppUILogicService } from './appuilogic/uilogic-service';

export class AppDEUIAction {

    /**
     * 模型数据
     *
     * @memberof AppDEUIAction
     */
    protected actionModel!: IPSAppDEUIAction;

    /**
     * 初始化AppDEUIAction
     *
     * @memberof AppDEUIAction
     */
    constructor(opts: any, context?: any) {
        this.actionModel = opts;
    }

    /**
     * 执行界面逻辑
     *
     * @param {any[]} args 数据对象
     * @param {*} context 应用上下文
     * @param {*} params 视图参数
     * @param {*} $event 事件源对象
     * @param {*} xData 部件对象
     * @param {*} actioncontext 界面容器对象
     * @param {*} srfParentDeName 关联父应用实体代码名称
     * @memberof AppDEUIAction
     */
    public async executeDEUILogic(args: any[], context: any = {}, params: any = {},
        $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (Object.is(this.actionModel.uILogicType, 'DEUILOGIC')) {
            const appDEUILogic = await getPSAppDEUILogicByModelObject(this.actionModel);
            if (appDEUILogic) {
                await AppUILogicService.getInstance().onExecute(appDEUILogic, args, context, params, $event, xData, actionContext, srfParentDeName);
            } else {
                LogUtil.warn('未找到实体界面处理逻辑对象');
            }
        } else {
            LogUtil.warn('未实现应用界面处理逻辑');
        }
    }

}