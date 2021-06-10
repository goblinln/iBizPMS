
import { MainViewInterface } from "./main-view";

/**
 *
 * 实体数据重定向视图接口
 * @export
 * @interface DeRedirectViewInterface
 * @extends {MainViewInterface}
 */
export interface RedirectViewInterface extends MainViewInterface {


    /**
     * 执行重定向逻辑
     *
     * @memberof RedirectViewInterface
     */
    executeRedirectLogic():void;

    /**
     * 关闭当前重定向视图
     *
     * @param {Array<any>} args 额外参数
     * @memberof RedirectViewInterface
     */
    closeRedirectView(args: Array<any>):void;

}