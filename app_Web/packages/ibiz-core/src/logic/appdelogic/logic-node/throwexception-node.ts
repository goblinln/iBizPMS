import { IPSDEThrowExceptionLogic } from '@ibiz/dynamic-model-api';
import { ActionContext } from '../action-context';
import { AppDeLogicNodeBase } from './logic-node-base';

/**
 * 抛出异常节点
 *
 * @export
 * @class AppThrowExceptionNode
 */
export class AppThrowExceptionNode extends AppDeLogicNodeBase {

    constructor() {
        super();
    }

    /**
     * 执行节点
     *
     * @static
     * @param {IPSDELogicNode} logicNode 逻辑节点
     * @param {ActionContext} actionContext 逻辑上下文
     * @memberof AppThrowExceptionNode
     */
    public async executeNode(logicNode: IPSDEThrowExceptionLogic, actionContext: ActionContext) {
        throw new Error(logicNode.errorInfo);
    }
}