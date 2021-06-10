import { MainControlInterface } from "ibiz-core";

/**
 * 导航基类接口
 *
 * @interface ExpBarControlInterface
 */
export interface ExpBarControlInterface extends MainControlInterface{

    /**
     * 关闭编辑视图
     *
     * @memberof ExpBarControlInterface
     */
    close(): void;

    /**
     *
     *
     * @param {*} [args] 额外参数
     * @memberof ExpBarControlInterface
     */
    refresh(): void;


    /**
     * 选中数据事件
     *
     * @param {any[]} args 选中数据
     * @memberof ExpBarControlInterface
     */
    onSelectionChange(args: any[]): void;


    /**
     * 快速分组值变化
     *
     * @param {*} $event 改变值
     * @memberof ExpBarControlInterface
     */
    quickGroupValueChange($event: any): void;


    /**
     * 工具栏点击
     *
     * @param {*} data
     * @param {*} $event
     * @memberof ExpBarControlInterface
     */
    handleItemClick(data: any, $event: any): void;


    /**
     * split值变化事件
     *
     * @memberof ExpBarControlInterface
     */
    onSplitChange(): void;

    /**
     * 执行搜索
     *
     * @memberof ExpBarControlInterface
     */
    onSearch(): void;
}
