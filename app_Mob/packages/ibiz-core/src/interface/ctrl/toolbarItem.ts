export interface ToolbarItem {
    /**
     * 标识
     *
     * @type {string}
     * @memberof ToolbarItem
     */
    name?: string;
    /**
     * 标题
     *
     * @type {string}
     * @memberof ToolbarItem
     */
    caption?: string;
    /**
     * 类型
     *
     * @type {('DEUIACTION' | 'SEPERATOR')} 界面行为 | 分割线
     * @memberof ToolbarItem
     */
    itemType?: 'DEUIACTION' | 'SEPERATOR';
    /**
     * 是否显示标题
     *
     * @type {boolean}
     * @memberof ToolbarItem
     */
    showCaption?: boolean;
    /**
     * 是否显示图标
     *
     * @type {boolean}
     * @memberof ToolbarItem
     */
    showIcon?: boolean;
    /**
     * 提示
     *
     * @type {string}
     * @memberof ToolbarItem
     */
    tooltip?: string;
    /**
     * 样式图标
     *
     * @type {string}
     * @memberof ToolbarItem
     */
    iconcls?: string;
    /**
     * 路径图标
     *
     * @type {string}
     * @memberof ToolbarItem
     */
    icon?: string;
    /**
     * 是否启用
     *
     * @type {boolean}
     * @memberof ToolbarItem
     */
    disabled?: boolean;
    /**
     * 是否显示
     *
     * @type {boolean}
     * @memberof ToolbarItem
     */
    visabled?: boolean;
    /**
     * 统一资源控制
     *
     * @type {string}
     * @memberof ToolbarItem
     */
    dataaccaction?: string;
    /**
     * 按钮额外样式
     *
     * @type {string}
     * @memberof ToolbarItem
     */
    class?: string;
    /**
     * 界面行为内容
     *
     * @type {{ tag: string, target: string }}
     * @memberof ToolbarItem
     */
    uiaction?: { tag: string; target: string };
    /**
     * 子数据
     *
     * @type {*}
     * @memberof ToolbarItem
     */
    model?: any;
    /**
     * 格式化后子工具栏项
     *
     * @type {any[]}
     * @memberof ToolbarItem
     */
    items?: any[];
}
