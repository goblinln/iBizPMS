import { IPSAppUILogic } from "@ibiz/dynamic-model-api";
import { UIActionContext } from "./uiaction-context";

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
     public async beforeExecute(logic: IPSAppUILogic, args: any[], context: any = {}, params: any = {},
          $event?: any, xData?: any, actioncontext?: any, srfParentDeName?: string) {
          await logic.fill(true);
          return new UIActionContext(logic, args, context, params, $event, xData, actioncontext, srfParentDeName)
     }

     /**
      * 执行处理逻辑
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
     public async onExecute(logic: IPSAppUILogic, args: any[], context: any = {}, params: any = {},
          $event?: any, xData?: any, actioncontext?: any, srfParentDeName?: string) {
          let actionContext = await this.beforeExecute(logic, args, context, params, $event, xData, actioncontext, srfParentDeName);
          console.log(actionContext);
          // let startNode: IPSDELogicNode | null = logic.getStartPSDELogicNode();
          // if (!startNode) {
          //     LogUtil.warn('没有开始节点');
          //     return params;
          // }
          // return this.executeNode(startNode, ActionContext);
     }


}