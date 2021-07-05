import { IPSDERawCodeLogic } from '@ibiz/dynamic-model-api';
import { ActionContext } from '../action-context';
import { AppDeLogicNodeBase } from './logic-node-base';

/**
 * 直接代码节点
 *
 * @export
 * @class AppRawsfcodeNode
 */
export class AppRawsfcodeNode extends AppDeLogicNodeBase{

    constructor() { 
        super();
    }
    
    /**
     * 执行节点
     *
     * @static
     * @param {IPSDERawCodeLogic} logicNode
     * @param {ActionContext} actionContext
     * @memberof AppRawsfcodeNode
     */
    public async executeNode(logicNode: IPSDERawCodeLogic, actionContext: ActionContext){
        this.setParam(logicNode, actionContext);
        if(logicNode && logicNode.code){
            eval(logicNode.code);
        }
        return this.computeNextNodes(logicNode, actionContext);
    }
}