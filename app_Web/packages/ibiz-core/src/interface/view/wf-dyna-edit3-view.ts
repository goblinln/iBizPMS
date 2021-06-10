
import { MainViewInterface } from "./main-view";

/**
 * 工作流动态编辑视图基类接口
 *
 * @export
 * @interface WFDynaEdit3ViewInterface
 * @extends {MainViewInterface}
 */
export interface WFDynaEdit3ViewInterface extends MainViewInterface {


    /**
     * 获取动态表单模型
     *
     * @return {*}  {Promise<any>}
     * @memberof WFDynaEditViewInterface
     */
    getFormModel(): Promise<any>;


    /**
     * 获取工具栏按钮
     *
     * @param {*} arg 请求参数
     * @return {*}  {Promise<any>}
     * @memberof WFDynaEditViewInterface
     */
    getWFLinkModel(arg: any): Promise<any>;


    /**
     * 动态工具栏点击
     *
     * @param {*} linkItem 点击数据
     * @param {*} $event 事件源
     * @memberof WFDynaEditViewInterface
     */
    dynamic_toolbar_click(linkItem: any, $event: any): void;


    /**
     * 提交工作流辅助功能
     *
     * @param {*} linkItem 点击项
     * @param {*} submitData 提交数据
     * @memberof WFDynaEditViewInterface
     */
    submitWFAddiFeature(linkItem: any, submitData: any):void;


    /**
     * 将待办任务标记为已读
     *
     * @param {*} data 业务数据
     * @memberof WFDynaEditViewInterface
     */
    readTask(data: any):void;
}