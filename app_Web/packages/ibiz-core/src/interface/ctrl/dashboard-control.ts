

/**
 * 数据看板部件基类接口
 *
 * @interface DashboardControlInterface
 */
export interface DashboardControlInterface {

    /**
     * 加载门户部件
     *
     * @return {*}  {Promise<any>}
     * @memberof DashboardControlInterface
     */
    loadPortletList(): Promise<any>;


    /**
     * 加载布局与数据模型
     *
     * @memberof DashboardControlInterface
     */
    loadModel(): void;


    /**
     * 处理私人定制按钮
     *
     * @memberof DashboardControlInterface
     */
    handleClick(): void;
}
