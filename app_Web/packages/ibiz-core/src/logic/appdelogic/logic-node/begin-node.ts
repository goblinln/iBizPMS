import { IPSDELogicNode } from '@ibiz/dynamic-model-api';
import { ActionContext } from '../action-context';
import { AppDeLogicNodeBase } from './logic-node-base';
/**
 * 开始类型节点
 *
 * @export
 * @class AppDeLogicBeginNode
 */
export class AppDeLogicBeginNode extends AppDeLogicNodeBase {

    constructor() {
        super();
    }

    /**
     * 执行节点
     *
     * @static
     * @param {IPSDELogicNode} logicNode 逻辑节点
     * @param {ActionContext} actionContext 逻辑上下文
     * @memberof AppDeLogicBeginNode
     */
    public async executeNode(logicNode: IPSDELogicNode, actionContext: ActionContext) {
        return this.computeNextNodes(logicNode, actionContext);
    }
}