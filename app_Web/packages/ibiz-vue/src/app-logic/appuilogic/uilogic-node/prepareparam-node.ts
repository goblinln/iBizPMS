import { IPSDEUILogicNode, IPSDEUILogicNodeParam, IPSDEUILogicParam } from '@ibiz/dynamic-model-api';
import { AppServiceBase, LogUtil } from 'ibiz-core';
import { UIActionContext } from '../uiaction-context';
import { AppUILogicNodeBase } from './logic-node-base';
/**
 * 准备处理参数节点
 *
 * @export
 * @class AppUILogicPrepareParamNode
 */
export class AppUILogicPrepareParamNode extends AppUILogicNodeBase {

    constructor() {
        super();
    }

    /**
     * 执行节点
     *
     * @param {IPSDEUILogicNode} logicNode 逻辑节点模型数据
     * @param {UIActionContext} actionContext 界面逻辑上下文
     * @memberof AppUILogicPrepareParamNode
     */
    public async executeNode(logicNode: IPSDEUILogicNode, actionContext: UIActionContext) {
        this.setParam(logicNode, actionContext);
        return this.computeNextNodes(logicNode, actionContext);
    }

    /**
     * 设置参数(根据配置把源逻辑参数的值赋给目标逻辑参数)
     *
     * @param {IPSDEUILogicNode} logicNode 逻辑节点模型数据
     * @param {UIActionContext} actionContext 界面逻辑上下文
     * @memberof AppUILogicPrepareParamNode
     */
    public setParam(logicNode: IPSDEUILogicNode, actionContext: UIActionContext) {
        if (!logicNode || !logicNode.getPSDEUILogicNodeParams()) {
            return;
        }
        for (let nodeParam of (logicNode.getPSDEUILogicNodeParams() as IPSDEUILogicNodeParam[])) {
            // 源类型参数和目标逻辑参数缺一跳过不做处理
            if (!nodeParam.getDstPSDEUILogicParam() || !nodeParam.srcValueType) {
                LogUtil.warn(`源类型参数或者目标逻辑参数缺失`);
                continue;
            }
            // 源逻辑参数处理
            let srcParam: any = actionContext.getParam((nodeParam.getSrcPSDEUILogicParam() as IPSDEUILogicParam)?.codeName);
            let srcFieldName = nodeParam.srcFieldName?.toLowerCase?.();
            // 目标逻辑参数处理
            let dstParam: any = actionContext.getParam((nodeParam.getDstPSDEUILogicParam() as IPSDEUILogicParam)?.codeName);
            // 目标逻辑参数属性，有实体拿实体属性codeName,没有就那dstFieldName
            let dstFieldName = nodeParam.dstFieldName?.toLowerCase();
            // 根据srcValueType，对目标逻辑参数的目标属性进行赋值。
            let targetValue = this.computeTargetParam(nodeParam, srcParam, srcFieldName, actionContext);
            if (dstParam && dstFieldName) dstParam[dstFieldName] = targetValue;
        }
    }

    /**
     * 计算目标值
     *
     * @param {IPSDEUILogicNodeParam} nodeParam 节点参数
     * @param {*} srcParam  源数据
     * @param {string} srcFieldName  源属性
     * @param {ActionContext} actionContext  逻辑上下文
     * @memberof AppUILogicPrepareParamNode
     */
    public computeTargetParam(nodeParam: IPSDEUILogicNodeParam, srcParam: any, srcFieldName: string, actionContext: UIActionContext) {
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
     * @param {IPSDEUILogicNodeParam} nodeParam 节点参数
     * @param {ActionContext} actionContext  逻辑上下文
     * @memberof AppUILogicPrepareParamNode
     */
    public computeExpRessionValue(nodeParam: any, actionContext: UIActionContext) {
        let expression: string = nodeParam?.expression;
        let { context,data } = actionContext;
        if (!expression) {
            LogUtil.warn(`表达式不能为空`);
            return;
        }
        try {
            expression = this.translateExpression(expression);
            return eval(expression);
        } catch (error) {
            LogUtil.warn(`表达式计算异常: ${error}`);
            return undefined;
        }
    }

    /**
     * 解析表达式
     *
     * @param {string} expression 表达式
     * @memberof AppUILogicPrepareParamNode
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