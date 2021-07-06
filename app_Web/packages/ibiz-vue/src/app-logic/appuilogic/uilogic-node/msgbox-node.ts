import { IPSAppUILogic } from '@ibiz/dynamic-model-api';
import { UIActionContext } from '../uiaction-context';
import { AppUILogicNodeBase } from './logic-node-base';
/**
 * 消息弹窗节点
 *
 * @export
 * @class AppUILogicMsgboxNode
 */
export class AppUILogicMsgboxNode extends AppUILogicNodeBase {

    constructor() {
        super();
    }

    /**
     * 执行节点
     *
     * @param {IPSAppUILogic} logicNode 逻辑节点模型数据
     * @param {UIActionContext} actionContext 界面逻辑上下文
     * @memberof AppUILogicMsgboxNode
     */
    public async executeNode(logicNode: IPSAppUILogic, actionContext: UIActionContext) {
        // TODO
        return this.computeNextNodes(logicNode, actionContext);
    }
}