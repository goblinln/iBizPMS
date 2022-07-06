import { MobMainControlInterface } from 'ibiz-core';


/**
 * 数据看板部件基类接口
 *
 * @interface MobDashboardControlInterface
 */
export interface MobDashboardControlInterface extends MobMainControlInterface{

    /**
     * 加载门户部件
     *
     * @return {*}  {Promise<any>}
     * @memberof MobDashboardControlInterface
     */
    loadPortletList(context: any, viewparams: any): Promise<any>;


    /**
     * 加载布局与数据模型
     *
     * @memberof MobDashboardControlInterface
     */
    loadModel(): void;


    /**
     * 处理私人定制按钮
     *
     * @memberof MobDashboardControlInterface
     */
    handleClick(): void;
}
