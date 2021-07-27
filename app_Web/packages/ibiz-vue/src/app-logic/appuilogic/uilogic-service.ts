import { LogUtil } from "ibiz-core";
import { UIActionContext } from "./uiaction-context";
import { AppUILogicBeginNode } from "./uilogic-node/begin-node";
import { AppUILogicDeactionNode } from "./uilogic-node/deaction-node";
import { AppUILogicDeUIActionNode } from "./uilogic-node/deuiaction-node";
import { AppUILogicMsgboxNode } from "./uilogic-node/msgbox-node";
import { AppUILogicPluginNode } from "./uilogic-node/plugin-node";
import { AppUILogicPrepareParamNode } from "./uilogic-node/prepareparam-node";
import { AppUILogicRawCodeNode } from "./uilogic-node/rawcode-node";
import { AppUILogicViewctrlInvokeNode } from "./uilogic-node/viewctrl-invoke-node";

/**
 * 界面逻辑执行对象
 *
 * @export
 * @class AppUILogicService
 */
export class AppUILogicService {

     /**
      * 唯一实例
      * 
      * @private
      * @static
      * @memberof AppUILogicService
      */
     private static readonly instance = new AppUILogicService();

     /**
      * 获取唯一实例
      *
      * @static
      * @return {*}  {AppUILogicService}
      * @memberof AppUILogicService
      */
     public static getInstance(): AppUILogicService {
          return AppUILogicService.instance;
     }

     /**
      * 执行之前的初始化操作
      * 
      * @param {IPSAppDELogic} logic 处理逻辑模型对象
      * @param {any[]} args 数据对象
      * @param {*} context 应用上下文
      * @param {*} params 视图参数
      * @param {*} $event 事件源对象
      * @param {*} xData 部件对象
      * @param {*} actioncontext 界面容器对象
      * @param {*} srfParentDeName 关联父应用实体代码名称
      * @memberof AppUILogicService
      */
     public async beforeExecute(logic: any, args: any[], context: any = {}, params: any = {},
          $event?: any, xData?: any, actioncontext?: any, srfParentDeName?: string) {
          await logic.fill(true);
          return new UIActionContext(logic, args, context, params, $event, xData, actioncontext, srfParentDeName)
     }

     /**
      * 执行处理逻辑
      *
      * @param {*} logic 处理逻辑模型对象
      * @param {any[]} args 数据对象
      * @param {*} context 应用上下文
      * @param {*} params 视图参数
      * @param {*} $event 事件源对象
      * @param {*} xData 部件对象
      * @param {*} actioncontext 界面容器对象
      * @param {*} srfParentDeName 关联父应用实体代码名称
      * @memberof AppUILogicService
      */
     public async onExecute(logic: any, args: any[], context: any = {}, params: any = {},
          $event?: any, xData?: any, actioncontext?: any, srfParentDeName?: string) {
          let actionContext = await this.beforeExecute(logic, args, context, params, $event, xData, actioncontext, srfParentDeName);
          let startNode: any | null = logic.getStartPSDEUILogicNode();
          if (!startNode) {
               LogUtil.warn('没有开始节点');
               return;
          }
          return await this.executeNode(startNode, actionContext);
     }

     /**
      * 执行界面处理逻辑节点
      *
      * @param {*} logicNode 界面处理逻辑节点
      * @param {UIActionContext} actionContext 界面逻辑执行上下文 
      * @memberof AppUILogicService
      */
     public async executeNode(logicNode: any, actionContext: UIActionContext) {
          let result: any = { actionContext };
          switch (logicNode.logicNodeType) {
               // 开始节点
               case 'BEGIN':
                    result = await new AppUILogicBeginNode().executeNode(logicNode, actionContext);
                    break;
               // 准备参数节点
               case 'PREPAREJSPARAM':
                    result = await new AppUILogicPrepareParamNode().executeNode(logicNode, actionContext);
                    break;
               // 调用实体界面行为
               case 'DEUIACTION':
                    result = await new AppUILogicDeUIActionNode().executeNode(logicNode, actionContext);
                    break;
               // 行为处理节点
               case 'DEACTION':
                    result = await new AppUILogicDeactionNode().executeNode(logicNode, actionContext);
                    break;
               // 视图部件调用
               // case 'VIEWCTRLINVOKE':
               //      result = await new AppUILogicViewctrlInvokeNode().executeNode(logicNode, actionContext);
               //      break;
               // 消息弹窗
               case 'MSGBOX':
                    result = await new AppUILogicMsgboxNode().executeNode(logicNode, actionContext);
                    break;
               // 前端代码
               case 'RAWJSCODE':
                    result = await new AppUILogicRawCodeNode().executeNode(logicNode, actionContext);
                    break;
               // 前端扩展插件
               // case 'PFPLUGIN':
               //      result = await new AppUILogicPluginNode().executeNode(logicNode, actionContext);
               //      break;
               default:
                    console.log(`${logicNode.logicNodeType}暂未支持`);
          }
          // 有后续节点时继续递归，反之返回
          if (result && result.nextNodes && (result.nextNodes?.length > 0)) {
               await this.executeNextNodes(result.nextNodes, actionContext);
          }
     }

     /**
      * 执行后续节点集合
       *
       * @param {*} logicNode 界面处理逻辑节点
       * @param {UIActionContext} actionContext 界面逻辑执行上下文 
       * @memberof AppUILogicService
       */
     public async executeNextNodes(nextNodes: any[], actionContext: UIActionContext) {
          let result: any = actionContext.data;
          if (nextNodes && (nextNodes.length > 0)) {
               for (let nextNode of nextNodes) {
                    result = await this.executeNode(nextNode, actionContext);
                    if (result && result?.nextNodes?.length > 0) {
                         result = await this.executeNextNodes(result.nextNodes, result.actionContext);
                    }
               }
          }
          return result;
     }

}