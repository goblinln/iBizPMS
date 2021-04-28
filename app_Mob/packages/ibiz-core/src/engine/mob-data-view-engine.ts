import { MDViewEngineBase } from './md-view-engine-base';

/**
 * 实体移动端卡片视图界面引擎
 *
 * @export
 * @class MobDataViewEngine
 * @extends {MDViewEngineBase}
 */
export class MobDataViewEngine extends MDViewEngineBase {
    /**
     * 表格部件
     *
     * @type {*}
     * @memberof MobDataViewEngine
     */
    protected dataview: any;


    /**
     * Creates an instance of MobDataViewEngine.
     * @memberof MobDataViewEngine
     */
    constructor() {
        super();
    }

    /**
     * 引擎初始化
     *
     * @param {*} [options={}]
     * @memberof MobDataViewEngine
     */
    public init(options: any = {}): void {
        this.dataview = options.dataview;
        super.init(options);
    }

    /**
     * 多数据部件
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof MobDataViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        if (Object.is(ctrlName, 'dataview')) {
            this.MDCtrlEvent(eventName, args);
        }
        super.onCtrlEvent(ctrlName, eventName, args);
    }

    /**
     * 获取多数据部件
     *
     * @returns {*}
     * @memberof MobDataViewEngine
     */
    public getMDCtrl(): any {
        return this.dataview;
    }
}