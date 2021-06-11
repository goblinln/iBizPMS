import { MainViewInterface } from "./main-view";

/**
 * 数据选择视图基类接口
 *
 * @export
 * @interface PickUpTreeViewInterface
 * @extends {MDViewInterface}
 */
export interface PickupViewInterface extends MainViewInterface {

    /**
     * 确认
     *
     * @memberof PickupViewInterface
     */
    onClickOk(): void;


    /**
     * 取消
     *
     * @memberof PickupViewInterface
     */
    onClickCancel(): void;
}