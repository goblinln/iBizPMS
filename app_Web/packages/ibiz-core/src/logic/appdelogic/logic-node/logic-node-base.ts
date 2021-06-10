import { IPSAppDataEntity, IPSAppDEField, IPSDELogicLink, IPSDELogicLinkCond, IPSDELogicLinkGroupCond, IPSDELogicLinkSingleCond, IPSDELogicNode, IPSDELogicNodeParam, IPSDELogicParam } from "@ibiz/dynamic-model-api";
import { Util, Verify } from "ibiz-core";
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
     * 设置参数(根据配置把源逻辑参数的值赋给目标逻辑参数)
     *
     * @static
     * @param {IPSDELogicNode} logicNode
     * @param {DeLogicContext} actionContext
     * @memberof AppDeLogicNodeBase
     */
    public setParam(logicNode: IPSDELogicNode, actionContext: ActionContext) {
        const { context } = actionContext;
        if (!logicNode || !logicNode.getPSDELogicNodeParams()) {
            return;
        }
        for (let nodeParam of (logicNode.getPSDELogicNodeParams() as IPSDELogicNodeParam[])) {
            // 缺少目标逻辑参数，目标逻辑参数属性名，源值类型任意一个时不赋值
            if (!(nodeParam.getDstPSDELogicParam() && nodeParam.dstFieldName && nodeParam.srcValueType)) {
                continue;
            }
            // 源逻辑参数
            let srcParam: any = actionContext.getParam((nodeParam.getSrcPSDELogicParam() as IPSDELogicParam).codeName);
            // 元逻辑参数属性,有实体拿实体属性codeName,没有就那srcFieldName
            let srcFieldName = nodeParam.srcFieldName?.toLowerCase?.();
            if (nodeParam.srcFieldName) {
                let srcAppDataEntity: IPSAppDataEntity = (nodeParam.getSrcPSDELogicParam() as any)?.getParamPSAppDataEntity?.();
                let deField = (srcAppDataEntity?.getAllPSAppDEFields() as IPSAppDEField[]).find((item: IPSAppDEField) => nodeParam.srcFieldName.toLocaleLowerCase() == item.codeName.toLowerCase())
                if (deField) srcFieldName = deField.codeName?.toLowerCase();
            }

            // 目标逻辑参数
            let dstParam: any = actionContext.getParam((nodeParam.getDstPSDELogicParam() as IPSDELogicParam).codeName);
            // 目标逻辑参数属性，有实体拿实体属性codeName,没有就那dstFieldName
            let dstFieldName = nodeParam.dstFieldName?.toLowerCase();
            let contextField = undefined;
            if (nodeParam.dstFieldName) {
                let dstAppDataEntity: IPSAppDataEntity = (nodeParam.getDstPSDELogicParam() as any)?.getParamPSAppDataEntity?.();
                let deField = (dstAppDataEntity?.getAllPSAppDEFields() as IPSAppDEField[]).find((item: IPSAppDEField) => nodeParam.dstFieldName == item.codeName)
                if (deField) {
                    dstFieldName = deField.codeName?.toLowerCase();
                    contextField = deField.keyField ? dstAppDataEntity.codeName.toLowerCase() : undefined;
                }
            }

            // 根据srcValueType，对目标逻辑参数的目标属性进行赋值。
            let finalValue = undefined;
            switch (nodeParam.srcValueType) {
                // 源逻辑参数
                case "SRCDLPARAM":
                    finalValue = srcParam[srcFieldName];
                    break;
                // 应用上下文 
                case "APPDATA":
                    finalValue = context[srcFieldName];
                    break;
                // 数据上下文
                case "DATACONTEXT":
                    finalValue = context[srcFieldName];
                    break;
                // 直接值
                case "SRCVALUE":
                    finalValue = srcParam[srcFieldName];
                    break;
                default:
                    console.error(`源值类型${nodeParam.srcValueType}暂未支持`)
            }
            if (finalValue) {
                if (contextField) context[contextField] = finalValue;
                if (dstParam && dstFieldName) dstParam[dstFieldName] = finalValue;
            }
        }
    }

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
                let testValue;
                switch (logicLinkSingleCond.paramType) {
                    case 'CURTIME':
                        testValue = Util.dateFormat(new Date(), 'YYYY-MM-DD');
                        break;
                    default:
                        testValue = logicLinkSingleCond.paramValue;
                }
                return Verify.testCond(dstValue, logicLinkSingleCond.condOP, testValue)
            }
        }
    }
}