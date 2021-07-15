import { IPSDEUIActionLogic,getDstPSAppDEUIAction } from '@ibiz/dynamic-model-api';
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
        const { data, context, otherParams } = actionContext;
        const dstEntity = logicNode.getDstPSAppDataEntity();
        const dstUIAction = await getDstPSAppDEUIAction(logicNode);
        if (dstEntity && dstUIAction) {
            try {
                const targetUIService = await UIServiceRegister.getInstance().getService(context, dstEntity.codeName.toLowerCase());
                await targetUIService.loaded();
                await targetUIService.excuteAction(
                    dstUIAction.uIActionTag,
                    [data],
                    context,
                    otherParams?.viewparams,
                    otherParams?.event,
                    otherParams?.control,
                    otherParams?.container,
                    otherParams?.parentDeName,
                );
                return this.computeNextNodes(logicNode, actionContext);
            } catch (error) {
                LogUtil.warn(`调用界面行为异常,${error}`);
                return;
            }
        } else {
            LogUtil.warn('调用界面行为参数不足');
            return;
        }
    }
}