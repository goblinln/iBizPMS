import { MobMainControlInterface } from "ibiz-core";

/**
 * 导航基类接口
 *
 * @interface MobExpBarControlInterface
 */
export interface MobExpBarControlInterface extends MobMainControlInterface{

    /**
     * 刷新
     *
     * @param {*} [args] 额外参数
     * @memberof MobExpBarControlInterface
     */
    refresh(): void;


    /**
     * 选中数据事件
     *
     * @param {any[]} args 选中数据
     * @memberof MobExpBarControlInterface
     */
    onSelectionChange(args: any[]): void;


    /**
     * 快速分组值变化
     *
     * @param {*} $event 改变值
     * @memberof MobExpBarControlInterface
     */
    quickGroupValueChange($event: any): void;


    /**
     * 工具栏点击
     *
     * @param {*} data
     * @param {*} $event
     * @memberof MobExpBarControlInterface
     */
    handleItemClick(data: any, $event: any): void;

    /**
     * 执行搜索
     *
     * @memberof MobExpBarControlInterface
     */
    onSearch(): void;
}
