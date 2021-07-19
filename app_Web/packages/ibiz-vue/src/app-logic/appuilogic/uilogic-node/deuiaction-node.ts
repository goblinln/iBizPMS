import { IPSDEUIActionLogic, getDstPSAppDEUIAction, IPSDEUILogicParam } from '@ibiz/dynamic-model-api';
import { LogUtil } from 'ibiz-core';
import { UIServiceRegister } from 'ibiz-service';
import { UIActionContext } from '../uiaction-context';
import { AppUILogicNodeBase } from './logic-node-base';
/**
 * 实体界面行为调用节点
 *
 * @export
 * @class AppUILogicDeUIActionNode
 */
export class AppUILogicDeUIActionNode extends AppUILogicNodeBase {

    constructor() {
        super();
    }

    /**
     * 执行节点
     *
     * @param {IPSDEUIActionLogic} logicNode 逻辑节点模型数据
     * @param {UIActionContext} actionContext 界面逻辑上下文
     * @memberof AppUILogicDeUIActionNode
     */
    public async executeNode(logicNode: IPSDEUIActionLogic, actionContext: UIActionContext) {
        return new Promise<void>(async (resolve) => {
            const { data, context, otherParams } = actionContext;
            const dstEntity = logicNode.getDstPSAppDataEntity();
            const dstUIAction = await getDstPSAppDEUIAction(logicNode);
            if (dstEntity && dstUIAction) {
                try {
                    const targetUIService = await UIServiceRegister.getInstance().getService(context, dstEntity.codeName.toLowerCase());
                    await targetUIService.loaded();
                    const result = await targetUIService.excuteAction(
                        dstUIAction.uIActionTag,
                        [data],
                        context,
                        otherParams?.viewparams,
                        otherParams?.event,
                        otherParams?.control,
                        otherParams?.container,
                        otherParams?.parentDeName,
                    );
                    const dstParam = actionContext.getParam((logicNode.getDstPSDEUILogicParam() as IPSDEUILogicParam)?.codeName);
                    if (result && result.ok && result.result) {
                        Object.assign(dstParam, Array.isArray(result?.result) ? result.result[0] : result.result);
                        resolve(this.computeNextNodes(logicNode, actionContext));
                    } else {
                        LogUtil.warn('调用界面行为异常');
                        return;
                    }
                } catch (error) {
                    LogUtil.warn(`调用界面行为异常,${error}`);
                    return;
                }
            } else {
                LogUtil.warn('调用界面行为参数不足');
                return;
            }
        })
    }
}