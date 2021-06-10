import { MDControlInterface } from 'ibiz-core';

/**
 * 看板基类接口
 *
 * @interface KanbanControlInterface
 */
export interface KanbanControlInterface extends MDControlInterface {
    
    /**
     * 数据加载
     *
     * @param {*} [opt={}] 额外参数
     * @param {boolean} [isReset=false] 是否重置数据，默认加载到的数据附加在已有的之后
     * @return {*} 
     * @memberof KanbanControlInterface
     */
     load(opt?: any, isReset?: boolean): void;

    /**
     * 删除
     *
     * @param {any[]} datas 删除数据
     * @returns {Promise<any>}
     * @memberof KanbanControlInterface
     */
    remove(datas: any[]): Promise<any>;

    /**
     * 加载更多
     *
     * @memberof KanbanControlInterface
     */
    loadMore(): void;

    /**
     * 刷新
     *
     * @param {*} [args] 额外参数
     * @memberof KanbanControlInterface
     */
    refresh(args?: any): void;

    /**
     * 界面行为
     *
     * @param {*} detail 界面行为
     * @param {*} $event 事件源
     * @param {*} group 看板分组
     * @memberof KanbanControlInterface
     */
    uiActionClick(detail: any, $event: any, group: any): void;

    /**
     * 单击事件
     *
     * @param {*} args 行数据
     * @memberof KanbanControlInterface
     */
    handleClick(args: any): void;

    /**
     * 双击事件
     *
     * @param {*} args 数据
     * @memberof KanbanControlInterface
     */
    handleDblClick(args: any): void;

    /**
     * 面板数据变化处理事件
     * @param {any} item 当前列数据
     * @param {any} $event 面板事件数据
     *
     * @memberof KanbanControlInterface
     */
    onPanelDataChange(item: any, $event: any): void;

    /**
     * 拖拽变化
     *
     * @param {*} evt 拖住对象
     * @param {*} name 分组名
     * @memberof KanbanControlInterface
     */
    onDragChange(evt: any, name: string): Promise<any>;

    /**
     * 修改分组集合
     *
     * @param {*} opt 数据
     * @param {*} newVal 新分组值
     * @memberof KanbanControlInterface
     */
    updateData(opt: any, newVal: any): void;
}
