import { MainViewInterface } from "./main-view";

/**
 * 数据选择视图基类接口
 *
 * @export
 * @interface PickUpTreeViewInterface
 * @extends {MDViewInterface}
 */
export interface PickUpViewInterface extends MainViewInterface {

    /**
     * 确认
     *
     * @memberof PickUpViewInterface
     */
    onClickOk(): void;


    /**
     * 取消
     *
     * @memberof PickUpViewInterface
     */
    onClickCancel(): void;
}