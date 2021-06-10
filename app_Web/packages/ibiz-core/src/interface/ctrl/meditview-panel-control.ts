import { MDControlInterface } from 'ibiz-core';

/**
 * 多编辑视图面板部件接口
 *
 * @interface MEditViewPanelControlInterface
 */
export interface MEditViewPanelControlInterface extends MDControlInterface {
    /**
     * 保存数据
     *
     * @param {*} [data] 数据
     * @memberof MEditViewPanelControlInterface
     */
    saveData(data?: any): void;

    /**
     * 数据加载
     *
     * @public
     * @param {*} data 额外参数
     * @memberof MEditViewPanelControlInterface
     */
    load(data: any): void;

    /**
     * 增加数据
     * 
     * @memberof MEditViewPanelControlInterface
     */
    handleAdd(): void;

    /**
     * 视图数据变更事件
     *
     * @param {*} $event 回调对象
     * @return {*} 
     * @memberof MEditViewPanelControlInterface
     */
    viewDataChange($event:any): void;

    /**
     * 视图加载完成
     *
     * @param {*} $event 回调对象
     * @memberof MEditViewPanelControlInterface
     */
    viewload($event:any): void;
}
