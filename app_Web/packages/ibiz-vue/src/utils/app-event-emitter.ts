import { EventEmitter } from 'events';

/**
 * 事件
 *
 * @export
 * @class IBzEventEmitter
 */
export class AppEventEmitter<K, V> {
    /**
     * 事件对象
     *
     * @private
     * @memberof AppEventEmitter
     */
    private readonly e = new EventEmitter();

    /**
     * 注册事件
     *
     * @param {K} eventName 事件名称
     * @param {(data: V) => void} callback 回调
     * @memberof AppEventEmitter
     */
    on(eventName: K, callback: (data: V) => void): void {
        this.e.on(eventName as any, callback);
    }

    /**
     * 订阅一次
     *
     * @param {K} eventName 事件名称
     * @param {(data: V) => void} callback 回调
     * @memberof AppEventEmitter
     */
    once(eventName: K, callback: (data: V) => void): void {
        this.e.once(eventName as any, callback);
    }

    /**
     * 发送事件
     *
     * @param {K} eventName 事件名称
     * @param {V} data 发送数据
     * @memberof AppEventEmitter
     */
    emit(eventName: K, data: V): void {
        this.e.emit(eventName as any, data);
    }

    /**
     * 取消订阅
     *
     * @param {K} eventName 事件名称
     * @param {(data: V) => void} callback 注册时的回调
     * @memberof IBzEventEmitter
     */
    off(eventName: K, callback: (data: V) => void): void {
        this.e.off(eventName as any, callback);
    }
}
