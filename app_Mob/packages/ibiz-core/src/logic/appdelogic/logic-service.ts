import { IPSAppDELogic, IPSDELogicNode } from "@ibiz/dynamic-model-api";
import { IContext, IParams } from "../../interface";
import { LogUtil } from "../../utils";
import { AppDeLogicBeginNode } from "./logic-node/begin-node";
import { AppDeLogicDeActionNode } from "./logic-node/deaction-node";
import { AppDeLogicPrepareParamNode } from "./logic-node/prepareparam-node";
import { AppRawsfcodeNode } from "./logic-node/rawsfcode-node";
import { AppThrowExceptionNode } from "./logic-node/throwexception-node";
import { ActionContext } from "./action-context";

/**
 * 实体处理逻辑执行对象
 *
 * @export
 * @class AppDeLogicService
 */
export class AppDeLogicService {

    /**
     * 唯一实例
     * 
     * @private
     * @static
     * @memberof AppDeLogicService
     */
    private static readonly instance = new AppDeLogicService();

    /**
     * 获取唯一实例
     *
     * @static
     * @return {*}  {AppDeLogicService}
     * @memberof AppDeLogicService
     */
    public static getInstance(): AppDeLogicService {
        return AppDeLogicService.instance;
    }

    /**
     * 执行之前的初始化操作
     *
     * @param {IPSAppDELogic} logic 处理逻辑模型对象
     * @param {IParams} params
     * @memberof AppDeLogicService
     */
    public async beforeExecute(logic: IPSAppDELogic, context: IContext, params: IParams) {
        await logic.fill(true);
        return new ActionContext(logic, context, params)
    }

    /**
     * 执行处理逻辑
     *
     * @param {IPSAppDELogic} logic 处理逻辑模型对象
     * @param {IContext} context 应用上下文参数
     * @param {IParams} data 数据参数
     * @return {*} 
     * @memberof AppDeLogicService
     */
    public async onExecute(logic: IPSAppDELogic, context: IContext, data: IParams) {
        let actionContext = await this.beforeExecute(logic, context, data);
        // 自定义脚本代码
        if (logic && logic.customCode) {
            if (logic.scriptCode) {
                if (logic && (logic as any)?.logicSubType && Object.is((logic as any).logicSubType, 'DEFIELD')) {
                    // 适配计算值和默认值类型直接输入值
                    if ((logic as any)?.dEFLogicMode && (Object.is((logic as any).dEFLogicMode, 'COMPUTE') || Object.is((logic as any).dEFLogicMode, 'DEFAULT'))) {
                        const result: any = eval(logic.scriptCode);
                        if ((result !== null) && (result !== undefined)) {
                            if (logic.M?.getPSAppDEField?.codeName) {
                                data[logic.M.getPSAppDEField.codeName.toLowerCase()] = result;
                            }
                        }
                    } else {
                        eval(logic.scriptCode);
                    }
                } else {
                    eval(logic.scriptCode);
                }
                return data;
            } else {
                LogUtil.warn('自定义代码不能为空');
            }
        } else {
            let startNode: IPSDELogicNode | null = logic.getStartPSDELogicNode();
            if (!startNode) {
                LogUtil.warn('没有开始节点');
                return data;
            }
            return this.executeNode(startNode, actionContext);
        }
    }

    /**
     * 执行处理逻辑节点
     *
     * @param {*} logicNode 处理逻辑节点
     * @param {IContext} context
     * @memberof AppDeLogicService
     */
    public async executeNode(logicNode: any, actionContext: ActionContext) {
        let result: any = { actionContext };
        switch (logicNode.logicNodeType) {
            // 开始节点
            case 'BEGIN':
                result = await new AppDeLogicBeginNode().executeNode(logicNode, actionContext);
                break;
            // 准备参数节点
            case 'PREPAREPARAM':
                result = await new AppDeLogicPrepareParamNode().executeNode(logicNode, actionContext);
                break;
            // 行为处理节点
            case 'DEACTION':
                result = await new AppDeLogicDeActionNode().executeNode(logicNode, actionContext);
                break;
            // 直接代码
            case 'RAWSFCODE':
                result = await new AppRawsfcodeNode().executeNode(logicNode, actionContext);
                break;
            // 抛出异常
            case 'THROWEXCEPTION':
                result = await new AppThrowExceptionNode().executeNode(logicNode, actionContext);
                break;
            default:
                console.log(`${logicNode.logicNodeType}暂未支持`);
        }
        // 有后续节点时继续递归，反之返回值。
        if (result && (result.nextNodes?.length > 0)) {
            return await this.executeNextNodes(result.nextNodes, actionContext);
        } else {
            const { defaultParam } = result.actionContext;
            return defaultParam;
        }
    }

    /**
     * 执行后续节点集合
     *
     * @param {IPSDELogicNode[]} nextNodes
     * @param {ActionContext} ActionContext
     * @memberof AppDeLogicService
     */
    public async executeNextNodes(nextNodes: IPSDELogicNode[], actionContext: ActionContext) {
        let result: any = { actionContext };
        for (let nextNode of nextNodes) {
            result = await this.executeNode(nextNode, actionContext);
            if (result.nextNodes?.length > 0) {
                result = await this.executeNextNodes(result.nextNodes, result.actionContext);
            }
        }
        return result;
    }

}