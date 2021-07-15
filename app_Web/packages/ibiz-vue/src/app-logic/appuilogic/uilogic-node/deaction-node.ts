import { getDstPSAppDEAction,IPSDEUIDEActionLogic } from '@ibiz/dynamic-model-api';
import { LogUtil } from '../../../../../ibiz-core/src';
import { UIActionContext } from '../uiaction-context';
import { AppUILogicNodeBase } from './logic-node-base';
/**
 * 实体行为调用节点
 *
 * @export
 * @class AppUILogicDeactionNode
 */
export class AppUILogicDeactionNode extends AppUILogicNodeBase {

    constructor() {
        super();
    }

    /**
     * 执行节点
     *
     * @param {IPSDEUIDEActionLogic} logicNode 逻辑节点模型数据
     * @param {UIActionContext} actionContext 界面逻辑上下文
     * @memberof AppUILogicDeactionNode
     */
    public async executeNode(logicNode: IPSDEUIDEActionLogic, actionContext: UIActionContext) {
        const dstEntity = logicNode.getDstPSAppDataEntity();
        const deAction = await getDstPSAppDEAction(logicNode);
        const dstParam = actionContext.defaultParam;
        if (dstEntity && deAction && dstParam) {
            try {
                const service = await ___ibz___.gs.getService(dstEntity.codeName);
                const res = await service[deAction.codeName](actionContext.context, dstParam);
                if (res && res.ok && res.data) {
                    Object.assign(dstParam, res.data);
                    return this.computeNextNodes(logicNode, actionContext);
                } else {
                    LogUtil.warn('调用实体行为异常');
                    return;
                }
            } catch (error) {
                LogUtil.warn(`调用实体行为异常,${JSON.stringify(error)}`);
                return;
            }
        } else {
            LogUtil.warn('调用实体行为参数不足');
            return;
        }
    }
}