
import { MainViewInterface } from "./main-view";

/**
 * 选项操作视图基类接口
 *
 * @export
 * @interface OptViewInterface
 * @extends {MainViewInterface}
 */
export interface OptViewInterface extends MainViewInterface {


    /**
     * 确定
     *
     * @memberof OptViewInterface
     */
    onClickOk(): void;


    /**
     * 取消
     *
     * @memberof OptViewInterface
     */
    onClickCancel(): void;
}