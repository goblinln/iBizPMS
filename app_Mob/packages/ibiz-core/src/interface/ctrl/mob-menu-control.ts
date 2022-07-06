import { MobControlInterface } from 'ibiz-core';

/**
 * 菜单基类接口
 *
 * @interface MobMenuControlInterface
 */
export interface MobMenuControlInterface extends MobControlInterface{

    /**
     * 菜单项选中处理
     *
     * @param {*} menuName  选中菜单名称
     * @memberof MobMenuControlInterface
     */
    select(menuName: any): void;

    /**
     * 获取菜单项数据
     *
     * @param {any[]} items 菜单数据
     * @param {string} name 菜单项名称
     * @memberof MobMenuControlInterface
     */
    compute(items: any[], name: string): void;

    /**
     * 菜单点击事件
     *
     * @param {*} item 点击项
     * @memberof MobMenuControlInterface
     */
    click(item: any): void;

    /**
     * 计算有效菜单项
     *
     * @param {Array<any>} inputMenus
     * @memberof MobMenuControlInterface
     */
    computedEffectiveMenus(inputMenus: Array<any>): void;
}
