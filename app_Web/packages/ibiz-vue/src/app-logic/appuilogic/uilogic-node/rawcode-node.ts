import { IPSDEUIRawCodeLogic } from '@ibiz/dynamic-model-api';
import { LogUtil } from 'ibiz-core';
import { UIActionContext } from '../uiaction-context';
import { AppUILogicNodeBase } from './logic-node-base';
/**
 * 直接前台代码节点
 *
 * @export
 * @class AppUILogicRawCodeNode
 */
export class AppUILogicRawCodeNode extends AppUILogicNodeBase {

    constructor() {
        super();
    }

    /**
     * 执行节点
     *
     * @param {IPSDEUIRawCodeLogic} logicNode 逻辑节点模型数据
     * @param {UIActionContext} actionContext 界面逻辑上下文
     * @memberof AppUILogicRawCodeNode
     */
    public async executeNode(logicNode: IPSDEUIRawCodeLogic, actionContext: UIActionContext) {
        let { context, data } = actionContext;
        if (logicNode && logicNode.code) {
            eval(logicNode?.code);
        }else{
            LogUtil.warn('无代码片段');
        }
        return this.computeNextNodes(logicNode, actionContext);
    }
}