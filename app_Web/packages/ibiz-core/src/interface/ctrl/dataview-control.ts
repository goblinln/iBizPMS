import { MDControlInterface } from 'ibiz-core';

/**
 * 数据视图基类接口
 *
 * @interface DataViewControlInterface
 */
export interface DataViewControlInterface extends MDControlInterface{
    
    /**
     * 数据加载
     *
     * @param {*} [opt={}] 额外参数
     * @param {boolean} [isReset=false] 是否重置数据，默认加载到的数据附加在已有的之后
     * @return {*} 
     * @memberof DataViewControlInterface
     */
    load(opt?: any, isReset?: boolean): void;

    /**
     * 删除
     *
     * @param {any[]} datas 删除数据
     * @returns {Promise<any>}
     * @memberof DataViewControlInterface
     */
    remove(datas: any[]): Promise<any>;

    /**
     * 保存
     *
     * @param {*} args 额外参数
     * @return {*} 
     * @memberof DataViewControlInterface
     */
    save(args: any): Promise<any>;

    /**
     * 加载更多
     *
     * @memberof DataViewControlInterface
     */
    loadMore(): void;

    /**
     * 刷新
     *
     * @param {*} [args] 额外参数
     * @memberof DataViewControlInterface
     */
    refresh(args?: any): void;

    /**
     * 单击事件
     *
     * @param {*} args 行数据
     * @memberof DataViewControlInterface
     */
    handleClick(args: any): void;

    /**
     * 双击事件
     *
     * @param {*} args 数据
     * @memberof DataViewControlInterface
     */
    handleDblClick(args: any): void;

    /**
     * 处理操作列点击
     * 
     * @param {*} data 行数据
     * @param {*} event 事件源
     * @param {*} item 数据视图项模型
     * @param {*} detail 操作列模型
     * @memberof DataViewControlInterface
     */
    handleActionClick(data: any, event: any, item: any, detail: any): void;

    /**
     * 面板数据变化处理事件
     * @param {any} item 当前列数据
     * @param {any} $event 面板事件数据
     *
     * @memberof DataViewControlInterface
     */
    onPanelDataChange(item: any, $event: any): void;

    /**
     * 排序点击事件
     * @param {string} field 属性名
     *
     * @memberof DataViewControlInterface
     */
    sortClick(field: string): void;

}
