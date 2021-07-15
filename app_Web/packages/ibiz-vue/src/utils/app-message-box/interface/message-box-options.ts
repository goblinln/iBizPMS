import { VNode } from "vue";

export interface MessageBoxOptions {
    /**
     * 对话框类型
     *
     * @type {(string | 'info' | 'success' | 'warning' | 'error' | 'ok' )}
     * @memberof AppModalok
     */
    type?: string | 'info' | 'success' | 'warning' | 'error';

    /**
     * 标题
     *
     * @type {string}
     * @memberof AppModalok
     */
    title?: string;

    /**
     * 内容
     *
     * @type {string}
     * @memberof AppModalok
     */
    content?: string;

    /**
     * 按钮类型
     * 默认值:'okcancel'
     * okcancel 确认/取消
     * yesno 是/否
     * yesnocanel 是/否/取消
     * ok 确认
     * 
     * @type {(string | 'okcancel' | 'yesno' | 'yesnocanel' | 'ok')}
     * @memberof ModalokOptions
     */
    buttonType?: string | 'okcancel' | 'yesno' | 'yesnocanel' | 'ok';


    /**
     * 启用自定义底部
     *
     * @type {boolean}
     * @memberof ModalokOptions
     */
    visibleCustomFooter?: boolean;


    /**
     * 自定义底部
     *
     * @type {VNode}
     * @memberof ModalokOptions
     */
    customFooter?: VNode;

    /**
     * 显示模式
     * 默认值：center
     *
     * @type {('top' | 'top-right' | 'top-left' | 'bottom-right' | 'bottom-left')}
     * @memberof NoticeOptions
     */
    showMode?: 'center' | string;

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
     * 是否显示右上角的关闭按钮
     * 默认值：false
     *
     * @type {boolean}
     * @memberof NoticeOptions
     */
    showClose?: boolean;

    /**
     * 是否显示遮罩
     * 默认值：true
     *
     * @type {boolean}
     * @memberof NoticeOptions
     */
    mask?: boolean;

    /**
     * 是否点击遮罩关闭
     * 默认值：false
     *
     * @type {boolean}
     * @memberof NoticeOptions
     */
    maskClosable?: boolean;


    /**
     * 引用对象名称
     *
     * @type {string}
     * @memberof ModalokOptions
     */
    refName?: string;

    /**
     * 关闭时的回调函数, 参数为被关闭的实例
     *
     * @memberof NoticeOptions
     */
    onClose?: (val: any) => void;

}
