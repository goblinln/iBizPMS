import { IPSAppDELogic, IPSDELogicNode } from "@ibiz/dynamic-model-api";
import { IContext, IParams } from "../../interface";
import { LogUtil } from "../../utils";
import { AppDeLogicBeginNode } from "./logic-node/begin-node";
import { AppDeLogicDeActionNode } from "./logic-node/deaction-node";
import { AppDeLogicPrepareParamNode } from "./logic-node/prepareparam-node";
import { ActionContext } from "./action-context";

/**
 * 实体处理逻辑执行对象
 *
 * @export
 * @class AppDeLogicService
 */
export class AppDeLogicService{

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
    public async beforeExecute(logic: IPSAppDELogic, context: IContext, params: IParams){
        await logic.fill(true);
        return new ActionContext(logic,context,params)
    }

    /**
     * 执行处理逻辑
     *
     * @param {IPSAppDELogic} logic 处理逻辑模型对象
     * @param {IContext} context 应用上下文参数
     * @param {IParams} params 数据参数
     * @return {*} 
     * @memberof AppDeLogicService
     */
    public async onExecute(logic: IPSAppDELogic, context: IContext, params: IParams){
        let ActionContext = await this.beforeExecute(logic, context, params);
        let startNode = logic.getStartPSDELogicNode();
        if(!startNode){
            LogUtil.warn('没有开始节点');
            return params;
        }
        return this.executeNode(startNode, ActionContext);
    }

    /**
     * 执行处理逻辑节点
     *
     * @param {IPSDELogicNode} logicNode 处理逻辑节点
     * @param {IContext} context
     * @memberof AppDeLogicService
     */
    public async executeNode(logicNode: IPSDELogicNode, actionContext: ActionContext){
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
            default:
                console.log(`${logicNode.logicNodeType}暂未支持`);
        }
        // 有后续节点时继续递归，反之返回值。
        if(result.nextNodes?.length > 0){
            return await this.executeNextNodes(result.nextNodes, actionContext);
        }else{
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
    public async executeNextNodes(nextNodes: IPSDELogicNode[], actionContext: ActionContext){
        let result: any = { actionContext };
        for(let nextNode of nextNodes){
            result = await this.executeNode(nextNode, actionContext);
            if(result.nextNodes?.length > 0){
                result = await this.executeNextNodes(result.nextNodes, result.actionContext);
            }
        }
        return result;
    }

}