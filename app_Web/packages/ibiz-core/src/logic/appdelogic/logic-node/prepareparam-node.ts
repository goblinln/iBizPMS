import { IPSDELogicNode } from '@ibiz/dynamic-model-api';
import { ActionContext } from '../action-context';
import { AppDeLogicNodeBase } from './logic-node-base';

/**
 * 准备参数节点
 *
 * @export
 * @class AppDeLogicPrepareParamNode
 */
export class AppDeLogicPrepareParamNode extends AppDeLogicNodeBase{

    constructor() { 
        super();
    }
    
    /**
     * 执行节点
     *
     * @static
     * @param {IPSDELogicNode} logicNode
     * @param {DeLogicContext} delogicContext
     * @memberof AppDeLogicPrepareParamNode
     */
    public async executeNode(logicNode: IPSDELogicNode, actionContext: ActionContext){
        this.setParam(logicNode, actionContext);
        return this.computeNextNodes(logicNode, actionContext);
    }
}