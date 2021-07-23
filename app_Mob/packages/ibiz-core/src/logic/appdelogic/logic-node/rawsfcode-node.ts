import { IPSDERawCodeLogic } from '@ibiz/dynamic-model-api';
import { LogUtil } from '../../../utils';
import { ActionContext } from '../action-context';
import { AppDeLogicNodeBase } from './logic-node-base';

/**
 * 直接代码节点
 *
 * @export
 * @class AppRawsfcodeNode
 */
export class AppRawsfcodeNode extends AppDeLogicNodeBase {

    constructor() {
        super();
    }

    /**
     * 执行节点
     *
     * @static
     * @param {IPSDELogicNode} logicNode 逻辑节点
     * @param {ActionContext} actionContext 逻辑上下文
     * @memberof AppRawsfcodeNode
     */
    public async executeNode(logicNode: IPSDERawCodeLogic, actionContext: ActionContext) {
        let { context: context, defaultParam: data } = actionContext;
        if (logicNode && logicNode.code) {
            eval(logicNode.code);
        }else{
            LogUtil.warn('无代码片段');
        }
        return this.computeNextNodes(logicNode, actionContext);
    }
}