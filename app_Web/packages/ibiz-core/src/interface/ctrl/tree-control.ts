import { MDControlInterface } from 'ibiz-core';

/**
 * 树基类接口
 *
 * @interface TreeControlInterface
 */
export interface TreeControlInterface extends MDControlInterface {

    /**
     * 数据加载
     *
     * @param {*} [node={}] 节点数据
     * @param {*} [resolve] 渲染树节点回调
     * @return {*} 
     * @memberof TreeControlInterface
     */
    load(node?: any, resolve?: any): void; 

    /**
     * 节点复选框选中事件
     *
     * @public
     * @param {*} data 当前节点对应传入对象
     * @param {*} checkedState 树目前选中状态对象
     * @memberof TreeControlInterface
     */
    onCheck(data: any, checkedState: any): void;

    /**
     * 当前选中节点变更事件
     *
     * @public
     * @param {*} data 节点对应传入对象
     * @param {*} node 节点对应node对象
     * @memberof TreeControlInterface
     */
    selectionChange(data: any, node: any): void;

    /**
     * 刷新
     *
     * @param {*} [args] 额外参数
     * @memberof TreeControlInterface
     */
    refresh(args?: any): void;

    /**
     * 刷新整个树
     *
     * @memberof TreeControlInterface
     */
    refresh_all(): void;

    /**
     * 刷新节点
     *
     * @public
     * @param {*} [curContext] 当前节点上下文
     * @param {*} [arg={}] 当前节点附加参数
     * @param {boolean} parentnode 是否是刷新父节点
     * @memberof TreeControlInterface
     */
    refresh_node(curContext: any, arg?: any, parentnode?: boolean): void;

    /**
     * 刷新当前节点
     *
     * @memberof TreeControlInterface
     */
    refresh_current(): void;

    /**
     * 刷新当前节点的父节点
     *
     * @memberof TreeControlInterface
     */
    refresh_parent(): void;

    /**
     * 执行默认界面行为(树节点双击事件)
     *
     * @param {*} node 节点数据
     * @memberof TreeControlInterface
     */
    doDefaultAction(node: any): void;

    /**
     * 显示上下文菜单事件
     *
     * @param data 节点数据
     * @param event 事件源
     * @memberof TreeControlInterface
     */
    showContext(data: any, event: any): void;
}
