/**
 * 布局状态
 *
 * @export
 * @interface LayoutState
 */
export interface LayoutState {
    /**
     * 应用呈现布局模式
     *
     * @default 'DEFAULT'
     * @type {('DEFAULT' | 'STYLE2')}
     * @memberof LayoutState
     */
    styleMode: 'DEFAULT' | 'STYLE2';
    /**
     * 当前主题
     *
     * @default 'dark'
     * @type {('light' | 'blue-dark' | 'dark' | 'dark-white')}
     * @memberof LayoutState
     */
    theme: 'light' | 'blue-dark' | 'dark' | 'dark-white';
    /**
     * 内容区底部是否展示
     *
     * @default true
     * @type {boolean}
     * @memberof LayoutState
     */
    contentBottomShow: boolean;
    /**
     * 内容区左右分割比例
     *
     * @default 0.23
     * @type {number}
     * @memberof LayoutState
     */
    contentHorizontalSplit: number;
    /**
     * 内容区上下分割比例
     *
     * @default 0.65
     * @type {number}
     * @memberof LayoutState
     */
    contentVerticalSplit: number;
    /**
     * 左侧导航默认激活项
     *
     * @default 0
     * @type {number}
     * @memberof LayoutState
     */
    leftExpActiveIndex: number;
    /**
     * 底部导航默认激活项
     *
     * @default 0
     * @type {number}
     * @memberof LayoutState
     */
    bottomExpActiveIndex: number;
    /**
     * 左侧导航内容区是否显示
     *
     * @default true
     * @type {boolean}
     * @memberof LayoutState
     */
    leftExpContentShow: boolean;
    /**
     * 在样式模式为style2时，左侧导航菜单是否收起
     *
     * @default false
     * @type {boolean}
     * @memberof LayoutState
     */
    leftNavMenuCollapse: boolean;
    /**
     * 左侧已展开菜单项
     *
     * @default []
     * @type {string[]}
     * @memberof LayoutState
     */
    leftNavOpenedMenus: string[];
}
