import { MobMDControlInterface } from 'ibiz-core';

/**
 * 列表基类接口
 *
 * @interface MobMdctrlControlInterface
 */
export interface MobMdctrlControlInterface extends MobMDControlInterface {
    
    /**
     * 列表数据加载
     *
     * @param {*} [opt={}] 额外参数
     * @returns {void}
     * @memberof MobMdctrlControlInterface
     */
    load(opt?: any): void;

    /**
     * 删除
     *
     * @param {any[]} items 删除数据
     * @returns {Promise<any>}
     * @memberof MobMdctrlControlInterface
     */
    remove(items: any[]): Promise<any>;

    /**
     * 刷新
     *
     * @param {*} [args] 额外参数
     * @memberof MobMdctrlControlInterface
     */
    refresh(args?: any): void;

    /**
     * 处理操作列点击
     * 
     * @param {*} data 行数据
     * @param {*} event 事件源
     * @param {*} item 列表项模型
     * @param {*} detail 操作列模型
     * @memberof MobMdctrlControlInterface
     */
    handleActionClick(data: any, event: any, item: any, detail: any): void;

    /**
     * 上拉加载更多数据
     *
     * @memberof MobMdctrlControlInterface
     */
    loadBottom(): Promise<any>;

    /**
     * 下拉刷新
     *
     * @returns {Promise<any>}
     * @memberof MobMdctrlControlInterface
     */
    pullDownToRefresh(): Promise<any>;

    /**
     * 单选选中变化
     * 
     * @memberof MobMdctrlControlInterface
     */
    onSimpleSelChange(item: any): void;

    /**
     * 选中或取消事件
     *
     * @memberof MobMdctrlControlInterface
     */
    checkboxSelect(item: any): void

    /** 
     * checkbox 选中回调
     *
     * @memberof MobMdctrlControlInterface
     */
    checkboxChange(data: any): void    

    /**
     * 全选事件
     *
     * @memberof MobMdctrlControlInterface
     */
    checkboxAll(value: any): void  

}
