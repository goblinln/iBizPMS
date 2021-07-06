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
     * @param {IPSDEThrowExceptionLogic} logicNode
     * @param {ActionContext} actionContext
     * @memberof AppThrowExceptionNode
     */
    public async executeNode(logicNode: IPSDEThrowExceptionLogic, actionContext: ActionContext) {
        actionContext.throwExceptionInfo = {
            exceptionInfo:logicNode.errorInfo
        };
        return this.computeNextNodes(logicNode, actionContext);
    }
}