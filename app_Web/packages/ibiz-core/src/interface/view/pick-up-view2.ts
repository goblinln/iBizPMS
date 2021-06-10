
import { MainViewInterface } from "./main-view";

/**
 * 实体选择视图(左右关系) 基类接口
 *
 * @interface MainViewInterface
 */
export interface PickUpView2Interface extends MainViewInterface {

    /**
     * 确认
     *
     * @memberof PickUpView2Interface
     */
    onClickOk(): void;


    /**
     * 取消
     *
     * @memberof PickUpView2Interface
     */
    onClickCancel(): void;
}