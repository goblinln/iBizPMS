import { IPSAppDataEntity, IPSAppDEField, IPSDELogicNode, IPSDELogicNodeParam, IPSDELogicParam } from '@ibiz/dynamic-model-api';
import { LogUtil } from '../../../utils';
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

    /**
     * 设置参数(根据配置把源逻辑参数的值赋给目标逻辑参数)
     *
     * @static
     * @param {IPSDELogicNode} logicNode
     * @param {DeLogicContext} actionContext
     * @memberof AppDeLogicPrepareParamNode
     */
     public setParam(logicNode: IPSDELogicNode, actionContext: ActionContext) {
        const { context } = actionContext;
        if (!logicNode || !logicNode.getPSDELogicNodeParams()) {
            return;
        }
        for (let nodeParam of (logicNode.getPSDELogicNodeParams() as IPSDELogicNodeParam[])) {
            // 源逻辑参数和目标逻辑参数缺一跳过不做处理
            if (!nodeParam.getDstPSDELogicParam() || !nodeParam.getSrcPSDELogicParam()) {
                LogUtil.warn(`源逻辑参数和目标逻辑参数缺少则跳过不做处理,[源逻辑参数]:${nodeParam.getSrcPSDELogicParam()},[目标逻辑参数]:${nodeParam.getDstPSDELogicParam()}`);
                continue;
            }
            // 源逻辑参数处理
            let srcParam: any = actionContext.getParam((nodeParam.getSrcPSDELogicParam() as IPSDELogicParam)?.codeName);
            let srcFieldName = nodeParam.srcFieldName?.toLowerCase?.();
            // 目标逻辑参数处理
            let dstParam: any = actionContext.getParam((nodeParam.getDstPSDELogicParam() as IPSDELogicParam)?.codeName);
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
                    LogUtil.warn(`源值类型${nodeParam.srcValueType}暂未支持`)
            }
            if (finalValue) {
                if (contextField) context[contextField] = finalValue;
                if (dstParam && dstFieldName) dstParam[dstFieldName] = finalValue;
            }
        }
    }
}