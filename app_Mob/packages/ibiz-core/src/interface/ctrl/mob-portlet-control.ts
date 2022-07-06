import { MobMainControlInterface } from 'ibiz-core';


/**
 * 菜单基类接口
 *
 * @interface MobPortletControlInterface
 */
export interface MobPortletControlInterface extends MobMainControlInterface{

    /**
     * 触发界面行为
     *
     * @param {*} data 触发数据源
     * @param {*} $event 事件对象
     * @memberof MobPortletControlInterface
     */
    handleItemClick(data: any, $event: any): void;


    /**
     * 刷新
     *
     * @param {*} [args] 额外参数
     * @memberof MobPortletControlInterface
     */
    refresh(args?: any): void;
}
