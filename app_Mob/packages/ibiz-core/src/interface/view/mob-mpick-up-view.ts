import { MobMainViewInterface } from "./mob-main-view";

/**
 * 多数据选择视图基类接口
 *
 * @export
 * @interface PickUpTreeViewInterface
 * @extends {MDViewInterface}
 */
export interface MobMPickUpViewInterface extends MobMainViewInterface {


    /**
     * 选中数据单击 todo
     *
     * @param {*} item 选中数据
     * @memberof MPickUpViewInterface
     */
    // selectionsClick(item: any): void;


    /**
     * 选中数据双击
     *
     * @param {*} item 选中数据
     * @memberof MPickUpViewInterface
     */
    // selectionsDBLClick(item: any): void;


    /**
     * 删除右侧全部选中数据
     *
     * @memberof MPickUpViewInterface
     */
    // onCLickLeft(): void;


    /**
     * 添加左侧选中数据
     *
     * @memberof MPickUpViewInterface
     */
    // onCLickRight(): void;


    /**
     * 选中数据全部删除
     *
     * @memberof MPickUpViewInterface
     */
    // onCLickAllLeft(): void;


    /**
     * 添加左侧面板所有数据到右侧
     *
     * @memberof MPickUpViewInterface
     */
    // onCLickAllRight(): void;


    /**
     * 确认
     *
     * @memberof MPickUpViewInterface
     */
    onClickOk(): void;


    /**
     * 取消
     *
     * @memberof MPickUpViewInterface
     */
    onClickCancel(): void;
}