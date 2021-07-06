import { IPSAppUILogic } from '@ibiz/dynamic-model-api';
import { UIActionContext } from '../uiaction-context';
import { AppUILogicNodeBase } from './logic-node-base';
/**
 * 前端扩展插件调用节点
 *
 * @export
 * @class AppUILogicPluginNode
 */
export class AppUILogicPluginNode extends AppUILogicNodeBase {

    constructor() {
        super();
    }

    /**
     * 执行节点
     *
     * @param {IPSAppUILogic} logicNode 逻辑节点模型数据
     * @param {UIActionContext} actionContext 界面逻辑上下文
     * @memberof AppUILogicPluginNode
     */
    public async executeNode(logicNode: IPSAppUILogic, actionContext: UIActionContext) {
        let { context: context, data: data } = actionContext;
        // todo
        return this.computeNextNodes(logicNode, actionContext);
    }
}