import { MobMDControlInterface } from 'ibiz-core';

/**
 * 树基类接口
 *
 * @interface MobTreeControlInterface
 */
export interface MobTreeControlInterface extends MobMDControlInterface {

    /**
     * 数据加载
     *
     * @param {*} [node={}] 节点数据
     * @param {*} [resolve] 渲染树节点回调
     * @return {*} 
     * @memberof MobTreeControlInterface
     */
    load(node?: any, resolve?: any): void; 

    /**
     * 节点复选框选中事件
     *
     * @public
     * @param {*} data 当前节点对应传入对象
     * @param {*} checkedState 树目前选中状态对象
     * @memberof MobTreeControlInterface
     */
    onCheck(data: any, checkedState: any): void;

    /**
     * 当前选中节点变更事件
     *
     * @public
     * @param {*} data 节点对应传入对象
     * @param {*} node 节点对应node对象
     * @memberof MobTreeControlInterface
     */
    selectionChange(data: any, node: any): void;

    /**
     * 刷新
     *
     * @param {*} [args] 额外参数
     * @memberof MobTreeControlInterface
     */
    refresh(args?: any): void;

    /**
     * 刷新整个树
     *
     * @memberof MobTreeControlInterface
     */
    refresh_all(): void;

    /**
     * 刷新当前节点的父节点
     *
     * @memberof MobTreeControlInterface
     */
    refresh_parent(): void;

    /**
     * 执行默认界面行为(树节点双击事件)
     *
     * @param {*} node 节点数据
     * @memberof MobTreeControlInterface
     */
    doDefaultAction(node: any): void;

    /**
     * 显示上下文菜单事件
     *
     * @param data 节点数据
     * @param event 事件源
     * @memberof MobTreeControlInterface
     */
    showContext(data: any, event: any): void;

    /**
     * 节点点击加载
     *
     * @memberof MobTreeControlInterface
     */
    click_node(data: any): void;

    /**
     * 对树节点进行筛选操作
     * @memberof MobTreeControlInterface
     */
    filterNode(value: any, data: any): void;
    
    /**
     * 单选点击行为
     *
     * @param {*} item
     * @memberof MobTreeControlInterface
     */
    onSimpleSelChange(item: any): void;

    /**
     * 多选点击行为
     *
     * @param {*} data
     * @memberof MobTreeControlInterface
     */
    onChecked(data: any): void;

    /**
     * 节点长按
     *
     * @memberof MobTreeControlInterface
     */
    node_touch(item: any): void;

    /**
     * 树前端搜索
     *
     * @memberof MobTreeControlInterface
     */
    webLoad(query: string): void;

    /**
     * 上下文菜单点击
     * @memberof MobTreeControlInterface 
     */
    context_menu_click(): void;

}
