import { AppUITriggerEngine } from "./app-ui-trigger-engine";

/**
 * 界面自定义触发逻辑引擎
 *
 * @export
 * @class AppTimerEngine
 */
export class AppTimerEngine extends AppUITriggerEngine {

    /**
     * 间隔时间
     * 
     * @memberof AppTimerEngine
     */
    public timer: number = 6000;

    /**
     * 计数器标识
     * 
     * @memberof AppTimerEngine
     */
    public timerId: any;

    /**
     * Creates an instance of AppTimerEngine.
     * @memberof AppTimerEngine
     */
    constructor(opts: any) {
        super(opts);
        this.timer = opts.timer ? opts.timer : 6000;
    }

    /**
     * 执行界面逻辑
     * @memberof AppTimerEngine
     */
    public async executeAsyncUILogic(opts: any) {
        if (!this.timerId) {
            super.executeAsyncUILogic(opts);
        }
        this.timerId = setInterval(() => {
            super.executeAsyncUILogic(opts);
        }, this.timer)
    }

    /**
     * 执行界面逻辑
     * @memberof AppTimerEngine
     */
    public executeUILogic(opts: any) {
        if (!this.timerId) {
            super.executeUILogic(opts);
        }
        this.timerId = setInterval(() => {
            super.executeUILogic(opts);
        }, this.timer);
    }

    /**
     * 销毁计数器
     * @memberof AppTimerEngine
     */
    public destroyTimer() {
        if (this.timerId) {
            clearInterval(this.timerId);
        }
    }

}