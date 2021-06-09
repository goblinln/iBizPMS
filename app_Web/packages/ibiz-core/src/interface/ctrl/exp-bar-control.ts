/**
 * 导航基类接口
 *
 * @interface EepBarControlInterface
 */
export interface ExpBarControlInterface {

    /**
     * 关闭编辑视图
     *
     * @memberof EepBarControlInterface
     */
    close(): void;

    /**
     *
     *
     * @param {*} [args] 额外参数
     * @memberof EepBarControlInterface
     */
    refresh(): void;


    /**
     * 选中数据事件
     *
     * @param {any[]} args 选中数据
     * @memberof EepBarControlInterface
     */
    onSelectionChange(args: any[]): void;


    /**
     * 快速分组值变化
     *
     * @param {*} $event 改变值
     * @memberof EepBarControlInterface
     */
    quickGroupValueChange($event: any): void;


    /**
     * 工具栏点击
     *
     * @param {*} data
     * @param {*} $event
     * @memberof EepBarControlInterface
     */
    handleItemClick(data: any, $event: any): void;


    /**
     * split值变化事件
     *
     * @memberof EepBarControlInterface
     */
    onSplitChange(): void;

    /**
     * 执行搜索
     *
     * @memberof EepBarControlInterface
     */
    onSearch(): void;
}
