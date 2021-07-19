import { Util, Verify } from "ibiz-core";
import { IPSDEUILogicLink, IPSDEUILogicLinkCond, IPSDEUILogicLinkGroupCond, IPSDEUILogicLinkSingleCond } from "@ibiz/dynamic-model-api";
import { UIActionContext } from "../uiaction-context";

/**
 * 处理逻辑节点基类
 *
 * @export
 * @class AppUILogicNodeBase
 */
export class AppUILogicNodeBase {

    constructor() { }

    /**
     * 根据处理连接计算后续逻辑节点
     *
     * @param {*} logicNode 逻辑节点模型数据
     * @param {UIActionContext} actionContext 界面逻辑上下文
     * @memberof AppUILogicNodeBase
     */
    public computeNextNodes(logicNode: any, actionContext: UIActionContext) {
        let result: any = { nextNodes: [], actionContext };
        if (logicNode && logicNode.getPSDEUILogicLinks() && ((logicNode.getPSDEUILogicLinks() as IPSDEUILogicLink[]).length > 0)) {
            for (let logicLink of (logicNode.getPSDEUILogicLinks() as IPSDEUILogicLink[])) {
                let nextNode = logicLink.getDstPSDEUILogicNode();
                // 没有连接条件组或有条件组且满足条件组时执行下一个节点
                if (!logicLink?.getPSDEUILogicLinkGroupCond?.() || this.computeCond((logicLink.getPSDEUILogicLinkGroupCond() as IPSDEUILogicLinkGroupCond), actionContext)) {
                    result.nextNodes.push(nextNode);
                }
            }
        }
        return result;
    }

    /**
     * 计算是否通过逻辑连接
     *
     * @param {IPSDEUILogicLinkCond} logicLinkCond 逻辑节点连接条件
     * @param {UIActionContext} actionContext 界面逻辑上下文
     * 
     * @memberof AppUILogicNodeBase
     */
    public computeCond(logicLinkCond: IPSDEUILogicLinkCond, actionContext: UIActionContext): any {
        if (logicLinkCond.logicType == 'GROUP') {
            const logicLinkGroupCond = logicLinkCond as IPSDEUILogicLinkGroupCond;
            const childConds: any = logicLinkGroupCond.getPSDEUILogicLinkConds();
            if (childConds?.length > 0) {
                return Verify.logicForEach(
                    childConds,
                    (item: any) => {
                        return this.computeCond(item, actionContext);
                    },
                    logicLinkGroupCond.groupOP,
                    !!logicLinkGroupCond.notMode,
                );
            }
        } else {
            if (logicLinkCond.logicType == 'SINGLE') {
                const logicLinkSingleCond = logicLinkCond as IPSDEUILogicLinkSingleCond;
                let dstLogicParam = actionContext.getParam(logicLinkSingleCond?.getDstLogicParam?.()?.codeName || actionContext.defaultParamName)
                let dstValue = dstLogicParam[logicLinkSingleCond.dstFieldName.toLowerCase()]
                let targetValue;
                if(logicLinkSingleCond.paramType){
                    switch (logicLinkSingleCond.paramType) {
                        case 'CURTIME':
                            targetValue = Util.dateFormat(new Date(), 'YYYY-MM-DD');
                            break;
                        default:
                            targetValue = logicLinkSingleCond.paramValue;
                    }
                }else{
                    targetValue = logicLinkSingleCond.paramValue;
                }
                return Verify.testCond(dstValue, logicLinkSingleCond.condOP, targetValue)
            }
        }
    }
}