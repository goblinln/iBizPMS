import { MDViewInterface } from "./md-view";

/**
 * 实体分页搜索视图基类接口
 *
 * @export
 * @interface TabSearchViewInterface
 * @extends {MDViewInterface}
 */
export interface TabSearchViewInterface extends MDViewInterface {


    /**
     * 分页面板点击
     *
     * @param {*} event
     * @memberof TabSearchViewInterface
     */
    tabPanelClick(event: any): void;
}