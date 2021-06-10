import { MainViewInterface } from "./main-view";

/**
 * 多数据视图基类接口
 *
 * @interface MainViewInterface
 */
export interface MDViewInterface extends MainViewInterface {

    /**
     * 快速分组值变化
     *
     * @param {*} $event 事件源对象
     * @memberof MDViewInterface
     */
    quickGroupValueChange($event: any): void;


    /**
     * 快速搜索
     *
     * @param {*} $event 事件源对象
     * @memberof MDViewInterface
     */
    onSearch($event: any): void;


    /**
     * 快速搜索栏值变化
     *
     * @param {*} $event 事件源对象
     * @memberof MDViewInterface
     */
    quickFormValueChange($event: any): void;

}