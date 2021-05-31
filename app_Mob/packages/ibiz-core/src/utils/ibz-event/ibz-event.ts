import { EventEmitter } from 'events';

/**
 * 事件
 *
 * @export
 * @class IBzEvent
 * @template T
 */
export class IBzEvent<T> {
    /**
     * 事件初始化
     *
     * @private
     * @type {EventEmitter}
     * @memberof IBzEvent
     */
    private e: EventEmitter;

    /**
     * Creates an instance of IBzEvent.
     * @memberof IBzEvent
     */
    constructor() {
        this.e = new EventEmitter();
        // 设置最大监控事件数量
        this.e.setMaxListeners(300);
    }

    /**
     * 订阅事件
     *
     * @template K
     * @param {K} name
     * @param {(...args) => void} cb
     * @memberof IBzEvent
     */
    on<K extends keyof T>(name: K, cb: (...args) => void): void {
        this.e.addListener(name as any, cb);
    }

    /**
     * 取消订阅事件
     *
     * @template K
     * @param {K} name
     * @param {(...args) => void} cb
     * @memberof IBzEvent
     */
    off<K extends keyof T>(name: K, cb: (...args) => void): void {
        this.e.removeListener(name as any, cb);
    }

    /**
     * 发送事件
     *
     * @template K
     * @param {K} name
     * @param {*} args
     * @memberof IBzEvent
     */
    emit<K extends keyof T>(name: K, ...args): void {
        this.e.emit(name as any, ...args);
    }
}
