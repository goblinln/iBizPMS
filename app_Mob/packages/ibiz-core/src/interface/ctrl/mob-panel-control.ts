import { MobMDControlInterface } from 'ibiz-core';

/**
 * 面板部件接口
 *
 * @interface MobPanelControlInterface
 */
export interface MobPanelControlInterface extends MobMDControlInterface {

    /**
     * 删除
     *
     * @param {any[]} datas
     * @returns {Promise<any>}
     * @memberof MobPanelControlInterface
     */
    remove(datas: any[]): Promise<any>;

    /**
     * 刷新
     *
     * @param {*} [args]
     * @memberof MobPanelControlInterface
     */
    refresh(args?: any): void;

    /**
     * 设置面板编辑项值变更
     *
     * @param data 面板数据
     * @param {{ name: string, value: any }} $event
     * @returns {void}
     * @memberof MobPanelControlInterface
     */
    onPanelItemValueChange(data: any, $event: { name: string; value: any }): void;

    /**
     * 面板编辑项值变化
     *
     * @public
     * @param data 面板数据
     * @param property 编辑项名
     * @param value 编辑项值
     * @returns {void}
     * @memberof MobPanelControlInterface
     */
    panelEditItemChange(data: any, property: string, value: any): void;

    /**
     * 分页切换事件
     *
     * @param {string} name 分页名
     * @param {*} $event 回调对象
     * @memberof MobPanelControlInterface
     */
    handleTabPanelClick(name: string, $event: any): void;

    /**
     * 按钮点击事件
     * 
     * @param {string} controlName 部件名称
     * @param {*} data 数据
     * @param {*} $event 事件源
     * @memberof MobPanelControlInterface
     */
    buttonClick(controlName: string, data: any, $event: any): void;

    /**
     * 面板逻辑
     *
     * @public
     * @param {{ name: string, newVal: any, oldVal: any }} { name, newVal, oldVal }
     * @memberof MobPanelControlInterface
     */
    panelLogic({ name, newVal, oldVal }: { name: string; newVal: any; oldVal: any }): void;
}
