import { IPSDEUICtrlInvokeLogic } from '@ibiz/dynamic-model-api';
import { UIActionContext } from '../uiaction-context';
import { AppUILogicNodeBase } from './logic-node-base';
/**
 * 视图部件调用节点
 *
 * @export
 * @class AppUILogicViewctrlInvokeNode
 */
export class AppUILogicViewctrlInvokeNode extends AppUILogicNodeBase {

    constructor() {
        super();
    }

    /**
     * 执行节点
     *
     * @param {IPSDEUICtrlInvokeLogic} logicNode 逻辑节点模型数据
     * @param {UIActionContext} actionContext 界面逻辑上下文
     * @memberof AppUILogicViewctrlInvokeNode
     */
    public async executeNode(logicNode: IPSDEUICtrlInvokeLogic, actionContext: UIActionContext) {
        // TODO
        return this.computeNextNodes(logicNode, actionContext);
    }
}