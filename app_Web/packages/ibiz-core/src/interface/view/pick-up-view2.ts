
import { MainViewInterface } from "./main-view";

/**
 * 实体选择视图(左右关系) 基类接口
 *
 * @interface MainViewInterface
 */
export interface PickupView2Interface extends MainViewInterface {

    /**
     * 确认
     *
     * @memberof PickupView2Interface
     */
    onClickOk(): void;


    /**
     * 取消
     *
     * @memberof PickupView2Interface
     */
    onClickCancel(): void;
}