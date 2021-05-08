import { VNode } from 'vue';

export interface NoticeOptions {
    /**
     * 标题
     *
     * @type {string}
     * @memberof NoticeOptions
     */
    title?: string;

    /**
     * 提示信息
     *
     * @type {(string | VNode)}
     * @memberof NoticeOptions
     */
    message?: string | VNode;

    /**
     * 提示类型
     * 默认值：info
     *
     * @type {('success' | 'warning' | 'info' | 'error')}
     * @memberof NoticeOptions
     */
    type?: 'success' | 'warning' | 'info' | 'error';

    /**
     * 自定义弹出位置
     * 默认值：top
     *
     * @type {('top' | 'top-right' | 'top-left' | 'bottom-right' | 'bottom-left')}
     * @memberof NoticeOptions
     */
    position?: 'top' | 'top-right' | 'top-left' | 'bottom-right' | 'bottom-left';

    /**
     * 自定义图标的类名，会覆盖type
     *
     * @type {string}
     * @memberof NoticeOptions
     */
    iconClass?: string;

    /**
     * 自定义类名
     *
     * @type {string}
     * @memberof NoticeOptions
     */
    customClass?: string;

    /**
     * 显示时间, 毫秒。设为 0 则不会自动关闭
     * 默认值：3000
     *
     * @type {number}
     * @memberof NoticeOptions
     */
    duration?: number;

    /**
     * 是否显示关闭按钮
     * 默认值：false
     *
     * @type {boolean}
     * @memberof NoticeOptions
     */
    showClose?: boolean;

    /**
     * 是否将 message 属性作为 HTML 片段处理
     * 默认值：false
     *
     * @type {boolean}
     * @memberof NoticeOptions
     */
    dangerouslyUseHTMLString?: boolean;

    /**
     * 关闭时的回调函数, 参数为被关闭的实例
     *
     * @memberof NoticeOptions
     */
    onClose?: () => void;

    /**
     * 自定义偏移量
     *
     * @type {number}
     * @memberof NoticeOptions
     */
    offset?: number
}
