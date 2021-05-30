import { VNode } from 'vue';
import moment from 'moment';
import './notification-signal.less';
import { TimerUtil } from 'ibiz-core';

/**
 * 通知信号指令
 *
 * @export
 * @class NotificationSignal
 */
export const NotificationSignal: any = {
    /**
     * 指定初始化
     *
     * @param {HTMLDivElement} el
     * @param {*} binding
     * @param {VNode} vNode
     * @param {VNode} oldVNode
     */
    bind(el: HTMLDivElement, binding: any, vNode: VNode, oldVNode: VNode) {
        nsc.init(el);
    },
    /**
     * 指令更新
     *
     * @param {HTMLDivElement} el
     * @param {*} binding
     * @param {VNode} vNode
     * @param {VNode} oldVNode
     */
     componentUpdated(el: HTMLDivElement, binding: any, vNode: VNode, oldVNode: VNode) {
        if(binding.value != binding.oldValue){
            if(binding.value == true){
                nsc.loading();
            }else if(binding.value == false){
                nsc.loadingEnd();
            }
        }
    }
};

/**
 * 信号闪烁控制器
 *
 * @export
 * @class NotificationSignal
 */
export class NotificationSignalController {
    /**
     * 唯一实例
     *
     * @private
     * @static
     * @memberof NotificationSignalController
     */
    private static readonly instance = new NotificationSignalController();
    /**
     * 容器
     *
     * @protected
     * @type {HTMLDivElement}
     * @memberof NotificationSignalController
     */
    protected el!: HTMLDivElement;
    /**
     * 加载计数
     *
     * @protected
     * @type {number}
     * @memberof NotificationSignalController
     */
    protected loadingCount: number = 0;
    /**
     * 加载动画定时器
     *
     * @protected
     * @type {*}
     * @memberof NotificationSignalController
     */
    protected loadingInterval: any;
    /**
     * 加载结束定时器
     *
     * @protected
     * @type {*}
     * @memberof NotificationSignalController
     */
    protected loadingEndTimer: any;
    /**
     * 加载动画宽度百分比
     *
     * @protected
     * @type {number}
     * @memberof NotificationSignalController
     */
    protected loadingWidth: number = 0;
    /**
     * 是否加载动画中
     *
     * @protected
     * @type {boolean}
     * @memberof NotificationSignalController
     */
    protected isLoading: boolean = false;

    /**
     * Creates an instance of NotificationSignalController.
     * @memberof NotificationSignalController
     */
    private constructor() {
        if (NotificationSignalController.instance) {
            return NotificationSignalController.instance;
        }
    }

    /**
     * 初始化
     *
     * @param {HTMLDivElement} el
     * @memberof NotificationSignalController
     */
    public init(el: HTMLDivElement): void {
        el.classList.add('nsc-container');
        const div: HTMLDivElement = document.createElement('div');
        div.classList.add('nsc-content');
        el.appendChild(div);
        this.el = div;
    }

    /**
     * 成功
     *
     * @param {number} [num=1500]
     * @memberof NotificationSignalController
     */
    public success(num: number = 1500): void {
        this.active();
        this.el.classList.add('success');
        TimerUtil.timeout(
            () => {
                this.inactive();
                this.el.classList.remove('success');
            },
            num,
            'nsc-success'
        );
    }

    /**
     * 警告
     *
     * @param {number} [num=1500]
     * @memberof NotificationSignalController
     */
    public waring(num: number = 1500): void {
        this.active();
        this.el.classList.add('waring');
        TimerUtil.timeout(
            () => {
                this.inactive();
                this.el.classList.remove('waring');
            },
            num,
            'nsc-waring'
        );
    }

    /**
     * 错误
     *
     * @param {number} [num=1500]
     * @memberof NotificationSignalController
     */
    public error(num: number = 1500): void {
        this.active();
        this.el.classList.add('error');
        TimerUtil.timeout(
            () => {
                this.inactive();
                this.el.classList.remove('error');
            },
            num,
            'nsc-error'
        );
    }

    /**
     * 激活态
     *
     * @protected
     * @memberof NotificationSignalController
     */
    protected active(): void {
        this.el.classList.add('active');
        this.el.classList.remove('inactive');
    }

    /**
     * 无活动态
     *
     * @protected
     * @memberof NotificationSignalController
     */
    protected inactive(): void {
        this.el.classList.add('inactive');
        this.el.classList.remove('active');
    }

    /**
     * 显示加载态
     *
     * @memberof NotificationSignalController
     */
    public loading(): void {
        if (this.loadingCount === 0 && this.el) {
            if (!this.isLoading) {
                this.active();
                this.loadingWidth = 0;
                this.el.classList.add('loading');
                this.setElWidth(this.loadingWidth);
                this.isLoading = true;
                this.loadingChange();
                this.loadingInterval = setInterval(() => {
                    this.loadingChange();
                }, 300);
            } else {
                this.loadingCount++;
                this.loadingEnd();
                return;
            }
        }
        this.loadingCount++;
    }

    /**
     * 加载动画变更
     *
     * @protected
     * @memberof NotificationSignalController
     */
    protected loadingChange(): void {
        this.loadingWidth += Math.abs(Math.random() * 5);
        if (this.loadingWidth >= 99) {
            this.loadingWidth = 99;
            this.clearLoadingInterval();
        }
        this.setElWidth(this.loadingWidth);
    }

    /**
     * 结束加载态
     *
     * @memberof NotificationSignalController
     */
    public loadingEnd(): void {
        if (this.loadingCount > 0) {
            this.loadingCount--;
        } else {
            this.loadingCount = 0;
        }
        if (this.loadingCount === 0 && this.el) {
            this.clearLoadingInterval();
            this.setElWidth(100);
            this.clearLoadingEndTimer();
            this.loadingEndTimer = setTimeout(() => {
                this.isLoading = false;
                this.inactive();
                this.el.classList.remove('loading');
            }, 300);
        }
    }

    /**
     * 清除加载动画定时器
     *
     * @protected
     * @memberof NotificationSignalController
     */
    protected clearLoadingInterval(): void {
        if (this.loadingInterval) {
            clearInterval(this.loadingInterval);
            this.loadingInterval = null;
        }
    }

    /**
     * 清除结束定时器
     *
     * @protected
     * @memberof NotificationSignalController
     */
    protected clearLoadingEndTimer(): void {
        if (this.loadingEndTimer) {
            clearTimeout(this.loadingEndTimer);
            this.loadingEndTimer = null;
        }
    }

    /**
     * 设置容器宽度百分比
     *
     * @protected
     * @param {number} width
     * @memberof NotificationSignalController
     */
    protected setElWidth(width: number): void {
        this.el.style.width = width + '%';
    }

    /**
     * 获取当前时间
     *
     * @protected
     * @returns {string}
     * @memberof NotificationSignalController
     */
    protected getLocalDate(): string {
        return moment().format('MM-DD HH:mm:ss');
    }

    /**
     * 获取唯一实例
     *
     * @static
     * @returns {NotificationSignalController}
     * @memberof NotificationSignalController
     */
    public static getInstance(): NotificationSignalController {
        return NotificationSignalController.instance;
    }
}

// 导出服务
export const nsc: NotificationSignalController = NotificationSignalController.getInstance();
