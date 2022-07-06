import { MobMainViewInterface } from "./mob-main-view";

/**
 * 数据选择视图基类接口
 *
 * @export
 * @interface PickUpTreeViewInterface
 * @extends {MDViewInterface}
 */
export interface MobPickupViewInterface extends MobMainViewInterface {

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