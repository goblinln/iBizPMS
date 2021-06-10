
import { MainViewInterface } from "./main-view";

/**
 * 工作流操作视图基类接口
 *
 * @export
 * @interface WFActionViewInterface
 * @extends {MainViewInterface}
 */
export interface WFActionViewInterface extends MainViewInterface {

    /**
     * 确认
     *
     * @memberof WFActionViewInterface
     */
    onClickOk(): void;


    /**
     * 取消
     *
     * @memberof WFActionViewInterface
     */
    onClickCancel(): void;

}