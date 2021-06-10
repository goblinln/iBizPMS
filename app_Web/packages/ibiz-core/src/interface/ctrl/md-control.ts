import { MainControlInterface } from 'ibiz-core';
/**
 * 实体部件基类接口
 *
 * @interface MDControlInterface
 */
export interface MDControlInterface extends MainControlInterface{

    /**
     * 部件工具栏点击
     *
     * @param ctrl 部件
     * @param data 工具栏回传数据
     * @param $event 事件源对象
     * @memberof MDControlInterface
     */
    handleItemClick(ctrl: string, data: any, $event: any): void;
}
