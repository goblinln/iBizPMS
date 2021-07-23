import { IPSAppDataEntity, IPSAppDEField, IPSDELogicNode, IPSDELogicNodeParam, IPSDELogicParam } from '@ibiz/dynamic-model-api';
import { AppServiceBase } from '../../../service';
import { LogUtil } from '../../../utils';
import { ActionContext } from '../action-context';
import { AppDeLogicNodeBase } from './logic-node-base';

/**
 * 准备参数节点
 *
 * @export
 * @class AppDeLogicPrepareParamNode
 */
export class AppDeLogicPrepareParamNode extends AppDeLogicNodeBase {

    constructor() {
        super();
    }

    /**
     * 执行节点
     *
     * @static
     * @param {IPSDELogicNode} logicNode 逻辑节点
     * @param {ActionContext} actionContext 逻辑上下文
     * @memberof AppDeLogicPrepareParamNode
     */
    public async executeNode(logicNode: IPSDELogicNode, actionContext: ActionContext) {
        this.setParam(logicNode, actionContext);
        return this.computeNextNodes(logicNode, actionContext);
    }

    /**
     * 设置参数(根据配置把源逻辑参数的值赋给目标逻辑参数)
     *
     * @param {IPSDELogicNode} logicNode 节点模型数据
     * @param {ActionContext} actionContext  逻辑上下文
     * @memberof AppDeLogicPrepareParamNode
     */
    public setParam(logicNode: IPSDELogicNode, actionContext: ActionContext) {
        if (!logicNode || !logicNode.getPSDELogicNodeParams()) {
            return;
        }
        const { context } = actionContext;
        for (let nodeParam of (logicNode.getPSDELogicNodeParams() as IPSDELogicNodeParam[])) {
            // 源类型参数和目标逻辑参数缺一跳过不做处理
            if (!nodeParam.getDstPSDELogicParam() || !nodeParam.srcValueType) {
                LogUtil.warn(`源类型参数或者目标逻辑参数缺失`);
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
            let targetValue = this.computeTargetParam(nodeParam, srcParam, srcFieldName, actionContext);
            if (contextField) context[contextField] = targetValue;
            if (dstParam && dstFieldName) dstParam[dstFieldName] = targetValue;
        }
    }

    /**
     * 计算目标值
     *
     * @param {IPSDELogicNodeParam} nodeParam 节点参数
     * @param {*} srcParam  源数据
     * @param {string} srcFieldName  源属性
     * @param {ActionContext} actionContext  逻辑上下文
     * @memberof AppDeLogicPrepareParamNode
     */
    public computeTargetParam(nodeParam: IPSDELogicNodeParam, srcParam: any, srcFieldName: string, actionContext: ActionContext) {
        let targetValue: any;
        switch (nodeParam.srcValueType) {
            case "SRCDLPARAM":       // 源逻辑参数
            case 'WEBCONTEXT':       // 网页请求上下文
            case 'VIEWPARAM':        // 当前视图参数
                targetValue = srcParam[srcFieldName];
                break;
            case 'APPLICATION':      // 系统全局对象          
            case 'SESSION':          // 用户全局对象 
            case "APPDATA":          // 应用上下文
            case "DATACONTEXT":      // 数据上下文
                const { context } = actionContext;
                targetValue = context[srcFieldName];
                break;
            case 'ENVPARAM':         // 当前环境参数
                const Environment = AppServiceBase.getInstance().getAppEnvironment();
                targetValue = Environment[srcFieldName];
                break;
            case 'EXPRESSION':       // 计算式
                targetValue = this.computeExpRessionValue(nodeParam, actionContext);
                break;
            case "SRCVALUE":         // 直接值
                targetValue = nodeParam?.srcValue;
                break;
            case 'NONEVALUE':        // 无值（NONE）
                targetValue = undefined;
                break;
            case 'NULLVALUE':        // 空值（NULL）
                targetValue = null;
                break;
            default:
                LogUtil.warn(`源值类型${nodeParam.srcValueType}暂未支持`)
        }
        return targetValue;
    }

    /**
     * 计算表达式值
     *
     * @param {IPSDELogicNodeParam} nodeParam 节点参数
     * @param {ActionContext} actionContext  逻辑上下文
     * @memberof AppDeLogicPrepareParamNode
     */
    public computeExpRessionValue(nodeParam: IPSDELogicNodeParam, actionContext: ActionContext) {
        let expression: string = nodeParam.expression;
        let data:any = actionContext.getParam(actionContext.defaultParamName);
        let { context } = actionContext;
        if (!expression) {
            LogUtil.warn(`表达式不能为空`);
            return;
        }
        try {
            expression = this.translateExpression(expression);
            return eval(expression);
        } catch (error) {
            LogUtil.warn(`表达式计算异常`);
            return undefined;
        }
    }

    /**
     * 解析表达式
     *
     * @param {string} expression 表达式
     * @memberof AppDeLogicPrepareParamNode
     */
    public translateExpression(expression: string): string {
        if ((expression.indexOf('${') != -1) && (expression.indexOf('}') != -1)) {
            const start: number = expression.indexOf('${');
            const end: number = expression.indexOf('}');
            const contentStr: string = expression.slice(start + 2, end);
            expression = expression.replace(expression.slice(start, end + 1), `data.${contentStr}`);
            return this.translateExpression(expression);
        }
        return expression;
    }
}