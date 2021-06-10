
import { MainViewInterface } from "./main-view";

/**
 * 工作流动态导航表格视图基类接口
 *
 * @export
 * @interface WFDynaEdit3ViewInterface
 * @extends {MainViewInterface}
 */
export interface WFDynaExpGridInterface extends MainViewInterface {

    /**
     * 获取树导航栏数据
     *
     * @return {*}  {Promise<any>}
     * @memberof WFDynaExpGridInterface
     */
    getWFStepModel(): Promise<any>;


    /**
     * 获取工具栏按钮
     *
     * @param {*} data 请求参数
     * @memberof WFDynaExpGridInterface
     */
    getWFLinkModel(data: any):void;


    /**
     * 工具栏点击事件
     *
     * @param {*} linkItem 点击对象
     * @param {*} $event 事件源
     * @memberof WFDynaExpGridInterface
     */
    dynamic_toolbar_click(linkItem: any, $event: any):void;


    /**
     * 左侧树选中节点
     *
     * @param {*} data 选择数据
     * @memberof WFDynaExpGridInterface
     */
    handleNodeClick(data: any):void;
}