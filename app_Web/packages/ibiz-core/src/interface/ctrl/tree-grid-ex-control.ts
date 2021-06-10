import { MDControlInterface } from 'ibiz-core';

/**
 * 树表格基类接口
 *
 * @interface TreeGridExControlInterface
 */
export interface TreeGridExControlInterface extends MDControlInterface {

    /**
     * 加载数据
     *
     * @param {*} [node={}] 节点数据
     * @param {*} [resolve] 渲染回调
     * @memberof TreeGridExControlInterface
     */
    load(node?: any, resolve?: any): void;

    /**
     * 刷新
     *
     * @param {*} [args] 额外参数
     * @memberof TreeGridExControlInterface
     */
    refresh(args?: any): void;

    /**
     * 加载事件
     *
     * @param {*} row 行数据
     * @param {*} treeNode 节点信息
     * @param {*} resolve 渲染回调
     * @memberof TreeGridExControlInterface
     */
    loadTreeNode(row: any, treeNode: any, resolve: any): void;

    /**
     * 当前选中变化事件
     *
     * @param {*} $event 行数据
     * @returns
     * @memberof TreeGridExControlInterface
     */
    select($event: any): void;
}
