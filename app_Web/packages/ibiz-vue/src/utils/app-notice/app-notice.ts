import Vue from 'vue';
import Message from './render/message.vue';
import Notification from './render/notification.vue';
const PopupManager = require('element-ui/src/utils/popup').PopupManager;
const isVNode = require('element-ui/src/utils/vdom').isVNode;
let MessageConstructor = Vue.extend(Message);
let NotificationConstructor = Vue.extend(Notification);
import { NoticeOptions } from './interface/notice-options';

/**
 * 提示信息
 *
 * @export
 * @class AppNotice
 */
export class AppNotice {
    /**
     * 唯一实例
     *
     * @private
     * @static
     * @memberof AppNotice
     */
    private static readonly instance = new AppNotice();

    /**
     * 获取唯一实例
     *
     * @static
     * @return {*}  {AppNotice}
     * @memberof AppNotice
     */
    public static getInstance(): AppNotice {
        return AppNotice.instance;
    }

    /**
     * 当前存在的实例集合
     *
     * @private
     * @type {*}
     * @memberof AppNotice
     */
    private instances: any = [];

    /**
     * 计数标识
     *
     * @private
     * @memberof AppNotice
     */
    private seed = 1;

    /**
     * 打开提示信息
     *
     * @param {*} options
     * @return {*}
     * @memberof AppNotice
     */
    public open(options: NoticeOptions) {
        if (!options) {
            return;
        }
        this.handleDefault(options);
        const isMessage = options.position == 'top';
        let id = 'notice_' + this.seed++;

        let userOnClose = options.onClose;
        options.onClose = () => {
            this.close(id, userOnClose);
        };

        let instance: any;
        if (isMessage) {
            instance = new MessageConstructor({
                data: options,
            });
        } else {
            instance = new NotificationConstructor({
                data: options,
            });
        }
        instance.id = id;

        // message为节点时的处理
        if (isVNode(instance.message)) {
            instance.$slots.default = [instance.message];
            instance.message = null;
        }
        instance.$mount();
        document.body.appendChild(instance.$el);

        // 计算偏移量
        let verticalOffset = options.offset || 16;
        this.instances
            .filter((item: any) => item.position === options.position)
            .forEach((item: any) => {
                verticalOffset += item.$el.offsetHeight + 16;
            });
        instance.verticalOffset = verticalOffset;
        instance.visible = true;
        instance.$el.style.zIndex = PopupManager.nextZIndex();
        this.instances.push(instance);
        return instance;
    }

    /**
     * 处理options的默认值
     *
     * @private
     * @param {*} options
     * @memberof AppNotice
     */
    private handleDefault(options: any) {
        if (!options.position) {
            options.position = 'top';
        } else {
            // 标题没有时显示默认标题
            if (!options.title) {
                switch (options.type) {
                    case 'error':
                        options.title = '错误';
                        break;
                    case 'success':
                        options.title = '成功';
                        break;
                    case 'warning':
                        options.title = '警告';
                        break;
                    default:
                        options.title = '消息';
                }
            }
        }
    }

    /**
     * 单个实例的公共关闭方法
     *
     * @param {*} id
     * @param {*} userOnClose 用户自定义回调
     * @memberof AppNotice
     */
    public close(id: any, userOnClose: any) {
        let len = this.instances.length;
        let index = -1;
        const instance = this.instances.find((instance: any, i: number) => {
            if (instance.id === id) {
                index = i;
                return true;
            }
            return false;
        });
        if (!instance) return;

        // 调用用户自定义关闭回调
        if (typeof userOnClose === 'function') {
            userOnClose(instance);
        }

        this.instances.splice(index, 1);

        if (len <= 1) return;
        const position = instance.position;
        const removedHeight = instance.$el.offsetHeight;
        for (let i = index; i < len - 1; i++) {
            if (this.instances[i].position === position) {
                this.instances[i].$el.style[instance.verticalProperty] =
                    parseInt(this.instances[i].$el.style[instance.verticalProperty], 10) - removedHeight - 16 + 'px';
            }
        }
    }

    /**
     * 关闭所有实例
     *
     * @memberof AppNotice
     */
    public closeAll() {
        for (let i = this.instances.length - 1; i >= 0; i--) {
            this.instances[i].close();
        }
    }
}
