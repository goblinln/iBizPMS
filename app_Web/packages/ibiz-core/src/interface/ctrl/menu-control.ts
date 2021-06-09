

/**
 * 菜单基类接口
 *
 * @interface MenuControlInterface
 */
export interface MenuControlInterface {


    /**
     * 数据加载
     *
     * @memberof MenuControlInterface
     */
    load(): void;


    /**
     * 菜单项选中处理
     *
     * @param {*} menuName  选中菜单名称
     * @memberof MenuControlInterface
     */
    select(menuName: any): void;


    /**
     * 计算菜单选中项
     *
     * @param {Array<any>} items 菜单数据
     * @param {string} appfunctag 应用功能tag
     * @return {*}  {boolean}
     * @memberof MenuControlInterface
     */
    computeMenuSelect(items: Array<any>, appfunctag: string): boolean;


    /**
     * 获取菜单项数据
     *
     * @param {any[]} items 菜单数据
     * @param {string} name 菜单项名称
     * @memberof MenuControlInterface
     */
    compute(items: any[], name: string): void;



    /**
     * 菜单点击事件
     *
     * @param {*} item 点击项
     * @memberof MenuControlInterface
     */
    click(item: any): void;


    /**
     * 计算有效菜单项
     *
     * @param {Array<any>} inputMenus
     * @memberof MenuControlInterface
     */
    computedEffectiveMenus(inputMenus: Array<any>): void;
}
