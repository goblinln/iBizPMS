/**
 * 列表基类接口
 *
 * @interface ListControlInterface
 */
export interface ListControlInterface {
    
    /**
     * 列表数据加载
     *
     * @param {*} [opt={}] 额外参数
     * @returns {void}
     * @memberof ListControlInterface
     */
    load(opt?: any): void;

    /**
     * 删除
     *
     * @param {any[]} items 删除数据
     * @returns {Promise<any>}
     * @memberof ListControlInterface
     */
    remove(items: any[]): Promise<any>;

    /**
     * 保存
     *
     * @param {*} args 额外参数
     * @return {*} 
     * @memberof ListControlInterface
     */
    save(args: any): Promise<any>;

    /**
     * 获取选中数据
     *
     * @returns {any[]}
     * @memberof ListControlInterface
     */
    getSelection(): any[];

    /**
     * 清除当前所有选中状态
     *
     * @memberof ListControlInterface
     */
    clearSelection(): void;

    /**
     * 加载更多
     *
     * @memberof ListControlInterface
     */
    loadMore(): void;

    /**
     * 刷新
     *
     * @param {*} [args] 额外参数
     * @memberof ListControlInterface
     */
    refresh(args?: any): void;

    /**
     * 行单击事件
     *
     * @param {*} args 行数据
     * @memberof ListControlInterface
     */
    handleClick(args: any): void;

    /**
     * 双击事件
     *
     * @param {*} args 数据
     * @memberof ListControlInterface
     */
    handleDblClick(args: any): void;

    /**
     * 处理操作列点击
     * 
     * @param {*} data 行数据
     * @param {*} event 事件源
     * @param {*} item 列表项模型
     * @param {*} detail 操作列模型
     * @memberof ListControlInterface
     */
    handleActionClick(data: any, event: any, item: any, detail: any): void;

    /**
     * 面板数据变化处理事件
     * @param {any} item 当前列数据
     * @param {any} $event 面板事件数据
     *
     * @memberof ListControlInterface
     */
    onPanelDataChange(item: any, $event: any): void;
}
