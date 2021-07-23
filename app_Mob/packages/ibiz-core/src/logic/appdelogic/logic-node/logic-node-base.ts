import { IPSAppDataEntity, IPSAppDEField, IPSDELogicLink, IPSDELogicLinkCond, IPSDELogicLinkGroupCond, IPSDELogicLinkSingleCond, IPSDELogicNode, IPSDELogicNodeParam, IPSDELogicParam } from "@ibiz/dynamic-model-api";
import { Util, Verify } from "ibiz-core";
import { LogUtil } from "../../../utils";
import { ActionContext } from "../action-context";

/**
 * 处理逻辑节点基类
 *
 * @export
 * @class AppDeLogicNodeBase
 */
export class AppDeLogicNodeBase {

    constructor() { }

    /**
     * 根据处理连接计算后续逻辑节点
     *
     * @param {IPSDELogicNode} logicNode 处理逻辑节点
     * @param {IContext} context
     * @memberof AppDeLogicNodeBase
     */
    public computeNextNodes(logicNode: IPSDELogicNode, actionContext: ActionContext) {
        let result: any = { nextNodes: [], actionContext };
        if (logicNode && logicNode.getPSDELogicLinks() && ((logicNode.getPSDELogicLinks() as IPSDELogicLink[]).length > 0)) {
            for (let logicLink of (logicNode.getPSDELogicLinks() as IPSDELogicLink[])) {
                let nextNode = logicLink.getDstPSDELogicNode();
                // 没有连接条件组或有条件组且满足条件组时执行下一个节点
                if (!logicLink?.getPSDELogicLinkGroupCond?.() || this.computeCond((logicLink.getPSDELogicLinkGroupCond() as IPSDELogicLinkGroupCond), actionContext)) {
                    result.nextNodes.push(nextNode);
                }
            }
        }
        return result;
    }

    /**
     * 计算是否通过逻辑连接
     *
     * @param {IPSDELogicLinkCond} logicLinkCond
     * @return {*} 
     * @memberof AppDeLogicNodeBase
     */
    public computeCond(logicLinkCond: IPSDELogicLinkCond, actionContext: ActionContext): any {
        if (logicLinkCond.logicType == 'GROUP') {
            const logicLinkGroupCond = logicLinkCond as IPSDELogicLinkGroupCond;
            const childConds: any = logicLinkGroupCond.getPSDELogicLinkConds();
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
                const logicLinkSingleCond = logicLinkCond as IPSDELogicLinkSingleCond
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