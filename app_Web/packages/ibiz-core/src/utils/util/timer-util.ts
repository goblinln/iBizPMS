/**
 * 定时器工具类
 *
 * @export
 * @class TimerUtil
 */
export class TimerUtil {
    /**
     * 定时器缓存
     *
     * @protected
     * @type {Map<string, number>}
     * @memberof TimerUtil
     */
    protected static readonly timerCache: Map<string, number> = new Map();

    /**
     * 定时器
     *
     * @static
     * @param {TimerHandler} method 回调
     * @param {(number | undefined)} [timeout] 超时时间
     * @param {string} [id=''] 标识
     * @param {any[]} [args] 额外参数
     * @memberof TimerUtil
     */
    public static timeout(method: TimerHandler, timeout: number, id: string, args: any[] = []): void {
        this.clearTimeout(id);
        this.timerCache.set(id, setTimeout(method, timeout, ...args));
    }

    /**
     * 轮询定时器
     *
     * @static
     * @param {TimerHandler} method 回调
     * @param {(number | undefined)} [timeout] 超时时间
     * @param {string} [id=''] 标识
     * @param {any[]} [args] 额外参数
     * @memberof TimerUtil
     */
    public static interval(method: TimerHandler, timeout: number, id: string, args: any[] = []) {
        this.clearInterval(id);
        this.timerCache.set(id, setInterval(method, timeout, ...args));
    }

    /**
     * 清理定制器
     *
     * @protected
     * @static
     * @param {string} id
     * @memberof TimerUtil
     */
    protected static clearTimeout(id: string): void {
        if (this.timerCache.has(id)) {
            clearTimeout(this.timerCache.get(id));
            this.timerCache.delete(id);
        }
    }

    /**
     * 清理轮询定时器
     *
     * @protected
     * @static
     * @param {string} id
     * @memberof TimerUtil
     */
    protected static clearInterval(id: string): void {
        if (this.timerCache.has(id)) {
            clearInterval(this.timerCache.get(id));
            this.timerCache.delete(id);
        }
    }
}
