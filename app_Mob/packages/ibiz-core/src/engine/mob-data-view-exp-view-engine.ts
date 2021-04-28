import { ViewEngine } from './view-engine';
/**
 * 实体移动端多数据界面引擎
 *
 * @export
 * @class MDViewEngine
 * @extends {ViewEngine}
 */
export class MobDataViewExpViewEngine extends ViewEngine {

    /**
     * 卡片导航栏部件
     *
     * @type {*}
     * @memberof GridViewEngine
     */
    protected dataviewExpBar: any = null;


    /**
     * Creates an instance of GridViewEngine.
     * @memberof GridViewEngine
     */
    constructor() {
        super();
    }

    /**
     * 引擎初始化
     *
     * @param {*} [options={}]
     * @memberof GridViewEngine
     */
    public init(options: any = {}): void {
        this.dataviewExpBar = options.dataviewexpbar;
        super.init(options);
    }

    /**
     * 引擎加载
     *
     * @memberof DataViewExpViewEngine
     */
    public load(): void {
        super.load();
        if (this.getDataviewExpBar()) {
            const tag = this.getDataviewExpBar().name;
            this.setViewState2({ tag: tag, action: 'load', viewdata: { ...this.view.viewparams } });
        } else {
            this.isLoadDefault = true;
        }
    }

    /**
     * 
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof MobMDViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);
        if (Object.is(ctrlName, 'dataviewexpbar')) {
            this.dataviewExpBarEvent(eventName, args);
        }
    }

    /**
     * 卡片导航事件
     *
     * @param {string} eventName
     * @param {*} args
     * @memberof DataViewExpViewEngine
     */
    public dataviewExpBarEvent(eventName: string, args: any): void {
        if (Object.is(eventName, 'load')) {
            this.emitViewEvent('viewload', args);
        }
        if (Object.is(eventName, 'selectionchange')) {
            this.emitViewEvent('viewdataschange', args);
        }
        if (Object.is(eventName, 'activated')) {
            this.emitViewEvent('viewdatasactivated', args);
        }
    }

    /**
     * 获取卡片视图部件
     *
     * @returns {*}
     * @memberof MDViewEngine
     */
    public getDataviewExpBar(): any {
        return this.dataviewExpBar;
    }
}