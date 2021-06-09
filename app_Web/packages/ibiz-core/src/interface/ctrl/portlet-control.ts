

/**
 * 菜单基类接口
 *
 * @interface PortletControlInterface
 */
export interface PortletControlInterface {


    /**
     * 触发界面行为
     *
     * @param {*} data 触发数据源
     * @param {*} $event 事件对象
     * @memberof PortletControlInterface
     */
    handleItemClick(data: any, $event: any): void;


    /**
     * 刷新
     *
     * @param {*} [args] 额外参数
     * @memberof PortletControlInterface
     */
    refresh(args?: any): void;
}
