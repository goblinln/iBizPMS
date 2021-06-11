
import { MainViewInterface } from "./main-view";

/**
 * 应用实体数据选择视图（分页关系） 基类接口
 *
 * @interface MainViewInterface
 */
export interface PickupView3Interface extends MainViewInterface {


    /**
     * 分页点击
     *
     * @param {*} event
     * @memberof PickupView3Interface
     */
    tabPanelClick(event: any): void;


    /**
     * 确认
     *
     * @memberof PickupView3Interface
     */
    onClickOk(): void;


    /**
     * 取消
     *
     * @memberof PickupView3Interface
     */
    onClickCancel(): void;
}