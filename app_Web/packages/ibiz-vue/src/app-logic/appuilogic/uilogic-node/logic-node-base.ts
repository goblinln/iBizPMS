import { IPSAppUILogic, IPSDELogicLinkCond, IPSDELogicLinkGroupCond, IPSDELogicLinkSingleCond } from "@ibiz/dynamic-model-api";
import { Util, Verify } from "ibiz-core";
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
     * @param {IPSAppUILogic} logicNode 逻辑节点模型数据
     * @param {UIActionContext} actionContext 界面逻辑上下文
     * @memberof AppUILogicNodeBase
     */
    public computeNextNodes(logicNode: IPSAppUILogic, actionContext: UIActionContext) {
        // let result: any = { nextNodes: [], actionContext };
        // if (logicNode && logicNode.getPSDELogicLinks() && ((logicNode.getPSDELogicLinks() as IPSDELogicLink[]).length > 0)) {
        //     for (let logicLink of (logicNode.getPSDELogicLinks() as IPSDELogicLink[])) {
        //         let nextNode = logicLink.getDstPSDELogicNode();
        //         // 没有连接条件组或有条件组且满足条件组时执行下一个节点
        //         if (!logicLink?.getPSDELogicLinkGroupCond?.() || this.computeCond((logicLink.getPSDELogicLinkGroupCond() as IPSDELogicLinkGroupCond), actionContext)) {
        //             result.nextNodes.push(nextNode);
        //         }
        //     }
        // }
        // return result;
    }

    /**
     * 计算是否通过逻辑连接
     *
     * @param {IPSAppUILogic} logicLinkCond 逻辑节点连接条件
     * @param {UIActionContext} actionContext 界面逻辑上下文
     * 
     * @memberof AppUILogicNodeBase
     */
    public computeCond(logicLinkCond: IPSDELogicLinkCond, actionContext: UIActionContext): any {
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
                switch (logicLinkSingleCond.paramType) {
                    case 'CURTIME':
                        targetValue = Util.dateFormat(new Date(), 'YYYY-MM-DD');
                        break;
                    default:
                        targetValue = logicLinkSingleCond.paramValue;
                }
                return Verify.testCond(dstValue, logicLinkSingleCond.condOP, targetValue)
            }
        }
    }
}